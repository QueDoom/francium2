package net.quedoom.francium.datagen.advancement_trigger;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.criterion.ContextAwarePredicate;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.SimpleCriterionTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public class MakeGlueTrigger extends SimpleCriterionTrigger<MakeGlueTrigger.TriggerInstance> {

    @Override
    public Codec<TriggerInstance> codec() {
        return TriggerInstance.CODEC;
    }

    public record TriggerInstance(Optional<ContextAwarePredicate> player, Item item)
            implements SimpleInstance {

        public static final Codec<TriggerInstance> CODEC = RecordCodecBuilder.create(i -> i.group(
                EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(TriggerInstance::player),
                BuiltInRegistries.ITEM.byNameCodec().fieldOf("glue_type").forGetter(TriggerInstance::item)
        ).apply(i, TriggerInstance::new));

        public boolean matches(ItemStack stack) {
            return stack.is(this.item);
        }
    }

    public void trigger(ServerPlayer player, ItemStack stack) {
        this.trigger(player, instance -> instance.matches(stack));
    }
}
