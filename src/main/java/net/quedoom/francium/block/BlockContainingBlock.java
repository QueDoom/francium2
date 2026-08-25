package net.quedoom.francium.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.quedoom.francium.block.entity.BlockContainingEntity;
import net.quedoom.francium.block.entity.GlueMixerEntity;
import org.jspecify.annotations.Nullable;

public class BlockContainingBlock extends BaseEntityBlock {
    public BlockContainingBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(BlockContainingBlock::new);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return new BlockContainingEntity(worldPosition, blockState);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!(level.getBlockEntity(pos) instanceof BlockContainingEntity entity)) return InteractionResult.FAIL;
        Container container = entity.getContainer();
        ItemStack stack = container.getItem(0);
        if (!(stack.getItem() instanceof BlockItem)) return InteractionResult.FAIL;
        if (stack.isEmpty()) {
            container.setItem(0, itemStack.copyWithCount(1));
            itemStack.shrink(1);
        } else {
            container.removeItemNoUpdate(0);
            if (itemStack.is(stack.getItem())) {
                itemStack.grow(1);
            } else {
                player.addItem(stack.copyWithCount(1));
            }
        }
        return InteractionResult.SUCCESS;
    }
}
