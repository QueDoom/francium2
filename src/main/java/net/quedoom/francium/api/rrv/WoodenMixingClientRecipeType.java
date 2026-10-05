package net.quedoom.francium.api.rrv;

import cc.cassian.rrv.api.recipe.ReliableClientRecipeType;
import cc.cassian.rrv.common.recipe.inventory.RecipeViewMenu;
import net.minecraft.world.item.ItemStack;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.francium.recipe.GlueMixingRecipe;

import java.util.List;

public class WoodenMixingClientRecipeType extends ReliableClientRecipeTypeHelper{
    protected static final ReliableClientRecipeType INSTANCE = new WoodenMixingClientRecipeType();

    protected WoodenMixingClientRecipeType() {
        super(
                GlueMixingRecipe.Type.ID,
                List.of(new ItemStack(ModBlocks.GLUE_MIXER)),
                124,
                52
        );
    }

    @Override
    public int getSlotCount() {
        return 4;
    }

    @Override
    public void placeSlots(RecipeViewMenu.SlotDefinition slotDefinition) {
        slotDefinition.addItemSlot(0, 5, 18);
        slotDefinition.addItemSlot(1, 26, 18);
        slotDefinition.addItemSlot(2, 59, 18);
        slotDefinition.addItemSlot(3, 99, 18);
    }
}
