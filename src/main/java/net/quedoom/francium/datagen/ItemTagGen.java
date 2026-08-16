package net.quedoom.francium.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import net.quedoom.francium.init.ModItems;
import net.quedoom.francium.init.ModTags;
import org.jspecify.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ItemTagGen extends FabricTagsProvider.ItemTagsProvider {

    public ItemTagGen(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        valueLookupBuilder(ItemTags.SHOVELS)
                .add(ModItems.SHARP_STICK);

        valueLookupBuilder(ItemTags.PICKAXES)
                .add(ModItems.SHARP_STICK);

        valueLookupBuilder(ItemTags.AXES)
                .add(ModItems.SHARP_STICK);

        valueLookupBuilder(ModTags.Items.NO_HOE_MULTITOOL)
                .add(ModItems.SHARP_STICK);
    }
}
