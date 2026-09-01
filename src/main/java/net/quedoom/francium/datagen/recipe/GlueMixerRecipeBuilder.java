package net.quedoom.francium.datagen.recipe;

import net.minecraft.advancements.Criterion;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeUnlockAdvancementBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.quedoom.francium.Francium;
import net.quedoom.francium.init.ModItems;
import net.quedoom.francium.recipe.GlueMixerGlueType;
import net.quedoom.francium.recipe.GlueMixingRecipe;
import org.jspecify.annotations.Nullable;

public class GlueMixerRecipeBuilder implements RecipeBuilder {
    private final RecipeCategory category;
    private final int strength;
    private final GlueMixerGlueType gType;
    private final ItemStackTemplate result;
    private final RecipeUnlockAdvancementBuilder advancementBuilder = new RecipeUnlockAdvancementBuilder();
    private @Nullable String group;

    public GlueMixerRecipeBuilder(RecipeCategory category, GlueMixerGlueType gType, int strength, ItemStackTemplate result) {
        this.category = category;
        this.gType = gType;
        this.strength = strength;
        this.result = result;
    }

    public static GlueMixerRecipeBuilder glueMixerRecipe(RecipeCategory category, GlueMixerGlueType gType, int strength, ItemLike result, int count) {
        return new GlueMixerRecipeBuilder(category, gType, strength, new ItemStackTemplate(result.asItem(), count));
    }


    public static GlueMixerRecipeBuilder glueMixerRecipe(RecipeCategory category, GlueMixerGlueType gType, int strength, ItemLike result) {
        return new GlueMixerRecipeBuilder(category, gType, strength, new ItemStackTemplate(result.asItem()));
    }

    @Override
    public RecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        advancementBuilder.unlockedBy(name, criterion);
        return this;
    }

    @Override
    public RecipeBuilder group(@Nullable String group) {
        this.group = group;
        return this;
    }

    @Override
    public ResourceKey<Recipe<?>> defaultId() {
        return RecipeBuilder.getDefaultRecipeId(this.result);
    }

    @Override
    public void save(RecipeOutput output, ResourceKey<Recipe<?>> id) {
        GlueMixingRecipe recipe = new GlueMixingRecipe(this.result, Ingredient.of(ModItems.GLUE_BOTTLE), Ingredient.of(switch (this.strength) {
            case 1 -> Items.GOLD_INGOT;
            case 2 -> Items.DIAMOND;
            case 3 -> Items.NETHERITE_INGOT;
            default -> Items.IRON_INGOT;
        }), Ingredient.of(GlueMixerGlueType.toItem(this.gType)));
        output.accept(id, recipe, this.advancementBuilder.build(output, id, this.category));
    }
}
