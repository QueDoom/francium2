package net.quedoom.francium.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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
import net.quedoom.francium.init.ModItems;
import net.quedoom.francium.init.ModProperties;
import net.quedoom.francium.init.ModTags;
import net.quedoom.francium.util.GlueMixerState;
import org.jspecify.annotations.Nullable;

public class GlueMixerBlock extends BaseEntityBlock {
    public static final EnumProperty<GlueMixerState> STATE = ModProperties.GLUE_MIXER_STATE;
    public static final IntegerProperty VEGETATION = ModProperties.GLUE_MIXER_VEGETATION;
    public static final IntegerProperty ECHO = ModProperties.GLUE_MIXER_ECHO;

    public GlueMixerBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(STATE, GlueMixerState.EMPTY).setValue(VEGETATION, 0).setValue(ECHO, 0));
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
        builder.add(STATE, VEGETATION, ECHO);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState onState, Entity entity) {
        GlueMixerState glueState = onState.getValue(STATE);
        int veg = onState.getValue(VEGETATION);
        int echo = onState.getValue(ECHO);
        if (entity instanceof ItemEntity itemEntity) {
            if (itemEntity.getItem().is(Items.SLIME_BLOCK) && glueState == GlueMixerState.EMPTY) {
                level.setBlockAndUpdate(pos, onState.setValue(STATE, GlueMixerState.SLIME));
                itemEntity.getItem().shrink(1);
            } else if (itemEntity.getItem().is(Items.HONEY_BLOCK) && glueState == GlueMixerState.SLIME &&
                    veg == 0) {
                level.setBlockAndUpdate(pos, onState.setValue(STATE, GlueMixerState.HONEY));
                itemEntity.getItem().shrink(1);
            } else if (itemEntity.getItem().is(ModTags.Items.SMALL_VEGETATION) && glueState == GlueMixerState.SLIME &&
                    veg < 4 && echo == 0) {
                level.setBlockAndUpdate(pos, onState.setValue(VEGETATION, onState.getValue(VEGETATION) + 1));
                itemEntity.getItem().shrink(1);
            } else if (itemEntity.getItem().is(ModTags.Items.BIG_VEGETATION) && glueState == GlueMixerState.SLIME &&
                    veg == 0 && echo == 0) {
                level.setBlockAndUpdate(pos, onState.setValue(VEGETATION, 4));
                itemEntity.getItem().shrink(1);
            } else if (itemEntity.getItem().is(ModTags.Items.SMALL_ECHO) && glueState == GlueMixerState.SLIME &&
                    echo < 4 && veg == 0) {
                level.setBlockAndUpdate(pos, onState.setValue(ECHO, onState.getValue(ECHO) + 1));
                itemEntity.getItem().shrink(1);
            } else if (itemEntity.getItem().is(ModTags.Items.BIG_ECHO) && glueState == GlueMixerState.SLIME &&
                    echo == 0 && veg == 0) {
                level.setBlockAndUpdate(pos, onState.setValue(ECHO, 4));
                itemEntity.getItem().shrink(1);
            }
        }
    }

    @Override
    public void destroy(LevelAccessor level, BlockPos pos, BlockState state) {
        if (state.getValue(ECHO) > 0) {
            destroyAndDropEcho((Level) level, pos, state, state.getValue(ECHO));
        } else if (state.getValue(VEGETATION) > 0) {
            destroyAndDropVegetation(((Level) level), pos, state, state.getValue(VEGETATION));
        } else if (state.getValue(STATE) == GlueMixerState.SLIME) {
            destroyAndDropSlime(((Level) level), pos, state);
        } else if (state.getValue(STATE) == GlueMixerState.HONEY) {
            destroyAndDropHoney(((Level) level), pos, state);
        }
    }

    private void destroyAndDrop(Level level, BlockPos pos, BlockState state, ItemStack stack) {
        Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), stack);
        level.setBlock(pos, state, 3);
    }

    private void destroyAndDropSlime(Level level, BlockPos pos, BlockState state) {
        destroyAndDrop(level, pos, state.setValue(STATE, GlueMixerState.EMPTY), Items.SLIME_BLOCK.getDefaultInstance());
    }
    private void destroyAndDropHoney(Level level, BlockPos pos, BlockState state) {
        destroyAndDrop(level, pos, state.setValue(STATE, GlueMixerState.SLIME), Items.HONEY_BLOCK.getDefaultInstance());
    }
    private void destroyAndDropVegetation(Level level, BlockPos pos, BlockState state, int veg) {
        ItemStack leafStack = veg == 4 ? Items.OAK_LEAVES.getDefaultInstance() : ModItems.LEAF.getDefaultInstance();
        if (veg != 4) leafStack.setCount(veg);
        destroyAndDrop(level, pos, state.setValue(STATE, GlueMixerState.SLIME).setValue(VEGETATION, 0), leafStack);
    }
    private void destroyAndDropEcho(Level level, BlockPos pos, BlockState state, int echo) {
        ItemStack leafStack = echo == 4 ? ModBlocks.ECHO_BLOCK.asItem().getDefaultInstance() : Items.ECHO_SHARD.getDefaultInstance();
        if (echo != 4) leafStack.setCount(echo);
        destroyAndDrop(level, pos, state.setValue(STATE, GlueMixerState.SLIME).setValue(ECHO, 0), leafStack);
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
