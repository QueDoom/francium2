package net.quedoom.francium.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.quedoom.francium.block.entity.BellowBlockEntity;
import net.quedoom.francium.block.entity.ObsidianMixerEntity;
import net.quedoom.francium.init.ModBlockEntities;
import net.quedoom.francium.init.ModBlocks;
import org.jspecify.annotations.Nullable;

public class ObsidianMixerBlock extends BaseEntityBlock {
    public static final IntegerProperty HEAT = IntegerProperty.create("heat", 0, 12);

    public ObsidianMixerBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(HEAT, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HEAT);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(ObsidianMixerBlock::new);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return new ObsidianMixerEntity(worldPosition, blockState);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState onState, Entity entity) {
        if (!(entity instanceof ItemEntity item)) return;
        if (((ObsidianMixerEntity) level.getBlockEntity(pos)).addItem(item.getItem())) {
            item.getItem().shrink(1);
        }
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        int heat = state.getValue(HEAT);
        if (level.getBlockState(pos.below()).is(ModBlocks.STOKED_FIRE)) {
            if (heat < 12) {
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
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> type) {
        return createTicker(level, type, ModBlockEntities.OBSIDIAN_MIXER_ENTITY);
    }

    protected static <T extends BlockEntity> @Nullable BlockEntityTicker<T> createTicker(final Level level, final BlockEntityType<T> actualType, final BlockEntityType<? extends ObsidianMixerEntity> expectedType) {
        return createTickerHelper(actualType, expectedType, (innerLevel, pos, state, entity) -> ObsidianMixerEntity.tick(level, pos, state, entity, level.getBlockState(pos.below())));
    }

    @Override
    public void destroy(LevelAccessor level, BlockPos pos, BlockState state) {
        if (level.getBlockEntity(pos) instanceof ObsidianMixerEntity entity && entity.getAvailableSlots() > 0) {
            ((Level) level).setBlockAndUpdate(pos, state);
        }
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack destroyedWith) {
        player.awardStat(Stats.BLOCK_MINED.get(this));
        player.causeFoodExhaustion(0.005F);
        if (blockEntity instanceof ObsidianMixerEntity entity && entity.getAvailableSlots() > 0) {
            dropResources(state, level, pos, blockEntity, player, destroyedWith);
        }
    }
}
