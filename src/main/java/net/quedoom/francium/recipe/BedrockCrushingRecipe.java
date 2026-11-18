package net.quedoom.francium.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.PrimitiveCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.quedoom.francium.init.ModRecipeTypes;
import net.quedoom.quet.misc.GetPath;

import java.util.Optional;

public record BedrockCrushingRecipe(Ingredient item, double fallDistance, ItemStackTemplate result) implements Recipe<BedrockCrushingRecipeInput> {
    public static final MapCodec<BedrockCrushingRecipe> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
            Ingredient.CODEC.fieldOf("ingredient").forGetter(BedrockCrushingRecipe::item),
            PrimitiveCodec.DOUBLE.fieldOf("distance").forGetter(BedrockCrushingRecipe::fallDistance),
            ItemStackTemplate.CODEC.fieldOf("result").forGetter(BedrockCrushingRecipe::result)
    ).apply(i, BedrockCrushingRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, BedrockCrushingRecipe> STREAM_CODEC =
            StreamCodec.composite(
                    Ingredient.CONTENTS_STREAM_CODEC,
                    BedrockCrushingRecipe::item,

                    ByteBufCodecs.DOUBLE,
                    BedrockCrushingRecipe::fallDistance,

                    ItemStackTemplate.STREAM_CODEC,
                    BedrockCrushingRecipe::result,
                    BedrockCrushingRecipe::new);
    
    @Override
    public boolean matches(BedrockCrushingRecipeInput input, Level level) {
        return item.test(input.input()) && input.fallDistance() >= fallDistance;
    }

    @Override
    public ItemStack assemble(BedrockCrushingRecipeInput input) {
        return result.create();
    }

    @Override
    public boolean showNotification() {
        return false;
    }

    @Override
    public String group() {
        return GetPath.get(result.create().getItem());
    }

    @Override
    public RecipeSerializer<? extends Recipe<BedrockCrushingRecipeInput>> getSerializer() {
        return ModRecipeTypes.BEDROCK_CRUSHING_SERIALIZER;
    }

    @Override
    public RecipeType<? extends Recipe<BedrockCrushingRecipeInput>> getType() {
        return ModRecipeTypes.BEDROCK_CRUSHING;
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.NOT_PLACEABLE;
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return null;
    }

    public static final class Type implements RecipeType<BedrockCrushingRecipe> {
        private Type() {}

        public static final BedrockCrushingRecipe.Type INSTANCE = new BedrockCrushingRecipe.Type();
        public static final String ID = "bedrock_crushing";
    }
}
