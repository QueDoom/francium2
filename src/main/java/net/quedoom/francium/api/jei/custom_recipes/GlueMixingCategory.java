package net.quedoom.francium.api.jei.custom_recipes;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.category.AbstractRecipeCategory;
import mezz.jei.api.recipe.types.IRecipeType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.crafting.Ingredient;
import net.quedoom.francium.Francium;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.francium.init.ModItems;
import net.quedoom.francium.recipe.GlueMixingRecipe;

public class GlueMixingCategory extends AbstractRecipeCategory<GlueMixingRecipe> {
    public static final IRecipeType<GlueMixingRecipe> TYPE =
            IRecipeType.create(Francium.id("glue_mixing"), GlueMixingRecipe.class);

    public GlueMixingCategory(IGuiHelper guiHelper) {
        super(
                TYPE,
                Component.translatable("jei." + Francium.MOD_ID + ".glue_mixing"),
                guiHelper.createDrawableItemStack(ModBlocks.GLUE_MIXER.asItem().getDefaultInstance()),
                116,
                44
        );
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, GlueMixingRecipe recipe, IFocusGroup focuses) {
        builder.addInputSlot(1, 0)
                .setStandardSlotBackground()
                .add(recipe.getIngredient());
        builder.addInputSlot(1, 27)
                .setStandardSlotBackground()
                .add(recipe.getGTypeAsBothBlockAndItem());
        builder.addInputSlot(42, 14)
                .setStandardSlotBackground()
                .add(Ingredient.of(ModBlocks.GLUE_MIXER));

        builder.addOutputSlot(95, 14)
                .setOutputSlotBackground()
                .add(recipe.getResult());
    }
}
