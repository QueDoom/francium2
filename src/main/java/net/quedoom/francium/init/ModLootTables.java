package net.quedoom.francium.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;
import net.quedoom.francium.Francium;

public class ModLootTables {
    public static final ResourceKey<LootTable> FORBIDDEN = register("blocks/forbidden");
    public static final ResourceKey<LootTable> SMALL_FORBIDDEN = register("blocks/small_forbidden");

    private static ResourceKey<LootTable> register(final String location) {
        return ResourceKey.create(Registries.LOOT_TABLE, Francium.id(location));
    }

    public static void registerLootTables() { };
}
