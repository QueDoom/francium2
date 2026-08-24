package net.quedoom.francium.api.jei.custom_recipes;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.quedoom.francium.init.ModRecipeTypes;
import net.quedoom.francium.recipe.DeepMergingRecipe;

import java.util.List;

public class AnvilPressingRecipe implements Recipe<SingleRecipeInput> {
    ItemStackTemplate result;
    List<Ingredient> ingredients;

    public AnvilPressingRecipe(ItemStackTemplate result, Ingredient first, Ingredient second) {
        this.result = result;
        this.ingredients = List.of(first, second);
    }

    public ItemStackTemplate getResult() {
        return this.result;
    }
    public Ingredient getFirstIngredient() {
        return this.ingredients.getFirst();
    }
    public Ingredient getSecondIngredient() {
        return this.ingredients.get(1);
    }

    @Override
    public boolean matches(SingleRecipeInput input, Level level) {
        return false;
    }

    @Override
    public ItemStack assemble(SingleRecipeInput input) {
        return null;
    }

    @Override
    public boolean showNotification() {
        return false;
    }

    @Override
    public String group() {
        return Type.ID;
    }

    @Override
    public RecipeSerializer<? extends Recipe<SingleRecipeInput>> getSerializer() {
        return null;
    }

    @Override
    public RecipeType<? extends Recipe<SingleRecipeInput>> getType() {
        return ModRecipeTypes.ANVIL_PRESSING;
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.NOT_PLACEABLE;
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return null;
    }

    public static final class Type implements RecipeType<AnvilPressingRecipe> {
        private Type() {}

        public static final AnvilPressingRecipe.Type INSTANCE = new AnvilPressingRecipe.Type();
        public static final String ID = "anvil_pressing";
    }
}
