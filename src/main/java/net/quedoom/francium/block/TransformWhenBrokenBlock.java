package net.quedoom.francium.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class TransformWhenBrokenBlock extends Block {
    private final BlockState transformState;
    public TransformWhenBrokenBlock(Properties properties, BlockState transformState) {
        super(properties);
        this.transformState = transformState;
    }

    @Override
    public void destroy(LevelAccessor level, BlockPos pos, BlockState state) {
        level.setBlock(pos, transformState, 1);
    }
}
