package net.quedoom.francium.api.rrv.recipe;

import cc.cassian.rrv.api.recipe.ReliableClientRecipeType;
import cc.cassian.rrv.common.recipe.inventory.RecipeViewMenu;
import cc.cassian.rrv.common.recipe.inventory.SlotContent;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.quedoom.francium.Francium;
import net.quedoom.francium.api.rrv.recipe.type.TwoBlockPressingClientRecipeType;
import net.quedoom.quet.api.rrv.ReliableClientRecipeWithoutTransfer;

import java.util.ArrayList;
import java.util.List;

public class TwoBlockPressingClientRecipe extends ReliableClientRecipeWithoutTransfer {
    private final SlotContent item, upperBlock, bottomBlock, result;

    public TwoBlockPressingClientRecipe(Ingredient items, int count, Block upperBlock, Block bottomBlock, ItemStackTemplate result) {
        HolderSet<Item> holderSet = items.values;
        List<ItemStack> inputItems = new ArrayList<>();
        for (Holder<Item> itemHolder : holderSet) {
            inputItems.add(new ItemStack(itemHolder.value(), count));
        }

        this.item = SlotContent.of(inputItems);
        this.upperBlock = SlotContent.of(upperBlock);
        this.bottomBlock = SlotContent.of(bottomBlock);
        this.result = SlotContent.of(result);
    }

    @Override
    protected ReliableClientRecipeType zeType() {
        return TwoBlockPressingClientRecipeType.INSTANCE;
    }

    @Override
    public Identifier identifier() {
        return Francium.of("two_block_anvil_pressing");
    }

    @Override
    public void bindSlots(RecipeViewMenu.SlotFillContext slotFillContext) {
        slotFillContext.bindSlot(0, this.item);
        slotFillContext.bindSlot(1, this.upperBlock);
        slotFillContext.bindSlot(2, this.bottomBlock);
        slotFillContext.bindSlot(3, this.result);
    }

    @Override
    public List<SlotContent> getIngredients() {
        return List.of(this.item, this.upperBlock, this.bottomBlock);
    }

    @Override
    public List<SlotContent> getResults() {
        return List.of(this.result);
    }
}
