package net.quedoom.francium.util;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SugarCaneBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.BlockColumnFeature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.BlockColumnConfiguration;
import net.quedoom.francium.init.ModProperties;

public class SugarCaneFeature extends BlockColumnFeature {
    public SugarCaneFeature(Codec<BlockColumnConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(final FeaturePlaceContext<BlockColumnConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockColumnConfiguration config = context.config();
        RandomSource random = context.random();
        int layerCount = config.layers().size();
        int[] layerHeights = new int[layerCount];
        int totalHeight = 0;
        for (int i = 0; i < layerCount; i++) {
            layerHeights[i] = config.layers().get(i).height().sample(random);
            totalHeight += layerHeights[i];
        }
        if (totalHeight == 0) {
            return false;
        }
        BlockPos.MutableBlockPos placePos = context.origin().mutable();
        BlockPos.MutableBlockPos nextPos = placePos.mutable().move(config.direction());
        for (int y = 0; y < totalHeight; y++) {
            if (!config.allowedPlacement().test(level, nextPos)) {
                truncate(layerHeights, totalHeight, y, config.prioritizeTip());
                break;
            }
            nextPos.move(config.direction());
        }

        int actualHeight = 0;
        for (int h : layerHeights) {
            actualHeight += h;
        }
        if (actualHeight == 0) {
            return false;
        }

        int placed = 0;
        for (int i = 0; i < layerCount; i++) {
            int count = layerHeights[i];
            for (int y = 0; y < count; y++) {
                placed++;
                boolean isTop = placed == actualHeight;
                BlockState state = Blocks.SUGAR_CANE.defaultBlockState()
                        .setValue(SugarCaneBlock.AGE, 0)
                        .setValue(ModProperties.TOP, isTop);
                level.setBlock(placePos, state, 2);
                placePos.move(config.direction());
            }
        }
        return true;
    }

    private static void truncate(final int[] layerHeights, final int totalHeight, final int newHeight, final boolean prioritizeTip) {
        int amountToRemove = totalHeight - newHeight;
        int direction = prioritizeTip ? 1 : -1;
        int start = prioritizeTip ? 0 : layerHeights.length - 1;
        int end = prioritizeTip ? layerHeights.length : -1;
        for (int i = start; i != end && amountToRemove > 0; i += direction) {
            int thisLayer = layerHeights[i];
            int toRemoveFromLayer = Math.min(thisLayer, amountToRemove);
            amountToRemove -= toRemoveFromLayer;
            layerHeights[i] -= toRemoveFromLayer;
        }
    }

}
