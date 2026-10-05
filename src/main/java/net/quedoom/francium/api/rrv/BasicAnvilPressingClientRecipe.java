package net.quedoom.francium.api.rrv;

import cc.cassian.rrv.api.client.RecipeScreenContext;
import cc.cassian.rrv.api.recipe.ReliableClientRecipeType;
import cc.cassian.rrv.common.recipe.inventory.RecipeViewMenu;
import cc.cassian.rrv.common.recipe.inventory.SlotContent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.quedoom.francium.Francium;
import net.quedoom.francium.recipe.BasicAnvilPressingRecipe;

import java.util.List;

public class BasicAnvilPressingClientRecipe extends ReliableClientRecipeWithoutTransfer {
    private final SlotContent item, block, result;

    public BasicAnvilPressingClientRecipe(Ingredient ingredient, int count, Block block, ItemStackTemplate result) {
        this.item = SlotContent.of(new ItemStack(ingredient.values.get(0), count));
        this.block = SlotContent.of(block);
        this.result = SlotContent.of(result);
    }

    @Override
    protected ReliableClientRecipeType zeType() {
        return BasicAnvilPressingClientRecipeType.INSTANCE;
    }

    @Override
    public Identifier identifier() {
        return Francium.of("anvil_pressing");
    }

    @Override
    public void bindSlots(RecipeViewMenu.SlotFillContext slotFillContext) {
        slotFillContext.bindSlot(0, this.item);
        slotFillContext.bindSlot(1, this.block);
        slotFillContext.bindSlot(2, this.result);
    }

    @Override
    public void renderRecipe(RecipeScreenContext context) {
        super.renderRecipe(context);
        GuiGraphicsExtractor guiGraphics = context.guiGraphics();

//        guiGraphics.text(Minecraft.getInstance().font, String.valueOf(count), 23, 52, -1, true);
    }

    @Override
    public List<SlotContent> getIngredients() {
        return List.of(this.item, this.block);
    }

    @Override
    public List<SlotContent> getResults() {
        return List.of(result);
    }
}
