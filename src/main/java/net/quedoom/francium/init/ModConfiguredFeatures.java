package net.quedoom.francium.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.quedoom.francium.Francium;

import java.util.List;

public class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> BEDROCK_CHUNK_KEY = create("bedrock_chunk");

    public static final ResourceKey<ConfiguredFeature<?, ?>> TUFF_COAL_ORE = create("tuff_coal_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TUFF_COPPER_ORE = create("tuff_copper_ore");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        register(context, BEDROCK_CHUNK_KEY, Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.BEDROCK_CHUNK.defaultBlockState())));

        register(context, TUFF_COAL_ORE, Feature.ORE, new OreConfiguration(
                List.of(OreConfiguration.target(new BlockMatchTest(Blocks.TUFF), ModBlocks.COAL_TUFF_ORE.defaultBlockState())),
                3));

        register(context, TUFF_COPPER_ORE, Feature.ORE, new OreConfiguration(
                List.of(OreConfiguration.target(new BlockMatchTest(Blocks.TUFF), ModBlocks.COPPER_TUFF_ORE.defaultBlockState())),
                3));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> create(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Francium.of(name));
    }
    private static <C extends FeatureConfiguration, F extends Feature<C>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                        ResourceKey<ConfiguredFeature<?, ?>> key, F feature, C config) {
        context.register(key, new ConfiguredFeature<>(feature, config));
    }
}
