package net.quedoom.francium.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.quedoom.francium.Francium;

public class ModTags {
    public static class Blocks {

        public static final TagKey<Block> STONE_ORES = createTag("stone_ores");
        public static final TagKey<Block> DEEPSLATE_ORES = createTag("deepslate_ores");
        public static final TagKey<Block> NETHER_ORES = createTag("nether_ores");

        public static final TagKey<Block> FORCE_REQUIRE_TOOL = createTag("force_require_tool");
        public static final TagKey<Block> FORCE_UNREQUIRE_TOOL = createTag("force_unrequire_tool");

        public static final TagKey<Block> HARD_BLOCKS = createTag("hard_block");

        public static final TagKey<Block> SHARP_STICK_MINES_FAST = createTag("sharp_stick_mines_fast");

        public static final TagKey<Block> BLOCK_CONTAINING_BLOCK_COMPATIBLE = createTag("block_containing_block_compatible");

        public static final TagKey<Block> DROPS_FORBIDDEN_DUST = createTag("drops_forbidden_dust");
        public static final TagKey<Block> DROPS_FORBIDDEN_FLAKE = createTag("drops_forbidden_flake");
        public static final TagKey<Block> SMALL_DROPS_FORBIDDEN_FLAKE = createTag("small_drops_forbidden_flake");

        public static final TagKey<Block> TILLS = createTag("tills");

        private static TagKey<Block> createTag(String name) {
            return TagKey.create(Registries.BLOCK, Francium.id(name));
        }
    }
    public static class Items {

        public static final TagKey<Item> NO_HOE_MULTITOOL = createTag("no_hoe_multitool");

        public static final TagKey<Item> WOODEN_MERGER_GLUE = createTag("wooden_merger_glue");
        public static final TagKey<Item> DEEP_MERGER_GLUE = createTag("deep_merger_glue");

        public static final TagKey<Item> SMALL_VEGETATION = createTag("small_vegetation");
        public static final TagKey<Item> BIG_VEGETATION = createTag("big_vegetation");

        public static final TagKey<Item> SMALL_ECHO = createTag("small_echo");
        public static final TagKey<Item> BIG_ECHO = createTag("big_echo");

        public static final TagKey<Item> AMETHYST_ROCK_MATERIALS = createTag("amethyst_rock_materials");
        public static final TagKey<Item> OBSIDIAN_ROCK_MATERIALS = createTag("obsidian_rock_materials");
        public static final TagKey<Item> BEDROCK_ROCK_MATERIALS = createTag("deepslate_rock_materials");

        private static TagKey<Item> createTag(String name) {
            return TagKey.create(Registries.ITEM, Francium.id(name));
        }
    }

    public static class Entities {

        public static final TagKey<EntityType<?>> DOES_NOT_DROP_SLIME = createTag("does_not_drop_slime");

        private static TagKey<EntityType<?>> createTag(String name) {
            return TagKey.create(Registries.ENTITY_TYPE, Francium.id(name));
        }
    }
}
