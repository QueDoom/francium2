package net.quedoom.francium.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.quedoom.francium.init.ModBlocks;

public class DustBlock extends Block {
    public DustBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (state.is(ModBlocks.FORBIDDEN_DUST) || state.is(ModBlocks.SPECIAL_FORBIDDEN_DUST)) {
               return box(2.5F, 0, 2.5F, 13.5F, 5, 13.5F);
        } else return box(5, 0, 5, 11, 5, 11);

    }

    @Override
    protected boolean canSurvive(final BlockState state, final LevelReader level, final BlockPos pos) {
        BlockPos below = pos.below();
        BlockState belowState = level.getBlockState(below);
        return this.canSurviveOn(level, below, belowState);
    }

    private boolean canSurviveOn(final BlockGetter level, final BlockPos relativePos, final BlockState relativeState) {
        return relativeState.isFaceSturdy(level, relativePos, Direction.UP);
    }
}
