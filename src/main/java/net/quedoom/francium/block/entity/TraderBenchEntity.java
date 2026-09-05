package net.quedoom.francium.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.quedoom.francium.init.ModBlockEntities;

public class TraderBenchEntity extends BlockEntity {
    public TraderBenchEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.TRADER_BENCH_ENTITY, worldPosition, blockState);
    }
}
