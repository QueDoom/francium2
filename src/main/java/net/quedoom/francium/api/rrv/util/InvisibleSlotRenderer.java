package net.quedoom.francium.api.rrv.util;

import cc.cassian.rrv.common.recipe.inventory.RecipeViewMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public class InvisibleSlotRenderer implements RecipeViewMenu.OptionalSlotRenderer {
    @Override
    public void extractRenderState(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
    }
}
