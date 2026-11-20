package net.quedoom.francium.api.rrv.util;

import cc.cassian.rrv.common.recipe.inventory.RecipeSlot;
import net.minecraft.world.Container;

public class InvisibleSlot extends RecipeSlot {
    public InvisibleSlot(Container viewContainer, int index, int x, int y, boolean highlightWithoutContents) {
        super(viewContainer, index, x, y, highlightWithoutContents);
    }

    public InvisibleSlot(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isHighlightable() {
        return false;
    }
}
