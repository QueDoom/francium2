package net.quedoom.francium.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.SugarCaneBlock;
import net.minecraft.world.level.block.state.BlockState;

public class StrippedSugarCaneBlock extends SolidSugarCaneBlock {
    public StrippedSugarCaneBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void destroy(LevelAccessor level, BlockPos pos, BlockState state) {
    }
}
