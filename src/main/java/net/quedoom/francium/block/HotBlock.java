package net.quedoom.francium.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.quedoom.francium.init.ModBlocks;

public class HotBlock extends Block {
    public static final IntegerProperty HEAT = IntegerProperty.create("heat", 0, 7);

    public HotBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(HEAT, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HEAT);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        int heat = state.getValue(HEAT);
        if (level.getBlockState(pos.below()).is(ModBlocks.STOKED_FIRE)) {
            if (heat < 7) {
                level.setBlockAndUpdate(pos, state.setValue(HEAT, heat + 1));
            } else {
                level.setBlockAndUpdate(pos, Blocks.LAVA.defaultBlockState());
            }
        } else {
            if (heat > 0) {
                level.setBlockAndUpdate(pos, state.setValue(HEAT, heat - 1));
            }
        }
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return true;
    }
}
