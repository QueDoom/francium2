package net.quedoom.francium.api.rrv.recipe.type;

import cc.cassian.rrv.common.recipe.inventory.RecipeViewMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.quedoom.francium.Francium;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.francium.recipe.BedrockCrushingRecipe;
import net.quedoom.quet.api.rrv.ReliableClientRecipeTypeHelper;

import java.util.List;

public class BedrockCrushingClientRecipeType extends ReliableClientRecipeTypeHelper {
    public BedrockCrushingClientRecipeType() {
        super(
                BedrockCrushingRecipe.Type.ID,
                List.of(
                        ModBlocks.BEDROCK_CRUSHER.asItem().getDefaultInstance(),
                        ModBlocks.BEDROCK_PLATEAU.asItem().getDefaultInstance()
                ),
                67, 80
        );
    }

    @Override
    protected String namespace() {
        return Francium.MOD_ID;
    }

    @Override
    public void placeSlots(RecipeViewMenu.SlotDefinition slotDefinition) {
        slotDefinition.addItemSlot(0, 4, 56);
        slotDefinition.addItemSlot(1, 43, 56);
    }
}
