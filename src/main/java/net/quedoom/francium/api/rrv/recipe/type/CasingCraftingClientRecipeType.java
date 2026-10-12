package net.quedoom.francium.api.rrv.recipe.type;

import cc.cassian.rrv.common.recipe.inventory.RecipeViewMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.quedoom.francium.Francium;
import net.quedoom.francium.api.rrv.recipe.CasingCraftingClientRecipe;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.quet.api.rrv.ReliableClientRecipeTypeHelper;

import java.util.List;

public class CasingCraftingClientRecipeType extends ReliableClientRecipeTypeHelper {
    public static final CasingCraftingClientRecipeType INSTANCE = new CasingCraftingClientRecipeType();

    protected CasingCraftingClientRecipeType() {
        super(
                "casing_crafting", List.of(ModBlocks.WOODEN_CASING.asItem().getDefaultInstance()),
                153, 37
        );
    }

    @Override
    protected String namespace() {
        return Francium.MOD_ID;
    }

    @Override
    public int getSlotCount() {
        return 6;
    }

    @Override
    public void placeSlots(RecipeViewMenu.SlotDefinition slotDefinition) {
        slotDefinition.addItemSlot(0, 4, 14);
        slotDefinition.addItemSlot(1, 37, 14);
        slotDefinition.addItemSlot(2, 55, 14);
        slotDefinition.addItemSlot(3, 73, 14);
        slotDefinition.addItemSlot(4, 91, 14);
        slotDefinition.addItemSlot(5, 130, 14);
    }
}
