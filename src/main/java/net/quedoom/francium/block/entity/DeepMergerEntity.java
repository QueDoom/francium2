package net.quedoom.francium.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.quedoom.francium.init.ModBlockEntities;

public class DeepMergerEntity extends BlockEntity {
    public DeepMergerEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.DEEP_MERGER_ENTITY, worldPosition, blockState);
    }
}
