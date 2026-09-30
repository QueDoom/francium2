package net.quedoom.francium.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.quedoom.francium.Francium;
import net.quedoom.quet.init.ModRegistrator;
import net.quedoom.quet.init.QueTTag;

public class ModTags {
    public static class Blocks {
        private static final QueTTag.QTBlockTags Q = new QueTTag.QTBlockTags(Francium.MOD_ID);

        public static final TagKey<Block> STONE_ORES = Q.create("stone_ores");
        public static final TagKey<Block> DEEPSLATE_ORES = Q.create("deepslate_ores");
        public static final TagKey<Block> NETHER_ORES = Q.create("nether_ores");

        public static final TagKey<Block> FORCE_REQUIRE_TOOL = Q.create("force_require_tool");
        public static final TagKey<Block> FORCE_UNREQUIRE_TOOL = Q.create("force_unrequire_tool");

        public static final TagKey<Block> HARD_BLOCKS = Q.create("hard_block");

        public static final TagKey<Block> SHARP_STICK_MINES_FAST = Q.create("sharp_stick_mines_fast");

        public static final TagKey<Block> BLOCK_CONTAINING_BLOCK_COMPATIBLE = Q.create("block_containing_block_compatible");

        public static final TagKey<Block> DROPS_FORBIDDEN_DUST = Q.create("drops_forbidden_dust");
        public static final TagKey<Block> DROPS_FORBIDDEN_FLAKE = Q.create("drops_forbidden_flake");
        public static final TagKey<Block> SMALL_DROPS_FORBIDDEN_FLAKE = Q.create("small_drops_forbidden_flake");

        public static final TagKey<Block> TILLS = Q.create("tills");

        public static final TagKey<Block> TUFF_ROCKS_REPLACEABLE = Q.create("tuff_rocks_replaceable");

        public static final TagKey<Block> GROWS_INTO_THICK = Q.create("grows_into_thick");
    }
    public static class Items extends QueTTag.QTItemTags {
        static {
            ModRegistrator.setNamespace(Francium.CONSTANT_MOD_ID);
        }

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

        // jei
        public static final TagKey<Item> ECHO_ITEMS = Q.create("glue_mixer_jei_echo_items");
        public static final TagKey<Item> LEAF_ITEMS = Q.create("glue_mixer_jei_leaf_items");
    }

    public static class Entities extends QueTTag.QTEntityTags {

        public static final TagKey<EntityType<?>> DOES_NOT_DROP_SLIME = Q.create("does_not_drop_slime");

    }
}
