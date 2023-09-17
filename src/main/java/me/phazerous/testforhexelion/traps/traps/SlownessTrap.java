package me.phazerous.testforhexelion.traps.traps;

import org.bukkit.Material;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SlownessTrap extends TrapWithEffects {
    public SlownessTrap(int effectDuration) {
        super(new PotionEffect(PotionEffectType.SLOW, effectDuration * 20, 1));
        setDuration(effectDuration);
        this.surfaceMaterial = Material.ICE;
        setSurfacePattern(buildSurfacePattern());
    }

    protected String[] buildSurfacePattern() {
        return new String[] {
                "0xxx0",
                "xxxxx",
                "xxxxx",
                "xxxxx",
                "0xxx0",
        };
    }
}
