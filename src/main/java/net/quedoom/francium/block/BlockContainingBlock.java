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
    private final BlockState parent;

    public BlockContainingBlock(Properties properties, BlockState parent) {
        super(properties);
        this.parent = parent;
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec((properties1 -> new BlockContainingBlock(properties1, parent)));
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return new BlockContainingEntity(worldPosition, blockState);
    }

    @Override
    public void destroy(LevelAccessor level, BlockPos pos, BlockState state) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof BlockContainingEntity blockContainingEntity) {
            if (blockContainingEntity.isEmpty()) {
                Containers.dropItemStack(((Level) level), pos.getX(), pos.getY(), pos.getZ(), this.parent.getBlock().asItem().getDefaultInstance());
            } else {
                ItemStack stack =  blockContainingEntity.getItem(0);
                Containers.dropItemStack(((Level) level), pos.getX(), pos.getY(), pos.getZ(), stack);
                ((Level) level).setBlockAndUpdate(pos, this.parent);
            }
        }
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState onState, Entity entity) {
        if (!(entity instanceof ItemEntity itemEntity)) return;
        ItemStack itemStack = itemEntity.getItem();
        if (!(level.getBlockEntity(pos) instanceof BlockContainingEntity blockEntity)) return;
        if (!(itemStack.getItem() instanceof BlockItem)) return;
        if (blockEntity.isEmpty()) {
            blockEntity.setItem(0, itemStack.copyWithCount(1));
            itemStack.shrink(1);
        } else {
            blockEntity.removeItemNoUpdate(0);
            if (itemStack.is(blockEntity.getItem(0).getItem())) {
                itemStack.grow(1);
            }
        }
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!(level.getBlockEntity(pos) instanceof BlockContainingEntity blockEntity)) return InteractionResult.FAIL;
        if (!(itemStack.getItem() instanceof BlockItem)) return InteractionResult.FAIL;
        if (blockEntity.isEmpty()) {
            blockEntity.setItem(0, itemStack.copyWithCount(1));
            itemStack.shrink(1);
        } else {
            blockEntity.removeItemNoUpdate(0);
            if (itemStack.is(blockEntity.getItem(0).getItem())) {
                itemStack.grow(1);
            } else {
                player.addItem(blockEntity.getItem(0).copyWithCount(1));
            }
        }
        return InteractionResult.SUCCESS;
    }
}
