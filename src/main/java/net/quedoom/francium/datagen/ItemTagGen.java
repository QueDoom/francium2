package net.quedoom.francium.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.quedoom.francium.init.ModBlocks;
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

        valueLookupBuilder(ModTags.Items.WOODEN_MERGER_GLUE)
                .add(ModItems.GLUE)
                .add(ModItems.VEGAN_GLUE)
                .add(ModItems.WOODEN_PLATE)
        ;

        valueLookupBuilder(ModTags.Items.DEEP_MERGER_GLUE)
                .addTag(ModTags.Items.WOODEN_MERGER_GLUE)
                .add(ModItems.ECHO_GLUE)
                .add(ModItems.SUPER_GLUE)
        ;

        valueLookupBuilder(ModTags.Items.SMALL_VEGETATION)
                .add(ModItems.LEAF)
                .add(ModItems.GRASS)
                .add(Items.SHORT_GRASS);

        valueLookupBuilder(ModTags.Items.BIG_VEGETATION)
                .addOptionalTag(ItemTags.LEAVES);

        valueLookupBuilder(ModTags.Items.SMALL_ECHO)
                .add(Items.ECHO_SHARD);

        valueLookupBuilder(ModTags.Items.BIG_ECHO)
                .add(ModBlocks.ECHO_BLOCK.asItem());


    }
}
