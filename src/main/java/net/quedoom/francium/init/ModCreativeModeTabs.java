package net.quedoom.francium.init;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.quedoom.francium.Francium;

public class ModCreativeModeTabs {

    public static final ResourceKey<CreativeModeTab> FRANCIUM_TAB_KEY = ResourceKey.create(
            BuiltInRegistries.CREATIVE_MODE_TAB.key(), Francium.id("francium_tab")
    );

  public static final CreativeModeTab FRANCIUM_TAB = FabricCreativeModeTab.builder()
          .icon(() -> new ItemStack(ModItems.DIRT_PILE))
          .title(Component.translatable("creativeTab.francium"))
          .displayItems(((parameters, output) -> {
              output.accept(ModItems.BROKEN_STICK);
              output.accept(ModItems.SHARP_STICK);
              output.accept(ModItems.WOODEN_SHEARS);
              output.accept(ModItems.FIRE_STARTER);
              output.accept(ModItems.BARK);
              output.accept(ModItems.LEAF);
              output.accept(ModItems.GRASS);

              output.accept(ModItems.WOODEN_PLATE);

              output.accept(ModBlocks.ECHO_BLOCK);

              output.accept(ModBlocks.GLUE_MIXER);
              output.accept(ModItems.GLUE);
              output.accept(ModItems.VEGAN_GLUE);
              output.accept(ModItems.SUPER_GLUE);
              output.accept(ModItems.ECHO_GLUE);

              output.accept(ModItems.ANDESITE_ALLOY);
              output.accept(ModItems.DIORITE_ALLOY);
              output.accept(ModItems.GRANITE_ALLOY);
              output.accept(ModItems.MINERAL_MIX);
              output.accept(ModItems.DRIPSTONE_PASTE);
              output.accept(ModItems.DRIPSTONE_COATED_MINERAL_MIX);

              output.accept(ModItems.SLOT);
              output.accept(ModItems.STACKED_SLOT);
              output.accept(ModItems.RAW_SLOT);
              output.accept(ModItems.STACKED_RAW_SLOT);

              output.accept(ModBlocks.WOODEN_MERGER);
              output.accept(ModBlocks.DEEP_MERGER);

              output.accept(ModItems.CRAFTING_TOKEN);
              output.accept(ModItems.SMELTING_TOKEN);
              output.accept(ModItems.SMITHING_TOKEN);

              output.accept(ModBlocks.WOODEN_CASING);
              output.accept(ModBlocks.MINERAL_MIXED_WOODEN_CASING);
              output.accept(ModBlocks.MINERAL_MIX_BLOCK);
              output.accept(ModBlocks.STONE_CASING);
              output.accept(ModBlocks.OBSIDIAN_CASING);


              output.accept(ModItems.DIRT_PILE);
              output.accept(ModItems.SAWDUST);
              output.accept(ModItems.GRAVEL_PILE);
              output.accept(ModItems.SAND_PILE);
              output.accept(ModItems.GLASS_SHARDS);
              output.accept(ModItems.ANDESITE_PILE);
              output.accept(ModItems.DIORITE_PILE );
              output.accept(ModItems.GRANITE_PILE);
              output.accept(ModItems.COAL_DUST);
              output.accept(ModItems.GOLD_ORE_PILE);
              output.accept(ModItems.DEEPSLATE_GOLD_ORE_PILE);
              output.accept(ModItems.GOLD_DUST);
              output.accept(ModItems.COPPER_ORE_PILE);
              output.accept(ModItems.DEEPSLATE_COPPER_ORE_PILE);
              output.accept(ModItems.COPPER_DUST);
              output.accept(ModItems.IRON_ORE_PILE);
              output.accept(ModItems.DEEPSLATE_IRON_ORE_PILE);
              output.accept(ModItems.IRON_DUST);
              output.accept(ModItems.DIAMOND_ORE_PILE);
              output.accept(ModItems.DEEPSLATE_DIAMOND_ORE_PILE);
              output.accept(ModItems.DIAMOND_DUST);
              output.accept(ModItems.DEEPSLATE_PILE);
              output.accept(ModItems.TUFF_PILE);
              output.accept(ModItems.TUFF_ZONG);
              output.accept(ModItems.CALCITE_PILE);
              output.accept(ModItems.AMETHYST_PILE);
              output.accept(ModItems.DRIPSTONE_PILE);
              output.accept(ModItems.NETHERRACK_PILE);
              output.accept(ModItems.BLACKSTONE_PILE);
              output.accept(ModItems.SOUL_PILE);
              output.accept(ModItems.BASALT_PILE);

              output.accept(ModItems.BEDROCK_PILE);
              output.accept(ModItems.BEDROCK_PEBBLES);
              output.accept(ModItems.BEDROCK_FLAKE);



          })).build();

    public static void registerTabs() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, FRANCIUM_TAB_KEY, FRANCIUM_TAB);
    }

}
