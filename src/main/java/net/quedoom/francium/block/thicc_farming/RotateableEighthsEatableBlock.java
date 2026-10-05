package net.quedoom.francium.block.thicc_farming;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.quedoom.francium.util.EighthsEatableOctant;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class RotateableEighthsEatableBlock extends EighthsEatableBlock{
    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;

    public RotateableEighthsEatableBlock(int nutrition, float saturation, Properties properties) {
        super(nutrition, saturation, properties);
        this.registerDefaultState(super.defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    public RotateableEighthsEatableBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(super.defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return super.getStateForPlacement(context).setValue(FACING, context.getNearestLookingDirection().getOpposite());
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return rotateShape(super.getShape(state, level, pos, context), state.getValue(FACING));
    }
    public static int rotations(Direction facing) {
        return (facing.get2DDataValue() + 2) % 4; // NORTH=0, EAST=1, SOUTH=2, WEST=3
    }

    public static VoxelShape rotateShape(VoxelShape shape, Direction facing) {
        VoxelShape result = shape;
        for (int i = 0; i < rotations(facing); i++) {
            VoxelShape[] next = {Shapes.empty()};
            result.forAllBoxes((x1, y1, z1, x2, y2, z2) ->
                    next[0] = Shapes.or(next[0], Shapes.box(1 - z2, y1, x1, 1 - z1, y2, x2)));
            result = next[0];
        }
        return result;
    }

    @Override
    protected @NonNull EighthsEatableOctant getOctant(Vec3 hitPos, BlockPos blockPos, Direction side, Direction facing) {
        double x = hitPos.x - blockPos.getX() - side.getStepX() * 0.01;
        double y = hitPos.y - blockPos.getY() - side.getStepY() * 0.01;
        double z = hitPos.z - blockPos.getZ() - side.getStepZ() * 0.01;


        for (int i = 0; i < rotations(facing); i++) {
            double nx = z;
            z = 1 - x;
            x = nx;
        }

        char ns = z < 0.5 ? 'n' : 's';
        char ew = x < 0.5 ? 'w' : 'e';
        char ud = y < 0.5 ? 'd' : 'u';

        return EighthsEatableOctant.ofChars(ns, ew, ud);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }
}
