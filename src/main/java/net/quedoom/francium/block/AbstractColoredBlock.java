package net.quedoom.francium.block;

import net.minecraft.core.BlockPos;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;

public abstract class AbstractColoredBlock extends Block {
    protected final DyeColor dyeColor;

    public AbstractColoredBlock(DyeColor color, Properties properties) {
        super(properties);
        this.dyeColor = color;
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack destroyedWith) {
        player.awardStat(Stats.BLOCK_MINED.get(this));
        player.causeFoodExhaustion(0.005F);
        drop(level, pos, state, player, destroyedWith);
    }

    protected abstract void drop(Level level, BlockPos pos, BlockState state, Player player, ItemStack destroyedWith);
}
