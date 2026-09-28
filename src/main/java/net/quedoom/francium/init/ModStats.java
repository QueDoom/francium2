package net.quedoom.francium.init;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.stats.Stat;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.StatType;
import net.minecraft.stats.Stats;
import net.minecraft.world.level.block.Block;
import net.quedoom.quet.init.QueTStats;

public class ModStats extends QueTStats {
    public static final Stat<?> INTERACTION_WITH_WOODEN_MERGER = makeCustomStat("interaction_with_wooden_merger", StatFormatter.DEFAULT);
    public static final Stat<?> INTERACTION_WITH_DEEP_MERGER = makeCustomStat("interaction_with_deep_merger", StatFormatter.DEFAULT);
    public static final Stat<?> INTERACTION_WITH_BUNDLE_TABLE = makeCustomStat("interaction_with_bundle_table", StatFormatter.DEFAULT);

}
