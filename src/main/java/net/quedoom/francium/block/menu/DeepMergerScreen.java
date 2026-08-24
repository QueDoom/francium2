package net.quedoom.francium.block.menu;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.ItemCombinerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.quedoom.francium.Francium;

public class DeepMergerScreen extends ItemCombinerScreen<DeepMergerMenu> {
    public DeepMergerScreen(DeepMergerMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title, Francium.id("textures/gui/deep_merger.png"));
    }

    @Override
    protected void extractErrorIcon(GuiGraphicsExtractor graphics, int xo, int yo) {

    }
}
