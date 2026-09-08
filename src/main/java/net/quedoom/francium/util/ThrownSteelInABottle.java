package net.quedoom.francium.util;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.quedoom.francium.init.ModEntityTypes;
import net.quedoom.francium.init.ModItems;

public class ThrownSteelInABottle extends ThrowableItemProjectile {
    public ThrownSteelInABottle(final EntityType<? extends ThrownSteelInABottle> type, final Level level) {
        super(type, level);
    }

    public ThrownSteelInABottle(final Level level, final LivingEntity mob, final ItemStack itemStack) {
        super(ModEntityTypes.STEEL_IN_A_BOTTLE, mob, level, itemStack);
    }

    public ThrownSteelInABottle(final Level level, final double x, final double y, final double z, final ItemStack itemStack) {
        super(ModEntityTypes.STEEL_IN_A_BOTTLE, x, y, z, level, itemStack);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.STEEL_IN_A_BOTTLE;
    }

    @Override
    protected double getDefaultGravity() {
        return 0.07;
    }

    @Override
    protected void onHit(final HitResult hitResult) {
        super.onHit(hitResult);

        this.level().playSound(null ,hitResult.getLocation().x, hitResult.getLocation().y, hitResult.getLocation().z, SoundEvents.GLASS_BREAK, SoundSource.NEUTRAL);
        Containers.dropItemStack(this.level(), this.position().x, this.position().y, this.position().z, ModItems.STEEL_DUST.getDefaultInstance());

        this.discard();

    }

    public static void dropItemStackExact(final Level level, final double x, final double y, final double z, final ItemStack itemStack) {
        double size = EntityType.ITEM.getWidth();
        double centerRange = 1.0 - size;
        double halfSize = size / 2.0;
        RandomSource random = level.getRandom();
        double xo = x + random.nextDouble() * centerRange + halfSize;
        double yo = y;
        double zo = z + random.nextDouble() * centerRange + halfSize;

        while (!itemStack.isEmpty()) {
            ItemEntity entity = new ItemEntity(level, xo, yo, zo, itemStack.split(random.nextInt(21) + 10));
            float pow = 0.05F;
            entity.setDeltaMovement(
                    random.triangle(0.0, 0.11485000171139836),
                    random.triangle(0.2, 0.11485000171139836),
                    random.triangle(0.0, 0.11485000171139836));
            level.addFreshEntity(entity);
        }
    }
}
