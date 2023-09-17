package me.phazerous.testforhexelion.traps;

import me.phazerous.testforhexelion.traps.enums.TrapType;
import me.phazerous.testforhexelion.traps.traps.TrapWithEffects;
import me.phazerous.testforhexelion.traps.utils.TrapUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class TrapCreator {
    private final TrapUtils trapUtils;
    private final TrapManager trapManager;

    public TrapCreator(TrapUtils trapUtils, TrapManager trapManager) {
        this.trapUtils = trapUtils;
        this.trapManager = trapManager;
    }

    public ItemStack createTrap(TrapType trapType, int seconds) {
        try {
            Class<? extends TrapWithEffects> potionEffectClass = trapType.getTrapClass();
            Constructor<? extends TrapWithEffects> constructor = potionEffectClass.getConstructor(int.class);

            TrapWithEffects trapInstance = constructor.newInstance(seconds);

            trapManager.registerTrap(trapInstance);

            ItemStack trap = buildTrap(trapType, seconds);
            trapUtils.setTrapId(trap, trapInstance.getId());

            return trap;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private ItemStack buildTrap(TrapType trapType, int seconds) {
        final Material TRAP_MATERIAL = Material.LIGHT_WEIGHTED_PRESSURE_PLATE;

        ItemStack itemStack = new ItemStack(TRAP_MATERIAL);

        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(trapType.getName());

        List<String> lore = new ArrayList<>();
        String trapDescription = String.format("§r§5The trap %s a player for %s seconds.", trapType.getActionString(), seconds);

        lore.add("");
        lore.add(ChatColor.translateAlternateColorCodes('§', trapDescription));

        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
}
