package net.quedoom.francium.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.quedoom.francium.init.ModTags;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Debug(export = true)
@Mixin(LivingEntity.class)
public abstract class SlimeBallDropsFromDEATHMixin extends Entity {

    public SlimeBallDropsFromDEATHMixin(EntityType<?> type, Level level) {
        super(type, level);
    }

    @Inject(
            method = "die",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;dropAllDeathLoot(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;)V")
    )

    private void dropSlime(DamageSource source, CallbackInfo ci) {
        if (!this.is(ModTags.Entities.DOES_NOT_DROP_SLIME)) {
            Containers.dropItemStack(level(), getX(), getY(), getZ(), Items.SLIME_BALL.getDefaultInstance());
        }
    }
}
