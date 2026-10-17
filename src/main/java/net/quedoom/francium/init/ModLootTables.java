package net.quedoom.francium.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;
import net.quedoom.francium.Francium;

public class ModLootTables {
    public static ResourceKey<LootTable> ADVANCEMENT_BUNDLE_TABLE = registerAdvancement("reward_bundle_table.json\n");

    private static ResourceKey<LootTable> register(final String location) {
        return ResourceKey.create(Registries.LOOT_TABLE, Francium.of(location));
    }
    private static ResourceKey<LootTable> registerAdvancement(final String location) {
        return ResourceKey.create(Registries.LOOT_TABLE, Francium.of("advancement/" + location));
    }

    public static void registerLootTables() { };
}
