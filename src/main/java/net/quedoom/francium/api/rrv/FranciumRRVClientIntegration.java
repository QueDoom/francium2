package net.quedoom.francium.api.rrv;

import cc.cassian.rrv.api.ReliableRecipeViewerClientPlugin;
import cc.cassian.rrv.api.recipe.ItemView;
import cc.cassian.rrv.client.recipe.ClientRecipeManager;
import cc.cassian.rrv.common.ReliableRecipeViewer;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.quedoom.francium.api.rrv.recipe.*;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.francium.init.ModItems;
import net.quedoom.francium.init.ModRecipeTypes;
import net.quedoom.francium.init.ModTags;
import net.quedoom.francium.recipe.*;
import net.quedoom.quet.misc.GetPath;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class FranciumRRVClientIntegration implements ReliableRecipeViewerClientPlugin {
    @Override
    public void onIntegrationInitialize() {
        ItemView.addClientRecipeProvider(recipeList -> {
            ClientRecipeManager.INSTANCE.getRecipesForType(ModRecipeTypes.WOODEN_MERGING).forEach(woodenMergingRecipeRecipeHolder -> {
                WoodenMergingRecipe recipe = woodenMergingRecipeRecipeHolder.value();
                recipeList.add(new WoodenMergingClientRecipe(recipe.getFirstIngredient(), recipe.getSecondIngredient(), recipe.getGlue(), recipe.getResult()));
            });
            ClientRecipeManager.INSTANCE.getRecipesForType(ModRecipeTypes.DEEP_MERGING).forEach(deepMergingRecipeRecipeHolder -> {
                DeepMergingRecipe recipe = deepMergingRecipeRecipeHolder.value();
                recipeList.add(new DeepMergingClientRecipe(recipe.getFirstIngredient(), recipe.getSecondIngredient(), recipe.getThirdIngredient(), recipe.getGlue(), recipe.getWildcard(), recipe.getResult()));
            });
            ClientRecipeManager.INSTANCE.getRecipesForType(ModRecipeTypes.GLUE_MIXING).forEach(glueMixingRecipeRecipeHolder -> {
                GlueMixingRecipe recipe = glueMixingRecipeRecipeHolder.value();
                recipeList.add(WoodenMixingClientRecipe.of(WoodenMixerGlueType.fromIngredient(recipe.getTypeProperty()), recipe.getResult()));
            });
            ClientRecipeManager.INSTANCE.getRecipesForType(ModRecipeTypes.TWO_BLOCK_ANVIL_PRESSING).forEach(twoBlockAnvilPressingRecipeRecipeHolder -> {
                TwoBlockAnvilPressingRecipe recipe = twoBlockAnvilPressingRecipeRecipeHolder.value();
                if (recipe.bottomBlock().asItem() != Items.AIR) {
                    recipeList.add(new TwoBlockPressingClientRecipe(recipe.ingredient(), recipe.count(), recipe.topBlock(), recipe.bottomBlock(), recipe.result()));
                }
            });
            ClientRecipeManager.INSTANCE.getRecipesForType(ModRecipeTypes.BASIC_ANVIL_PRESSING).forEach(basicAnvilPressingRecipeRecipeHolder -> {
                BasicAnvilPressingRecipe recipe = basicAnvilPressingRecipeRecipeHolder.value();
                Block block = recipe.block();
                if (block.asItem() != Items.AIR) {
                    recipeList.add(new BasicAnvilPressingClientRecipe(recipe.ingredient(), recipe.countReq(), recipe.block(), recipe.result()));
                }
            });
            ClientRecipeManager.INSTANCE.getRecipesForType(ModRecipeTypes.BURNING).forEach(burningRecipeRecipeHolder -> {
                BurningRecipe recipe = burningRecipeRecipeHolder.value();
                Block block = recipe.block();
                BlockState result = recipe.result();
                if (block.asItem() != Items.AIR && result.getBlock().asItem() != Items.AIR) {
                    recipeList.add(new BurningClientRecipe(block, recipe.chance(), result));
                }
            });
            ClientRecipeManager.INSTANCE.getRecipesForType(ModRecipeTypes.BLOWING).forEach(blowingRecipeRecipeHolder -> {
                BlowingRecipe recipe = blowingRecipeRecipeHolder.value();
                Block block = recipe.block();
                BlockState result = recipe.result();
                if (block.asItem() != Items.AIR && result.getBlock().asItem() != Items.AIR) {
                    recipeList.add(new BlowingClientRecipe(block, result));
                }
            });
            ClientRecipeManager.INSTANCE.getRecipesForType(ModRecipeTypes.OBSIDIAN_MIXING).forEach(obsidianMixerRecipeRecipeHolder -> {
                ObsidianMixerRecipe recipe = obsidianMixerRecipeRecipeHolder.value();
                Ingredient firstItem = recipe.firstItem();
                Ingredient secondItem = recipe.secondItem();
                Ingredient thirdItem = recipe.thirdItem();
                Ingredient fourthItem = recipe.fourthItem();
                recipeList.add(new ObsidianMixingClientRecipe(firstItem, secondItem, thirdItem, fourthItem, recipe.time(), recipe.result()));
            });
            ClientRecipeManager.INSTANCE.getRecipesForType(ModRecipeTypes.BEDROCK_CRUSHING).forEach(bedrockCrushingRecipeRecipeHolder -> {
                BedrockCrushingRecipe recipe = bedrockCrushingRecipeRecipeHolder.value();
                recipeList.add(new BedrockCrushingClientRecipe(recipe.item(), ((int) recipe.fallDistance()), recipe.result()));
            });

            recipeList.add(new CasingCraftingClientRecipe(ModBlocks.WOODEN_CASING, ModItems.ANDESITE_COATED_ROCK, ModItems.DIORITE_COATED_ROCK, ModItems.GRANITE_COATED_ROCK, null, ModBlocks.STONE_CASING));
            recipeList.add(new CasingCraftingClientRecipe(ModBlocks.WOODEN_CASING, ModItems.ANDESITE_COATED_ROCK, ModItems.PACKED_DEEPSLATE, ModItems.OBSIDIAN_BOOK, ModItems.OBSIDIAN_BOOK, ModBlocks.OBSIDIAN_CASING));
        });

        // region waxable
        List<Block> noWax = List.of(
                // Full blocks
                Blocks.COPPER_BLOCK,
                Blocks.EXPOSED_COPPER,
                Blocks.WEATHERED_COPPER,
                Blocks.OXIDIZED_COPPER,

                // Cut copper
                Blocks.CUT_COPPER,
                Blocks.EXPOSED_CUT_COPPER,
                Blocks.WEATHERED_CUT_COPPER,
                Blocks.OXIDIZED_CUT_COPPER,

                // Cut copper stairs
                Blocks.CUT_COPPER_STAIRS,
                Blocks.EXPOSED_CUT_COPPER_STAIRS,
                Blocks.WEATHERED_CUT_COPPER_STAIRS,
                Blocks.OXIDIZED_CUT_COPPER_STAIRS,

                // Cut copper slabs
                Blocks.CUT_COPPER_SLAB,
                Blocks.EXPOSED_CUT_COPPER_SLAB,
                Blocks.WEATHERED_CUT_COPPER_SLAB,
                Blocks.OXIDIZED_CUT_COPPER_SLAB,

                // Chiseled copper
                Blocks.CHISELED_COPPER,
                Blocks.EXPOSED_CHISELED_COPPER,
                Blocks.WEATHERED_CHISELED_COPPER,
                Blocks.OXIDIZED_CHISELED_COPPER,

                // Copper grate
                Blocks.COPPER_GRATE,
                Blocks.EXPOSED_COPPER_GRATE,
                Blocks.WEATHERED_COPPER_GRATE,
                Blocks.OXIDIZED_COPPER_GRATE,

                // Copper bulb
                Blocks.COPPER_BULB,
                Blocks.EXPOSED_COPPER_BULB,
                Blocks.WEATHERED_COPPER_BULB,
                Blocks.OXIDIZED_COPPER_BULB,

                // Copper door
                Blocks.COPPER_DOOR,
                Blocks.EXPOSED_COPPER_DOOR,
                Blocks.WEATHERED_COPPER_DOOR,
                Blocks.OXIDIZED_COPPER_DOOR,

                // Copper trapdoor
                Blocks.COPPER_TRAPDOOR,
                Blocks.EXPOSED_COPPER_TRAPDOOR,
                Blocks.WEATHERED_COPPER_TRAPDOOR,
                Blocks.OXIDIZED_COPPER_TRAPDOOR,

                // Copper bars
                Blocks.COPPER_BARS.unaffected(),
                Blocks.COPPER_BARS.exposed(),
                Blocks.COPPER_BARS.weathered(),
                Blocks.COPPER_BARS.oxidized(),

                // Copper chain
                Blocks.COPPER_CHAIN.unaffected(),
                Blocks.COPPER_CHAIN.exposed(),
                Blocks.COPPER_CHAIN.weathered(),
                Blocks.COPPER_CHAIN.oxidized(),

                // Copper lantern
                Blocks.COPPER_LANTERN.unaffected(),
                Blocks.COPPER_LANTERN.exposed(),
                Blocks.COPPER_LANTERN.weathered(),
                Blocks.COPPER_LANTERN.oxidized()
        );
        // endregion waxable
        // region reverse oxidizing
        List<Block> noDeox = List.of(
                // Full blocks
                Blocks.COPPER_BLOCK,
                Blocks.EXPOSED_COPPER,
                Blocks.WEATHERED_COPPER,

                // Cut copper
                Blocks.CUT_COPPER,
                Blocks.EXPOSED_CUT_COPPER,
                Blocks.WEATHERED_CUT_COPPER,

                // Cut copper stairs
                Blocks.CUT_COPPER_STAIRS,
                Blocks.EXPOSED_CUT_COPPER_STAIRS,
                Blocks.WEATHERED_CUT_COPPER_STAIRS,

                // Cut copper slabs
                Blocks.CUT_COPPER_SLAB,
                Blocks.EXPOSED_CUT_COPPER_SLAB,
                Blocks.WEATHERED_CUT_COPPER_SLAB,

                // Chiseled copper
                Blocks.CHISELED_COPPER,
                Blocks.EXPOSED_CHISELED_COPPER,
                Blocks.WEATHERED_CHISELED_COPPER,

                // Copper grate
                Blocks.COPPER_GRATE,
                Blocks.EXPOSED_COPPER_GRATE,
                Blocks.WEATHERED_COPPER_GRATE,

                // Copper bulb
                Blocks.COPPER_BULB,
                Blocks.EXPOSED_COPPER_BULB,
                Blocks.WEATHERED_COPPER_BULB,

                // Copper door
                Blocks.COPPER_DOOR,
                Blocks.EXPOSED_COPPER_DOOR,
                Blocks.WEATHERED_COPPER_DOOR,

                // Copper trapdoor
                Blocks.COPPER_TRAPDOOR,
                Blocks.EXPOSED_COPPER_TRAPDOOR,
                Blocks.WEATHERED_COPPER_TRAPDOOR,

                // Copper bars
                Blocks.COPPER_BARS.unaffected(),
                Blocks.COPPER_BARS.exposed(),
                Blocks.COPPER_BARS.weathered(),

                // Copper chain
                Blocks.COPPER_CHAIN.unaffected(),
                Blocks.COPPER_CHAIN.exposed(),
                Blocks.COPPER_CHAIN.weathered(),

                // Copper lantern
                Blocks.COPPER_LANTERN.unaffected(),
                Blocks.COPPER_LANTERN.exposed(),
                Blocks.COPPER_LANTERN.weathered(),

                // Lightning rod
                Blocks.LIGHTNING_ROD,
                Blocks.EXPOSED_LIGHTNING_ROD,
                Blocks.WEATHERED_LIGHTNING_ROD,

                // Copper chest
                Blocks.COPPER_CHEST,
                Blocks.EXPOSED_COPPER_CHEST,
                Blocks.WEATHERED_COPPER_CHEST,

                // Copper golem statue
                Blocks.COPPER_GOLEM_STATUE,
                Blocks.EXPOSED_COPPER_GOLEM_STATUE,
                Blocks.WEATHERED_COPPER_GOLEM_STATUE
        );
        // endregion reverse oxidizin

        for (Block block : noWax) {
            ItemView.excludeRecipes(ReliableRecipeViewer.of("world_interaction"),
                    Identifier.withDefaultNamespace("/world_interaction/wax_off_waxed_" + GetPath.get(block))
            );
        }
        for (Block block : noWax) {
            ItemView.excludeRecipes(ReliableRecipeViewer.of("world_interaction"),
                    Identifier.withDefaultNamespace("/world_interaction/" + GetPath.get(block) + "_reverse_oxidizing")
            );
        }
    }
}
