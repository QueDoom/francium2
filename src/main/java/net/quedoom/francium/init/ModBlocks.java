package net.quedoom.francium.init;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.quedoom.francium.Francium;
import net.quedoom.francium.block.*;

import java.util.function.Function;

public class ModBlocks {

    public static final Block GLUE_MIXER = register("glue_mixer", GlueMixerBlock::new, BlockBehaviour.Properties.of()
            .ignitedByLava().mapColor(Blocks.OAK_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).noOcclusion()
            .strength(2.5F, 3.5F).sound(SoundType.WOOD));
    public static final Block WOODEN_MERGER = register("wooden_merger", WoodenMergerBlock::new, BlockBehaviour.Properties.of()
            .ignitedByLava().mapColor(Blocks.OAK_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS)
            .strength(2.5F, 3.5F).sound(SoundType.WOOD));
    public static final Block DEEP_MERGER = register("deep_merger", DeepMergerBlock::new, BlockBehaviour.Properties.of()
            .noOcclusion().mapColor(Blocks.DRIPSTONE_BLOCK.defaultMapColor()).instrument(NoteBlockInstrument.BASS)
            .strength(4.5F, 3.5F).sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops());

    public static final Block WOODEN_CASING = register("wooden_casing", Block::new, BlockBehaviour.Properties.of()
            .noOcclusion().ignitedByLava().mapColor(Blocks.OAK_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS)
            .strength(2.0F, 3.0F).sound(SoundType.WOOD));

    public static final Block MINERAL_MIX_BLOCK = register("mineral_mix_block", Block::new, BlockBehaviour.Properties.of()
            .noOcclusion().mapColor(Blocks.ANDESITE.defaultMapColor()).instrument(NoteBlockInstrument.BASS).requiresCorrectToolForDrops()
            .strength(3.25F, 6.0F).sound(SoundType.STONE));

    public static final Block MINERAL_MIXED_WOODEN_CASING = register("mineral_mixed_wooden_casing", properties ->
            new TransformWhenBrokenBlock(properties, MINERAL_MIX_BLOCK.defaultBlockState()), BlockBehaviour.Properties.of()
            .mapColor(Blocks.OAK_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS)
            .strength(2.0F, 3.0F).sound(SoundType.WOOD));

    public static final Block STONE_CASING = register("stone_casing", Block::new, BlockBehaviour.Properties.of()
            .noOcclusion().mapColor(Blocks.OAK_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).requiresCorrectToolForDrops()
            .strength(1.5F, 6.0F).sound(SoundType.STONE));

    public static final Block OBSIDIAN_CASING = register("obsidian_casing", Block::new, BlockBehaviour.Properties.of()
            .noOcclusion().mapColor(Blocks.OAK_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops()
            .strength(50.0F, 1200.0F).sound(SoundType.STONE));

    public static final Block PILE_OF_LEAVES = register("pile_of_leaves", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES), false);

    public static final Block BLOCK_CONTAINING_WOODEN_CASING = register("block_containing_wooden_casing", BlockContainingBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noOcclusion().noLootTable(), false);

    public static final Block ECHO_BLOCK = register("echo_block", BlockBehaviour.Properties.ofFullCopy(WOODEN_CASING));




    private static Block register(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties, boolean shouldRegisterItem) {
        // Create a registry key for the block
        ResourceKey<Block> blockKey = keyOfBlock(name);
        // Create the block instance
        Block block = blockFactory.apply(properties.setId(blockKey));

        // Sometimes, you may not want to register an item for the block.
        // Eg: if it's a technical block like `minecraft:moving_piston` or `minecraft:end_gateway`
        if (shouldRegisterItem) {
            // Items need to be registered with a different type of registry key, but the ID
            // can be the same.
            ResourceKey<Item> itemKey = keyOfItem(name);

            BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(itemKey).useBlockDescriptionPrefix());
            Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
        }

        return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
    }
    private static Block register(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties) {
        // Create a registry key for the block
        ResourceKey<Block> blockKey = keyOfBlock(name);
        // Create the block instance
        Block block = blockFactory.apply(properties.setId(blockKey));

        ResourceKey<Item> itemKey = keyOfItem(name);

        BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(itemKey).useBlockDescriptionPrefix());
        Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);

        return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
    }

    private static Block register(String name, BlockBehaviour.Properties properies) {
        return register(name, Block::new, properies);
    }

    private static ResourceKey<Block> keyOfBlock(String name) {
        return ResourceKey.create(Registries.BLOCK, Francium.id(name));
    }

    private static ResourceKey<Item> keyOfItem(String name) {
        return ResourceKey.create(Registries.ITEM, Francium.id(name));
    }

    public static void registerBlocks() {

    }

}
