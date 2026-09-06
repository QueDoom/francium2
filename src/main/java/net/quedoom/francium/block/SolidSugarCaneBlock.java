package net.quedoom.francium.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SugarCaneBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.redstone.Orientation;
import net.quedoom.francium.Francium;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.francium.init.ModProperties;
import org.jspecify.annotations.Nullable;

public class SolidSugarCaneBlock extends SugarCaneBlock {
    public static final BooleanProperty TOP = ModProperties.TOP;

    public SolidSugarCaneBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(super.defaultBlockState().setValue(TOP, false));
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState aboveState = context.getLevel().getBlockState(context.getClickedPos().above());
        return this.defaultBlockState().setValue(TOP, !(aboveState.is(Blocks.SUGAR_CANE) || aboveState.is(ModBlocks.STRIPPED_SUGAR_CANE)));
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, @Nullable Orientation orientation, boolean movedByPiston) {
        BlockState aboveState = level.getBlockState(pos.above());
        boolean ifelse = aboveState.is(Blocks.SUGAR_CANE) || aboveState.is(ModBlocks.STRIPPED_SUGAR_CANE);
        level.setBlockAndUpdate(pos, state.setValue(TOP, !ifelse));
    }

    @Override
    public void destroy(LevelAccessor level, BlockPos pos, BlockState state) {
        ((Level) level).setBlockAndUpdate(pos, ModBlocks.STRIPPED_SUGAR_CANE.defaultBlockState().setValue(TOP, state.getValue(TOP)));
        destroyAllOtherGuys(level, pos, state);
    }

    protected void destroyAllOtherGuys(LevelAccessor level, BlockPos pos, BlockState state) {
        int i = 1;
        while (level.getBlockState(pos.below(i)).is(Blocks.SUGAR_CANE) || level.getBlockState(pos.below(i)).is(ModBlocks.STRIPPED_SUGAR_CANE)) {
            i += 1;
            Francium.LOGGER.info("{}", i);
        }
        BlockPos bottomestPos = pos.below(i);
        Francium.LOGGER.info(bottomestPos.toString());
        i = 1;
        while (level.getBlockState(bottomestPos.above(i)).is(Blocks.SUGAR_CANE) || level.getBlockState(bottomestPos.above(i)).is(ModBlocks.STRIPPED_SUGAR_CANE)) {
            Francium.LOGGER.info("{}", i);
            boolean isTop = level.getBlockState(bottomestPos.above(i)).getValue(TOP);
            ((Level) level).setBlockAndUpdate(bottomestPos.above(i), ModBlocks.STRIPPED_SUGAR_CANE.defaultBlockState().setValue(TOP, isTop));
            i += 1;
        }
        for (int j = 2; j < i; j++) {
            Block.dropResources(Blocks.SUGAR_CANE.defaultBlockState(), ((Level) level), pos);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(TOP);
    }
}
