package net.quedoom.francium.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.quedoom.francium.init.ModBlocks;
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
    private void checkForWheat(LevelReader level, BlockPos pos, BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (state.is(Blocks.POTATOES) || state.is(Blocks.CARROTS) || state.is(Blocks.BEETROOTS)) {
            cir.setReturnValue(true);
        }
    }


    @Inject(
            method = "isRandomlyTicking",
            at = @At("HEAD"),
            cancellable = true
    )
    private void checkForWheatAgain(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (state.is(Blocks.POTATOES) || state.is(Blocks.CARROTS) || state.is(Blocks.BEETROOTS)) {
            cir.setReturnValue(true);
        }
    }


    @Inject(
            method = "growCrops",
            at = @At("HEAD"),
            cancellable = true
    )
    private void checkForWheatAndGrow(Level level, BlockPos pos, BlockState state, CallbackInfo ci) {
        if (state.is(Blocks.POTATOES) || state.is(Blocks.CARROTS) || state.is(Blocks.BEETROOTS)) {
            if (state.getValue(CropBlock.AGE) == getMaxAge()) {
                BlockState stateToPlace;
                if (state.is(Blocks.POTATOES)) stateToPlace = ModBlocks.THICK_POTATO.defaultBlockState();
                else if (state.is(Blocks.CARROTS)) stateToPlace = ModBlocks.THICK_CARROT.defaultBlockState();
                else stateToPlace = ModBlocks.THICK_BEETROOT.defaultBlockState();
                level.setBlockAndUpdate(pos, stateToPlace);
                ci.cancel();
            }
        }
    }
}
