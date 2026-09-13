package net.quedoom.francium.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.SpikeConfiguration;

public class TuffRockFeature extends Feature<TuffRockConfiguration> {
    public TuffRockFeature() {
        super(TuffRockConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<TuffRockConfiguration> context) {
        BlockPos origin = context.origin();
        RandomSource random = context.random();
        WorldGenLevel level = context.level();

        TuffRockConfiguration config = context.config();
        if (!config.canPlaceOn().test(level, origin)) {
            return false;
        }
        int height = random.nextInt(2);
        int width = height / 4 + random.nextInt(2);

        for (int layerOneX = 0; layerOneX < width; layerOneX++) {
            for (int layerOneZ = 0; layerOneZ < width; layerOneZ++) {
                BlockPos pos = origin;
                if (0 == random.nextInt(10)) {
                    this.setBlock(level, pos.north(layerOneX).east(layerOneZ), Blocks.TUFF.defaultBlockState());
                }
            }
        }
        origin.above();
        for (int layerOneX = 0; layerOneX < width; layerOneX++) {
            for (int layerOneZ = 0; layerOneZ < width; layerOneZ++) {
                BlockPos pos = origin;
                if (0 == random.nextInt(40)) {
                    this.setBlock(level, pos.north(layerOneX).east(layerOneZ), Blocks.TUFF.defaultBlockState());
                }
            }
        }
        origin.above();
        for (int layerOneX = 0; layerOneX < width; layerOneX++) {
            for (int layerOneZ = 0; layerOneZ < width; layerOneZ++) {
                BlockPos pos = origin;
                if (0 == random.nextInt(85)) {
                    this.setBlock(level, pos.north(layerOneX).east(layerOneZ), Blocks.TUFF.defaultBlockState());
                }
            }
        }
        return true;
    }
}
