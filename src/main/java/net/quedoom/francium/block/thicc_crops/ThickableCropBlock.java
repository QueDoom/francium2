package net.quedoom.francium.block.thicc_crops;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.quedoom.francium.block.supers.EighthsEatableBlock;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.francium.init.ModTags;

import java.util.Optional;


public class ThickableCropBlock extends CropBlock {
    private final Block thickBlock;
    private final Optional<Block> thickFoliageBlock;

    public ThickableCropBlock(Block thickBlock, Properties properties) {
        super(properties);
        if ((thickBlock instanceof EighthsEatableBlock)) throw new IllegalArgumentException("Thick blocks actually gotta be eatable too!");
        this.thickBlock = thickBlock;
        this.thickFoliageBlock = Optional.empty();
    }

    public ThickableCropBlock(Block thickBlock, Block thickFoliage, Properties properties) {
        super(properties);
        if ((thickBlock instanceof EighthsEatableBlock)) throw new IllegalArgumentException("Thick blocks actually gotta be eatable too!");
        if ((thickFoliage instanceof ThickableCropBlock)) throw new IllegalArgumentException("Thick foliage actually gotta be thick foliage too!");
        this.thickBlock = thickBlock;
        this.thickFoliageBlock = Optional.of(thickFoliage);
    }

    public void thicken(Level level, BlockPos pos) {
        level.setBlockAndUpdate(pos.below(), Blocks.ROOTED_DIRT.defaultBlockState());
        level.setBlockAndUpdate(pos, thickBlock.defaultBlockState());
        thickFoliageBlock.ifPresent(block -> {
            if (level.getBlockState(pos.above()).canBeReplaced()) {
                level.setBlockAndUpdate(pos.above(), block.defaultBlockState());
            }
        });
    }

    @Override
    public void growCrops(Level level, BlockPos pos, BlockState state) {
        if (state.is(ModTags.Blocks.GROWS_INTO_THICK)) {
            if (state.getValue(CropBlock.AGE) == getMaxAge()) {
                thicken(level, pos);
                return;
            }
        }
        super.growCrops(level, pos, state);
    }
}
