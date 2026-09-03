package net.quedoom.francium.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.PrimitiveCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.francium.init.ModItems;
import net.quedoom.francium.init.ModRecipeTypes;
import net.quedoom.francium.init.ModTags;

public class GlueMixingRecipe implements Recipe<GlueMixerInput> {
    public static final MapCodec<GlueMixingRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    ItemStackTemplate.CODEC.fieldOf("result").forGetter(GlueMixingRecipe::getResult),
                    Ingredient.CODEC.fieldOf("input").forGetter(GlueMixingRecipe::getIngredient),
                    Ingredient.CODEC.fieldOf("strength").forGetter(GlueMixingRecipe::getStrength),
                    Ingredient.CODEC.fieldOf("gType").forGetter(GlueMixingRecipe::getTypeProperty)
            ).apply(instance, GlueMixingRecipe::new)
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, GlueMixingRecipe> STREAM_CODEC = StreamCodec.composite(
            ItemStackTemplate.STREAM_CODEC,
            GlueMixingRecipe::getResult,
            Ingredient.CONTENTS_STREAM_CODEC,
            GlueMixingRecipe::getIngredient,
            Ingredient.CONTENTS_STREAM_CODEC,
            GlueMixingRecipe::getStrength,
            Ingredient.CONTENTS_STREAM_CODEC,
            GlueMixingRecipe::getTypeProperty,
            GlueMixingRecipe::new
    );



    final ItemStackTemplate result;
    final Ingredient ingredient;
    final Ingredient gtype;
    final Ingredient strength;

    public GlueMixingRecipe(ItemStackTemplate result, Ingredient first, Ingredient strength, Ingredient type) {
        this.result = result;
        this.ingredient = first;
        this.gtype = type;
        this.strength = strength;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }
    public ItemStackTemplate getResult() {
        return result;
    }
    public Ingredient getTypeProperty() {
        return gtype;
    }
    public Ingredient getGTypeAsBothBlockAndItem() {
        if (gtype.test(ModItems.LEAF.getDefaultInstance())) {
            return Ingredient.of(
                    ModItems.LEAF,
                    Items.OAK_LEAVES,
                    Items.ACACIA_LEAVES,
                    Items.CHERRY_LEAVES,
                    Items.PALE_OAK_LEAVES,
                    Items.DARK_OAK_LEAVES,
                    Items.MANGROVE_LEAVES,
                    Items.BIRCH_LEAVES
                    );
        } else if (gtype.test(Items.ECHO_SHARD.getDefaultInstance())) {
            return Ingredient.of(ModBlocks.ECHO_BLOCK.asItem(), Items.ECHO_SHARD);
        }
        return gtype;
    }
    public Ingredient getStrength() {
        return strength;
    }

    @Override
    public boolean matches(GlueMixerInput input, Level level) {
        return getMatches(input, ingredient, gtype, strength);
    }

    private boolean getMatches(GlueMixerInput input, Ingredient in, Ingredient type, Ingredient strength) {
        return in.test(input.input()) && type.test(input.type()) && strength.test(input.strength());
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
