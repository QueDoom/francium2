package net.quedoom.francium.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.quedoom.francium.init.ModBlockEntities;

public class BlockContainingEntity extends BlockEntity implements Container {
    public static final NonNullList<ItemStack> containerList = NonNullList.withSize(1, ItemStack.EMPTY);

    public BlockContainingEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.BLOCK_CONTAINING_ENTITY, worldPosition, blockState);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        ContainerHelper.saveAllItems(output, containerList);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        ContainerHelper.loadAllItems(input, containerList);
    }

    @Override
    public int getContainerSize() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return containerList.isEmpty();
    }

    @Override
    public ItemStack getItem(int slot) {
        return containerList.get(slot);
    }

    @Override
    public ItemStack removeItem(int slot, int count) {
        if (getItem(slot).getCount() < count) {
            throw new IllegalArgumentException("Count cannot exceed the amount of items in Container");
        } else if (getItem(slot).getCount() == count) {
            setChanged();
            return removeItemNoUpdate(slot);
        } else if (getItem(slot).getCount() > count) {
            setChanged();
            return containerList.set(slot, getItem(slot).copyWithCount(getItem(slot).getCount() - count));
        }
        return containerList.get(slot);
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return containerList.set(slot, ItemStack.EMPTY);
    }

    @Override
    public void setItem(int slot, ItemStack itemStack) {
        containerList.set(slot, itemStack);
        setChanged();
    }

    @Override
    public void setChanged() {
        setChanged(this.level, this.worldPosition, this.level.getBlockState(this.worldPosition));
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public void clearContent() {
        containerList.clear();
    }
}
