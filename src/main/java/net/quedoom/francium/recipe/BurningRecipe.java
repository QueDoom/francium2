package net.quedoom.francium.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.PrimitiveCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.quedoom.quet.misc.GetPath;

public record BurningRecipe(Block block, float chance, BlockState result) implements Recipe<BlockRecipeInputWithChance> {
    public static final MapCodec<BurningRecipe> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
            Block.CODEC.fieldOf("block").forGetter(BurningRecipe::block),
            PrimitiveCodec.FLOAT.optionalFieldOf("chance", 0.5f).forGetter(BurningRecipe::chance),
            BlockState.CODEC.fieldOf("result").forGetter(BurningRecipe::result)
    ).apply(i, BurningRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, BurningRecipe> STREAM_CODEC =
            StreamCodec.composite(
                    BlockRecipeInputWithChance.BLOCK_STREAM,
                    BurningRecipe::block,

                    ByteBufCodecs.FLOAT,
                    BurningRecipe::chance,

                    BlockRecipeInputWithChance.BLOCKSTATE_STREAM,
                    BurningRecipe::result,
                    BurningRecipe::new);
    
    @Override
    public boolean matches(BlockRecipeInputWithChance input, Level level) {
        return block.equals(input.block());
    }

    @Override
    public ItemStack assemble(BlockRecipeInputWithChance input) {
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
    public RecipeSerializer<? extends Recipe<BlockRecipeInputWithChance>> getSerializer() {
        return ;
    }

    @Override
    public RecipeType<? extends Recipe<BlockRecipeInputWithChance>> getType() {
        return ;
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.NOT_PLACEABLE;
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return null;
    }
}
