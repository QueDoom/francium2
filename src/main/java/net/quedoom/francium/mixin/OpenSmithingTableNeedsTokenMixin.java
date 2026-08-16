package net.quedoom.francium.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.SmithingTableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.quedoom.francium.init.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SmithingTableBlock.class)
public class OpenSmithingTableNeedsTokenMixin {
    @Inject(method = "useWithoutItem",
            at = @At(value = "HEAD"), cancellable = true)

    protected void checkForToken(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult, CallbackInfoReturnable<InteractionResult> cir) {
        if (!player.getInventory().contains(ModItems.SMITHING_TOKEN.getDefaultInstance())) {
            cir.setReturnValue(InteractionResult.FAIL);
        }
    }
}
