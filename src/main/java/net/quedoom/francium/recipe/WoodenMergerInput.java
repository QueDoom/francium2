package net.quedoom.francium.recipe;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeInput;

import java.util.List;

public record WoodenMergerInput(ItemStack first, ItemStack second, ItemStack glue) implements RecipeInput {

    @Override
    public ItemStack getItem(int index) {
        return switch (index) {
            case 0 -> this.first;
            case 1 -> this.second;
            case 2 -> this.glue;
            default -> ItemStack.EMPTY;
        };
    }

    ItemStack getItem() {
        return this.first;
    }

    public static WoodenMergerInput of(ItemStack first, ItemStack second, ItemStack glue) {
        return new WoodenMergerInput(first, second, glue);
    }


    @Override
    public int size() {
        return 3;
    }
}
