package net.quedoom.francium.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.quedoom.francium.api.prickle.FranciumConfigManager;
import net.quedoom.francium.block.BellowBlock;
import net.quedoom.francium.init.ModBlockEntities;

public class BellowBlockEntity extends BlockEntity {
    private int tick = 0;

    public BellowBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.BELLOW, worldPosition, blockState);
    }

    public int getTick() {
        return this.tick;
    }

    public void add() {
        this.tick++;
    }

    public static void tick(ServerLevel level, BlockState state, BlockPos pos, BlockEntity entity) {
        if (!(entity instanceof BellowBlockEntity blockEntity)) return;
        if (!(state.getBlock() instanceof BellowBlock bellowBlock)) return;
        blockEntity.add();
        if (blockEntity.tick % FranciumConfigManager.CONFIG.bellow_delay == 0) {
            bellowBlock.raise(level, pos, state);
        }
    }

}
