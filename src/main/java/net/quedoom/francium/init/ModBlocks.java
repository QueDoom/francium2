package net.quedoom.francium.init;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.quedoom.francium.Francium;
import net.quedoom.francium.block.*;

import java.util.function.Function;

public class ModBlocks {

    public static final Block GLUE_MIXER = register("glue_mixer", GlueMixerBlock::new, BlockBehaviour.Properties.of()
            .ignitedByLava().mapColor(Blocks.OAK_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).noOcclusion()
            .strength(2.5F, 3.5F).sound(SoundType.WOOD));
    public static final Block WOODEN_MERGER = register("wooden_merger", WoodenMergerBlock::new, BlockBehaviour.Properties.of()
            .ignitedByLava().mapColor(Blocks.OAK_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS)
            .strength(2.5F, 3.5F).sound(SoundType.WOOD));
    public static final Block DRIPSTONE_SPIKES = register("dripstone_spikes", DripstoneSpikesBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DRIPSTONE_BLOCK).noOcclusion());
    public static final Block DEEP_MERGER = register("deep_merger", DeepMergerBlock::new, BlockBehaviour.Properties.of()
            .noOcclusion().mapColor(Blocks.DRIPSTONE_BLOCK.defaultMapColor()).instrument(NoteBlockInstrument.BASS)
            .strength(4.5F, 3.5F).sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops());
    public static final Block BUNDLE_TABLE = register("bundle_table", BundleTableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noOcclusion());
    public static final Block TRADER_BENCH = register("trader_bench", TraderBenchBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noOcclusion());

public static final Block WOODEN_CASING = register("wooden_casing", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noOcclusion());

    public static final Block MINERAL_MIX_BLOCK = register("mineral_mix_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ANDESITE));

    public static final Block MINERAL_MIXED_WOODEN_CASING = register("mineral_mixed_wooden_casing", properties ->
            new TransformWhenBrokenBlock(properties, MINERAL_MIX_BLOCK.defaultBlockState()), BlockBehaviour.Properties.ofFullCopy(WOODEN_CASING).mapColor(MINERAL_MIX_BLOCK.defaultMapColor()));

    public static final Block STONE_CASING = register("stone_casing", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));

    public static final Block OBSIDIAN_CASING = register("obsidian_casing", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN));

    public static final Block PILE_OF_LEAVES = register("pile_of_leaves", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES), false);

    public static final Block BLOCK_CONTAINING_WOODEN_CASING = register("block_containing_wooden_casing", p -> new BlockContainingBlock(p, WOODEN_CASING.defaultBlockState()),
            BlockBehaviour.Properties.ofFullCopy(WOODEN_CASING).noOcclusion().noLootTable(), false);

    public static final Block BLOCK_CONTAINING_STONE_CASING = register("block_containing_stone_casing", p -> new BlockContainingBlock(p, STONE_CASING.defaultBlockState()),
            BlockBehaviour.Properties.ofFullCopy(STONE_CASING).noOcclusion().noLootTable().requiresCorrectToolForDrops(), false);

    public static final Block ECHO_BLOCK = register("echo_block", BlockBehaviour.Properties.ofFullCopy(Blocks.SCULK));

    public static final Block FORBIDDEN_DUST = register("forbidden_dust", DustBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.REDSTONE_WIRE));
    public static final Block FORBIDDEN_FLAKE = register("forbidden_flake", DustBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.REDSTONE_WIRE));

    public static final Block SPECIAL_FORBIDDEN_DUST = register("special_forbidden_dust", DustBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.REDSTONE_WIRE), false);
    public static final Block SPECIAL_FORBIDDEN_FLAKE = register("special_forbidden_flake", DustBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.REDSTONE_WIRE), false);
    public static final Block SMALL_SPECIAL_FORBIDDEN_FLAKE = register("small_special_forbidden_flake", DustBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.REDSTONE_WIRE), false);

    public static final Block ANCIENT_BUNS = register("ancient_buns", AncientBunsBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ANCIENT_DEBRIS));
    public static final Block STRIPPED_SUGAR_CANE = register("stripped_sugar_cane", StrippedSugarCaneBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SUGAR_CANE));
    public static final Block STRIPPED_BAMBOO = register("stripped_bamboo", BambooStalkBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO));

    public static final Block FRYING_PAN_CAMPFIRE = register("frying_pan_campfire", FryingPanCampfireBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CAMPFIRE).sound(SoundType.IRON), false);
    public static final Block POT_CAMPFIRE = register("pot_campfire", PotCampfireBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CAMPFIRE).sound(SoundType.IRON), false);


    public static final Block HEAVY_SCULK = register("heavy_sculk", HeavySculkBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SCULK).strength(5));
    public static final Block RUBBER_BLOCK = register("rubber_block", BlockBehaviour.Properties.ofFullCopy(Blocks.HONEYCOMB_BLOCK));

    // Slabs



    private static Block register(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties, boolean shouldRegisterItem) {
        // Create a registry key for the block
        ResourceKey<Block> blockKey = keyOfBlock(name);
        // Create the block instance
        Block block = blockFactory.apply(properties.setId(blockKey));

        // Sometimes, you may not want to register an item for the block.
        // Eg: if it's a technical block like `minecraft:moving_piston` or `minecraft:end_gateway`
        if (shouldRegisterItem) {
            // Items need to be registered with a different type of registry key, but the ID
            // can be the same.
            ResourceKey<Item> itemKey = keyOfItem(name);

            BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(itemKey).useBlockDescriptionPrefix());
            Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
        }

        return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
    }
    private static Block register(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties) {
        // Create a registry key for the block
        ResourceKey<Block> blockKey = keyOfBlock(name);
        // Create the block instance
        Block block = blockFactory.apply(properties.setId(blockKey));

        ResourceKey<Item> itemKey = keyOfItem(name);

        BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(itemKey).useBlockDescriptionPrefix());
        Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);

        return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
    }

    private static Block register(String name, BlockBehaviour.Properties properies) {
        return register(name, Block::new, properies);
    }

    private static ResourceKey<Block> keyOfBlock(String name) {
        return ResourceKey.create(Registries.BLOCK, Francium.id(name));
    }

    private static ResourceKey<Item> keyOfItem(String name) {
        return ResourceKey.create(Registries.ITEM, Francium.id(name));
    }

    public static void registerBlocks() {

    }

}
