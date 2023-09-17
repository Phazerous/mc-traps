package me.phazerous.testforhexelion.traps.utils;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class TrapUtils {
    private final JavaPlugin plugin;
    private final NamespacedKey trapIdNamespaceKey;

    public TrapUtils(JavaPlugin plugin) {
        this.plugin = plugin;
        this.trapIdNamespaceKey = new NamespacedKey(plugin, "trap-id");
    }

    public String setTrapId(ItemStack itemStack, String id) {
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.getPersistentDataContainer().set(trapIdNamespaceKey, PersistentDataType.STRING, id);

        itemStack.setItemMeta(itemMeta);

        return id;
    }

    public boolean hasTrapId(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();

        return itemMeta.getPersistentDataContainer().has(trapIdNamespaceKey, PersistentDataType.STRING);
    }

    public String getTrapId(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();

        return itemMeta.getPersistentDataContainer().get(trapIdNamespaceKey, PersistentDataType.STRING);
    }
}
