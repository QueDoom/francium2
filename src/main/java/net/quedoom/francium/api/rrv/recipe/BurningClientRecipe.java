package net.quedoom.francium.api.rrv.recipe;

import cc.cassian.rrv.api.client.RecipeScreenContext;
import cc.cassian.rrv.api.recipe.ReliableClientRecipeType;
import cc.cassian.rrv.common.recipe.inventory.RecipeViewMenu;
import cc.cassian.rrv.common.recipe.inventory.SlotContent;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.quedoom.francium.Francium;
import net.quedoom.francium.api.rrv.recipe.type.BurningClientRecipeType;
import net.quedoom.francium.recipe.BurningRecipe;
import net.quedoom.quet.api.rrv.ReliableClientRecipeWithoutTransfer;

import java.util.List;

public class BurningClientRecipe extends ReliableClientRecipeWithoutTransfer {
    private final SlotContent block, result;
    private final float chance;

    public BurningClientRecipe(Block block, float chance, BlockState result) {
        this.block = SlotContent.of(block);
        this.result = SlotContent.of(result.getBlock());
        this.chance = chance;
    }

    @Override
    protected ReliableClientRecipeType zeType() {
        return BurningClientRecipeType.INSTANCE;
    }

    @Override
    public Identifier identifier() {
        return Francium.of(BurningRecipe.Type.ID);
    }

    @Override
    public void bindSlots(RecipeViewMenu.SlotFillContext slotFillContext) {
        slotFillContext.bindSlot(0, this.block);
        slotFillContext.bindSlot(1, this.result);
    }

    @Override
    public List<SlotContent> getIngredients() {
        return List.of(this.block);
    }

    @Override
    public List<SlotContent> getResults() {
        return List.of(this.result);
    }

    @Override
    public void renderRecipe(RecipeScreenContext context) {
        context.guiGraphics().text(Minecraft.getInstance().font, "Chance: " + this.chance, 25, 5, -16777216, false);
    }
}
