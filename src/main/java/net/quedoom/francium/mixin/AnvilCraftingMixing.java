package net.quedoom.francium.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.quedoom.francium.Francium;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.francium.init.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(AnvilBlock.class)
public class AnvilCraftingMixing {
    @Inject(method = "onLand",
            at = @At("TAIL"))

    private void craft(Level level, BlockPos pos, BlockState state, BlockState replacedBlock, FallingBlockEntity entity, CallbackInfo ci) {
        AABB aabb = AABB.encapsulatingFullBlocks(pos, pos);
        List<ItemEntity> list = level.getEntitiesOfClass(ItemEntity.class, aabb, EntitySelector.ENTITY_STILL_ALIVE);
        Francium.LOGGER.info(list.toString());
        for (ItemEntity itemEntity : list) {
            Francium.LOGGER.info(itemEntity.toString());
            ItemStack stack = itemEntity.getItem();
            if (!stack.is(ModItems.DRIPSTONE_COATED_MINERAL_MIX)) continue;
            if (stack.getCount() >= 4 && level.getBlockState(pos.below()).is(ModBlocks.WOODEN_CASING)) {
                stack.shrink(4);
                level.setBlockAndUpdate(pos.below(), ModBlocks.MINERAL_MIXED_WOODEN_CASING.defaultBlockState());
            }
        }
    }
}
