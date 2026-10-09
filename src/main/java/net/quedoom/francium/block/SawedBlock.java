package net.quedoom.francium.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.quedoom.francium.Francium;
import net.quedoom.francium.block.entity.SawedBlockEntity;
import net.quedoom.francium.init.ModProperties;
import net.quedoom.francium.util.EighthsEatableOctant;
import net.quedoom.francium.util.QTLogger;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class SawedBlock extends BaseEntityBlock {
    public static final BooleanProperty NORTH_EAST_DOWN = ModProperties.NORTH_EAST_DOWN;
    public static final BooleanProperty NORTH_WEST_DOWN = ModProperties.NORTH_WEST_DOWN;
    public static final BooleanProperty SOUTH_EAST_DOWN = ModProperties.SOUTH_EAST_DOWN;
    public static final BooleanProperty SOUTH_WEST_DOWN = ModProperties.SOUTH_WEST_DOWN;
    public static final BooleanProperty NORTH_EAST_UP = ModProperties.NORTH_EAST_UP;
    public static final BooleanProperty NORTH_WEST_UP = ModProperties.NORTH_WEST_UP;
    public static final BooleanProperty SOUTH_EAST_UP = ModProperties.SOUTH_EAST_UP;
    public static final BooleanProperty SOUTH_WEST_UP = ModProperties.SOUTH_WEST_UP;

    public static final VoxelShape FULL_BOX = box(0, 0, 0, 16, 16, 16);

    public SawedBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(NORTH_EAST_DOWN, true).setValue(NORTH_WEST_DOWN, true)
                .setValue(SOUTH_EAST_DOWN, true).setValue(SOUTH_WEST_DOWN, true)
                .setValue(NORTH_EAST_UP, true).setValue(NORTH_WEST_UP, true)
                .setValue(SOUTH_EAST_UP, true).setValue(SOUTH_WEST_UP, true));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(SawedBlock::new);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(
                NORTH_EAST_DOWN,
                NORTH_WEST_DOWN,
                SOUTH_EAST_DOWN,
                SOUTH_WEST_DOWN,
                NORTH_EAST_UP,
                NORTH_WEST_UP,
                SOUTH_EAST_UP,
                SOUTH_WEST_UP
        );
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState();
    }


    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return new SawedBlockEntity(worldPosition, blockState);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        VoxelShape ned = EighthsEatableOctant.ned.get(state) ? box(8, 0, 0, 16, 8, 8) : Shapes.empty();
        VoxelShape nwd = EighthsEatableOctant.nwd.get(state) ? box(0, 0, 0, 8, 8, 8) : Shapes.empty();
        VoxelShape sed = EighthsEatableOctant.sed.get(state) ? box(8, 0, 8, 16, 8, 16) : Shapes.empty();
        VoxelShape swd = EighthsEatableOctant.swd.get(state) ? box(0, 0, 8, 8, 8, 16) : Shapes.empty();
        VoxelShape neu = EighthsEatableOctant.neu.get(state) ? box(8, 8, 0, 16, 16, 8) : Shapes.empty();
        VoxelShape nwu = EighthsEatableOctant.nwu.get(state) ? box(0, 8, 0, 8, 16, 8) : Shapes.empty();
        VoxelShape seu = EighthsEatableOctant.seu.get(state) ? box(8, 8, 8, 16, 16, 16) : Shapes.empty();
        VoxelShape swu = EighthsEatableOctant.swu.get(state) ? box(0, 8, 8, 8, 16, 16) : Shapes.empty();

        return Shapes.or(neu, nwu, seu, swu, ned, nwd, sed, swd);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (level.isClientSide()) {
            if (chisel(level, pos, state, player, hitResult).consumesAction()) {
                return InteractionResult.SUCCESS;
            }

            if (player.getItemInHand(InteractionHand.MAIN_HAND).isEmpty()) {
                return InteractionResult.CONSUME;
            }
        }

        return this.chisel(level, pos, state, player, hitResult);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (itemStack.getItem() instanceof BlockItem item) {
            Francium.LOGGER.info("intanceof BlockItem");
            if (level.getBlockEntity(pos) instanceof SawedBlockEntity entity) {
                Francium.LOGGER.info("intanceof SawedBlockEntity");
                entity.setBlock(item.getBlock().defaultBlockState());
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.TRY_WITH_EMPTY_HAND;
    }

    protected void set(LevelAccessor level, BlockPos pos, BlockState state, EighthsEatableOctant octant, boolean value) {
        octant.set(((Level) level), state, pos, value);
    }

    protected InteractionResult chisel(final LevelAccessor level, final BlockPos pos, final BlockState state, final Player player, final BlockHitResult hitResult) {
        int bites = getBites(state);
        level.gameEvent(player, GameEvent.EAT, pos);
        if (bites < 7) {
            Vec3 hitPos = hitResult.getLocation();
            BlockPos blockPos = hitResult.getBlockPos();
            Direction side = hitResult.getDirection();

            EighthsEatableOctant octant = this.getOctant(hitPos, blockPos, side);

            set(level, pos, state, octant, !octant.get(state));

        } else {
            level.removeBlock(pos, false);
            level.gameEvent(player, GameEvent.BLOCK_DESTROY, pos);
        }
        return InteractionResult.SUCCESS;
    }

    private @NonNull EighthsEatableOctant getTrueOctant(Vec3 hitPos, BlockPos blockPos, Direction side) {
        double dx = hitPos.x - blockPos.getX() - (side.equals(Direction.EAST) ? 0.01F : -0.01);
        double dy = hitPos.y - blockPos.getY() - (side.equals(Direction.UP) ? 0.01F : -0.01);
        double dz = hitPos.z - blockPos.getZ() - (side.equals(Direction.NORTH) ? -0.01F : 0.01);

        char ns = dz < 0.5 ? 'n' : 's';
        char ew = dx < 0.5 ? 'w' : 'e';
        char ud = dy < 0.5 ? 'd' : 'u';

        return EighthsEatableOctant.ofChars(ns, ew, ud);
    }

    protected @NonNull EighthsEatableOctant getOctant(Vec3 hitPos, BlockPos blockPos, Direction side) {
        EighthsEatableOctant originalOctant = getTrueOctant(hitPos, blockPos, side);

        double dx = hitPos.x - blockPos.getX();
        double dy = hitPos.y - blockPos.getY();
        double dz = hitPos.z - blockPos.getZ();

        double crazyDX = dx;
        double crazyDY = dy;
        double crazyDZ = dz;

        double adjust = 0.2;

        if (originalOctant.up()) {
            if (originalOctant.north()) {
                if (originalOctant.east()) {
                    // north up east
                    switch (side) {
                        case NORTH -> crazyDZ -= adjust;
                        case UP -> crazyDY += adjust;
                        case EAST -> crazyDX += adjust;
                    }
                } else {
                    // north up west
                    switch (side) {
                        case NORTH -> crazyDZ -= adjust;
                        case UP -> crazyDY += adjust;
                        case WEST -> crazyDX -= adjust;
                    }
                }
            } else {
                if (originalOctant.east()) {
                    switch (side) {
                        // south up east
                        case UP -> crazyDY += adjust;
                        case EAST -> crazyDX += adjust;
                        case SOUTH -> crazyDZ += adjust;
                    }
                } else {
                    switch (side) {
                        // south up west
                        case UP -> crazyDY += adjust;
                        case SOUTH -> crazyDZ += adjust;
                        case WEST -> crazyDX -= adjust;
                    }
                }
            }
        } else {
            if (originalOctant.north()) {
                if (originalOctant.east()) {
                    // north down east
                    switch (side) {
                        case NORTH -> crazyDZ -= adjust;
                        case EAST -> crazyDX += adjust;
                        case DOWN -> crazyDY -= adjust;
                    }
                } else {
                    // north down west
                    switch (side) {
                        case NORTH -> crazyDZ -= adjust;
                        case EAST -> crazyDX += adjust;
                        case DOWN -> crazyDY -= adjust;
                    }
                }
            } else {
                if (originalOctant.east()) {
                    switch (side) {
                        // south down east
                        case EAST -> crazyDX += adjust;
                        case SOUTH -> crazyDZ += adjust;
                        case DOWN -> crazyDY -= adjust;
                    }
                } else {
                    switch (side) {
                        // south down west
                        case SOUTH -> crazyDZ += adjust;
                        case WEST -> crazyDX -= adjust;
                        case DOWN -> crazyDY -= adjust;
                    }
                }
            }
        }

        dx -= side.equals(Direction.EAST) ? 0.01F : -0.01;
        dy -= side.equals(Direction.UP) ? 0.01F : -0.01;
        dz -= side.equals(Direction.NORTH) ? -0.01F : 0.01;

        int newX = (int) Math.floor(blockPos.getX() + crazyDX);
        int newY = (int) Math.floor(blockPos.getY() + crazyDY);
        int newZ = (int) Math.floor(blockPos.getZ() + crazyDZ);

        Francium.LOGGER.info("Position [ X: {} Y: {} Z: {} ]", newX ,newY, newZ);

        if (new BlockPos(newX, newY, newZ).equals(blockPos)) {
            char ns = crazyDZ < 0.5 ? 'n' : 's';
            char ew = crazyDX < 0.5 ? 'w' : 'e';
            char ud = crazyDY < 0.5 ? 'd' : 'u';

            return QTLogger.logAndReturn(EighthsEatableOctant.ofChars(ns, ew, ud));
        } else {
            char ns = dz < 0.5 ? 'n' : 's';
            char ew = dx < 0.5 ? 'w' : 'e';
            char ud = dy < 0.5 ? 'd' : 'u';

            return QTLogger.logAndReturn(EighthsEatableOctant.ofChars(ns, ew, ud));
        }
    }

    protected static int getBites(BlockState state) {
        int bites = 0;
        if (!state.getValue(NORTH_EAST_DOWN)) {
            bites++;
        }
        if (!state.getValue(NORTH_WEST_DOWN)) {
            bites++;
        }
        if (!state.getValue(SOUTH_EAST_DOWN)) {
            bites++;
        }
        if (!state.getValue(SOUTH_WEST_DOWN)) {
            bites++;
        }
        if (!state.getValue(NORTH_EAST_UP)) {
            bites++;
        }
        if (!state.getValue(NORTH_WEST_UP)) {
            bites++;
        }
        if (!state.getValue(SOUTH_EAST_UP)) {
            bites++;
        }
        if (!state.getValue(SOUTH_WEST_UP)) {
            bites++;
        }
        return bites;
    }
}
