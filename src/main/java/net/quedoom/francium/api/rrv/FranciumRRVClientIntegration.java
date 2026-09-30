package net.quedoom.francium.api.rrv;

import cc.cassian.rrv.api.ReliableRecipeViewerClientPlugin;
import cc.cassian.rrv.api.recipe.ItemView;
import cc.cassian.rrv.client.recipe.ClientRecipeManager;
import net.quedoom.francium.Francium;
import net.quedoom.francium.init.ModRecipeTypes;
import net.quedoom.francium.recipe.DeepMergingRecipe;
import net.quedoom.francium.recipe.WoodenMergingRecipe;
import net.quedoom.quet.init.ModRegistrator;

public class FranciumRRVClientIntegration implements ReliableRecipeViewerClientPlugin {
    @Override
    public void onIntegrationInitialize() {
        ModRegistrator.setNamespace(Francium.CONSTANT_MOD_ID);
        ItemView.addClientRecipeProvider(recipeList -> {
            ClientRecipeManager.INSTANCE.getRecipesForType(ModRecipeTypes.WOODEN_MERGING).forEach(woodenMergingRecipeRecipeHolder -> {
                WoodenMergingRecipe recipe = woodenMergingRecipeRecipeHolder.value();
                recipeList.add(new WoodenMergingClientRecipe(Francium.of("wooden_merging"), recipe.getFirstIngredient(), recipe.getSecondIngredient(), recipe.getGlue(), recipe.getResult()));
            });
            ClientRecipeManager.INSTANCE.getRecipesForType(ModRecipeTypes.DEEP_MERGING).forEach(deepMergingRecipeRecipeHolder -> {
                DeepMergingRecipe recipe = deepMergingRecipeRecipeHolder.value();
                recipeList.add(new DeepMergingClientRecipe(Francium.of("deep_merging"), recipe.getFirstIngredient(), recipe.getSecondIngredient(), recipe.getThirdIngredient(), recipe.getGlue(), recipe.getWildcardFixEmpty(), recipe.getResult()));
            });
        });
    }
}
