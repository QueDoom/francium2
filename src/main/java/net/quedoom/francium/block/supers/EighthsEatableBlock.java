package net.quedoom.francium.block.supers;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
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
import net.quedoom.francium.init.ModProperties;
import net.quedoom.francium.init.ModStats;
import net.quedoom.francium.util.EighthsEatableOctant;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class EighthsEatableBlock extends Block {
    public static final BooleanProperty NORTH_EAST_DOWN = ModProperties.NORTH_EAST_DOWN;
    public static final BooleanProperty NORTH_WEST_DOWN = ModProperties.NORTH_WEST_DOWN;
    public static final BooleanProperty SOUTH_EAST_DOWN = ModProperties.SOUTH_EAST_DOWN;
    public static final BooleanProperty SOUTH_WEST_DOWN = ModProperties.SOUTH_WEST_DOWN;
    public static final BooleanProperty NORTH_EAST_UP = ModProperties.NORTH_EAST_UP;
    public static final BooleanProperty NORTH_WEST_UP = ModProperties.NORTH_WEST_UP;
    public static final BooleanProperty SOUTH_EAST_UP = ModProperties.SOUTH_EAST_UP;
    public static final BooleanProperty SOUTH_WEST_UP = ModProperties.SOUTH_WEST_UP;

    protected final int nutrition;
    protected final float saturation;

    public EighthsEatableBlock(int nutrition, float saturation, Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(NORTH_EAST_DOWN, true).setValue(NORTH_WEST_DOWN, true).setValue(SOUTH_EAST_DOWN, true).setValue(SOUTH_EAST_DOWN, true)
                                                                 .setValue(NORTH_EAST_UP, true).setValue(NORTH_WEST_UP, true).setValue(SOUTH_EAST_UP, true).setValue(SOUTH_WEST_UP, true));
        this.nutrition = nutrition;
        this.saturation = saturation;
    }

    public EighthsEatableBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(NORTH_EAST_DOWN, true).setValue(NORTH_WEST_DOWN, true).setValue(SOUTH_EAST_DOWN, true).setValue(SOUTH_EAST_DOWN, true)
                .setValue(NORTH_EAST_UP, true).setValue(NORTH_WEST_UP, true).setValue(SOUTH_EAST_UP, true).setValue(SOUTH_WEST_UP, true));
        this.nutrition = 1;
        this.saturation = 0.1F;
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState();
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
            if (eat(level, pos, state, player, hitResult).consumesAction()) {
                return InteractionResult.SUCCESS;
            }

            if (player.getItemInHand(InteractionHand.MAIN_HAND).isEmpty()) {
                return InteractionResult.CONSUME;
            }
        }

        return this.eat(level, pos, state, player, hitResult);
    }

    protected void set(LevelAccessor level, BlockPos pos, BlockState state, EighthsEatableOctant octant) {
        octant.set(((Level) level), state, pos, false);
    }

    protected InteractionResult eat(final LevelAccessor level, final BlockPos pos, final BlockState state, final Player player, final BlockHitResult hitResult) {
        if (!player.canEat(false)) {
            return InteractionResult.PASS;
        }

        player.awardStat(ModStats.EAT_EIGHTHS_BLOCK);
        playerEat(player);
        int bites = getBites(state);
        level.gameEvent(player, GameEvent.EAT, pos);
        if (bites < 7) {
            Vec3 hitPos = hitResult.getLocation();
            BlockPos blockPos = hitResult.getBlockPos();
            Direction side = hitResult.getDirection();

            EighthsEatableOctant octant = this.getOctant(hitPos, blockPos, side, (state.getBlock() instanceof RotateableEighthsEatableBlock ? state.getValue(RotateableEighthsEatableBlock.FACING) : null));

            set(level, pos, state, octant);
            Francium.LOGGER.info(octant.toString());

        } else {
            level.removeBlock(pos, false);
            level.gameEvent(player, GameEvent.BLOCK_DESTROY, pos);
        }

        return InteractionResult.SUCCESS;
    }

    protected void playerEat(Player player) {
        player.getFoodData().eat(nutrition, saturation);
    }

    protected @NonNull EighthsEatableOctant getOctant(Vec3 hitPos, BlockPos blockPos, Direction side) {
        return getOctant(hitPos, blockPos, side, null);
    }
    protected @NonNull EighthsEatableOctant getOctant(Vec3 hitPos, BlockPos blockPos, Direction side, @Nullable Direction facing) {
        double dx = hitPos.x - blockPos.getX() - (side.equals(Direction.EAST) ? 0.01F : -0.01);
        double dy = hitPos.y - blockPos.getY() - (side.equals(Direction.UP) ? 0.01F : -0.01);
        double dz = hitPos.z - blockPos.getZ() - (side.equals(Direction.NORTH) ? -0.01F : 0.01);

        char ns = dz < 0.5 ? 'n' : 's';
        char ew = dx < 0.5 ? 'w' : 'e';
        char ud = dy < 0.5 ? 'd' : 'u';

        return EighthsEatableOctant.ofChars(ns, ew, ud);
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
        Francium.LOGGER.info("{}", bites);
        return bites;
    }


}
