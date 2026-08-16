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
            ItemStack playerStack100000 = player.getMainHandItem();
            float returnValue = -1f;

            if (playerStack100000.is(ItemTags.AXES)) {
                if (state.requiresCorrectToolForDrops() || state.is(ModTags.Blocks.FORCE_REQUIRE_TOOL)) {
                    if (!state.is(BlockTags.MINEABLE_WITH_AXE)) {
                        returnValue = 0;
                    } else {
                        returnValue = -1;
                    }
                }
            }
            if (playerStack100000.is(ItemTags.HOES)) {
                if (state.requiresCorrectToolForDrops() || state.is(ModTags.Blocks.FORCE_REQUIRE_TOOL)) {
                    if (!state.is(BlockTags.MINEABLE_WITH_HOE)) {
                        returnValue = 0;
                    } else {
                        returnValue = -1;
                    }
                }
            }
            if (playerStack100000.is(ItemTags.PICKAXES)) {
                if (state.requiresCorrectToolForDrops() || state.is(ModTags.Blocks.FORCE_REQUIRE_TOOL)) {
                    if (!state.is(BlockTags.MINEABLE_WITH_PICKAXE)) {
                        returnValue = 0;
                    } else {
                        returnValue = -1;
                    }
                }
            }
            if (playerStack100000.is(ItemTags.SHOVELS)) {
                if (state.requiresCorrectToolForDrops() || state.is(ModTags.Blocks.FORCE_REQUIRE_TOOL)) {
                    if (!state.is(BlockTags.MINEABLE_WITH_SHOVEL)) {
                        returnValue = 0;
                    } else {
                        returnValue = -1;
                    }
                }
            }
            if (playerStack100000.is(ModTags.Items.NO_HOE_MULTITOOL)) {
                if (state.requiresCorrectToolForDrops() || state.is(ModTags.Blocks.FORCE_REQUIRE_TOOL)) {
                    if (state.is(BlockTags.MINEABLE_WITH_SHOVEL) ||
                        state.is(BlockTags.MINEABLE_WITH_PICKAXE) ||
                        state.is(BlockTags.MINEABLE_WITH_AXE)) {
                        returnValue = -1;
                    } else {
                        returnValue = 0;
                    }
                }
            }

            if (!playerStack100000.is(ItemTags.AXES) && !playerStack100000.is(ItemTags.HOES) &&
                !playerStack100000.is(ItemTags.PICKAXES) && !playerStack100000.is(ItemTags.SHOVELS) &&
                !playerStack100000.is(ModTags.Items.NO_HOE_MULTITOOL))
                if (state.requiresCorrectToolForDrops() || state.is(ModTags.Blocks.FORCE_REQUIRE_TOOL))
                    returnValue = 0;


            if (returnValue >= 0) cir.setReturnValue(returnValue);
        }
}
