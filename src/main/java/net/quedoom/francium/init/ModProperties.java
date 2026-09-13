package net.quedoom.francium.init;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.quedoom.francium.util.WoodenMixerState;

public class ModProperties {

    public static final IntegerProperty PILE_OF_LEAVES_SEGMENTS = IntegerProperty.create("leaves", 0, 7);

    public static final EnumProperty<WoodenMixerState> WOODEN_MIXER_STATE = EnumProperty.create("glue_state", WoodenMixerState.class);
    public static final IntegerProperty WOODEN_MIXER_VEGETATION = IntegerProperty.create("vegetation", 0, 4);
    public static final IntegerProperty WOODEN_MIXER_ECHO = IntegerProperty.create("echo", 0, 4);
    public static final IntegerProperty WOODEN_MIXER_STEEL = IntegerProperty.create("steel", 0, 6);

    public static final BooleanProperty ANCIENT_BUNS_0 = BooleanProperty.create("ancient_buns_0");
    public static final BooleanProperty ANCIENT_BUNS_1 = BooleanProperty.create("ancient_buns_1");
    public static final BooleanProperty ANCIENT_BUNS_2 = BooleanProperty.create("ancient_buns_2");
    public static final BooleanProperty ANCIENT_BUNS_3 = BooleanProperty.create("ancient_buns_3");
    public static final BooleanProperty ANCIENT_BUNS_4 = BooleanProperty.create("ancient_buns_4");

    public static final BooleanProperty OPEN = BooleanProperty.create("open");

    public static final BooleanProperty TOP = BooleanProperty.create("top");

    public static final BooleanProperty NORTH_EAST_DOWN = BooleanProperty.create("north_east_down");
    public static final BooleanProperty NORTH_WEST_DOWN = BooleanProperty.create("north_west_down");
    public static final BooleanProperty SOUTH_EAST_DOWN = BooleanProperty.create("south_east_down");
    public static final BooleanProperty SOUTH_WEST_DOWN = BooleanProperty.create("south_west_down");
    public static final BooleanProperty NORTH_EAST_UP = BooleanProperty.create("north_east_up");
    public static final BooleanProperty NORTH_WEST_UP = BooleanProperty.create("north_west_up");
    public static final BooleanProperty SOUTH_EAST_UP = BooleanProperty.create("south_east_up");
    public static final BooleanProperty SOUTH_WEST_UP = BooleanProperty.create("south_west_up");



}
