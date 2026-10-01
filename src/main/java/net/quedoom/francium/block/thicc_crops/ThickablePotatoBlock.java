package net.quedoom.francium.block.thicc_crops;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.quedoom.francium.init.ModBlocks;

import java.util.Optional;

public class ThickablePotatoBlock extends ThickableCropBlock {
    private static final VoxelShape[] SHAPES = Block.boxes(7, (age) -> Block.column((double)16.0F, (double)0.0F, (double)(2 + age)));

    public ThickablePotatoBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return Items.POTATO;
    }

    @Override
    protected VoxelShape getShape(final BlockState state, final BlockGetter level, final BlockPos pos, final CollisionContext context) {
        return SHAPES[this.getAge(state)];
    }

    @Override
    protected Block thickBlock() {
        return ModBlocks.THICK_POTATO;
    }

    @Override
    protected Optional<Block> thickFoliage() {
        return Optional.of(ModBlocks.THICK_POTATO_FOLIAGE);
    }

    @Override
    protected int addDelayToGrowing() {
        return 0;
    }
}
