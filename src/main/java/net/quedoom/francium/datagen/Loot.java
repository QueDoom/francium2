package net.quedoom.francium.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.context.ContextKeySet;
import net.minecraft.world.level.storage.loot.LootTable;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.francium.init.ModItems;
import net.quedoom.francium.init.ModLootTables;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class Loot extends FabricBlockLootSubProvider {
    public Loot(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }

    @Override
    public void generate() {
        dropSelf(ModBlocks.WOODEN_CASING);
        dropOther(ModBlocks.MINERAL_MIXED_WOODEN_CASING, ModBlocks.WOODEN_CASING);
        dropSelf(ModBlocks.MINERAL_MIX_BLOCK);
        dropSelf(ModBlocks.STONE_CASING);
        dropSelf(ModBlocks.OBSIDIAN_CASING);
        dropSelf(ModBlocks.WOODEN_MERGER);
        dropSelf(ModBlocks.DEEP_MERGER);
        dropSelf(ModBlocks.FORBIDDEN_DUST);
        dropSelf(ModBlocks.FORBIDDEN_FLAKE);
        dropOther(ModBlocks.ANCIENT_BUNS, ModItems.ANCIENT_BUN);



    }
}
