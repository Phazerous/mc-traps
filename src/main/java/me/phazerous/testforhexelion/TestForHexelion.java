package me.phazerous.testforhexelion;

import me.phazerous.testforhexelion.traps.TrapsModule;
import org.bukkit.plugin.java.JavaPlugin;

public final class TestForHexelion extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        new TrapsModule(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
