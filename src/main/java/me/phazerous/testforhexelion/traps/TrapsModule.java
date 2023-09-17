package me.phazerous.testforhexelion.traps;

import me.phazerous.testforhexelion.traps.commands.CreateTrapCommand;
import me.phazerous.testforhexelion.traps.listeners.TrapActivateListener;
import me.phazerous.testforhexelion.traps.listeners.TrapPlaceListener;
import me.phazerous.testforhexelion.traps.utils.TrapUtils;
import org.bukkit.plugin.java.JavaPlugin;

public class TrapsModule {
    final private JavaPlugin plugin;

    public TrapsModule(JavaPlugin plugin) {
        this.plugin = plugin;

        TrapManager trapManager = new TrapManager();
        TrapUtils trapUtils = new TrapUtils(plugin);
        TrapCreator trapCreator = new TrapCreator(trapUtils, trapManager);

        plugin.getServer().getPluginManager().registerEvents(new TrapActivateListener(trapManager), plugin);
        plugin.getServer().getPluginManager().registerEvents(new TrapPlaceListener(trapUtils, trapManager), plugin);

        plugin.getCommand("createtrap").setExecutor(new CreateTrapCommand(trapCreator));
    }
}
