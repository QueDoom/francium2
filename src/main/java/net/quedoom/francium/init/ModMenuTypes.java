package net.quedoom.francium.init;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.quedoom.francium.Francium;
import net.quedoom.francium.block.menu.*;

public class ModMenuTypes {

    public static final MenuType<WoodenMergerMenu> WOODEN_MERGER = register("wooden_merger", WoodenMergerMenu::new);
    public static final MenuType<DeepMergerMenu> DEEP_MERGER = register("deep_merger", DeepMergerMenu::new);

    public static <T extends AbstractContainerMenu> MenuType<T> register(
            String name,
            MenuType.MenuSupplier<T> constructor
    ) {
        return Registry.register(BuiltInRegistries.MENU, Francium.id(name), new MenuType<>(constructor, FeatureFlagSet.of()));
    }

    public static void registerMenus() {}

}
