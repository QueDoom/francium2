package net.quedoom.francium.api.rrv;

import cc.cassian.rrv.api.recipe.ReliableClientRecipeType;
import cc.cassian.rrv.common.recipe.inventory.RecipeViewMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;

import java.util.List;

public class TwoBlockPressingClientRecipeType extends ReliableClientRecipeTypeHelper {
    protected static final ReliableClientRecipeType INSTANCE = new TwoBlockPressingClientRecipeType();

    protected TwoBlockPressingClientRecipeType() {
        super(
                "two_block_anvil_pressing",
                List.of(new ItemStack(Blocks.ANVIL), new ItemStack(Blocks.CHIPPED_ANVIL), new ItemStack(Blocks.DAMAGED_ANVIL)),
                82, 92
        );
    }

    @Override
    public int getSlotCount() {
        return 4;
    }

    @Override
    public void placeSlots(RecipeViewMenu.SlotDefinition slotDefinition) {
        slotDefinition.addItemSlot(0, 4, 49);
        slotDefinition.addItemSlot(1, 24, 49);
        slotDefinition.addItemSlot(2, 14, 69);
        slotDefinition.addItemSlot(3, 58, 68);
    }
}
