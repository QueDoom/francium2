package net.quedoom.francium.block.thicc_crops;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.quedoom.francium.block.supers.EighthsEatableBlock;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.francium.init.ModTags;

import java.util.Optional;


public abstract class ThickableCropBlock extends CropBlock {
    public ThickableCropBlock(Properties properties) {
        super(properties);
    }

    protected abstract Block thickBlock();
    protected abstract Optional<Block> thickFoliage();

    public void thicken(Level level, BlockPos pos, BlockState state) {
        level.setBlockAndUpdate(pos, thickBlock().defaultBlockState());
        level.setBlockAndUpdate(pos.below(), Blocks.ROOTED_DIRT.defaultBlockState());
        thickFoliage().ifPresent(block -> {
            if (level.getBlockState(pos.above()).canBeReplaced()) {
                level.setBlockAndUpdate(pos.above(), block.defaultBlockState());
            }
        });
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return random.nextInt(16) == 0;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (level.getRawBrightness(pos, 0) >= 9) {
            int age = this.getAge(state);
            if (age < this.getMaxAge()) {
                float growthSpeed = getGrowthSpeed(this, level, pos) / 2;
                if (random.nextInt((int)(25.0F / growthSpeed) + 1 + addDelayToGrowing()) == 0) {
                    level.setBlock(pos, this.getStateForAge(age + 1), 2);
                }
            } else {
                thicken(level, pos, state);
            }
        }
    }

    protected abstract int addDelayToGrowing();

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void growCrops(Level level, BlockPos pos, BlockState state) {
        if (state.getValue(CropBlock.AGE) == getMaxAge()) {
            thicken(level, pos, state);
            return;
        }

        super.growCrops(level, pos, state);
    }
}
