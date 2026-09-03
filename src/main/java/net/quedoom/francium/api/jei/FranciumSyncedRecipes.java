package net.quedoom.francium.api.jei;

import net.fabricmc.fabric.api.recipe.v1.sync.SynchronizedRecipes;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FranciumSyncedRecipes {

    private static SynchronizedRecipes syncedRecipes;

    public static void register(SynchronizedRecipes sr) {
        syncedRecipes = sr;
    }

    public static SynchronizedRecipes getSyncedRecipes() {
        return syncedRecipes;
    }

    public static <I extends RecipeInput, T extends Recipe<I>> List<T> getRecipesFromType(RecipeType<T> type) {
        if (syncedRecipes == null) {
            return Collections.emptyList();
        }

        return syncedRecipes.getAllOfType(type).stream()
                .map(RecipeHolder::value)
                .toList();
    }

}
