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
import net.quedoom.quet.init.ModRegistrator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Francium extends ModRegistrator implements ModInitializer {
	public static final String MOD_ID = setNamespace("francium_2");
	public static final String CONSTANT_MOD_ID = "francium_2";
	public static final Logger LOGGER = logger();

	@Override
	public void onInitialize() {
		LOGGER.info(namespace());

//		boolean isLoaded = FabricLoader.getInstance().isModLoaded("");

		QTTranslationBuilder.SHOULD_AUTO_TRANSLATE_BY_DEFAULT = true;
		ModItems.register();
		ModCreativeModeTabs.registerTabs();

		ModBlocks.register();
		ModBlockEntities.registerBlockEntities();

		ModEntityTypes.registerEntityTypes();

		ModRecipeTypes.registerRecipeTypes();

		RecipeSynchronization.synchronizeRecipeSerializer(ModRecipeTypes.WOODEN_MERGING_SERIALIZER);

		ModMenuTypes.registerMenus();

		ModStats.register();
		ModLootTables.registerLootTables();


	}
}
