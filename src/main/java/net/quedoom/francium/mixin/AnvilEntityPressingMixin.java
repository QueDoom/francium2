package net.quedoom.francium.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.quedoom.francium.recipe.AnvilPressing;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FallingBlockEntity.class)
public abstract class AnvilEntityPressingMixin extends Entity {
    @Shadow private BlockState blockState;

    public AnvilEntityPressingMixin(EntityType<?> type, Level level) { super(type, level); }

    @ModifyExpressionValue(method = "tick", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/level/block/state/BlockState;canBeReplaced(Lnet/minecraft/world/item/context/BlockPlaceContext;)Z"))
    private boolean francium$canBeReplaced(boolean original) {
        return original || pressingMatches();
    }

    @ModifyExpressionValue(method = "tick", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/level/block/state/BlockState;canSurvive(Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;)Z"))
    private boolean francium$canSurvive(boolean original) {
        return original || pressingMatches();
    }

    @Unique
    private boolean pressingMatches() {
        if (!(this.level() instanceof ServerLevel server)) return false;
        if (!this.blockState.is(BlockTags.ANVIL)) return false;
        BlockPos pos = this.blockPosition();
        return AnvilPressing.find(server, pos, server.getBlockState(pos), AnvilPressing.itemsTall(server, pos.below(), 2)).isPresent();
    }
}

