package net.quedoom.francium.mixin;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.quedoom.francium.item.vanilla.StickItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.lang.reflect.Method;
import java.util.function.Function;

@Mixin(Items.class)
public class SharpenSticksMixin {

    @Inject(method = "registerItem(Ljava/lang/String;)Lnet/minecraft/world/item/Item;",
            at = @At(value = "HEAD"), cancellable = true)

    private static void registerItem(String name, CallbackInfoReturnable<Item> cir) {
        if (name.equals("stick")) {
            final Function<Item.Properties, Item> itemFactory = StickItem::new;
            final Item.Properties properties = new Item.Properties();
            ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, Identifier.withDefaultNamespace(name));

            final Item item = itemFactory.apply(properties.setId(key));
            cir.setReturnValue(Registry.register(BuiltInRegistries.ITEM, key, item));
        }
    }
}
