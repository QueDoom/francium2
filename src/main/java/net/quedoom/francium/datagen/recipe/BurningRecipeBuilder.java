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
import net.minecraft.world.level.block.state.BlockState;
import net.quedoom.francium.recipe.BasicAnvilPressingRecipe;
import net.quedoom.francium.recipe.BurningRecipe;
import org.jspecify.annotations.Nullable;

public record BurningRecipeBuilder(RecipeCategory category, Block block, float chance, BlockState result, RecipeUnlockAdvancementBuilder advancementBuilder) implements RecipeBuilder {

    public BurningRecipeBuilder(RecipeCategory category, Block block, float chance, BlockState result) {
        this(category, block, chance, result, new RecipeUnlockAdvancementBuilder());
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
        return RecipeBuilder.getDefaultRecipeId(new ItemStackTemplate(this.result.getBlock().asItem()));
    }

    @Override
    public void save(RecipeOutput output, ResourceKey<Recipe<?>> location) {
        BurningRecipe recipe = new BurningRecipe(block, chance, result);
        output.accept(location, recipe, this.advancementBuilder.build(output, location, category));
    }
}
