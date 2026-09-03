package net.quedoom.francium.api.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.runtime.IJeiRuntime;
import net.fabricmc.fabric.api.recipe.v1.sync.SynchronizedRecipes;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.quedoom.francium.Francium;
import net.quedoom.francium.api.jei.custom_recipes.AnvilPressingCategory;
import net.quedoom.francium.api.jei.custom_recipes.DeepMergingCategory;
import net.quedoom.francium.api.jei.custom_recipes.GlueMixingCategory;
import net.quedoom.francium.api.jei.custom_recipes.WoodenMergingCategory;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.francium.init.ModRecipeTypes;

import java.util.Collection;
import java.util.List;

@JeiPlugin
public class FranciumJeiPlugin implements IModPlugin {
    @Override public Identifier getPluginUid() {
        return Francium.id("jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IGuiHelper guiHelper = registration.getJeiHelpers().getGuiHelper();
        registration.addRecipeCategories(new WoodenMergingCategory(guiHelper));
        registration.addRecipeCategories(new DeepMergingCategory(guiHelper));
        registration.addRecipeCategories(new GlueMixingCategory(guiHelper));
        //registration.addRecipeCategories(new AnvilPressingCategory(guiHelper));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(WoodenMergingCategory.TYPE, FranciumSyncedRecipes.getRecipesFromType(ModRecipeTypes.WOODEN_MERGING));
        registration.addRecipes(DeepMergingCategory.TYPE, FranciumSyncedRecipes.getRecipesFromType(ModRecipeTypes.DEEP_MERGING));
        registration.addRecipes(GlueMixingCategory.TYPE, FranciumSyncedRecipes.getRecipesFromType(ModRecipeTypes.GLUE_MIXING));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addCraftingStation(WoodenMergingCategory.TYPE, ModBlocks.WOODEN_MERGER);
        registration.addCraftingStation(DeepMergingCategory.TYPE, ModBlocks.DEEP_MERGER);
        registration.addCraftingStation(GlueMixingCategory.TYPE, ModBlocks.GLUE_MIXER);
    }
}
