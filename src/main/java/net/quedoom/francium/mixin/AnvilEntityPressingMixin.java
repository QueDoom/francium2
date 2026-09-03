package net.quedoom.francium.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.quedoom.francium.Francium;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.francium.init.ModItems;
import net.quedoom.francium.init.ModTags;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Debug(export = true)
@Mixin(FallingBlockEntity.class)
public abstract class AnvilEntityPressingMixin extends Entity {
    @Shadow
    private BlockState blockState;

    public AnvilEntityPressingMixin(EntityType<?> type, Level level) {
        super(type, level);
    }

    @Inject(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/Fallable;onLand(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/entity/item/FallingBlockEntity;)V"
            )
    )
    private void entityCrafting(CallbackInfo ci) {
        Level level = this.level();
        if (level != null && !level.isClientSide()) return;
        BlockPos pos = this.blockPosition();
        BlockState stateBelow = level.getBlockState(pos.below());

        AABB aabb = AABB.encapsulatingFullBlocks(pos, pos);
        List<ItemEntity> list = level.getEntitiesOfClass(ItemEntity.class, aabb, EntitySelector.ENTITY_STILL_ALIVE);
        ItemStack stackedSlot = ItemStack.EMPTY;
        ItemStack rawStackedSlot = ItemStack.EMPTY;
        Francium.LOGGER.info(list.toString());
        for (ItemEntity itemEntity : list) {
            Francium.LOGGER.info(itemEntity.toString());
            ItemStack stack = itemEntity.getItem();
            if (stack.is(ModItems.STACKED_SLOT)) stackedSlot = stack;
            if (stack.is(ModItems.STACKED_RAW_SLOT)) rawStackedSlot = stack;
        }
        Francium.LOGGER.info(stackedSlot.toString());
        Francium.LOGGER.info(rawStackedSlot.toString());
        Francium.LOGGER.info("{}", !stackedSlot.isEmpty() && !rawStackedSlot.isEmpty());
        if (getOr(false) && stateBelow.is(Blocks.DEEPSLATE) && !stackedSlot.isEmpty() && !rawStackedSlot.isEmpty()) {
            Francium.LOGGER.info("nuttah");
            stackedSlot.shrink(1);
            rawStackedSlot.shrink(1);
            level.setBlockAndUpdate(pos.below(), ModBlocks.DEEP_MERGER.defaultBlockState());
        }
        return;
    }


    @ModifyExpressionValue(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/state/BlockState;canBeReplaced(Lnet/minecraft/world/item/context/BlockPlaceContext;)Z")
    )
    private boolean canBeReplacedOr(boolean original) {
        return getOr(original);
    }

    @ModifyExpressionValue(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/state/BlockState;canSurvive(Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;)Z")
    )
    private boolean canSurviveOr(boolean original) {
        return getOr(original);
    }

    private boolean getOr(boolean original) {
        BlockState state = this.level().getBlockState(this.blockPosition());
        if (this.blockState.is(Blocks.ANVIL) && state.is(ModBlocks.DRIPSTONE_SPIKES)) {
            return true;
        }
        return original;
    }

}

