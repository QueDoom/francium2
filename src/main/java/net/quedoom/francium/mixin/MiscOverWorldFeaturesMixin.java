package net.quedoom.francium.mixin;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.MiscOverworldFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.quedoom.francium.init.ModFeatures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MiscOverworldFeatures.class)
public class MiscOverWorldFeaturesMixin {
    @Inject(
            method = "bootstrap",
            at = @At("TAIL")
    )
    private static void register(BootstrapContext<ConfiguredFeature<?, ?>> context, CallbackInfo ci) {
        ModFeatures.registerMiscOverworld(context);
    }
}
