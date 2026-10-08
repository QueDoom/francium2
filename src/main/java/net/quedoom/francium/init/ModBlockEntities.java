package net.quedoom.francium.init;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.quedoom.francium.Francium;
import net.quedoom.francium.block.entity.*;
import net.quedoom.quet.init.QueTBlockEntity;

public class ModBlockEntities {
    private static final QueTBlockEntity Q = new QueTBlockEntity(Francium.MOD_ID);

    public static final BlockEntityType<DeepMergerEntity> DEEP_MERGER_ENTITY =
            Q.register("deep_merger", DeepMergerEntity::new, ModBlocks.DEEP_MERGER);

    public static final BlockEntityType<GlueMixerEntity> GLUE_MIXER_ENTITY =
            Q.register("glue_mixer", GlueMixerEntity::new, ModBlocks.GLUE_MIXER);

    public static final BlockEntityType<BlockContainingEntity> BLOCK_CONTAINING_ENTITY =
            Q.register("block_containing_block", BlockContainingEntity::new, ModBlocks.WOODEN_CASING_CONTAINING_BLOCK, ModBlocks.STONE_CASING_CONTAINING_BLOCK, ModBlocks.OBSIDIAN_CASING_CONTAINING_BLOCK);
    public static final BlockEntityType<BlockContainingItemsEntity> BLOCK_CONTAINING_ITEMS_ENTITY =
            Q.register("block_containing_items", BlockContainingItemsEntity::new, ModBlocks.WOODEN_CASING_CONTAINING_ITEMS, ModBlocks.STONE_CASING_CONTAINING_ITEMS, ModBlocks.OBSIDIAN_CASING_CONTAINING_ITEMS);

    public static final BlockEntityType<BundleTableEntity> BUNDLE_TABLE_ENTITY =
            Q.register("bundle_table", BundleTableEntity::new, ModBlocks.BUNDLE_TABLE);

    public static final BlockEntityType<TraderBenchEntity> TRADER_BENCH_ENTITY =
            Q.register("trader_bench", TraderBenchEntity::new, ModBlocks.TRADER_BENCH);

    public static final BlockEntityType<SawedBlockEntity> SAWED_BLOCK_ENTITY =
            Q.register("sawed_block", SawedBlockEntity::new, ModBlocks.SAWED_BLOCK);

    
    public static void registerBlockEntities() {}

}
