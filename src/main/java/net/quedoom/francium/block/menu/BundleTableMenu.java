package net.quedoom.francium.block.menu;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.quedoom.francium.init.ModMenuTypes;
import org.jspecify.annotations.Nullable;

public class BundleTableMenu extends AbstractContainerMenu {
    private final Container container;
    private final int containerRows;

    public BundleTableMenu(int containerId, Inventory inventory, final int rows) {
        this(containerId, inventory, new SimpleContainer(3 * rows), rows);
    }

    public BundleTableMenu(final int containerId, final Inventory inventory, final Container container, final int rows) {
        super(ModMenuTypes.BUNDLE_TABLE, containerId);
        checkContainerSize(container, rows * 3);
        this.container = container;
        this.containerRows = rows;
        container.startOpen(inventory.player);
        int chestGridTop = 18;
        this.addChestGrid(container, 62, chestGridTop + 8);
        int inventoryTop = chestGridTop + this.containerRows * chestGridTop + 13;
        this.addStandardInventorySlots(inventory, 8, inventoryTop + 17);
    }

    private void addChestGrid(final Container container, final int left, final int top) {
        for(int y = 0; y < this.containerRows; ++y) {
            for(int x = 0; x < 3; ++x) {
                this.addSlot(new Slot(container, x + y * 3, left + x * 18, top + y * 18));
            }
        }

    }

    @Override
    public boolean stillValid(final Player player) {
        return this.container.stillValid(player);
    }

    @Override
    public ItemStack quickMoveStack(final Player player, final int slotIndex) {
        ItemStack clicked = ItemStack.EMPTY;
        Slot slot = (Slot)this.slots.get(slotIndex);
        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            clicked = stack.copy();
            if (slotIndex < this.containerRows * 3) {
                if (!this.moveItemStackTo(stack, this.containerRows * 3, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(stack, 0, this.containerRows * 3, false)) {
                return ItemStack.EMPTY;
            }

            if (stack.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return clicked;
    }

    @Override
    public void removed(final Player player) {
        super.removed(player);
        this.container.stopOpen(player);
    }
}
