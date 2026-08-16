package net.quedoom.francium.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.resources.Identifier;
import net.quedoom.francium.Francium;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.francium.init.ModItems;

import java.util.Optional;

public class Models extends FabricModelProvider {
    public Models(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        blockModelGenerators.family(ModBlocks.WOODEN_CASING);
        blockModelGenerators.family(ModBlocks.MINERAL_MIXED_WOODEN_CASING);
        blockModelGenerators.family(ModBlocks.STONE_CASING);
        blockModelGenerators.family(ModBlocks.OBSIDIAN_CASING);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(ModItems.DIRT_PILE, PILE_FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.SAWDUST, PILE_FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.BROKEN_STICK, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.GRAVEL_PILE, PILE_FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.ANDESITE_PILE, PILE_FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.DIORITE_PILE, PILE_FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.GRANITE_PILE, PILE_FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.GOLD_ORE_PILE, PILE_FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.DEEPSLATE_GOLD_ORE_PILE, PILE_FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.GOLD_DUST, PILE_FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.IRON_ORE_PILE, PILE_FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.DEEPSLATE_IRON_ORE_PILE, PILE_FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.IRON_DUST, PILE_FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.COPPER_ORE_PILE, PILE_FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.DEEPSLATE_COPPER_ORE_PILE, PILE_FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.COPPER_DUST, PILE_FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.DIAMOND_ORE_PILE, PILE_FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.DEEPSLATE_DIAMOND_ORE_PILE, PILE_FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.DIAMOND_DUST, PILE_FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.DEEPSLATE_PILE, PILE_FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.TUFF_PILE, PILE_FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.CALCITE_PILE, PILE_FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.DRIPSTONE_PILE, PILE_FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.NETHERRACK_PILE, PILE_FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.BLACKSTONE_PILE, PILE_FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.SOUL_PILE, PILE_FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.BASALT_PILE, PILE_FLAT_ITEM);

        itemModelGenerators.generateFlatItem(ModItems.BARK, ModelTemplates.FLAT_ITEM);

        itemModelGenerators.generateFlatItem(ModItems.SHARP_STICK, ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModelGenerators.generateFlatItem(ModItems.ANDESITE_ALLOY, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.DIORITE_ALLOY, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.GRANITE_ALLOY, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.MINERAL_MIX, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.DRIPSTONE_PASTE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.DRIPSTONE_COATED_MINERAL_MIX, ModelTemplates.FLAT_ITEM);

        itemModelGenerators.generateFlatItem(ModItems.CRAFTING_TOKEN, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.SMELTING_TOKEN, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.SMITHING_TOKEN, ModelTemplates.FLAT_ITEM);
    }
    
    public static final ModelTemplate PILE_FLAT_ITEM = createItem("pile_generated", TextureSlot.LAYER0);

    private static ModelTemplate createItem(final String id, final TextureSlot... slots) {
        return new ModelTemplate(Optional.of(Francium.id("item/" + id)), Optional.empty(), slots);
    }
    
    
}
