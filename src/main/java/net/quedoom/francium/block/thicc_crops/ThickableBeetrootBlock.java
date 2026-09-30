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

public class ThickableBeetrootBlock extends ThickableCropBlock {
    public static final int MAX_AGE = 3;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    private static final VoxelShape[] SHAPES = Block.boxes(3, (age) -> Block.column((double)16.0F, (double)0.0F, (double)(2 + age * 2)));

    public ThickableBeetrootBlock(Block thickBlock, Properties properties) {
        super(thickBlock, properties);
    }

    public ThickableBeetrootBlock(Block thickBlock, Block thickFoliage, Properties properties) {
        super(thickBlock, thickFoliage, properties);
    }

    protected IntegerProperty getAgeProperty() {
        return AGE;
    }

    public int getMaxAge() {
        return 3;
    }

    protected ItemLike getBaseSeedId() {
        return Items.BEETROOT_SEEDS;
    }

    protected void randomTick(final BlockState state, final ServerLevel level, final BlockPos pos, final RandomSource random) {
        if (random.nextInt(5) != 0) {
            super.randomTick(state, level, pos, random);
        }

    }

    protected int getBonemealAgeIncrease(final Level level) {
        return super.getBonemealAgeIncrease(level) / 3;
    }

    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    protected VoxelShape getShape(final BlockState state, final BlockGetter level, final BlockPos pos, final CollisionContext context) {
        return SHAPES[this.getAge(state)];
    }
}
