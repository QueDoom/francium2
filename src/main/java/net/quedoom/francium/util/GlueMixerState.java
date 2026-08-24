package net.quedoom.francium.util;

import net.minecraft.util.StringRepresentable;

public enum GlueMixerState implements StringRepresentable {
    EMPTY("empty"),
    SLIME("slime"),
    HONEY("honey");

    private final String name;

    GlueMixerState(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
