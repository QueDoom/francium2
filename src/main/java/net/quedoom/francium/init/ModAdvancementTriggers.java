package net.quedoom.francium.init;

import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.quedoom.francium.Francium;
import net.quedoom.francium.datagen.advancement_trigger.CraftCasingTrigger;
import net.quedoom.francium.datagen.advancement_trigger.MakeGlueTrigger;

public class ModAdvancementTriggers {
    public static final CraftCasingTrigger CRAFT_CASING_EVENT = register("craft_casing_event", new CraftCasingTrigger());
    public static final MakeGlueTrigger MAKE_GLUE_EVENT = register("make_glue_event", new MakeGlueTrigger());

    private static <T extends CriterionTrigger<?>> T register(String name, T trigger) {
        return Registry.register(BuiltInRegistries.TRIGGER_TYPES,
                Francium.of(name), trigger);
    }

    public static void init() {}
}
