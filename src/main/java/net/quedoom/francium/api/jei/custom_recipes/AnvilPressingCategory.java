package net.quedoom.francium.api.jei.custom_recipes;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.AbstractRecipeCategory;
import mezz.jei.api.recipe.types.IRecipeType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Blocks;
import net.quedoom.francium.Francium;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.francium.recipe.AnvilPressingRecipe;

public class AnvilPressingCategory extends AbstractRecipeCategory<AnvilPressingRecipe> {
    public static final IRecipeType<AnvilPressingRecipe> TYPE =
            IRecipeType.create(Francium.id("anvil_pressing"), AnvilPressingRecipe.class);

    public AnvilPressingCategory(IGuiHelper guiHelper) {
        super(
                TYPE,
                Component.translatable("jei" + Francium.MOD_ID + "anvil_pressing"),
                guiHelper.createDrawableItemLike(ModBlocks.WOODEN_MERGER),
                36,
                64
        );
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, AnvilPressingRecipe recipe, IFocusGroup focuses) {
        builder.addInputSlot(11, 11)
                .setStandardSlotBackground()
                .add(recipe.getFirstIngredient());
        builder.addInputSlot(11, 29)
                .setStandardSlotBackground()
                .add(recipe.getSecondIngredient());

        builder.addOutputSlot(44, 21)
                .setOutputSlotBackground()
                .add(recipe.getResult());

        builder.addInvisibleIngredients(RecipeIngredientRole.CRAFTING_STATION)
                .add(Blocks.ANVIL);
    }
}
