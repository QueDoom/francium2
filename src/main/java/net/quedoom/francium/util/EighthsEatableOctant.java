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

    public void set(Level level, BlockState state, BlockPos pos, boolean value) {
        BooleanProperty property;
        if (north) {
            if (east) {
                if (up) {
                    property = ModProperties.NORTH_EAST_UP;
                } else {
                    property = ModProperties.NORTH_EAST_DOWN;
                }
            } else {
                if (up) {
                    property = ModProperties.NORTH_WEST_UP;
                } else {
                    property = ModProperties.NORTH_WEST_DOWN;
                }
            }
        } else {
            if (east) {
                if (up) {
                    property = ModProperties.SOUTH_EAST_UP;
                } else {
                    property = ModProperties.SOUTH_EAST_DOWN;
                }
            } else {
                if (up) {
                    property = ModProperties.SOUTH_WEST_UP;
                } else {
                    property = ModProperties.SOUTH_WEST_DOWN;
                }
            }
        }
        level.setBlockAndUpdate(pos, state.setValue(property, value));
    }

    public boolean get(BlockState state) {
        BooleanProperty property;
        if (north) {
            if (east) {
                if (up) {
                    property = ModProperties.NORTH_EAST_UP;
                } else {
                    property = ModProperties.NORTH_EAST_DOWN;
                }
            } else {
                if (up) {
                    property = ModProperties.NORTH_WEST_UP;
                } else {
                    property = ModProperties.NORTH_WEST_DOWN;
                }
            }
        } else {
            if (east) {
                if (up) {
                    property = ModProperties.SOUTH_EAST_UP;
                } else {
                    property = ModProperties.SOUTH_EAST_DOWN;
                }
            } else {
                if (up) {
                    property = ModProperties.SOUTH_WEST_UP;
                } else {
                    property = ModProperties.SOUTH_WEST_DOWN;
                }
            }
        }
        return state.getValue(property);
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
