package net.quedoom.francium;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.recipe.v1.sync.RecipeSynchronization;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Block;
import net.quedoom.francium.init.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Francium implements ModInitializer {
	public static final String MOD_ID = "francium_2";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static String getPath(Block block) {
		return BuiltInRegistries.BLOCK.getKey(block).getPath();
	}

	public static String getPath(Item item) {
		return BuiltInRegistries.ITEM.getKey(item).getPath();
	}

	public static String getPath(TagKey<?> tagKey) {
		return tagKey.location().getPath();
	}

	@Override
	public void onInitialize() {

		ModItems.registerItems();
		ModCreativeModeTabs.registerTabs();

		ModBlocks.registerBlocks();
		ModBlockEntities.registerBlockEntities();

		ModRecipeTypes.registerRecipeTypes();

		RecipeSynchronization.synchronizeRecipeSerializer(ModRecipeTypes.WOODEN_MERGING_SERIALIZER);

		ModMenuTypes.registerMenus();

		ModStats.registerStats();
		ModLootTables.registerLootTables();


	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
	public static String jeiId(String path) {
		return "jei." + MOD_ID + '.' + path;
	}
}
