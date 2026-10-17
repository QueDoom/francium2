package net.quedoom.francium.datagen.recipe;

import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.level.ItemLike;
import net.quedoom.quet.misc.GetPath;

public record QTRecipeHelper(RecipeProvider provider, RecipeOutput output, String namespace) {

    public void reversible3x3Compacting(ItemLike item, ItemLike compact) {
        provider.shaped(RecipeCategory.BUILDING_BLOCKS, compact)
                .pattern("III")
                .pattern("III")
                .pattern("III")
                .define('I', item)
                .unlockedBy(RecipeProvider.getHasName(item), provider.has(item))
                .save(output, GetPath.get(compact.asItem()) + "_from_" + GetPath.get(item.asItem()));
        provider.shapeless(RecipeCategory.MISC, item, 9)
                .requires(compact)
                .unlockedBy(RecipeProvider.getHasName(compact), provider.has(compact))
                .save(output, GetPath.get(item.asItem()) + "_from_" + GetPath.get(compact.asItem()));
    }

    public void reversible2x2Compacting(ItemLike item, ItemLike compact) {
        provider.shaped(RecipeCategory.BUILDING_BLOCKS, compact)
                .pattern("II")
                .pattern("II")
                .define('I', item)
                .unlockedBy(RecipeProvider.getHasName(item), provider.has(item))
                .save(output, GetPath.get(compact.asItem()) + "_from_" + GetPath.get(item.asItem()));
        provider.shapeless(RecipeCategory.MISC, item, 4)
                .requires(compact)
                .unlockedBy(RecipeProvider.getHasName(compact), provider.has(compact))
                .save(output, GetPath.get(item.asItem()) + "_from_" + GetPath.get(compact.asItem()));
    }
    public void irreversible3x3Compacting(ItemLike item, ItemLike compact) {
        provider.shaped(RecipeCategory.BUILDING_BLOCKS, compact)
                .pattern("III")
                .pattern("III")
                .pattern("III")
                .define('I', item)
                .unlockedBy(RecipeProvider.getHasName(item), provider.has(item))
                .save(output, GetPath.get(compact.asItem()) + "_from_" + GetPath.get(item.asItem()));
    }

    public void irreversible2x2Compacting(ItemLike item, ItemLike compact) {
        provider.shaped(RecipeCategory.BUILDING_BLOCKS, compact)
                .pattern("II")
                .pattern("II")
                .define('I', item)
                .unlockedBy(RecipeProvider.getHasName(item), provider.has(item))
                .save(output, GetPath.get(compact.asItem()) + "_from_" + GetPath.get(item.asItem()));
    }

    public ShapedRecipeBuilder shaped(RecipeCategory category, ItemLike mainItem, ItemLike result, int count, String pattern0, String pattern1, String pattern2) {
        return provider.shaped(category, result, count)
                .pattern(pattern0)
                .pattern(pattern1)
                .pattern(pattern2)
                .unlockedBy(RecipeProvider.getHasName(mainItem), provider.has(mainItem));
    }

    public ShapedRecipeBuilder shaped(RecipeCategory category, ItemLike mainItem, ItemLike result, int count, String pattern0, String pattern1) {
        return provider.shaped(category, result, count)
                .pattern(pattern0)
                .pattern(pattern1)
                .unlockedBy(RecipeProvider.getHasName(mainItem), provider.has(mainItem));
    }

    public ShapedRecipeBuilder shaped(RecipeCategory category, ItemLike mainItem, ItemLike result, int count, String pattern0) {
        return provider.shaped(category, result, count)
                .pattern(pattern0)
                .unlockedBy(RecipeProvider.getHasName(mainItem), provider.has(mainItem));
    }

    public ShapedRecipeBuilder shaped(RecipeCategory category, ItemLike mainItem, ItemLike result, String pattern0, String pattern1, String pattern2) {
        return shaped(RecipeCategory.MISC, mainItem, result, 1, pattern0, pattern1, pattern2);
    }

    public ShapedRecipeBuilder shaped(RecipeCategory category, ItemLike mainItem, ItemLike result, String pattern0, String pattern1) {
        return shaped(RecipeCategory.MISC, mainItem, result, 1, pattern0, pattern1);
    }

    public ShapedRecipeBuilder shaped(RecipeCategory category, ItemLike mainItem, ItemLike result, String pattern0) {
        return shaped(RecipeCategory.MISC, mainItem, result, 1, pattern0);
    }

    public ShapedRecipeBuilder shaped(ItemLike mainItem, ItemLike result, int count, String pattern0, String pattern1, String pattern2) {
        return shaped(RecipeCategory.MISC, mainItem, result, count, pattern0, pattern1, pattern2);
    }

    public ShapedRecipeBuilder shaped(ItemLike mainItem, ItemLike result, int count, String pattern0, String pattern1) {
        return shaped(RecipeCategory.MISC, mainItem, result, count, pattern0, pattern1);
    }

    public ShapedRecipeBuilder shaped(ItemLike mainItem, ItemLike result, int count, String pattern0) {
        return shaped(RecipeCategory.MISC, mainItem, result, count, pattern0);
    }
    
    public ShapedRecipeBuilder shaped(ItemLike mainItem, ItemLike result, String pattern0, String pattern1, String pattern2) {
        return shaped(RecipeCategory.MISC, mainItem, result, 1, pattern0, pattern1, pattern2);
    }

    public ShapedRecipeBuilder shaped(ItemLike mainItem, ItemLike result, String pattern0, String pattern1) {
        return shaped(RecipeCategory.MISC, mainItem, result, 1, pattern0, pattern1);
    }

    public ShapedRecipeBuilder shaped(ItemLike mainItem, ItemLike result, String pattern0) {
        return shaped(RecipeCategory.MISC, mainItem, result, 1, pattern0);
    }

}
