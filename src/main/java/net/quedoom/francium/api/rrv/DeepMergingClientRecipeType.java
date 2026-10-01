package net.quedoom.francium.api.rrv;

import cc.cassian.rrv.api.recipe.ReliableClientRecipeType;
import cc.cassian.rrv.common.recipe.inventory.RecipeViewMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.francium.recipe.DeepMergingRecipe;

import java.util.List;

public class DeepMergingClientRecipeType extends ReliableClientRecipeTypeHelper {
    protected static final ReliableClientRecipeType INSTANCE = new DeepMergingClientRecipeType();

    protected DeepMergingClientRecipeType() {
        super(
                DeepMergingRecipe.Type.ID,
                List.of(new ItemStack(ModBlocks.DEEP_MERGER)),
                116, 58
        );
    }

    @Override
    public int getSlotCount() {
        return 6;
    }

    @Override
    public void placeSlots(RecipeViewMenu.SlotDefinition slotDefinition) {
        slotDefinition.addItemSlot(0, 1, 0);
        slotDefinition.addItemSlot(1, 1, 21);
        slotDefinition.addItemSlot(2, 1, 41);
        slotDefinition.addItemSlot(3, 42, 11);
        slotDefinition.addItemSlot(4, 42, 33);
        slotDefinition.addItemSlot(5, 95, 21);
    }
}
