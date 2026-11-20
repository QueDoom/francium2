package net.quedoom.francium.init;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.quedoom.francium.Francium;
import net.quedoom.quet.init.QueTTag;

import java.util.ArrayList;
import java.util.List;

public class ModTags {
    private static final QueTTag BUILDER = QueTTag.builder(Francium.MOD_ID);

    public static class Blocks {
        private static final QueTTag.QTBlockTags Q = BUILDER.block();

        public static final TagKey<Block> STONE_ORES = Q.create("stone_ores");
        public static final TagKey<Block> DEEPSLATE_ORES = Q.create("deepslate_ores");
        public static final TagKey<Block> NETHER_ORES = Q.create("nether_ores");
        public static final TagKey<Block> TUFF_ORES = Q.create("tuff_ores");

        public static final TagKey<Block> FORCE_REQUIRE_TOOL = Q.create("force_require_tool");
        public static final TagKey<Block> FORCE_UNREQUIRE_TOOL = Q.create("force_unrequire_tool");

        public static final TagKey<Block> HARD_BLOCKS = Q.create("hard_block");

        public static final TagKey<Block> FAN_BLOWS_THROUGH = Q.create("fan_blows_through");

        public static final TagKey<Block> SHARP_STICK_MINES_FAST = Q.create("sharp_stick_mines_fast");

        public static final TagKey<Block> DROPS_FORBIDDEN_DUST = Q.create("drops_forbidden_dust");
        public static final TagKey<Block> DROPS_FORBIDDEN_FLAKE = Q.create("drops_forbidden_flake");
        public static final TagKey<Block> SMALL_DROPS_FORBIDDEN_FLAKE = Q.create("small_drops_forbidden_flake");

        public static final TagKey<Block> TILLS = Q.create("tills");

        public static final TagKey<Block> TUFF_ROCKS_REPLACEABLE = Q.create("tuff_rocks_replaceable");

        public static final TagKey<Block> GROWS_INTO_THICK = Q.create("grows_into_thick");

        public static final TagKey<Block> CRUSHED_BY_HEAVY_ANVIL = Q.create("crushed_by_heavy_anvil");

        public static final TagKey<Block> INCORRECT_FOR_BEDROCK_TOOLS = Q.create("incorrect_for_bedrock_tools");

        public static final TagKey<Block> THICKABLE_CROPS = Q.create("thickable_crops");
        public static final TagKey<Block> THICK_CROPS = Q.create("thick_crops");
        public static final TagKey<Block> THICK_CROP_FOLIAGE = Q.create("thick_crop_foliage");

        public static final TagKey<Block> OBSIDIAN_CHISEL_CAN_ALSO_BREAK = Q.create("obsidian_chisel_can_also_break");
    }
    public static class Items {
        private static final QueTTag.QTItemTags Q = BUILDER.item();

        public static final TagKey<Item> NO_HOE_MULTITOOL = Q.create("no_hoe_multitool");

        public static final TagKey<Item> WOODEN_MERGER_GLUE = Q.create("wooden_merger_glue");
        public static final TagKey<Item> DEEP_MERGER_GLUE = Q.create("deep_merger_glue");

        public static final TagKey<Item> SMALL_VEGETATION = Q.create("small_vegetation");
        public static final TagKey<Item> BIG_VEGETATION = Q.create("big_vegetation");

        public static final TagKey<Item> SMALL_ECHO = Q.create("small_echo");
        public static final TagKey<Item> BIG_ECHO = Q.create("big_echo");

        public static final TagKey<Item> AMETHYST_ROCK_MATERIALS = Q.create("amethyst_rock_materials");
        public static final TagKey<Item> OBSIDIAN_ROCK_MATERIALS = Q.create("obsidian_rock_materials");
        public static final TagKey<Item> BEDROCK_ROCK_MATERIALS = Q.create("deepslate_rock_materials");

        public static final TagKey<Item> PACKED_BLOCKS = Q.create("packed_blocks");

        public static final TagKey<Item> MAKES_PAPER = Q.create("makes_paper");

        public static final TagKey<Item> CLEAVERS = Q.create("cleavers");
        public static final TagKey<Item> ITEMS_TO_THICKABLE_CROPS = Q.create("items_to_thickable_crops");

        public static final TagKey<Item> STEEL_TOOL_MATERIALS = Q.create("steel_tool_materials");

        public static final TagKey<Item> OFF_BLUE_DYES = Q.create("off_blue_dyes");

        public static final TagKey<Item> LEVER_BASE = Q.create("lever_base");
        public static final TagKey<Item> LEVER_TOP = Q.create("lever_top");

        public static final TagKey<Item> FORBIDDEN_ITEMS = Q.create("foridden_items");

        // rrv
        public static final TagKey<Item> ECHO_ITEMS = Q.create("glue_mixer_rrv_echo_items");
        public static final TagKey<Item> LEAF_ITEMS = Q.create("glue_mixer_rrv_leaf_items");
    }

    public static class Entities {
        private static final QueTTag.QTEntityTags Q = BUILDER.entity();

        public static final TagKey<EntityType<?>> DOES_NOT_DROP_SLIME = Q.create("does_not_drop_slime");

    }

    public static class Biomes {
        public static final TagKey<Biome> ROCKS_SPAWN_IN = create("has_structure/rocks");


        private static TagKey<Biome> create(String name) {
            return TagKey.create(Registries.BIOME, Francium.of(name));
        }
    }

    public static List<Item> itemsInTag(TagKey<Item> tag) {
        List<Item> items = new ArrayList<>();
        for (Holder<Item> holder : BuiltInRegistries.ITEM.getTagOrEmpty(tag)) {
            items.add(holder.value());
        }
        return items;
    }

    public static List<Block> blocksInTag(TagKey<Block> tag) {
        List<Block> blocks = new ArrayList<>();
        for (Holder<Block> holder : BuiltInRegistries.BLOCK.getTagOrEmpty(tag)) {
            blocks.add(holder.value());
        }
        return blocks;
    }

}
