package net.quedoom.francium.block.thicc_crops;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BeetrootBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.quedoom.francium.init.ModBlocks;

import java.util.Optional;

public class ThickableBeetrootBlock extends ThickableCropBlock {
    public static final int MAX_AGE = 3;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    private static final VoxelShape[] SHAPES = Block.boxes(3, (age) -> Block.column((double)16.0F, (double)0.0F, (double)(2 + age * 2)));

    public ThickableBeetrootBlock(Properties properties) {
        super(properties);
    }


    protected IntegerProperty getAgeProperty() {
        return AGE;
    }

    public int getMaxAge() {
        return MAX_AGE;
    }

    protected ItemLike getBaseSeedId() {
        return Items.BEETROOT_SEEDS;
    }

    @Override
    protected int addDelayToGrowing() {
        return 5;
    }

    protected int getBonemealAgeIncrease(final Level level) {
        return super.getBonemealAgeIncrease(level) / MAX_AGE;
    }

    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    protected VoxelShape getShape(final BlockState state, final BlockGetter level, final BlockPos pos, final CollisionContext context) {
        return SHAPES[this.getAge(state)];
    }

    @Override
    protected Block thickBlock() {
        return ModBlocks.THICK_BEETROOT;
    }

    @Override
    protected Optional<Block> thickFoliage() {
        return Optional.of(ModBlocks.THICK_BEETROOT_FOLIAGE);
    }
}
