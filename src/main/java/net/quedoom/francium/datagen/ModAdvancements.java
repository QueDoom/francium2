package net.quedoom.francium.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.advancements.*;
import net.minecraft.advancements.criterion.ItemUsedOnLocationTrigger;
import net.minecraft.advancements.criterion.PlayerTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.quedoom.francium.Francium;
import net.quedoom.francium.datagen.advancement_trigger.CraftCasingTrigger;
import net.quedoom.francium.datagen.advancement_trigger.MakeGlueTrigger;
import net.quedoom.francium.init.ModAdvancementTriggers;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.francium.init.ModItems;
import net.quedoom.francium.init.ModLootTables;
import net.quedoom.quet.datagen.advancement.QTAdvancement;
import net.quedoom.quet.datagen.advancement.QTAdvancementHolderConsumer;
import net.quedoom.quet.datagen.advancement.QueTAdvancementProvider;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancements extends QueTAdvancementProvider {

    public ModAdvancements(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    protected String namespace() {
        return Francium.MOD_ID;
    }

    @Override
    public void generateAdvancements(HolderLookup.Provider registryLookup, Consumer<AdvancementHolder> consumer, QTAdvancementHolderConsumer qtConsumer) {
        QTAdvancement francium = qtConsumer.builderWithoutParent(
                ModItems.DIRT_PILE, "francium", "francium", AdvancementType.TASK,true, false
        ).addCriterion("tick", PlayerTrigger.TriggerInstance.tick())
         .buildWithTranslations(consumer, "Francium 2", "Hello :)");

        // region baseline
        QTAdvancement pruner = qtConsumer.builder(
                francium, ModItems.BROKEN_STICK, "pruner", AdvancementType.TASK
        ).addCriterion("get_broken_stick", singleItemTrigger(ModItems.BROKEN_STICK))
         .rewards(new AdvancementRewards.Builder()
                .addLootTable(ModLootTables.ADVANCEMENT_BUNDLE_TABLE))
         .buildWithTranslations(consumer, "Pruner", "Prune some leaves for sticks.");

        // region casings
        QTAdvancement wooden_casing = qtConsumer.builder(
                pruner, ModBlocks.WOODEN_CASING, "wooden_casing", AdvancementType.TASK
        ).addCriterion("wooden_casing", singleItemTrigger(ModBlocks.WOODEN_CASING.asItem()))
         .buildWithTranslations(consumer, "Fragile Starts", "Build yourself a wooden casing.");
        QTAdvancement stone_casing = qtConsumer.builder(
                wooden_casing, ModBlocks.STONE_CASING, "stone_casing", AdvancementType.GOAL, true, true, true
        ).addCriterion("craft_stone_casing", casingCraftTrigger(ModBlocks.STONE_CASING))
         .buildWithTranslations(consumer, "Rocky Frame", "Add some rocky bits and bops to the wood.");
        QTAdvancement obsidian_casing = qtConsumer.builder(
                stone_casing, ModBlocks.OBSIDIAN_CASING, "obsidian_casing", AdvancementType.CHALLENGE, true, true, true
        ).addCriterion("craft_obsidian_casing", casingCraftTrigger(ModBlocks.OBSIDIAN_CASING))
         .rewards(new AdvancementRewards.Builder()
                .addLootTable(ModLootTables.ADVANCEMENT_BUNDLE_TABLE))
         .buildWithTranslations(consumer, "Hollow Lava (but it's cold)", "Read hard stories to the wood.");
        // endregion casings
        // region main
        QTAdvancement sharp = qtConsumer.builder(
                pruner, ModItems.SHARP_STICK, "sharp_stick", AdvancementType.TASK
        ).addCriterion("sharp_stick", singleItemTrigger(ModItems.BROKEN_STICK))
                .buildWithTranslations(consumer, "Sharp as a stick", "Sharpen a stick (by using it on something hard.");
        QTAdvancement shears = qtConsumer.builder(
                sharp, ModItems.WOODEN_SHEARS, "wooden_shears", AdvancementType.TASK
        ).addCriterion("wooden_shears", singleItemTrigger(ModItems.WOODEN_SHEARS))
                .buildWithTranslations(consumer, "Sharp as a shear", "Might be useful to cut leaves faster.");

        QTAdvancement wooden_mixer = qtConsumer.builder(
            francium, ModBlocks.GLUE_MIXER, "wooden_mixer", AdvancementType.TASK
        ).addCriterion("place_mixer", placedBlockTrigger(ModBlocks.GLUE_MIXER))
                .buildWithTranslations(consumer, "Sticky Bowl", "Just THROW ;);) in some slime and stuff.");
        QTAdvancement glue = qtConsumer.builder(
            wooden_mixer, ModItems.GLUE, "glue", AdvancementType.TASK
        ).addCriterion("glue", makeGlueTrigger(ModItems.GLUE))
                .buildWithTranslations(consumer, "*sniff sniff*", "Create the first bottle of glue.");
        QTAdvancement vegan_glue = qtConsumer.builder(
            glue, ModItems.VEGAN_GLUE, "vegan_glue", AdvancementType.TASK
        ).addCriterion("vegan_glue", makeGlueTrigger(ModItems.VEGAN_GLUE))
                .buildWithTranslations(consumer, "I am vegan", "Create vegan, moral glue. Slime is definitely vegan... ehh.");
        QTAdvancement echo_glue = qtConsumer.builder(
            vegan_glue, ModItems.ECHO_GLUE, "echo_glue", AdvancementType.GOAL
        ).addCriterion("echo_glue", makeGlueTrigger(ModItems.ECHO_GLUE))
                .buildWithTranslations(consumer, "Deep and dark glue", "Head down to the scary place and make glue with it.");
        QTAdvancement super_glue = qtConsumer.builder(
            vegan_glue, ModItems.SUPER_GLUE, "super_glue", AdvancementType.GOAL
        ).addCriterion("super_glue", makeGlueTrigger(ModItems.SUPER_GLUE))
                .buildWithTranslations(consumer, "SUPER-GLUE", "The bees make honey.");
        // endregion main
        // endregion baseline
    }

    protected static Criterion<CraftCasingTrigger.TriggerInstance> casingCraftTrigger(Block isBlock) {
        return ModAdvancementTriggers.CRAFT_CASING_EVENT.createCriterion(new CraftCasingTrigger.TriggerInstance(Optional.empty(), isBlock));
    }
    protected static Criterion<MakeGlueTrigger.TriggerInstance> makeGlueTrigger(Item isItem) {
        return ModAdvancementTriggers.MAKE_GLUE_EVENT.createCriterion(new MakeGlueTrigger.TriggerInstance(Optional.empty(), isItem));
    }

    protected static Criterion<ItemUsedOnLocationTrigger.TriggerInstance> placedBlockTrigger(Block block) {
        return ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(block);
    }
}
