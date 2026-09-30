package net.quedoom.francium.block.supers;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

public class ShearableEightsEatableBlock extends EighthsEatableBlock {
    private final Block sheared;

    public ShearableEightsEatableBlock(int nutrition, float saturation, Block sheared, Properties properties) {
        super(nutrition, saturation, properties);
        this.sheared = sheared;
    }

    public ShearableEightsEatableBlock(Block sheared, Properties properties) {
        super(properties);
        this.sheared = sheared;
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (itemStack.is(Items.SHEARS)) {
            Direction newFacing = hitResult.getDirection().getAxis().isHorizontal() ? hitResult.getDirection() : Direction.orderedByNearest(player)[0].getOpposite();

            int k = Math.floorMod(RotateableEighthsEatableBlock.rotations(Direction.NORTH) - RotateableEighthsEatableBlock.rotations(newFacing), 4);
            BooleanProperty[] down = {NORTH_EAST_DOWN, SOUTH_EAST_DOWN, SOUTH_WEST_DOWN, NORTH_WEST_DOWN};
            BooleanProperty[] up = {NORTH_EAST_UP, SOUTH_EAST_UP, SOUTH_WEST_UP, NORTH_WEST_UP};

            BlockState newState = sheared.defaultBlockState()
                    .setValue(RotateableEighthsEatableBlock.FACING, newFacing);
            for (int i = 0; i < 4; i++) {
                newState = newState
                        .setValue(down[(i + k) % 4], state.getValue(down[i]))
                        .setValue(up[(i + k) % 4], state.getValue(up[i]));
            }

            level.setBlockAndUpdate(pos, newState);
            itemStack.hurtAndBreak(1, player, InteractionHand.MAIN_HAND);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.TRY_WITH_EMPTY_HAND;
    }
}
