package net.quedoom.francium.api.rrv;

import cc.cassian.rrv.api.recipe.ReliableClientRecipe;
import cc.cassian.rrv.common.recipe.inventory.RecipeViewMenu;
import cc.cassian.rrv.common.recipe.inventory.SlotContent;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;

import java.util.List;

public abstract class ReliableClientRecipeWithTransfer implements ReliableClientRecipe {
    private final List<Class<? extends AbstractContainerScreen<?>>> screenClasses;

    protected ReliableClientRecipeWithTransfer(List<Class<? extends AbstractContainerScreen<?>>> screenClasses) {
        this.screenClasses = screenClasses;
    }
    @Override
    public boolean supportsItemTransfer() {
        return true;
    }

    @Override
    public List<Class<? extends AbstractContainerScreen<?>>> getTransferClasses() {
        return screenClasses;
    }

    @Override
    public void mapRecipeItems(RecipeTransferMap transferMap, AbstractContainerScreen<?> screen) {
        mapRecipesItem(transferMap, screen);
    }

    protected abstract void mapRecipesItem(RecipeTransferMap transferMap, AbstractContainerScreen<?> screen);
}
