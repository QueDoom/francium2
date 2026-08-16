package net.quedoom.francium.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
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

    @Override
    public String getName() {
        return "RecipeGen";
    }
}
