package net.quedoom.francium.init;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.quedoom.francium.util.GlueMixerState;

public class ModProperties {

    public static final IntegerProperty PILE_OF_LEAVES_SEGMENTS = IntegerProperty.create("leaves", 0, 7);

    public static final EnumProperty<GlueMixerState> GLUE_MIXER_STATE = EnumProperty.create("glue_state", GlueMixerState.class);
    public static final IntegerProperty GLUE_MIXER_VEGETATION = IntegerProperty.create("vegetation", 0, 4);
    public static final IntegerProperty GLUE_MIXER_ECHO = IntegerProperty.create("echo", 0, 4);

    public static final BooleanProperty ANCIENT_BUNS_0 = BooleanProperty.create("ancient_buns_0");
    public static final BooleanProperty ANCIENT_BUNS_1 = BooleanProperty.create("ancient_buns_1");
    public static final BooleanProperty ANCIENT_BUNS_2 = BooleanProperty.create("ancient_buns_2");
    public static final BooleanProperty ANCIENT_BUNS_3 = BooleanProperty.create("ancient_buns_3");
    public static final BooleanProperty ANCIENT_BUNS_4 = BooleanProperty.create("ancient_buns_4");




}
