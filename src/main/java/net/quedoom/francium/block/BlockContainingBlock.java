package net.quedoom.francium.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.quedoom.francium.block.entity.BlockContainingEntity;
import net.quedoom.francium.block.entity.GlueMixerEntity;
import net.quedoom.francium.init.ModRecipeTypes;
import net.quedoom.francium.recipe.DeepMergerInput;
import net.quedoom.francium.recipe.DeepMergingRecipe;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

public class BlockContainingBlock extends BaseEntityBlock {
    public BlockContainingBlock(Properties properties, Block parent) {
        super(properties.overrideLootTable(parent.getLootTable()));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(p -> new BlockContainingBlock(p, null));
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return new BlockContainingEntity(worldPosition, blockState);
    }

    @Override
    public void destroy(LevelAccessor level, BlockPos pos, BlockState state) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof BlockContainingEntity blockContainingEntity) {
            if (!blockContainingEntity.isEmpty()) {
                if (blockContainingEntity.getItem(0).getItem() instanceof BlockItem blockItem) {
                    level.setBlock(pos, blockItem.getBlock().defaultBlockState(), Block.UPDATE_ALL);
                }
            }
        }
    }
}
