package net.quedoom.francium.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.quedoom.francium.block.entity.GlueMixerEntity;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.francium.init.ModProperties;
import net.quedoom.francium.init.ModTags;
import net.quedoom.francium.util.GlueMixerState;
import org.jspecify.annotations.Nullable;

public class GlueMixerBlock extends BaseEntityBlock {
    public static final EnumProperty<GlueMixerState> STATE = ModProperties.GLUE_MIXER_STATE;
    public static final IntegerProperty VEGETATION = ModProperties.GLUE_MIXER_VEGETATION;

    public GlueMixerBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(STATE, GlueMixerState.EMPTY).setValue(VEGETATION, 0));
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState();
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(GlueMixerBlock::new);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return new GlueMixerEntity(worldPosition, blockState);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(STATE, VEGETATION);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState onState, Entity entity) {
        if (entity instanceof ItemEntity itemEntity) {
            if (itemEntity.getItem().is(Items.SLIME_BLOCK) && onState.getValue(STATE) == GlueMixerState.EMPTY) {
                level.setBlockAndUpdate(pos, onState.setValue(STATE, GlueMixerState.SLIME));
                itemEntity.getItem().shrink(1);
            } else if (itemEntity.getItem().is(Items.HONEY_BLOCK) && onState.getValue(STATE) ==
                    GlueMixerState.SLIME && onState.getValue(VEGETATION) == 0) {
                level.setBlockAndUpdate(pos, onState.setValue(STATE, GlueMixerState.HONEY));
                itemEntity.getItem().shrink(1);
            } else if (itemEntity.getItem().is(ModTags.Items.SMALL_VEGETATION) && onState.getValue(STATE) ==
                    GlueMixerState.SLIME && onState.getValue(VEGETATION) < 3) {
                level.setBlockAndUpdate(pos, onState.setValue(VEGETATION, onState.getValue(VEGETATION) + 1));
                itemEntity.getItem().shrink(1);
            } else if (itemEntity.getItem().is(ModTags.Items.BIG_VEGETATION) && onState.getValue(STATE) ==
                    GlueMixerState.SLIME && onState.getValue(VEGETATION) == 0) {
                level.setBlockAndUpdate(pos, onState.setValue(VEGETATION, 3));
                itemEntity.getItem().shrink(1);
            }
        }
    }

    @Override
    public void destroy(LevelAccessor level, BlockPos pos, BlockState state) {
        if (state.getValue(VEGETATION) >= 0) {
            level.setBlock(pos, state.setValue(VEGETATION, 0).setValue(STATE, GlueMixerState.SLIME), 3);
        }
        if (state.getValue(STATE) == GlueMixerState.SLIME) {
            level.setBlock(pos, ModBlocks.GLUE_MIXER.defaultBlockState(), 3);
        } else if (state.getValue(STATE) == GlueMixerState.HONEY) {
            level.setBlock(pos, state.setValue(STATE, GlueMixerState.SLIME), 3);
        }
    }

//    @Override
//    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
//        switch (state.getValue(STATE)) {
//            case EMPTY -> level.setBlockAndUpdate(pos, state.setValue(STATE, GlueMixerState.SLIME));
//            case SLIME -> level.setBlockAndUpdate(pos, state.setValue(STATE, GlueMixerState.HONEY));
//            case HONEY -> level.setBlockAndUpdate(pos, state.setValue(STATE, GlueMixerState.EMPTY));
//        }
//
//        return InteractionResult.SUCCESS;
//    }
}
