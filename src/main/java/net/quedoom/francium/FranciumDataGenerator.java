package net.quedoom.francium;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.quedoom.francium.datagen.*;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.francium.init.ModItems;
import net.quedoom.quet.datagen.QTRegistryBuilder;
import net.quedoom.quet.datagen.QueTDataGeneratorEntrypoint;
import net.quedoom.quet.datagen.lang.QTTranslationBuilder;
import net.quedoom.quet.datagen.lang.QueTLanguageProvider;
import net.quedoom.quet.init.ModRegistrator;
import net.quedoom.quet.misc.QueTObjectStorage;

public class FranciumDataGenerator extends QueTDataGeneratorEntrypoint {
	@Override
	protected void doDatagen(FabricDataGenerator fabricDataGenerator, FabricDataGenerator.Pack pack) {
		pack.addProvider(BlockTagGen::new);
		pack.addProvider(ItemTagGen::new);
		pack.addProvider(Francium2LanguageProvider::new);
		pack.addProvider(Loot::new);
		pack.addProvider(Models::new);
		pack.addProvider(RecipeGen::new);
		pack.addProvider(ModAdvancements::new);
	}

	@Override
	protected void buildRegistry(QTRegistryBuilder qtRegistryBuilder) {

	}
}
