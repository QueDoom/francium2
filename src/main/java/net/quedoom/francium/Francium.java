package net.quedoom.francium;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.recipe.v1.sync.RecipeSynchronization;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Block;
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


	@Override
	public void onInitialize() {

//		boolean isLoaded = FabricLoader.getInstance().isModLoaded("");

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


	}
}
