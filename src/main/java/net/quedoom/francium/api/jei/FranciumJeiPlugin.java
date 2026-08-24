package net.quedoom.francium.api.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IRecipeManager;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.runtime.IJeiRuntime;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.recipe.v1.sync.SynchronizedRecipes;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeMap;
import net.minecraft.world.level.block.Blocks;
import net.quedoom.francium.Francium;
import net.quedoom.francium.api.jei.custom_recipes.AnvilPressingCategory;
import net.quedoom.francium.api.jei.custom_recipes.AnvilPressingRecipe;
import net.quedoom.francium.api.jei.custom_recipes.DeepMergingCategory;
import net.quedoom.francium.api.jei.custom_recipes.WoodenMergingCategory;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.francium.init.ModItems;
import net.quedoom.francium.init.ModRecipeTypes;
import net.quedoom.francium.recipe.DeepMergingRecipe;
import net.quedoom.francium.recipe.WoodenMergingRecipe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@JeiPlugin
public class FranciumJeiPlugin implements IModPlugin {
    @Override public Identifier getPluginUid() {
        return Francium.id("jei_plugin");
    }
    private static IJeiRuntime jeiRuntime;


    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IGuiHelper guiHelper = registration.getJeiHelpers().getGuiHelper();
        registration.addRecipeCategories(new WoodenMergingCategory(guiHelper));
        registration.addRecipeCategories(new DeepMergingCategory(guiHelper));
        registration.addRecipeCategories(new AnvilPressingCategory(guiHelper));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        if (Minecraft.getInstance().level == null) return;

        SynchronizedRecipes recipeMap = Minecraft.getInstance().level.recipeAccess().getSynchronizedRecipes();

        registration.addRecipes(WoodenMergingCategory.TYPE, new ArrayList<>(recipeMap.getAllOfType(ModRecipeTypes.WOODEN_MERGING).stream().map(RecipeHolder::value).toList()));
        registration.addRecipes(DeepMergingCategory.TYPE, new ArrayList<>(recipeMap.getAllOfType(ModRecipeTypes.DEEP_MERGING).stream().map(RecipeHolder::value).toList()));
        registration.addRecipes(AnvilPressingCategory.TYPE, new ArrayList<>(List.of(
                new AnvilPressingRecipe(ItemStackTemplate.fromNonEmptyStack(new ItemStack(ModBlocks.MINERAL_MIXED_WOODEN_CASING.asItem())),
                        Ingredient.of(ModItems.DRIPSTONE_COATED_MINERAL_MIX), Ingredient.of(ModBlocks.WOODEN_CASING.asItem()))
        )));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addCraftingStation(WoodenMergingCategory.TYPE, ModBlocks.WOODEN_MERGER);
        registration.addCraftingStation(DeepMergingCategory.TYPE, ModBlocks.DEEP_MERGER);
        registration.addCraftingStation(AnvilPressingCategory.TYPE, Blocks.ANVIL);
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime runtime) {
        jeiRuntime = runtime;
        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> pushRecipes());
    }

    private static void pushRecipes() {
        if (jeiRuntime == null || Minecraft.getInstance().level == null) return;
        SynchronizedRecipes recipeMap = Minecraft.getInstance().level.recipeAccess().getSynchronizedRecipes();
        IRecipeManager rm = jeiRuntime.getRecipeManager();
        rm.addRecipes(WoodenMergingCategory.TYPE, new ArrayList<>(recipeMap.getAllOfType(ModRecipeTypes.WOODEN_MERGING).stream().map(RecipeHolder::value).toList()));
        rm.addRecipes(DeepMergingCategory.TYPE, new ArrayList<>(recipeMap.getAllOfType(ModRecipeTypes.DEEP_MERGING).stream().map(RecipeHolder::value).toList()));
    }

//    @Override
//    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
//        registration.addRecipeTransferHandler(WoodenMergerMenu.class, ModMenuTypes.WOODEN_MERGER, ModRecipeTypes.WOODEN_MERGING, 0, 3, 0, 4 * 9);
//    }
}
