package net.quedoom.francium.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

@FunctionalInterface
public interface SetBlocker {

    void place(Level level, BlockPos pos, BlockState state);

}
