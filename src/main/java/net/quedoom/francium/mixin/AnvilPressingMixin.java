package net.quedoom.francium.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.quedoom.francium.Francium;
import net.quedoom.francium.block.entity.BlockContainingEntity;
import net.quedoom.francium.init.ModItems;
import net.quedoom.francium.init.ModRecipeTypes;
import net.quedoom.francium.init.ModTags;
import net.quedoom.francium.recipe.AnvilPressingRecipe;
import net.quedoom.francium.recipe.AnvilPressingRecipeInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Optional;

@Mixin(AnvilBlock.class)
public class AnvilPressingMixin {
    @Inject(method = "onLand",
            at = @At("TAIL"))

    private void craft(Level level, BlockPos pos, BlockState state, BlockState replacedBlock, FallingBlockEntity entity, CallbackInfo ci) {
        AABB aabb = AABB.encapsulatingFullBlocks(pos, pos);
        List<ItemEntity> list = level.getEntitiesOfClass(ItemEntity.class, aabb, EntitySelector.ENTITY_STILL_ALIVE);
        for (ItemEntity itemEntity : list) {
            Francium.LOGGER.info(itemEntity.toString());
            ItemStack stack = itemEntity.getItem();
            if (stack.getCount() >= 4 && level.getBlockState(pos.below()).is(ModTags.Blocks.BLOCK_CONTAINING_BLOCK_COMPATIBLE)) {
                Francium.LOGGER.info("hehe");
                if (createResult(level, stack, (level.getBlockState(pos.below()).getBlock()).asItem().getDefaultInstance(), pos)) {
                    stack.shrink(4);
                }
            }
        }
    }

    public boolean createResult(Level level, ItemStack in, ItemStack var67, BlockPos pos) {
        ItemStack inCopy = in.copy(); inCopy.setCount(1);
        AnvilPressingRecipeInput input = new AnvilPressingRecipeInput(inCopy, var67);
        Optional<RecipeHolder<AnvilPressingRecipe>> foundRecipe;
        if (level instanceof ServerLevel serverLevel) {
            foundRecipe = serverLevel.recipeAccess().getRecipeFor(ModRecipeTypes.ANVIL_PRESSING, input, serverLevel);
            Francium.LOGGER.info("found recipe:");
            Francium.LOGGER.info(foundRecipe.toString());
        } else {
            foundRecipe = Optional.empty();
        }

        ItemStack stack = ModItems.UNUSED_ITEM_BECAUSE_I_CANT_FIGURE_OUT_HOW_TO_MAKE_OPTIONAL_ITEMS_BECAUSE_IM_STUPID.getDefaultInstance();

        foundRecipe.ifPresentOrElse((recipe) -> {
            Francium.LOGGER.info("Found recipe again!!!");
            ItemStack result = (recipe.value()).assemble(input);
            ((BlockContainingEntity) level.getBlockEntity(pos.below())).setItem(0, result);
            stack.grow(32);
        }, ( ) -> { });
        return stack.getCount() > 2;
    }
}
