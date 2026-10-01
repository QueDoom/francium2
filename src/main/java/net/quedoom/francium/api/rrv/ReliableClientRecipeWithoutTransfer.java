package net.quedoom.francium.api.rrv;

import cc.cassian.rrv.api.recipe.ReliableClientRecipe;
import cc.cassian.rrv.api.recipe.ReliableClientRecipeType;
import cc.cassian.rrv.client.ReliableRecipeViewerClient;
import cc.cassian.rrv.common.recipe.inventory.RecipeViewMenu;
import cc.cassian.rrv.common.recipe.inventory.SlotContent;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.resources.Identifier;

import java.util.List;

public abstract class ReliableClientRecipeWithoutTransfer implements ReliableClientRecipe {
    public ReliableClientRecipeWithoutTransfer() {}
    @Override
    public boolean supportsItemTransfer() {
        return false;
    }

    @Override
    public ReliableClientRecipeType getType() {
        return zeType();
    }

    protected abstract ReliableClientRecipeType zeType();

    @Override
    public Identifier getId() {
        return identifier();
    }

    public abstract Identifier identifier();
}
