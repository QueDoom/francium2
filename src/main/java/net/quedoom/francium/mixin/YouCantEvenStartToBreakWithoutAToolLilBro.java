package net.quedoom.francium.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.quedoom.francium.init.ModTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBehaviour.class)
public class YouCantEvenStartToBreakWithoutAToolLilBro {
        @Inject(method = "getDestroyProgress",
                at = @At(value = "HEAD"), cancellable = true)

        protected void getDestroyProgress(BlockState state, Player player, BlockGetter level, BlockPos pos, CallbackInfoReturnable<Float> cir) {
            ItemStack playerStack = player.getMainHandItem();
            boolean shouldAllow = !(state.requiresCorrectToolForDrops() && state.is(ModTags.Blocks.FORCE_REQUIRE_TOOL));

            if (!shouldAllow && state.is(BlockTags.MINEABLE_WITH_PICKAXE)) {
                if (playerStack.is(ItemTags.PICKAXES)) {
                    shouldAllow = true;
                }
            }
            if (!shouldAllow && state.is(BlockTags.MINEABLE_WITH_SHOVEL)) {
                if (playerStack.is(ItemTags.SHOVELS)) {
                    shouldAllow = true;
                }
            }
            if (!shouldAllow && state.is(BlockTags.MINEABLE_WITH_HOE)) {
                if (playerStack.is(ItemTags.HOES)) {
                    shouldAllow = true;
                }
            }
            if (!shouldAllow && state.is(BlockTags.MINEABLE_WITH_AXE)) {
                if (playerStack.is(ItemTags.AXES)) {
                    shouldAllow = true;
                }
            }

            if (!shouldAllow) cir.setReturnValue(0F);
        }
}
