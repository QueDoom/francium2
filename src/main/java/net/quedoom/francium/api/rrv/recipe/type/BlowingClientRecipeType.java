package net.quedoom.francium.api.rrv.recipe.type;

import cc.cassian.rrv.common.recipe.inventory.RecipeViewMenu;
import net.minecraft.world.item.Items;
import net.quedoom.francium.Francium;
import net.quedoom.francium.recipe.BurningRecipe;
import net.quedoom.quet.api.rrv.ReliableClientRecipeTypeHelper;

import java.util.List;

public class BlowingClientRecipeType extends ReliableClientRecipeTypeHelper {
    public static final BlowingClientRecipeType INSTANCE = new BlowingClientRecipeType();

    protected BlowingClientRecipeType() {
        super(
                BurningRecipe.Type.ID,
                List.of(Items.FLINT_AND_STEEL.getDefaultInstance()),
                82, 32
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
        slotDefinition.addItemSlot(0, 4, 8);
        slotDefinition.addItemSlot(1, 58, 8);
    }
}
