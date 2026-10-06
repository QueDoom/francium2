package net.quedoom.francium.api.rrv.recipe;

import cc.cassian.rrv.api.recipe.ReliableClientRecipeType;
import cc.cassian.rrv.common.recipe.inventory.RecipeViewMenu;
import cc.cassian.rrv.common.recipe.inventory.SlotContent;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.quedoom.francium.Francium;
import net.quedoom.francium.api.rrv.recipe.type.WoodenMixingClientRecipeType;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.francium.init.ModItems;
import net.quedoom.francium.recipe.GlueMixingRecipe;
import net.quedoom.francium.recipe.WoodenMixerGlueType;
import net.quedoom.quet.api.rrv.ReliableClientRecipeWithoutTransfer;

import java.util.List;

public class WoodenMixingClientRecipe extends ReliableClientRecipeWithoutTransfer {
    private final SlotContent typeItem, subType, glueBottle, result;

    public WoodenMixingClientRecipe(Ingredient typeItem, ItemStackTemplate result, Item... subTypes) {
        this.typeItem = SlotContent.of(typeItem);
        this.subType = SlotContent.of(subTypes);
        this.glueBottle = SlotContent.of(ModItems.GLUE_BOTTLE);
        this.result = SlotContent.of(result);
    }
    public WoodenMixingClientRecipe(List<Item> typeItems, ItemStackTemplate result, Item... subTypes) {
        this.typeItem = SlotContent.ofItemList(typeItems);
        this.subType = SlotContent.of(subTypes);
        this.glueBottle = SlotContent.of(ModItems.GLUE_BOTTLE);
        this.result = SlotContent.of(result);
    }
    public WoodenMixingClientRecipe(Ingredient typeItem, Ingredient subType, ItemStackTemplate result) {
        this.typeItem = SlotContent.of(typeItem);
        this.subType = SlotContent.of(subType);
        this.glueBottle = SlotContent.of(ModItems.GLUE_BOTTLE);
        this.result = SlotContent.of(result);
    }
    public WoodenMixingClientRecipe(Ingredient typeItem, ItemStackTemplate result) {
        this.typeItem = SlotContent.of(typeItem);
        this.subType = SlotContent.of();
        this.glueBottle = SlotContent.of(ModItems.GLUE_BOTTLE);
        this.result = SlotContent.of(result);
    }

    public static WoodenMixingClientRecipe of(WoodenMixerGlueType gType, ItemStackTemplate result) {
        return switch (gType) {
            case NORMAL -> new WoodenMixingClientRecipe(Ingredient.of(gType.toItem()), result);
            case SUPER -> new WoodenMixingClientRecipe(Ingredient.of(Blocks.SLIME_BLOCK), Ingredient.of(gType.toItem()), result);
            case VEGAN -> new WoodenMixingClientRecipe(Ingredient.of(Blocks.SLIME_BLOCK),  result,
                    Items.ACACIA_LEAVES,
                    Items.AZALEA_LEAVES,
                    Items.BIRCH_LEAVES,
                    Items.CHERRY_LEAVES,
                    Items.DARK_OAK_LEAVES,
                    Items.FLOWERING_AZALEA_LEAVES,
                    Items.JUNGLE_LEAVES,
                    Items.MANGROVE_LEAVES,
                    Items.OAK_LEAVES,
                    Items.PALE_OAK_LEAVES,
                    Items.SPRUCE_LEAVES,
                    ModItems.LEAF,
                    ModItems.HUSK,
                    ModItems.GRASS
            );
            case STEEL -> new WoodenMixingClientRecipe(List.of(Items.SLIME_BLOCK, Items.ECHO_SHARD), result,
                        ModItems.IRON_DUST,
                        ModItems.COAL_DUST
                    );
            case ECHO -> new WoodenMixingClientRecipe(Ingredient.of(Blocks.SLIME_BLOCK), result,
                        ModBlocks.ECHO_BLOCK.asItem(),
                        Items.ECHO_SHARD
                    );
        };
    }

    @Override
    protected ReliableClientRecipeType zeType() {
        return WoodenMixingClientRecipeType.INSTANCE;
    }

    @Override
    public Identifier identifier() {
        return Francium.of(GlueMixingRecipe.Type.ID);
    }

    @Override
    public void bindSlots(RecipeViewMenu.SlotFillContext slotFillContext) {
        slotFillContext.bindSlot(0, typeItem);
        slotFillContext.bindOptionalSlot(1, subType, RecipeViewMenu.OptionalSlotRenderer.DEFAULT);
        slotFillContext.bindSlot(2, glueBottle);
        slotFillContext.bindSlot(3, result);
    }

    @Override
    public List<SlotContent> getIngredients() {
        return List.of(this.typeItem, this.subType, this.glueBottle);
    }

    @Override
    public List<SlotContent> getResults() {
        return List.of(this.result);
    }
}
