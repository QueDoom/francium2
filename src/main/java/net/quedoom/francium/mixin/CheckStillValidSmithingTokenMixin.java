//package net.quedoom.francium.mixin;
//
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.inventory.AbstractFurnaceMenu;
//import net.minecraft.world.inventory.ItemCombinerMenu;
//import net.minecraft.world.inventory.SmithingMenu;
//import net.quedoom.francium.init.ModItems;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
//
//@Mixin(SmithingMenu.class)
//public class CheckStillValidSmithingTokenMixin {
//    @Inject(
//            method = "stillValid",
//            at = @At(value = "HEAD"),
//            cancellable = true)
//    private void stillHasToken(Player player, CallbackInfoReturnable<Boolean> cir) {
//        if (!player.getInventory().contains(ModItems.SMITHING_TOKEN.getDefaultInstance())) cir.setReturnValue(false);
//    }
//}
