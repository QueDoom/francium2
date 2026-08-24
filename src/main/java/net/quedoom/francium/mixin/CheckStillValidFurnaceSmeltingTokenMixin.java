package net.quedoom.francium.mixin;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.inventory.FurnaceMenu;
import net.quedoom.francium.init.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractFurnaceMenu.class)
public class CheckStillValidFurnaceSmeltingTokenMixin {
    @Inject(
            method = "stillValid",
            at = @At(value = "HEAD"),
            cancellable = true)
    private void stillHasToken(Player player, CallbackInfoReturnable<Boolean> cir) {
        if (!player.getInventory().contains(ModItems.SMELTING_TOKEN.getDefaultInstance())) cir.setReturnValue(false);
    }
}
