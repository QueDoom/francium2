package net.quedoom.francium.init;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Function;
import java.util.function.Supplier;

public record ReRegistrator(ResourceKey<Block> id, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties, CallbackInfoReturnable<?> cir) {
    public BlockBehaviour.Properties getCopyOfProperty() {
        Supplier<BlockBehaviour.Properties> base = () -> properties;
        return base.get();
    }

    public String path() {
        return id.identifier().getPath();
    }

    public void reRegisterWithProperty(BlockBehaviour.Properties properties) {

    }

    public void reRegister(String nameToMatch, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties) {
    }
}
