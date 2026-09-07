package net.quedoom.francium.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.francium.util.SetBlocker;
import org.jspecify.annotations.Nullable;

public class PotCampfireBlock extends Block implements SetBlocker {
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;

    public PotCampfireBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(LIT, false).setValue(FACING, Direction.NORTH));
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(LIT, false).setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public void place(Level level, BlockPos pos, BlockState state) {
        level.setBlockAndUpdate(pos, ModBlocks.POT_CAMPFIRE.defaultBlockState().setValue(LIT, state.getValue(LIT)).setValue(FACING, state.getValue(FACING)));
    }

    @Override
    public void destroy(LevelAccessor level, BlockPos pos, BlockState state) {
        ((Level) level).setBlockAndUpdate(pos, Blocks.CAMPFIRE.defaultBlockState().setValue(BlockStateProperties.LIT, state.getValue(BlockStateProperties.LIT)).setValue(HorizontalDirectionalBlock.FACING, state.getValue(HorizontalDirectionalBlock.FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT, FACING);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (itemStack.is(ItemTags.SHOVELS)) {
            level.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.LIT, false));
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}
