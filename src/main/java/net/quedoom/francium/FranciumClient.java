package net.quedoom.francium;


import cc.cassian.rrv.api.recipe.ItemView;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.recipe.v1.sync.ClientRecipeSynchronizedEvent;
import net.fabricmc.fabric.api.event.lifecycle.v1.CommonLifecycleEvents;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fabricmc.fabric.api.recipe.v1.sync.SynchronizedRecipes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.block.Block;
import net.quedoom.francium.api.rrv.recipe.ForbiddenClientRecipe;
import net.quedoom.francium.block.entity.*;
import net.quedoom.francium.block.menu.BundleTableScreen;
import net.quedoom.francium.block.menu.DeepMergerScreen;
import net.quedoom.francium.block.menu.WoodenMergerScreen;
import net.quedoom.francium.datagen.recipe.FranciumSyncedRecipes;
import net.quedoom.francium.init.ModBlockEntities;
import net.quedoom.francium.init.ModEntityTypes;
import net.quedoom.francium.init.ModMenuTypes;
import net.quedoom.francium.init.ModTags;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class FranciumClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientRecipeSynchronizedEvent.EVENT.register(FranciumClient::compareWithLocalServer);

        BlockEntityRenderers.register(ModBlockEntities.DEEP_MERGER, DeepMergerRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.GLUE_MIXER, GlueMixerRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.OBSIDIAN_MIXER, ObsidianMixerRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.BLOCK_CONTAINING, BlockContainingRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.BLOCK_CONTAINING_ITEMS, BlockContainingItemsRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.FAN, FanBlockEntityRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.BEDROCK_PLATEAU, BedrockPlateauEntityRenderer::new);

        EntityRenderers.register(ModEntityTypes.STEEL_IN_A_BOTTLE, ThrownItemRenderer::new);

        MenuScreens.register(ModMenuTypes.WOODEN_MERGER, WoodenMergerScreen::new);
        MenuScreens.register(ModMenuTypes.DEEP_MERGER, DeepMergerScreen::new);
        MenuScreens.register(ModMenuTypes.BUNDLE_TABLE, BundleTableScreen::new);

        CommonLifecycleEvents.TAGS_LOADED.register(((registries, client) -> {
            ItemView.addClientRecipeProvider(recipeList -> {
                final Set<Item> items = new LinkedHashSet<>();
                for (TagKey<Block> tag : List.of(
                        ModTags.Blocks.DROPS_FORBIDDEN_DUST,
                        ModTags.Blocks.DROPS_FORBIDDEN_FLAKE,
                        ModTags.Blocks.SMALL_DROPS_FORBIDDEN_FLAKE)) {
                    for (Block block : ModTags.blocksInTag(tag)) {
                        Item item = block.asItem();
                        if (item != Items.AIR) items.add(item);
                    }
                }
                for (Item item : items) {
                    recipeList.add(new ForbiddenClientRecipe(item));
                }
            });
        }));
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
