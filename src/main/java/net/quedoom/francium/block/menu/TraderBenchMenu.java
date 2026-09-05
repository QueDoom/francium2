package net.quedoom.francium.block.menu;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;

import java.util.Objects;

public class TraderBenchMenu extends AbstractContainerMenu {
    public static final int PLASTIC_CHARM_SLOT = 0;
    public static final int RESULT_SLOT = 1;
    public static final int SLOT_COUNT = 2;

    protected final Container container;
    protected final ResultContainer resultSlots = new ResultContainer() {
        {
            Objects.requireNonNull(TraderBenchMenu.this);
        }

        public void setChanged() {
            TraderBenchMenu.this.slotsChanged(this);
        }
    };

    protected TraderBenchMenu(@Nullable MenuType<?> menuType, int containerId, final Container container, final Inventory inventory) {
        super(menuType, containerId);

        this.container = container;
        this.addSlot(new Slot(container, PLASTIC_CHARM_SLOT, 11, 11));
        this.addSlot(new FurnaceResultSlot(inventory.player, container, RESULT_SLOT, 102, 30));
        this.addStandardInventorySlots(inventory, 8, 84);
    }

    public boolean stillValid(final Player player) {
        return this.container.stillValid(player);
    }

    public ItemStack quickMoveStack(final Player player, final int slotIndex) {
        ItemStack clicked = ItemStack.EMPTY;
        Slot slot = (Slot)this.slots.get(slotIndex);
        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            clicked = stack.copy();
            if (slotIndex == RESULT_SLOT) {
                if (!this.moveItemStackTo(stack, SLOT_COUNT, 38, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(stack, clicked);
            } else if (!this.moveItemStackTo(stack, SLOT_COUNT, 38, false)) {
                return ItemStack.EMPTY;
            }

            if (stack.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (stack.getCount() == clicked.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, stack);
        }

        return clicked;
    }

}
