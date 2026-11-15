package net.quedoom.francium.init;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.quedoom.quet.init.QueTTag;

public class FranciumConventionalTags {
    private static final QueTTag TAGS = QueTTag.builder("c");

    public static class Items {
        private static final QueTTag.QTItemTags C = TAGS.item();
        // region block items
        public static final TagKey<Item> TUFF_COBBLESTONES = C.create("cobblestones/tuff");
        public static final TagKey<Item> COBBLESTONES = C.create("cobblestones");
        // endregion block items

        public static final TagKey<Item> STEEL_INGOTS = C.create("ingots/steel");
        public static final TagKey<Item> STEEL_DUSTS = C.create("dusts/steel");
        public static final TagKey<Item> RAW_DIAMONDS = C.create("raw_materials/diamond");
        public static final TagKey<Item> RAW_MATERIALS = C.create("raw_materials");
    }
    public static class Piles {
        public static final TagKey<Item> SAND = pile("sands/colorless");
        public static final TagKey<Item> RED_SAND = pile("sands/red");


        private static TagKey<Item> pile(String name) {
            return TAGS.item().create("piles/" + name);
        }
    }
    public static class Blocks {
        private static final QueTTag.QTBlockTags C = TAGS.block();

        public static final TagKey<Block> TUFF_COBBLESTONES = C.create("cobblestones/tuff");
        public static final TagKey<Block> COBBLESTONES = C.create("cobblestones");
    }

}
