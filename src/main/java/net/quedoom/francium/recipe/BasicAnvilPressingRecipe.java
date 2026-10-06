package net.quedoom.francium.recipe;

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
import net.quedoom.francium.init.ModRecipeTypes;

public record BasicAnvilPressingRecipe(Ingredient ingredient, int countReq, Block block, ItemStackTemplate result) implements Recipe<BasicAnvilPressingRecipeInput> {
    public static final MapCodec<BasicAnvilPressingRecipe> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
            Ingredient.CODEC.fieldOf("block").forGetter(BasicAnvilPressingRecipe::ingredient),
            PrimitiveCodec.INT.optionalFieldOf("chance", 1).forGetter(BasicAnvilPressingRecipe::countReq),
            BuiltInRegistries.BLOCK.byNameCodec().fieldOf("block").forGetter(BasicAnvilPressingRecipe::block),
            ItemStackTemplate.CODEC.fieldOf("result").forGetter(BasicAnvilPressingRecipe::result)
    ).apply(i, BasicAnvilPressingRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, BasicAnvilPressingRecipe> STREAM_CODEC =
            StreamCodec.composite(
                    Ingredient.CONTENTS_STREAM_CODEC,
                    BasicAnvilPressingRecipe::ingredient,

                    ByteBufCodecs.VAR_INT,
                    BasicAnvilPressingRecipe::countReq,

                    ByteBufCodecs.registry(Registries.BLOCK),
                    BasicAnvilPressingRecipe::block,

                    ItemStackTemplate.STREAM_CODEC,
                    BasicAnvilPressingRecipe::result,
                    BasicAnvilPressingRecipe::new);

    @Override
    public boolean matches(BasicAnvilPressingRecipeInput input, Level level) {
        if (!input.block().is(block)) return false;

        int total = 0;
        for (ItemStack stack : input.items()) {
            if (ingredient.test(stack)) total += stack.getCount();
        }
        return total >= countReq;
    }

    @Override
    public ItemStack assemble(BasicAnvilPressingRecipeInput input) {
        return result.create();
    }

    @Override
    public boolean showNotification() {
        return false;
    }

    @Override
    public String group() {
        return "anvil_pressing_basic";
    }

    @Override
    public RecipeSerializer<? extends Recipe<BasicAnvilPressingRecipeInput>> getSerializer() {
        return ModRecipeTypes.BASIC_ANVIL_PRESSING_SERIALIZER;
    }

    @Override
    public RecipeType<? extends Recipe<BasicAnvilPressingRecipeInput>> getType() {
        return ModRecipeTypes.BASIC_ANVIL_PRESSING;
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

        public static final BasicAnvilPressingRecipe.Type INSTANCE = new BasicAnvilPressingRecipe.Type();
        public static final String ID = "anvil_pressing/basic";
    }
}
