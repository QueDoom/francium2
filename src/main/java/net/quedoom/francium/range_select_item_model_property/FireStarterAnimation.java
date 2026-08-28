package net.quedoom.francium.range_select_item_model_property;

import com.mojang.serialization.MapCodec;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.numeric.BundleFullness;
import net.minecraft.client.renderer.item.properties.numeric.RangeSelectItemModelProperty;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.item.ItemStack;
import net.quedoom.francium.item.FireStarterItem;
import org.jspecify.annotations.Nullable;

@Environment(EnvType.CLIENT)
public record FireStarterAnimation() implements RangeSelectItemModelProperty {
    public static final MapCodec<FireStarterAnimation> MAP_CODEC = MapCodec.unit(new FireStarterAnimation());

    public FireStarterAnimation() {
    }

    @Override
    public float get(ItemStack itemStack, @Nullable ClientLevel level, @Nullable ItemOwner owner, int seed) {
        return FireStarterItem.getAnimationTick(itemStack);
    }

    @Override
    public MapCodec<? extends RangeSelectItemModelProperty> type() {
        return MAP_CODEC ;
    }
}
