package me.phazerous.testforhexelion.traps;

import me.phazerous.testforhexelion.traps.utils.TrapUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class TrapsCreator {
    private final TrapUtils trapUtils;
    private final TrapManager trapManager;

    public TrapsCreator(TrapUtils trapUtils, TrapManager trapManager) {
        this.trapUtils = trapUtils;
        this.trapManager = trapManager;
    }

    public ItemStack createTrap() {
        final Material TRAP_MATERIAL = Material.LIGHT_WEIGHTED_PRESSURE_PLATE;

        ItemStack itemStack = new ItemStack(TRAP_MATERIAL);

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Trap");

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('§', "§r§5This is a trap"));

        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        String trapId = trapUtils.setTrapId(itemStack);

//        trapManager.registerTrap(trapId);

        return itemStack;
    }
}
