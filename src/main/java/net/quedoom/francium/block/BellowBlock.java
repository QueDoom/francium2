package net.quedoom.francium.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.client.particle.DustParticle;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.quedoom.francium.block.entity.BellowBlockEntity;
import net.quedoom.francium.init.ModBlockEntities;
import org.jspecify.annotations.Nullable;

public class BellowBlock extends BaseEntityBlock {
    public static final IntegerProperty PRESSURE = IntegerProperty.create("pressure", 0, 7);
    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;

    public BellowBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH).setValue(PRESSURE, 7));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(BellowBlock::new);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(PRESSURE, FACING);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction direction = context.getHorizontalDirection();
        return this.defaultBlockState().setValue(FACING, context.getPlayer().isCrouching() ? direction : direction.getOpposite());
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        int pressure = state.getValue(PRESSURE);
        VoxelShape top = box(0, 6.5 + pressure, 0, 16, 9 + pressure, 16);
        VoxelShape middle = box(0.5, 2.5, 0.5, 15.5, 6.5 + pressure, 15.5);
        VoxelShape bottom = box(0, 0, 0, 16, 2.5, 16);

        return Shapes.or(top, middle, bottom);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState onState, Entity entity) {
         if (!(entity instanceof Player player)) return;
         int pressure = onState.getValue(PRESSURE);
         if (pressure == 0) return;
         level.setBlockAndUpdate(pos, onState.setValue(PRESSURE, pressure - 1));
         level.scheduleTick(pos, this.asBlock(), 20);
         if (pressure < 3) {
             double relX = 0, relZ = 0;
             double relY = 0.28125;

             Direction facing = onState.getValue(FACING);
             BlockPos targetPos = pos.relative(facing);

             switch (facing) {
                 case NORTH -> relX = 0.5;
                 case EAST -> {
                     relX = 1;
                     relZ = 0.5;
                 }
                 case SOUTH -> {
                     relX = 0.5;
                     relZ = 1;
                 }
                 case WEST -> relZ = 0.5;
             }

             blowParticles(level, pos, relX, relY, relZ, facing);
         }
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return state.getValue(PRESSURE) < 2;
    }

    public void raise(Level level, BlockPos pos, BlockState state) {
        if (!level.getEntitiesOfClass(Player.class, AABB.encapsulatingFullBlocks(pos, pos.above())).isEmpty()) return;
        int pressure = state.getValue(PRESSURE);
        if (pressure < 7) {
            level.setBlockAndUpdate(pos, state.setValue(PRESSURE, pressure + 1));
        }
    }

    private void blowParticles(Level level, BlockPos pos, double relativeX, double relativeY, double relativeZ, Direction direction) {
        double xd;
        double yd = insignificant();
        double zd;
        switch (direction) {
            case NORTH -> {
                xd = -insignificant();
                zd = -significant();
            }
            case EAST -> {
                xd = significant();
                zd = insignificant();
            }
            case SOUTH -> {
                xd = insignificant();
                zd = significant();
            }
            case WEST -> {
                xd = -significant();
                zd = -insignificant();
            }
            default -> {
                xd = 0;
                zd = 0;
            }
        }
        level.addParticle(new DustParticleOptions(15788758,  ((float) Math.max(Math.random(), 0.75))),
                pos.getX() + relativeX, pos.getY() + relativeY, pos.getZ() + relativeZ,
                xd, yd, zd
        );
    }

    private double insignificant() {
        return Math.clamp(Math.random(), 0.25D, 0.75D) - 0.5D;
    }
    private double significant() {
        return Math.clamp(Math.random() * 3, 1.25D, 2.75);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return new BellowBlockEntity(worldPosition, blockState);
    }

    protected static <T extends BlockEntity> @Nullable BlockEntityTicker<T> createTicker(final Level level, final BlockEntityType<T> actualType, final BlockEntityType<? extends BellowBlockEntity> expectedType) {
        BlockEntityTicker var10000;
        if (level instanceof ServerLevel serverLevel) {
            var10000 = createTickerHelper(actualType, expectedType, (innerLevel, pos, state, entity) -> BellowBlockEntity.tick(serverLevel, state, pos, entity));
        } else {
            var10000 = null;
        }

        return var10000;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> type) {
        return createTicker(level, type, ModBlockEntities.BELLOW_ENTITY);
    }
}
