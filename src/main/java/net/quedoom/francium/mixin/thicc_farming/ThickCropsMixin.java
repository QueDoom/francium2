package net.quedoom.francium.mixin.thicc_farming;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Debug(export = true)
@Mixin(CropBlock.class)
public abstract class ThickCropsMixin {
    @Shadow
    public abstract void growCrops(Level level, BlockPos pos, BlockState state);

    @ModifyReturnValue(
            method = "getGrowthSpeed",
            at = @At(
                    value = "RETURN")
    )
    private static float halfGrowthSpeed(float original) {
        return original / 2f;
    }

    @Inject(
            method = "randomTick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerLevel;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z"),
            cancellable = true)
    private void callGrowCropsInstead(BlockState state, ServerLevel level, BlockPos pos, RandomSource random, CallbackInfo ci) {
        growCrops(level, pos, state);
        ci.cancel();
    }
}
