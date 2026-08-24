package net.quedoom.francium.recipe;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public record DeepMergerInput(ItemStack first, ItemStack second, ItemStack third, ItemStack glue, ItemStack wildcard) implements RecipeInput {

    @Override
    public ItemStack getItem(int index) {
        return switch (index) {
            case 0 -> this.first;
            case 1 -> this.second;
            case 2 -> this.third;
            case 3 -> this.glue;
            case 4 -> this.wildcard;
            default -> ItemStack.EMPTY;
        };
    }

    public ItemStack getItem() {
        return this.first;
    }

    public static DeepMergerInput of(ItemStack first, ItemStack second, ItemStack third, ItemStack glue, ItemStack wildcard) {
        return new DeepMergerInput(first, second, third, glue, wildcard);
    }

    public static DeepMergerInput of(ItemStack first, ItemStack second, ItemStack third, ItemStack glue) {
        return new DeepMergerInput(first, second, third, glue, ItemStack.EMPTY);
    }

    @Override
    public int size() {
        return 5;
    }

}

