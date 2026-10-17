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
import net.minecraft.world.level.ItemLike;
import net.quedoom.francium.recipe.ObsidianMixerRecipe;
import net.quedoom.francium.recipe.WoodenMergingRecipe;
import org.jspecify.annotations.Nullable;

public class ObsidianMixingRecipeBuilder implements RecipeBuilder {
    private final RecipeCategory category;
    private final ItemStackTemplate result;
    private final Ingredient firstItem, secondItem, thirdItem, fourthItem;
    private final int time;
    private final RecipeUnlockAdvancementBuilder advancementBuilder = new RecipeUnlockAdvancementBuilder();
    private @Nullable String group;

    public ObsidianMixingRecipeBuilder(RecipeCategory category, Ingredient firstItem, Ingredient secondItem, Ingredient thirdItem, Ingredient fourthItem, int time, ItemStackTemplate result) {
        this.category = category;
        this.firstItem = firstItem;
        this.secondItem = secondItem;
        this.thirdItem = thirdItem;
        this.fourthItem = fourthItem;
        this.time = time;
        this.result = result;
    }

    public static ObsidianMixingRecipeBuilder of(RecipeCategory category, Ingredient firstItem, Ingredient secondItem, Ingredient thirdItem, Ingredient fourthItem, int time, ItemStackTemplate result) {
        return new ObsidianMixingRecipeBuilder(category, firstItem, secondItem, thirdItem, fourthItem, time, result);
    }

    @Override
    public RecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        advancementBuilder.unlockedBy(name, criterion);
        return this;
    }

    @Override
    public RecipeBuilder group(@Nullable String group) {
        this.group = group;
        return this;
    }

    @Override
    public ResourceKey<Recipe<?>> defaultId() {
        return RecipeBuilder.getDefaultRecipeId(this.result);
    }

    @Override
    public void save(RecipeOutput output, ResourceKey<Recipe<?>> id) {
        ObsidianMixerRecipe recipe = new ObsidianMixerRecipe(this.firstItem, this.secondItem, this.thirdItem, this.fourthItem, this.time, this.result);
        output.accept(id, recipe, this.advancementBuilder.build(output, id, this.category));
    }
}
