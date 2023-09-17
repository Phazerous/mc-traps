package me.phazerous.testforhexelion.traps.listeners;

import me.phazerous.testforhexelion.traps.TrapManager;
import me.phazerous.testforhexelion.traps.utils.TrapUtils;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class TrapActivateListener implements Listener {

    private final TrapManager trapManager;

    public TrapActivateListener(TrapManager trapManager) {
        this.trapManager = trapManager;
    }

    @EventHandler
    void onPlayerMove(PlayerMoveEvent e) {

        Location toLocation = e.getTo();

        String trapId = trapManager.getTrapIdByLocation(toLocation);

        if (trapId == null) return;

        if (trapManager.handleTrapActivation(trapId, e.getPlayer()))  e.getPlayer().sendMessage("You have entered the trap!");
    }
}
