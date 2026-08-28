package net.quedoom.francium.mixin;

import com.mojang.serialization.MapCodec;
import net.minecraft.client.renderer.item.properties.numeric.RangeSelectItemModelProperties;
import net.minecraft.client.renderer.item.properties.numeric.RangeSelectItemModelProperty;
import net.minecraft.data.Main;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ExtraCodecs;
import net.quedoom.francium.Francium;
import net.quedoom.francium.range_select_item_model_property.FireStarterAnimation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RangeSelectItemModelProperties.class)
public class AddModRangeSelectItemModelPropertiesMixin {
    @Shadow
    @Final
    public static ExtraCodecs.LateBoundIdMapper<Identifier, MapCodec<? extends RangeSelectItemModelProperty>> ID_MAPPER;

    @Inject(method = "bootstrap",
            at = @At(value = "TAIL"))
    private static void addItemModelProperties(CallbackInfo ci) {
        ID_MAPPER.put(Francium.id("fire_starter_anim"), FireStarterAnimation.MAP_CODEC);
    }
}
