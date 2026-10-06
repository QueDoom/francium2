package net.quedoom.francium.api.rrv.recipe;

import cc.cassian.rrv.api.recipe.ReliableClientRecipeType;
import cc.cassian.rrv.common.recipe.inventory.RecipeViewMenu;
import cc.cassian.rrv.common.recipe.inventory.SlotContent;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.quedoom.francium.Francium;
import net.quedoom.francium.api.rrv.recipe.type.WoodenMergingClientRecipeType;
import net.quedoom.francium.block.menu.WoodenMergerScreen;
import net.quedoom.francium.recipe.WoodenMergingRecipe;
import net.quedoom.quet.api.rrv.ReliableClientRecipeWithTransfer;

import java.util.List;

public class WoodenMergingClientRecipe extends ReliableClientRecipeWithTransfer {

    private final SlotContent firstItem, secondItem, glueItem, resultItem;


    //You can design your constructor to suit your needs
    public WoodenMergingClientRecipe(Ingredient firstItem, Ingredient secondItem, Ingredient glueItem, ItemStackTemplate resultItem) {
        super(List.of(WoodenMergerScreen.class));
        this.firstItem = SlotContent.of(firstItem);
        this.secondItem = SlotContent.of(secondItem);
        this.glueItem = SlotContent.of(glueItem);
        this.resultItem = SlotContent.of(resultItem);
    }

    @Override
    protected ReliableClientRecipeType zeType() {
        return WoodenMergingClientRecipeType.INSTANCE;
    }

    @Override
    public Identifier identifier() {
        return Francium.of(WoodenMergingRecipe.Type.ID);
    }

    @Override
    public void bindSlots(RecipeViewMenu.SlotFillContext slotFillContext) {
        slotFillContext.bindSlot(0, firstItem);
        slotFillContext.bindSlot(1, secondItem);
        slotFillContext.bindSlot(2, glueItem);
        slotFillContext.bindSlot(3, resultItem);
    }

    @Override
    public List<SlotContent> getIngredients() {
        return List.of(this.firstItem, this.secondItem, this.glueItem);
    }

    @Override
    public List<SlotContent> getResults() {
        return List.of(this.resultItem);
    }

    @Override
    protected void mapRecipesItem(RecipeTransferMap transferMap, AbstractContainerScreen<?> screen) {
        transferMap.linkSlots(0, 0);
        transferMap.linkSlots(1, 1);
        transferMap.linkSlots(2, 2);
    }
}
