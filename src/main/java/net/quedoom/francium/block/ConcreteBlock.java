package net.quedoom.francium.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.quedoom.francium.util.ColorHelper;

public class ConcreteBlock extends AbstractColoredBlock {
    public ConcreteBlock(DyeColor color, Properties properties) {
        super(color, properties);
    }

    @Override
    protected void drop(Level level, BlockPos pos, BlockState state, Player player, ItemStack destroyedWith) {
    }

    @Override
    public void destroy(LevelAccessor level, BlockPos pos, BlockState state) {
        if (PowderedConcreteBlock.touchesLiquid(level, pos)) {
            ((Level) level).setBlockAndUpdate(pos, this.color.toConcrete().defaultBlockState());
        } else {
            ((Level) level).setBlockAndUpdate(pos, this.color.toConcretePowder().defaultBlockState());
        }
    }
}
