package net.quedoom.francium.block.thicc_farming;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.quedoom.thicc_farming.block.thicc_farming.ThickFoliageBlock;
import net.quedoom.thicc_farming.util.EighthsEatableOctant;

public class EighthsEatableBlockWithStem extends EighthsEatableBlock{
    public EighthsEatableBlockWithStem(String item, int nutrition, float saturation, Properties properties) {
        super(item, nutrition, saturation, properties);
    }
    public EighthsEatableBlockWithStem(int nutrition, float saturation, Properties properties) {
        super("", nutrition, saturation, properties);
    }

    public EighthsEatableBlockWithStem(String item, Properties properties) {
        super(item, properties);
    }

    public EighthsEatableBlockWithStem(Properties properties) {
        super("", properties);
    }

    @Override
    protected void set(LevelAccessor level, BlockPos pos, BlockState state, EighthsEatableOctant octant) {
        BlockState above = level.getBlockState(pos.above());
        if (above.getBlock() instanceof ThickFoliageBlock foliageBlock) {
            octant.getStemProperty().ifPresent(booleanProperty ->
                    foliageBlock.updateFromCrop(above, ((Level) level), pos.above(), booleanProperty));
        }
        super.set(level, pos, state, octant);
    }
}
