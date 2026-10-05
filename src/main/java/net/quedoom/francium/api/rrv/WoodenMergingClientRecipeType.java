package net.quedoom.francium.api.rrv;

import cc.cassian.rrv.api.recipe.ReliableClientRecipeType;
import cc.cassian.rrv.common.recipe.inventory.RecipeViewMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.quedoom.francium.Francium;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.francium.recipe.WoodenMergingRecipe;
import net.quedoom.quet.api.rrv.ReliableClientRecipeTypeHelper;
import net.quedoom.quet.init.ModRegistrator;

import java.util.List;

public class WoodenMergingClientRecipeType extends ReliableClientRecipeTypeHelper {
    protected static final ReliableClientRecipeType INSTANCE = new WoodenMergingClientRecipeType();

    protected WoodenMergingClientRecipeType() {
        super(
                WoodenMergingRecipe.Type.ID,
                List.of(new ItemStack(ModBlocks.WOODEN_MERGER)),
                122, 50
        );
    }

    @Override
    public int getSlotCount() {
        return 4;
    }

    @Override
    public void placeSlots(RecipeViewMenu.SlotDefinition slotDefinition) {
        slotDefinition.addItemSlot(0, 4, 4);
        slotDefinition.addItemSlot(1, 4, 30);
        slotDefinition.addItemSlot(2, 45, 17);
        slotDefinition.addItemSlot(3, 98, 17);
    }

    @Override
    protected String namespace() {
        return Francium.MOD_ID;
    }
}
