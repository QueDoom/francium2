package net.quedoom.francium.recipe;

import io.netty.buffer.ByteBuf;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public record BlockRecipeInputWithChance(Block block, float chance) implements RecipeInput {
    public static final StreamCodec<RegistryFriendlyByteBuf, Block> BLOCK_STREAM =
            ByteBufCodecs.registry(Registries.BLOCK);

    public static final StreamCodec<ByteBuf, BlockState> BLOCKSTATE_STREAM =
            ByteBufCodecs.idMapper(Block.BLOCK_STATE_REGISTRY);

    @Override
    public ItemStack getItem(int index) {
        return block.asItem().getDefaultInstance();
    }

    @Override
    public int size() {
        return 2;
    }
}
