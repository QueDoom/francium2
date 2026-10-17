package net.quedoom.francium.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.quedoom.francium.api.prickle.FranciumConfigManager;
import net.quedoom.francium.init.ModBlockEntities;

public class StokedFireEntity extends BlockEntity {
    private int tick = 0;

    public StokedFireEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.STOKED_FIRE_ENTITY, worldPosition, blockState);

    }

    public static void tick(ServerLevel level, BlockPos pos, BlockState state, StokedFireEntity entity) {
        entity.tick += 1;

        if (entity.tick >= FranciumConfigManager.CONFIG.stoked_fire_time) {
            level.setBlockAndUpdate(pos, Blocks.FIRE.defaultBlockState());
        }
    }

    public void blow() {
        int reset = FranciumConfigManager.CONFIG.stoked_fire_blow_adding;
        if (reset < 0 || this.tick - reset < 0 ) {
            this.tick = 0;
        } else {
            this.tick -= reset;
        }
    }
}
