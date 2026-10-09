package net.quedoom.francium.init;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.quedoom.francium.Francium;
import net.quedoom.francium.block.*;
import net.quedoom.francium.block.thicc_farming.EighthsEatableBlockWithStem;
import net.quedoom.francium.block.thicc_farming.FallingEighthsEatableBlock;
import net.quedoom.francium.block.thicc_farming.ThickFoliageBlock;
import net.quedoom.quet.init.QueTBlock;

import java.util.Optional;
import java.util.function.Function;

public class ModBlocks  {
    private static final QueTBlock Q = new QueTBlock(Francium.MOD_ID);

    public static final Block GLUE_MIXER = Q.register("glue_mixer", WoodenMixerBlock::new, BlockBehaviour.Properties.of()
            .ignitedByLava().mapColor(Blocks.OAK_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).noOcclusion()
            .strength(2.5F, 3.5F).sound(SoundType.WOOD));
    public static final Block WOODEN_MERGER = Q.register("wooden_merger", WoodenMergerBlock::new, BlockBehaviour.Properties.of()
            .ignitedByLava().mapColor(Blocks.OAK_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS)
            .strength(2.5F, 3.5F).sound(SoundType.WOOD));
    public static final Block DRIPSTONE_SPIKES = Q.register("dripstone_spikes", DripstoneSpikesBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DRIPSTONE_BLOCK).noOcclusion());
    public static final Block DEEP_MERGER = Q.register("deep_merger", DeepMergerBlock::new, BlockBehaviour.Properties.of()
            .noOcclusion().mapColor(Blocks.DRIPSTONE_BLOCK.defaultMapColor()).instrument(NoteBlockInstrument.BASS)
            .strength(4.5F, 3.5F).sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops());
    public static final Block BUNDLE_TABLE = Q.register("bundle_table", BundleTableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noOcclusion());
    public static final Block TRADER_BENCH = Q.register("trader_bench", TraderBenchBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noOcclusion());

public static final Block WOODEN_CASING = Q.register("wooden_casing", WoodenCasingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noOcclusion());

    public static final Block MINERAL_MIX_BLOCK = Q.register("mineral_mix_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ANDESITE));

    public static final Block STONE_CASING = Q.register("stone_casing", StoneCasingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));

    public static final Block OBSIDIAN_CASING = Q.register("obsidian_casing", ObsidianCasingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN));

    public static final Block PILE_OF_LEAVES = Q.register("pile_of_leaves", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES), false);

    public static final Block WOODEN_CASING_CONTAINING_BLOCK = Q.register("wooden_casing_containing_block", BlockContainingBlock::new,
            BlockBehaviour.Properties.ofFullCopy(WOODEN_CASING).noOcclusion(), false);
    public static final Block STONE_CASING_CONTAINING_BLOCK = Q.register("stone_casing_containing_block", BlockContainingBlock::new,
            BlockBehaviour.Properties.ofFullCopy(STONE_CASING).noOcclusion().requiresCorrectToolForDrops(), false);
    public static final Block OBSIDIAN_CASING_CONTAINING_BLOCK = Q.register("obsidian_casing_containing_block", BlockContainingBlock::new,
            BlockBehaviour.Properties.ofFullCopy(OBSIDIAN_CASING).noOcclusion().requiresCorrectToolForDrops(), false);

    public static final Block WOODEN_CASING_CONTAINING_ITEMS = Q.register("wooden_casing_containing_items", BlockContainingItems::new,
            BlockBehaviour.Properties.ofFullCopy(WOODEN_CASING).noLootTable().noOcclusion(), false);
    public static final Block STONE_CASING_CONTAINING_ITEMS = Q.register("stone_casing_containing_items", BlockContainingItems::new,
            BlockBehaviour.Properties.ofFullCopy(STONE_CASING).noLootTable().noOcclusion().requiresCorrectToolForDrops(), false);
    public static final Block OBSIDIAN_CASING_CONTAINING_ITEMS = Q.register("obsidian_casing_containing_items", BlockContainingItems::new,
            BlockBehaviour.Properties.ofFullCopy(OBSIDIAN_CASING).noLootTable().noOcclusion().requiresCorrectToolForDrops(), false);
    public static final Block HEAVY_ANVIL = Q.register("heavy_anvil", HeavyAnvilBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.ANVIL).noOcclusion());
    public static final Block WOODEN_CASING_WITH_HEAVY_ANVIL = Q.register("wooden_casing_with_heavy_anvil", WoodenCasingWithHeavyAnvilBlock::new,
            BlockBehaviour.Properties.ofFullCopy(WOODEN_CASING).noOcclusion(), false);

    public static final Block SMOOTH_CACTUS = Q.register("smooth_cactus", SmoothCactusBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noOcclusion().randomTicks().strength(0.4F).sound(SoundType.WOOL).pushReaction(PushReaction.DESTROY));

    public static final Block ECHO_BLOCK = Q.register("echo_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SCULK), false);

    public static final Block FORBIDDEN_DUST = Q.register("forbidden_dust", DustBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.REDSTONE_WIRE));
    public static final Block FORBIDDEN_FLAKE = Q.register("forbidden_flake", DustBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.REDSTONE_WIRE));

    public static final Block SPECIAL_FORBIDDEN_DUST = Q.register("special_forbidden_dust", DustBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.REDSTONE_WIRE), false);
    public static final Block SPECIAL_FORBIDDEN_FLAKE = Q.register("special_forbidden_flake", DustBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.REDSTONE_WIRE), false);
    public static final Block SMALL_SPECIAL_FORBIDDEN_FLAKE = Q.register("small_special_forbidden_flake", DustBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.REDSTONE_WIRE), false);

    public static final Block ANCIENT_BUNS = Q.register("ancient_buns", AncientBunsBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ANCIENT_DEBRIS));
    public static final Block STRIPPED_SUGAR_CANE = Q.register("stripped_sugar_cane", StrippedSugarCaneBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SUGAR_CANE));
    public static final Block STRIPPED_BAMBOO = Q.register("stripped_bamboo", BambooStalkBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO), false);
    public static final Item STRIPPED_BAMBOO_ITEM = registerItem(createItem("stripped_bamboo"), Item::new, new Item.Properties());

    public static final Block FRYING_PAN_CAMPFIRE = Q.register("frying_pan_campfire", FryingPanCampfireBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CAMPFIRE).sound(SoundType.IRON), false);
    public static final Block POT_CAMPFIRE = Q.register("pot_campfire", PotCampfireBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CAMPFIRE).sound(SoundType.IRON), false);

    public static final Block HEAVY_SCULK = Q.register("heavy_sculk", HeavySculkBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SCULK).strength(5));
    public static final Block RUBBER_BLOCK = Q.register("rubber_block", BlockBehaviour.Properties.ofFullCopy(Blocks.HONEYCOMB_BLOCK));

    public static final Block SAWED_BLOCK = Q.register("sawed_block", SawedBlock::new, BlockBehaviour.Properties.of().noLootTable().strength(-1).noOcclusion(), false);

    public static final Block THICK_POTATO = Q.register("thick_potato", p -> new EighthsEatableBlockWithStem(1, 0.15F, p), BlockBehaviour.Properties.ofFullCopy(Blocks.MELON).strength(-1f).noLootTable().noOcclusion());
    public static final Block THICK_POTATO_FOLIAGE = Q.register("thick_potato_foliage", p -> new ThickFoliageBlock(THICK_POTATO, Optional.of(Items.POTATO), p), thickFoliage(), false);
    public static final Block THICK_CARROT = Q.register("thick_carrot", EighthsEatableBlockWithStem::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MELON).strength(-1f).noLootTable().noOcclusion());
    public static final Block THICK_CARROT_FOLIAGE = Q.register("thick_carrot_foliage", p -> new ThickFoliageBlock(THICK_CARROT, Optional.of(Items.CARROT), p), thickFoliage(), false);
    public static final Block THICK_BEETROOT = Q.register("thick_beetroot", p -> new EighthsEatableBlockWithStem(1, 0.05F, p), BlockBehaviour.Properties.ofFullCopy(Blocks.MELON).strength(-1f).noLootTable().noOcclusion());
    public static final Block THICK_BEETROOT_FOLIAGE = Q.register("thick_beetroot_foliage", p -> new ThickFoliageBlock(THICK_BEETROOT, Optional.of(Items.BEETROOT_SEEDS), p), thickFoliage(), false);
    public static final Block THICK_APPLE = Q.register("thick_apple", FallingEighthsEatableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MELON).strength(-1f).noLootTable().noOcclusion());

    private static BlockBehaviour.Properties thickFoliage() {
        return BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollision().strength(-1).noLootTable().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY);
    }

    static {
        registerItem(createItem("echo_block"), p -> new BlockItem(ECHO_BLOCK, p), new Item.Properties().rarity(Rarity.UNCOMMON));
    }

    public static Item registerItem(ResourceKey<Item> key, Function<Item.Properties, Item> itemFactory, Item.Properties properties) {
        Item item = itemFactory.apply(properties.setId(key));
        if (item instanceof BlockItem blockItem) {
            blockItem.registerBlocks(Item.BY_BLOCK, item);
        }

        return Registry.register(BuiltInRegistries.ITEM, key, item);
    }

    public static ResourceKey<Item> createItem(String name) {
        // Create the item key.
        return ResourceKey.create(Registries.ITEM, Q.of(name));
    }


    // <editor-fold desc="Allowed Folding">
    // FORBIDDEN
    public static final Block ALLOWED_GOLD_BLOCK = Q.register(
            "allowed_gold_block",
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.GOLD)
                    .instrument(NoteBlockInstrument.BELL)
                    .requiresCorrectToolForDrops()
                    .strength(3.0F, 6.0F)
                    .sound(SoundType.METAL)
    );
    public static final Block ALLOWED_COPPER_BLOCK = Q.register(
            "allowed_copper_block",
            p -> new WeatheringCopperFullBlock(WeatheringCopper.WeatherState.UNAFFECTED, p),
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_ORANGE)
                    .requiresCorrectToolForDrops()
                    .strength(3.0F, 6.0F)
                    .instrument(NoteBlockInstrument.TRUMPET)
                    .sound(SoundType.COPPER)
    );
    public static final Block ALLOWED_EXPOSED_COPPER = Q.register(
            "allowed_exposed_copper",
            p -> new WeatheringCopperFullBlock(WeatheringCopper.WeatherState.EXPOSED, p),
            BlockBehaviour.Properties.ofFullCopy(ALLOWED_COPPER_BLOCK).instrument(NoteBlockInstrument.TRUMPET_EXPOSED).mapColor(MapColor.TERRACOTTA_LIGHT_GRAY)
    );
    public static final Block ALLOWED_WEATHERED_COPPER = Q.register(
            "allowed_weathered_copper",
            p -> new WeatheringCopperFullBlock(WeatheringCopper.WeatherState.WEATHERED, p),
            BlockBehaviour.Properties.ofFullCopy(ALLOWED_COPPER_BLOCK).instrument(NoteBlockInstrument.TRUMPET_WEATHERED).mapColor(MapColor.WARPED_STEM)
    );
    public static final Block ALLOWED_OXIDIZED_COPPER = Q.register(
            "allowed_oxidized_copper",
            p -> new WeatheringCopperFullBlock(WeatheringCopper.WeatherState.OXIDIZED, p),
            BlockBehaviour.Properties.ofFullCopy(ALLOWED_COPPER_BLOCK).instrument(NoteBlockInstrument.TRUMPET_OXIDIZED).mapColor(MapColor.WARPED_NYLIUM)
    );
    public static final Block ALLOWED_OXIDIZED_CUT_COPPER = Q.register(
            "allowed_oxidized_cut_copper", p -> new WeatheringCopperFullBlock(WeatheringCopper.WeatherState.OXIDIZED, p), BlockBehaviour.Properties.ofFullCopy(ALLOWED_OXIDIZED_COPPER)
    );
    public static final Block ALLOWED_WEATHERED_CUT_COPPER = Q.register(
            "allowed_weathered_cut_copper",
            p -> new WeatheringCopperFullBlock(WeatheringCopper.WeatherState.WEATHERED, p),
            BlockBehaviour.Properties.ofFullCopy(ALLOWED_WEATHERED_COPPER)
    );
    public static final Block ALLOWED_EXPOSED_CUT_COPPER = Q.register(
            "allowed_exposed_cut_copper", p -> new WeatheringCopperFullBlock(WeatheringCopper.WeatherState.EXPOSED, p), BlockBehaviour.Properties.ofFullCopy(ALLOWED_EXPOSED_COPPER)
    );
    public static final Block ALLOWED_CUT_COPPER = Q.register(
            "allowed_cut_copper", p -> new WeatheringCopperFullBlock(WeatheringCopper.WeatherState.UNAFFECTED, p), BlockBehaviour.Properties.ofFullCopy(ALLOWED_COPPER_BLOCK)
    );
    public static final Block ALLOWED_OXIDIZED_CHISELED_COPPER = Q.register(
            "allowed_oxidized_chiseled_copper",
            p -> new WeatheringCopperFullBlock(WeatheringCopper.WeatherState.OXIDIZED, p),
            BlockBehaviour.Properties.ofFullCopy(ALLOWED_OXIDIZED_COPPER)
    );
    public static final Block ALLOWED_WEATHERED_CHISELED_COPPER = Q.register(
            "allowed_weathered_chiseled_copper",
            p -> new WeatheringCopperFullBlock(WeatheringCopper.WeatherState.WEATHERED, p),
            BlockBehaviour.Properties.ofFullCopy(ALLOWED_WEATHERED_COPPER)
    );
    public static final Block ALLOWED_EXPOSED_CHISELED_COPPER = Q.register(
            "allowed_exposed_chiseled_copper", p -> new WeatheringCopperFullBlock(WeatheringCopper.WeatherState.EXPOSED, p), BlockBehaviour.Properties.ofFullCopy(ALLOWED_EXPOSED_COPPER)
    );
    public static final Block ALLOWED_CHISELED_COPPER = Q.register(
            "allowed_chiseled_copper", p -> new WeatheringCopperFullBlock(WeatheringCopper.WeatherState.UNAFFECTED, p), BlockBehaviour.Properties.ofFullCopy(ALLOWED_COPPER_BLOCK)
    );
    public static final Block ALLOWED_WAXED_OXIDIZED_CHISELED_COPPER = Q.register(
            "allowed_waxed_oxidized_chiseled_copper", BlockBehaviour.Properties.ofFullCopy(ALLOWED_OXIDIZED_CHISELED_COPPER)
    );
    public static final Block ALLOWED_WAXED_WEATHERED_CHISELED_COPPER = Q.register(
            "allowed_waxed_weathered_chiseled_copper", BlockBehaviour.Properties.ofFullCopy(ALLOWED_WEATHERED_CHISELED_COPPER)
    );
    public static final Block ALLOWED_WAXED_EXPOSED_CHISELED_COPPER = Q.register(
            "allowed_waxed_exposed_chiseled_copper", BlockBehaviour.Properties.ofFullCopy(ALLOWED_EXPOSED_CHISELED_COPPER)
    );
    public static final Block ALLOWED_WAXED_CHISELED_COPPER = Q.register("allowed_waxed_chiseled_copper", BlockBehaviour.Properties.ofFullCopy(ALLOWED_CHISELED_COPPER));
    public static final Block ALLOWED_OXIDIZED_CUT_COPPER_STAIRS = Q.register(
            "allowed_oxidized_cut_copper_stairs",
            p -> new WeatheringCopperStairBlock(WeatheringCopper.WeatherState.OXIDIZED, ALLOWED_OXIDIZED_CUT_COPPER.defaultBlockState(), p),
            BlockBehaviour.Properties.ofFullCopy(ALLOWED_OXIDIZED_CUT_COPPER)
    );
    public static final Block ALLOWED_WEATHERED_CUT_COPPER_STAIRS = Q.register(
            "allowed_weathered_cut_copper_stairs",
            p -> new WeatheringCopperStairBlock(WeatheringCopper.WeatherState.WEATHERED, ALLOWED_WEATHERED_CUT_COPPER.defaultBlockState(), p),
            BlockBehaviour.Properties.ofFullCopy(ALLOWED_WEATHERED_COPPER)
    );
    public static final Block ALLOWED_EXPOSED_CUT_COPPER_STAIRS = Q.register(
            "allowed_exposed_cut_copper_stairs",
            p -> new WeatheringCopperStairBlock(WeatheringCopper.WeatherState.EXPOSED, ALLOWED_EXPOSED_CUT_COPPER.defaultBlockState(), p),
            BlockBehaviour.Properties.ofFullCopy(ALLOWED_EXPOSED_COPPER)
    );
    public static final Block ALLOWED_CUT_COPPER_STAIRS = Q.register(
            "allowed_cut_copper_stairs",
            p -> new WeatheringCopperStairBlock(WeatheringCopper.WeatherState.UNAFFECTED, ALLOWED_CUT_COPPER.defaultBlockState(), p),
            BlockBehaviour.Properties.ofFullCopy(ALLOWED_COPPER_BLOCK)
    );
    public static final Block ALLOWED_OXIDIZED_CUT_COPPER_SLAB = Q.register(
            "allowed_oxidized_cut_copper_slab",
            p -> new WeatheringCopperSlabBlock(WeatheringCopper.WeatherState.OXIDIZED, p),
            BlockBehaviour.Properties.ofFullCopy(ALLOWED_OXIDIZED_CUT_COPPER)
    );
    public static final Block ALLOWED_WEATHERED_CUT_COPPER_SLAB = Q.register(
            "allowed_weathered_cut_copper_slab",
            p -> new WeatheringCopperSlabBlock(WeatheringCopper.WeatherState.WEATHERED, p),
            BlockBehaviour.Properties.ofFullCopy(ALLOWED_WEATHERED_CUT_COPPER)
    );
    public static final Block ALLOWED_EXPOSED_CUT_COPPER_SLAB = Q.register(
            "allowed_exposed_cut_copper_slab",
            p -> new WeatheringCopperSlabBlock(WeatheringCopper.WeatherState.EXPOSED, p),
            BlockBehaviour.Properties.ofFullCopy(ALLOWED_EXPOSED_CUT_COPPER)
    );
    public static final Block ALLOWED_CUT_COPPER_SLAB = Q.register(
            "allowed_cut_copper_slab", p -> new WeatheringCopperSlabBlock(WeatheringCopper.WeatherState.UNAFFECTED, p), BlockBehaviour.Properties.ofFullCopy(ALLOWED_CUT_COPPER)
    );
    public static final Block ALLOWED_WAXED_COPPER_BLOCK = Q.register("allowed_waxed_copper_block", BlockBehaviour.Properties.ofFullCopy(ALLOWED_COPPER_BLOCK));
    public static final Block ALLOWED_WAXED_WEATHERED_COPPER = Q.register("allowed_waxed_weathered_copper", BlockBehaviour.Properties.ofFullCopy(ALLOWED_WEATHERED_COPPER));
    public static final Block ALLOWED_WAXED_EXPOSED_COPPER = Q.register("allowed_waxed_exposed_copper", BlockBehaviour.Properties.ofFullCopy(ALLOWED_EXPOSED_COPPER));
    public static final Block ALLOWED_WAXED_OXIDIZED_COPPER = Q.register("allowed_waxed_oxidized_copper", BlockBehaviour.Properties.ofFullCopy(ALLOWED_OXIDIZED_COPPER));
    public static final Block ALLOWED_WAXED_OXIDIZED_CUT_COPPER = Q.register("allowed_waxed_oxidized_cut_copper", BlockBehaviour.Properties.ofFullCopy(ALLOWED_OXIDIZED_COPPER));
    public static final Block ALLOWED_WAXED_WEATHERED_CUT_COPPER = Q.register("allowed_waxed_weathered_cut_copper", BlockBehaviour.Properties.ofFullCopy(ALLOWED_WEATHERED_COPPER));
    public static final Block ALLOWED_WAXED_EXPOSED_CUT_COPPER = Q.register("allowed_waxed_exposed_cut_copper", BlockBehaviour.Properties.ofFullCopy(ALLOWED_EXPOSED_COPPER));
    public static final Block ALLOWED_WAXED_CUT_COPPER = Q.register("allowed_waxed_cut_copper", BlockBehaviour.Properties.ofFullCopy(ALLOWED_COPPER_BLOCK));
    public static final Block ALLOWED_WAXED_OXIDIZED_CUT_COPPER_STAIRS = Q.registerStair("allowed_waxed_oxidized_cut_copper_stairs", ALLOWED_WAXED_OXIDIZED_CUT_COPPER);
    public static final Block ALLOWED_WAXED_WEATHERED_CUT_COPPER_STAIRS = Q.registerStair("allowed_waxed_weathered_cut_copper_stairs", ALLOWED_WAXED_WEATHERED_CUT_COPPER);
    public static final Block ALLOWED_WAXED_EXPOSED_CUT_COPPER_STAIRS = Q.registerStair("allowed_waxed_exposed_cut_copper_stairs", ALLOWED_WAXED_EXPOSED_CUT_COPPER);
    public static final Block ALLOWED_WAXED_CUT_COPPER_STAIRS = Q.registerStair("allowed_waxed_cut_copper_stairs", ALLOWED_WAXED_CUT_COPPER);
    public static final Block ALLOWED_WAXED_OXIDIZED_CUT_COPPER_SLAB = Q.register(
            "allowed_waxed_oxidized_cut_copper_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(ALLOWED_WAXED_OXIDIZED_CUT_COPPER).requiresCorrectToolForDrops()
    );
    public static final Block ALLOWED_WAXED_WEATHERED_CUT_COPPER_SLAB = Q.register(
            "allowed_waxed_weathered_cut_copper_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(ALLOWED_WAXED_WEATHERED_CUT_COPPER).requiresCorrectToolForDrops()
    );
    public static final Block ALLOWED_WAXED_EXPOSED_CUT_COPPER_SLAB = Q.register(
            "allowed_waxed_exposed_cut_copper_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(ALLOWED_WAXED_EXPOSED_CUT_COPPER).requiresCorrectToolForDrops()
    );
    public static final Block ALLOWED_WAXED_CUT_COPPER_SLAB = Q.register(
            "allowed_waxed_cut_copper_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(ALLOWED_WAXED_CUT_COPPER).requiresCorrectToolForDrops()
    );

    //</editor-fold>

    public static void registerBlock() {}

}
