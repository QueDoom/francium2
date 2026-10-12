package net.quedoom.francium.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.quedoom.francium.init.ModBlockEntities;

public class ObsidianMixerEntity extends BlockEntity {
    public ObsidianMixerEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.OBSIDIAN_MIXER_ENTITY, worldPosition, blockState);
    }


}
