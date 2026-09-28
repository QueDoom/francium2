package net.quedoom.francium.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.quedoom.francium.Francium;
import net.quedoom.quet.init.QueTTag;

public class ModTags {
    public static class Blocks extends QueTTag.QTBlockTags {

        public static final TagKey<Block> STONE_ORES = create("stone_ores");
        public static final TagKey<Block> DEEPSLATE_ORES = create("deepslate_ores");
        public static final TagKey<Block> NETHER_ORES = create("nether_ores");

        public static final TagKey<Block> FORCE_REQUIRE_TOOL = create("force_require_tool");
        public static final TagKey<Block> FORCE_UNREQUIRE_TOOL = create("force_unrequire_tool");

        public static final TagKey<Block> HARD_BLOCKS = create("hard_block");

        public static final TagKey<Block> SHARP_STICK_MINES_FAST = create("sharp_stick_mines_fast");

        public static final TagKey<Block> BLOCK_CONTAINING_BLOCK_COMPATIBLE = create("block_containing_block_compatible");

        public static final TagKey<Block> DROPS_FORBIDDEN_DUST = create("drops_forbidden_dust");
        public static final TagKey<Block> DROPS_FORBIDDEN_FLAKE = create("drops_forbidden_flake");
        public static final TagKey<Block> SMALL_DROPS_FORBIDDEN_FLAKE = create("small_drops_forbidden_flake");

        public static final TagKey<Block> TILLS = create("tills");

        public static final TagKey<Block> TUFF_ROCKS_REPLACEABLE = create("tuff_rocks_replaceable");
    }
    public static class Items extends QueTTag.QTItemTags {

        public static final TagKey<Item> NO_HOE_MULTITOOL = create("no_hoe_multitool");

        public static final TagKey<Item> WOODEN_MERGER_GLUE = create("wooden_merger_glue");
        public static final TagKey<Item> DEEP_MERGER_GLUE = create("deep_merger_glue");

        public static final TagKey<Item> SMALL_VEGETATION = create("small_vegetation");
        public static final TagKey<Item> BIG_VEGETATION = create("big_vegetation");

        public static final TagKey<Item> SMALL_ECHO = create("small_echo");
        public static final TagKey<Item> BIG_ECHO = create("big_echo");

        public static final TagKey<Item> AMETHYST_ROCK_MATERIALS = create("amethyst_rock_materials");
        public static final TagKey<Item> OBSIDIAN_ROCK_MATERIALS = create("obsidian_rock_materials");
        public static final TagKey<Item> BEDROCK_ROCK_MATERIALS = create("deepslate_rock_materials");

        public static final TagKey<Item> PACKED_BLOCKS = create("packed_blocks");

        // jei
        public static final TagKey<Item> ECHO_ITEMS = create("glue_mixer_jei_echo_items");
        public static final TagKey<Item> LEAF_ITEMS = create("glue_mixer_jei_leaf_items");
    }

    public static class Entities extends QueTTag.QTEntityTags {

        public static final TagKey<EntityType<?>> DOES_NOT_DROP_SLIME = create("does_not_drop_slime");

    }
}
