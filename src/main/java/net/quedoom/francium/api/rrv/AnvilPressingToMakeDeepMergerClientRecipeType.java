package net.quedoom.francium.api.rrv;

import cc.cassian.rrv.common.recipe.inventory.RecipeViewMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.quedoom.francium.init.ModBlocks;

import java.util.List;

public class AnvilPressingToMakeDeepMergerClientRecipeType extends ReliableClientRecipeTypeHelper{
    protected AnvilPressingToMakeDeepMergerClientRecipeType() {
        super(
                "anvil_pressing",
                List.of(new ItemStack(Blocks.ANVIL), new ItemStack(ModBlocks.DRIPSTONE_SPIKES), new ItemStack(Blocks.DEEPSLATE)),
                30,
                116
        );
    }

    @Override
    public int getSlotCount() {
        return 2;
    }

    @Override
    public void placeSlots(RecipeViewMenu.SlotDefinition slotDefinition) {
        slotDefinition.addItemSlot(0, 15, 15);
        slotDefinition.addItemSlot(0, 15, 30);
    }
}
