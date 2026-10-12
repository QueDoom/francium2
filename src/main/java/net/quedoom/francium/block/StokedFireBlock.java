package net.quedoom.francium.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.Nullable;

public class StokedFireBlock extends BaseFireBlock {
    public static final BooleanProperty SHORT = BooleanProperty.create("short");

    public StokedFireBlock(Properties properties) {
        super(properties, 3.5f);
        this.registerDefaultState(this.getStateDefinition().any().setValue(SHORT, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(SHORT);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (random.nextBoolean()) {
            level.setBlock(pos, Blocks.FIRE.defaultBlockState(), Block.UPDATE_CLIENTS);
        }
    }

    @Override
    protected boolean canBeReplaced(BlockState state, Fluid fluid) {
        return true;
    }

    @Override
    protected MapCodec<? extends BaseFireBlock> codec() {
        return simpleCodec(StokedFireBlock::new);
    }

    @Override
    protected boolean canBurn(BlockState state) {
        return ((FireBlock) Blocks.FIRE).canBurn(state);
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, @Nullable Orientation orientation, boolean movedByPiston) {
         level.setBlock(pos, state.setValue(SHORT, !level.getBlockState(pos.above()).is(BlockTags.AIR)), UPDATE_CLIENTS);
    }
}