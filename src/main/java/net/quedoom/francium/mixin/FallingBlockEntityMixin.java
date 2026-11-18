package net.quedoom.francium.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.phys.Vec3;
import net.quedoom.francium.block.FallingSlabBlock;
import net.quedoom.francium.block.entity.BedrockPlateauEntity;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.francium.recipe.AnvilPressing;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Debug(export = true)
@Mixin(FallingBlockEntity.class)
public abstract class FallingBlockEntityMixin extends Entity {
    @Shadow private BlockState blockState;

    @Shadow
    public static FallingBlockEntity fall(Level level, BlockPos pos, BlockState state) {
        throw new UnsupportedOperationException("Implemented via mixin");
    }

    public FallingBlockEntityMixin(EntityType<?> type, Level level) { super(type, level); }

    @Unique
    private boolean francium$merge;

    @ModifyExpressionValue(method = "tick", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/level/block/state/BlockState;canBeReplaced(Lnet/minecraft/world/item/context/BlockPlaceContext;)Z"))
    private boolean francium$canBeReplaced(boolean original) {
        francium$merge = isFallableSlabAndMatching();
        return original || pressingMatches() || francium$merge;
    }


    @ModifyExpressionValue(method = "tick", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/level/block/state/BlockState;canSurvive(Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;)Z"))
    private boolean francium$canSurvive(boolean original) {
        return original || pressingMatches() || francium$merge;
    }

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;canBeReplaced(Lnet/minecraft/world/item/context/BlockPlaceContext;)Z"))
    private void combineSlabs(CallbackInfo ci) {
        if (francium$merge) {
            ((FallingSlabBlock) this.blockState.getBlock()).makeFull(this.level(), this.blockPosition());
            this.discard();
        }
    }

    @Inject(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/item/FallingBlockEntity;discard()V",
                    ordinal = 3
            ),
            cancellable = true
    )
    private void bedrockCrusher(CallbackInfo ci) {
        Level level = this.level();
        BlockPos pos = this.blockPosition();
        if (level.getBlockEntity(pos) instanceof BedrockPlateauEntity plateau
                && plateau.crush(level, pos, this.fallDistance)) {
            this.discard();
            ci.cancel();
        }
    }

    @Unique
    private BlockPos getBlockPos() {
        Vec3 vecFloat = position();
        Vec3i vecInt = new Vec3i(((int) vecFloat.x), ((int) vecFloat.y), ((int) vecFloat.z));
        return new BlockPos(vecInt);
    }

    @Unique
    private boolean isFallableSlabAndMatching() {
        if (!(this.blockState.getBlock() instanceof FallingSlabBlock)) return false;
        BlockState current = this.level().getBlockState(this.blockPosition());
        return current.is(this.blockState.getBlock())
                && current.getValue(SlabBlock.TYPE) == SlabType.BOTTOM
                && this.blockState.getValue(SlabBlock.TYPE) == SlabType.BOTTOM;
    }

    @Unique
    private boolean pressingMatches() {
        if (!(this.level() instanceof ServerLevel server)) return false;
        if (!this.blockState.is(ModBlocks.HEAVY_ANVIL)) return false;
        BlockPos pos = this.blockPosition();
        return AnvilPressing.find(server, pos, server.getBlockState(pos), AnvilPressing.itemsTall(server, pos.below(), 2)).isPresent();
    }

    @Unique
    private boolean matchesAndBelow(Block block, Block blockBottom) {
        if (!(this.level() instanceof ServerLevel server)) return false;
        if (!this.blockState.is(block)) return false;
        BlockPos pos = this.blockPosition().below();
        return server.getBlockState(pos).is(blockBottom);
    }
    @Unique
    private boolean matchesAndBelow(Block block, TagKey<Block> blocksBottom) {
        if (!(this.level() instanceof ServerLevel server)) return false;
        if (!this.blockState.is(block)) return false;
        BlockPos pos = this.blockPosition();
        return server.getBlockState(pos).is(blocksBottom);
    }
}

