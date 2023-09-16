package me.phazerous.testforhexelion.traps.listeners;

import me.phazerous.testforhexelion.traps.TrapManager;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class TrapActivatedListener implements Listener {
    
    private final TrapManager trapManager;

    public TrapActivatedListener(TrapManager trapManager) {
        this.trapManager = trapManager;
    }

    @EventHandler
    void onPlayerMove(PlayerMoveEvent e) {

        Location toLocation = e.getTo();

        if (trapManager.isTrapLocation(toLocation)) {
            trapManager.handleTrapActivation(toLocation, e.getPlayer());
            e.getPlayer().sendMessage("You have entered the trap!");
        }
    }
}
