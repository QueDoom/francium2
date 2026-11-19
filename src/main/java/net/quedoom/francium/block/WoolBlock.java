package net.quedoom.francium.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.quedoom.francium.util.ColorHelper;

public class WoolBlock extends AbstractColoredBlock {
    public WoolBlock(ColorHelper color, Properties properties) {
        super(color, properties);
    }
    public WoolBlock(DyeColor color, Properties properties) {
        super(color, properties);
    }

    @Override
    protected void drop(Level level, BlockPos pos, BlockState state, Player player, ItemStack destroyedWith) {
        dropResources(Blocks.WHITE_WOOL.defaultBlockState(), level, pos, null, player, destroyedWith);
        Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), this.color.toItem().getDefaultInstance());
    }
}
