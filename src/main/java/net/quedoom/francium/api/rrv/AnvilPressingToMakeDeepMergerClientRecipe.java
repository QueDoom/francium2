package net.quedoom.francium.api.rrv;

import cc.cassian.rrv.api.recipe.ReliableClientRecipeType;
import cc.cassian.rrv.common.recipe.inventory.RecipeViewMenu;
import cc.cassian.rrv.common.recipe.inventory.SlotContent;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.quedoom.francium.Francium;

import java.util.List;

public class AnvilPressingToMakeDeepMergerClientRecipe extends ReliableClientRecipeWithoutTransfer {
    private final SlotContent mineralMix, resultItem;


    //You can design your constructor to suit your needs
    public AnvilPressingToMakeDeepMergerClientRecipe(Ingredient mineralMix, ItemStackTemplate resultItem) {
        this.mineralMix = SlotContent.of(mineralMix);
        this.resultItem = SlotContent.of(resultItem);
    }

    @Override
    protected ReliableClientRecipeType zeType() {
        return new AnvilPressingToMakeDeepMergerClientRecipeType();
    }

    @Override
    public Identifier identifier() {
        return Francium.of("anvil_pressing");
    }

    @Override
    public void bindSlots(RecipeViewMenu.SlotFillContext slotFillContext) {
        slotFillContext.bindSlot(0, mineralMix);
        slotFillContext.bindSlot(1, resultItem);
    }

    @Override
    public List<SlotContent> getIngredients() {
        return List.of(mineralMix);
    }

    @Override
    public List<SlotContent> getResults() {
        return List.of(resultItem);
    }
}
