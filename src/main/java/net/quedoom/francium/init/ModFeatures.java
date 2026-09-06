package net.quedoom.francium.init;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.BlockColumnFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockColumnConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.quedoom.francium.util.SugarCaneFeature;

public class ModFeatures {
    public static final Feature<BlockColumnConfiguration> SUGAR_CANE = register("sugar_cane", new SugarCaneFeature(BlockColumnConfiguration.CODEC));

    private static <C extends FeatureConfiguration, F extends Feature<C>> F register(final String name, final F feature) {
        return Registry.register(BuiltInRegistries.FEATURE, name, feature);
    }
    public static void registerFeatures() {}
}
