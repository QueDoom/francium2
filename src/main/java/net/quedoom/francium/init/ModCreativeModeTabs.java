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
              output.accept(ModItems.BARK);
              output.accept(ModItems.LEAF);

              output.accept(ModItems.WOODEN_PLATE);

              output.accept(ModItems.ANDESITE_ALLOY);
              output.accept(ModItems.DIORITE_ALLOY);
              output.accept(ModItems.GRANITE_ALLOY);
              output.accept(ModItems.MINERAL_MIX);
              output.accept(ModItems.DRIPSTONE_PASTE);
              output.accept(ModItems.DRIPSTONE_COATED_MINERAL_MIX);

              output.accept(ModItems.CRAFTING_TOKEN);
              output.accept(ModItems.SMELTING_TOKEN);
              output.accept(ModItems.SMITHING_TOKEN);

              output.accept(ModBlocks.WOODEN_CASING);
              output.accept(ModBlocks.MINERAL_MIXED_WOODEN_CASING);
              output.accept(ModBlocks.STONE_CASING);
              output.accept(ModBlocks.OBSIDIAN_CASING);


              output.accept(ModItems.DIRT_PILE);
              output.accept(ModItems.SAWDUST);
              output.accept(ModItems.GRAVEL_PILE);
              output.accept(ModItems.ANDESITE_PILE);
              output.accept(ModItems.DIORITE_PILE );
              output.accept(ModItems.GRANITE_PILE);
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
              output.accept(ModItems.CALCITE_PILE);
              output.accept(ModItems.DRIPSTONE_PILE);
              output.accept(ModItems.NETHERRACK_PILE);
              output.accept(ModItems.BLACKSTONE_PILE);
              output.accept(ModItems.SOUL_PILE);
              output.accept(ModItems.BASALT_PILE);
          })).build();

    public static void registerItemGroups() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, FRANCIUM_TAB_KEY, FRANCIUM_TAB);
    }

}
