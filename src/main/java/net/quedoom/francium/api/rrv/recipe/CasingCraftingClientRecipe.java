package net.quedoom.francium.api.rrv.recipe;

import cc.cassian.rrv.api.client.RecipeScreenContext;
import cc.cassian.rrv.api.recipe.ReliableClientRecipeType;
import cc.cassian.rrv.common.recipe.inventory.RecipeViewMenu;
import cc.cassian.rrv.common.recipe.inventory.SlotContent;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.quedoom.francium.Francium;
import net.quedoom.francium.api.rrv.recipe.type.CasingCraftingClientRecipeType;
import net.quedoom.quet.api.rrv.ReliableClientRecipeWithoutTransfer;
import org.jspecify.annotations.Nullable;

import java.util.List;

public class CasingCraftingClientRecipe extends ReliableClientRecipeWithoutTransfer {
    private final SlotContent startCasing, firstItem, secondItem, thirdItem, fourthItem, resultCasing;

    public CasingCraftingClientRecipe(Block startCasing, Item firstItem, @Nullable Item secondItem, @Nullable Item thirdItem, @Nullable Item fourthItem, Block resultCasing) {
        this.startCasing = SlotContent.of(startCasing);
        this.firstItem = SlotContent.of(firstItem);
        this.secondItem = SlotContent.of(secondItem);
        this.thirdItem = SlotContent.of(thirdItem);
        this.fourthItem = SlotContent.of(fourthItem);
        this.resultCasing = SlotContent.of(resultCasing);
    }

    @Override
    protected ReliableClientRecipeType zeType() {
        return CasingCraftingClientRecipeType.INSTANCE;
    }

    @Override
    public Identifier identifier() {
        return Francium.of("casing_crafting");
    }

    @Override
    public void bindSlots(RecipeViewMenu.SlotFillContext slotFillContext) {
        slotFillContext.bindSlot(0, this.startCasing);
        slotFillContext.bindSlot(1, this.firstItem);
        slotFillContext.bindOptionalSlot(2, this.secondItem, RecipeViewMenu.OptionalSlotRenderer.DEFAULT);
        slotFillContext.bindOptionalSlot(3, this.thirdItem, RecipeViewMenu.OptionalSlotRenderer.DEFAULT);
        slotFillContext.bindOptionalSlot(4, this.fourthItem, RecipeViewMenu.OptionalSlotRenderer.DEFAULT);
        slotFillContext.bindSlot(5, this.resultCasing);
    }

    @Override
    public List<SlotContent> getIngredients() {
        return List.of(this.startCasing, this.firstItem, this.secondItem, this.thirdItem, this.fourthItem);
    }

    @Override
    public List<SlotContent> getResults() {
        return List.of(this.fourthItem);
    }

    @Override
    public void renderRecipe(RecipeScreenContext context) {
        context.guiGraphics().text(Minecraft.getInstance().font, Francium.translatable("rrv", "use_in_order"), 37, 5,-16777216, false);
    }
}
