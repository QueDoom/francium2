package net.quedoom.francium.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.quedoom.francium.block.entity.DeepMergerEntity;
import net.quedoom.francium.block.menu.DeepMergerMenu;
import net.quedoom.francium.init.ModStats;
import org.jspecify.annotations.Nullable;

public class DeepMergerBlock extends BaseEntityBlock {
    public DeepMergerBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(DeepMergerBlock::new);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return new DeepMergerEntity(worldPosition, blockState);
    }

    protected InteractionResult useWithoutItem(final BlockState state, final Level level, final BlockPos pos, final Player player, final BlockHitResult hitResult) {
        if (!level.isClientSide()) {
            player.openMenu(state.getMenuProvider(level, pos));
            player.awardStat(ModStats.INTERACTION_WITH_DEEP_MERGER);
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    protected @Nullable MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        return new SimpleMenuProvider((containerId, inventory, player) -> new DeepMergerMenu(containerId, inventory, ContainerLevelAccess.create(level, pos)), Component.translatable("menu.francium_2.merging"));
    }
}
