package net.quedoom.francium.api.modmenu;

import com.terraformersmc.modmenu.ModMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.network.chat.Component;
import net.quedoom.francium.Francium;
import net.quedoom.francium.api.prickle.FranciumConfigManager;
import net.quedoom.francium.api.prickle.FranciumConfigs;

import java.util.List;

public class FranciumOptionsScreen extends OptionsSubScreen {
    public FranciumOptionsScreen(Screen lastScreen) {
        super(lastScreen, Minecraft.getInstance().options, Component.translatable(Francium.MOD_ID+ ".options"));
    }

    @Override
    protected void addOptions() {
        if (this.list == null) return;
        List<AbstractWidget> widgets = FranciumConfigs.asWidgets(this.options);
        for (int i = 0; i < widgets.size(); i += 2) {
            AbstractWidget left = widgets.get(i);
            AbstractWidget right = i + 1 < widgets.size() ? widgets.get(i + 1) : null;
            this.list.addSmall(left, right); // adjust to the overload that actually exists
        }
    }

    @Override
    public void added() {
        ModMenu.LOGGER.info("Opened Config Screen for Francium 2");
    }

    @Override
    public void removed() {
        FranciumConfigManager.save();
        FranciumConfigManager.load();
    }
}
