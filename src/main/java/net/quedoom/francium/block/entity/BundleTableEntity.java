package net.quedoom.francium.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.quedoom.francium.Francium;
import net.quedoom.francium.block.menu.BundleTableMenu;
import net.quedoom.francium.init.ModBlockEntities;
import net.quedoom.francium.init.ModBlocks;

public class BundleTableEntity extends BaseContainerBlockEntity implements Container {
    public static final NonNullList<ItemStack> container = NonNullList.withSize(6, ItemStack.EMPTY);

    public BundleTableEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.BUNDLE_TABLE_ENTITY, worldPosition, blockState);
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("menu." + Francium.MOD_ID + ".bundle_table");
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return container;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> items) {
        if (container.size() == items.size()) {
            for (int i = 0; i < items.size(); i++) {
                container.set(i, items.get(i));
            }
        }
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
        return new BundleTableMenu(containerId, inventory, 2);
    }

    @Override
    public int getContainerSize() {
        return 6;
    }

    public NonNullList<ItemStack> getContainer() {
        return container;
    }
}
