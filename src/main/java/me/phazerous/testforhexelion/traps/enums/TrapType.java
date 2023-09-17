package me.phazerous.testforhexelion.traps.enums;

import me.phazerous.testforhexelion.traps.traps.FireTrap;
import me.phazerous.testforhexelion.traps.traps.PoisonTrap;
import me.phazerous.testforhexelion.traps.traps.SlownessTrap;
import me.phazerous.testforhexelion.traps.traps.TrapWithEffects;

public enum TrapType {
    SLOWNESS_TRAP("Slowness Trap", "slows down", SlownessTrap.class),
    PoisonTrap("Poison Trap", "poisons", PoisonTrap.class),
    FireTrap("Fire Trap", "burns", FireTrap.class);

    private final String name;
    private final String actionString;
    private final Class<? extends TrapWithEffects> trapClass;

    TrapType(String name, String actionString, Class<? extends TrapWithEffects> trapClass) {
        this.name = name;
        this.actionString = actionString;
        this.trapClass = trapClass;
    }

    public String getName() {
        return this.name;
    }

    public String getActionString() {
        return this.actionString;
    }

    public Class<? extends TrapWithEffects> getTrapClass() {
        return this.trapClass;
    }
}
