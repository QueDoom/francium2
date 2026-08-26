package net.quedoom.francium.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeInput;

public record GlueMixerInput(GlueMixerGlueType type, int strength, ItemStack input, ItemStack output) implements RecipeInput {
    @Override
    public ItemStack getItem(int index) {
        return switch (index) {
            case 2 -> input;
            case 3 -> output;
            default -> ItemStack.EMPTY;
        };
    }

    @Override
    public int size() {
        return 4;
    }
}
