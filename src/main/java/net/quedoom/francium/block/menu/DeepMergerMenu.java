package net.quedoom.francium.block.menu;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeAccess;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.quedoom.francium.init.*;
import net.quedoom.francium.recipe.DeepMergerInput;
import net.quedoom.francium.recipe.DeepMergingRecipe;
import net.quedoom.francium.recipe.WoodenMergerInput;
import net.quedoom.francium.recipe.WoodenMergingRecipe;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class DeepMergerMenu extends ItemCombinerMenu {
    public static final int FIRST_SLOT = 0;
    public static final int SECOND_SLOT = 1;
    public static final int THIRD_SLOT = 2;
    public static final int GLUE_SLOT = 3;
    public static final int WILDCARD_SLOT = 4;
    public static final int RESULT_SLOT = 5;
    private final Level level;
    private final DataSlot hasRecipeError;

    public DeepMergerMenu(final int containerId, final Inventory inventory) {
        this(containerId, inventory, ContainerLevelAccess.NULL);
    }

    public DeepMergerMenu(final int containerId, final Inventory inventory, final ContainerLevelAccess access) {
        this(containerId, inventory, access, inventory.player.level());
    }

    private DeepMergerMenu(final int containerId, final Inventory inventory, final ContainerLevelAccess access, final Level level) {
        super(ModMenuTypes.DEEP_MERGER, containerId, inventory, access, createInputSlotDefinitions(level.recipeAccess()));
        this.hasRecipeError = DataSlot.standalone();
        this.level = level;
        this.addDataSlot(this.hasRecipeError).set(0);
    }

    private static ItemCombinerMenuSlotDefinition createInputSlotDefinitions(final RecipeAccess recipes) {
        ItemCombinerMenuSlotDefinition.Builder builder = ItemCombinerMenuSlotDefinition.create();

        builder = builder.withSlot(FIRST_SLOT, 29, 13, item -> true);
        builder = builder.withSlot(SECOND_SLOT, 29, 33, item -> true);
        builder = builder.withSlot(THIRD_SLOT, 29, 53, item -> true);
        builder = builder.withSlot(GLUE_SLOT, 70, 23, item -> item.is(ModTags.Items.WOODEN_MERGER_GLUE));
        builder = builder.withSlot(WILDCARD_SLOT, 70, 45, item -> true);

        return builder.withResultSlot(RESULT_SLOT, 123, 33).build();
    }

    @Override
    protected void onTake(Player player, ItemStack carried) {
        carried.onCraftedBy(player, carried.getCount());
        this.resultSlots.awardUsedRecipes(player, this.getRelevantItems());
        this.shrinkStackInSlot(0);
        this.shrinkStackInSlot(1);
        this.shrinkStackInSlot(2);
        this.shrinkStackInSlot(3);
        this.shrinkStackInSlot(4);
        this.access.execute((level, pos) -> level.levelEvent(1044, pos, 0));
    }

    private List<ItemStack> getRelevantItems() {
        return List.of(
                this.inputSlots.getItem(0),
                this.inputSlots.getItem(1),
                this.inputSlots.getItem(2),
                this.inputSlots.getItem(3),
                this.inputSlots.getItem(4).isEmpty() ? ModItems.UNUSED_ITEM_BECAUSE_I_CANT_FIGURE_OUT_HOW_TO_MAKE_OPTIONAL_ITEMS_BECAUSE_IM_STUPID.getDefaultInstance() : this.inputSlots.getItem(4)
        );
    }

    private DeepMergerInput createRecipeInput() {
        return new DeepMergerInput(
                this.inputSlots.getItem(0),
                this.inputSlots.getItem(1),
                this.inputSlots.getItem(2),
                this.inputSlots.getItem(3),
                this.inputSlots.getItem(4).isEmpty() ? ModItems.UNUSED_ITEM_BECAUSE_I_CANT_FIGURE_OUT_HOW_TO_MAKE_OPTIONAL_ITEMS_BECAUSE_IM_STUPID.getDefaultInstance() : this.inputSlots.getItem(4)
        );
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
            boolean hasRecipeError =
                    this.getSlot(FIRST_SLOT).hasItem() &&
                    this.getSlot(SECOND_SLOT).hasItem() &&
                    this.getSlot(THIRD_SLOT).hasItem() &&
                    this.getSlot(GLUE_SLOT).hasItem() &&
                   (this.getSlot(WILDCARD_SLOT).hasItem() || this.getSlot(WILDCARD_SLOT).getItem().is(ModItems.UNUSED_ITEM_BECAUSE_I_CANT_FIGURE_OUT_HOW_TO_MAKE_OPTIONAL_ITEMS_BECAUSE_IM_STUPID)) &&
                   !this.getSlot(this.getResultSlot()).hasItem();
            this.hasRecipeError.set(hasRecipeError ? 1 : 0);
        }

    }

    @Override
    protected boolean isValidBlock(BlockState state) {
        return state.is(ModBlocks.DEEP_MERGER);
    }

    @Override
    public void createResult() {
        DeepMergerInput input = this.createRecipeInput();
        Level var4 = this.level;
        Optional<RecipeHolder<DeepMergingRecipe>> foundRecipe;
        if (var4 instanceof ServerLevel serverLevel) {
            foundRecipe = serverLevel.recipeAccess().getRecipeFor(ModRecipeTypes.DEEP_MERGING, input, serverLevel);
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
        } else if (!this.getSlot(THIRD_SLOT).hasItem()) {
            return true;
        } else if (!this.getSlot(WILDCARD_SLOT).hasItem()) {
            return true;
        } else {
            return stack.is(ModTags.Items.DEEP_MERGER_GLUE) && !this.getSlot(GLUE_SLOT).hasItem();
        }
    }
}
