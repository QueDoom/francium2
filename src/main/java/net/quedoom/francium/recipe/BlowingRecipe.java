package net.quedoom.francium.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.quedoom.francium.init.ModRecipeTypes;
import net.quedoom.quet.misc.GetPath;

import java.util.Optional;

public record BlowingRecipe(Block block, BlockState result) implements Recipe<BlockRecipeInput> {
    public static final MapCodec<BlowingRecipe> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
            BuiltInRegistries.BLOCK.byNameCodec().fieldOf("block").forGetter(BlowingRecipe::block),
            BlockState.CODEC.fieldOf("result").forGetter(BlowingRecipe::result)
    ).apply(i, BlowingRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, BlowingRecipe> STREAM_CODEC =
            StreamCodec.composite(
                    BlockRecipeInput.BLOCK_STREAM,
                    BlowingRecipe::block,

                    BlockRecipeInput.BLOCKSTATE_STREAM,
                    BlowingRecipe::result,
                    BlowingRecipe::new);
    
    @Override
    public boolean matches(BlockRecipeInput input, Level level) {
        return block.equals(input.block());
    }

    @Override
    public ItemStack assemble(BlockRecipeInput input) {
        return result.getBlock().asItem().getDefaultInstance();
    }

    @Override
    public boolean showNotification() {
        return false;
    }

    @Override
    public String group() {
        return GetPath.get(result.getBlock());
    }

    @Override
    public RecipeSerializer<? extends Recipe<BlockRecipeInput>> getSerializer() {
        return ModRecipeTypes.BLOWING_SERIALIZER;
    }

    @Override
    public RecipeType<? extends Recipe<BlockRecipeInput>> getType() {
        return ModRecipeTypes.BLOWING;
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.NOT_PLACEABLE;
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return null;
    }

    public static final class Type implements RecipeType<BlowingRecipe> {
        private Type() {}

        public static final BlowingRecipe.Type INSTANCE = new BlowingRecipe.Type();
        public static final String ID = "blowing";
    }

    public static Optional<RecipeHolder<BlowingRecipe>> find(ServerLevel level, Block block) {
        BlockRecipeInput input = new BlockRecipeInput(block);
        return level.recipeAccess().getRecipeFor(ModRecipeTypes.BLOWING, input, level);
    }
}
