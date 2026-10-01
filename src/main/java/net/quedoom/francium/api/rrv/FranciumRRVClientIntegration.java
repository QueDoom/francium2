package net.quedoom.francium.api.rrv;

import cc.cassian.rrv.api.ReliableRecipeViewerClientPlugin;
import cc.cassian.rrv.api.recipe.ItemView;
import cc.cassian.rrv.client.recipe.ClientRecipeManager;
import net.quedoom.francium.Francium;
import net.quedoom.francium.init.ModRecipeTypes;
import net.quedoom.francium.recipe.DeepMergingRecipe;
import net.quedoom.francium.recipe.GlueMixingRecipe;
import net.quedoom.francium.recipe.WoodenMergingRecipe;
import net.quedoom.francium.recipe.WoodenMixerGlueType;
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
        });
    }
}
