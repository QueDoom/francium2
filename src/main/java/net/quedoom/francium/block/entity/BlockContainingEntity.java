package net.quedoom.francium.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.quedoom.francium.init.ModBlockEntities;

public class BlockContainingEntity extends BlockEntity {
    public static final Container container = new Container() {
        @Override
        public int getContainerSize() {
            return 1;
        }

        @Override
        public boolean isEmpty() {
            return container.isEmpty();
        }

        @Override
        public ItemStack getItem(int slot) {
            return container.getItem(0);
        }

        public ItemStack getItem() {
            return container.getItem(0);
        }

        @Override
        public ItemStack removeItem(int slot, int count) {
            return container.removeItem(0, count);
        }

        @Override
        public ItemStack removeItemNoUpdate(int slot) {
            return container.removeItemNoUpdate(0);
        }

        @Override
        public void setItem(int slot, ItemStack itemStack) {
            container.setItem(0, itemStack);
        }

        @Override
        public void setChanged() {
            container.setChanged();
        }

        @Override
        public boolean stillValid(Player player) {
            return container.stillValid(player);
        }

        @Override
        public void clearContent() {
            container.clearContent();
        }
    };

    public BlockContainingEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.BLOCK_CONTAINING_ENTITY, worldPosition, blockState);
    }

    public Container getContainer() {
        return container;
    }

}
