package net.quedoom.francium.api.rrv;

import cc.cassian.rrv.common.recipe.inventory.RecipeViewMenu;
import net.minecraft.world.item.ItemStack;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.francium.recipe.GlueMixingRecipe;

import java.util.List;

public class WoodenMixingClientRecipeType extends ReliableClientRecipeTypeHelper{
    protected WoodenMixingClientRecipeType() {
        super(
                GlueMixingRecipe.Type.ID,
                List.of(new ItemStack(ModBlocks.GLUE_MIXER)),
                118,
                46
        );
    }

    @Override
    public int getSlotCount() {
        return 4;
    }

    @Override
    public void placeSlots(RecipeViewMenu.SlotDefinition slotDefinition) {
        slotDefinition.addItemSlot(0, 2, 15);
        slotDefinition.addItemSlot(1, 23, 15);
        slotDefinition.addItemSlot(2, 56, 15);
        slotDefinition.addItemSlot(3, 96, 15);
    }
}
