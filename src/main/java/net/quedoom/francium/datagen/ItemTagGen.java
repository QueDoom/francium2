package net.quedoom.francium.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.quedoom.francium.init.FranciumConventionalTags;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.francium.init.ModItems;
import net.quedoom.francium.init.ModTags;

import java.util.concurrent.CompletableFuture;

public class ItemTagGen extends FabricTagsProvider.ItemTagsProvider {

    public ItemTagGen(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        // region conventional
        valueLookupBuilder(FranciumConventionalTags.Items.TUFF_COBBLESTONES)
                .add(
                        ModBlocks.COBBLED_TUFF.asItem()
                );
        valueLookupBuilder(FranciumConventionalTags.Items.COBBLESTONES)
                .addOptionalTag(FranciumConventionalTags.Items.TUFF_COBBLESTONES);

        valueLookupBuilder(ConventionalItemTags.COPPER_RAW_MATERIALS)
                .add(
                        ModItems.COPPER_DUST,
                        ModItems.DEEPSLATE_COPPER_ORE_PILE
                );
        valueLookupBuilder(ConventionalItemTags.IRON_RAW_MATERIALS)
                .add(
                        ModItems.IRON_ORE_PILE,
                        ModItems.DEEPSLATE_IRON_ORE_PILE
                );
        valueLookupBuilder(ConventionalItemTags.COPPER_RAW_MATERIALS)
                .add(
                        ModItems.COPPER_ORE_PILE,
                        ModItems.DEEPSLATE_COPPER_ORE_PILE
                );
        valueLookupBuilder(FranciumConventionalTags.Items.STEEL_DUSTS)
                .add(
                        ModItems.STEEL_DUST
                );
        valueLookupBuilder(FranciumConventionalTags.Items.STEEL_INGOTS)
                .add(
                        ModItems.STEEL_INGOT
                );
        valueLookupBuilder(FranciumConventionalTags.Items.RAW_DIAMONDS)
                .add(
                        ModItems.DIAMOND_ORE_PILE,
                        ModItems.DEEPSLATE_DIAMOND_ORE_PILE
                );
        valueLookupBuilder(FranciumConventionalTags.Items.RAW_MATERIALS)
                .addOptionalTag(FranciumConventionalTags.Items.RAW_DIAMONDS);


        // endregion conventional
        valueLookupBuilder(ModTags.Items.OFF_BLUE_DYES)
                .add(
                        ModItems.OFF_BLUE_DYE,
                        ModItems.ANOTHER_OFF_BLUE_DYE
                );
        valueLookupBuilder(ModTags.Items.STEEL_TOOL_MATERIALS)
                .addOptionalTag(ConventionalItemTags.INGOTS);
        valueLookupBuilder(ModTags.Items.CLEAVERS)
                .add(
                    ModItems.STEEL_CLEAVER,
                    ModItems.BEDROCK_CLEAVER
                );
        valueLookupBuilder(ModTags.Items.ITEMS_TO_THICKABLE_CROPS)
                .add(
                        Items.POTATO,
                        Items.CARROT,
                        Items.BEETROOT
                );
        valueLookupBuilder(ItemTags.SHOVELS)
                .add(
                        ModItems.SHARP_STICK,
                        ModItems.SHARP_ROCK,
                        ModItems.SHARP_AMETHYST_ROCK,
                        ModItems.SHARP_DEEPSLATE_ROCK,
                        ModItems.SHARP_OBSIDIAN_ROCK,
                        ModItems.SHARP_BEDROCK_ROCK
                );

        valueLookupBuilder(ItemTags.PICKAXES)
                .add(
                        ModItems.SHARP_STICK,
                        ModItems.SHARP_ROCK,
                        ModItems.SHARP_AMETHYST_ROCK,
                        ModItems.SHARP_DEEPSLATE_ROCK,
                        ModItems.SHARP_OBSIDIAN_ROCK,
                        ModItems.SHARP_BEDROCK_ROCK
                );

        valueLookupBuilder(ItemTags.AXES)
                .add(
                        ModItems.SHARP_STICK,
                        ModItems.SHARP_ROCK,
                        ModItems.SHARP_AMETHYST_ROCK,
                        ModItems.SHARP_DEEPSLATE_ROCK,
                        ModItems.SHARP_OBSIDIAN_ROCK,
                        ModItems.SHARP_BEDROCK_ROCK
                );

        valueLookupBuilder(ModTags.Items.NO_HOE_MULTITOOL)
                .add(
                        ModItems.SHARP_STICK,
                        ModItems.SHARP_ROCK
                );

        valueLookupBuilder(ModTags.Items.WOODEN_MERGER_GLUE)
                .add(ModItems.GLUE)
                .add(ModItems.VEGAN_GLUE)
                .add(ModItems.WOODEN_PLATE)
        ;

        valueLookupBuilder(ModTags.Items.DEEP_MERGER_GLUE)
                .addTag(ModTags.Items.WOODEN_MERGER_GLUE)
                .add(ModItems.ECHO_GLUE)
                .add(ModItems.SUPER_GLUE)
        ;

        valueLookupBuilder(ModTags.Items.SMALL_VEGETATION)
                .add(ModItems.LEAF)
                .add(ModItems.GRASS)
                .add(Items.SHORT_GRASS);

        valueLookupBuilder(ModTags.Items.BIG_VEGETATION)
                .addOptionalTag(ItemTags.LEAVES);

        valueLookupBuilder(ModTags.Items.SMALL_ECHO)
                .add(Items.ECHO_SHARD);

        valueLookupBuilder(ModTags.Items.BIG_ECHO)
                .add(ModBlocks.ECHO_BLOCK.asItem());

        valueLookupBuilder(ModTags.Items.AMETHYST_ROCK_MATERIALS)
                .add(
                        Items.AMETHYST_BLOCK,
                        Items.AMETHYST_SHARD,
                        Items.AMETHYST_CLUSTER
                );
        valueLookupBuilder(ModTags.Items.OBSIDIAN_ROCK_MATERIALS)
                .add(
                        Items.OBSIDIAN
                );

        valueLookupBuilder(ModTags.Items.BEDROCK_ROCK_MATERIALS)
                .add(
                        ModItems.BEDROCK_PEBBLES,
                        ModItems.BEDROCK_FLAKE
                );

        valueLookupBuilder(ModTags.Items.LEAF_ITEMS)
                .add(ModItems.LEAF)
                .addOptionalTag(ItemTags.LEAVES);

        valueLookupBuilder(ModTags.Items.ECHO_ITEMS)
                .add(
                        Items.ECHO_SHARD,
                        ModBlocks.ECHO_BLOCK.asItem()
                );

        valueLookupBuilder(ModTags.Items.PACKED_BLOCKS)
                .add(
                        ModItems.PACKED_CALCITE,
                        ModItems.PACKED_BASALT,
                        ModItems.PACKED_DEEPSLATE,
                        ModItems.PACKED_NETHERRACK,
                        ModItems.PACKED_DIRT,
                        ModItems.PACKED_PLANKS
                );
        valueLookupBuilder(ModTags.Items.MAKES_PAPER)
                .add(
                        ModItems.SAWDUST,
                        ModItems.BARK,
                        ModItems.HUSK
                );


    }
}
