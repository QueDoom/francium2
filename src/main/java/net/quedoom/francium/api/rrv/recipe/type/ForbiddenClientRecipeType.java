package net.quedoom.francium.api.rrv.recipe.type;

import cc.cassian.rrv.common.recipe.inventory.RecipeViewMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.quedoom.francium.Francium;
import net.quedoom.francium.api.rrv.util.InvisibleSlot;
import net.quedoom.francium.init.ModTags;
import net.quedoom.quet.api.rrv.ReliableClientRecipeTypeHelper;

import java.util.List;

public class ForbiddenClientRecipeType extends ReliableClientRecipeTypeHelper {
    public ForbiddenClientRecipeType() {
        super("forbidden",
                ModTags.itemsInTag(ItemTags.PICKAXES).stream()
                        .map(Item::getDefaultInstance)
                        .toList(),
                82, 64);
    }

    @Override
    protected String namespace() {
        return Francium.MOD_ID;
    }

    @Override
    public void placeSlots(RecipeViewMenu.SlotDefinition slotDefinition) {
        slotDefinition.addItemSlot(0, 5, 5, InvisibleSlot::new);
        slotDefinition.addItemSlot(1, 58, 24, InvisibleSlot::new);
    }
}
