package net.quedoom.francium.datagen.recipe;

import net.minecraft.advancements.Criterion;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeUnlockAdvancementBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.Block;
import net.quedoom.francium.recipe.BasicAnvilPressingRecipe;
import net.quedoom.francium.recipe.BedrockCrushingRecipe;
import org.jspecify.annotations.Nullable;

public record BedrockCrushingRecipeBuilder(RecipeCategory category, Ingredient ingredient, double fallDistance, ItemStackTemplate result, RecipeUnlockAdvancementBuilder advancementBuilder) implements RecipeBuilder {

    public BedrockCrushingRecipeBuilder(RecipeCategory category, Ingredient ingredient, double fallDistance, ItemStackTemplate result) {
        this(category, ingredient, fallDistance, result, new RecipeUnlockAdvancementBuilder());
    }

    @Override
    public RecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        advancementBuilder.unlockedBy(name, criterion);
        return this;
    }

    @Override
    public RecipeBuilder group(@Nullable String group) {
        return this;
    }

    @Override
    public ResourceKey<Recipe<?>> defaultId() {
        return RecipeBuilder.getDefaultRecipeId(this.result);
    }

    @Override
    public void save(RecipeOutput output, ResourceKey<Recipe<?>> location) {
        BedrockCrushingRecipe recipe = new BedrockCrushingRecipe(ingredient, fallDistance, result);
        output.accept(location, recipe, this.advancementBuilder.build(output, location, category));
    }
}
