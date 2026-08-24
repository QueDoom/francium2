package net.quedoom.francium;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.quedoom.francium.block.entity.DeepMergerRenderer;
import net.quedoom.francium.block.entity.GlueMixerRenderer;
import net.quedoom.francium.block.menu.DeepMergerScreen;
import net.quedoom.francium.block.menu.WoodenMergerMenu;
import net.quedoom.francium.block.menu.WoodenMergerScreen;
import net.quedoom.francium.init.ModBlockEntities;
import net.quedoom.francium.init.ModMenuTypes;

public class FranciumClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRenderers.register(ModBlockEntities.DEEP_MERGER_ENTITY, DeepMergerRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.GLUE_MIXER_ENTITY, GlueMixerRenderer::new);

        MenuScreens.register(ModMenuTypes.WOODEN_MERGER, WoodenMergerScreen::new);
        MenuScreens.register(ModMenuTypes.DEEP_MERGER, DeepMergerScreen::new);
    }
}
