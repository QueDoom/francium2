package net.quedoom.francium.block;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.quedoom.francium.block.menu.HeavyAnvilMenu;
import net.quedoom.francium.init.ModBlocks;
import org.jspecify.annotations.Nullable;

public class HeavyAnvilBlock extends AnvilBlock {
    private static final Component CONTAINER_TITLE = Component.translatable("container.repair");

    public HeavyAnvilBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @Nullable MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        return new SimpleMenuProvider(
                (containerId, inventory, player) -> new HeavyAnvilMenu(containerId, inventory, ContainerLevelAccess.create(level, pos)), CONTAINER_TITLE
        );
    }
}
