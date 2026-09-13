package net.quedoom.francium.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SpikeConfiguration;

public record TuffRockConfiguration(BlockPredicate canPlaceOn, BlockPredicate canReplace) implements FeatureConfiguration {
    public static final Codec<TuffRockConfiguration> CODEC = RecordCodecBuilder.create(
            i -> i.group(
                            BlockPredicate.CODEC.fieldOf("can_place_on").forGetter(TuffRockConfiguration::canPlaceOn),
                            BlockPredicate.CODEC.fieldOf("can_replace").forGetter(TuffRockConfiguration::canReplace)
                    )
                    .apply(i, TuffRockConfiguration::new)
    );
}
