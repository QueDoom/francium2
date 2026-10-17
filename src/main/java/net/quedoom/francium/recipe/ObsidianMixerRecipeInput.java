package net.quedoom.francium.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeInput;

public record ObsidianMixerRecipeInput(ItemStack firstItem, ItemStack secondItem, ItemStack thirdItem, ItemStack fourthItem) implements RecipeInput {
    @Override
    public ItemStack getItem(int index) {
        return switch (index) {
            case 1 -> secondItem;
            case 2 -> thirdItem;
            case 3 -> fourthItem;
            default -> firstItem;
        };
    }

    @Override
    public int size() {
        return 4;
    }
}
