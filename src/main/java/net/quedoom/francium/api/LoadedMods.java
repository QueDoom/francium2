package net.quedoom.francium.api;

import net.fabricmc.loader.api.FabricLoader;

public class LoadedMods {
    public static final boolean rrv = FabricLoader.getInstance().isModLoaded("rrv");
    public static final boolean toughasnails = FabricLoader.getInstance().isModLoaded("toughasnails");

    public static void set() {
    }
}
