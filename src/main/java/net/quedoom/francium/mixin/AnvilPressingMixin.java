package net.quedoom.francium.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.quedoom.francium.block.entity.BlockContainingItemsEntity;
import net.quedoom.francium.block.CasingWithPotentialContainer;
import net.quedoom.francium.init.ModRecipeTypes;
import net.quedoom.francium.recipe.AnvilPressing;
import net.quedoom.francium.recipe.BasicAnvilPressingRecipe;
import net.quedoom.francium.recipe.BasicAnvilPressingRecipeInput;
import net.quedoom.francium.recipe.TwoBlockAnvilPressingRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(AnvilBlock.class)
public class AnvilPressingMixin {
    @Inject(method = "onLand",
            at = @At("TAIL"))

    private void craft(Level level, BlockPos pos, BlockState state, BlockState replacedBlock, FallingBlockEntity entity, CallbackInfo ci) {
        if (!(level instanceof ServerLevel server)) return;

        List<ItemEntity> items = server.getEntitiesOfClass(ItemEntity.class, new AABB(pos));
        if (items.isEmpty()) return;

        BlockState below = server.getBlockState(pos.below());

        BasicAnvilPressingRecipeInput input = new BasicAnvilPressingRecipeInput(
                items.stream().map(ItemEntity::getItem).toList(), below);

        server.recipeAccess()
                .getRecipeFor(ModRecipeTypes.BASIC_ANVIL_PRESSING, input, server)
                .ifPresent(holder -> {
                    BasicAnvilPressingRecipe recipe = holder.value();
                    ItemStack itemResult = recipe.result().create();
                    boolean done;

                    if (itemResult.getItem() instanceof BlockItem blockItem) {
                        if (!(below.getBlock() instanceof CasingWithPotentialContainer potentialContainer
                                && potentialContainer.placeContainingBlock(level, pos, input.block(), itemResult))) {
                            server.setBlock(pos.below(), blockItem.getBlock().defaultBlockState(), Block.UPDATE_ALL);
                        }
                        done = true;
                    } else {
                        if (below.getBlock() instanceof CasingWithPotentialContainer potentialContainer) {
                            done = potentialContainer.placeContainingItems(level, pos, input.block(), itemResult);
                        } else if (level.getBlockEntity(pos.below()) instanceof BlockContainingItemsEntity containingItems) {
                            done = containingItems.placeInAvailableSlots(itemResult);
                        } else done = false;
                    }

                    if (done) consume(items, recipe);
                });
    }

    @Unique
    private static void consume(List<ItemEntity> items, BasicAnvilPressingRecipe recipe) {
        int left = recipe.countReq();
        for (ItemEntity e : items) {
            if (left <= 0) break;
            ItemStack s = e.getItem();
            if (!recipe.ingredient().test(s)) continue;
            int take = Math.min(left, s.getCount());
            left -= take;
            if (take == s.getCount()) {
                e.discard();
            } else {
                e.setItem(s.copyWithCount(s.getCount() - take));
            }
        }
    }

    @Inject(method = "onLand", at = @At("TAIL"))
    private void francium$press(Level level, BlockPos pos, BlockState state, BlockState replacedBlock,
                                FallingBlockEntity entity, CallbackInfo ci) {
        if (!(level instanceof ServerLevel server)) return;

        AnvilPressing.find(server, pos, replacedBlock).ifPresent(holder -> {
            TwoBlockAnvilPressingRecipe recipe = holder.value();

            int left = recipe.count();
            for (ItemEntity e : AnvilPressing.items(server, pos)) {
                ItemStack s = e.getItem();
                if (left <= 0 || !recipe.ingredient().test(s)) continue;
                int take = Math.min(left, s.getCount());
                left -= take;
                if (take == s.getCount()) {
                    e.discard();
                } else {
                    ItemStack c = s.copy();
                    c.shrink(take);
                    e.setItem(c);
                }
            }

            ItemStack result = recipe.result().create();
            BlockPos bottom = pos.below();
            BlockState bottomState = level.getBlockState(bottom);
            boolean done;

            if (result.getItem() instanceof BlockItem blockItem) {
                if (!(bottomState.getBlock() instanceof CasingWithPotentialContainer potentialContainer
                        && potentialContainer.placeContainingBlock(level, pos, bottomState, result))) {
                    server.setBlock(pos.below(), blockItem.getBlock().defaultBlockState(), Block.UPDATE_ALL);
                }
                done = true;
            } else {
                if (bottomState.getBlock() instanceof CasingWithPotentialContainer potentialContainer) {
                    done = potentialContainer.placeContainingItems(level, pos, bottomState, result);
                } else if (level.getBlockEntity(pos.below()) instanceof BlockContainingItemsEntity containingItems) {
                    done = containingItems.placeInAvailableSlots(result);
                } else done = false;
            }

            if (!done) {
                if (result.getItem() instanceof BlockItem bi) {
                    server.setBlock(bottom, bi.getBlock().defaultBlockState(), Block.UPDATE_ALL);
                } else {
                    server.addFreshEntity(new ItemEntity(server, bottom.getX() + 0.5, bottom.getY() + 1.1, bottom.getZ() + 0.5, result));
                }
            }
        });
    }


}
