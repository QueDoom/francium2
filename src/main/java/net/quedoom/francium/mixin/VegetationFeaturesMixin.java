package net.quedoom.francium.mixin;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.quedoom.francium.init.ModFeatures;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Debug(export = true)
@Mixin(FeatureUtils.class)
public class VegetationFeaturesMixin {
    @Inject(
            method = "register(Lnet/minecraft/data/worldgen/BootstrapContext;Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/world/level/levelgen/feature/Feature;Lnet/minecraft/world/level/levelgen/feature/configurations/FeatureConfiguration;)V",
            at = @At(value = "HEAD"
            ),
            cancellable = true
    )
    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void changeFeature(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> id, F feature, FC config, CallbackInfo ci) {
        if (id.identifier().getPath().equals("sugar_cane")) {
            context.register(id, new ConfiguredFeature(ModFeatures.SUGAR_CANE, config));
            ci.cancel();
        }
    }
}
