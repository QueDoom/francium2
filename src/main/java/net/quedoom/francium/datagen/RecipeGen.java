package net.quedoom.francium.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.quedoom.francium.Francium;
import net.quedoom.francium.datagen.recipe.*;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.francium.init.ModItems;
import net.quedoom.francium.init.ModTags;
import net.quedoom.francium.recipe.BasicAnvilPressingRecipe;
import net.quedoom.francium.recipe.WoodenMixerGlueType;
import net.quedoom.quet.datagen.recipe.QueTRecipeProvider;
import net.quedoom.quet.misc.GetPath;
import net.quedoom.quet.misc.LocalizedGetPath;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RecipeGen extends QueTRecipeProvider implements LocalizedGetPath {
    public RecipeGen(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                FranciumRecipeHelper recipes = new FranciumRecipeHelper(this, output);
                QTRecipeHelper qtRecipe = new QTRecipeHelper(this, output, Francium.MOD_ID);
                HolderLookup.RegistryLookup<Item> itemLookup = registries.lookupOrThrow(Registries.ITEM);

                glueMixing(this, output, WoodenMixerGlueType.NORMAL, 0, ModItems.GLUE, 32);
                glueMixing(this, output, WoodenMixerGlueType.SUPER, 0, ModItems.SUPER_GLUE, 4);
                glueMixing(this, output, WoodenMixerGlueType.ECHO, 1, ModItems.ECHO_GLUE, 4, "2");
                glueMixing(this, output, WoodenMixerGlueType.ECHO, 2, ModItems.ECHO_GLUE, 6, "3");
                glueMixing(this, output, WoodenMixerGlueType.ECHO, 3, ModItems.ECHO_GLUE, 8, "4");
                glueMixing(this, output, WoodenMixerGlueType.VEGAN, 1, ModItems.VEGAN_GLUE, 6, "2");
                glueMixing(this, output, WoodenMixerGlueType.VEGAN, 2, ModItems.VEGAN_GLUE, 8, "3");
                glueMixing(this, output, WoodenMixerGlueType.VEGAN, 3, ModItems.VEGAN_GLUE, 16, "4");
                glueMixing(this, output, WoodenMixerGlueType.STEEL, 0, ModItems.STEEL_IN_A_BOTTLE, 2);

                woodenMerging(this, output, Items.CRAFTING_TABLE, Items.IRON_INGOT, ModItems.WOODEN_PLATE, ModItems.CRAFTING_TOKEN, 1);
                woodenMerging(this, output, Items.FURNACE, Items.IRON_INGOT, ModItems.WOODEN_PLATE, ModItems.SMELTING_TOKEN, 1);
                woodenMerging(this, output, Items.SMITHING_TABLE, Items.IRON_INGOT, ModItems.WOODEN_PLATE, ModItems.SMITHING_TOKEN, 1);
                woodenMerging(this, output, ModItems.TUFF_PILE, ModItems.TUFF_PILE, ModItems.GLUE, ModItems.TUFF_ZONG, 1);

                woodenMerging(this, output, ModItems.SLOT, ModItems.SLOT, ModItems.VEGAN_GLUE, ModItems.STACKED_SLOT, 1);
                woodenMerging(this, output, ModItems.SLOT, ModItems.IRON_ORE_PILE, ModItems.VEGAN_GLUE, ModItems.RAW_SLOT, 1);
                woodenMerging(this, output, ModItems.RAW_SLOT, ModItems.RAW_SLOT, ModItems.VEGAN_GLUE, ModItems.STACKED_RAW_SLOT, 1);

                woodenMerging(this, output, ModItems.CLAY_PILE, ModItems.CLAY_PILE, ModItems.GLUE, Items.CLAY_BALL, 1);
                deepMerging(this, output, Items.CLAY_BALL, ModItems.CLAY_PILE, Items.CLAY_BALL, ModItems.GLUE, Items.CLAY, 1);

                woodenMerging(this, output, ModItems.ANCIENT_ROD, ModItems.STEEL_BOWL, ModItems.ECHO_GLUE, ModItems.POT, 1);
                deepMerging(this, output, ModItems.STEEL_INGOT,  ModItems.STEEL_BOWL, ModItems.STEEL_INGOT, ModItems.VEGAN_GLUE, ModItems.ANCIENT_ROD, ModItems.FRYING_PAN, 1);

                woodenMerging(this, output, ModItems.ANDESITE_ALLOY,  ModItems.ROCK, ModItems.VEGAN_GLUE, ModItems.ANDESITE_COATED_ROCK,    1);
                woodenMerging(this, output, ModItems.DIORITE_ALLOY,   ModItems.ROCK, ModItems.VEGAN_GLUE, ModItems.DIORITE_COATED_ROCK,     1);
                woodenMerging(this, output, ModItems.GRANITE_ALLOY,   ModItems.ROCK, ModItems.VEGAN_GLUE, ModItems.GRANITE_COATED_ROCK,     1);
                woodenMerging(this, output, ModItems.AMETHYST_PASTE,  Items.DIAMOND, ModItems.SUPER_GLUE, ModItems.AMETHYST_COATED_DIAMOND, 1);

                deepMerging(this, output, ModItems.ANDESITE_ALLOY,        ModItems.DIORITE_ALLOY,       ModItems.GRANITE_ALLOY,         ModItems.VEGAN_GLUE, ModItems.MINERAL_MIX,                                                    1);
                deepMerging(this, output, ModItems.OBSIDIAN_PASTE,        Items.LEATHER,                ModItems.OBSIDIAN_PASTE,        ModItems.SUPER_GLUE, ModItems.OBSIDIAN_COATED_LEATHER,                                        1);
                deepMerging(this, output, ModItems.CACTUS_PAPER,          ModItems.CACTUS_PAPER,        ModItems.CACTUS_PAPER,          ModItems.ECHO_GLUE,  ModItems.OBSIDIAN_COATED_LEATHER, ModItems.OBSIDIAN_BOOK,                1);
                deepMerging(this, output, ModItems.DIAMOND_DUST,          ModItems.DIAMOND_DUST,        ModItems.DIAMOND_DUST,          ModItems.SUPER_GLUE, ModItems.OBSIDIAN_PASTE,          ModItems.OBSIDIAN_INFUSED_DIAMOND,     1);
                deepMerging(this, output, ModItems.SHARP_DEEPSLATE_ROCK,  ModItems.SHARP_AMETHYST_ROCK, Items.IRON_INGOT,               ModItems.GLUE_MIX,   ModItems.CRAFTING_TOKEN,          ModItems.TOOLBOX,                      1);
                deepMerging(this, output, ModItems.MINERAL_MIX,           ModItems.MINERAL_MIX,         ModItems.MINERAL_MIX,           ModItems.SUPER_GLUE, ModItems.DRIPSTONE_PASTE,         ModItems.DRIPSTONE_COATED_MINERAL_MIX, 2);
                deepMerging(this, output, ModItems.FLATTENED_ENDER_PEARL, ModItems.DENCHO_SHARD,        ModItems.FLATTENED_ENDER_PEARL, ModItems.GLUE_MIX,   ModItems.AMETHYST_PASTE,          ModItems.DASH_ORB,                     1);

                basicAnvilPressing(this, output, ModItems.DRIPSTONE_COATED_MINERAL_MIX, 4, ModBlocks.WOODEN_CASING, ModBlocks.MINERAL_MIX_BLOCK);
                recipes.itemsAnvilPressing(ModItems.CALCITE_PILE, 9, ModBlocks.WOODEN_CASING, ModBlocks.WOODEN_CASING_CONTAINING_ITEMS, ModItems.PACKED_CALCITE);
                recipes.anvilWoodenCompacting2x2(Items.SLIME_BALL, Items.SLIME_BLOCK);
                recipes.itemsAnvilPressing(ModItems.PULP, 1, ModBlocks.WOODEN_CASING, ModBlocks.WOODEN_CASING_CONTAINING_ITEMS, Items.PAPER);
                recipes.itemsBlocksAnvilPressing(ModItems.PULP, 2, ModBlocks.SMOOTH_CACTUS, ModBlocks.STONE_CASING, ModBlocks.STONE_CASING_CONTAINING_ITEMS, ModItems.CACTUS_PAPER);
                basicAnvilPressing(this, output, ModItems.STEEL_INGOT, 3, ModBlocks.STONE_CASING, ModItems.STEEL_BOWL);
                basicAnvilPressing(this, output, ModItems.STEEL_INGOT, 3, ModBlocks.STONE_CASING_CONTAINING_ITEMS, ModItems.STEEL_BOWL, "from_items_casing");
                basicAnvilPressing(this, output, ModBlocks.FORBIDDEN_DUST, 64, ModBlocks.STONE_CASING, ModItems.CORRUPTED_MODEL);
                basicAnvilPressing(this, output, ModBlocks.FORBIDDEN_DUST, 64, ModBlocks.STONE_CASING_CONTAINING_ITEMS, ModItems.CORRUPTED_MODEL, "from_items_casing");
                recipes.itemsAnvilPressing(Items.ENDER_PEARL, 1, ModBlocks.WOODEN_CASING, ModBlocks.WOODEN_CASING_CONTAINING_ITEMS, ModItems.FLATTENED_ENDER_PEARL);

                recipes.itemsBlocksAnvilPressing(ModTags.Items.MAKES_PAPER, 4, Blocks.WATER, ModBlocks.STONE_CASING, ModBlocks.STONE_CASING_CONTAINING_ITEMS, ModItems.PULP);
                recipes.blocksAnvilPressing(Items.HEAVY_CORE, Blocks.SCULK, ModBlocks.STONE_CASING, ModBlocks.HEAVY_SCULK);
                recipes.itemsBlocksAnvilPressing(Items.ECHO_SHARD, 3, ModBlocks.HEAVY_SCULK, ModBlocks.OBSIDIAN_CASING, ModBlocks.OBSIDIAN_CASING_CONTAINING_ITEMS, ModItems.DENCHO_SHARD, 6);
                recipes.blocksAnvilPressing(ModItems.STACKED_RAW_SLOT, 2, ModBlocks.DRIPSTONE_SPIKES, Blocks.DEEPSLATE, ModBlocks.DEEP_MERGER);
                recipes.blocksAnvilPressing(ModItems.STEEL_BOWL, 1, ModBlocks.STEEL_WEDGE, Blocks.OBSIDIAN, ModBlocks.OBSIDIAN_MIXER);

                recipes.burning(Blocks.CACTUS, 0.25f, ModBlocks.SMOOTH_CACTUS.defaultBlockState());

                recipes.obsidianMixing(Blocks.OBSIDIAN, Blocks.HONEY_BLOCK, Blocks.HONEY_BLOCK, Blocks.OBSIDIAN, 400, ModItems.OBSIDIAN_PASTE);
                recipes.obsidianMixing(Blocks.AMETHYST_BLOCK, Blocks.HONEY_BLOCK, Blocks.SLIME_BLOCK, Blocks.AMETHYST_BLOCK, 200, ModItems.AMETHYST_PASTE);
                recipes.obsidianMixing(Blocks.DRIPSTONE_BLOCK, Blocks.SLIME_BLOCK, Blocks.SLIME_BLOCK, Blocks.DRIPSTONE_BLOCK, 100, ModItems.DRIPSTONE_PASTE);
                recipes.obsidianMixing(ModItems.GLUE, ModItems.VEGAN_GLUE, ModItems.ECHO_GLUE, ModItems.SUPER_GLUE, 140, ModItems.GLUE_MIX);
                recipes.obsidianMixing(ModItems.ANCIENT_DUST, ModItems.ANCIENT_DUST, ModItems.GOLD_DUST, ModItems.GOLD_DUST, 260, ModItems.NETHERITE_DUST);
                recipes.obsidianMixing(ModItems.GLASS_SHARDS, ModItems.GLASS_SHARDS, ModItems.GLASS_SHARDS, ModItems.GLASS_SHARDS, 140, Items.GLASS);
                recipes.obsidianMixing(ModItems.FIRE_ESSENCE, Items.NETHERRACK, Items.NETHERRACK, ModItems.FIRE_ESSENCE, 60, Items.MAGMA_BLOCK);
                recipes.obsidianMixing(ModItems.GLASS_SHARDS, Items.MAGMA_CREAM, Items.MAGMA_CREAM, Items.MAGMA_BLOCK, 200, ModItems.NETHER_GLUE, 6);

                qtRecipe.shaped(ModItems.DIRT_PILE, ModBlocks.COARSE_DIRT_SLAB, "DG", "GD")
                                .define('D', ModItems.DIRT_PILE)
                                .define('G', ModItems.GRAVEL_PILE)
                                .save(output);
                qtRecipe.irreversible2x2Compacting(ModItems.DIRT_PILE, ModBlocks.DIRT_SLAB);
                qtRecipe.irreversible3x3Compacting(ModItems.DIRT_PILE, Blocks.DIRT);
                qtRecipe.irreversible2x2Compacting(ModItems.SAND_PILE, ModBlocks.SAND_SLAB);
                qtRecipe.irreversible3x3Compacting(ModItems.SAND_PILE, Blocks.SAND);
                qtRecipe.irreversible2x2Compacting(ModItems.RED_SAND_PILE, ModBlocks.RED_SAND_SLAB);
                qtRecipe.irreversible3x3Compacting(ModItems.RED_SAND_PILE, Blocks.RED_SAND);
                qtRecipe.irreversible2x2Compacting(ModItems.CLAY_PILE, ModBlocks.CLAY_SLAB);
                qtRecipe.irreversible3x3Compacting(ModItems.CLAY_PILE, Blocks.CLAY);
                qtRecipe.shaped(ModItems.GRAVEL_PILE, ModBlocks.GRAVEL_SLAB, "GG", "  ")
                        .define('G', ModItems.GRAVEL_PILE)
                        .save(output);
                qtRecipe.irreversible3x3Compacting(ModItems.GRAVEL_PILE, Blocks.GRAVEL);
                recipes.anvilCompacting(ModItems.GRAVEL_PILE, 4, ModBlocks.WOODEN_CASING, Blocks.STONE);
                qtRecipe.irreversible2x2Compacting(ModItems.SOUL_PILE, ModBlocks.SOUL_SLAB);
                qtRecipe.irreversible3x3Compacting(ModItems.SOUL_PILE, Blocks.SOUL_SAND);

                campfireSmelting(ModItems.SAND_PILE, ModItems.GLASS_SHARDS, RecipeCategory.MISC, 400, this, output);

                oreSmelting(List.of(ModItems.STEEL_DUST), RecipeCategory.MISC, CookingBookCategory.MISC,
                        ModItems.STEEL_INGOT, 2, 200, "steel_dust");
                oreBlasting(List.of(ModItems.STEEL_DUST), RecipeCategory.MISC, CookingBookCategory.MISC,
                        ModItems.STEEL_INGOT, 2, 100, "steel_dust");

                qtRecipe.reversible3x3Compacting(ModItems.STEEL_INGOT, ModBlocks.STEEL_BLOCK);

                qtRecipe.shaped(ModBlocks.STEEL_BLOCK, ModBlocks.HEAVY_ANVIL, "SSS", " B ", "SSS")
                                .define('S', ModBlocks.STEEL_BLOCK)
                                .define('B', ModItems.ANCIENT_BUN)
                                .save(output);

                shaped(RecipeCategory.MISC, ModItems.ANCIENT_ROD)
                        .pattern("D")
                        .pattern("D")
                        .pattern("D")
                        .define('D', ModItems.ANCIENT_DUST)
                        .unlockedBy(getHasName(ModItems.ANCIENT_DUST), has(ModItems.ANCIENT_DUST))
                        .save(output)
                ;

                shaped(RecipeCategory.MISC, ModItems.PACKED_BASALT)
                        .pattern("DDD")
                        .pattern("DPD")
                        .pattern("DDD")
                        .define('D', ModItems.BASALT_PILE)
                        .define('P', ModItems.PACKED_CALCITE)
                        .unlockedBy(getHasName(ModItems.PACKED_CALCITE), has(ModItems.PACKED_CALCITE))
                        .save(output)
                ;

                shaped(RecipeCategory.MISC, ModItems.PACKED_DEEPSLATE)
                        .pattern("DDD")
                        .pattern("DPD")
                        .pattern("DDD")
                        .define('D', ModItems.DEEPSLATE_PILE)
                        .define('P', ModItems.PACKED_BASALT)
                        .unlockedBy(getHasName(ModItems.PACKED_BASALT), has(ModItems.PACKED_BASALT))
                        .save(output)
                ;

                shaped(RecipeCategory.MISC, ModItems.PACKED_NETHERRACK)
                        .pattern("DDD")
                        .pattern("DPD")
                        .pattern("DDD")
                        .define('D', ModItems.NETHERRACK_PILE)
                        .define('P', ModItems.PACKED_DEEPSLATE)
                        .unlockedBy(getHasName(ModItems.PACKED_DEEPSLATE), has(ModItems.PACKED_DEEPSLATE))
                        .save(output)
                ;

                shaped(RecipeCategory.MISC, ModItems.PACKED_DIRT)
                        .pattern("DDD")
                        .pattern("DPD")
                        .pattern("DDD")
                        .define('D', ModItems.DIRT_PILE)
                        .define('P', ModItems.PACKED_NETHERRACK)
                        .unlockedBy(getHasName(ModItems.PACKED_NETHERRACK), has(ModItems.PACKED_NETHERRACK))
                        .save(output)
                ;

                shaped(RecipeCategory.MISC, ModItems.PACKED_PLANKS)
                        .pattern("DDD")
                        .pattern("DPD")
                        .pattern("DDD")
                        .define('D', ModItems.SAWDUST)
                        .define('P', ModItems.PACKED_NETHERRACK)
                        .unlockedBy(getHasName(ModItems.PACKED_NETHERRACK), has(ModItems.PACKED_NETHERRACK))
                        .save(output)
                ;

                shaped(RecipeCategory.MISC, ModItems.SLOT)
                        .pattern("TR")
                        .pattern("RT")
                        .define('T', ModItems.TUFF_ZONG)
                        .define('R', ModItems.ROCK)
                        .unlockedBy(getHasName(ModItems.ROCK), has(ModItems.ROCK))
                        .save(output)
                ;

                shaped(RecipeCategory.MISC, ModItems.GLUE_BOTTLE)
                        .pattern("GG")
                        .pattern("GG")
                        .define('G', ModItems.GLASS_SHARDS)
                        .unlockedBy(getHasName(ModItems.GLASS_SHARDS), has(ModItems.GLASS_SHARDS))
                        .save(output)
                ;

                shaped(RecipeCategory.MISC, Items.CAMPFIRE)
                        .pattern("CW")
                        .pattern("BB")
                        .define('B', ModItems.BARK)
                        .define('C', ModItems.COAL_DUST)
                        .define('W', ModItems.SAWDUST)
                        .unlockedBy(getHasName(ModItems.TUFF_PILE), has(ModItems.TUFF_PILE))
                        .save(output);
                ;

                shaped(RecipeCategory.MISC, ModItems.TUFF_ZONG)
                        .pattern("TT")
                        .pattern("TT")
                        .define('T', ModItems.TUFF_PILE)
                        .unlockedBy(getHasName(ModItems.TUFF_PILE), has(ModItems.TUFF_PILE))
                        .save(output);
                ;

                shaped(RecipeCategory.TOOLS, ModItems.WOODEN_SHEARS)
                        .pattern("S ")
                        .pattern("DS")
                        .define('S', ModItems.SHARP_STICK)
                        .define('D', ModItems.SAWDUST)
                        .unlockedBy(getHasName(ModItems.SHARP_STICK), has(ModItems.SHARP_STICK))
                        .save(output);
                ;

                shaped(RecipeCategory.TOOLS, ModItems.FIRE_STARTER)
                        .pattern("C ")
                        .pattern("SS")
                        .define('S', Items.STICK)
                        .define('C', ModItems.COAL_DUST)
                        .unlockedBy(getHasName(ModItems.SHARP_STICK), has(ModItems.SHARP_STICK))
                        .save(output);
                ;

                shaped(RecipeCategory.TOOLS, ModItems.WOODEN_PLATE)
                        .pattern("DD")
                        .define('D', ModItems.SAWDUST)
                        .unlockedBy(getHasName(ModItems.SAWDUST), has(ModItems.SAWDUST))
                        .save(output);
                ;

                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLUE_MIXER)
                        .pattern("PS")
                        .pattern("WB")
                        .define('P', ModItems.SAWDUST)
                        .define('S', Items.STICK)
                        .define('W', ModBlocks.WOODEN_CASING)
                        .define('B', Items.BOWL)
                        .unlockedBy(getHasName(ModItems.BROKEN_STICK), has(ModItems.BROKEN_STICK))
                        .save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WOODEN_MERGER)
                        .pattern("SS")
                        .pattern("SW")
                        .define('S', ModItems.SLOT)
                        .define('W', ModBlocks.WOODEN_CASING)
                        .unlockedBy(getHasName(ModBlocks.WOODEN_CASING), has(ModBlocks.WOODEN_CASING))
                        .save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DRIPSTONE_SPIKES)
                        .pattern("DDD")
                        .pattern("DDD")
                        .pattern("MGM")
                        .define('D', Blocks.POINTED_DRIPSTONE)
                        .define('M', ModBlocks.MINERAL_MIX_BLOCK)
                        .define('G', Items.GOLD_INGOT)
                        .unlockedBy(getHasName(ModItems.DRIPSTONE_PILE), has(ModItems.DRIPSTONE_PILE))
                        .save(output);

                shapeless(RecipeCategory.BUILDING_BLOCKS, Blocks.GRAVEL)
                        .requires(ModItems.GRAVEL_PILE, 9)
                        .unlockedBy(getHasName(ModItems.GRAVEL_PILE), has(ModItems.GRAVEL_PILE))
                        .save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, ModItems.ROCK)
                        .pattern("GG")
                        .pattern("GG")
                        .define('G', ModItems.GRAVEL_PILE)
                        .unlockedBy(getHasName(ModItems.GRAVEL_PILE), has(ModItems.GRAVEL_PILE))
                        .save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, Items.STICK)
                        .pattern("S")
                        .pattern("S")
                        .define('S', ModItems.BROKEN_STICK)
                        .unlockedBy(getHasName(ModItems.BROKEN_STICK), has(ModItems.BROKEN_STICK))
                        .save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WOODEN_CASING)
                        .pattern("SS")
                        .pattern("SS")
                        .define('S', Items.STICK)
                        .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                        .save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, Blocks.OAK_LEAVES)
                        .pattern("LL")
                        .pattern("LL")
                        .define('L', ModItems.LEAF)
                        .unlockedBy(getHasName(ModItems.LEAF), has(ModItems.LEAF))
                        .save(output);
            }
        };
    }

    private void glueMixing(RecipeProvider provider, RecipeOutput output, WoodenMixerGlueType gType, int strength, ItemLike result, int count) {
        GlueMixerRecipeBuilder.glueMixerRecipe(RecipeCategory.MISC, gType, strength, result, count)
                .unlockedBy(RecipeProvider.getHasName(ModItems.GLUE_BOTTLE), provider.has(ModItems.GLUE_BOTTLE))
                .group(getPath(result.asItem()))
                .save(output, "francium_2:wooden_mixing/" + GetPath.get(result.asItem()))
        ;
    }

    private void glueMixing(RecipeProvider provider, RecipeOutput output, WoodenMixerGlueType gType, int strength, ItemLike result, int count, String id) {
        GlueMixerRecipeBuilder.glueMixerRecipe(RecipeCategory.MISC, gType, strength, result, count)
                .unlockedBy(RecipeProvider.getHasName(ModItems.GLUE_BOTTLE), provider.has(ModItems.GLUE_BOTTLE))
                .group(getPath(result.asItem()))
                .save(output, "francium_2:wooden_mixing/" + GetPath.get(result.asItem()) + "_" + id)
        ;
    }

    private void woodenMerging(RecipeProvider provider, RecipeOutput output, ItemLike firstItem, ItemLike secondItem, ItemLike glue, ItemLike result, int count) {
        WoodenMergerRecipeBuilder.woodenMergerRecipe(RecipeCategory.MISC, firstItem, secondItem, glue, result, count)
                .unlockedBy(RecipeProvider.getHasName(firstItem), provider.has(firstItem))
                .group(getPath(result.asItem()))
                .save(output, "francium_2:wooden_merging/" + GetPath.get(result.asItem()))
        ;
    }

    private void woodenMerging(RecipeProvider provider, RecipeOutput output, ItemLike firstItem, ItemLike secondItem, ItemLike glue, ItemLike result, int count, String id) {
        WoodenMergerRecipeBuilder.woodenMergerRecipe(RecipeCategory.MISC, firstItem, secondItem, glue, result, count)
                .unlockedBy(RecipeProvider.getHasName(firstItem), provider.has(firstItem))
                .group(getPath(result.asItem()))
                .save(output, "francium_2:wooden_merging/" + GetPath.get(result.asItem()) + "_" + id)
        ;
    }

    private void deepMerging(RecipeProvider provider, RecipeOutput output, ItemLike firstItem, ItemLike secondItem, ItemLike thirdItem, ItemLike glue, ItemLike wildcard, ItemLike result, int count) {
        DeepMergerRecipeBuilder.deepMergerRecipe(RecipeCategory.MISC, firstItem, secondItem, thirdItem, glue, wildcard, result, count)
                .unlockedBy(RecipeProvider.getHasName(firstItem), provider.has(firstItem))
                .group(getPath(result.asItem()))
                .save(output, "francium_2:deep_merging/" + GetPath.get(result.asItem()))
        ;
    }

    private void deepMerging(RecipeProvider provider, RecipeOutput output, ItemLike firstItem, ItemLike secondItem, ItemLike thirdItem, ItemLike glue, ItemLike wildcard, ItemLike result, int count, String id) {
        DeepMergerRecipeBuilder.deepMergerRecipe(RecipeCategory.MISC, firstItem, secondItem, thirdItem, glue, wildcard, result, count)
                .unlockedBy(RecipeProvider.getHasName(firstItem), provider.has(firstItem))
                .group(getPath(result.asItem()))
                .save(output, "francium_2:deep_merging/" + GetPath.get(result.asItem()) + "_" + id)
        ;
    }

    private void deepMerging(RecipeProvider provider, RecipeOutput output, ItemLike firstItem, ItemLike secondItem, ItemLike thirdItem, ItemLike glue, ItemLike result, int count) {
        DeepMergerRecipeBuilder.deepMergerRecipe(RecipeCategory.MISC, firstItem, secondItem, thirdItem, glue, result, count)
                .unlockedBy(RecipeProvider.getHasName(firstItem), provider.has(firstItem))
                .group(getPath(result.asItem()))
                .save(output, "francium_2:deep_merging/" + GetPath.get(result.asItem()))
        ;
    }

    private void deepMerging(RecipeProvider provider, RecipeOutput output, ItemLike firstItem, ItemLike secondItem, ItemLike thirdItem, ItemLike glue, ItemLike result, int count, String id) {
        DeepMergerRecipeBuilder.deepMergerRecipe(RecipeCategory.MISC, firstItem, secondItem, thirdItem, glue, result, count)
                .unlockedBy(RecipeProvider.getHasName(firstItem), provider.has(firstItem))
                .group(getPath(result.asItem()))
                .save(output, "francium_2:deep_merging/" + GetPath.get(result.asItem()) + "_" + id)
        ;
    }

    private void basicAnvilPressing(RecipeProvider provider, RecipeOutput output, ItemLike item, int count, Block block, ItemLike result, String id) {
        new BasicPressingRecipeBuilder(RecipeCategory.MISC, Ingredient.of(item), count, block, new ItemStackTemplate(result.asItem()))
                .unlockedBy(RecipeProvider.getHasName(item), provider.has(item))
                .group(getPath(result.asItem()))
                .save(output, "francium_2:anvil_pressing/basic/" + GetPath.get(result.asItem()) + '_' + id);
    }

    private void basicAnvilPressing(RecipeProvider provider, RecipeOutput output, ItemLike item, int count, Block block, ItemLike result) {
        new BasicPressingRecipeBuilder(RecipeCategory.MISC, Ingredient.of(item), count, block, new ItemStackTemplate(result.asItem()))
                .unlockedBy(RecipeProvider.getHasName(item), provider.has(item))
                .group(getPath(result.asItem()))
                .save(output, "francium_2:anvil_pressing/basic/" + GetPath.get(result.asItem()));
    }

    private void campfireSmelting(ItemLike in, ItemLike out, RecipeCategory category, int cookingTime, RecipeProvider provider, RecipeOutput output) {
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(in), category, out, 0, cookingTime)
                .unlockedBy(RecipeProvider.getHasName(in), provider.has(in))
                .group(GetPath.get(out.asItem()))
                .save(output, out + "_from_campfire");
    }

    @Override
    public String getName() {
        return "RecipeGen";
    }
}
