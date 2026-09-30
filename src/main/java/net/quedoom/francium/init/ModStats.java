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

public class ModStats {
    public static final Identifier INTERACTION_WITH_WOODEN_MERGER = makeCustomStat("interaction_with_wooden_merger", StatFormatter.DEFAULT);
    public static final Identifier INTERACTION_WITH_DEEP_MERGER = makeCustomStat("interaction_with_deep_merger", StatFormatter.DEFAULT);
    public static final Identifier INTERACTION_WITH_BUNDLE_TABLE = makeCustomStat("interaction_with_bundle_table", StatFormatter.DEFAULT);
    public static final Identifier EAT_EIGHTHS_BLOCK = makeCustomStat("eat_eighths_block", StatFormatter.DEFAULT);


    private static Identifier makeCustomStat(final String id, final StatFormatter formatter) {
        Identifier location = Identifier.withDefaultNamespace(id);
        Registry.register(BuiltInRegistries.CUSTOM_STAT, id, location);
        Stats.CUSTOM.get(location, formatter);
        return location;
    }

    public static void registerStats() {}

}
