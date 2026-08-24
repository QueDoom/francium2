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
                .add(Blocks.DIRT)
                .add(Blocks.GRASS_BLOCK);

        valueLookupBuilder(ModTags.Blocks.HARD_BLOCKS)
                .addOptionalTag(BlockTags.MINEABLE_WITH_PICKAXE)
                .addOptionalTag(ConventionalBlockTags.GLASS_BLOCKS);

        valueLookupBuilder(ModTags.Blocks.SHARP_STICK_MINES_FAST)
                .addOptionalTag(BlockTags.MINEABLE_WITH_PICKAXE)
                .addOptionalTag(BlockTags.MINEABLE_WITH_AXE)
                .addOptionalTag(BlockTags.MINEABLE_WITH_SHOVEL);


        valueLookupBuilder(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.WOODEN_CASING)
                .add(ModBlocks.MINERAL_MIXED_WOODEN_CASING)
                .add(ModBlocks.WOODEN_MERGER)
                .add(ModBlocks.GLUE_MIXER);

        valueLookupBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.OBSIDIAN_CASING);

        valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.STONE_CASING)
                .add(ModBlocks.OBSIDIAN_CASING)
                .add(ModBlocks.DEEP_MERGER)
                .add(ModBlocks.MINERAL_MIX_BLOCK)
        ;
    }
}
