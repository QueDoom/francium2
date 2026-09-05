package net.quedoom.francium.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.quedoom.francium.block.entity.BundleTableEntity;
import net.quedoom.francium.init.ModProperties;
import net.quedoom.francium.init.ModStats;
import org.jspecify.annotations.Nullable;

public class BundleTableBlock extends BaseEntityBlock {
    public static final BooleanProperty OPEN = ModProperties.OPEN;
    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;

    public BundleTableBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(OPEN, false).setValue(FACING, Direction.NORTH));
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(OPEN, FACING);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(BundleTableBlock::new);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return new BundleTableEntity(worldPosition, blockState);
    }

    @Override
    public void destroy(LevelAccessor level, BlockPos pos, BlockState state) {
        BlockEntity entity = level.getBlockEntity(pos);
        if (entity instanceof BundleTableEntity)
            Containers.dropContents(((Level) level), entity.getBlockPos(), ((BundleTableEntity) entity).getContainer());
    }

    @Override
    protected InteractionResult useWithoutItem(final BlockState state, final Level level, final BlockPos pos, final Player player, final BlockHitResult hitResult) {
        if (level instanceof ServerLevel serverLevel) {
            MenuProvider menuProvider = this.getMenuProvider(state, level, pos);
            if (menuProvider != null) {
                player.openMenu(menuProvider);
                player.awardStat(ModStats.INTERACTION_WITH_BUNDLE_TABLE);
            }
        }

        return InteractionResult.SUCCESS;
    }
}
