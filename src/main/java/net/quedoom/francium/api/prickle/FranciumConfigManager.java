package net.quedoom.francium.api.prickle;

import com.google.gson.*;
import net.darkhax.pricklemc.common.api.annotations.Value;
import net.darkhax.pricklemc.common.api.config.ConfigManager;
import net.darkhax.pricklemc.common.impl.PrickleMod;
import net.fabricmc.loader.api.FabricLoader;
import net.quedoom.francium.Francium;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

public class FranciumConfigManager {
    private static final String CONFIG_ID = "francium";
    public static FranciumConfigs CONFIG = ConfigManager.load(CONFIG_ID, new FranciumConfigs());
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    private static Path path() {
        return FabricLoader.getInstance().getConfigDir().resolve(CONFIG_ID + ".json");
    }

    public static void load() {
        CONFIG = ConfigManager.load(CONFIG_ID, new FranciumConfigs());
    }

    public static void save() {
        Path path = path();
        try {
            JsonObject root;
            try (BufferedReader reader = Files.newBufferedReader(path)) {
                root = JsonParser.parseReader(reader).getAsJsonObject();
            }

            for (Field field : FranciumConfigs.class.getDeclaredFields()) {
                Value meta = field.getAnnotation(Value.class);
                if (meta == null) continue;
                field.setAccessible(true);

                String key = findKey(root, field, meta);
                JsonObject entry = root.has(key) && root.get(key).isJsonObject()
                        ? root.getAsJsonObject(key)
                        : new JsonObject();

                JsonElement value = GSON.toJsonTree(field.get(CONFIG));
                entry.add("value", value);
                root.add(key, entry);
            }

            try (BufferedWriter writer = Files.newBufferedWriter(path)) {
                GSON.toJson(root, writer);
            }
        }
        catch (IOException | IllegalAccessException e) {
            PrickleMod.LOG.error("Couldn't save Francium config!");
            e.printStackTrace();
        }
    }

    private static String findKey(JsonObject root, Field field, Value meta) {
        String snake = field.getName().replaceAll("([a-z0-9])([A-Z])", "$1_$2").toLowerCase(Locale.ROOT);
        for (String candidate : new String[]{meta.name(), field.getName(), snake}) {
            if (!candidate.isEmpty() && root.has(candidate)) return candidate;
        }
        return meta.name().isEmpty() ? field.getName() : meta.name();
    }

    public static void init() {
        PrickleMod.LOG.info("Loading Configs for Francium...");
    }
}
