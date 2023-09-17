package me.phazerous.testforhexelion.traps;

import me.phazerous.testforhexelion.traps.traps.TrapWithEffects;
import me.phazerous.testforhexelion.utils.LocationUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.*;

public class TrapManager {

    private final List<TrapWithEffects> traps = new ArrayList<>();

    public void registerTrap(TrapWithEffects trap) {
        traps.add(trap);
    }

    public boolean placeTrap(String id, Location location) {
        Optional<TrapWithEffects> abstractTrap = traps.stream().filter(trap -> trap.getId().equalsIgnoreCase(id))
                .findFirst();

        if (!abstractTrap.isPresent()) return false;

        Location integerLocation = LocationUtils.getIntegerLocation(location);
        abstractTrap.get().setLocation(integerLocation);

        return true;
    }

    private TrapWithEffects getTrapById(String id) {
        Optional<TrapWithEffects> abstractTrap = traps.stream().filter(trap -> trap.getId().equalsIgnoreCase(id))
                .findFirst();

        return abstractTrap.orElse(null);
    }

    public String getTrapIdByLocation(Location location) {
        Location intergerLocation = LocationUtils.getIntegerLocation(location);

        Optional<TrapWithEffects> abstractTrap = traps.stream().filter(trap -> {
            Location trapLocation = trap.getLocation();

            if (trapLocation == null) return false;
            return LocationUtils.isSameLocation(trap.getLocation(), intergerLocation);
        }).findFirst();

        return abstractTrap.map(TrapWithEffects::getId).orElse(null);
    }

    public boolean handleTrapActivation(String trapId, Player player) {
        TrapWithEffects trap = getTrapById(trapId);

        if (trap == null) return false;

        player.addPotionEffect(trap.getEffect());
        surroundAreaWithBlocks(trap);

        traps.removeIf(it -> it.getId().equalsIgnoreCase(trap.getId()));
        return true;
    }

    public void surroundAreaWithBlocks(TrapWithEffects trap) {
        Location location = trap.getLocation();
        Material blockMaterial = trap.getSurfaceMaterial();
        int duration = trap.getDuration();

        boolean[][] pattern = trap.getSurfacePattern();
        int patternXLength = pattern[0].length;
        int patternZLength = pattern.length;

        int startX = location.getBlockX() - (patternXLength / 2);
        int startZ = location.getBlockZ() - (patternZLength / 2);

        for (int z = 0; z < patternZLength; z++) {
            for (int x = 0; x < patternXLength; x++) {
                Block block = location.getWorld().getBlockAt(startX + x, location.getBlockY() - 1, startZ + z);
                Material previousBlockMaterial = block.getType();

                if (pattern[z][x]) {
                    block.setType(blockMaterial);
                    //COULD BE OPTIMIZED BY USING A SINGLE TASK
                    Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("TestForHexelion"), () -> {
                        block.setType(previousBlockMaterial);
                    }, duration);
                }
            }
        }
    }
}
