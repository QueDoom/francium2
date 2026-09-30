package net.quedoom.francium.block.thicc_crops;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ThickableCarrotBlock extends ThickableCropBlock {
    private static final VoxelShape[] SHAPES = Block.boxes(7, (age) -> Block.column((double)16.0F, (double)0.0F, (double)(2 + age)));

    public ThickableCarrotBlock(Block thickBlock, Properties properties) {
        super(thickBlock, properties);
    }

    public ThickableCarrotBlock(Block thickBlock, Block thickFoliage, Properties properties) {
        super(thickBlock, thickFoliage, properties);
    }
    @Override
    protected ItemLike getBaseSeedId() {
        return Items.CARROT;
    }

    @Override
    protected VoxelShape getShape(final BlockState state, final BlockGetter level, final BlockPos pos, final CollisionContext context) {
        return SHAPES[this.getAge(state)];
    }
}
