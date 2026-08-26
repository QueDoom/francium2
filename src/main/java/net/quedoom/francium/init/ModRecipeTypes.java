package net.quedoom.francium.init;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.quedoom.francium.Francium;
import net.quedoom.francium.recipe.AnvilPressingRecipe;
import net.quedoom.francium.recipe.DeepMergingRecipe;
import net.quedoom.francium.recipe.GlueMixingRecipe;
import net.quedoom.francium.recipe.WoodenMergingRecipe;

public class ModRecipeTypes {

    public static final RecipeSerializer<WoodenMergingRecipe> WOODEN_MERGING_SERIALIZER = Registry.register(
            BuiltInRegistries.RECIPE_SERIALIZER,
            Francium.id(WoodenMergingRecipe.Type.ID),
            new RecipeSerializer<>(WoodenMergingRecipe.CODEC, WoodenMergingRecipe.STREAM_CODEC)
    );

    public static final RecipeType<WoodenMergingRecipe> WOODEN_MERGING = Registry.register(
            BuiltInRegistries.RECIPE_TYPE,
            Francium.id(WoodenMergingRecipe.Type.ID),
            new RecipeType<WoodenMergingRecipe>() { }
    );

//    public static final RecipeType<WoodenMergingRecipe> WOODEN_MERGING = createRecipeType("wooden_merging", WoodenMergingRecipe.Type.INSTANCE);

    public static final RecipeSerializer<DeepMergingRecipe> DEEP_MERGING_SERIALIZER = Registry.register(
            BuiltInRegistries.RECIPE_SERIALIZER,
            Francium.id(DeepMergingRecipe.Type.ID),
            new RecipeSerializer<>(DeepMergingRecipe.CODEC, DeepMergingRecipe.STREAM_CODEC)
    );

    public static final RecipeType<DeepMergingRecipe> DEEP_MERGING = Registry.register(
            BuiltInRegistries.RECIPE_TYPE,
            Francium.id(DeepMergingRecipe.Type.ID),
            new RecipeType<DeepMergingRecipe>() { }
    );


    public static final RecipeType<AnvilPressingRecipe> ANVIL_PRESSING = Registry.register(
            BuiltInRegistries.RECIPE_TYPE,
            Francium.id(AnvilPressingRecipe.Type.ID),
            new RecipeType<AnvilPressingRecipe>() { }
    );


    public static final RecipeSerializer<GlueMixingRecipe> GLUE_MIXING_SERIALIZER = Registry.register(
            BuiltInRegistries.RECIPE_SERIALIZER,
            Francium.id(GlueMixingRecipe.Type.ID),
            new RecipeSerializer<>(GlueMixingRecipe.CODEC, GlueMixingRecipe.STREAM_CODEC)
    );

    public static final RecipeType<GlueMixingRecipe> GLUE_MIXING = Registry.register(
            BuiltInRegistries.RECIPE_TYPE,
            Francium.id(GlueMixingRecipe.Type.ID),
            new RecipeType<GlueMixingRecipe>() { }
    );


    private static <T extends Recipe<?>> RecipeSerializer<T> createSerializer(String name, RecipeSerializer<T> instance) {
        return Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, Francium.id(name), instance);
    }
    private static <T extends Recipe<?>> RecipeType<T> createRecipeType(String name, RecipeType<T> instance) {
        return Registry.register(BuiltInRegistries.RECIPE_TYPE, Francium.id(name), instance);
    }
    private static RecipeBookCategory createRecipeCategory(String name) {
        return Registry.register(BuiltInRegistries.RECIPE_BOOK_CATEGORY, Francium.id(name), new RecipeBookCategory());
    }


    public static void registerRecipeTypes() {}

}
