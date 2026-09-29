package net.quedoom.francium.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.*;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.quedoom.francium.Francium;
import net.quedoom.francium.init.ModItems;
import net.quedoom.quet.datagen.advancement.QTAdvancementHolderConsumer;
import net.quedoom.quet.datagen.advancement.QueTAdvancementProvider;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancements extends QueTAdvancementProvider {

    public ModAdvancements(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(HolderLookup.Provider registryLookup, Consumer<AdvancementHolder> consumer) {

    }

    @Override
    public void generateAdvancement(HolderLookup.Provider registryLookup, Consumer<AdvancementHolder> consumer, QTAdvancementHolderConsumer qtConsumer) {
//        AdvancementHolder getGravelPile = qtConsumer.newItemPickupHolder(ModItems.GRAVEL_PILE, "get_gravel_pile", consumer.andThen());
    }

}
