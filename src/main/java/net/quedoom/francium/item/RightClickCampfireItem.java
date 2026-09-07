package net.quedoom.francium.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.quedoom.francium.util.SetBlocker;

public class RightClickCampfireItem extends Item {
    private final Block camfireBlock;

    public RightClickCampfireItem(Properties properties, Block campfireBlock) {
        super(properties);
        this.camfireBlock = campfireBlock;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);
        if (state.is(Blocks.CAMPFIRE)) {
            context.getPlayer().getItemInHand(InteractionHand.MAIN_HAND).shrink(1);
            if (camfireBlock instanceof SetBlocker) {
                ((SetBlocker) camfireBlock).place(level, pos, state);
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}
