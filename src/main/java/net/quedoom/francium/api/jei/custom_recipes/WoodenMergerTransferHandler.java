package net.quedoom.francium.api.jei.custom_recipes;

import mezz.jei.api.gui.ingredient.IRecipeSlotView;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.transfer.IRecipeTransferError;
import mezz.jei.api.recipe.transfer.IRecipeTransferHandler;
import mezz.jei.api.recipe.transfer.IRecipeTransferHandlerHelper;
import mezz.jei.api.recipe.types.IRecipeType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.quedoom.francium.Francium;
import net.quedoom.francium.api.jei.FranciumJeiPlugin;
import net.quedoom.francium.block.menu.WoodenMergerMenu;
import net.quedoom.francium.init.ModMenuTypes;
import net.quedoom.francium.init.ModRecipeTypes;
import net.quedoom.francium.recipe.WoodenMergingRecipe;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class WoodenMergerTransferHandler implements IRecipeTransferHandler<WoodenMergerMenu, RecipeHolder<WoodenMergingRecipe>> {
    private final IRecipeTransferHandlerHelper helper;

    public WoodenMergerTransferHandler(IRecipeTransferHandlerHelper helper) {
        this.helper = helper;
    }

    @Override
    public Class<? extends WoodenMergerMenu> getContainerClass() {
        return WoodenMergerMenu.class;
    }

    @Override
    public Optional<MenuType<WoodenMergerMenu>> getMenuType() {
        return Optional.of(ModMenuTypes.WOODEN_MERGER);
    }

    @Override
    public IRecipeType<RecipeHolder<WoodenMergingRecipe>> getRecipeType() {
        return null;
    }

    @Override
    public @Nullable IRecipeTransferError transferRecipe(WoodenMergerMenu container, RecipeHolder<WoodenMergingRecipe> recipe,
                                                         IRecipeSlotsView recipeSlots, Player player, boolean maxTransfer, boolean doTransfer) {
        List<IRecipeSlotView> inputSlots = recipeSlots.getSlotViews(RecipeIngredientRole.INPUT);
//        for (int i = 0; i <= 2; i++) {
//            if (container.getSlot(i).getItem().isEmpty() && player.getInventory().contains(inputSlots.get(i)) {
//
//            }
//        }
        return null;
    }
}
