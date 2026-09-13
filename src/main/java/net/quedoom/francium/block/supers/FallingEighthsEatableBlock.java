package net.quedoom.francium.block.supers;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Fallable;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;

public class FallingEighthsEatableBlock extends EighthsEatableBlock implements Fallable {
    public FallingEighthsEatableBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void onPlace(final BlockState state, final Level level, final BlockPos pos, final BlockState oldState, final boolean movedByPiston) {
        level.scheduleTick(pos, this, this.getDelayAfterPlace());
    }

    protected int getDelayAfterPlace() {
        return 2;
    }

    @Override
    protected BlockState updateShape(
            final BlockState state,
            final LevelReader level,
            final ScheduledTickAccess ticks,
            final BlockPos pos,
            final Direction directionToNeighbour,
            final BlockPos neighbourPos,
            final BlockState neighbourState,
            final RandomSource random
    ) {
        ticks.scheduleTick(pos, this, this.getDelayAfterPlace());
        return super.updateShape(state, level, ticks, pos, directionToNeighbour, neighbourPos, neighbourState, random);
    }

    @Override
    protected void tick(final BlockState state, final ServerLevel level, final BlockPos pos, final RandomSource random) {
        if (FallingBlock.isFree(level.getBlockState(pos.below())) && pos.getY() >= level.getMinY()) {
            FallingBlockEntity entity = FallingBlockEntity.fall(level, pos, state);
            this.falling(entity);
        }
    }



    protected void falling(final FallingBlockEntity entity) {
    }
}
