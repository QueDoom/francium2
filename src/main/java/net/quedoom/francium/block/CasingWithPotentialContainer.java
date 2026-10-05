package net.quedoom.francium.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.quedoom.francium.block.entity.BlockContainingEntity;
import net.quedoom.francium.block.entity.BlockContainingItemsEntity;
import org.jspecify.annotations.NonNull;

public abstract class CasingWithPotentialContainer extends Block {
    public CasingWithPotentialContainer(Properties properties) {
        super(properties);
    }

    public boolean placeContainingBlock(Level level, BlockPos pos, BlockState state, ItemStack stack) {
        if (!state.is(this)) return false;
        level.setBlockAndUpdate(pos.below(), containingBlock().defaultBlockState());
        if (!(level.getBlockEntity(pos.below()) instanceof BlockContainingEntity container)) return false;

        if (container.isEmpty()) {
            container.setTheItem(stack);
            return true;
        }
        return false;
    }

    public boolean placeContainingItems(Level level, BlockPos pos, BlockState state, ItemStack stack) {
        if (!state.is(this)) return false;
        level.setBlockAndUpdate(pos.below(), containingItems().defaultBlockState());
        if (!(level.getBlockEntity(pos.below()) instanceof BlockContainingItemsEntity container)) return false;

        return container.placeInAvailableSlots(stack);
    }

    protected abstract @NonNull Block containingBlock();
    protected abstract @NonNull Block containingItems();

}
