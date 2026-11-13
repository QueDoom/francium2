package net.quedoom.francium.init;

import net.fabricmc.fabric.api.client.model.loading.v1.ExtraModelKey;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.model.loading.v1.SimpleUnbakedExtraModel;
import net.minecraft.client.renderer.block.dispatch.BlockStateModel;
import net.quedoom.francium.Francium;

public class ModBlockStateModels {
    public static final ExtraModelKey<BlockStateModel> FAN_BLADE = create("fan_blade");

    public static void register() {
        register(FAN_BLADE, "fan_blade");
    }

    private static void register(ExtraModelKey<BlockStateModel> key, String name) {
        ModelLoadingPlugin.register(ctx ->
                ctx.addModel(key, SimpleUnbakedExtraModel.blockStateModel(
                        Francium.of("block/" + name)
                )));
    }

    public static ExtraModelKey<BlockStateModel> create(String name) {
        return ExtraModelKey.create(() -> Francium.MOD_ID + ":block/" + name);
    }
}