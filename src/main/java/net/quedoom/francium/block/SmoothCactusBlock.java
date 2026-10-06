package net.quedoom.francium.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.quedoom.francium.init.ModBlocks;

public class SmoothCactusBlock extends Block {
    public SmoothCactusBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return mayPlaceOn(state, level, pos);
    }

    protected boolean mayPlaceOn(final BlockState state, final BlockGetter level, final BlockPos pos) {
        BlockPos below = pos.below();
        if (level.getBlockState(below).is(Blocks.AIR)) return false;
        return canSupportRigidBlock(level, below) || canSupportCenter(((LevelReader) level), below, Direction.UP);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Block.column((double)14.0F, (double)0.0F, (double)16.0F);
    }
}
