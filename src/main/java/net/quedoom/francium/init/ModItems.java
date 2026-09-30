package net.quedoom.francium.init;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.quedoom.francium.Francium;
import net.quedoom.francium.item.*;
import net.quedoom.quet.init.QueTItem;

import java.util.function.BiFunction;
import java.util.function.Function;

public class ModItems {
    public static QueTItem Q = new QueTItem(Francium.MOD_ID);
    
    public static Item DIRT_PILE = Q.register("dirt_pile", false);
    public static Item BROKEN_STICK = Q.register("broken_stick");
    public static Item SAWDUST = Q.register("sawdust");
    public static Item GRAVEL_PILE = Q.register("gravel_pile", false);
    public static Item SAND_PILE = Q.register("sand_pile", false);
    public static Item ANDESITE_PILE = Q.register("andesite_pile", false);
    public static Item DIORITE_PILE = Q.register("diorite_pile", false);
    public static Item GRANITE_PILE = Q.register("granite_pile", false);

    public static Item GOLD_ORE_PILE = Q.register("gold_ore_pile", false);
    public static Item DEEPSLATE_GOLD_ORE_PILE = Q.register("deepslate_gold_ore_pile", false);
    public static Item GOLD_DUST = Q.register("gold_dust");
    public static Item IRON_ORE_PILE = Q.register("iron_ore_pile", false);
    public static Item DEEPSLATE_IRON_ORE_PILE = Q.register("deepslate_iron_ore_pile", false);
    public static Item IRON_DUST = Q.register("iron_dust");
    public static Item COPPER_ORE_PILE = Q.register("copper_ore_pile", false);
    public static Item DEEPSLATE_COPPER_ORE_PILE = Q.register("deepslate_copper_ore_pile", false);
    public static Item COPPER_DUST = Q.register("copper_dust");
    public static Item DIAMOND_ORE_PILE = Q.register("diamond_ore_pile", false);
    public static Item DEEPSLATE_DIAMOND_ORE_PILE = Q.register("deepslate_diamond_ore_pile", false);
    public static Item DIAMOND_DUST = Q.register("diamond_dust");
    public static Item COAL_DUST = Q.register("coal_dust");

    public static Item DEEPSLATE_PILE = Q.register("deepslate_pile", false);
    public static Item TUFF_PILE = Q.register("tuff_pile", false);
    public static Item TUFF_ZONG = Q.register("tuff_zong");
    public static Item CALCITE_PILE = Q.register("calcite_pile", false);
    public static Item AMETHYST_PILE = Q.register("amethyst_pile", false);
    public static Item DRIPSTONE_PILE = Q.register("dripstone_pile", false);
    public static Item NETHERRACK_PILE = Q.register("netherrack_pile", false);
    public static Item BLACKSTONE_PILE = Q.register("blackstone_pile", false);
    public static Item SOUL_PILE = Q.register("soul_pile", false);
    public static Item BASALT_PILE = Q.register("basalt_pile", false);
    public static Item NETHER_GOLD_ORE_PILE = Q.register("nether_gold_ore_pile", false);
    public static Item NETHER_QUARTZ_ORE_PILE = Q.register("nether_quartz_ore_pile", false);
    public static Item NETHER_QUARTZ_DUST = Q.register("quartz_dust");
    public static Item ANCIENT_DUST = Q.register("ancient_dust");
    public static Item ANCIENT_BUN = Q.register("ancient_bun");
    public static Item NETHERITE_DUST = Q.register("netherite_dust");

    public static Item STEEL_IN_A_BOTTLE = Q.register("steel_in_a_bottle", SteelInABottleItem::new);
    public static Item STEEL_DUST = Q.register("steel_dust");

    public static Item SHARP_ROCK = Q.register("sharp_rock", new Item.Properties().tool(ModToolMaterials.SHARP_ROCK, ModTags.Blocks.SHARP_STICK_MINES_FAST, 1.4F, 0.9F, 0F));
    public static Item ROCK = Q.register(Q.create("rock"), p -> new RockItem(p, SHARP_ROCK), new Item.Properties());
    public static Item SHARP_DEEPSLATE_ROCK = Q.register("sharp_deepslate_rock", new Item.Properties().tool(ModToolMaterials.SHARP_DEEPSLATE_ROCK, ModTags.Blocks.SHARP_STICK_MINES_FAST, 1.4F, 0.9F, 0F));
    public static Item DEEPSLATE_ROCK = Q.register(Q.create("deepslate_rock"), p -> new RockItem(p, SHARP_DEEPSLATE_ROCK), new Item.Properties());
    public static Item SHARP_AMETHYST_ROCK = Q.register("sharp_amethyst_rock", new Item.Properties().tool(ModToolMaterials.SHARP_AMETHYST_ROCK, ModTags.Blocks.SHARP_STICK_MINES_FAST, 1.4F, 0.9F, 0F));
    public static Item AMETHYST_ROCK = Q.register(Q.create("amethyst_rock"), p -> new RockItem(p, SHARP_AMETHYST_ROCK), new Item.Properties());
    public static Item SHARP_OBSIDIAN_ROCK = Q.register("sharp_obsidian_rock", new Item.Properties().tool(ModToolMaterials.SHARP_OBSIDIAN_ROCK, ModTags.Blocks.SHARP_STICK_MINES_FAST, 1.4F, 0.9F, 0F));
    public static Item OBSIDIAN_ROCK = Q.register(Q.create("obsidian_rock"), p -> new RockItem(p, SHARP_OBSIDIAN_ROCK), new Item.Properties());
    public static Item SHARP_BEDROCK_ROCK = Q.register("sharp_bedrock_rock", new Item.Properties().tool(ModToolMaterials.SHARP_BEDROCK_ROCK, ModTags.Blocks.SHARP_STICK_MINES_FAST, 1.4F, 0.9F, 0F));
    public static Item BEDROCK_ROCK = Q.register(Q.create("bedrock_rock"), p -> new RockItem(p, SHARP_BEDROCK_ROCK), new Item.Properties());

    public static Item TOOLBOX = Q.register("toolbox");

    public static Item GLUE_BOTTLE = Q.register("glue_bottle");
    public static Item GLUE = Q.register("glue");
    public static Item VEGAN_GLUE = Q.register("vegan_glue");
    public static Item SUPER_GLUE = Q.register("super_glue");
    public static Item ECHO_GLUE = Q.register("echo_glue");

    public static Item BEDROCK_PILE = Q.register("bedrock_pile", false);
    public static Item BEDROCK_PEBBLES = Q.register("bedrock_pebbles");
    public static Item BEDROCK_FLAKE = Q.register("bedrock_flake");

    public static Item BARK = Q.register("bark");
    public static Item LEAF = Q.register(Q.create("leaf"), LeafItem::new, new Item.Properties());
    public static Item GRASS = Q.register(Q.create("grass"));
    public static Item HUSK = Q.register(Q.create("husk"));

    public static Item WOODEN_PLATE = Q.register("wooden_plate");

    public static Item ANDESITE_ALLOY = Q.register("andesite_alloy");
    public static Item DIORITE_ALLOY = Q.register("diorite_alloy");
    public static Item GRANITE_ALLOY = Q.register("granite_alloy");

    public static Item ANDESITE_COATED_ROCK = Q.register("andesite_coated_rock");
    public static Item DIORITE_COATED_ROCK = Q.register("diorite_coated_rock");
    public static Item GRANITE_COATED_ROCK = Q.register("granite_coated_rock");

    public static Item MINERAL_MIX = Q.register("mineral_mix");
    public static Item DRIPSTONE_PASTE = Q.register("dripstone_paste");
    public static Item AMETHYST_PASTE = Q.register("amethyst_paste");
    public static Item OBSIDIAN_PASTE = Q.register("obsidian_paste");
    public static Item DRIPSTONE_COATED_MINERAL_MIX = Q.register("dripstone_coated_mineral_mix");
    public static Item AMETHYST_COATED_DIAMOND = Q.register("amethyst_coated_diamond");
    public static Item OBSIDIAN_INFUSED_DIAMOND = Q.register("obsidian_infused_diamond");

    public static Item CACTUS_PAPER = Q.register("cactus_paper");
    public static Item OBSIDIAN_BOOK = Q.register("obsidian_book");

    public static Item RAW_SLOT = Q.register("raw_slot");
    public static Item STACKED_RAW_SLOT = Q.register("stacked_raw_slot");
    public static Item SLOT = Q.register("slot");
    public static Item STACKED_SLOT = Q.register("stacked_slot");

    public static Item CRAFTING_TOKEN = Q.register("crafting_token", new Item.Properties().stacksTo(1));
    public static Item SMELTING_TOKEN = Q.register("smelting_token", new Item.Properties().stacksTo(1));
    public static Item SMITHING_TOKEN = Q.register("smithing_token", new Item.Properties().stacksTo(1));

    public static Item SHARP_STICK = Q.register("sharp_stick", new Item.Properties().tool(ModToolMaterials.SHARP_STICK, ModTags.Blocks.SHARP_STICK_MINES_FAST, 0.5F, 3F, 0F));
    public static Item WOODEN_SHEARS = Q.register(Q.create("wooden_shears"), ShearsItem::new, new Item.Properties().durability(283).component(DataComponents.TOOL, ShearsItem.createToolProperties()));
    public static Item FIRE_STARTER = Q.register(Q.create("fire_starter"), FireStarterItem::new, new Item.Properties().durability(127));
    public static Item GLASS_SHARDS = Q.register("glass_shards");

    public static Item DASH_ORB = Q.register("dash_orb", DashOrbItem::new);
    public static Item STEEL_BOWL = Q.register("steel_bowl");

    public static Item PACKED_CALCITE = Q.register("packed_calcite");
    public static Item PACKED_BASALT = Q.register("packed_basalt");
    public static Item PACKED_DEEPSLATE = Q.register("packed_deepslate");
    public static Item PACKED_NETHERRACK = Q.register("packed_netherrack");
    public static Item PACKED_DIRT = Q.register("packed_dirt");
    public static Item PACKED_PLANKS = Q.register("packed_planks");

    public static Item CHEWING_GUM = Q.register("chewing_gum");
    public static Item MICROPLASTIC = Q.register("microplastic");
    public static Item PLASTIC_SHEET = Q.register("plastic_sheet");
    public static Item PLASTIC_NECKLACE = Q.register("plastic_necklace");
    public static Item DENCHO_SHARD = Q.register("dencho_shard", new Item.Properties().rarity(Rarity.RARE));
    public static Item CORRUPTED_MODEL = Q.register("corrupted_model");

    public static Item FRYING_PAN = Q.register(Q.create("frying_pan"), p -> new RightClickCampfireItem(p, ModBlocks.FRYING_PAN_CAMPFIRE), new Item.Properties().sword(ToolMaterial.IRON, 6, 0.3f));
    public static Item POT = Q.register(Q.create("pot"), p -> new RightClickCampfireItem(p, ModBlocks.POT_CAMPFIRE), new Item.Properties().sword(ToolMaterial.IRON, 3, 0.25f));

    public static Item UNUSED_ITEM_BECAUSE_I_CANT_FIGURE_OUT_HOW_TO_MAKE_OPTIONAL_ITEMS_BECAUSE_IM_STUPID = Q.register("unused_item", new Item.Properties());

    public static void registerItem() {}

}
