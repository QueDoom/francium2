package net.quedoom.francium.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SugarCaneBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
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

    private VoxelShape shape (BlockState state) {
        boolean isTop = state.getValue(TOP);
        VoxelShape straw0 = box(toDecimal(3), 0, toDecimal(4), toDecimal(5), isTop ? toDecimal(10) : 1, toDecimal(6));
        VoxelShape straw1 = box(toDecimal(4), 0, toDecimal(10), toDecimal(6), isTop ? toDecimal(10) : 1, toDecimal(12));
        VoxelShape straw2 = box(toDecimal(5), 0, toDecimal(7), toDecimal(7), isTop ? toDecimal(10) : 1, toDecimal(9));
        VoxelShape straw3 = box(toDecimal(10), 0, toDecimal(3), toDecimal(12), isTop ? toDecimal(10) : 1, toDecimal(5));
        VoxelShape straw4 = box(toDecimal(9), 0, toDecimal(11), toDecimal(11), isTop ? toDecimal(10) : 1, toDecimal(13));
//
//        return box(toDecimal(3), 0, toDecimal(4), toDecimal(5), isTop ? toDecimal(10) : 1, toDecimal(6));
        return Shapes.or(straw0, straw1, straw2, straw3, straw4);
//        return box(0, 0, 0, toDecimal(4), toDecimal(4), toDecimal(4));
    }

    private double toDecimal(double bollas) {
        Francium.LOGGER.info("{}", 0.0625f * bollas);
        return 0.0625f * bollas;
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
