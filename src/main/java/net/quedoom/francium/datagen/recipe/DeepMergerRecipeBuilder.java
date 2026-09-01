package net.quedoom.francium.datagen.recipe;

import net.minecraft.advancements.Criterion;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeUnlockAdvancementBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.quedoom.francium.Francium;
import net.quedoom.francium.init.ModItems;
import net.quedoom.francium.recipe.DeepMergingRecipe;
import net.quedoom.francium.recipe.WoodenMergingRecipe;
import org.jspecify.annotations.Nullable;

public class DeepMergerRecipeBuilder implements RecipeBuilder {
    private final RecipeCategory category;
    private final ItemStackTemplate result;
    private final Ingredient firstItem;
    private final Ingredient secondItem;
    private final Ingredient thirdItem;
    private final Ingredient glue;
    private final Ingredient wildcard;
    private final RecipeUnlockAdvancementBuilder advancementBuilder = new RecipeUnlockAdvancementBuilder();
    private @Nullable String group;

    public DeepMergerRecipeBuilder(RecipeCategory category, Ingredient firstItem, Ingredient secondItem, Ingredient thirdItem, Ingredient glue, Ingredient wildcard, ItemStackTemplate result) {
        this.category = category;
        this.firstItem = firstItem;
        this.secondItem = secondItem;
        this.thirdItem = thirdItem;
        this.glue = glue;
        this.wildcard = wildcard;
        this.result = result;
    }

    public static DeepMergerRecipeBuilder deepMergerRecipe(RecipeCategory category, Ingredient firstItem, Ingredient secondItem, Ingredient thirdItem, Ingredient glue, Ingredient wildcard, ItemLike result, int count) {
        return new DeepMergerRecipeBuilder(category, firstItem, secondItem, thirdItem, glue, wildcard, new ItemStackTemplate(result.asItem(), count));
    }

    public static DeepMergerRecipeBuilder deepMergerRecipe(RecipeCategory category, Ingredient firstItem, Ingredient secondItem, Ingredient thirdItem, Ingredient glue, Ingredient wildcard, ItemLike result) {
        return new DeepMergerRecipeBuilder(category, firstItem, secondItem, thirdItem, glue, wildcard, new ItemStackTemplate(result.asItem()));
    }

    public static DeepMergerRecipeBuilder deepMergerRecipe(RecipeCategory category, ItemLike firstItem, ItemLike secondItem, ItemLike thirdItem, ItemLike glue, ItemLike wildcard, ItemLike result, int count) {
        return deepMergerRecipe(category, Ingredient.of(firstItem), Ingredient.of(secondItem), Ingredient.of(thirdItem), Ingredient.of(glue), Ingredient.of(wildcard), result, count);
    }

    public static DeepMergerRecipeBuilder deepMergerRecipe(RecipeCategory category, ItemLike firstItem, ItemLike secondItem, ItemLike thirdItem, ItemLike glue, ItemLike wildcard, ItemLike result) {
        return deepMergerRecipe(category, Ingredient.of(firstItem), Ingredient.of(secondItem), Ingredient.of(thirdItem), Ingredient.of(glue), Ingredient.of(wildcard), result);
    }

    public static DeepMergerRecipeBuilder deepMergerRecipe(RecipeCategory category, ItemLike firstItem, ItemLike secondItem, ItemLike thirdItem, ItemLike glue, ItemLike result, int count) {
        return deepMergerRecipe(category, Ingredient.of(firstItem), Ingredient.of(secondItem), Ingredient.of(thirdItem), Ingredient.of(glue), Ingredient.of(ModItems.UNUSED_ITEM_BECAUSE_I_CANT_FIGURE_OUT_HOW_TO_MAKE_OPTIONAL_ITEMS_BECAUSE_IM_STUPID), result, count);
    }

    public static DeepMergerRecipeBuilder deepMergerRecipe(RecipeCategory category, ItemLike firstItem, ItemLike secondItem, ItemLike thirdItem, ItemLike glue, ItemLike result) {
        return deepMergerRecipe(category, Ingredient.of(firstItem), Ingredient.of(secondItem), Ingredient.of(thirdItem), Ingredient.of(glue), Ingredient.of(ModItems.UNUSED_ITEM_BECAUSE_I_CANT_FIGURE_OUT_HOW_TO_MAKE_OPTIONAL_ITEMS_BECAUSE_IM_STUPID), result);
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
        DeepMergingRecipe recipe = new DeepMergingRecipe(this.result, this.firstItem, this.secondItem, this.thirdItem, this.glue, this.wildcard);
        output.accept(id, recipe, this.advancementBuilder.build(output, id, this.category));
    }
}
