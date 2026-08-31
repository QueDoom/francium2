package net.quedoom.francium.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.quedoom.francium.Francium;
import net.quedoom.francium.init.ModRecipeTypes;

import java.util.List;

public class    WoodenMergingRecipe implements Recipe<WoodenMergerInput> {
    public static final MapCodec<WoodenMergingRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    ItemStackTemplate.CODEC.fieldOf("result").forGetter(WoodenMergingRecipe::getResult),
                    Ingredient.CODEC.fieldOf("firstItem").forGetter(WoodenMergingRecipe::getFirstIngredient),
                    Ingredient.CODEC.fieldOf("secondItem").forGetter(WoodenMergingRecipe::getSecondIngredient),
                    Ingredient.CODEC.fieldOf("glue").forGetter(WoodenMergingRecipe::getGlue)
            ).apply(instance, WoodenMergingRecipe::new)
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, WoodenMergingRecipe> STREAM_CODEC = StreamCodec.composite(
            ItemStackTemplate.STREAM_CODEC,
            WoodenMergingRecipe::getResult,
            Ingredient.CONTENTS_STREAM_CODEC,
            WoodenMergingRecipe::getFirstIngredient,
            Ingredient.CONTENTS_STREAM_CODEC,
            WoodenMergingRecipe::getSecondIngredient,
            Ingredient.CONTENTS_STREAM_CODEC,
            WoodenMergingRecipe::getGlue,
            WoodenMergingRecipe::new
    );

    ItemStackTemplate result;
    List<Ingredient> ingredients;
    Ingredient glue;

    public WoodenMergingRecipe(ItemStackTemplate result, Ingredient first, Ingredient second, Ingredient glue) {
        this.result = result;
        this.ingredients = List.of(first, second);
        this.glue = glue;
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
    public Ingredient getGlue() {
        return this.glue;
    }

    @Override
    public boolean matches(WoodenMergerInput input, Level level) {
        return ((ingredients.get(0).test(input.first()) && ingredients.get(1).test(input.second())) ||
                (ingredients.get(0).test(input.second()) && ingredients.get(1).test(input.first()))) &&
        glue.test(input.glue());
    }

    @Override
    public ItemStack assemble(WoodenMergerInput input) {
        return this.result.create();
    }

    @Override
    public boolean showNotification() {
        return true;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String group() {
        return "wooden_merging";
    }

    @Override
    public RecipeSerializer<? extends Recipe<WoodenMergerInput>> getSerializer() {
        return ModRecipeTypes.WOODEN_MERGING_SERIALIZER;
    }

    @Override
    public RecipeType<? extends Recipe<WoodenMergerInput>> getType() {
        return ModRecipeTypes.WOODEN_MERGING;
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.NOT_PLACEABLE;
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return null;
    }

    public NonNullList<ItemStack> getRemainingItems(final WoodenMergerInput input) {
        return defaultCraftingReminder(input);
    }

    public static NonNullList<ItemStack> defaultCraftingReminder(final WoodenMergerInput input) {
        NonNullList<ItemStack> result = NonNullList.withSize(input.size(), ItemStack.EMPTY);

        for(int slot = 0; slot < result.size(); ++slot) {
            Item item = input.getItem(slot).getItem();
            ItemStackTemplate remainder = item.getCraftingRemainder();
            result.set(slot, remainder != null ? remainder.create() : ItemStack.EMPTY);
        }

        return result;
    }

    public static final class Type implements RecipeType<WoodenMergingRecipe> {
        private Type() {}

        public static final Type INSTANCE = new Type();
        public static final String ID = "wooden_merging";
    }
}
