package net.quedoom.francium.util.sewer;

import net.minecraft.util.StringRepresentable;

public enum SewerArrowDirection implements StringRepresentable {
    STRAIGHT("straight"),
    LEFT("left"),
    RIGHT("right");


    private String name;

    SewerArrowDirection(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
