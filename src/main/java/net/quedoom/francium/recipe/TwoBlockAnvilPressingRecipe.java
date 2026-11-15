package net.quedoom.francium.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.PrimitiveCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.quedoom.francium.init.ModItems;
import net.quedoom.francium.init.ModRecipeTypes;

import java.util.Optional;

public record TwoBlockAnvilPressingRecipe(Optional<Ingredient> ingredient, int count, Block topBlock, Block bottomBlock, ItemStackTemplate result) implements Recipe<TwoBlockAnvilPressingRecipeInput> {
    public static final MapCodec<TwoBlockAnvilPressingRecipe> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
            Ingredient.CODEC.optionalFieldOf("item").forGetter(TwoBlockAnvilPressingRecipe::ingredient),
            Codec.INT.optionalFieldOf("count", 1).forGetter(TwoBlockAnvilPressingRecipe::count),
            BuiltInRegistries.BLOCK.byNameCodec().fieldOf("top_block").forGetter(TwoBlockAnvilPressingRecipe::topBlock),
            BuiltInRegistries.BLOCK.byNameCodec().fieldOf("bottom_block").forGetter(TwoBlockAnvilPressingRecipe::bottomBlock),
            ItemStackTemplate.CODEC.fieldOf("result").forGetter(TwoBlockAnvilPressingRecipe::result)
    ).apply(i, TwoBlockAnvilPressingRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, TwoBlockAnvilPressingRecipe> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.optional(Ingredient.CONTENTS_STREAM_CODEC),
                    TwoBlockAnvilPressingRecipe::ingredient,

                    ByteBufCodecs.VAR_INT,
                    TwoBlockAnvilPressingRecipe::count,

                    ByteBufCodecs.registry(Registries.BLOCK),
                    TwoBlockAnvilPressingRecipe::topBlock,

                    ByteBufCodecs.registry(Registries.BLOCK),
                    TwoBlockAnvilPressingRecipe::bottomBlock,

                    ItemStackTemplate.STREAM_CODEC,
                    TwoBlockAnvilPressingRecipe::result,
                    TwoBlockAnvilPressingRecipe::new);

    @Override
    public boolean matches(TwoBlockAnvilPressingRecipeInput input, Level level) {
        if (!input.topState().is(topBlock)) return false;
        if (!input.bottomState().is(bottomBlock)) return false;
        if (ingredient.isEmpty()) return true;

        int total = 0;
        for (ItemStack stack : input.optionalItems()) {
            if (ingredient.get().test(stack)) total += stack.getCount();
        }
        return total >= count;
    }

    @Override
    public ItemStack assemble(TwoBlockAnvilPressingRecipeInput input) {
        return result.create();
    }

    @Override
    public boolean showNotification() {
        return false;
    }

    @Override
    public String group() {
        return "anvil_pressing_twoblock";
    }

    @Override
    public RecipeSerializer<? extends Recipe<TwoBlockAnvilPressingRecipeInput>> getSerializer() {
        return ModRecipeTypes.TWO_BLOCK_ANVIL_PRESSING_SERIALIZER;
    }

    @Override
    public RecipeType<? extends Recipe<TwoBlockAnvilPressingRecipeInput>> getType() {
        return ModRecipeTypes.TWO_BLOCK_ANVIL_PRESSING;
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.NOT_PLACEABLE;
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return null;
    }

    public static final class Type implements RecipeType<TwoBlockAnvilPressingRecipe> {
        private Type() {}

        public static final TwoBlockAnvilPressingRecipe.Type INSTANCE = new TwoBlockAnvilPressingRecipe.Type();
        public static final String ID = "anvil_pressing/twoblock";
    }
}
