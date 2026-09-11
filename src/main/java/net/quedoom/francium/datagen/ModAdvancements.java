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

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancements extends FabricAdvancementProvider {

    public ModAdvancements(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(HolderLookup.Provider registryLookup, Consumer<AdvancementHolder> consumer) {
        AdvancementHolder getGravelPile = newItemPickupHolder(
                consumer, ModItems.GRAVEL_PILE, "get_gravel_pile", AdvancementType.TASK);


    }

    private static AdvancementHolder newRewardsHolder(Consumer<AdvancementHolder> consumer, Item item, String name,
                                                           AdvancementType type, Criterion<?> trigger, AdvancementRewards.Builder rewards) {
        return Advancement.Builder.advancement()
                .display(
                        item,
                        Component.translatable(Francium.MOD_ID + ".advancementTitle." + name),
                        Component.translatable(Francium.MOD_ID + ".advancementDescription." + name),
                        Identifier.withDefaultNamespace("gui/advancements/backgrounds/adventure"),
                        type,
                        true,
                        true,
                        false
                )
                .rewards(rewards)
                .addCriterion(name, trigger).save(consumer, Francium.id(name));
    }

    private static AdvancementHolder newRewardsHolder(Consumer<AdvancementHolder> consumer, Item item, String name,
                                                      AdvancementType type, Criterion<?> trigger, AdvancementRewards.Builder rewards, AdvancementHolder parent) {
        return Advancement.Builder.advancement()
                .parent(parent)
                .display(
                        item,
                        Component.translatable(Francium.MOD_ID + ".advancementTitle." + name),
                        Component.translatable(Francium.MOD_ID + ".advancementDescription." + name),
                        Identifier.withDefaultNamespace("gui/advancements/backgrounds/adventure"),
                        type,
                        true,
                        true,
                        false
                )
                .rewards(rewards)
                .addCriterion(name, trigger).save(consumer, Francium.id(name));
    }

    private static AdvancementHolder newItemPickupHolder(Consumer<AdvancementHolder> consumer, Item item, String name,
                                                         AdvancementType type) {
        return newHolder(consumer, item, name, type,
                InventoryChangeTrigger.TriggerInstance.hasItems(item));
    }

    private static AdvancementHolder newItemPickupHolder(Consumer<AdvancementHolder> consumer, Item item, String name,
                                                         AdvancementType type, AdvancementHolder parent) {
        return newHolder(consumer, item, name, type,
                InventoryChangeTrigger.TriggerInstance.hasItems(item), parent);
    }

    private static AdvancementHolder newHolder(Consumer<AdvancementHolder> consumer, Item item, String name,
                                              AdvancementType type, Criterion<?> trigger) {
        return Advancement.Builder.advancement()
                .display(
                        item,
                        Component.translatable(Francium.MOD_ID + ".advancementTitle." + name),
                        Component.translatable(Francium.MOD_ID + ".advancementDescription." + name),
                        Identifier.withDefaultNamespace("gui/advancements/backgrounds/adventure"),
                        type,
                        true,
                        true,
                        false
                ).addCriterion(name, trigger).save(consumer, Francium.id(name));
    }
    private static AdvancementHolder newHolder(Consumer<AdvancementHolder> consumer, Item item, String name,
                                               AdvancementType type, Criterion<?> trigger, AdvancementHolder parent) {
        return Advancement.Builder.advancement()
                .parent(parent)
                .display(
                        item,
                        Component.translatable(Francium.MOD_ID + ".advancementTitle." + name),
                        Component.translatable(Francium.MOD_ID + ".advancementDescription." + name),
                        Identifier.withDefaultNamespace("gui/advancements/backgrounds/adventure"),
                        type,
                        true,
                        true,
                        false
                ).addCriterion(name, trigger).save(consumer, Francium.id(name));
    }

}
