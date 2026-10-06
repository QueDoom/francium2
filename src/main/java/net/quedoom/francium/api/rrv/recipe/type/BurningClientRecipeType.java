package net.quedoom.francium.api.rrv.recipe.type;

import cc.cassian.rrv.common.recipe.inventory.RecipeViewMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.quedoom.francium.Francium;
import net.quedoom.francium.recipe.BurningRecipe;
import net.quedoom.quet.api.rrv.ReliableClientRecipeTypeHelper;

import java.util.List;

public class BurningClientRecipeType extends ReliableClientRecipeTypeHelper {
    public static final BurningClientRecipeType INSTANCE = new BurningClientRecipeType();

    protected BurningClientRecipeType() {
        super(
                BurningRecipe.Type.ID,
                List.of(Items.FLINT_AND_STEEL.getDefaultInstance()),
                67, 45
        );
    }

    @Override
    protected String namespace() {
        return Francium.MOD_ID;
    }

    @Override
    public int getSlotCount() {
        return 2;
    }

    @Override
    public void placeSlots(RecipeViewMenu.SlotDefinition slotDefinition) {
        slotDefinition.addItemSlot(0, 4, 21);
        slotDefinition.addItemSlot(1, 43, 21);
    }
}
