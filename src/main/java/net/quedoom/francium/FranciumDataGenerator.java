package net.quedoom.francium;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.quedoom.francium.datagen.*;

public class FranciumDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(BlockTagGen::new);
		pack.addProvider(ItemTagGen::new);
		pack.addProvider(Lang::new);
		pack.addProvider(Loot::new);
		pack.addProvider(Models::new);
		pack.addProvider(RecipeGen::new);


	}
}
