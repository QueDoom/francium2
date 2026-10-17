package net.quedoom.francium.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.BlockHitResult;
import net.quedoom.francium.block.entity.BellowBlockEntity;
import net.quedoom.francium.block.entity.StokedFireEntity;
import net.quedoom.francium.init.ModBlockEntities;
import org.jspecify.annotations.Nullable;
import org.w3c.dom.Entity;

public class StokedFireBlock extends BaseFireBlock implements EntityBlock {
    public static final BooleanProperty SHORT = BooleanProperty.create("short");

    public StokedFireBlock(Properties properties) {
        super(properties, 3.5f);
        this.registerDefaultState(this.getStateDefinition().any().setValue(SHORT, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(SHORT);
    }

    @Override
    protected boolean canBeReplaced(BlockState state, Fluid fluid) {
        return true;
    }

    @Override
    protected MapCodec<? extends BaseFireBlock> codec() {
        return simpleCodec(StokedFireBlock::new);
    }

    @Override
    protected boolean canBurn(BlockState state) {
        return ((FireBlock) Blocks.FIRE).canBurn(state);
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, @Nullable Orientation orientation, boolean movedByPiston) {
         level.setBlock(pos, state.setValue(SHORT, !level.getBlockState(pos.above()).is(BlockTags.AIR)), UPDATE_CLIENTS);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return new StokedFireEntity(worldPosition, blockState);
    }

    protected static <T extends BlockEntity> @Nullable BlockEntityTicker<T> createTicker(final Level level, final BlockEntityType<T> actualType, final BlockEntityType<? extends StokedFireEntity> expectedType) {
        BlockEntityTicker var10000;
        if (level instanceof ServerLevel serverLevel) {
            var10000 = createTickerHelper(actualType, expectedType, (innerLevel, pos, state, entity) -> StokedFireEntity.tick(serverLevel, pos, state, entity));
        } else {
            var10000 = null;
        }

        return var10000;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> type) {
        return createTicker(level, type, ModBlockEntities.STOKED_FIRE_ENTITY);
    }

    @SuppressWarnings("unchecked")
    protected static <E extends BlockEntity, A extends BlockEntity> @Nullable BlockEntityTicker<A> createTickerHelper(
            final BlockEntityType<A> actual, final BlockEntityType<E> expected, final BlockEntityTicker<? super E> ticker
    ) {
        return expected == actual ? (BlockEntityTicker<A>) ticker : null;
    }
}