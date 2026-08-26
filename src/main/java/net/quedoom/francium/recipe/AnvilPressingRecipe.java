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

import java.util.List;

public class AnvilPressingRecipe implements Recipe<AnvilPressingRecipeInput> {

    public static final MapCodec<AnvilPressingRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    ItemStackTemplate.CODEC.fieldOf("result").forGetter(AnvilPressingRecipe::getResult),
                    Ingredient.CODEC.fieldOf("firstItem").forGetter(AnvilPressingRecipe::getFirstIngredient),
                    Ingredient.CODEC.fieldOf("secondItem").forGetter(AnvilPressingRecipe::getSecondIngredient)
            ).apply(instance, AnvilPressingRecipe::new)
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, AnvilPressingRecipe> STREAM_CODEC = StreamCodec.composite(
            ItemStackTemplate.STREAM_CODEC,
            AnvilPressingRecipe::getResult,
            Ingredient.CONTENTS_STREAM_CODEC,
            AnvilPressingRecipe::getFirstIngredient,
            Ingredient.CONTENTS_STREAM_CODEC,
            AnvilPressingRecipe::getSecondIngredient,
            AnvilPressingRecipe::new
    );

    ItemStackTemplate result;
    List<Ingredient> ingredients;

    public AnvilPressingRecipe(ItemStackTemplate result, Ingredient first, Ingredient second) {
        this.result = result;
        this.ingredients = List.of(first, second);
    }

    public ItemStackTemplate getResult() {
        return this.result;
    }
    public Ingredient getFirstIngredient() {
        return this.ingredients.getFirst();
    }
    public Ingredient getSecondIngredient() {
        return this.ingredients.get(1);
    }

    @Override
    public boolean matches(AnvilPressingRecipeInput input, Level level) {
        return this.getFirstIngredient().test(input.first()) && this.getSecondIngredient().test(input.second());
    }

    @Override
    public ItemStack assemble(AnvilPressingRecipeInput input) {
        return this.result.create();
    }

    @Override
    public boolean showNotification() {
        return false;
    }

    @Override
    public String group() {
        return Type.ID;
    }

    @Override
    public RecipeSerializer<? extends Recipe<AnvilPressingRecipeInput>> getSerializer() {
        return ModRecipeTypes.ANVIL_PRESSING_SERIALIZER;
    }

    @Override
    public RecipeType<? extends Recipe<AnvilPressingRecipeInput>> getType() {
        return ModRecipeTypes.ANVIL_PRESSING;
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.NOT_PLACEABLE;
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return null;
    }

    public static final class Type implements RecipeType<AnvilPressingRecipe> {
        private Type() {}

        public static final AnvilPressingRecipe.Type INSTANCE = new AnvilPressingRecipe.Type();
        public static final String ID = "anvil_pressing";
    }
}
