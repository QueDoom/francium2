package net.quedoom.francium.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
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
import net.quedoom.francium.Francium;
import net.quedoom.francium.api.prickle.FranciumConfigManager;
import net.quedoom.francium.block.entity.BellowBlockEntity;
import net.quedoom.francium.block.entity.StokedFireEntity;
import net.quedoom.francium.init.ModBlockEntities;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.francium.recipe.BlowingRecipe;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;

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
         if (!(entity instanceof Player)) return;
         int pressure = onState.getValue(PRESSURE);
         if (pressure != 0) {
             level.setBlockAndUpdate(pos, onState.setValue(PRESSURE, pressure - 1));
         }
         if (pressure < 3 && pressure != 0) {
             double relX = 0, relZ = 0;
             double relY = pressure == 0 ? 0.25D : 0.28125D;

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

             if (pressure <= 1) {
                 if (!(level instanceof ServerLevel server)) return;
                 BlockState targetState = level.getBlockState(targetPos);

                 if (targetState.is(Blocks.FIRE)) {
                     boolean north = targetState.getValue(FireBlock.NORTH);
                     boolean east = targetState.getValue(FireBlock.EAST);
                     boolean south = targetState.getValue(FireBlock.SOUTH);
                     boolean west = targetState.getValue(FireBlock.WEST);
                     boolean up = targetState.getValue(FireBlock.UP);

                     if (!north && !east && !south && !west && !up) {
                         if (level.getRandom().nextFloat() <= FranciumConfigManager.CONFIG.stoke_fire_chance)
                             level.setBlockAndUpdate(targetPos, ModBlocks.STOKED_FIRE.defaultBlockState());

                     }
                 }

                 if (targetState.is(ModBlocks.STOKED_FIRE)) {
                     ((StokedFireEntity) level.getBlockEntity(targetPos)).blow();
                 }

                 BlowingRecipe.find(server, targetState.getBlock()).ifPresent(blowingRecipeRecipeHolder ->
                         level.setBlockAndUpdate(targetPos, blowingRecipeRecipeHolder.value().result()));
             }
         }

    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return state.getValue(PRESSURE) < 2;
    }

    public void raise(Level level, BlockPos pos, BlockState state) {
        if (!level.getEntitiesOfClass(Player.class, AABB.encapsulatingFullBlocks(pos, pos.above()).setMaxY(pos.above().getY())).isEmpty()) return;
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
        level.addParticle(new DustParticleOptions(15921649,  ((float) Math.max(Math.random(), 0.75))),
                pos.getX() + relativeX, pos.getY() + relativeY, pos.getZ() + relativeZ,
                xd, yd, zd
        );
    }

    private double insignificant() {
        return Math.clamp(Math.random(), 0.25D, 0.75D) - 0.5D;
    }
    private double significant() {
        return Math.max(Math.random() * 16, 1.25D);
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
