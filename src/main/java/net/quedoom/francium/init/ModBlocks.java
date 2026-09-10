package net.quedoom.francium.init;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.quedoom.francium.Francium;
import net.quedoom.francium.block.*;

import java.util.function.Function;

public class ModBlocks {

    public static final Block GLUE_MIXER = register("glue_mixer", WoodenMixerBlock::new, BlockBehaviour.Properties.of()
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

    public static final Block ECHO_BLOCK = register("echo_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SCULK), false);
    public static final Item ECHO_BLOCK_ITEM = registerItem("echo_block", ECHO_BLOCK, new Item.Properties().rarity(Rarity.RARE));

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

    // FORBIDDEN
    public static final Block ALLOWED_COPPER_BLOCK = register(
            "allowed_copper_block",
            p -> new WeatheringCopperFullBlock(WeatheringCopper.WeatherState.UNAFFECTED, p),
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_ORANGE)
                    .requiresCorrectToolForDrops()
                    .strength(3.0F, 6.0F)
                    .instrument(NoteBlockInstrument.TRUMPET)
                    .sound(SoundType.COPPER)
    );
    public static final Block ALLOWED_EXPOSED_COPPER = register(
            "allowed_exposed_copper",
            p -> new WeatheringCopperFullBlock(WeatheringCopper.WeatherState.EXPOSED, p),
            BlockBehaviour.Properties.ofFullCopy(ALLOWED_COPPER_BLOCK).instrument(NoteBlockInstrument.TRUMPET_EXPOSED).mapColor(MapColor.TERRACOTTA_LIGHT_GRAY)
    );
    public static final Block ALLOWED_WEATHERED_COPPER = register(
            "allowed_weathered_copper",
            p -> new WeatheringCopperFullBlock(WeatheringCopper.WeatherState.WEATHERED, p),
            BlockBehaviour.Properties.ofFullCopy(ALLOWED_COPPER_BLOCK).instrument(NoteBlockInstrument.TRUMPET_WEATHERED).mapColor(MapColor.WARPED_STEM)
    );
    public static final Block ALLOWED_OXIDIZED_COPPER = register(
            "allowed_oxidized_copper",
            p -> new WeatheringCopperFullBlock(WeatheringCopper.WeatherState.OXIDIZED, p),
            BlockBehaviour.Properties.ofFullCopy(ALLOWED_COPPER_BLOCK).instrument(NoteBlockInstrument.TRUMPET_OXIDIZED).mapColor(MapColor.WARPED_NYLIUM)
    );
    public static final Block ALLOWED_OXIDIZED_CUT_COPPER = register(
            "allowed_oxidized_cut_copper", p -> new WeatheringCopperFullBlock(WeatheringCopper.WeatherState.OXIDIZED, p), BlockBehaviour.Properties.ofFullCopy(ALLOWED_OXIDIZED_COPPER)
    );
    public static final Block ALLOWED_WEATHERED_CUT_COPPER = register(
            "allowed_weathered_cut_copper",
            p -> new WeatheringCopperFullBlock(WeatheringCopper.WeatherState.WEATHERED, p),
            BlockBehaviour.Properties.ofFullCopy(ALLOWED_WEATHERED_COPPER)
    );
    public static final Block ALLOWED_EXPOSED_CUT_COPPER = register(
            "allowed_exposed_cut_copper", p -> new WeatheringCopperFullBlock(WeatheringCopper.WeatherState.EXPOSED, p), BlockBehaviour.Properties.ofFullCopy(ALLOWED_EXPOSED_COPPER)
    );
    public static final Block ALLOWED_CUT_COPPER = register(
            "allowed_cut_copper", p -> new WeatheringCopperFullBlock(WeatheringCopper.WeatherState.UNAFFECTED, p), BlockBehaviour.Properties.ofFullCopy(ALLOWED_COPPER_BLOCK)
    );
    public static final Block ALLOWED_OXIDIZED_CHISELED_COPPER = register(
            "allowed_oxidized_chiseled_copper",
            p -> new WeatheringCopperFullBlock(WeatheringCopper.WeatherState.OXIDIZED, p),
            BlockBehaviour.Properties.ofFullCopy(ALLOWED_OXIDIZED_COPPER)
    );
    public static final Block ALLOWED_WEATHERED_CHISELED_COPPER = register(
            "allowed_weathered_chiseled_copper",
            p -> new WeatheringCopperFullBlock(WeatheringCopper.WeatherState.WEATHERED, p),
            BlockBehaviour.Properties.ofFullCopy(ALLOWED_WEATHERED_COPPER)
    );
    public static final Block ALLOWED_EXPOSED_CHISELED_COPPER = register(
            "allowed_exposed_chiseled_copper", p -> new WeatheringCopperFullBlock(WeatheringCopper.WeatherState.EXPOSED, p), BlockBehaviour.Properties.ofFullCopy(ALLOWED_EXPOSED_COPPER)
    );
    public static final Block ALLOWED_CHISELED_COPPER = register(
            "allowed_chiseled_copper", p -> new WeatheringCopperFullBlock(WeatheringCopper.WeatherState.UNAFFECTED, p), BlockBehaviour.Properties.ofFullCopy(ALLOWED_COPPER_BLOCK)
    );
    public static final Block ALLOWED_WAXED_OXIDIZED_CHISELED_COPPER = register(
            "allowed_waxed_oxidized_chiseled_copper", BlockBehaviour.Properties.ofFullCopy(ALLOWED_OXIDIZED_CHISELED_COPPER)
    );
    public static final Block ALLOWED_WAXED_WEATHERED_CHISELED_COPPER = register(
            "allowed_waxed_weathered_chiseled_copper", BlockBehaviour.Properties.ofFullCopy(ALLOWED_WEATHERED_CHISELED_COPPER)
    );
    public static final Block ALLOWED_WAXED_EXPOSED_CHISELED_COPPER = register(
            "allowed_waxed_exposed_chiseled_copper", BlockBehaviour.Properties.ofFullCopy(ALLOWED_EXPOSED_CHISELED_COPPER)
    );
    public static final Block ALLOWED_WAXED_CHISELED_COPPER = register("allowed_waxed_chiseled_copper", BlockBehaviour.Properties.ofFullCopy(ALLOWED_CHISELED_COPPER));
    public static final Block ALLOWED_OXIDIZED_CUT_COPPER_STAIRS = register(
            "allowed_oxidized_cut_copper_stairs",
            p -> new WeatheringCopperStairBlock(WeatheringCopper.WeatherState.OXIDIZED, ALLOWED_OXIDIZED_CUT_COPPER.defaultBlockState(), p),
            BlockBehaviour.Properties.ofFullCopy(ALLOWED_OXIDIZED_CUT_COPPER)
    );
    public static final Block ALLOWED_WEATHERED_CUT_COPPER_STAIRS = register(
            "allowed_weathered_cut_copper_stairs",
            p -> new WeatheringCopperStairBlock(WeatheringCopper.WeatherState.WEATHERED, ALLOWED_WEATHERED_CUT_COPPER.defaultBlockState(), p),
            BlockBehaviour.Properties.ofFullCopy(ALLOWED_WEATHERED_COPPER)
    );
    public static final Block ALLOWED_EXPOSED_CUT_COPPER_STAIRS = register(
            "allowed_exposed_cut_copper_stairs",
            p -> new WeatheringCopperStairBlock(WeatheringCopper.WeatherState.EXPOSED, ALLOWED_EXPOSED_CUT_COPPER.defaultBlockState(), p),
            BlockBehaviour.Properties.ofFullCopy(ALLOWED_EXPOSED_COPPER)
    );
    public static final Block ALLOWED_CUT_COPPER_STAIRS = register(
            "allowed_cut_copper_stairs",
            p -> new WeatheringCopperStairBlock(WeatheringCopper.WeatherState.UNAFFECTED, ALLOWED_CUT_COPPER.defaultBlockState(), p),
            BlockBehaviour.Properties.ofFullCopy(ALLOWED_COPPER_BLOCK)
    );
    public static final Block ALLOWED_OXIDIZED_CUT_COPPER_SLAB = register(
            "allowed_oxidized_cut_copper_slab",
            p -> new WeatheringCopperSlabBlock(WeatheringCopper.WeatherState.OXIDIZED, p),
            BlockBehaviour.Properties.ofFullCopy(ALLOWED_OXIDIZED_CUT_COPPER)
    );
    public static final Block ALLOWED_WEATHERED_CUT_COPPER_SLAB = register(
            "allowed_weathered_cut_copper_slab",
            p -> new WeatheringCopperSlabBlock(WeatheringCopper.WeatherState.WEATHERED, p),
            BlockBehaviour.Properties.ofFullCopy(ALLOWED_WEATHERED_CUT_COPPER)
    );
    public static final Block ALLOWED_EXPOSED_CUT_COPPER_SLAB = register(
            "allowed_exposed_cut_copper_slab",
            p -> new WeatheringCopperSlabBlock(WeatheringCopper.WeatherState.EXPOSED, p),
            BlockBehaviour.Properties.ofFullCopy(ALLOWED_EXPOSED_CUT_COPPER)
    );
    public static final Block ALLOWED_CUT_COPPER_SLAB = register(
            "allowed_cut_copper_slab", p -> new WeatheringCopperSlabBlock(WeatheringCopper.WeatherState.UNAFFECTED, p), BlockBehaviour.Properties.ofFullCopy(ALLOWED_CUT_COPPER)
    );
    public static final Block ALLOWED_WAXED_COPPER_BLOCK = register("allowed_waxed_copper_block", BlockBehaviour.Properties.ofFullCopy(ALLOWED_COPPER_BLOCK));
    public static final Block ALLOWED_WAXED_WEATHERED_COPPER = register("allowed_waxed_weathered_copper", BlockBehaviour.Properties.ofFullCopy(ALLOWED_WEATHERED_COPPER));
    public static final Block ALLOWED_WAXED_EXPOSED_COPPER = register("allowed_waxed_exposed_copper", BlockBehaviour.Properties.ofFullCopy(ALLOWED_EXPOSED_COPPER));
    public static final Block ALLOWED_WAXED_OXIDIZED_COPPER = register("allowed_waxed_oxidized_copper", BlockBehaviour.Properties.ofFullCopy(ALLOWED_OXIDIZED_COPPER));
    public static final Block ALLOWED_WAXED_OXIDIZED_CUT_COPPER = register("allowed_waxed_oxidized_cut_copper", BlockBehaviour.Properties.ofFullCopy(ALLOWED_OXIDIZED_COPPER));
    public static final Block ALLOWED_WAXED_WEATHERED_CUT_COPPER = register("allowed_waxed_weathered_cut_copper", BlockBehaviour.Properties.ofFullCopy(ALLOWED_WEATHERED_COPPER));
    public static final Block ALLOWED_WAXED_EXPOSED_CUT_COPPER = register("allowed_waxed_exposed_cut_copper", BlockBehaviour.Properties.ofFullCopy(ALLOWED_EXPOSED_COPPER));
    public static final Block ALLOWED_WAXED_CUT_COPPER = register("allowed_waxed_cut_copper", BlockBehaviour.Properties.ofFullCopy(ALLOWED_COPPER_BLOCK));
    public static final Block ALLOWED_WAXED_OXIDIZED_CUT_COPPER_STAIRS = registerStair("allowed_waxed_oxidized_cut_copper_stairs", ALLOWED_WAXED_OXIDIZED_CUT_COPPER);
    public static final Block ALLOWED_WAXED_WEATHERED_CUT_COPPER_STAIRS = registerStair("allowed_waxed_weathered_cut_copper_stairs", ALLOWED_WAXED_WEATHERED_CUT_COPPER);
    public static final Block ALLOWED_WAXED_EXPOSED_CUT_COPPER_STAIRS = registerStair("allowed_waxed_exposed_cut_copper_stairs", ALLOWED_WAXED_EXPOSED_CUT_COPPER);
    public static final Block ALLOWED_WAXED_CUT_COPPER_STAIRS = registerStair("allowed_waxed_cut_copper_stairs", ALLOWED_WAXED_CUT_COPPER);
    public static final Block ALLOWED_WAXED_OXIDIZED_CUT_COPPER_SLAB = register(
            "allowed_waxed_oxidized_cut_copper_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(ALLOWED_WAXED_OXIDIZED_CUT_COPPER).requiresCorrectToolForDrops()
    );
    public static final Block ALLOWED_WAXED_WEATHERED_CUT_COPPER_SLAB = register(
            "allowed_waxed_weathered_cut_copper_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(ALLOWED_WAXED_WEATHERED_CUT_COPPER).requiresCorrectToolForDrops()
    );
    public static final Block ALLOWED_WAXED_EXPOSED_CUT_COPPER_SLAB = register(
            "allowed_waxed_exposed_cut_copper_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(ALLOWED_WAXED_EXPOSED_CUT_COPPER).requiresCorrectToolForDrops()
    );
    public static final Block ALLOWED_WAXED_CUT_COPPER_SLAB = register(
            "allowed_waxed_cut_copper_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(ALLOWED_WAXED_CUT_COPPER).requiresCorrectToolForDrops()
    );

    private static Block registerStair(String name, Block block) {
        return register(name, p -> new StairBlock(block.defaultBlockState(), p), BlockBehaviour.Properties.ofFullCopy(block));
    }

    private static Item registerItem(String name, Block block, Item.Properties properties) {
        ResourceKey<Item> itemKey = keyOfItem(name);

        BlockItem blockItem = new BlockItem(block, properties);
        return Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
    }

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
