package net.quedoom.francium;


import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.recipe.v1.sync.ClientRecipeSynchronizedEvent;
import net.fabricmc.fabric.api.recipe.v1.sync.SynchronizedRecipes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.quedoom.francium.api.jei.FranciumSyncedRecipes;
import net.quedoom.francium.block.entity.BlockContainingRenderer;
import net.quedoom.francium.block.entity.DeepMergerRenderer;
import net.quedoom.francium.block.entity.GlueMixerRenderer;
import net.quedoom.francium.block.menu.BundleTableScreen;
import net.quedoom.francium.block.menu.DeepMergerScreen;
import net.quedoom.francium.block.menu.WoodenMergerMenu;
import net.quedoom.francium.block.menu.WoodenMergerScreen;
import net.quedoom.francium.init.ModBlockEntities;
import net.quedoom.francium.init.ModMenuTypes;

public class FranciumClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientRecipeSynchronizedEvent.EVENT.register(FranciumClient::compareWithLocalServer);

        BlockEntityRenderers.register(ModBlockEntities.DEEP_MERGER_ENTITY, DeepMergerRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.GLUE_MIXER_ENTITY, GlueMixerRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.BLOCK_CONTAINING_ENTITY, BlockContainingRenderer::new);

        MenuScreens.register(ModMenuTypes.WOODEN_MERGER, WoodenMergerScreen::new);
        MenuScreens.register(ModMenuTypes.DEEP_MERGER, DeepMergerScreen::new);
        MenuScreens.register(ModMenuTypes.BUNDLE_TABLE, BundleTableScreen::new);
    }

    private static void compareWithLocalServer(Minecraft mc, SynchronizedRecipes synchronizedRecipes) {
        if (mc.getSingleplayerServer() == null) {
            return;
        }

        RecipeManager recipeManager = mc.getSingleplayerServer().getRecipeManager();

        for (RecipeHolder<?> recipeHolder : synchronizedRecipes.recipes()) {
            RecipeHolder<?> serverRecipeHolder = recipeManager.getSynchronizedRecipes().get(recipeHolder.id());

            if (serverRecipeHolder.value().getSerializer() != recipeHolder.value().getSerializer()) {
                throw new IllegalStateException("Client and server have mismatched serializer for recipe '" + recipeHolder.id().toString() + "'!");
            }

            if (serverRecipeHolder.value().getType() != recipeHolder.value().getType()) {
                throw new IllegalStateException("Client and server have mismatched type for recipe '" + recipeHolder.id() + "'!");
            }

            // This should be valid case when we include other mods, just invalid for vanilla sync.
            if (serverRecipeHolder.value().getClass() != recipeHolder.value().getClass()) {
                throw new IllegalStateException("Client and server have mismatched class for recipe '" + recipeHolder.id() + "'!");
            }
        }

        FranciumSyncedRecipes.register(synchronizedRecipes);
    }
}
