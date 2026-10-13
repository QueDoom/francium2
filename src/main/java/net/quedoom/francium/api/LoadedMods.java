package net.quedoom.francium.api;

import net.fabricmc.loader.api.FabricLoader;

public class LoadedMods {
    public static final boolean RRV = FabricLoader.getInstance().isModLoaded("rrv");
    public static final boolean TOUGH_AS_NAILS = FabricLoader.getInstance().isModLoaded("toughasnails");

    public static void set() {
    }
}
