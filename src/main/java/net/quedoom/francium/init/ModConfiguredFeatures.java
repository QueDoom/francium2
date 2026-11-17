package net.quedoom.francium.init;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.quedoom.francium.Francium;

import java.util.List;

public class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> BEDROCK_CHUNK_KEY = create("bedrock_chunk");

    public static final ResourceKey<ConfiguredFeature<?, ?>> TUFF_COAL_ORE = create("tuff_coal_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TUFF_COPPER_ORE = create("tuff_copper_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> TUFF = create("rock/tuff");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TUFF_COAL_ORE_ROCK = create("rock/tuff_coal_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TUFF_COPPER_ORE_ROCK = create("rock/tuff_copper_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> ROCK_SELECTOR = create("rock/selector");


    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        HolderGetter<ConfiguredFeature<?, ?>> features = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, BEDROCK_CHUNK_KEY, Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.BEDROCK_CHUNK.defaultBlockState())));

        register(context, TUFF_COAL_ORE, Feature.ORE, new OreConfiguration(
                List.of(OreConfiguration.target(new BlockMatchTest(Blocks.TUFF), ModBlocks.COAL_TUFF_ORE.defaultBlockState())),
                3));

        register(context, TUFF_COPPER_ORE, Feature.ORE, new OreConfiguration(
                List.of(OreConfiguration.target(new BlockMatchTest(Blocks.TUFF), ModBlocks.COPPER_TUFF_ORE.defaultBlockState())),
                3));

        BlockPredicate rockGround = BlockPredicate.matchesTag(BlockTags.FOREST_ROCK_CAN_PLACE_ON);

        register(context, TUFF, Feature.BLOCK_BLOB,
                new BlockBlobConfiguration(Blocks.TUFF.defaultBlockState(), rockGround));
        register(context, TUFF_COAL_ORE_ROCK, Feature.BLOCK_BLOB,
                new BlockBlobConfiguration(ModBlocks.COAL_TUFF_ORE.defaultBlockState(), rockGround));
        register(context, TUFF_COPPER_ORE_ROCK, Feature.BLOCK_BLOB,
                new BlockBlobConfiguration(ModBlocks.COPPER_TUFF_ORE.defaultBlockState(), rockGround));

        Holder<PlacedFeature> tuffCoalOre = PlacementUtils.inlinePlaced(features.getOrThrow(TUFF_COAL_ORE_ROCK));
        Holder<PlacedFeature> tuffCopperOre = PlacementUtils.inlinePlaced(features.getOrThrow(TUFF_COPPER_ORE_ROCK));
        Holder<PlacedFeature> tuff = PlacementUtils.inlinePlaced(features.getOrThrow(TUFF));

        register(context, ROCK_SELECTOR, Feature.RANDOM_SELECTOR,
                new RandomFeatureConfiguration(
                        List.of(
                                new WeightedPlacedFeature(tuffCoalOre, 0.25F),
                                new WeightedPlacedFeature(tuffCopperOre, 0.3333333F)
                        ),
                        tuff
                ));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> create(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Francium.of(name));
    }
    private static <C extends FeatureConfiguration, F extends Feature<C>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                        ResourceKey<ConfiguredFeature<?, ?>> key, F feature, C config) {
        context.register(key, new ConfiguredFeature<>(feature, config));
    }
}
