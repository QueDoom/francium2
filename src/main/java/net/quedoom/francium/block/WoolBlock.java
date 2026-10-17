package net.quedoom.francium.block;

import net.minecraft.core.BlockPos;
import net.minecraft.stats.Stats;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.quedoom.francium.util.ColorHelper;
import org.jspecify.annotations.Nullable;

public class WoolBlock extends AbstractColoredBlock {
    public WoolBlock(DyeColor color, Properties properties) {
        super(color, properties);
    }

    @Override
    protected void drop(Level level, BlockPos pos, BlockState state, Player player, ItemStack destroyedWith) {
        dropResources(Blocks.WHITE_WOOL.defaultBlockState(), level, pos, null, player, destroyedWith);
        Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), ColorHelper.toItem(this.dyeColor).getDefaultInstance());
    }
}
