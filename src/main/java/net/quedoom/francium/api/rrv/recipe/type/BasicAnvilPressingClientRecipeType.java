package net.quedoom.francium.api.rrv.recipe.type;

import cc.cassian.rrv.api.recipe.ReliableClientRecipeType;
import cc.cassian.rrv.common.recipe.inventory.RecipeViewMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.quedoom.francium.Francium;
import net.quedoom.quet.api.rrv.ReliableClientRecipeTypeHelper;

import java.util.List;

public class BasicAnvilPressingClientRecipeType extends ReliableClientRecipeTypeHelper {
    public static final ReliableClientRecipeType INSTANCE = new BasicAnvilPressingClientRecipeType();

    protected BasicAnvilPressingClientRecipeType() {
        super(
                "anvil_pressing",
                List.of(new ItemStack(Blocks.ANVIL), new ItemStack(Blocks.CHIPPED_ANVIL), new ItemStack(Blocks.DAMAGED_ANVIL)),
                67, 92
        );

    }

    @Override
    public int getSlotCount() {
        return 3;
    }

    @Override
    public void placeSlots(RecipeViewMenu.SlotDefinition slotDefinition) {
        slotDefinition.addItemSlot(0, 4, 45);
        slotDefinition.addItemSlot(1, 4, 68);
        slotDefinition.addItemSlot(2, 45, 68);
    }

    @Override
    protected String namespace() {
        return Francium.MOD_ID;
    }
}
