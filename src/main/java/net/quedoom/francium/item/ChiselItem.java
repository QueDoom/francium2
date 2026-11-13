package net.quedoom.francium.item;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.quedoom.francium.init.ModBlocks;

public class ChiselItem extends Item {
    private final boolean chiselsEverything;

    public ChiselItem(boolean chiselsEverything, Properties properties) {
        super(properties);
        this.chiselsEverything = chiselsEverything;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (!(context.getLevel() instanceof ServerLevel level)
                || !(context.getPlayer() instanceof ServerPlayer player)) {
            return InteractionResult.SUCCESS;
        }

        BlockPos pos = context.getClickedPos();
        ItemStack tool = context.getItemInHand();

        if (!canBreak(player, level, pos)) {
            level.playSound(null, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 0.5f, 0.25f);
            return InteractionResult.FAIL;
        }

        BlockState state = level.getBlockState(pos);
        ItemStack drop = getDropStack(level, pos, state);

        level.levelEvent(LevelEvent.PARTICLES_DESTROY_BLOCK, pos, Block.getId(state));
        level.removeBlock(pos, false);

        if (!drop.isEmpty()) {
            Vec3 center = pos.getCenter();
            Containers.dropItemStack(level, center.x, center.y, center.z, drop);
        }

        tool.hurtAndBreak(1, player, context.getHand().asEquipmentSlot());
        return InteractionResult.SUCCESS_SERVER;
    }

    private static ItemStack getDropStack(ServerLevel level, BlockPos pos, BlockState state) {
        Item item = state.getBlock().asItem();
        return item == Items.AIR
                ? state.getCloneItemStack(level, pos, true)
                : item.getDefaultInstance();
    }

    public boolean canBreak(ServerPlayer player, ServerLevel level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);

        if (state.is(ModBlocks.BEDROCK_CHUNK)) return true;

        if (!this.chiselsEverything) {
            if (state.getDestroySpeed(level, pos) < 0) return false;
            if (!player.getMainHandItem().canDestroyBlock(state, level, pos, player)) return false;
        }

        if (state.isAir()) return false;
        if (!player.mayInteract(level, pos)) return false;
        if (player.blockActionRestricted(level, pos, player.gameMode.getGameModeForPlayer())) return false;
        return true;
    }
}
