package net.quedoom.francium.api.rrv.recipe;

import cc.cassian.rrv.api.recipe.ReliableClientRecipeType;
import cc.cassian.rrv.common.recipe.inventory.RecipeViewMenu;
import cc.cassian.rrv.common.recipe.inventory.SlotContent;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.ItemLike;
import net.quedoom.francium.Francium;
import net.quedoom.francium.api.rrv.recipe.type.ForbiddenClientRecipeType;
import net.quedoom.francium.api.rrv.util.InvisibleSlotRenderer;
import net.quedoom.francium.init.ModTags;
import net.quedoom.quet.api.rrv.ReliableClientRecipeWithoutTransfer;

import java.util.List;

public class ForbiddenClientRecipe extends ReliableClientRecipeWithoutTransfer {
    private final SlotContent item, result;

    public ForbiddenClientRecipe(ItemLike itemLike) {
        this.item = SlotContent.of(itemLike.asItem());
        this.result = SlotContent.of(ModTags.Items.FORBIDDEN_ITEMS);
    }

    @Override
    protected ReliableClientRecipeType zeType() {
        return new ForbiddenClientRecipeType();
    }

    @Override
    public Identifier identifier() {
        return Francium.of("forbidden");
    }

    @Override
    public void bindSlots(RecipeViewMenu.SlotFillContext slotFillContext) {
        slotFillContext.bindOptionalSlot(0, this.item, new InvisibleSlotRenderer());
        slotFillContext.bindOptionalSlot(1, this.result, new InvisibleSlotRenderer());
    }

    @Override
    public List<SlotContent> getIngredients() {
        return List.of(this.item);
    }

    @Override
    public List<SlotContent> getResults() {
        return List.of(this.result);
    }
}
