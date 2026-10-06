package net.quedoom.francium.api.rrv;

import cc.cassian.rrv.api.ReliableRecipeViewerPlugin;
import cc.cassian.rrv.common.recipe.ServerRecipeManager;
import net.quedoom.francium.Francium;
import net.quedoom.francium.init.ModRecipeTypes;
import net.quedoom.quet.init.ModRegistrator;

public class FranciumRRVIntegration implements ReliableRecipeViewerPlugin {
    @Override
    public void onIntegrationInitialize() {
        ServerRecipeManager.INSTANCE.synchronizeRecipeType(ModRecipeTypes.WOODEN_MERGING_SERIALIZER, ModRecipeTypes.WOODEN_MERGING);
        ServerRecipeManager.INSTANCE.synchronizeRecipeType(ModRecipeTypes.DEEP_MERGING_SERIALIZER, ModRecipeTypes.DEEP_MERGING);
        ServerRecipeManager.INSTANCE.synchronizeRecipeType(ModRecipeTypes.GLUE_MIXING_SERIALIZER, ModRecipeTypes.GLUE_MIXING);
        ServerRecipeManager.INSTANCE.synchronizeRecipeType(ModRecipeTypes.BASIC_ANVIL_PRESSING_SERIALIZER, ModRecipeTypes.BASIC_ANVIL_PRESSING);
        ServerRecipeManager.INSTANCE.synchronizeRecipeType(ModRecipeTypes.TWO_BLOCK_ANVIL_PRESSING_SERIALIZER, ModRecipeTypes.TWO_BLOCK_ANVIL_PRESSING);
        ServerRecipeManager.INSTANCE.synchronizeRecipeType(ModRecipeTypes.BURNING_SERIALIZER, ModRecipeTypes.BURNING);
    }
}
