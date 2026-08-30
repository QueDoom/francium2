package net.quedoom.francium.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.quedoom.francium.block.AncientBunsBlock;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.francium.init.ModTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.concurrent.ThreadLocalRandom;

@Mixin(Block.class)
public class DestroyBlock {
    @Inject(method = "destroy",
            at = @At(value = "HEAD"))

    private void destroy(LevelAccessor level, BlockPos pos, BlockState state, CallbackInfo ci) {
        if (state.is(Blocks.ACACIA_LOG)) {
            level.setBlock(pos, Blocks.STRIPPED_ACACIA_LOG.defaultBlockState().setValue(BlockStateProperties.AXIS,
                    state.getValue(BlockStateProperties.AXIS)), 1);
        }
        if (state.is(Blocks.BIRCH_LOG)) {
            level.setBlock(pos, Blocks.STRIPPED_BIRCH_LOG.defaultBlockState().setValue(BlockStateProperties.AXIS,
                    state.getValue(BlockStateProperties.AXIS)), 1);
        }
        if (state.is(Blocks.CHERRY_LOG)) {
            level.setBlock(pos, Blocks.STRIPPED_CHERRY_LOG.defaultBlockState().setValue(BlockStateProperties.AXIS,
                    state.getValue(BlockStateProperties.AXIS)), 1);
        }
        if (state.is(Blocks.DARK_OAK_LOG)) {
            level.setBlock(pos, Blocks.STRIPPED_DARK_OAK_LOG.defaultBlockState().setValue(BlockStateProperties.AXIS,
                    state.getValue(BlockStateProperties.AXIS)), 1);
        }
        if (state.is(Blocks.JUNGLE_LOG)) {
            level.setBlock(pos, Blocks.STRIPPED_JUNGLE_LOG.defaultBlockState().setValue(BlockStateProperties.AXIS,
                    state.getValue(BlockStateProperties.AXIS)), 1);
        }
        if (state.is(Blocks.MANGROVE_LOG)) {
            level.setBlock(pos, Blocks.STRIPPED_MANGROVE_LOG.defaultBlockState().setValue(BlockStateProperties.AXIS,
                    state.getValue(BlockStateProperties.AXIS)), 1);
        }
        if (state.is(Blocks.OAK_LOG)) {
            level.setBlock(pos, Blocks.STRIPPED_OAK_LOG.defaultBlockState().setValue(BlockStateProperties.AXIS,
                    state.getValue(BlockStateProperties.AXIS)), 1);
        }
        if (state.is(Blocks.PALE_OAK_LOG)) {
            level.setBlock(pos, Blocks.STRIPPED_PALE_OAK_LOG.defaultBlockState().setValue(BlockStateProperties.AXIS,
                    state.getValue(BlockStateProperties.AXIS)), 1);
        }
        if (state.is(Blocks.SPRUCE_LOG)) {
            level.setBlock(pos, Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState().setValue(BlockStateProperties.AXIS,
                    state.getValue(BlockStateProperties.AXIS)), 1);
        }

        if (state.is(Blocks.ACACIA_WOOD)) {
            level.setBlock(pos, Blocks.STRIPPED_ACACIA_WOOD.defaultBlockState().setValue(BlockStateProperties.AXIS,
                    state.getValue(BlockStateProperties.AXIS)), 1);
        }
        if (state.is(Blocks.BIRCH_WOOD)) {
            level.setBlock(pos, Blocks.STRIPPED_BIRCH_WOOD.defaultBlockState().setValue(BlockStateProperties.AXIS,
                    state.getValue(BlockStateProperties.AXIS)), 1);
        }
        if (state.is(Blocks.CHERRY_WOOD)) {
            level.setBlock(pos, Blocks.STRIPPED_CHERRY_WOOD.defaultBlockState().setValue(BlockStateProperties.AXIS,
                    state.getValue(BlockStateProperties.AXIS)), 1);
        }
        if (state.is(Blocks.DARK_OAK_WOOD)) {
            level.setBlock(pos, Blocks.STRIPPED_DARK_OAK_WOOD.defaultBlockState().setValue(BlockStateProperties.AXIS,
                    state.getValue(BlockStateProperties.AXIS)), 1);
        }
        if (state.is(Blocks.JUNGLE_WOOD)) {
            level.setBlock(pos, Blocks.STRIPPED_JUNGLE_WOOD.defaultBlockState().setValue(BlockStateProperties.AXIS,
                    state.getValue(BlockStateProperties.AXIS)), 1);
        }
        if (state.is(Blocks.MANGROVE_WOOD)) {
            level.setBlock(pos, Blocks.STRIPPED_MANGROVE_WOOD.defaultBlockState().setValue(BlockStateProperties.AXIS,
                    state.getValue(BlockStateProperties.AXIS)), 1);
        }
        if (state.is(Blocks.OAK_WOOD)) {
            level.setBlock(pos, Blocks.STRIPPED_OAK_WOOD.defaultBlockState().setValue(BlockStateProperties.AXIS,
                    state.getValue(BlockStateProperties.AXIS)), 1);
        }
        if (state.is(Blocks.PALE_OAK_WOOD)) {
            level.setBlock(pos, Blocks.STRIPPED_PALE_OAK_WOOD.defaultBlockState().setValue(BlockStateProperties.AXIS,
                    state.getValue(BlockStateProperties.AXIS)), 1);
        }
        if (state.is(Blocks.SPRUCE_WOOD)) {
            level.setBlock(pos, Blocks.STRIPPED_SPRUCE_WOOD.defaultBlockState().setValue(BlockStateProperties.AXIS,
                    state.getValue(BlockStateProperties.AXIS)), 1);
        }

        if (state.is(ModTags.Blocks.STONE_ORES)) {
            level.setBlock(pos, Blocks.STONE.defaultBlockState(), 1);
        }
        if (state.is(ModTags.Blocks.DEEPSLATE_ORES)) {
            level.setBlock(pos, Blocks.DEEPSLATE.defaultBlockState(), 1);
        }
        if (state.is(ModTags.Blocks.NETHER_ORES)) {
            level.setBlock(pos, Blocks.NETHERRACK.defaultBlockState(), 1);
        }

        if (state.is(Blocks.STONE)) {
            level.setBlock(pos, Blocks.COBBLESTONE.defaultBlockState(), 1);
        }
        if (state.is(Blocks.DEEPSLATE)) {
            level.setBlock(pos, Blocks.COBBLED_DEEPSLATE.defaultBlockState(), 1);
        }

        if (state.is(Blocks.ANCIENT_DEBRIS)) {
            level.setBlock(pos, AncientBunsBlock.placementState(ModBlocks.ANCIENT_BUNS.defaultBlockState()), 1);
        }


    }
}
