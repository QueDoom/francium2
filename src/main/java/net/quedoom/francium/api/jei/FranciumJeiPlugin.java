package net.quedoom.francium.api.jei;

import com.google.gson.JsonElement;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.JsonOps;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IRecipeManager;
import mezz.jei.api.recipe.types.IRecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.runtime.IJeiRuntime;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.recipe.v1.sync.SynchronizedRecipes;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.RegistryOps;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeMap;
import net.minecraft.world.level.block.Blocks;
import net.quedoom.francium.Francium;
import net.quedoom.francium.api.jei.custom_recipes.AnvilPressingCategory;
import net.quedoom.francium.recipe.AnvilPressingRecipe;
import net.quedoom.francium.api.jei.custom_recipes.DeepMergingCategory;
import net.quedoom.francium.api.jei.custom_recipes.WoodenMergingCategory;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.francium.init.ModItems;
import net.quedoom.francium.init.ModRecipeTypes;
import net.quedoom.francium.recipe.WoodenMergingRecipe;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@JeiPlugin
public class FranciumJeiPlugin implements IModPlugin {
    @Override public Identifier getPluginUid() {
        return Francium.id("jei_plugin");
    }
    private static IJeiRuntime jeiRuntime;

    public static IJeiRuntime getJeiRuntime() {
        return jeiRuntime;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IGuiHelper guiHelper = registration.getJeiHelpers().getGuiHelper();
        registration.addRecipeCategories(new WoodenMergingCategory(guiHelper));
        registration.addRecipeCategories(new DeepMergingCategory(guiHelper));
        registration.addRecipeCategories(new AnvilPressingCategory(guiHelper));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {

    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addCraftingStation(WoodenMergingCategory.TYPE, ModBlocks.WOODEN_MERGER);
    }

}
