package me.phazerous.testforhexelion.traps.traps;

import org.bukkit.Material;
import org.bukkit.potion.PotionEffect;

public abstract class TrapWithEffects extends AbstractTrap {
    protected final PotionEffect effect;
    protected int duration;
    protected Material surfaceMaterial;
    protected boolean[][] surfacePattern;

    public TrapWithEffects(PotionEffect effect) {
        super();
        this.effect = effect;
    }

    public int getDuration() {
        return duration;
    }

    public PotionEffect getEffect() {
        return effect;
    }

    public Material getSurfaceMaterial() {
        return surfaceMaterial;
    }

    public void setDuration(int seconds) {
        this.duration = seconds * 20;
    }

    public void setSurfacePattern(String[] surfacePattern) {
        int length = surfacePattern.length;
        int width = surfacePattern[0].length();

        this.surfacePattern = new boolean[length][width];

        for (int i = 0; i < length; i++) {
            String row = surfacePattern[i];

            for (int j = 0; j < width; j++) {
                char character = row.charAt(j);

                if (character == 'x') {
                    this.surfacePattern[i][j] = true;
                }
            }
        }
    }

    public boolean[][] getSurfacePattern() {
        return surfacePattern;
    }

    protected abstract String[] buildSurfacePattern();
}
