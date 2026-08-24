package net.quedoom.francium.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.quedoom.francium.init.ModBlockEntities;

public class GlueMixerEntity extends BlockEntity  {
    public GlueMixerEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.GLUE_MIXER_ENTITY, worldPosition, blockState);
    }
}
