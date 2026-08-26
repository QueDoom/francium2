package net.quedoom.francium.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.PrimitiveCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.quedoom.francium.init.ModRecipeTypes;

public class GlueMixingRecipe implements Recipe<GlueMixerInput> {
    public static final MapCodec<GlueMixingRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    ItemStackTemplate.CODEC.fieldOf("result").forGetter(GlueMixingRecipe::getResult),
                    Ingredient.CODEC.fieldOf("input").forGetter(GlueMixingRecipe::getIngredient),
                    PrimitiveCodec.INT.fieldOf("strength").forGetter(GlueMixingRecipe::getStrength),
                    PrimitiveCodec.INT.fieldOf("gtype").forGetter(GlueMixingRecipe::getGTypeAsInt)

            ).apply(instance, GlueMixingRecipe::new)
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, GlueMixingRecipe> STREAM_CODEC = StreamCodec.composite(
            ItemStackTemplate.STREAM_CODEC,
            GlueMixingRecipe::getResult,
            Ingredient.CONTENTS_STREAM_CODEC,
            GlueMixingRecipe::getIngredient,
            ByteBufCodecs.INT,
            GlueMixingRecipe::getStrength,
            ByteBufCodecs.INT,
            GlueMixingRecipe::getStrength,
            GlueMixingRecipe::new
    );



    ItemStackTemplate result;
    Ingredient ingredient;
    GlueMixerGlueType gtype;
    int strength;

    public GlueMixingRecipe(ItemStackTemplate result, Ingredient first, int strength, int gtype) {
        this.result = result;
        this.ingredient = first;
        this.gtype = GlueMixerGlueType.fromInt(gtype);
        this.strength = strength;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }
    public ItemStackTemplate getResult() {
        return result;
    }
    public GlueMixerGlueType getTypeProperty() {
        return gtype;
    }
    public int getGTypeAsInt() {
        return switch (gtype) {
            case NORMAL -> 0;
            case VEGAN -> 1;
            case ECHO -> 2;
            case SUPER -> 3;
        };
    }
    public int getStrength() {
        return strength;
    }

    @Override
    public boolean matches(GlueMixerInput input, Level level) {
        return getMatches(input, ingredient, gtype, strength);
    }

    private boolean getMatches(GlueMixerInput input, Ingredient in, GlueMixerGlueType gtype, int strength) {
        return in.test(input.input()) && GlueMixerGlueType.test(gtype, input) && strength == this.strength;
    }

    @Override
    public ItemStack assemble(GlueMixerInput input) {
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
    public RecipeSerializer<? extends Recipe<GlueMixerInput>> getSerializer() {
        return ModRecipeTypes.GLUE_MIXING_SERIALIZER;
    }

    @Override
    public RecipeType<? extends Recipe<GlueMixerInput>> getType() {
        return ModRecipeTypes.GLUE_MIXING;
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

        public static final GlueMixingRecipe.Type INSTANCE = new GlueMixingRecipe.Type();
        public static final String ID = "glue_mixing";
    }
}
