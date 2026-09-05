package net.quedoom.francium.recipe;


import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.quedoom.francium.init.ModItems;
import net.quedoom.francium.init.ModRecipeTypes;

public class TradingRecipe implements Recipe<SingleRecipeInput> {
    public static final MapCodec<TradingRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    ItemStackTemplate.CODEC.fieldOf("result").forGetter(TradingRecipe::getResult),
                    Ingredient.CODEC.fieldOf("ingredient").forGetter(TradingRecipe::getIngredient)
            ).apply(instance, TradingRecipe::new)
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, TradingRecipe> STREAM_CODEC = StreamCodec.composite(
            ItemStackTemplate.STREAM_CODEC,
            TradingRecipe::getResult,
            Ingredient.CONTENTS_STREAM_CODEC,
            TradingRecipe::getIngredient,
            TradingRecipe::new
    );

    final ItemStackTemplate result;
    final Ingredient ingredient;

    public TradingRecipe(ItemStackTemplate result, Ingredient ingredient) {
        this.ingredient = ingredient;
        this.result = result;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }
    public ItemStackTemplate getResult() {
        return result;
    }

    @Override
    public boolean matches(SingleRecipeInput input, Level level) {
        return ingredient.test(input.getItem(0));
    }

    @Override
    public ItemStack assemble(SingleRecipeInput input) {
        return this.result.create();
    }

    @Override
    public boolean showNotification() {
        return false;
    }

    @Override
    public String group() {
        return "trading";
    }

    @Override
    public RecipeSerializer<? extends Recipe<SingleRecipeInput>> getSerializer() {
        return ModRecipeTypes.TRADING_SERIALIZER;
    }

    @Override
    public RecipeType<? extends Recipe<SingleRecipeInput>> getType() {
        return ModRecipeTypes.TRADING;
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.NOT_PLACEABLE;
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return null;
    }
    public static final class Type implements RecipeType<DeepMergingRecipe> {
        private Type() {}

        public static final TradingRecipe.Type INSTANCE = new TradingRecipe.Type();
        public static final String ID = "trading";
    }

}
