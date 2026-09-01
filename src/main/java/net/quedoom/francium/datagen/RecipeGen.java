package net.quedoom.francium.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.quedoom.francium.Francium;
import net.quedoom.francium.datagen.recipe.DeepMergerRecipeBuilder;
import net.quedoom.francium.datagen.recipe.GlueMixerRecipeBuilder;
import net.quedoom.francium.datagen.recipe.WoodenMergerRecipeBuilder;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.francium.init.ModItems;
import net.quedoom.francium.recipe.GlueMixerGlueType;

import java.util.concurrent.CompletableFuture;

public class RecipeGen extends FabricRecipeProvider {
    public RecipeGen(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                HolderLookup.RegistryLookup<Item> itemLookup = registries.lookupOrThrow(Registries.ITEM);

                glueMixing(this, output, GlueMixerGlueType.NORMAL, 0, ModItems.GLUE, 32);
                glueMixing(this, output, GlueMixerGlueType.SUPER, 0, ModItems.SUPER_GLUE, 4);
                glueMixing(this, output, GlueMixerGlueType.ECHO, 1, ModItems.ECHO_GLUE, 4, "2");
                glueMixing(this, output, GlueMixerGlueType.ECHO, 2, ModItems.ECHO_GLUE, 6, "3");
                glueMixing(this, output, GlueMixerGlueType.ECHO, 3, ModItems.ECHO_GLUE, 8, "4");
                glueMixing(this, output, GlueMixerGlueType.VEGAN, 1, ModItems.VEGAN_GLUE, 6, "2");
                glueMixing(this, output, GlueMixerGlueType.VEGAN, 2, ModItems.VEGAN_GLUE, 8, "3");
                glueMixing(this, output, GlueMixerGlueType.VEGAN, 3, ModItems.VEGAN_GLUE, 16, "4");

                woodenMerging(this, output, Items.CRAFTING_TABLE, Items.IRON_INGOT, ModItems.GLUE, ModItems.CRAFTING_TOKEN, 1);
                woodenMerging(this, output, Items.FURNACE, Items.IRON_INGOT, ModItems.GLUE, ModItems.SMELTING_TOKEN, 1);
                woodenMerging(this, output, Items.SMITHING_TABLE, Items.IRON_INGOT, ModItems.GLUE, ModItems.SMITHING_TOKEN, 1);
                woodenMerging(this, output, ModItems.TUFF_PILE, ModItems.TUFF_PILE, ModItems.GLUE, ModItems.TUFF_ZONG, 1);

                woodenMerging(this, output, ModItems.SLOT, ModItems.SLOT, ModItems.VEGAN_GLUE, ModItems.STACKED_SLOT, 1);
                woodenMerging(this, output, ModItems.SLOT, ModItems.IRON_ORE_PILE, ModItems.VEGAN_GLUE, ModItems.GLUE, 1);
                woodenMerging(this, output, ModItems.RAW_SLOT, ModItems.RAW_SLOT, ModItems.VEGAN_GLUE, ModItems.STACKED_RAW_SLOT, 1);

                deepMerging(this, output, ModItems.ANDESITE_ALLOY, ModItems.DIORITE_ALLOY, ModItems.GRANITE_ALLOY, ModItems.VEGAN_GLUE, ModItems.MINERAL_MIX, 1);

                campfireSmelting(ModItems.SAND_PILE, ModItems.GLASS_SHARDS, RecipeCategory.MISC, 400, this, output);

                shaped(RecipeCategory.MISC, ModBlocks.DRIPSTONE_SPIKES)
                        .pattern("DDD")
                        .pattern("DDD")
                        .pattern("MMM")
                        .define('D', Blocks.POINTED_DRIPSTONE)
                        .define('M', ModBlocks.MINERAL_MIX_BLOCK)
                        .unlockedBy(getHasName(ModBlocks.MINERAL_MIX_BLOCK), has(ModBlocks.MINERAL_MIX_BLOCK))
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

                shaped(RecipeCategory.BUILDING_BLOCKS, Blocks.CRAFTING_TABLE)
                        .pattern("SS")
                        .pattern("SW")
                        .define('S', ModItems.STACKED_RAW_SLOT)
                        .define('W', ModBlocks.WOODEN_MERGER)
                        .unlockedBy(getHasName(ModBlocks.WOODEN_CASING), has(ModBlocks.WOODEN_CASING))
                        .save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, Blocks.FURNACE)
                        .pattern("CM")
                        .pattern("ST")
                        .define('C', Blocks.CAMPFIRE)
                        .define('M', ModBlocks.MINERAL_MIX_BLOCK)
                        .define('S', ModItems.STACKED_RAW_SLOT)
                        .define('T', ModBlocks.STONE_CASING)
                        .unlockedBy(getHasName(ModBlocks.STONE_CASING), has(ModBlocks.STONE_CASING))
                        .save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DEEP_MERGER)
                        .pattern("DDD")
                        .pattern("RGS")
                        .pattern("EEE")
                        .define('D', Blocks.POINTED_DRIPSTONE)
                        .define('R', ModItems.STACKED_RAW_SLOT)
                        .define('G', Items.GOLD_INGOT)
                        .define('S', ModItems.STACKED_SLOT)
                        .define('E', Blocks.DEEPSLATE)
                        .unlockedBy(getHasName(ModItems.DRIPSTONE_PILE), has(ModItems.DRIPSTONE_PILE))
                        .save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, Blocks.BLAST_FURNACE)
                        .pattern("DDD")
                        .pattern("AFA")
                        .pattern("MMM")
                        .define('D', ModItems.DIORITE_ALLOY)
                        .define('A', ModItems.ANDESITE_ALLOY)
                        .define('F', Blocks.FURNACE)
                        .define('M', ModBlocks.MINERAL_MIX_BLOCK)
                        .unlockedBy(getHasName(Blocks.FURNACE), has(Blocks.FURNACE))
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

    private void glueMixing(RecipeProvider provider, RecipeOutput output, GlueMixerGlueType gType, int strength, ItemLike result, int count) {
        GlueMixerRecipeBuilder.glueMixerRecipe(RecipeCategory.MISC, gType, strength, result, count)
                .unlockedBy(RecipeProvider.getHasName(ModItems.GLUE_BOTTLE), provider.has(ModItems.GLUE_BOTTLE))
                .save(output, "francium_2:glue_mixer/" + Francium.getPath(result.asItem()) + "_from_wooden_merger")
        ;
    }

    private void glueMixing(RecipeProvider provider, RecipeOutput output, GlueMixerGlueType gType, int strength, ItemLike result, int count, String id) {
        GlueMixerRecipeBuilder.glueMixerRecipe(RecipeCategory.MISC, gType, strength, result, count)
                .unlockedBy(RecipeProvider.getHasName(ModItems.GLUE_BOTTLE), provider.has(ModItems.GLUE_BOTTLE))
                .save(output, "francium_2:glue_mixer/" + Francium.getPath(result.asItem()) + "_from_wooden_merger_" + id)
        ;
    }

    private void woodenMerging(RecipeProvider provider, RecipeOutput output, ItemLike firstItem, ItemLike secondItem, ItemLike glue, ItemLike result, int count) {
        WoodenMergerRecipeBuilder.woodenMergerRecipe(RecipeCategory.MISC, firstItem, secondItem, glue, result, count)
                .unlockedBy(RecipeProvider.getHasName(firstItem), provider.has(firstItem))
                .save(output, "francium_2:wooden_merger/" + Francium.getPath(result.asItem()) + "_from_glue_mixer")
        ;
    }

    private void woodenMerging(RecipeProvider provider, RecipeOutput output, ItemLike firstItem, ItemLike secondItem, ItemLike glue, ItemLike result, int count, String id) {
        WoodenMergerRecipeBuilder.woodenMergerRecipe(RecipeCategory.MISC, firstItem, secondItem, glue, result, count)
                .unlockedBy(RecipeProvider.getHasName(firstItem), provider.has(firstItem))
                .save(output, "francium_2:wooden_merger/" + Francium.getPath(result.asItem()) + "_from_glue_mixer_" + id)
        ;
    }

    private void deepMerging(RecipeProvider provider, RecipeOutput output, ItemLike firstItem, ItemLike secondItem, ItemLike thirdItem, ItemLike glue, ItemLike wildcard, ItemLike result, int count) {
        DeepMergerRecipeBuilder.deepMergerRecipe(RecipeCategory.MISC, firstItem, secondItem, thirdItem, glue, wildcard, result, count)
                .unlockedBy(RecipeProvider.getHasName(firstItem), provider.has(firstItem))
                .save(output, "francium_2:deep_merging/" + Francium.getPath(result.asItem()) + "_from_deep_merging")
        ;
    }

    private void deepMerging(RecipeProvider provider, RecipeOutput output, ItemLike firstItem, ItemLike secondItem, ItemLike thirdItem, ItemLike glue, ItemLike wildcard, ItemLike result, int count, String id) {
        DeepMergerRecipeBuilder.deepMergerRecipe(RecipeCategory.MISC, firstItem, secondItem, thirdItem, glue, wildcard, result, count)
                .unlockedBy(RecipeProvider.getHasName(firstItem), provider.has(firstItem))
                .save(output, "francium_2:deep_merging/" + Francium.getPath(result.asItem()) + "_from_deep_merging_" + id)
        ;
    }

    private void deepMerging(RecipeProvider provider, RecipeOutput output, ItemLike firstItem, ItemLike secondItem, ItemLike thirdItem, ItemLike glue, ItemLike result, int count) {
        DeepMergerRecipeBuilder.deepMergerRecipe(RecipeCategory.MISC, firstItem, secondItem, thirdItem, glue, result, count)
                .unlockedBy(RecipeProvider.getHasName(firstItem), provider.has(firstItem))
                .save(output, "francium_2:deep_merging/" + Francium.getPath(result.asItem()) + "_from_deep_merging")
        ;
    }

    private void deepMerging(RecipeProvider provider, RecipeOutput output, ItemLike firstItem, ItemLike secondItem, ItemLike thirdItem, ItemLike glue, ItemLike result, int count, String id) {
        DeepMergerRecipeBuilder.deepMergerRecipe(RecipeCategory.MISC, firstItem, secondItem, thirdItem, glue, result, count)
                .unlockedBy(RecipeProvider.getHasName(firstItem), provider.has(firstItem))
                .save(output, "francium_2:deep_merging/" + Francium.getPath(result.asItem()) + "_from_deep_merging_" + id)
        ;
    }

    private void campfireSmelting(ItemLike in, ItemLike out, RecipeCategory category, int cookingTime, RecipeProvider provider, RecipeOutput output) {
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(in), category, out, 0, cookingTime)
                .unlockedBy(RecipeProvider.getHasName(in), provider.has(in))
                .group(Francium.getPath(out.asItem()))
                .save(output, out + "_from_campfire");
    }

    @Override
    public String getName() {
        return "RecipeGen";
    }
}
