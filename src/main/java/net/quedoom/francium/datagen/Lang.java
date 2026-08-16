package net.quedoom.francium.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.quedoom.francium.Francium;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.francium.init.ModItems;

import java.util.concurrent.CompletableFuture;

public class Lang extends FabricLanguageProvider {
    public Lang(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(packOutput, registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder translationBuilder) {
        pileTranslate(translationBuilder, ModItems.DIRT_PILE, "Dirt");
        pileTranslate(translationBuilder, ModItems.GRAVEL_PILE, "Gravel");
        pileTranslate(translationBuilder, ModItems.DIORITE_PILE, "Diorite");
        pileTranslate(translationBuilder, ModItems.GRANITE_PILE, "Granite");
        pileTranslate(translationBuilder, ModItems.ANDESITE_PILE, "Andesite");
        pileTranslate(translationBuilder, ModItems.TUFF_PILE, "Tuff");
        pileTranslate(translationBuilder, ModItems.GOLD_ORE_PILE, "Gold Ore");
        pileTranslate(translationBuilder, ModItems.DEEPSLATE_GOLD_ORE_PILE, "Deepslate Gold Ore");
        translationBuilder.add(ModItems.GOLD_DUST, "Gold Dust");
        pileTranslate(translationBuilder, ModItems.IRON_ORE_PILE, "Iron Ore");
        pileTranslate(translationBuilder, ModItems.DEEPSLATE_IRON_ORE_PILE, "Deepslate Iron Ore");
        translationBuilder.add(ModItems.IRON_DUST, "Iron Dust");
        pileTranslate(translationBuilder, ModItems.COPPER_ORE_PILE, "Copper Ore");
        pileTranslate(translationBuilder, ModItems.DEEPSLATE_COPPER_ORE_PILE, "Deepslate Copper Ore");
        translationBuilder.add(ModItems.COPPER_DUST, "Copper Dust");
        pileTranslate(translationBuilder, ModItems.DIAMOND_ORE_PILE, "Diamond Ore");
        pileTranslate(translationBuilder, ModItems.DEEPSLATE_DIAMOND_ORE_PILE, "Deepslate Diamond Ore");
        translationBuilder.add(ModItems.DIAMOND_DUST, "Diamond Dust");
        pileTranslate(translationBuilder, ModItems.DEEPSLATE_PILE, "Deepslate");
        pileTranslate(translationBuilder, ModItems.CALCITE_PILE, "Calcite");
        pileTranslate(translationBuilder, ModItems.DRIPSTONE_PILE, "Dripstone");
        pileTranslate(translationBuilder, ModItems.NETHERRACK_PILE, "Netherrack");
        pileTranslate(translationBuilder, ModItems.BLACKSTONE_PILE, "Blackstone");
        pileTranslate(translationBuilder, ModItems.SOUL_PILE, "Soul");
        pileTranslate(translationBuilder, ModItems.BASALT_PILE, "Basalt");
        autoTranslate(translationBuilder, ModItems.BROKEN_STICK);
        autoTranslate(translationBuilder, ModItems.SHARP_STICK);
        autoTranslate(translationBuilder, ModItems.SAWDUST);

        translationBuilder.add(ModItems.BARK, "Tree Bark");
        translationBuilder.add(ModBlocks.PILE_OF_LEAVES, "Leaf");

        autoTranslate(translationBuilder, ModItems.WOODEN_PLATE);

        autoTranslate(translationBuilder, ModItems.ANDESITE_ALLOY);
        autoTranslate(translationBuilder, ModItems.DIORITE_ALLOY);
        autoTranslate(translationBuilder, ModItems.GRANITE_ALLOY);
        autoTranslate(translationBuilder, ModItems.MINERAL_MIX);
        autoTranslate(translationBuilder, ModItems.DRIPSTONE_PASTE);
        autoTranslate(translationBuilder, ModItems.DRIPSTONE_COATED_MINERAL_MIX);

        autoTranslate(translationBuilder, ModItems.CRAFTING_TOKEN);
        autoTranslate(translationBuilder, ModItems.SMELTING_TOKEN);
        autoTranslate(translationBuilder, ModItems.SMITHING_TOKEN);

        autoTranslate(translationBuilder, ModBlocks.WOODEN_CASING);
        autoTranslate(translationBuilder, ModBlocks.MINERAL_MIXED_WOODEN_CASING);
        autoTranslate(translationBuilder, ModBlocks.STONE_CASING);
        autoTranslate(translationBuilder, ModBlocks.OBSIDIAN_CASING);



        translationBuilder.add("creativeTab.francium", "Francium");
    }

    private void pileTranslate(TranslationBuilder builder, Item item, String material) {
        builder.add(item, "Pile Of " + material);
    }

    private void autoTranslate(TranslationBuilder builder, Block block) {
        String snakeCase = Francium.getPath(block);
        String replaceUnderscores = snakeCase.replace('_', ' ');
        String[] words = replaceUnderscores.split(" ");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1))
                        .append(" ");
            }
        }
        String titleCase = result.toString().trim();

        builder.add(block, titleCase);
    }
    private void autoTranslate(TranslationBuilder builder, Item item) {
        String snakeCase = Francium.getPath(item);
        String replaceUnderscores = snakeCase.replace('_', ' ');
        String[] words = replaceUnderscores.split(" ");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1))
                        .append(" ");
            }
        }
        String titleCase = result.toString().trim();

        builder.add(item, titleCase);
    }
}
