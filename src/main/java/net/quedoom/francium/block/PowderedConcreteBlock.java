package net.quedoom.francium.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.francium.util.ColorHelper;

public class PowderedConcreteBlock extends AbstractColoredBlock implements Fallable {
    private final Block concrete;

    public PowderedConcreteBlock(DyeColor color, Properties properties) {
        super(color, properties);
        this.concrete = ColorHelper.toConcrete(color);
    }

    @Override
    protected void drop(Level level, BlockPos pos, BlockState state, Player player, ItemStack destroyedWith) {
        dropResources(Blocks.WHITE_CONCRETE_POWDER.defaultBlockState(), level, pos, null, player, destroyedWith);
        Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), ColorHelper.toItem(this.dyeColor).getDefaultInstance());
    }

    @Override
    protected void onPlace(final BlockState state, final Level level, final BlockPos pos, final BlockState oldState, final boolean movedByPiston) {
        level.scheduleTick(pos, this, this.getDelayAfterPlace());
    }

    @Override
    protected void tick(final BlockState state, final ServerLevel level, final BlockPos pos, final RandomSource random) {
        if (isFree(level.getBlockState(pos.below())) && pos.getY() >= level.getMinY()) {
            FallingBlockEntity entity = FallingBlockEntity.fall(level, pos, state);
            this.falling(entity);
        }
    }

    protected void falling(final FallingBlockEntity entity) {
    }

    protected int getDelayAfterPlace() {
        return 2;
    }

    public static boolean isFree(final BlockState state) {
        return state.isAir() || state.is(BlockTags.FIRE) || state.is(ModBlocks.STOKED_FIRE) || state.liquid() || state.canBeReplaced();
    }

    @Override
    public void animateTick(final BlockState state, final Level level, final BlockPos pos, final RandomSource random) {
        if (random.nextInt(16) == 0) {
            BlockPos below = pos.below();
            if (isFree(level.getBlockState(below))) {
                ParticleUtils.spawnParticleBelow(level, pos, random, new BlockParticleOption(ParticleTypes.FALLING_DUST, state));
            }
        }
    }

    @Override
    public void onLand(final Level level, final BlockPos pos, final BlockState state, final BlockState replacedBlock, final FallingBlockEntity entity) {
        if (shouldSolidify(level, pos, replacedBlock)) {
            level.setBlock(pos, this.concrete.defaultBlockState(), 3);
        }
    }

    @Override
    public BlockState getStateForPlacement(final BlockPlaceContext context) {
        BlockGetter level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState replacedBlock = level.getBlockState(pos);
        return shouldSolidify(level, pos, replacedBlock) ? this.concrete.defaultBlockState() : super.getStateForPlacement(context);
    }

    public static boolean shouldSolidify(final BlockGetter level, final BlockPos pos, final BlockState replacedBlock) {
        return canSolidify(replacedBlock) || touchesLiquid(level, pos);
    }

    public static boolean touchesLiquid(final BlockGetter level, final BlockPos pos) {
        boolean touchesLiquid = false;
        BlockPos.MutableBlockPos testPos = pos.mutable();

        for (Direction direction : Direction.values()) {
            BlockState blockState = level.getBlockState(testPos);
            if (direction != Direction.DOWN || canSolidify(blockState)) {
                testPos.setWithOffset(pos, direction);
                blockState = level.getBlockState(testPos);
                if (canSolidify(blockState) && !blockState.isFaceSturdy(level, pos, direction.getOpposite())) {
                    touchesLiquid = true;
                    break;
                }
            }
        }

        return touchesLiquid;
    }

    private static boolean canSolidify(final BlockState state) {
        return state.getFluidState().is(FluidTags.WATER);
    }

    @Override
    protected BlockState updateShape(
            final BlockState state,
            final LevelReader level,
            final ScheduledTickAccess ticks,
            final BlockPos pos,
            final Direction directionToNeighbour,
            final BlockPos neighbourPos,
            final BlockState neighbourState,
            final RandomSource random
    ) {
        return touchesLiquid(level, pos)
                ? this.concrete.defaultBlockState()
                : super.updateShape(state, level, ticks, pos, directionToNeighbour, neighbourPos, neighbourState, random);
    }

    public int getDustColor(final BlockState blockState, final BlockGetter level, final BlockPos pos) {
        return blockState.getMapColor(level, pos).col;
    }
}
