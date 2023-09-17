package me.phazerous.testforhexelion.traps.traps;

import org.bukkit.Location;

import java.util.UUID;

abstract public class AbstractTrap {
    protected final String id;
    protected Location location;

    public AbstractTrap() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
}
