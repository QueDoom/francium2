package net.quedoom.francium.block.menu;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.quedoom.francium.Francium;
import net.quedoom.francium.init.*;
import net.quedoom.francium.recipe.WoodenMergerInput;
import net.quedoom.francium.recipe.WoodenMergingRecipe;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class WoodenMergerMenu extends ItemCombinerMenu {
    public static final int FIRST_SLOT = 0;
    public static final int SECOND_SLOT = 1;
    public static final int GLUE_SLOT = 2;
    public static final int RESULT_SLOT = 3;
    private final Level level;
    private final DataSlot hasRecipeError;

    public WoodenMergerMenu(final int containerId, final Inventory inventory) {
        this(containerId, inventory, ContainerLevelAccess.NULL);
    }

    public WoodenMergerMenu(final int containerId, final Inventory inventory, final ContainerLevelAccess access) {
        this(containerId, inventory, access, inventory.player.level());
    }

    private WoodenMergerMenu(final int containerId, final Inventory inventory, final ContainerLevelAccess access, final Level level) {
        super(ModMenuTypes.WOODEN_MERGER, containerId, inventory, access, createInputSlotDefinitions(level.recipeAccess()));
        this.hasRecipeError = DataSlot.standalone();
        this.level = level;
        this.addDataSlot(this.hasRecipeError).set(0);
    }



    private static ItemCombinerMenuSlotDefinition createInputSlotDefinitions(final RecipeAccess recipes) {
        ItemCombinerMenuSlotDefinition.Builder builder = ItemCombinerMenuSlotDefinition.create();

        builder = builder.withSlot(FIRST_SLOT, 30, 17, item -> true);
        builder = builder.withSlot(SECOND_SLOT, 30, 43, item -> true);
        builder = builder.withSlot(GLUE_SLOT, 71, 30, item -> item.is(ModTags.Items.WOODEN_MERGER_GLUE));

        return builder.withResultSlot(RESULT_SLOT, 124, 30).build();
    }


    @Override
    protected void onTake(Player player, ItemStack carried) {
        carried.onCraftedBy(player, carried.getCount());
        this.resultSlots.awardUsedRecipes(player, this.getRelevantItems());
        this.shrinkStackInSlot(0);
        this.shrinkStackInSlot(1);
        this.shrinkStackInSlot(2);
//        this.access.execute((level, pos) -> level.levelEvent(1044, pos, 0));
    }

    private List<ItemStack> getRelevantItems() {
        return List.of(this.inputSlots.getItem(0), this.inputSlots.getItem(1), this.inputSlots.getItem(2));
    }

    private WoodenMergerInput createRecipeInput() {
        return new WoodenMergerInput(this.inputSlots.getItem(0), this.inputSlots.getItem(1), this.inputSlots.getItem(2));
    }

    private void shrinkStackInSlot(final int slot) {
        ItemStack stack = this.inputSlots.getItem(slot);
        if (!stack.isEmpty()) {
            stack.shrink(1);
            this.inputSlots.setItem(slot, stack);
        }

    }

    @Override
    public void slotsChanged(final Container container) {
        super.slotsChanged(container);
        if (this.level instanceof ServerLevel) {
            boolean hasRecipeError = this.getSlot(0).hasItem() && this.getSlot(1).hasItem() && this.getSlot(2).hasItem() && !this.getSlot(this.getResultSlot()).hasItem();
            this.hasRecipeError.set(hasRecipeError ? 1 : 0);
        }

    }

    @Override
    protected boolean isValidBlock(BlockState state) {
        return state.is(ModBlocks.WOODEN_MERGER);
    }


    public void createResult() {
        WoodenMergerInput input = this.createRecipeInput();
        Level var4 = this.level;
        Optional<RecipeHolder<WoodenMergingRecipe>> foundRecipe;
        if (var4 instanceof ServerLevel serverLevel) {
            foundRecipe = serverLevel.recipeAccess().getRecipeFor(ModRecipeTypes.WOODEN_MERGING, input, serverLevel);
        } else {
            foundRecipe = Optional.empty();
        }
        foundRecipe.ifPresentOrElse((recipe) -> {
            ItemStack result = (recipe.value()).assemble(input);
            this.resultSlots.setRecipeUsed(recipe);
            this.resultSlots.setItem(0, result);
        }, () -> {
            this.resultSlots.setRecipeUsed(null);
            this.resultSlots.setItem(0, ItemStack.EMPTY);
        });
    }

    @Override
    public boolean canTakeItemForPickAll(final ItemStack carried, final Slot target) {
        return target.container != this.resultSlots && super.canTakeItemForPickAll(carried, target);
    }

    @Override
    public boolean canMoveIntoInputSlots(final ItemStack stack) {
        if (!this.getSlot(FIRST_SLOT).hasItem()) {
            return true;
        } else if (!this.getSlot(SECOND_SLOT).hasItem()) {
            return true;
        } else {
            return stack.is(ModTags.Items.WOODEN_MERGER_GLUE) && !this.getSlot(GLUE_SLOT).hasItem();
        }
    }

    public boolean hasRecipeError() {
        return this.hasRecipeError.get() > 0;
    }
}
