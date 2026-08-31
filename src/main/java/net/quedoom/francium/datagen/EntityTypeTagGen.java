package net.quedoom.francium.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.entity.EntityType;
import net.quedoom.francium.init.ModTags;

import java.util.concurrent.CompletableFuture;

public class EntityTypeTagGen extends FabricTagsProvider.EntityTypeTagsProvider{
    public EntityTypeTagGen(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        valueLookupBuilder(ModTags.Entities.DOES_NOT_DROP_SLIME)
                .add(
                        EntityType.SKELETON,
                        EntityType.SKELETON_HORSE,
                        EntityType.WITHER_SKELETON,
                        EntityType.WITHER,
                        EntityType.ENDER_DRAGON
                );
    }
}
