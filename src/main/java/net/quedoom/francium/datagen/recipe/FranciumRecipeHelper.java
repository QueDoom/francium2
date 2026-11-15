package net.quedoom.francium.datagen.recipe;

import net.minecraft.advancements.Criterion;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.francium.init.ModItems;
import net.quedoom.quet.misc.GetPath;

import java.util.Optional;

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
        new TwoBlockPressingRecipeBuilder(RecipeCategory.MISC, Optional.of(Ingredient.of(item)), count, topBlock, bottomBlock, new ItemStackTemplate(result.asItem()))
                .unlockedBy(RecipeProvider.getHasName(item), provider.has(item))
                .save(output, "francium_2:anvil_pressing/twoblock/" + GetPath.get(result.asItem()))
        ;
    }
    public void blocksAnvilPressing(ItemLike item, int count, Block topBlock, Block bottomBlock, ItemLike result, String prefix) {
        new TwoBlockPressingRecipeBuilder(RecipeCategory.MISC, Optional.of(Ingredient.of(item)), count, topBlock, bottomBlock, new ItemStackTemplate(result.asItem()))
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

    private void twoBlockCore(Optional<Ingredient> ingredient, int count,
                              Block topBlock, Block bottomBlock, Block bottomBlockStorage,
                              ItemLike result, int countResult,
                              String unlockName, Criterion<?> criterion, String prefix) {
        String base = "francium_2:anvil_pressing/twoblock/" + GetPath.get(result.asItem());
        String suffix = prefix == null ? "" : "_" + prefix;

        TwoBlockPressingRecipeBuilder normal = new TwoBlockPressingRecipeBuilder(
                RecipeCategory.MISC, ingredient, count, topBlock, bottomBlock,
                new ItemStackTemplate(result.asItem(), countResult));
        TwoBlockPressingRecipeBuilder container = new TwoBlockPressingRecipeBuilder(
                RecipeCategory.MISC, ingredient, count, topBlock, bottomBlockStorage,
                new ItemStackTemplate(result.asItem(), countResult));

        if (criterion == null) {
            unlockName = RecipeProvider.getHasName(topBlock);
            criterion = provider.has(topBlock);
        }
        normal.unlockedBy(unlockName, criterion);
        container.unlockedBy(unlockName, criterion);

        normal.save(output, base + suffix);
        container.save(output, base + "_container" + suffix);
    }

    public void itemsBlocksAnvilPressing(ItemLike item, int count, Block topBlock, Block bottomBlock,
                                         Block bottomBlockStorage, ItemLike result, int countResult, String prefix) {
        twoBlockCore(Optional.of(Ingredient.of(item)), count, topBlock, bottomBlock, bottomBlockStorage,
                result, countResult, RecipeProvider.getHasName(item), provider.has(item), prefix);
    }

    public void itemsBlocksAnvilPressing(ItemLike item, int count, Block topBlock, Block bottomBlock,
                                         Block bottomBlockStorage, ItemLike result, int countResult) {
        itemsBlocksAnvilPressing(item, count, topBlock, bottomBlock, bottomBlockStorage, result, countResult, null);
    }

    public void itemsBlocksAnvilPressing(ItemLike item, int count, Block topBlock, Block bottomBlock,
                                         Block bottomBlockStorage, ItemLike result, String prefix) {
        itemsBlocksAnvilPressing(item, count, topBlock, bottomBlock, bottomBlockStorage, result, 1, prefix);
    }

    public void itemsBlocksAnvilPressing(ItemLike item, int count, Block topBlock, Block bottomBlock,
                                         Block bottomBlockStorage, ItemLike result) {
        itemsBlocksAnvilPressing(item, count, topBlock, bottomBlock, bottomBlockStorage, result, 1, null);
    }

    public void itemsBlocksAnvilPressing(ItemLike item, Block topBlock, Block bottomBlock,
                                         Block bottomBlockStorage, ItemLike result, int countResult, String prefix) {
        itemsBlocksAnvilPressing(item, 1, topBlock, bottomBlock, bottomBlockStorage, result, countResult, prefix);
    }

    public void itemsBlocksAnvilPressing(ItemLike item, Block topBlock, Block bottomBlock,
                                         Block bottomBlockStorage, ItemLike result, int countResult) {
        itemsBlocksAnvilPressing(item, 1, topBlock, bottomBlock, bottomBlockStorage, result, countResult, null);
    }

    public void itemsBlocksAnvilPressing(ItemLike item, Block topBlock, Block bottomBlock,
                                         Block bottomBlockStorage, ItemLike result, String prefix) {
        itemsBlocksAnvilPressing(item, 1, topBlock, bottomBlock, bottomBlockStorage, result, 1, prefix);
    }

    public void itemsBlocksAnvilPressing(ItemLike item, Block topBlock, Block bottomBlock,
                                         Block bottomBlockStorage, ItemLike result) {
        itemsBlocksAnvilPressing(item, 1, topBlock, bottomBlock, bottomBlockStorage, result, 1, null);
    }

    public void itemsBlocksAnvilPressing(TagKey<Item> items, int count, Block topBlock, Block bottomBlock,
                                         Block bottomBlockStorage, ItemLike result, int countResult, String prefix) {
        twoBlockCore(Optional.of(provider.tag(items)), count, topBlock, bottomBlock, bottomBlockStorage,
                result, countResult, "has_" + GetPath.get(result.asItem()), provider.has(items), prefix);
    }

    public void itemsBlocksAnvilPressing(TagKey<Item> items, int count, Block topBlock, Block bottomBlock,
                                         Block bottomBlockStorage, ItemLike result, int countResult) {
        itemsBlocksAnvilPressing(items, count, topBlock, bottomBlock, bottomBlockStorage, result, countResult, null);
    }

    public void itemsBlocksAnvilPressing(TagKey<Item> items, int count, Block topBlock, Block bottomBlock,
                                         Block bottomBlockStorage, ItemLike result, String prefix) {
        itemsBlocksAnvilPressing(items, count, topBlock, bottomBlock, bottomBlockStorage, result, 1, prefix);
    }

    public void itemsBlocksAnvilPressing(TagKey<Item> items, int count, Block topBlock, Block bottomBlock,
                                         Block bottomBlockStorage, ItemLike result) {
        itemsBlocksAnvilPressing(items, count, topBlock, bottomBlock, bottomBlockStorage, result, 1, null);
    }

    public void itemsBlocksAnvilPressing(Block topBlock, Block bottomBlock, Block bottomBlockStorage,
                                         ItemLike result, int countResult, String prefix) {
        twoBlockCore(Optional.empty(), 1, topBlock, bottomBlock, bottomBlockStorage,
                result, countResult, null, null, prefix);
    }

    public void itemsBlocksAnvilPressing(Block topBlock, Block bottomBlock, Block bottomBlockStorage,
                                         ItemLike result, int countResult) {
        itemsBlocksAnvilPressing(topBlock, bottomBlock, bottomBlockStorage, result, countResult, null);
    }

    public void itemsBlocksAnvilPressing(Block topBlock, Block bottomBlock, Block bottomBlockStorage,
                                         ItemLike result, String prefix) {
        itemsBlocksAnvilPressing(topBlock, bottomBlock, bottomBlockStorage, result, 1, prefix);
    }

    public void itemsBlocksAnvilPressing(Block topBlock, Block bottomBlock, Block bottomBlockStorage,
                                         ItemLike result) {
        itemsBlocksAnvilPressing(topBlock, bottomBlock, bottomBlockStorage, result, 1, null);
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
    public void burning(Block block, float chance, BlockState result, String suffix) {
        new BurningRecipeBuilder(RecipeCategory.MISC, block, chance, result)
                .unlockedBy(RecipeProvider.getHasName(block), provider.has(block))
                .save(output, "francium_2:burning/" + GetPath.get(result.getBlock()) + '_' + suffix);
    }
    public void burning(Block block, float chance, BlockState result) {
        new BurningRecipeBuilder(RecipeCategory.MISC, block, chance, result)
                .unlockedBy(RecipeProvider.getHasName(block), provider.has(block))
                .save(output, "francium_2:burning/" + GetPath.get(result.getBlock()));
    }
    public void blowing(Block block, BlockState result, String suffix) {
        new BlowingRecipeBuilder(RecipeCategory.MISC, block, result)
                .unlockedBy(RecipeProvider.getHasName(block), provider.has(block))
                .save(output, "francium_2:blowing/" + GetPath.get(result.getBlock()) + '_' + suffix);
    }
    public void blowing(Block block, BlockState result) {
        new BlowingRecipeBuilder(RecipeCategory.MISC, block, result)
                .unlockedBy(RecipeProvider.getHasName(block), provider.has(block))
                .save(output, "francium_2:blowing/" + GetPath.get(result.getBlock()));
    }
    public void obsidianMixing(ItemLike firstItem, ItemLike secondItem, ItemLike thirdItem, ItemLike fourthItem, int time, ItemLike result, String suffix) {
        obsidianMixing(firstItem, secondItem, thirdItem, fourthItem, time, result, 1, suffix);
    }
    public void obsidianMixing(ItemLike firstItem, ItemLike secondItem, ItemLike thirdItem, ItemLike fourthItem, int time, ItemLike result) {
        obsidianMixing(firstItem, secondItem, thirdItem, fourthItem, time, result, 1);
    }
    public void obsidianMixing(ItemLike firstItem, ItemLike secondItem, ItemLike thirdItem, ItemLike fourthItem, int time, ItemLike result, int count, String suffix) {
        new ObsidianMixingRecipeBuilder(RecipeCategory.MISC, Ingredient.of(firstItem), Ingredient.of(secondItem), Ingredient.of(thirdItem), Ingredient.of(fourthItem), time, new ItemStackTemplate(result.asItem(), count))
                .unlockedBy(RecipeProvider.getHasName(firstItem), provider.has(secondItem))
                .save(output, "francium_2:obsidian_mixing/" + GetPath.get(result.asItem()) + '_' + suffix);
    }
    public void obsidianMixing(ItemLike firstItem, ItemLike secondItem, ItemLike thirdItem, ItemLike fourthItem, int time, ItemLike result, int count) {
        new ObsidianMixingRecipeBuilder(RecipeCategory.MISC, Ingredient.of(firstItem), Ingredient.of(secondItem), Ingredient.of(thirdItem), Ingredient.of(fourthItem), time, new ItemStackTemplate(result.asItem(), count))
                .unlockedBy(RecipeProvider.getHasName(firstItem), provider.has(secondItem))
                .save(output, "francium_2:obsidian_mixing/" + GetPath.get(result.asItem()));
    }

}
