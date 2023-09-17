package me.phazerous.testforhexelion.traps.traps;

import org.bukkit.Material;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FireTrap extends TrapWithEffects {
    public FireTrap(int effectDuration) {
        super(new PotionEffect(PotionEffectType.BLINDNESS, effectDuration * 20, 1));
        setDuration(effectDuration);
        this.surfaceMaterial = Material.NETHERRACK;
        setSurfacePattern(buildSurfacePattern());
    }

    protected String[] buildSurfacePattern() {
        return new String[] {
                "xx00000xx",
                "xx00000xx",
                "000000000",
                "000000000",
                "000000000",
                "000000000",
                "000000000",
                "xx00000xx",
                "xx00000xx"
        };
    }
}
