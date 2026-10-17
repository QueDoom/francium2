package net.quedoom.francium.block;

import net.minecraft.core.BlockPos;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;

public class WoolCarpetBlock extends AbstractColoredBlock {
    public WoolCarpetBlock(DyeColor color, Properties properties) {
        super(color, properties);
    }

    @Override
    protected void drop(Level level, BlockPos pos, BlockState state, Player player, ItemStack destroyedWith) {
        dropResources(Blocks.WHITE_CARPET.defaultBlockState(), level, pos, null, player, destroyedWith);
    }
}
