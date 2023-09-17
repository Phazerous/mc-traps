package me.phazerous.testforhexelion.traps.traps;

import org.bukkit.Material;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PoisonTrap extends TrapWithEffects {
    public PoisonTrap(int effectDuration) {
        super(new PotionEffect(PotionEffectType.POISON, effectDuration * 20, 1));
        setDuration(effectDuration);
        this.surfaceMaterial = Material.MOSSY_COBBLESTONE;
        setSurfacePattern(buildSurfacePattern());
    }

    protected String[] buildSurfacePattern() {
        return new String[] {
                "0x0x0",
                "x0x0x",
                "0x0x0",
                "x0x0x",
                "0x0x0",
        };
    }
}
