package net.quedoom.francium.api.rrv;

import cc.cassian.rrv.api.ReliableRecipeViewerClientPlugin;
import cc.cassian.rrv.api.recipe.ItemView;
import cc.cassian.rrv.client.recipe.ClientRecipeManager;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.quedoom.francium.Francium;
import net.quedoom.francium.init.ModRecipeTypes;
import net.quedoom.francium.recipe.*;
import net.quedoom.quet.init.ModRegistrator;

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
        });
    }
}
