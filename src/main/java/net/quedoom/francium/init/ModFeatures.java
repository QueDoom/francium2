package net.quedoom.francium.init;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.quedoom.francium.Francium;
import net.quedoom.francium.feature.TuffRockConfiguration;
import net.quedoom.francium.feature.TuffRockFeature;

public class ModFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> TUFF_ROCK_KEY = create("tuff_rock");
    public static final Feature<TuffRockConfiguration> TUFF_ROCK = register("tuff_rock", new TuffRockFeature());

    public static void registerMiscOverworld(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        FeatureUtils.register(
                context,
                TUFF_ROCK_KEY,
                TUFF_ROCK,
                new TuffRockConfiguration(
                        BlockPredicate.matchesBlocks(Blocks.GRASS_BLOCK, Blocks.STONE), BlockPredicate.matchesTag(ModTags.Blocks.TUFF_ROCKS_REPLACEABLE)
                )
        );
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> create(String string) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Francium.id(string));
    }

    private static <C extends FeatureConfiguration, F extends Feature<C>> F register(final String name, final F feature) {
        return Registry.register(BuiltInRegistries.FEATURE, name, feature);
    }
}
