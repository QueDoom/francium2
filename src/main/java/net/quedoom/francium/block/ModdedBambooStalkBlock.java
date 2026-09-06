package net.quedoom.francium.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BambooStalkBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.quedoom.francium.Francium;
import net.quedoom.francium.init.ModBlocks;

public class ModdedBambooStalkBlock extends BambooStalkBlock {
    public ModdedBambooStalkBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void destroy(LevelAccessor level, BlockPos pos, BlockState state) {
        ((Level) level).setBlockAndUpdate(pos, ModBlocks.STRIPPED_BAMBOO.defaultBlockState().setValue(AGE, state.getValue(AGE)));
        destroyAllOtherGuys(level, pos, state);
    }

    protected void destroyAllOtherGuys(LevelAccessor level, BlockPos pos, BlockState state) {
        int i = 1;
        while (level.getBlockState(pos.below(i)).is(Blocks.BAMBOO) || level.getBlockState(pos.below(i)).is(ModBlocks.STRIPPED_BAMBOO)) {
            i += 1;
            Francium.LOGGER.info("{}", i);
        }
        BlockPos bottomestPos = pos.below(i);
        Francium.LOGGER.info(bottomestPos.toString());
        i = 1;
        while (level.getBlockState(bottomestPos.above(i)).is(Blocks.BAMBOO) || level.getBlockState(bottomestPos.above(i)).is(ModBlocks.STRIPPED_BAMBOO)) {
            Francium.LOGGER.info("{}", i);
            int age = level.getBlockState(bottomestPos.above(i)).getValue(AGE);
            ((Level) level).setBlockAndUpdate(bottomestPos.above(i), ModBlocks.STRIPPED_BAMBOO.defaultBlockState().setValue(AGE, age));
            i += 1;
        }
        for (int j = 2; j < i; j++) {
            Block.dropResources(Blocks.BAMBOO.defaultBlockState(), ((Level) level), pos);
        }
    }
}
