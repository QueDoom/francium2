package net.quedoom.francium.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.redstone.Orientation;
import net.quedoom.francium.block.entity.BellowBlockEntity;
import net.quedoom.francium.block.entity.FanBlockEntity;
import net.quedoom.francium.init.ModBlockEntities;
import org.jspecify.annotations.Nullable;

public class FanBlock extends BaseEntityBlock {
    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;

    public FanBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void neighborChanged(final BlockState state, final Level level, final BlockPos pos, final Block block, final @Nullable Orientation orientation, final boolean movedByPiston) {
        if (!level.isClientSide()) {
            if (!(level.getBlockEntity(pos) instanceof FanBlockEntity entity)) return;
            boolean isSpinning = entity.isSpinning();
            if (isSpinning != level.hasNeighborSignal(pos)) {
                entity.isSpinning(!isSpinning);
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getPlayer().isCrouching() ? context.getHorizontalDirection() : context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(FanBlock::new);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return new FanBlockEntity(worldPosition, blockState);
    }

    protected static <T extends BlockEntity> @Nullable BlockEntityTicker<T> createTicker(final Level level, final BlockEntityType<T> actualType, final BlockEntityType<? extends FanBlockEntity> expectedType) {
        BlockEntityTicker var10000;
        if (level instanceof ServerLevel serverLevel) {
            var10000 = createTickerHelper(actualType, expectedType, (innerLevel, pos, state, entity) -> FanBlockEntity.tick(serverLevel, state, pos, entity));
        } else {
            var10000 = null;
        }

        return var10000;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> type) {
        return createTicker(level, type, ModBlockEntities.FAN);
    }
}
