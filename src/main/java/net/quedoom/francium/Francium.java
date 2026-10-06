package net.quedoom.francium;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.recipe.v1.sync.RecipeSynchronization;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.quedoom.francium.api.LoadedMods;
import net.quedoom.francium.init.*;
import net.quedoom.quet.datagen.lang.QTTranslationBuilder;
import net.quedoom.quet.datagen.lang.QueTLanguageProvider;
import net.quedoom.quet.init.ModRegistrator;
import net.quedoom.quet.misc.QueTObjectStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Francium implements ModInitializer {
	public static final String MOD_ID = "francium_2";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Identifier of(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}

	public static Component translatable(String suffix, String prefix) {
		return Component.translatable(translationString(suffix, prefix));
	}

	public static String translationString(String prefix, String suffix) {
		return prefix + '.' + MOD_ID + '.' + suffix;
	}

	@Override
	public void onInitialize() {
		LoadedMods.set();

		QTTranslationBuilder.SHOULD_AUTO_TRANSLATE_BY_DEFAULT = true;
		ModItems.registerItem();
		ModCreativeModeTabs.registerTabs();

		ModBlocks.registerBlock();
		ModBlockEntities.registerBlockEntities();

		ModEntityTypes.registerEntityTypes();

		ModRecipeTypes.registerRecipeTypes();

		RecipeSynchronization.synchronizeRecipeSerializer(ModRecipeTypes.WOODEN_MERGING_SERIALIZER);

		ModMenuTypes.registerMenus();

		ModStats.registerStats();
		ModLootTables.registerLootTables();

		FlammableBlockRegistry.getDefaultInstance().add(Blocks.CACTUS, 5, 5);


	}
}
