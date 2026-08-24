package net.quedoom.francium.init;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.block.Block;
import net.quedoom.francium.Francium;
import net.quedoom.francium.item.LeafItem;

import java.util.function.BiFunction;
import java.util.function.Function;

public class ModItems {

    public static Item DIRT_PILE = register("dirt_pile");
    public static Item BROKEN_STICK = register("broken_stick");
    public static Item SAWDUST = register("sawdust");
    public static Item GRAVEL_PILE = register("gravel_pile");
    public static Item ANDESITE_PILE = register("andesite_pile");
    public static Item DIORITE_PILE = register("diorite_pile");
    public static Item GRANITE_PILE = register("granite_pile");

    public static Item GOLD_ORE_PILE = register("gold_ore_pile");
    public static Item DEEPSLATE_GOLD_ORE_PILE = register("deepslate_gold_ore_pile");
    public static Item GOLD_DUST = register("gold_dust");
    public static Item IRON_ORE_PILE = register("iron_ore_pile");
    public static Item DEEPSLATE_IRON_ORE_PILE = register("deepslate_iron_ore_pile");
    public static Item IRON_DUST = register("iron_dust");
    public static Item COPPER_ORE_PILE = register("copper_ore_pile");
    public static Item DEEPSLATE_COPPER_ORE_PILE = register("deepslate_copper_ore_pile");
    public static Item COPPER_DUST = register("copper_dust");
    public static Item DIAMOND_ORE_PILE = register("diamond_ore_pile");
    public static Item DEEPSLATE_DIAMOND_ORE_PILE = register("deepslate_diamond_ore_pile");
    public static Item DIAMOND_DUST = register("diamond_dust");
    public static Item COAL_DUST = register("coal_dust");

    public static Item DEEPSLATE_PILE = register("deepslate_pile");
    public static Item TUFF_PILE = register("tuff_pile");
    public static Item CALCITE_PILE = register("calcite_pile");
    public static Item AMETHYST_PILE = register("amethyst_pile");
    public static Item DRIPSTONE_PILE = register("dripstone_pile");
    public static Item NETHERRACK_PILE = register("netherrack_pile");
    public static Item BLACKSTONE_PILE = register("blackstone_pile");
    public static Item SOUL_PILE = register("soul_pile");
    public static Item BASALT_PILE = register("basalt_pile");

    public static Item GLUE = register("glue");
    public static Item VEGAN_GLUE = register("vegan_glue");
    public static Item SUPER_GLUE = register("super_glue");
    public static Item ECHO_GLUE = register("echo_glue");

    public static Item BEDROCK_PILE = register("bedrock_pile");
    public static Item BEDROCK_PEBBLES = register("bedrock_pebbles");
    public static Item BEDROCK_FLAKE = register("bedrock_flake");

    public static Item BARK = register("bark");
    public static Item LEAF = register(create("leaf"), LeafItem::new, new Item.Properties());
    public static Item GRASS = register(create("grass"));

    public static Item WOODEN_PLATE = register("wooden_plate");

    public static Item ANDESITE_ALLOY = register("andesite_alloy");
    public static Item DIORITE_ALLOY = register("diorite_alloy");
    public static Item GRANITE_ALLOY = register("granite_alloy");

    public static Item MINERAL_MIX = register("mineral_mix");
    public static Item DRIPSTONE_PASTE = register("dripstone_paste");
    public static Item DRIPSTONE_COATED_MINERAL_MIX = register("dripstone_coated_mineral_mix");

    public static Item RAW_SLOT = register("raw_slot");
    public static Item STACKED_RAW_SLOT = register("stacked_raw_slot");
    public static Item SLOT = register("slot");
    public static Item STACKED_SLOT = register("stacked_slot");

    public static Item CRAFTING_TOKEN = register("crafting_token");
    public static Item SMELTING_TOKEN = register("smelting_token");
    public static Item SMITHING_TOKEN = register("smithing_token");

    public static Item SHARP_STICK = register("sharp_stick", new Item.Properties().tool(ModToolMaterials.SHARP_STICK, ModTags.Blocks.SHARP_STICK_MINES_FAST, 0.5F, 3F, 0F));

    public static Item UNUSED_ITEM_BECAUSE_I_CANT_FIGURE_OUT_HOW_TO_MAKE_OPTIONAL_ITEMS_BECAUSE_IM_STUPID = register("unused_item", new Item.Properties());


    public static Item register(String name) {
        return register(create(name), Item::new, new Item.Properties());
    }
    public static Item register(String name, Item.Properties properties) {
        return register(create(name), Item::new, properties);
    }
    public static Item register(ResourceKey<Item> key) {
        return register(key, Item::new, new Item.Properties());
    }

    public static Item register(ResourceKey<Item> key, Function<Item.Properties, Item> itemFactory, Item.Properties properties) {
        Item item = itemFactory.apply(properties.setId(key));
        if (item instanceof BlockItem blockItem) {
            blockItem.registerBlocks(Item.BY_BLOCK, item);
        }

        return Registry.register(BuiltInRegistries.ITEM, key, item);
    }

    public static ResourceKey<Item> create(String name) {
        // Create the item key.
        return ResourceKey.create(Registries.ITEM, Francium.id(name));
    }

    private static Item registerBlock(final Block block) {
        return registerBlock(block, BlockItem::new);
    }
    private static Item registerBlock(final Block block, final BiFunction<Block, Item.Properties, Item> itemFactory) {
        return registerBlock(block, itemFactory, new Item.Properties());
    }
    private static ResourceKey<Item> blockIdToItemId(final ResourceKey<Block> blockName) {
        return ResourceKey.create(Registries.ITEM, blockName.identifier());
    }
    private static Item registerBlock(final Block block, final BiFunction<Block, Item.Properties, Item> itemFactory, final Item.Properties properties) {
        return register(blockIdToItemId(block.properties().blockIdOrThrow()),
                (p) -> itemFactory.apply(block, p),
                properties.useBlockDescriptionPrefix().requiredFeatures(block.requiredFeatures()));
    }

    public static void registerItems() {

    }

}
