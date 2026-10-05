package net.quedoom.francium.recipe;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.quedoom.francium.init.ModRecipeTypes;

import java.util.List;
import java.util.Optional;

public final class AnvilPressing {
    public static List<ItemEntity> items(ServerLevel level, BlockPos pos) {
        return level.getEntitiesOfClass(ItemEntity.class, new AABB(pos), EntitySelector.ENTITY_STILL_ALIVE);
    }

    public static Optional<RecipeHolder<TwoBlockAnvilPressingRecipe>> find(ServerLevel level, BlockPos pos, BlockState top) {
        TwoBlockAnvilPressingRecipeInput input = new TwoBlockAnvilPressingRecipeInput(
                items(level, pos).stream().map(ItemEntity::getItem).toList(),
                top,
                level.getBlockState(pos.below()));
        return level.recipeAccess().getRecipeFor(ModRecipeTypes.TWO_BLOCK_ANVIL_PRESSING, input, level);
    }
}
