package net.quedoom.francium.datagen.advancement_trigger;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.criterion.ContextAwarePredicate;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.SimpleCriterionTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

public class CraftCasingTrigger extends SimpleCriterionTrigger<CraftCasingTrigger.TriggerInstance> {

    @Override
    public Codec<TriggerInstance> codec() {
        return TriggerInstance.CODEC;
    }

    public record TriggerInstance(Optional<ContextAwarePredicate> player, Block block)
            implements SimpleCriterionTrigger.SimpleInstance {

        public static final Codec<TriggerInstance> CODEC = RecordCodecBuilder.create(i -> i.group(
                EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(TriggerInstance::player),
                BuiltInRegistries.BLOCK.byNameCodec().fieldOf("casing_type").forGetter(TriggerInstance::block)
        ).apply(i, TriggerInstance::new));

        public boolean matches(BlockState block) {
            return block.is(this.block);
        }
    }

    public void trigger(ServerPlayer player, BlockState state) {
        this.trigger(player, instance -> instance.matches(state));
    }
}
