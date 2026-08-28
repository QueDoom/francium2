package net.quedoom.francium.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.level.block.CampfireBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(CampfireBlock.class)
public class PlaceSandInCampfireMixin {
    @ModifyExpressionValue(
            method = "useItemOn",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/crafting/RecipePropertySet;test(Lnet/minecraft/world/item/ItemStack;)Z")
    )

    private boolean editCondition(boolean original) {
        return original;
    }
}
