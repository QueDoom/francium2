package net.quedoom.francium.block;

import com.mojang.math.OctahedralGroup;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.StonecutterMenu;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

public class RotatableStonecutterBlock extends Block {
    private static final VoxelShape SHAPE = box(0, 0, 0, 16, 9, 16);
    private static final Component CONTAINER_TITLE = Component.translatable("container.stonecutter");

    public static final BooleanProperty ROTATION = BooleanProperty.create("rotation");
    public static final EnumProperty<Direction> FACING = BlockStateProperties.FACING;

    public RotatableStonecutterBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(ROTATION, false).setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ROTATION, FACING);
    }

    protected InteractionResult useWithoutItem(final BlockState state, final Level level, final BlockPos pos, final Player player, final BlockHitResult hitResult) {
        if (!level.isClientSide()) {
            player.openMenu(state.getMenuProvider(level, pos));
            player.awardStat(Stats.INTERACT_WITH_STONECUTTER);
        }

        return InteractionResult.SUCCESS;
    }

    protected @Nullable MenuProvider getMenuProvider(final BlockState state, final Level level, final BlockPos pos) {
        return new SimpleMenuProvider((containerId, inventory, player) -> new StonecutterMenu(containerId, inventory, ContainerLevelAccess.create(level, pos)), CONTAINER_TITLE);
    }

    protected boolean useShapeForLightOcclusion(final BlockState state) {
        return true;
    }


    protected boolean isPathfindable(final BlockState state, final PathComputationType type) {
        return false;
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction face = context.getClickedFace();
        Direction facing = context.getHorizontalDirection();
        boolean rotation = facing.getAxis() != face.getOpposite().getAxis();
        return this.defaultBlockState().setValue(ROTATION, rotation).setValue(FACING, face);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        OctahedralGroup octahedralGroup = switch (state.getValue(FACING)) {
            case NORTH -> OctahedralGroup.BLOCK_ROT_X_90;
            case SOUTH -> OctahedralGroup.BLOCK_ROT_X_270;
            case EAST -> OctahedralGroup.BLOCK_ROT_Z_90;
            case WEST -> OctahedralGroup.BLOCK_ROT_Z_270;
            case DOWN -> OctahedralGroup.INVERT_Y;
            case UP -> null;
        };
        return octahedralGroup == null ? SHAPE : Shapes.rotate(SHAPE, octahedralGroup);
    }
}
