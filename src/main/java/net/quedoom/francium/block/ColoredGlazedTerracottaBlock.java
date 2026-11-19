package net.quedoom.francium.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.quedoom.francium.util.ColorHelper;

public class ColoredGlazedTerracottaBlock extends AbstractColoredBlock {
    public ColoredGlazedTerracottaBlock(ColorHelper color, Properties properties) {
        super(color, properties);
    }

    public ColoredGlazedTerracottaBlock(DyeColor color, Properties properties) {
        super(color, properties);
    }

    @Override
    protected void drop(Level level, BlockPos pos, BlockState state, Player player, ItemStack destroyedWith) {

    }
}
