package net.quedoom.francium.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.francium.init.ModItems;

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

                campfireSmelting(ModItems.SAND_PILE, ModItems.GLASS_SHARDS, RecipeCategory.MISC, 200);

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

                shapeless(RecipeCategory.BUILDING_BLOCKS, Blocks.STONE)
                        .requires(ModItems.GRAVEL_PILE, 9)
                        .unlockedBy(getHasName(ModItems.GRAVEL_PILE), has(ModItems.GRAVEL_PILE))
                        .save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, Blocks.GRAVEL)
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

    private void campfireSmelting(ItemLike in, ItemLike out, RecipeCategory category, int cookingTime) {
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(in), category, out, 0, cookingTime);
    }

    @Override
    public String getName() {
        return "RecipeGen";
    }
}
