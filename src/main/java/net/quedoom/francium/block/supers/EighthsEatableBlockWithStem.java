package net.quedoom.francium.block.supers;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.quedoom.francium.init.ModProperties;
import net.quedoom.francium.init.ModStats;
import net.quedoom.francium.util.EighthsEatableOctant;
import org.jspecify.annotations.Nullable;

public class EighthsEatableBlockWithStem extends EighthsEatableBlock{
    public EighthsEatableBlockWithStem(int nutrition, float saturation, Properties properties) {
        super(nutrition, saturation, properties);
    }

    public EighthsEatableBlockWithStem(Properties properties) {
        super(properties);
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
