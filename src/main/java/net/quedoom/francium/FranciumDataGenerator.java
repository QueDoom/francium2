package net.quedoom.francium;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.registries.Registries;
import net.quedoom.francium.datagen.*;
import net.quedoom.francium.init.ModConfiguredFeatures;
import net.quedoom.francium.init.ModPlacedFeatures;
import net.quedoom.quet.datagen.QTRegistryBuilder;
import net.quedoom.quet.datagen.QueTDataGeneratorEntrypoint;

public class FranciumDataGenerator extends QueTDataGeneratorEntrypoint {
	@Override
	protected void doDatagen(FabricDataGenerator fabricDataGenerator, FabricDataGenerator.Pack pack) {
		pack.addProvider(ModAdvancements::new);
		pack.addProvider(BlockTagGen::new);
		pack.addProvider(ItemTagGen::new);
		pack.addProvider(Francium2LanguageProvider::new);
		pack.addProvider(Loot::new);
		pack.addProvider(Models::new);
		pack.addProvider(RecipeGen::new);
		pack.addProvider(FranciumRegistryDataProvider::new);
	}

	@Override
	protected void buildRegistry(QTRegistryBuilder qtRegistryBuilder) {
		qtRegistryBuilder.add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);
		qtRegistryBuilder.add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap);
	}
}
