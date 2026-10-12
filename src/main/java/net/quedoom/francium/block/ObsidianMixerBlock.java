package net.quedoom.francium.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.quedoom.francium.block.entity.BellowBlockEntity;
import net.quedoom.francium.block.entity.ObsidianMixerEntity;
import net.quedoom.francium.init.ModBlockEntities;
import org.jspecify.annotations.Nullable;

public class ObsidianMixerBlock extends BaseEntityBlock {
    public ObsidianMixerBlock(Properties properties) {
        super(properties);
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
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> type) {
        return createTicker(level, type, ModBlockEntities.OBSIDIAN_MIXER_ENTITY);
    }

    protected static <T extends BlockEntity> @Nullable BlockEntityTicker<T> createTicker(final Level level, final BlockEntityType<T> actualType, final BlockEntityType<? extends ObsidianMixerEntity> expectedType) {
        BlockEntityTicker<T> var10000;
        if (level instanceof ServerLevel serverLevel) {
            var10000 = createTickerHelper(actualType, expectedType, (innerLevel, pos, state, entity) -> ObsidianMixerEntity.tick(serverLevel, pos, state, entity));
        } else {
            var10000 = null;
        }

        return var10000;
    }
}
