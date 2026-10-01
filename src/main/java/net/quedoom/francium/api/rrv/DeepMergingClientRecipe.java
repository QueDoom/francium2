package net.quedoom.francium.api.rrv;

import cc.cassian.rrv.api.recipe.ReliableClientRecipeType;
import cc.cassian.rrv.common.recipe.inventory.RecipeViewMenu;
import cc.cassian.rrv.common.recipe.inventory.SlotContent;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.quedoom.francium.Francium;
import net.quedoom.francium.block.menu.DeepMergerScreen;
import net.quedoom.francium.init.ModItems;
import net.quedoom.francium.recipe.DeepMergingRecipe;

import java.util.List;

public class DeepMergingClientRecipe extends ReliableClientRecipeWithTransfer {
    private final SlotContent firstSlot, secondSlot, thirdSlot, glueSlot, wildcardSlot, resultSlot;

    protected DeepMergingClientRecipe(Ingredient firstItem, Ingredient secondItem, Ingredient thirdItem, Ingredient glueItem, Ingredient wildcardItem, ItemStackTemplate resultItem) {
        super(List.of(DeepMergerScreen.class));
        this.firstSlot = SlotContent.of(firstItem);
        this.secondSlot = SlotContent.of(secondItem);
        this.thirdSlot = SlotContent.of(thirdItem);
        this.glueSlot = SlotContent.of(glueItem);
        if (wildcardItem.equals(Ingredient.of(ModItems.UNUSED_ITEM_BECAUSE_I_CANT_FIGURE_OUT_HOW_TO_MAKE_OPTIONAL_ITEMS_BECAUSE_IM_STUPID)))
            this.wildcardSlot = SlotContent.of();
            else this.wildcardSlot = SlotContent.of(wildcardItem);

        this.resultSlot = SlotContent.of(resultItem);
    }

    @Override
    protected void mapRecipesItem(RecipeTransferMap transferMap, AbstractContainerScreen<?> screen) {
        transferMap.linkSlots(0, 0);
        transferMap.linkSlots(1, 1);
        transferMap.linkSlots(2, 2);
        transferMap.linkSlots(3, 3);
        transferMap.linkSlots(4, 4);
        transferMap.linkSlots(5, 5);
    }

    @Override
    public Identifier identifier() {
        return Francium.of(DeepMergingRecipe.Type.ID);
    }

    @Override
    public ReliableClientRecipeType getType() {
        return DeepMergingClientRecipeType.INSTANCE;
    }

    @Override
    protected ReliableClientRecipeType zeType() {
        return new DeepMergingClientRecipeType();
    }

    @Override
    public void bindSlots(RecipeViewMenu.SlotFillContext slotFillContext) {
        slotFillContext.bindSlot(0, this.firstSlot);
        slotFillContext.bindSlot(1, this.secondSlot);
        slotFillContext.bindSlot(2, this.thirdSlot);
        slotFillContext.bindSlot(3, this.glueSlot);
        slotFillContext.bindSlot(4, this.wildcardSlot);
        slotFillContext.bindSlot(5, this.resultSlot);
    }

    @Override
    public List<SlotContent> getIngredients() {
        return List.of(this.firstSlot, this.secondSlot, this.thirdSlot, this.glueSlot, this.wildcardSlot);
    }

    @Override
    public List<SlotContent> getResults() {
        return List.of(this.resultSlot);
    }
}
