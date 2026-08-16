package net.quedoom.francium.block;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SegmentableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.quedoom.francium.init.ModProperties;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Map;
import java.util.function.Function;

public class PileOfLeavesBlock extends Block {
    int MIN_SEGMENT = 1;
    int MAX_SEGMENT = 8;
    IntegerProperty AMOUNT = ModProperties.PILE_OF_LEAVES_SEGMENTS;

    public PileOfLeavesBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(AMOUNT, 1));
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = context.getLevel().getBlockState(context.getClickedPos());
        return this.defaultBlockState().setValue(AMOUNT, state.getValue(AMOUNT) + 1);
    }

    @Override
    protected boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        return state.getValue(AMOUNT) != 8;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AMOUNT);
    }
}
