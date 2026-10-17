package net.quedoom.francium.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.quedoom.francium.util.ColorHelper;
import org.jspecify.annotations.Nullable;

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
            ((Level) level).setBlockAndUpdate(pos, ColorHelper.toConcrete(this.dyeColor).defaultBlockState());
        } else {
            ((Level) level).setBlockAndUpdate(pos, ColorHelper.toConcretePowder(this.dyeColor).defaultBlockState());
        }
    }
}
