package net.quedoom.francium.init;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.quedoom.francium.Francium;
import net.quedoom.francium.recipe.*;

import javax.xml.stream.events.EntityReference;

public class ModRecipeTypes {

    public static final RecipeSerializer<WoodenMergingRecipe> WOODEN_MERGING_SERIALIZER = Registry.register(
            BuiltInRegistries.RECIPE_SERIALIZER,
            Francium.of(WoodenMergingRecipe.Type.ID),
            new RecipeSerializer<>(WoodenMergingRecipe.CODEC, WoodenMergingRecipe.STREAM_CODEC)
    );

    public static final RecipeType<WoodenMergingRecipe> WOODEN_MERGING = Registry.register(
            BuiltInRegistries.RECIPE_TYPE,
            Francium.of(WoodenMergingRecipe.Type.ID),
            new RecipeType<WoodenMergingRecipe>() {
                @Override
                public String toString() {
                    return WoodenMergingRecipe.Type.ID;
                }
            }
    );

//    public static final RecipeType<WoodenMergingRecipe> WOODEN_MERGING = createRecipeType("wooden_merging", WoodenMergingRecipe.Type.INSTANCE);

    public static final RecipeSerializer<DeepMergingRecipe> DEEP_MERGING_SERIALIZER = Registry.register(
            BuiltInRegistries.RECIPE_SERIALIZER,
            Francium.of(DeepMergingRecipe.Type.ID),
            new RecipeSerializer<>(DeepMergingRecipe.CODEC, DeepMergingRecipe.STREAM_CODEC)
    );

    public static final RecipeType<DeepMergingRecipe> DEEP_MERGING = Registry.register(
            BuiltInRegistries.RECIPE_TYPE,
            Francium.of(DeepMergingRecipe.Type.ID),
            new RecipeType<DeepMergingRecipe>() {
                @Override
                public String toString() {
                    return DeepMergingRecipe.Type.ID;
                }
            }
    );

    public static final RecipeSerializer<BasicAnvilPressingRecipe> BASIC_ANVIL_PRESSING_SERIALIZER = Registry.register(
            BuiltInRegistries.RECIPE_SERIALIZER,
            Francium.of(BasicAnvilPressingRecipe.Type.ID),
            new RecipeSerializer<>(BasicAnvilPressingRecipe.CODEC, BasicAnvilPressingRecipe.STREAM_CODEC)
    );

    public static final RecipeType<BasicAnvilPressingRecipe> BASIC_ANVIL_PRESSING = Registry.register(
            BuiltInRegistries.RECIPE_TYPE,
            Francium.of(BasicAnvilPressingRecipe.Type.ID),
            new RecipeType<BasicAnvilPressingRecipe>() {
                @Override
                public String toString() {
                    return BasicAnvilPressingRecipe.Type.ID;
                }
            }
    );


    public static final RecipeSerializer<GlueMixingRecipe> GLUE_MIXING_SERIALIZER = Registry.register(
            BuiltInRegistries.RECIPE_SERIALIZER,
            Francium.of(GlueMixingRecipe.Type.ID),
            new RecipeSerializer<>(GlueMixingRecipe.CODEC, GlueMixingRecipe.STREAM_CODEC)
    );

    public static final RecipeType<GlueMixingRecipe> GLUE_MIXING = Registry.register(
            BuiltInRegistries.RECIPE_TYPE,
            Francium.of(GlueMixingRecipe.Type.ID),
            new RecipeType<GlueMixingRecipe>() {
                @Override
                public String toString() {
                    return GlueMixingRecipe.Type.ID;
                }
            }
    );

    public static final RecipeSerializer<TradingRecipe> TRADING_SERIALIZER = Registry.register(
            BuiltInRegistries.RECIPE_SERIALIZER,
            Francium.of(TradingRecipe.Type.ID),
            new RecipeSerializer<>(TradingRecipe.CODEC, TradingRecipe.STREAM_CODEC)
    );

    public static final RecipeType<TradingRecipe> TRADING = Registry.register(
            BuiltInRegistries.RECIPE_TYPE,
            Francium.of(TradingRecipe.Type.ID),
            new RecipeType<TradingRecipe>() {
                @Override
                public String toString() {
                    return TradingRecipe.Type.ID;
                }
            }
    );


    private static <T extends Recipe<?>> RecipeSerializer<T> createSerializer(String name, RecipeSerializer<T> instance) {
        return Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, Francium.of(name), instance);
    }
    private static <T extends Recipe<?>> RecipeType<T> createRecipeType(String name, RecipeType<T> instance) {
        return Registry.register(BuiltInRegistries.RECIPE_TYPE, Francium.of(name), instance);
    }
    private static RecipeBookCategory createRecipeCategory(String name) {
        return Registry.register(BuiltInRegistries.RECIPE_BOOK_CATEGORY, Francium.of(name), new RecipeBookCategory());
    }


    public static void registerRecipeTypes() {}

}
