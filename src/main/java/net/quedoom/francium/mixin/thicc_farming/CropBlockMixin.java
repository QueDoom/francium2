package net.quedoom.francium.mixin.thicc_farming;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.quedoom.francium.block.thicc_crops.ThickableCropBlock;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.francium.init.ModTags;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CropBlock.class)
public abstract class CropBlockMixin {
    @Shadow
    @Final
    public static IntegerProperty AGE;

    @Shadow
    public abstract int getMaxAge();

    @Inject(
            method = "isValidBonemealTarget",
            at = @At("HEAD"),
            cancellable = true
    )
    private void checkForThickable(LevelReader level, BlockPos pos, BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (state.getBlock() instanceof ThickableCropBlock) {
            cir.setReturnValue(true);
        }
    }


    @Inject(
            method = "isRandomlyTicking",
            at = @At("HEAD"),
            cancellable = true
    )
    private void checkForThickableAgain(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (state.getBlock() instanceof ThickableCropBlock) {
            cir.setReturnValue(true);
        }
    }
}
