package net.quedoom.francium.datagen.recipe;

import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.quedoom.francium.init.ModBlocks;

public record FranciumRecipeHelper(RecipeProvider provider, RecipeOutput output) {
    public void anvilCompacting(ItemLike item, int xByX, Block block, ItemLike result) {
        new BasicPressingRecipeBuilder(RecipeCategory.MISC, Ingredient.of(item), xByX * xByX, block, new ItemStackTemplate(result.asItem()));
    }
    public void anvilWoodenCompacting(ItemLike item, int xByX, ItemLike result) {
        new BasicPressingRecipeBuilder(RecipeCategory.MISC, Ingredient.of(item), xByX * xByX, ModBlocks.WOODEN_CASING, new ItemStackTemplate(result.asItem()));
    }
    public void anvilWoodenCompacting2x2(ItemLike item, ItemLike result) {
        new BasicPressingRecipeBuilder(RecipeCategory.MISC, Ingredient.of(item), 4, ModBlocks.WOODEN_CASING, new ItemStackTemplate(result.asItem()));
    }
}
