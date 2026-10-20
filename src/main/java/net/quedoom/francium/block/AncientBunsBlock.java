package net.quedoom.francium.block;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.ListTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.francium.init.ModProperties;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class AncientBunsBlock extends Block {
    public static final BooleanProperty BUN_0 = ModProperties.ANCIENT_BUNS_0;
    public static final BooleanProperty BUN_1 = ModProperties.ANCIENT_BUNS_1;
    public static final BooleanProperty BUN_2 = ModProperties.ANCIENT_BUNS_2;
    public static final BooleanProperty BUN_3 = ModProperties.ANCIENT_BUNS_3;
    public static final BooleanProperty BUN_4 = ModProperties.ANCIENT_BUNS_4;

    public AncientBunsBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(BUN_0, false).setValue(BUN_1, false).setValue(BUN_2, false).setValue(BUN_3, false).setValue(BUN_4, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BUN_0, BUN_1, BUN_2, BUN_3, BUN_4);
    }

    public static BlockState placementState(Level level) {
        BlockState defaultState = ModBlocks.ANCIENT_BUNS.defaultBlockState();
        RandomSource random = level.getRandom();
        int first = random.nextInt(0, 5);
        int second = random.nextInt(0, 5);
        int third = random.nextInt(0, 5);

        return defaultState.setValue(getBunProperty(first), true).setValue(getBunProperty(second), true).setValue(getBunProperty(third), true);
    }

    @Override
    public void destroy(LevelAccessor level, BlockPos pos, BlockState state) {
        if (trueProperties(state) <= 1) return;

        BooleanProperty randomProperty = getRandomProperty(((Level) level), state);

        level.setBlock(pos, state.setValue(randomProperty, false), Block.UPDATE_SKIP_ON_PLACE);

    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return box(5, 0, 5, 11, 16, 11);
    }

    private static BooleanProperty getRandomProperty(Level level, BlockState state) {
        List<BooleanProperty> shownBuns = getShownBuns(state);
        return shownBuns.get(level.getRandom().nextInt(0, shownBuns.size()));
    }

    private static List<BooleanProperty> getShownBuns(BlockState state) {
        List<BooleanProperty> booleanProperties = new ArrayList<>();
        for (BooleanProperty property : getProperties()) {
            if (state.getValue(property)) booleanProperties.add(property);
        }
        return booleanProperties;
    }

    private static List<BooleanProperty> getProperties() {
        return List.of(BUN_0, BUN_1, BUN_2, BUN_3, BUN_4);
    }

    public static boolean getBuns(BlockState state, int value) {
        return switch (value) {
            case 1 -> state.getValue(BUN_1);
            case 2 -> state.getValue(BUN_2);
            case 3 -> state.getValue(BUN_3);
            case 4 -> state.getValue(BUN_4);
            default -> state.getValue(BUN_0);
        };
    }

    public static BooleanProperty getBunProperty(int value) {
        return switch (value) {
            case 1 -> BUN_1;
            case 2 -> BUN_2;
            case 3 -> BUN_3;
            case 4 -> BUN_4;
            default -> BUN_0;
        };
    }

    private static int trueProperties(BlockState state) {
        return getShownBuns(state).size();
    }
}
