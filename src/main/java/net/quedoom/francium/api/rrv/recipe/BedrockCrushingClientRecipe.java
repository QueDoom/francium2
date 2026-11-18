package net.quedoom.francium.api.rrv.recipe;

import cc.cassian.rrv.api.client.RecipeScreenContext;
import cc.cassian.rrv.api.recipe.ReliableClientRecipeType;
import cc.cassian.rrv.common.recipe.inventory.RecipeViewMenu;
import cc.cassian.rrv.common.recipe.inventory.SlotContent;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.quedoom.francium.Francium;
import net.quedoom.francium.api.rrv.recipe.type.BedrockCrushingClientRecipeType;
import net.quedoom.francium.recipe.BedrockCrushingRecipe;
import net.quedoom.quet.api.rrv.ReliableClientRecipeWithoutTransfer;

import java.util.List;

public class BedrockCrushingClientRecipe extends ReliableClientRecipeWithoutTransfer {
    private final SlotContent item, result;
    private final int fallDistance;

    public BedrockCrushingClientRecipe(Ingredient item, int fallDistance, ItemStackTemplate result) {
        this.item = SlotContent.of(item);
        this.result = SlotContent.of(result);
        this.fallDistance = fallDistance;
    }

    @Override
    protected ReliableClientRecipeType zeType() {
        return new BedrockCrushingClientRecipeType();
    }

    @Override
    public Identifier identifier() {
        return Francium.of(BedrockCrushingRecipe.Type.ID);
    }

    @Override
    public void bindSlots(RecipeViewMenu.SlotFillContext slotFillContext) {
        slotFillContext.bindSlot(0, this.item);
        slotFillContext.bindSlot(1, this.result);
    }

    @Override
    public void renderRecipe(RecipeScreenContext context) {
        context.guiGraphics().text(
                Minecraft.getInstance().font,
                Component.translatable(Francium.translationString("rrv", BedrockCrushingRecipe.Type.ID, "fall_distance", "line0")),
                26, 8, -16777216, false
        );
        context.guiGraphics().text(
                Minecraft.getInstance().font,
                Component.translatable(Francium.translationString("rrv", BedrockCrushingRecipe.Type.ID, "fall_distance", "line1")),
                26, 16, -16777216, false
        );
        context.guiGraphics().text(
                Minecraft.getInstance().font,
                Component.translatable(Francium.translationString("rrv", BedrockCrushingRecipe.Type.ID, "fall_distance", "line2")),
                26, 24, -16777216, false
        );
        context.guiGraphics().text(
                Minecraft.getInstance().font,
                Component.translatable(Francium.translationString("rrv", BedrockCrushingRecipe.Type.ID, "fall_distance", "value"), this.fallDistance),
                26, 32, -16777216, false
        );
    }

    @Override
    public List<SlotContent> getIngredients() {
        return List.of(
                this.item
        );
    }

    @Override
    public List<SlotContent> getResults() {
        return List.of(
                this.result
        );
    }
}
