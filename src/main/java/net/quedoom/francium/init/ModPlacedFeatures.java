package net.quedoom.francium.init;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.quedoom.francium.Francium;

import java.util.List;

public class ModPlacedFeatures {
    public static ResourceKey<PlacedFeature> BEDROCK_CHUNK = create("bedrock_chunk");
    public static ResourceKey<PlacedFeature> TUFF_COAL_ORES = create("tuff_coal_ores");
    public static ResourceKey<PlacedFeature> TUFF_COPPER_ORES = create("tuff_copper_ores");
    public static final ResourceKey<PlacedFeature> ROCK = create("rock/selector");


    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, BEDROCK_CHUNK, configuredFeatures.getOrThrow(ModConfiguredFeatures.BEDROCK_CHUNK_KEY),
                List.of(
                        CountPlacement.of(4),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.BOTTOM, VerticalAnchor.aboveBottom(8)),
                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.BEDROCK)),
                        BiomeFilter.biome()
                ));

        register(context, TUFF_COAL_ORES, configuredFeatures.getOrThrow(ModConfiguredFeatures.TUFF_COAL_ORE),
                OrePlacements.commonOrePlacement(4, HeightRangePlacement.triangle(VerticalAnchor.BOTTOM, VerticalAnchor.aboveBottom(64))));
        register(context, TUFF_COPPER_ORES, configuredFeatures.getOrThrow(ModConfiguredFeatures.TUFF_COPPER_ORE),
                OrePlacements.commonOrePlacement(4, HeightRangePlacement.triangle(VerticalAnchor.BOTTOM, VerticalAnchor.aboveBottom(64))));

        register(context, ROCK, configuredFeatures.getOrThrow(ModConfiguredFeatures.ROCK_SELECTOR),
                List.of(
                        NoiseBasedCountPlacement.of(1, 64.0D, 0.0D),
                        CountPlacement.of(3),
                        RarityFilter.onAverageOnceEvery(12),
                        InSquarePlacement.spread(),
                        HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR),
                        SurfaceWaterDepthFilter.forMaxDepth(1),
                        BiomeFilter.biome()
                ));
    }

    public static ResourceKey<PlacedFeature> create(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Francium.of(name));
    }
    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> config, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(config, List.copyOf(modifiers)));
    }
}
