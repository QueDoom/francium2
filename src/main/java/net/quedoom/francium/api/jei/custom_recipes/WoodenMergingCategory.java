package net.quedoom.francium.api.jei.custom_recipes;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.AbstractRecipeCategory;
import mezz.jei.api.recipe.types.IRecipeType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.quedoom.francium.Francium;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.francium.recipe.WoodenMergingRecipe;

public class WoodenMergingCategory extends AbstractRecipeCategory<WoodenMergingRecipe> {

    public static final IRecipeType<WoodenMergingRecipe> TYPE =
            IRecipeType.create(Francium.id("wooden_merging"), WoodenMergingRecipe.class);


    public WoodenMergingCategory(IGuiHelper guiHelper) {
        super(
                TYPE,
                Component.translatable("jei." + Francium.MOD_ID + ".wooden_merging"),
                guiHelper.createDrawableItemLike(ModBlocks.WOODEN_MERGER),
                116,
                44
        );
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, WoodenMergingRecipe recipe, IFocusGroup focuses) {
        builder.addInputSlot(1, 0)
                .setStandardSlotBackground()
                .add(recipe.getFirstIngredient());
        builder.addInputSlot(1, 27)
                .setStandardSlotBackground()
                .add(recipe.getSecondIngredient());
        builder.addInputSlot(42, 14)
                .setStandardSlotBackground()
                .add(recipe.getGlue());

        builder.addOutputSlot(95, 14)
                .setOutputSlotBackground()
                .add(recipe.getResult());

        builder.addInvisibleIngredients(RecipeIngredientRole.CRAFTING_STATION)
                .add(ModBlocks.WOODEN_MERGER);
    }
}
