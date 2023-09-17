package me.phazerous.testforhexelion.utils;

import org.bukkit.Location;
import org.bukkit.World;

import java.util.List;

public class LocationUtils {
    public static boolean isSameLocation(Location location1, Location location2) {
        Location rawLocation1 = removeDecimal(location1);
        Location rawLocation2 = removeDecimal(location2);

        return rawLocation1.getX() == rawLocation2.getX() && rawLocation1.getY() == rawLocation2.getY() && rawLocation1.getZ() == rawLocation2.getZ();
    }

    public static Location removeDecimal(Location location) {
        return new Location(location.getWorld(), location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    public static Location getIntegerLocation(Location location) {
        World world = location.getWorld();
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();

        return new Location(world, x, y, z);
    }
}
