package net.quedoom.francium.api.rrv.recipe.type;

import cc.cassian.rrv.api.recipe.ReliableClientRecipeType;
import cc.cassian.rrv.common.recipe.inventory.RecipeViewMenu;
import net.minecraft.world.item.ItemStack;
import net.quedoom.francium.Francium;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.francium.recipe.DeepMergingRecipe;
import net.quedoom.quet.api.rrv.ReliableClientRecipeTypeHelper;

import java.util.List;

public class DeepMergingClientRecipeType extends ReliableClientRecipeTypeHelper {
    public static final ReliableClientRecipeType INSTANCE = new DeepMergingClientRecipeType();

    protected DeepMergingClientRecipeType() {
        super(
                DeepMergingRecipe.Type.ID,
                List.of(new ItemStack(ModBlocks.DEEP_MERGER)),
                122, 64
        );
    }

    @Override
    public int getSlotCount() {
        return 6;
    }

    private final int leftAlign = 4;

    @Override
    public void placeSlots(RecipeViewMenu.SlotDefinition slotDefinition) {
        slotDefinition.addItemSlot(0, leftAlign, 4);
        slotDefinition.addItemSlot(1, leftAlign, 24);
        slotDefinition.addItemSlot(2, leftAlign, 44);
        slotDefinition.addItemSlot(3, 45, 14);
        slotDefinition.addItemSlot(4, 45, 36);
        slotDefinition.addItemSlot(5, 98, 24);
    }

    @Override
    protected String namespace() {
        return Francium.MOD_ID;
    }
}
