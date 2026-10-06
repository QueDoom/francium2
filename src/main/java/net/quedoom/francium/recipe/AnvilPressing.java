package net.quedoom.francium.recipe;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.quedoom.francium.init.ModRecipeTypes;

import java.util.List;
import java.util.Optional;

public final class AnvilPressing {
    public static List<ItemEntity> items(ServerLevel level, BlockPos pos) {
        return level.getEntitiesOfClass(ItemEntity.class, new AABB(pos), EntitySelector.ENTITY_STILL_ALIVE);
    }
    public static List<ItemEntity> itemsTall(ServerLevel level, BlockPos pos, int height) {
        AABB box = new AABB(pos.getX(), pos.getY(), pos.getZ(),
                pos.getX() + 1, pos.getY() + height, pos.getZ() + 1);
        return level.getEntitiesOfClass(ItemEntity.class, box, EntitySelector.ENTITY_STILL_ALIVE);
    }

    public static Optional<RecipeHolder<TwoBlockAnvilPressingRecipe>> find(ServerLevel level, BlockPos pos, BlockState top, List<ItemEntity> items) {
        TwoBlockAnvilPressingRecipeInput input = new TwoBlockAnvilPressingRecipeInput(
                items.stream().map(ItemEntity::getItem).toList(),
                top,
                level.getBlockState(pos.below()));
        return level.recipeAccess().getRecipeFor(ModRecipeTypes.TWO_BLOCK_ANVIL_PRESSING, input, level);
    }
}
