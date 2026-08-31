package net.quedoom.francium.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.quedoom.francium.init.ModItems;
import net.quedoom.francium.init.ModRecipeTypes;

import java.util.List;

public class DeepMergingRecipe implements Recipe<DeepMergerInput>{
    public static final MapCodec<DeepMergingRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    ItemStackTemplate.CODEC.fieldOf("result").forGetter(DeepMergingRecipe::getResult),
                    Ingredient.CODEC.fieldOf("firstItem").forGetter(DeepMergingRecipe::getFirstIngredient),
                    Ingredient.CODEC.fieldOf("secondItem").forGetter(DeepMergingRecipe::getSecondIngredient),
                    Ingredient.CODEC.fieldOf("thirdItem").forGetter(DeepMergingRecipe::getFirstIngredient),
                    Ingredient.CODEC.fieldOf("glue").forGetter(DeepMergingRecipe::getGlue),
                    Ingredient.CODEC.optionalFieldOf("wildcard", Ingredient.of(ModItems.UNUSED_ITEM_BECAUSE_I_CANT_FIGURE_OUT_HOW_TO_MAKE_OPTIONAL_ITEMS_BECAUSE_IM_STUPID)).forGetter(DeepMergingRecipe::getWildcard)
            ).apply(instance, DeepMergingRecipe::new)
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, DeepMergingRecipe> STREAM_CODEC = StreamCodec.composite(
            ItemStackTemplate.STREAM_CODEC,
            DeepMergingRecipe::getResult,
            Ingredient.CONTENTS_STREAM_CODEC,
            DeepMergingRecipe::getFirstIngredient,
            Ingredient.CONTENTS_STREAM_CODEC,
            DeepMergingRecipe::getSecondIngredient,
            Ingredient.CONTENTS_STREAM_CODEC,
            DeepMergingRecipe::getThirdIngredient,
            Ingredient.CONTENTS_STREAM_CODEC,
            DeepMergingRecipe::getGlue,
            Ingredient.CONTENTS_STREAM_CODEC,
            DeepMergingRecipe::getWildcard,
            DeepMergingRecipe::new
    );

    ItemStackTemplate result;
    List<Ingredient> ingredients;
    Ingredient glue;
    Ingredient wildcard;

    public DeepMergingRecipe(ItemStackTemplate result, Ingredient first, Ingredient second, Ingredient third, Ingredient glue, Ingredient wildcard) {
        this.result = result;
        this.ingredients = List.of(first, second, third);
        this.glue = glue;
        this.wildcard = wildcard;
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
    public Ingredient getThirdIngredient() {
        return this.ingredients.get(2);
    }
    public Ingredient getGlue() {
        return this.glue;
    }
    public Ingredient getWildcard() {
        return this.wildcard;
    }

    @Override
    public boolean showNotification() {
        return true;
    }

    @Override
    public boolean matches(DeepMergerInput input, Level level) {
        return (getMatches(input, 0, 1, 2) || getMatches(input, 0, 2, 1) || 
                getMatches(input, 1, 0, 2) || getMatches(input, 1, 2, 0) || 
                getMatches(input, 2, 0, 1) || getMatches(input, 2, 1, 0)) &&
                glue.test(input.glue()) && (wildcard.isEmpty() || wildcard.test(input.wildcard()));
    }

    public NonNullList<ItemStack> getRemainingItems(final DeepMergerInput input) {
        return defaultCraftingReminder(input);
    }

    public static NonNullList<ItemStack> defaultCraftingReminder(final DeepMergerInput input) {
        NonNullList<ItemStack> result = NonNullList.withSize(input.size(), ItemStack.EMPTY);

        for(int slot = 0; slot < result.size(); ++slot) {
            Item item = input.getItem(slot).getItem();
            ItemStackTemplate remainder = item.getCraftingRemainder();
            result.set(slot, remainder != null ? remainder.create() : ItemStack.EMPTY);
        }

        return result;
    }

    private boolean getMatches(DeepMergerInput input, int first, int second, int third) {
        return ingredients.get(0).test(input.getItem(first)) &&
            ingredients.get(1).test(input.getItem(second)) &&
            ingredients.get(2).test(input.getItem(third));
    }

    @Override
    public ItemStack assemble(DeepMergerInput input) {
        return this.result.create();
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override 
    public String group() {
        return "deep_merging";
    }

    @Override
    public RecipeSerializer<? extends Recipe<DeepMergerInput>> getSerializer() {
        return ModRecipeTypes.DEEP_MERGING_SERIALIZER;
    }

    @Override
    public RecipeType<? extends Recipe<DeepMergerInput>> getType() {
        return ModRecipeTypes.DEEP_MERGING;
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

        public static final DeepMergingRecipe.Type INSTANCE = new DeepMergingRecipe.Type();
        public static final String ID = "deep_merging";
    }
}
