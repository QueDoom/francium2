package net.quedoom.francium.api.rrv.recipe;

import cc.cassian.rrv.api.client.RecipeScreenContext;
import cc.cassian.rrv.api.recipe.ReliableClientRecipeType;
import cc.cassian.rrv.common.recipe.inventory.RecipeViewMenu;
import cc.cassian.rrv.common.recipe.inventory.SlotContent;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.quedoom.francium.Francium;
import net.quedoom.francium.api.rrv.recipe.type.ObsidianMixingClientRecipeType;
import net.quedoom.francium.recipe.ObsidianMixerRecipe;
import net.quedoom.quet.api.rrv.ReliableClientRecipeWithoutTransfer;

import java.util.List;

public class ObsidianMixingClientRecipe extends ReliableClientRecipeWithoutTransfer {
    private final SlotContent firstItem, secondItem, thirdItem, fourthItem, result;
    private final int time;

    public ObsidianMixingClientRecipe(Ingredient firstItem, Ingredient secondItem, Ingredient thirdItem, Ingredient fourthItem, int time, ItemStackTemplate result) {
        this.firstItem = SlotContent.of(firstItem);
        this.secondItem = SlotContent.of(secondItem);
        this.thirdItem = SlotContent.of(thirdItem);
        this.fourthItem = SlotContent.of(fourthItem);
        this.time = time;
        this.result = SlotContent.of(result);
    }

    @Override
    protected ReliableClientRecipeType zeType() {
        return ObsidianMixingClientRecipeType.INSTANCE;
    }

    @Override
    public Identifier identifier() {
        return Francium.of(ObsidianMixerRecipe.Type.ID);
    }

    @Override
    public void bindSlots(RecipeViewMenu.SlotFillContext slotFillContext) {
        slotFillContext.bindSlot(0, this.firstItem);
        slotFillContext.bindSlot(1, this.secondItem);
        slotFillContext.bindSlot(2, this.thirdItem);
        slotFillContext.bindSlot(3, this.fourthItem);
        slotFillContext.bindSlot(4, this.result);
    }

    @Override
    public List<SlotContent> getIngredients() {
        return List.of(this.firstItem, this.secondItem, this.thirdItem, this.fourthItem);
    }

    @Override
    public List<SlotContent> getResults() {
        return List.of(this.result);
    }

    @Override
    public void renderRecipe(RecipeScreenContext context) {
        context.guiGraphics().text(Minecraft.getInstance().font,
                Component.translatable("rrv.francium_2.obsidian_mixing.time", (int) (this.time * 0.05)),
                6, 77, -16777216, false);
    }
}
