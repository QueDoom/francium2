package net.quedoom.francium.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.quedoom.francium.init.ModProperties;
import org.jetbrains.annotations.NotNull;

public record EighthsEatableOctant(boolean north, boolean east, boolean up) {
    public static final EighthsEatableOctant neu = new EighthsEatableOctant(true, true, true);
    public static final EighthsEatableOctant nwu = new EighthsEatableOctant(true, false, true);
    public static final EighthsEatableOctant seu = new EighthsEatableOctant(false, true, true);
    public static final EighthsEatableOctant swu = new EighthsEatableOctant(false, false, true);
    public static final EighthsEatableOctant ned = new EighthsEatableOctant(true, true, false);
    public static final EighthsEatableOctant nwd = new EighthsEatableOctant(true, false, false);
    public static final EighthsEatableOctant sed = new EighthsEatableOctant(false, true, false);
    public static final EighthsEatableOctant swd = new EighthsEatableOctant(false, false, false);

    public static EighthsEatableOctant ofChars(char ns, char ew, char ud) {
        return new EighthsEatableOctant(ns == 'n', ew == 'e', ud == 'u');
    }

    public BlockState getStem(BlockState state) {
        BooleanProperty property;
        BooleanProperty blockProperty;
        if (!up) return state;
        if (north) {
            if (east) blockProperty = ModProperties.NORTH_EAST_UP;
                 else blockProperty = ModProperties.NORTH_WEST_UP;
        } else {
            if (east) blockProperty = ModProperties.SOUTH_EAST_UP;
                else blockProperty = ModProperties.SOUTH_WEST_UP;
        }
        if (north) {
            if (east) property = ModProperties.STEM_NE;
                else property = ModProperties.STEM_NW;
        } else {
            if (east) property = ModProperties.STEM_SE;
                else property = ModProperties.STEM_SW;
        }
        return state.setValue(property, state.getValue(blockProperty));
    }

    public void set(Level level, BlockState state, BlockPos pos, boolean value) {
        level.setBlockAndUpdate(pos, state.setValue(getProperty(), value));
    }

    public void setStem(Level level, BlockState state, BlockPos pos, boolean value) {
        BlockState stateEaten = state.setValue(getProperty(), value);
        level.setBlockAndUpdate(pos, getStem(stateEaten));
    }

    public BooleanProperty getProperty() {
        BooleanProperty blockProperty;
        if (north) {
            if (east) {
                if (up) {
                    blockProperty = ModProperties.NORTH_EAST_UP;
                } else {
                    blockProperty = ModProperties.NORTH_EAST_DOWN;
                }
            } else {
                if (up) {
                    blockProperty = ModProperties.NORTH_WEST_UP;
                } else {
                    blockProperty = ModProperties.NORTH_WEST_DOWN;
                }
            }
        } else {
            if (east) {
                if (up) {
                    blockProperty = ModProperties.SOUTH_EAST_UP;
                } else {
                    blockProperty = ModProperties.SOUTH_EAST_DOWN;
                }
            } else {
                if (up) {
                    blockProperty = ModProperties.SOUTH_WEST_UP;
                } else {
                    blockProperty = ModProperties.SOUTH_WEST_DOWN;
                }
            }
        }
        return blockProperty;
    }

    public boolean get(BlockState state) {
        return state.getValue(getProperty());
    }

    @Override
    public @NotNull String toString() {
        char ns, ew, ud;
        ns = north ? 'n' : 's';
        ew = east ? 'e' : 'w';
        ud = up ? 'u' : 'd';
        return "EighthsEatableQuadrant[" + ns + ew + ud + ']';
    }
}
