package net.quedoom.francium.block;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.quedoom.francium.block.menu.WoodenMergerMenu;
import net.quedoom.francium.init.ModStats;

public class WoodenMergerBlock extends Block {
    public WoodenMergerBlock(Properties properties) {
        super(properties);
    }

    protected InteractionResult useWithoutItem(final BlockState state, final Level level, final BlockPos pos, final Player player, final BlockHitResult hitResult) {
        if (!level.isClientSide()) {
            player.openMenu(state.getMenuProvider(level, pos));
            player.awardStat(ModStats.INTERACTION_WITH_WOODEN_MERGER);
        }

        return InteractionResult.SUCCESS;
    }

    protected MenuProvider getMenuProvider(final BlockState state, final Level level, final BlockPos pos) {
        return new SimpleMenuProvider((containerId, inventory, player) -> new WoodenMergerMenu(containerId, inventory, ContainerLevelAccess.create(level, pos)), Component.translatable("menu.francium_2.merging"));
    }
}
