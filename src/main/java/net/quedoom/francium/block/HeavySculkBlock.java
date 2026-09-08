package net.quedoom.francium.block;

import com.mojang.datafixers.types.templates.Const;
import net.minecraft.core.BlockPos;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockState;

public class HeavySculkBlock extends DropExperienceBlock {
    public HeavySculkBlock(Properties properties) {
        super(ConstantInt.of(1), properties);
    }

    @Override
    public void destroy(LevelAccessor level, BlockPos pos, BlockState state) {
        ((Level) level).setBlockAndUpdate(pos, Blocks.HEAVY_CORE.defaultBlockState());
    }
}
