package net.quedoom.francium.util;

import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.fabricmc.fabric.api.resource.v1.pack.PackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.chat.Component;
import net.quedoom.quet.init.ModRegistrator;

public record Packs(ModRegistrator registrator) {
    public Packs(String namespace) {
        this(new ModRegistrator(namespace));
    }

    public void add(String  name) {
        add(name, PackActivationType.ALWAYS_ENABLED);
    }
    public void add(String  name, PackActivationType type) {
        FabricLoader.getInstance().getModContainer(registrator().namespace()).ifPresent(container ->
                ResourceLoader.registerBuiltinPack(registrator.of(name), container, Component.literal(name), type)
        );
    }

    public void add(PackActivationType type, String... names) {
        for (String name : names) {
            add(name, type);
        }
    }
    public void add(String... names) {
        for (String name : names) {
            add(name);
        }
    }
}
