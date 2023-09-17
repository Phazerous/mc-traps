package me.phazerous.testforhexelion.traps.listeners;

import me.phazerous.testforhexelion.traps.TrapManager;
import me.phazerous.testforhexelion.traps.utils.TrapUtils;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class TrapPlaceListener implements Listener {
    private final TrapUtils trapUtils;
    private final TrapManager trapManager;

    public TrapPlaceListener(TrapUtils trapUtils, TrapManager trapManager) {
        this.trapUtils = trapUtils;
        this.trapManager = trapManager;
    }

    @EventHandler
    public void onTrapPlace(BlockPlaceEvent e) {
        Player player = e.getPlayer();

        ItemStack item = player.getInventory().getItemInMainHand();

        if (!trapUtils.hasTrapId(item)) return;

        String trapId = trapUtils.getTrapId(item);
        Location placedLocation = e.getBlockPlaced().getLocation();

        if (!trapManager.placeTrap(trapId, placedLocation)) {
            player.sendMessage("Something went wrong when you were setting the trap!");
            return;
        }

        if (item.getAmount() > 1) item.setAmount(item.getAmount() - 1);
        else player.getInventory().remove(item);

        e.setCancelled(true);

        player.sendMessage("You have placed a trap!");
    }
}
