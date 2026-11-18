package net.quedoom.francium.init;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.quedoom.francium.Francium;
import net.quedoom.francium.block.entity.*;
import net.quedoom.quet.init.QueTBlockEntity;

public class ModBlockEntities {
    private static final QueTBlockEntity Q = new QueTBlockEntity(Francium.MOD_ID);

    public static final BlockEntityType<DeepMergerEntity> DEEP_MERGER =
            Q.register("deep_merger", DeepMergerEntity::new, ModBlocks.DEEP_MERGER);

    public static final BlockEntityType<GlueMixerEntity> GLUE_MIXER =
            Q.register("glue_mixer", GlueMixerEntity::new, ModBlocks.GLUE_MIXER);

    public static final BlockEntityType<ObsidianMixerEntity> OBSIDIAN_MIXER =
            Q.register("obsidian_mixer", ObsidianMixerEntity::new, ModBlocks.OBSIDIAN_MIXER);

    public static final BlockEntityType<BlockContainingEntity> BLOCK_CONTAINING =
            Q.register("block_containing_block", BlockContainingEntity::new, ModBlocks.WOODEN_CASING_CONTAINING_BLOCK, ModBlocks.STONE_CASING_CONTAINING_BLOCK, ModBlocks.OBSIDIAN_CASING_CONTAINING_BLOCK);
    public static final BlockEntityType<BlockContainingItemsEntity> BLOCK_CONTAINING_ITEMS =
            Q.register("block_containing_items", BlockContainingItemsEntity::new, ModBlocks.WOODEN_CASING_CONTAINING_ITEMS, ModBlocks.STONE_CASING_CONTAINING_ITEMS, ModBlocks.OBSIDIAN_CASING_CONTAINING_ITEMS);

    public static final BlockEntityType<BundleTableEntity> BUNDLE_TABLE =
            Q.register("bundle_table", BundleTableEntity::new, ModBlocks.BUNDLE_TABLE);

    public static final BlockEntityType<TraderBenchEntity> TRADER_BENCH =
            Q.register("trader_bench", TraderBenchEntity::new, ModBlocks.TRADER_BENCH);

    public static final BlockEntityType<SawedBlockEntity> SAWED_BLOCK =
            Q.register("sawed_block", SawedBlockEntity::new, ModBlocks.SAWED_BLOCK);

    public static final BlockEntityType<BellowBlockEntity> BELLOW =
            Q.register("bellow", BellowBlockEntity::new, ModBlocks.BELLOW);

    public static final BlockEntityType<StokedFireEntity> STOKED_FIRE =
            Q.register("stoked_fire", StokedFireEntity::new, ModBlocks.STOKED_FIRE);

    public static final BlockEntityType<FanBlockEntity> FAN =
            Q.register("fan", FanBlockEntity::new, ModBlocks.FAN);

    public static final BlockEntityType<BedrockPlateauEntity> BEDROCK_PLATEAU =
            Q.register("bedrock_plateau", BedrockPlateauEntity::new, ModBlocks.BEDROCK_PLATEAU);

    
    public static void registerBlockEntities() {}

}
