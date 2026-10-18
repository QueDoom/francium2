package net.quedoom.francium.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Fallable;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.quedoom.quet.misc.QTLogger;
import org.jspecify.annotations.Nullable;

public class FallingSlabBlock extends SlabBlock implements Fallable {
    private final String fullBlock;

    public FallingSlabBlock(String fullBlock, Properties properties) {
        super(properties);
        this.fullBlock = fullBlock;
    }

    @Override
    protected void onPlace(final BlockState state, final Level level, final BlockPos pos, final BlockState oldState, final boolean movedByPiston) {
        if (level.isClientSide()) return;

        if (state.getValue(TYPE) != SlabType.DOUBLE) {
            BlockState below = level.getBlockState(pos.below());
            if (below.is(this) && below.getValue(TYPE) == SlabType.BOTTOM) {
                level.setBlockAndUpdate(pos.below(), state.setValue(TYPE, SlabType.DOUBLE));
                level.removeBlock(pos, false);
                return;
            }
        }

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
            entity.dropItem = false;
            this.falling(entity);
        }
    }

    @Override
    public void onBrokenAfterFall(Level level, BlockPos pos, FallingBlockEntity entity) {
        dropResources(entity.blockState, level, pos);
    }

    public void makeFull(Level level, BlockPos pos) {
        Identifier id = Identifier.tryParse(this.fullBlock);
        Block block = BuiltInRegistries.BLOCK.getValue(id);
        level.setBlockAndUpdate(pos, block.defaultBlockState());
    }

    protected void falling(final FallingBlockEntity entity) {
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = super.getStateForPlacement(context);
        if (state.getValue(TYPE).equals(SlabType.TOP)) {
            return state.setValue(TYPE, SlabType.BOTTOM);
        }
        return state;
    }
}
