package net.quedoom.francium.block.supers;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.quedoom.francium.init.ModProperties;
import net.quedoom.francium.util.EighthsEatableOctant;
import org.jspecify.annotations.Nullable;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class ThickFoliageBlock extends VegetationBlock {
    public static final BooleanProperty THICK_FOLIAGE_NE = ModProperties.THICK_FOLIAGE_NE;
    public static final BooleanProperty THICK_FOLIAGE_NW = ModProperties.THICK_FOLIAGE_NW;
    public static final BooleanProperty THICK_FOLIAGE_SE = ModProperties.THICK_FOLIAGE_SE;
    public static final BooleanProperty THICK_FOLIAGE_SW = ModProperties.THICK_FOLIAGE_SW;

    private static final VoxelShape NE = box(8, 0, 3, 13, 16, 8);
    private static final VoxelShape NW = box(3, 0, 3, 8, 16, 8);
    private static final VoxelShape SW = box(3, 0, 8, 8, 16, 13);
    private static final VoxelShape SE = box(8, 0, 8, 13, 16, 13);

    private final Block placedOn;
    private final Optional<Item> pickItem;

    public ThickFoliageBlock(Block placedOn, Properties properties) {
        this(placedOn, Optional.empty(), properties);
    }

    public ThickFoliageBlock(Block placedOn, Optional<Item> pickBlockItem, Properties properties) {
        super(properties);
        if (!(placedOn instanceof EighthsEatableBlock)) throw new IllegalArgumentException("Block that Foliage can be placed on must be an instance of " + EighthsEatableBlock.class);
        this.placedOn = placedOn;
        this.pickItem = pickBlockItem;
        this.registerDefaultState(this.getStateDefinition().any().setValue(THICK_FOLIAGE_NE, true).setValue(THICK_FOLIAGE_NW, true).setValue(THICK_FOLIAGE_SE, true).setValue(THICK_FOLIAGE_SW, true));
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        VoxelShape start = Shapes.empty();
        if (state.getValue(THICK_FOLIAGE_NE)) start = Shapes.or(start, NE);
        if (state.getValue(THICK_FOLIAGE_NW)) start = Shapes.or(start, NW);
        if (state.getValue(THICK_FOLIAGE_SE)) start = Shapes.or(start, SE);
        if (state.getValue(THICK_FOLIAGE_SW)) start = Shapes.or(start, SW);
        return start;
    }

    @Override
    protected ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData) {
        return pickItem
                .map(Item::getDefaultInstance)
                .orElseGet(() -> super.getCloneItemStack(level, pos, state, includeData));
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(placedOn);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState below = level.getBlockState(pos.below());
        if (!(below.getBlock() instanceof EighthsEatableBlock)) return this.defaultBlockState();

        return this.defaultBlockState()
                .setValue(THICK_FOLIAGE_NE, below.getValue(EighthsEatableBlock.NORTH_EAST_UP))
                .setValue(THICK_FOLIAGE_NW, below.getValue(EighthsEatableBlock.NORTH_WEST_UP))
                .setValue(THICK_FOLIAGE_SE, below.getValue(EighthsEatableBlock.SOUTH_EAST_UP))
                .setValue(THICK_FOLIAGE_SW, below.getValue(EighthsEatableBlock.SOUTH_WEST_UP))
                ;
    }


    public void updateFromCrop(BlockState foliageState, Level level, BlockPos foliagePos, BooleanProperty quadrant) {
        if (!foliageState.getValue(quadrant)) return;
        level.levelEvent(LevelEvent.PARTICLES_DESTROY_BLOCK, foliagePos, Block.getId(foliageState));
        if (getOnProperties(foliageState) > 1) {
            level.setBlockAndUpdate(foliagePos, foliageState.setValue(quadrant, false));
        } else {
            level.removeBlock(foliagePos, false);
            Containers.dropItemStack(level, foliagePos.getX(), foliagePos.getY() - 0.25F, foliagePos.getZ(), getCloneItemStack(level, foliagePos, foliageState, false));
        }
    }

    public static int getOnProperties(BlockState state) {
        int result = 0;
        if (state.getValue(THICK_FOLIAGE_NE)) result += 1;
        if (state.getValue(THICK_FOLIAGE_NW)) result += 1;
        if (state.getValue(THICK_FOLIAGE_SE)) result += 1;
        if (state.getValue(THICK_FOLIAGE_SW)) result += 1;
        return result;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(THICK_FOLIAGE_NE, THICK_FOLIAGE_NW, THICK_FOLIAGE_SE, THICK_FOLIAGE_SW);
    }

    @Override
    protected MapCodec<? extends VegetationBlock> codec() {
        return simpleCodec(p -> new ThickFoliageBlock(placedOn, p));
    }
}
