package net.quedoom.francium.api.rrv.recipe.type;

import cc.cassian.rrv.common.recipe.inventory.RecipeViewMenu;
import net.minecraft.world.item.ItemStack;
import net.quedoom.francium.Francium;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.francium.recipe.ObsidianMixerRecipe;
import net.quedoom.quet.api.rrv.ReliableClientRecipeTypeHelper;

import java.util.List;

public class ObsidianMixingClientRecipeType extends ReliableClientRecipeTypeHelper {
    public static final ObsidianMixingClientRecipeType INSTANCE = new ObsidianMixingClientRecipeType();

    protected ObsidianMixingClientRecipeType() {
        super(
                ObsidianMixerRecipe.Type.ID, List.of(ModBlocks.OBSIDIAN_MIXER.asItem().getDefaultInstance()),
                84, 92
        );
    }

    @Override
    protected String namespace() {
        return Francium.MOD_ID;
    }

    @Override
    public int getSlotCount() {
        return 5;
    }

    @Override
    public void placeSlots(RecipeViewMenu.SlotDefinition slotDefinition) {
        slotDefinition.addItemSlot(0, 10, 29);
        slotDefinition.addItemSlot(1, 28, 29);
        slotDefinition.addItemSlot(2, 10, 47);
        slotDefinition.addItemSlot(3, 28, 47);
        slotDefinition.addItemSlot(4, 60, 38);
    }
}
