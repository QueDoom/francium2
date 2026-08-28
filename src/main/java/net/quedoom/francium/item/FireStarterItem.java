package net.quedoom.francium.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.quedoom.francium.Francium;
import net.quedoom.francium.init.ModItems;
import net.quedoom.francium.init.ModTags;
import net.quedoom.francium.item.vanilla.StickItem;

import java.util.Random;

public class FireStarterItem extends Item {
    private static final int USE_DURATION = 100;

    public FireStarterItem(Properties properties) {
        super(properties);
    }

    public static float getAnimationTick(ItemStack stack) {
        int damage = stack.getDamageValue();
        float anim = Math.floorMod(damage, 4) + 1;
        Francium.LOGGER.info("Fire Starter Anim: {}", anim);
        return anim;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);
        if (state.is(Blocks.FIRE)) return InteractionResult.FAIL;
        RandomSource randon = level.getRandom();
        boolean randBool0 = randon.nextBoolean();
        boolean randBool1 = randon.nextBoolean();
        boolean randBool2 = randon.nextBoolean();
        boolean randBool3 = randon.nextBoolean();
        if (!(randBool0 && randBool1 && randBool2 && randBool3)) {
            if (player != null) {
                context.getItemInHand().hurtAndBreak(1, player, context.getHand().asEquipmentSlot());
            }
            return InteractionResult.PASS;
        }
        if (!CampfireBlock.canLight(state) && !CandleBlock.canLight(state) && !CandleCakeBlock.canLight(state)) {
            BlockPos relativePos = pos.relative(context.getClickedFace());
            if (BaseFireBlock.canBePlacedAt(level, relativePos, context.getHorizontalDirection())) {
                level.playSound(player, relativePos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
                BlockState fireState = BaseFireBlock.getState(level, relativePos);
                level.setBlock(relativePos, fireState, 11);
                level.gameEvent(player, GameEvent.BLOCK_PLACE, pos);
                ItemStack itemStack = context.getItemInHand();
                if (player instanceof ServerPlayer) {
                    CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer)player, relativePos, itemStack);
                    itemStack.hurtAndBreak(1, player, context.getHand().asEquipmentSlot());
                }

                return InteractionResult.SUCCESS;
            } else {
                return InteractionResult.FAIL;
            }
        } else {
            level.playSound(player, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
            level.setBlock(pos, (BlockState)state.setValue(BlockStateProperties.LIT, true), 11);
            level.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
            if (player != null) {
                context.getItemInHand().hurtAndBreak(1, player, context.getHand().asEquipmentSlot());
            }

            return InteractionResult.SUCCESS;
        }
    }
}
