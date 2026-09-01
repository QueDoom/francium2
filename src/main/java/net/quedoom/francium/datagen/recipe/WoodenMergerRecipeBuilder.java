package net.quedoom.francium.datagen.recipe;

import net.minecraft.advancements.Criterion;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeUnlockAdvancementBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.monster.Giant;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.quedoom.francium.Francium;
import net.quedoom.francium.recipe.WoodenMergingRecipe;
import org.jspecify.annotations.Nullable;

public class WoodenMergerRecipeBuilder implements RecipeBuilder {
    private final RecipeCategory category;
    private final ItemStackTemplate result;
    private final Ingredient firstItem;
    private final Ingredient secondItem;
    private final Ingredient glue;
    private final RecipeUnlockAdvancementBuilder advancementBuilder = new RecipeUnlockAdvancementBuilder();
    private @Nullable String group;

    public WoodenMergerRecipeBuilder(RecipeCategory category, Ingredient firstItem, Ingredient secondItem, Ingredient glue, ItemStackTemplate result) {
        this.category = category;
        this.firstItem = firstItem;
        this.secondItem = secondItem;
        this.glue = glue;
        this.result = result;
    }

    public static WoodenMergerRecipeBuilder woodenMergerRecipe(RecipeCategory category, Ingredient firstItem, Ingredient secondItem, Ingredient glue, ItemLike result, int count) {
        return new WoodenMergerRecipeBuilder(category, firstItem, secondItem, glue, new ItemStackTemplate(result.asItem(), count));
    }

    public static WoodenMergerRecipeBuilder woodenMergerRecipe(RecipeCategory category, Ingredient firstItem, Ingredient secondItem, Ingredient glue, ItemLike result) {
        return new WoodenMergerRecipeBuilder(category, firstItem, secondItem, glue, new ItemStackTemplate(result.asItem()));
    }

    public static WoodenMergerRecipeBuilder woodenMergerRecipe(RecipeCategory category, ItemLike firstItem, ItemLike secondItem, ItemLike glue, ItemLike result, int count) {
        return woodenMergerRecipe(category, Ingredient.of(firstItem), Ingredient.of(secondItem), Ingredient.of(glue), result, count);
    }

    public static WoodenMergerRecipeBuilder woodenMergerRecipe(RecipeCategory category, ItemLike firstItem, ItemLike secondItem, ItemLike glue, ItemLike result) {
        return woodenMergerRecipe(category, Ingredient.of(firstItem), Ingredient.of(secondItem), Ingredient.of(glue), result);
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
        WoodenMergingRecipe recipe = new WoodenMergingRecipe(this.result, this.firstItem, this.secondItem, this.glue);
        output.accept(id, recipe, this.advancementBuilder.build(output, id, this.category));
    }
}
