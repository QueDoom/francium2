package net.quedoom.francium.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.francium.init.ModTags;

import java.util.concurrent.CompletableFuture;

public class BlockTagGen extends FabricTagsProvider.BlockTagsProvider {
    public BlockTagGen(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        valueLookupBuilder(ModTags.Blocks.STONE_ORES)
                .add(Blocks.COAL_ORE)
                .add(Blocks.IRON_ORE)
                .add(Blocks.GOLD_ORE)
                .add(Blocks.COPPER_ORE)
                .add(Blocks.LAPIS_ORE)
                .add(Blocks.EMERALD_ORE)
                .add(Blocks.REDSTONE_ORE)
                .add(Blocks.DIAMOND_ORE);

        valueLookupBuilder(ModTags.Blocks.DEEPSLATE_ORES)
                .add(Blocks.DEEPSLATE_COAL_ORE)
                .add(Blocks.DEEPSLATE_IRON_ORE)
                .add(Blocks.DEEPSLATE_GOLD_ORE)
                .add(Blocks.DEEPSLATE_COPPER_ORE)
                .add(Blocks.DEEPSLATE_LAPIS_ORE)
                .add(Blocks.DEEPSLATE_DIAMOND_ORE)
                .add(Blocks.DEEPSLATE_REDSTONE_ORE)
                .add(Blocks.DEEPSLATE_EMERALD_ORE);

        valueLookupBuilder(ModTags.Blocks.NETHER_ORES)
                .add(Blocks.NETHER_QUARTZ_ORE)
                .add(Blocks.NETHER_GOLD_ORE);

        valueLookupBuilder(ModTags.Blocks.FORCE_REQUIRE_TOOL)
                .addOptionalTag(BlockTags.LOGS)
                .addOptionalTag(BlockTags.PLANKS)
                .addOptionalTag(ModTags.Blocks.TILLS)
                .add(Blocks.FARMLAND);

        valueLookupBuilder(ModTags.Blocks.HARD_BLOCKS)
                .addOptionalTag(BlockTags.MINEABLE_WITH_PICKAXE)
                .addOptionalTag(ConventionalBlockTags.GLASS_BLOCKS);

        valueLookupBuilder(ModTags.Blocks.SHARP_STICK_MINES_FAST)
                .addOptionalTag(BlockTags.MINEABLE_WITH_PICKAXE)
                .addOptionalTag(BlockTags.MINEABLE_WITH_AXE)
                .addOptionalTag(BlockTags.MINEABLE_WITH_SHOVEL);

        valueLookupBuilder(BlockTags.MINEABLE_WITH_AXE)
                .add(
                        ModBlocks.WOODEN_CASING,
                        ModBlocks.BLOCK_CONTAINING_WOODEN_CASING,
                        ModBlocks.MINERAL_MIXED_WOODEN_CASING,
                        ModBlocks.WOODEN_MERGER,
                        ModBlocks.GLUE_MIXER,
                        ModBlocks.BUNDLE_TABLE,
                        ModBlocks.TRADER_BENCH
                );

        valueLookupBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.OBSIDIAN_CASING);

        valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(
                        ModBlocks.STONE_CASING,
                        ModBlocks.OBSIDIAN_CASING,
                        ModBlocks.DRIPSTONE_SPIKES,
                        ModBlocks.DEEP_MERGER,
                        ModBlocks.MINERAL_MIX_BLOCK,
                        ModBlocks.BLOCK_CONTAINING_STONE_CASING,
                        ModBlocks.ANCIENT_BUNS
                );

        valueLookupBuilder(ModTags.Blocks.BLOCK_CONTAINING_BLOCK_COMPATIBLE)
                .add(ModBlocks.WOODEN_CASING)
                .add(ModBlocks.STONE_CASING)
        ;

//        valueLookupBuilder(ModTags.Blocks.DROPS_FORBIDDEN_DUST);

        valueLookupBuilder(ModTags.Blocks.DROPS_FORBIDDEN_FLAKE)
                .addOptionalTag(BlockTags.COPPER)
                .add(
                        Blocks.CUT_COPPER,
                        Blocks.CUT_COPPER_SLAB,
                        Blocks.CUT_COPPER_STAIRS,
                        Blocks.WAXED_CUT_COPPER,
                        Blocks.WAXED_CUT_COPPER_SLAB,
                        Blocks.WAXED_CUT_COPPER_STAIRS,
                        Blocks.EXPOSED_CUT_COPPER,
                        Blocks.EXPOSED_CUT_COPPER_SLAB,
                        Blocks.EXPOSED_CUT_COPPER_STAIRS,
                        Blocks.WAXED_EXPOSED_CUT_COPPER,
                        Blocks.WAXED_EXPOSED_CUT_COPPER_SLAB,
                        Blocks.WAXED_EXPOSED_CUT_COPPER_STAIRS,
                        Blocks.WEATHERED_CUT_COPPER,
                        Blocks.WEATHERED_CUT_COPPER_SLAB,
                        Blocks.WEATHERED_CUT_COPPER_STAIRS,
                        Blocks.WAXED_WEATHERED_CUT_COPPER,
                        Blocks.WAXED_WEATHERED_CUT_COPPER_SLAB,
                        Blocks.WAXED_WEATHERED_CUT_COPPER_STAIRS,
                        Blocks.OXIDIZED_CUT_COPPER,
                        Blocks.OXIDIZED_CUT_COPPER_SLAB,
                        Blocks.OXIDIZED_CUT_COPPER_STAIRS,
                        Blocks.WAXED_OXIDIZED_CUT_COPPER,
                        Blocks.WAXED_OXIDIZED_CUT_COPPER_SLAB,
                        Blocks.WAXED_OXIDIZED_CUT_COPPER_STAIRS,
                        Blocks.CHISELED_COPPER,
                        Blocks.WAXED_CHISELED_COPPER,
                        Blocks.EXPOSED_CHISELED_COPPER,
                        Blocks.WAXED_EXPOSED_CHISELED_COPPER,
                        Blocks.WEATHERED_CHISELED_COPPER,
                        Blocks.WAXED_WEATHERED_CHISELED_COPPER,
                        Blocks.OXIDIZED_CHISELED_COPPER,
                        Blocks.WAXED_OXIDIZED_CHISELED_COPPER
        );

        valueLookupBuilder(ModTags.Blocks.SMALL_DROPS_FORBIDDEN_FLAKE)
                .add(
                        Blocks.COPPER_GRATE,
                        Blocks.WAXED_COPPER_GRATE,
                        Blocks.EXPOSED_COPPER_GRATE,
                        Blocks.WAXED_EXPOSED_COPPER_GRATE,
                        Blocks.WEATHERED_COPPER_GRATE,
                        Blocks.WAXED_WEATHERED_COPPER_GRATE,
                        Blocks.OXIDIZED_COPPER_GRATE,
                        Blocks.WAXED_OXIDIZED_COPPER_GRATE,
                        Blocks.COPPER_CHAIN.unaffected(),
                        Blocks.COPPER_BARS.waxed(),
                        Blocks.COPPER_CHAIN.exposed(),
                        Blocks.COPPER_BARS.waxedExposed(),
                        Blocks.COPPER_CHAIN.weathered(),
                        Blocks.COPPER_BARS.weathered(),
                        Blocks.COPPER_CHAIN.oxidized(),
                        Blocks.COPPER_BARS.waxedOxidized(),
                        Blocks.COPPER_CHAIN.unaffected(),
                        Blocks.COPPER_CHAIN.waxed(),
                        Blocks.COPPER_BARS.exposed(),
                        Blocks.COPPER_CHAIN.waxedExposed(),
                        Blocks.COPPER_BARS.weathered(),
                        Blocks.COPPER_CHAIN.weathered(),
                        Blocks.COPPER_BARS.oxidized(),
                        Blocks.COPPER_CHAIN.waxedOxidized()
                );

        valueLookupBuilder(ModTags.Blocks.TILLS)
                .add(
                        Blocks.DIRT,
                        Blocks.DIRT_PATH,
                        Blocks.COARSE_DIRT,
                        Blocks.ROOTED_DIRT,
                        Blocks.GRASS_BLOCK
                );
    }
}

