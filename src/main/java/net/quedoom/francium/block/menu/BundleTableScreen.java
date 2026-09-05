package net.quedoom.francium.block.menu;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.ContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.quedoom.francium.Francium;
import net.quedoom.francium.init.ModMenuTypes;

public class BundleTableScreen extends AbstractContainerScreen<BundleTableMenu> {
    private static final Identifier CONTAINER_BACKGROUND = Francium.id("textures/gui/bundle_table.png");
    private final int containerRows;

    public BundleTableScreen(BundleTableMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 176, 166);
        this.containerRows = 2;
        this.inventoryLabelY = this.imageHeight - 94;
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);
        int xo = (this.width - this.imageWidth) / 2;
        int yo = (this.height - this.imageHeight) / 2;
        graphics.blit(RenderPipelines.GUI_TEXTURED, CONTAINER_BACKGROUND, xo, yo, 0, 0, this.imageWidth, this.imageHeight, 256, 256);
//        graphics.blit(RenderPipelines.GUI_TEXTURED, CONTAINER_BACKGROUND, xo, yo + this.containerRows + 18 * 17, 0, 126, this.imageWidth, 96, 256, 256);
    }
}
