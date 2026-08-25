package net.quedoom.francium.init;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.quedoom.francium.Francium;
import net.quedoom.francium.block.BlockContainingBlock;
import net.quedoom.francium.block.entity.BlockContainingEntity;
import net.quedoom.francium.block.entity.DeepMergerEntity;
import net.quedoom.francium.block.entity.GlueMixerEntity;

public class ModBlockEntities {

    public static final BlockEntityType<DeepMergerEntity> DEEP_MERGER_ENTITY =
            register("deep_merger", DeepMergerEntity::new, ModBlocks.DEEP_MERGER);

    public static final BlockEntityType<GlueMixerEntity> GLUE_MIXER_ENTITY =
            register("glue_mixer", GlueMixerEntity::new, ModBlocks.GLUE_MIXER);

    public static final BlockEntityType<BlockContainingEntity> BLOCK_CONTAINING_ENTITY =
            register("block_containing_block", BlockContainingEntity::new, ModBlocks.BLOCK_CONTAINING_WOODEN_CASING);

    private static <T extends BlockEntity> BlockEntityType<T> register(
            String name,
            FabricBlockEntityTypeBuilder.Factory<? extends T> entityFactory,
            Block... blocks
    ) {
        Identifier id = Francium.id(name);
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, id, FabricBlockEntityTypeBuilder.<T>create(entityFactory, blocks).build());
    }

    public static void registerBlockEntities() {}

}
