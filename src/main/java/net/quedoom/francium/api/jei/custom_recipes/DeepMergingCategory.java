package net.quedoom.francium.api.jei.custom_recipes;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.AbstractRecipeCategory;
import mezz.jei.api.recipe.types.IRecipeType;
import net.minecraft.network.chat.Component;
import net.quedoom.francium.Francium;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.francium.recipe.DeepMergingRecipe;

public class DeepMergingCategory extends AbstractRecipeCategory<DeepMergingRecipe> {

    public static final IRecipeType<DeepMergingRecipe> TYPE =
            IRecipeType.create(Francium.id("deep_merging"), DeepMergingRecipe.class);

    public DeepMergingCategory(IGuiHelper guiHelper) {
        super(
                TYPE,
                Component.translatable("jei." + Francium.MOD_ID + ".deep_merging"),
                guiHelper.createDrawableItemLike(ModBlocks.DEEP_MERGER),
                116,
                58
        );
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, DeepMergingRecipe recipe, IFocusGroup focuses) {
        builder.addInputSlot(1, 0)
                .setStandardSlotBackground()
                .add(recipe.getFirstIngredient());
        builder.addInputSlot(1, 21)
                .setStandardSlotBackground()
                .add(recipe.getSecondIngredient());
        builder.addInputSlot(1, 41)
                .setStandardSlotBackground()
                .add(recipe.getThirdIngredient());
        builder.addInputSlot(42, 11)
                .setStandardSlotBackground()
                .add(recipe.getGlue());
        builder.addInputSlot(42, 33)
                .setStandardSlotBackground()
                .add(recipe.getWildcard());

        builder.addOutputSlot(95, 21)
                .setOutputSlotBackground()
                .add(recipe.getResult());

        builder.addInvisibleIngredients(RecipeIngredientRole.CRAFTING_STATION)
                .add(ModBlocks.DEEP_MERGER);
    }
}
