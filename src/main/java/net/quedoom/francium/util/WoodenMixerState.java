package net.quedoom.francium.util;

import net.minecraft.util.StringRepresentable;

public enum WoodenMixerState implements StringRepresentable {
    EMPTY("empty"),
    SLIME("slime"),
    HONEY("honey");

    private final String name;

    WoodenMixerState(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
