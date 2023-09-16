package me.phazerous.testforhexelion.traps;

import me.phazerous.testforhexelion.utils.LocationUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TrapsManager {

    private final List<Location> trapsLocation = new ArrayList<>();
    private final HashMap<String, Location> traps = new HashMap<>();

    public void registerTrap(String id) {
        traps.put(id, null);
    }

    public void placeTrap(Location location) {
        Location trapLocation = LocationUtils.getIntegerLocation(location);
        trapsLocation.add(trapLocation);
    }

    public boolean isTrapLocation(Location location) {
        Location trapLocation = LocationUtils.getIntegerLocation(location);
        return LocationUtils.listContainsLocation(trapLocation, trapsLocation);
    }

    public void handleTrapActivation(Location trapLocation, Player player) {
        LocationUtils.removeLocationFromList(trapLocation, trapsLocation);

        Material trapMaterial = Material.GLASS;
        int secondsUntilRespawn = 3;

        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20 * secondsUntilRespawn, 1));

        surroundAreaWithBlocks(player.getLocation(), trapMaterial, secondsUntilRespawn);
    }

    public void surroundAreaWithBlocks(Location location, Material blockMaterial, int secondsUntilRespawn) {
        boolean[][] pattern = getAreaPattern();
        int patternXLength = pattern[0].length;
        int patternZLength = pattern.length;

        int startX = location.getBlockX() - (patternXLength / 2);
        int startZ = location.getBlockZ() - (patternZLength / 2);

        for (int z = 0; z < patternZLength; z++) {
            for (int x = 0; x < patternXLength; x++) {
                Block block = location.getWorld().getBlockAt(startX + x, location.getBlockY(), startZ + z);
                Material previousBlockMaterial = block.getType();

                if (pattern[z][x]) {
                    block.setType(blockMaterial);
                    Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("TestForHexelion"), () -> {
                        block.setType(previousBlockMaterial);
                    }, secondsUntilRespawn * 20);
                }
            }
        }
    }

    private boolean[][] getAreaPattern() {
        // --> X
        // |
        // V Z
        return new boolean[][] {
                {false, true, true, true, false},
                {true, true, true, true, true},
                {true, true, true, true, true},
                {true, true, true, true, true},
                {false, true, true, true, false}
        };
    }
}
