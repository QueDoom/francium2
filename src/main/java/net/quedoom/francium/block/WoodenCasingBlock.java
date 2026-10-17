package net.quedoom.francium.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.quedoom.francium.init.ModAdvancementTriggers;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.francium.init.ModItems;
import org.jspecify.annotations.NonNull;

public class WoodenCasingBlock extends CasingWithPotentialContainer {
    public static final IntegerProperty STONE_CASING_PROGRESS = IntegerProperty.create("stone_casing_progress", 0, 2);
    public static final IntegerProperty OBSIDIAN_CASING_PROGRESS = IntegerProperty.create("obsidian_casing_progress", 0, 3);

    public WoodenCasingBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(STONE_CASING_PROGRESS, 0).setValue(OBSIDIAN_CASING_PROGRESS, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(STONE_CASING_PROGRESS, OBSIDIAN_CASING_PROGRESS);
    }

    @Override
    protected @NonNull Block containingBlock() {
        return ModBlocks.WOODEN_CASING_CONTAINING_BLOCK;
    }

    @Override
    protected @NonNull Block containingItems() {
        return ModBlocks.WOODEN_CASING_CONTAINING_ITEMS;
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        int stone = state.getValue(STONE_CASING_PROGRESS);
        int obsidian = state.getValue(OBSIDIAN_CASING_PROGRESS);

        if (itemStack.is(ModItems.ANDESITE_COATED_ROCK)) {
            if (stone == 0) {
                if (updateCasing(level, pos, state.setValue(STONE_CASING_PROGRESS, 1).setValue(OBSIDIAN_CASING_PROGRESS, 1), itemStack, player)) {
                    return InteractionResult.SUCCESS;
                }
            }
        }
        if (itemStack.is(ModItems.DIORITE_COATED_ROCK)) {
            if (stone == 1 && obsidian == 1) {
                if (updateCasing(level, pos, state.setValue(STONE_CASING_PROGRESS, 2), itemStack, player)) {
                    return InteractionResult.SUCCESS;
                }
            }
        }
        if (itemStack.is(ModItems.GRANITE_COATED_ROCK)) {
            if (stone == 2 && obsidian == 1) {
                if (updateCasing(level, pos, ModBlocks.STONE_CASING.defaultBlockState(), itemStack, player)) {
                    return InteractionResult.SUCCESS;
                }
            }
        }
        if (itemStack.is(ModItems.PACKED_DEEPSLATE)) {
            if (obsidian == 1 && stone == 1) {
                if (updateCasing(level, pos, state.setValue(OBSIDIAN_CASING_PROGRESS, 2), itemStack, player)) {
                    return InteractionResult.SUCCESS;
                }
            }
        }
        if (itemStack.is(ModItems.OBSIDIAN_BOOK)) {
            if (obsidian == 2 && stone == 1) {
                if (updateCasing(level, pos, state.setValue(OBSIDIAN_CASING_PROGRESS, 3), itemStack, player)) {
                    return InteractionResult.SUCCESS;
                }
            }
            if (obsidian == 3 && stone == 1) {
                if (updateCasing(level, pos, ModBlocks.OBSIDIAN_CASING.defaultBlockState(), itemStack, player)) {
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return InteractionResult.PASS;
    }

    private boolean updateCasing(Level level, BlockPos pos, BlockState state, ItemStack stack, Player player) {
        if (level instanceof ServerLevel server) {
            level.setBlockAndUpdate(pos, state);
            server.levelEvent(LevelEvent.PARTICLES_DESTROY_BLOCK, pos, Block.getId(state));
            if (!player.hasInfiniteMaterials()) stack.shrink(1);
            if (!state.is(this) && player instanceof ServerPlayer serverPlayer) {
                ModAdvancementTriggers.CRAFT_CASING_EVENT.trigger(serverPlayer, state);
            }
            return true;
        }
        return false;
    }
}
