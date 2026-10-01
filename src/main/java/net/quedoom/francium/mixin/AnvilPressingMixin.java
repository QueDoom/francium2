package net.quedoom.francium.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.quedoom.francium.block.BlockContainingBlock;
import net.quedoom.francium.block.entity.BlockContainingEntity;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.francium.init.ModRecipeTypes;
import net.quedoom.francium.recipe.BasicAnvilPressingRecipe;
import net.quedoom.francium.recipe.BasicAnvilPressingRecipeInput;
import org.spongepowered.asm.mixin.Mixin;
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
                    items.forEach(Entity::discard);
                    ItemStack itemResult = recipe.result().create();
                    if (itemResult.getItem() instanceof BlockItem blockItem) {
                        if (below.getBlock() instanceof BlockContainingBlock blockContainingBlock) {

                            server.setBlock(pos.below(), below.is(ModBlocks.STONE_CASING) ?
                                    ModBlocks.STONE_CASING_CONTAINING_BLOCK.defaultBlockState() :
                                    ModBlocks.WOODEN_CASING_CONTAINING_BLOCK.defaultBlockState(), Block.UPDATE_ALL);

                            if (level.getBlockEntity(pos.below()) instanceof BlockContainingEntity container) {
                                if (container.isEmpty()) {
                                    container.setItem(0, itemResult);
                                    return;
                                }
                            }
                        }
                        server.setBlock(pos.below(), blockItem.getBlock().defaultBlockState(), Block.UPDATE_ALL);
                    } else {
                        throw new IllegalArgumentException("The result must be a block sorry :(. Recipe to change: " + recipe);
                    }
                });
    }


}
