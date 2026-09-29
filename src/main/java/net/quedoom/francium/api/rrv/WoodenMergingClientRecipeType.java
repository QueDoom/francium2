package net.quedoom.francium.api.rrv;

import cc.cassian.rrv.api.recipe.ReliableClientRecipeType;
import cc.cassian.rrv.common.recipe.inventory.RecipeViewMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.quet.init.ModRegistrator;

import java.util.List;

public class WoodenMergingClientRecipeType extends ReliableClientRecipeTypeHelper{
    protected static final ReliableClientRecipeType INSTANCE = new WoodenMergingClientRecipeType();

    protected WoodenMergingClientRecipeType() {
        super(
                "wooden_merging",
                List.of(new ItemStack(ModBlocks.WOODEN_MERGER)),
                116, 44
        );
    }

    @Override
    public int getSlotCount() {
        return 4;
    }

    @Override
    public void placeSlots(RecipeViewMenu.SlotDefinition slotDefinition) {
        slotDefinition.addItemSlot(0, 1, 0);
        slotDefinition.addItemSlot(1, 1, 27);
        slotDefinition.addItemSlot(2, 42, 14);
        slotDefinition.addItemSlot(3, 95, 14);
    }
}
