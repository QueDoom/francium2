package net.quedoom.francium.api.rrv;

import cc.cassian.rrv.api.ReliableRecipeViewerPlugin;
import cc.cassian.rrv.common.recipe.ServerRecipeManager;
import net.quedoom.francium.Francium;
import net.quedoom.francium.init.ModRecipeTypes;
import net.quedoom.quet.init.ModRegistrator;

public class FranciumRRVIntegration implements ReliableRecipeViewerPlugin {
    @Override
    public void onIntegrationInitialize() {
        ModRegistrator.setNamespace(Francium.CONSTANT_MOD_ID);
        ServerRecipeManager.INSTANCE.synchronizeRecipeType(ModRecipeTypes.WOODEN_MERGING_SERIALIZER, ModRecipeTypes.WOODEN_MERGING);
        ServerRecipeManager.INSTANCE.synchronizeRecipeType(ModRecipeTypes.DEEP_MERGING_SERIALIZER, ModRecipeTypes.DEEP_MERGING);
    }
}
