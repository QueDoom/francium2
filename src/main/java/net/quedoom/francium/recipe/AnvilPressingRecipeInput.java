package net.quedoom.francium.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public record AnvilPressingRecipeInput(ItemStack first, ItemStack second) implements RecipeInput {
    @Override
    public ItemStack getItem(int index) {
        return switch (index) {
            default -> first;
            case 1 -> second;
        };
    }

    public ItemStack getFirst() {
        return this.first;
    }
    public ItemStack getSecond() {
        return this.second;
    }

    @Override
    public int size() {
        return 2;
    }
}
