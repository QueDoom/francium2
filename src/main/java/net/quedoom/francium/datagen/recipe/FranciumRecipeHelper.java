package net.quedoom.francium.datagen.recipe;

import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.francium.init.ModItems;
import net.quedoom.quet.misc.GetPath;

public record FranciumRecipeHelper(RecipeProvider provider, RecipeOutput output) {
    public void anvilCompacting(ItemLike item, int xByX, Block block, ItemLike result) {
        new BasicPressingRecipeBuilder(RecipeCategory.MISC, Ingredient.of(item), xByX * xByX, block, new ItemStackTemplate(result.asItem()))
                .unlockedBy(RecipeProvider.getHasName(block.asItem()), provider.has(block.asItem()))
                .save(output, "francium_2:anvil_pressing/basic/" + GetPath.get(result.asItem()))
        ;
    }
    public void anvilCompacting(ItemLike item, int xByX, Block block, ItemLike result, String prefix) {
        new BasicPressingRecipeBuilder(RecipeCategory.MISC, Ingredient.of(item), xByX * xByX, block, new ItemStackTemplate(result.asItem()))
                .unlockedBy(RecipeProvider.getHasName(block.asItem()), provider.has(block.asItem()))
                .save(output, "francium_2:anvil_pressing/basic/" + GetPath.get(result.asItem()) + '_' + prefix)
        ;
    }
    public void anvilWoodenCompacting(ItemLike item, int xByX, ItemLike result) {
        anvilCompacting(item, xByX, ModBlocks.WOODEN_CASING, result);
    }
    public void anvilWoodenCompacting2x2(ItemLike item, ItemLike result) {
        anvilWoodenCompacting(item, 2, result);
    }
    public void anvilWoodenCompacting(ItemLike item, int xByX, ItemLike result, String prefix) {
        anvilCompacting(item, xByX, ModBlocks.WOODEN_CASING, result, prefix);
    }
    public void anvilWoodenCompacting2x2(ItemLike item, ItemLike result, String prefix) {
        anvilWoodenCompacting(item, 2, result, prefix);
    }

    public void blocksAnvilPressing(ItemLike item, int count, Block topBlock, Block bottomBlock, ItemLike result) {
        new TwoBlockPressingRecipeBuilder(RecipeCategory.MISC, Ingredient.of(item), count, topBlock, bottomBlock, new ItemStackTemplate(result.asItem()))
                .unlockedBy(RecipeProvider.getHasName(item), provider.has(item))
                .save(output, "francium_2:anvil_pressing/twoblock/" + GetPath.get(result.asItem()))
        ;
    }
    public void blocksAnvilPressing(ItemLike item, int count, Block topBlock, Block bottomBlock, ItemLike result, String prefix) {
        new TwoBlockPressingRecipeBuilder(RecipeCategory.MISC, Ingredient.of(item), count, topBlock, bottomBlock, new ItemStackTemplate(result.asItem()))
                .unlockedBy(RecipeProvider.getHasName(item), provider.has(item))
                .save(output, "francium_2:anvil_pressing/twoblock/" + GetPath.get(result.asItem()) + '_' + prefix)
        ;
    }
    public void blocksAnvilPressing(ItemLike item, Block topBlock, Block bottomBlock, ItemLike result) {
        blocksAnvilPressing(item, 1, topBlock, bottomBlock, result);
    }
    public void blocksAnvilPressing(Block topBlock, Block bottomBlock, ItemLike result) {
        blocksAnvilPressing(ModItems.UNUSED_ITEM_BECAUSE_I_CANT_FIGURE_OUT_HOW_TO_MAKE_OPTIONAL_ITEMS_BECAUSE_IM_STUPID, 1, topBlock, bottomBlock, result);
    }
    public void blocksAnvilPressing(ItemLike item, Block topBlock, Block bottomBlock, ItemLike result, String prefix) {
        blocksAnvilPressing(item, 1, topBlock, bottomBlock, result, prefix);
    }
    public void blocksAnvilPressing(Block topBlock, Block bottomBlock, ItemLike result, String prefix) {
        blocksAnvilPressing(ModItems.UNUSED_ITEM_BECAUSE_I_CANT_FIGURE_OUT_HOW_TO_MAKE_OPTIONAL_ITEMS_BECAUSE_IM_STUPID, 1, topBlock, bottomBlock, result, prefix);
    }

    public void itemsBlocksAnvilPressing(ItemLike item, int count, Block topBlock, Block bottomBlock, Block bottomBlockStorage, ItemLike result) {
        new TwoBlockPressingRecipeBuilder(RecipeCategory.MISC, Ingredient.of(item), count, topBlock, bottomBlock, new ItemStackTemplate(result.asItem()))
                .unlockedBy(RecipeProvider.getHasName(item), provider.has(item))
                .save(output, "francium_2:anvil_pressing/twoblock/" + GetPath.get(result.asItem()))
        ;
        new TwoBlockPressingRecipeBuilder(RecipeCategory.MISC, Ingredient.of(item), count, topBlock, bottomBlockStorage, new ItemStackTemplate(result.asItem()))
                .unlockedBy(RecipeProvider.getHasName(item), provider.has(item))
                .save(output, "francium_2:anvil_pressing/twoblock/" + GetPath.get(result.asItem()) + "_container")
        ;
    }
    public void itemsBlocksAnvilPressing(ItemLike item, int count, Block topBlock, Block bottomBlock, Block bottomBlockStorage, ItemLike result, String prefix) {
        new TwoBlockPressingRecipeBuilder(RecipeCategory.MISC, Ingredient.of(item), count, topBlock, bottomBlock, new ItemStackTemplate(result.asItem()))
                .unlockedBy(RecipeProvider.getHasName(item), provider.has(item))
                .save(output, "francium_2:anvil_pressing/twoblock/" + GetPath.get(result.asItem()) + '_' + prefix)
        ;
        new TwoBlockPressingRecipeBuilder(RecipeCategory.MISC, Ingredient.of(item), count, topBlock, bottomBlockStorage, new ItemStackTemplate(result.asItem()))
                .unlockedBy(RecipeProvider.getHasName(item), provider.has(item))
                .save(output, "francium_2:anvil_pressing/twoblock/" + GetPath.get(result.asItem()) + "_container_" + prefix)
        ;
    }

    public void itemsBlocksAnvilPressing(TagKey<Item> items, int count, Block topBlock, Block bottomBlock, Block bottomBlockStorage, ItemLike result) {
        String tagPath = GetPath.get(result.asItem());

        new TwoBlockPressingRecipeBuilder(RecipeCategory.MISC, provider.tag(items), count, topBlock, bottomBlock, new ItemStackTemplate(result.asItem()))
                .unlockedBy("has_" + tagPath, provider.has(items))
                .save(output, "francium_2:anvil_pressing/twoblock/" + GetPath.get(result.asItem()))
        ;
        new TwoBlockPressingRecipeBuilder(RecipeCategory.MISC, provider.tag(items), count, topBlock, bottomBlockStorage, new ItemStackTemplate(result.asItem()))
                .unlockedBy("has_" + tagPath, provider.has(items))
                .save(output, "francium_2:anvil_pressing/twoblock/" + GetPath.get(result.asItem()) + "_container")
        ;
    }
    public void itemsBlocksAnvilPressing(TagKey<Item> items, int count, Block topBlock, Block bottomBlock, Block bottomBlockStorage, ItemLike result, String prefix) {
        String tagPath = GetPath.get(result.asItem());

        new TwoBlockPressingRecipeBuilder(RecipeCategory.MISC, provider.tag(items), count, topBlock, bottomBlock, new ItemStackTemplate(result.asItem()))
                .unlockedBy("has_" + tagPath, provider.has(items))
                .save(output, "francium_2:anvil_pressing/twoblock/" + GetPath.get(result.asItem()) + '_' + prefix)
        ;
        new TwoBlockPressingRecipeBuilder(RecipeCategory.MISC, provider.tag(items), count, topBlock, bottomBlockStorage, new ItemStackTemplate(result.asItem()))
                .unlockedBy("has_" + tagPath, provider.has(items))
                .save(output, "francium_2:anvil_pressing/twoblock/" + GetPath.get(result.asItem()) + "_container_" + prefix)
        ;
    }
    public void itemsBlocksAnvilPressing(ItemLike item, Block topBlock, Block bottomBlock, Block bottomBlockStorage, ItemLike result) {
        itemsBlocksAnvilPressing(item, 1, topBlock, bottomBlock,  bottomBlockStorage, result);
    }
    public void itemsBlocksAnvilPressing(Block topBlock, Block bottomBlock, Block bottomBlockStorage, ItemLike result) {
        itemsBlocksAnvilPressing(ModItems.UNUSED_ITEM_BECAUSE_I_CANT_FIGURE_OUT_HOW_TO_MAKE_OPTIONAL_ITEMS_BECAUSE_IM_STUPID, 1, topBlock, bottomBlock, bottomBlockStorage, result);
    }
    public void itemsBlocksAnvilPressing(ItemLike item, Block topBlock, Block bottomBlock, Block bottomBlockStorage, ItemLike result, String prefix) {
        itemsBlocksAnvilPressing(item, 1, topBlock, bottomBlock, bottomBlockStorage, result, prefix);
    }
    public void itemsBlocksAnvilPressing(Block topBlock, Block bottomBlock, Block bottomBlockStorage, ItemLike result, String prefix) {
        itemsBlocksAnvilPressing(ModItems.UNUSED_ITEM_BECAUSE_I_CANT_FIGURE_OUT_HOW_TO_MAKE_OPTIONAL_ITEMS_BECAUSE_IM_STUPID, 1, topBlock, bottomBlock, bottomBlockStorage, result, prefix);
    }

    public void itemsAnvilPressing(ItemLike item, int count, Block block, Block containerBlock, ItemLike result) {
        new BasicPressingRecipeBuilder(RecipeCategory.MISC, Ingredient.of(item), count, block, new ItemStackTemplate(result.asItem()))
                .unlockedBy(RecipeProvider.getHasName(item), provider.has(item))
                .save(output, "francium_2:anvil_pressing/basic/" + GetPath.get(result.asItem()));
        new BasicPressingRecipeBuilder(RecipeCategory.MISC, Ingredient.of(item), count, containerBlock, new ItemStackTemplate(result.asItem()))
                .unlockedBy(RecipeProvider.getHasName(item), provider.has(item))
                .save(output, "francium_2:anvil_pressing/basic/" + GetPath.get(result.asItem()) + "_container");
    }

    public void itemsAnvilPressing(ItemLike item, int count, Block block, Block containerBlock, ItemLike result, String suffix) {
        new BasicPressingRecipeBuilder(RecipeCategory.MISC, Ingredient.of(item), count, block, new ItemStackTemplate(result.asItem()))
                .unlockedBy(RecipeProvider.getHasName(item), provider.has(item))
                .save(output, "francium_2:anvil_pressing/basic/" + GetPath.get(result.asItem()) + '_' + suffix);
        new BasicPressingRecipeBuilder(RecipeCategory.MISC, Ingredient.of(item), count, containerBlock, new ItemStackTemplate(result.asItem()))
                .unlockedBy(RecipeProvider.getHasName(item), provider.has(item))
                .save(output, "francium_2:anvil_pressing/basic/" + GetPath.get(result.asItem()) + "_container_" + suffix);
    }
}
