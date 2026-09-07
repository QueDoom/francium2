package net.quedoom.francium.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FlintAndSteelItem.class)
public class MakeItSoFlintAndSteeelWorksOnPotPanBlockMixin {
    @ModifyExpressionValue(
            method = "useOn",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/CampfireBlock;canLight(Lnet/minecraft/world/level/block/state/BlockState;)Z")
    )

    private boolean supaCampa(boolean original) {
//        BlockPos pos = context.getClickedPos();
//        BlockState state = context.getLevel().getBlockState(pos);
        return original;
    }
}
