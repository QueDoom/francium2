package net.quedoom.francium.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Item;
import net.quedoom.francium.Francium;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.francium.init.ModItems;
import net.quedoom.francium.init.ModTags;
import net.quedoom.quet.datagen.lang.QTTranslationBuilder;
import net.quedoom.quet.datagen.lang.QueTLanguageProvider;

import java.util.concurrent.CompletableFuture;

public class Francium2LanguageProvider extends QueTLanguageProvider {
    public Francium2LanguageProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(packOutput, registryLookup);
    }

    @Override
    protected String getNamespace() {
        return Francium.MOD_ID;
    }

    @Override
    public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder translationBuilder, QTTranslationBuilder qtTranslationBuilder) {
        pileTranslate(qtTranslationBuilder, ModItems.DIRT_PILE, "Dirt");
        pileTranslate(qtTranslationBuilder, ModItems.GRAVEL_PILE, "Gravel");
        pileTranslate(qtTranslationBuilder, ModItems.SAND_PILE, "Sand");
        pileTranslate(qtTranslationBuilder, ModItems.DIORITE_PILE, "Diorite");
        pileTranslate(qtTranslationBuilder, ModItems.GRANITE_PILE, "Granite");
        pileTranslate(qtTranslationBuilder, ModItems.ANDESITE_PILE, "Andesite");
        pileTranslate(qtTranslationBuilder, ModItems.TUFF_PILE, "Tuff");
        pileTranslate(qtTranslationBuilder, ModItems.GOLD_ORE_PILE, "Gold Ore");
        pileTranslate(qtTranslationBuilder, ModItems.DEEPSLATE_GOLD_ORE_PILE, "Deepslate Gold Ore");
        pileTranslate(qtTranslationBuilder, ModItems.IRON_ORE_PILE, "Iron Ore");
        pileTranslate(qtTranslationBuilder, ModItems.DEEPSLATE_IRON_ORE_PILE, "Deepslate Iron Ore");
        pileTranslate(qtTranslationBuilder, ModItems.COPPER_ORE_PILE, "Copper Ore");
        pileTranslate(qtTranslationBuilder, ModItems.DEEPSLATE_COPPER_ORE_PILE, "Deepslate Copper Ore");
        pileTranslate(qtTranslationBuilder, ModItems.DIAMOND_ORE_PILE, "Diamond Ore");
        pileTranslate(qtTranslationBuilder, ModItems.DEEPSLATE_DIAMOND_ORE_PILE, "Deepslate Diamond Ore");
        pileTranslate(qtTranslationBuilder, ModItems.DEEPSLATE_PILE, "Deepslate");
        pileTranslate(qtTranslationBuilder, ModItems.CALCITE_PILE, "Calcite");
        pileTranslate(qtTranslationBuilder, ModItems.AMETHYST_PILE, "Amethyst");
        pileTranslate(qtTranslationBuilder, ModItems.DRIPSTONE_PILE, "Dripstone");
        pileTranslate(qtTranslationBuilder, ModItems.NETHERRACK_PILE, "Netherrack");
        pileTranslate(qtTranslationBuilder, ModItems.BLACKSTONE_PILE, "Blackstone");
        pileTranslate(qtTranslationBuilder, ModItems.SOUL_PILE, "Soul");
        pileTranslate(qtTranslationBuilder, ModItems.BASALT_PILE, "Basalt");
        pileTranslate(qtTranslationBuilder, ModItems.NETHER_GOLD_ORE_PILE, "Nether Gold Ore");
        pileTranslate(qtTranslationBuilder, ModItems.NETHER_QUARTZ_ORE_PILE, "Nether Quartz Ore");

        qtTranslationBuilder.auto(ModBlocks.FORBIDDEN_DUST);
        qtTranslationBuilder.auto(ModBlocks.FORBIDDEN_FLAKE);
        qtTranslationBuilder.auto(ModBlocks.ANCIENT_BUNS);

        qtTranslationBuilder.auto(ModBlocks.POT_CAMPFIRE);
        qtTranslationBuilder.auto(ModBlocks.FRYING_PAN_CAMPFIRE);

        qtTranslationBuilder.auto(ModBlocks.RUBBER_BLOCK);

        pileTranslate(qtTranslationBuilder, ModItems.BEDROCK_PILE, "Bedrock");

        translationBuilder.add(ModBlocks.PILE_OF_LEAVES, "Leaf");

        translationBuilder.add(ModBlocks.GLUE_MIXER, "Glue Mixer");

        qtTranslationBuilder.auto(ModBlocks.WOODEN_MERGER);
        qtTranslationBuilder.auto(ModBlocks.DEEP_MERGER);
        qtTranslationBuilder.auto(ModBlocks.BUNDLE_TABLE);
        translationBuilder.add(ModBlocks.TRADER_BENCH, "Trader's Bench");

        qtTranslationBuilder.auto(ModBlocks.HEAVY_SCULK);

        qtTranslationBuilder.auto(ModBlocks.WOODEN_CASING);
        qtTranslationBuilder.auto(ModBlocks.WOODEN_CASING_CONTAINING_BLOCK);
        qtTranslationBuilder.auto(ModBlocks.STONE_CASING_CONTAINING_BLOCK);
        qtTranslationBuilder.auto(ModBlocks.MINERAL_MIXED_WOODEN_CASING);
        translationBuilder.add(ModBlocks.MINERAL_MIX_BLOCK, "Block of Mineral Mix");
        qtTranslationBuilder.auto(ModBlocks.STONE_CASING);
        qtTranslationBuilder.auto(ModBlocks.OBSIDIAN_CASING);
        qtTranslationBuilder.auto(ModBlocks.ECHO_BLOCK);
        qtTranslationBuilder.auto(ModBlocks.ECHO_BLOCK.asItem());
        qtTranslationBuilder.auto(ModBlocks.STRIPPED_SUGAR_CANE);
        qtTranslationBuilder.auto(ModBlocks.STRIPPED_BAMBOO);
        qtTranslationBuilder.auto(ModBlocks.STRIPPED_BAMBOO_ITEM);
        qtTranslationBuilder.auto(ModBlocks.THICK_POTATO);
        qtTranslationBuilder.auto(ModBlocks.THICK_POTATO_FOLIAGE);
        qtTranslationBuilder.auto(ModBlocks.THICK_CARROT);
        qtTranslationBuilder.auto(ModBlocks.THICK_CARROT_FOLIAGE);
        qtTranslationBuilder.auto(ModBlocks.THICK_BEETROOT);
        qtTranslationBuilder.auto(ModBlocks.THICK_BEETROOT_FOLIAGE);
        qtTranslationBuilder.auto(ModBlocks.THICK_APPLE);

        qtTranslationBuilder.auto(ModTags.Items.AMETHYST_ROCK_MATERIALS);
        qtTranslationBuilder.auto(ModTags.Items.BEDROCK_ROCK_MATERIALS);
        qtTranslationBuilder.auto(ModTags.Items.BIG_VEGETATION);
        qtTranslationBuilder.auto(ModTags.Items.DEEP_MERGER_GLUE);
        qtTranslationBuilder.auto(ModTags.Items.ECHO_ITEMS);
        qtTranslationBuilder.auto(ModTags.Items.LEAF_ITEMS);
        qtTranslationBuilder.auto(ModTags.Items.NO_HOE_MULTITOOL);
        qtTranslationBuilder.auto(ModTags.Items.OBSIDIAN_ROCK_MATERIALS);
        qtTranslationBuilder.auto(ModTags.Items.BIG_ECHO);
        qtTranslationBuilder.auto(ModTags.Items.SMALL_ECHO);
        qtTranslationBuilder.auto(ModTags.Items.SMALL_VEGETATION);
        qtTranslationBuilder.auto(ModTags.Items.WOODEN_MERGER_GLUE);

        qtTranslationBuilder.auto(ModTags.Entities.DOES_NOT_DROP_SLIME);

        qtTranslationBuilder.auto(ModTags.Blocks.BLOCK_CONTAINING_BLOCK_COMPATIBLE);
        qtTranslationBuilder.auto(ModTags.Blocks.DEEPSLATE_ORES);
        qtTranslationBuilder.auto(ModTags.Blocks.DROPS_FORBIDDEN_DUST);
        qtTranslationBuilder.auto(ModTags.Blocks.DROPS_FORBIDDEN_FLAKE);
        qtTranslationBuilder.auto(ModTags.Blocks.FORCE_REQUIRE_TOOL);
        qtTranslationBuilder.auto(ModTags.Blocks.HARD_BLOCKS);
        qtTranslationBuilder.auto(ModTags.Blocks.NETHER_ORES);
        qtTranslationBuilder.auto(ModTags.Blocks.SHARP_STICK_MINES_FAST);
        qtTranslationBuilder.auto(ModTags.Blocks.SMALL_DROPS_FORBIDDEN_FLAKE);
        qtTranslationBuilder.auto(ModTags.Blocks.STONE_ORES);
        qtTranslationBuilder.auto(ModTags.Blocks.TILLS);

        translationBuilder.add(Francium.translationString("rrv", "wooden_merging"), "Wooden Merging");
        translationBuilder.add(Francium.translationString("rrv", "deep_merging"), "Deep Merging");
        translationBuilder.add(Francium.translationString("rrv", "glue_mixing"), "Glue Mixing");

        translationBuilder.add(Francium.translationString("stackgroup", "piles"), "Piles");

        translationBuilder.add("menu.francium_2.merging", "Merging");
        translationBuilder.add("menu.francium_2.trader_bench", "Trading");
        translationBuilder.add("menu.francium_2.bundle_table", "Bundle Table");

        autoTranslateAdvancement(translationBuilder, "get_gravel_pile", "Acquire some gravel.");

        translationBuilder.add(ModItems.UNUSED_ITEM_BECAUSE_I_CANT_FIGURE_OUT_HOW_TO_MAKE_OPTIONAL_ITEMS_BECAUSE_IM_STUPID, "No item");

        qtTranslationBuilder.auto(ModBlocks.ALLOWED_COPPER_BLOCK);
        qtTranslationBuilder.auto(ModBlocks.ALLOWED_EXPOSED_COPPER);
        qtTranslationBuilder.auto(ModBlocks.ALLOWED_WEATHERED_COPPER);
        qtTranslationBuilder.auto(ModBlocks.ALLOWED_OXIDIZED_COPPER);
        qtTranslationBuilder.auto(ModBlocks.ALLOWED_OXIDIZED_CUT_COPPER);
        qtTranslationBuilder.auto(ModBlocks.ALLOWED_WEATHERED_CUT_COPPER);
        qtTranslationBuilder.auto(ModBlocks.ALLOWED_EXPOSED_CUT_COPPER);
        qtTranslationBuilder.auto(ModBlocks.ALLOWED_CUT_COPPER);
        qtTranslationBuilder.auto(ModBlocks.ALLOWED_OXIDIZED_CHISELED_COPPER);
        qtTranslationBuilder.auto(ModBlocks.ALLOWED_WEATHERED_CHISELED_COPPER);
        qtTranslationBuilder.auto(ModBlocks.ALLOWED_EXPOSED_CHISELED_COPPER);
        qtTranslationBuilder.auto(ModBlocks.ALLOWED_CHISELED_COPPER);
        qtTranslationBuilder.auto(ModBlocks.ALLOWED_WAXED_OXIDIZED_CHISELED_COPPER);
        qtTranslationBuilder.auto(ModBlocks.ALLOWED_WAXED_WEATHERED_CHISELED_COPPER);
        qtTranslationBuilder.auto(ModBlocks.ALLOWED_WAXED_EXPOSED_CHISELED_COPPER);
        qtTranslationBuilder.auto(ModBlocks.ALLOWED_WAXED_CHISELED_COPPER);
        qtTranslationBuilder.auto(ModBlocks.ALLOWED_OXIDIZED_CUT_COPPER_STAIRS);
        qtTranslationBuilder.auto(ModBlocks.ALLOWED_WEATHERED_CUT_COPPER_STAIRS);
        qtTranslationBuilder.auto(ModBlocks.ALLOWED_EXPOSED_CUT_COPPER_STAIRS);
        qtTranslationBuilder.auto(ModBlocks.ALLOWED_CUT_COPPER_STAIRS);
        qtTranslationBuilder.auto(ModBlocks.ALLOWED_OXIDIZED_CUT_COPPER_SLAB);
        qtTranslationBuilder.auto(ModBlocks.ALLOWED_WEATHERED_CUT_COPPER_SLAB);
        qtTranslationBuilder.auto(ModBlocks.ALLOWED_EXPOSED_CUT_COPPER_SLAB);
        qtTranslationBuilder.auto(ModBlocks.ALLOWED_CUT_COPPER_SLAB);
        qtTranslationBuilder.auto(ModBlocks.ALLOWED_WAXED_COPPER_BLOCK);
        qtTranslationBuilder.auto(ModBlocks.ALLOWED_WAXED_WEATHERED_COPPER);
        qtTranslationBuilder.auto(ModBlocks.ALLOWED_WAXED_EXPOSED_COPPER);
        qtTranslationBuilder.auto(ModBlocks.ALLOWED_WAXED_OXIDIZED_COPPER);
        qtTranslationBuilder.auto(ModBlocks.ALLOWED_WAXED_OXIDIZED_CUT_COPPER);
        qtTranslationBuilder.auto(ModBlocks.ALLOWED_WAXED_WEATHERED_CUT_COPPER);
        qtTranslationBuilder.auto(ModBlocks.ALLOWED_WAXED_EXPOSED_CUT_COPPER);
        qtTranslationBuilder.auto(ModBlocks.ALLOWED_WAXED_CUT_COPPER);
        qtTranslationBuilder.auto(ModBlocks.ALLOWED_WAXED_OXIDIZED_CUT_COPPER_STAIRS);
        qtTranslationBuilder.auto(ModBlocks.ALLOWED_WAXED_WEATHERED_CUT_COPPER_STAIRS);
        qtTranslationBuilder.auto(ModBlocks.ALLOWED_WAXED_EXPOSED_CUT_COPPER_STAIRS);
        qtTranslationBuilder.auto(ModBlocks.ALLOWED_WAXED_CUT_COPPER_STAIRS);
        qtTranslationBuilder.auto(ModBlocks.ALLOWED_WAXED_OXIDIZED_CUT_COPPER_SLAB);
        qtTranslationBuilder.auto(ModBlocks.ALLOWED_WAXED_WEATHERED_CUT_COPPER_SLAB);
        qtTranslationBuilder.auto(ModBlocks.ALLOWED_WAXED_EXPOSED_CUT_COPPER_SLAB);
        qtTranslationBuilder.auto(ModBlocks.ALLOWED_WAXED_CUT_COPPER_SLAB);
        qtTranslationBuilder.auto(ModBlocks.ALLOWED_GOLD_BLOCK);
    }


    private void advancementTranslate(TranslationBuilder translationBuilder, String advancementName, String type, String name) {
        translationBuilder.add(Francium.MOD_ID + "." + type + "." + advancementName, name);
    }

    private void advancementTitleTranslate(TranslationBuilder translationBuilder, String advancementName, String name) {
        advancementTranslate(translationBuilder, advancementName, "advancementTitle", name);
    }

    private void advancementDescriptionTranslate(TranslationBuilder translationBuilder, String advancementName, String name) {
        advancementTranslate(translationBuilder, advancementName, "advancementDescription", name);
    }

    private void autoTranslateAdvancement(TranslationBuilder translationBuilder, String advancement, String description) {
        advancementTitleTranslate(translationBuilder, advancement, QTTranslationBuilder.snakeToTitleCase(advancement));
        advancementDescriptionTranslate(translationBuilder, advancement, description);
    }

    private void pileTranslate(QTTranslationBuilder builder, Item item, String material) {
        builder.builder().add(item, "Pile Of " + material);
    }
}
