package net.quedoom.francium.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public record TwoBlockAnvilPressingRecipeInput(List<ItemStack> optionalItems, BlockState topState, BlockState bottomState) implements RecipeInput {
    @Override
    public ItemStack getItem(int index) {
        if (optionalItems.isEmpty()) return ItemStack.EMPTY;
        return optionalItems.get(index);
    }

    @Override
    public int size() {
        return optionalItems.size();
    }
}
