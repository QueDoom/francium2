package net.quedoom.francium.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.quedoom.francium.init.ModBlockEntities;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.francium.recipe.ObsidianMixerRecipe;
import org.jspecify.annotations.Nullable;

import java.util.Collections;
import java.util.Optional;

public class ObsidianMixerEntity extends BlockEntity implements Container {
    public NonNullList<ItemStack> CONTAINER = NonNullList.withSize(4, ItemStack.EMPTY);
    /**
     * item 0 = idle;<br>
     * item 1 = mixing;<br>
     * item 2 = resetting;
     */
    private int state = 0;
    private boolean mixing = false;

    private int progress = 0;
    private float tick = 0f;
    private float stableTick = 0f;

    private int mixTime = 1;

    public ObsidianMixerEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.OBSIDIAN_MIXER_ENTITY, worldPosition, blockState);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, final BlockEntity e, BlockState below) {
        if (!(e instanceof final ObsidianMixerEntity entity)) return;

        if (level.isClientSide()) {
            if (entity.mixing) {
                entity.tick += 1 + (entity.stableTick * 0.1f);
                if (below.is(ModBlocks.STOKED_FIRE)) {
                    entity.stableTick += 1;
                } else if (entity.stableTick > 0) {
                    entity.stableTick -= 1;
                }

            } else {
                entity.tick = 0;
                entity.stableTick = 0;
            }
            return;
        }

        boolean wasMixing = entity.mixing;
        Optional<RecipeHolder<ObsidianMixerRecipe>> recipe = entity.getAvailableSlots() == 0
                ? ObsidianMixerRecipe.find(level, entity)
                : Optional.empty();

        recipe.ifPresentOrElse(obsidianMixerRecipeRecipeHolder -> {
            entity.mixing = true;
            entity.mixTime = Math.max(1, obsidianMixerRecipeRecipeHolder.value().time());
            if (below.is(ModBlocks.STOKED_FIRE)) {
                entity.progress += 1;
                if (entity.progress >= obsidianMixerRecipeRecipeHolder.value().time()) {
                    entity.clearContent();
                    Containers.dropItemStack(level, pos.getCenter().x, pos.getCenter().y, pos.getCenter().z, obsidianMixerRecipeRecipeHolder.value().assemble());

                    entity.progress = 0;
                    entity.mixing = false;
                }
            } else if (entity.progress > 0) {
                entity.mixing = true;
                entity.progress -= 1;
            } else {
                entity.mixing = false;
            }
        }, () -> {
            entity.progress = 0;
            entity.mixing = false;
        });

        if (entity.mixing != wasMixing) {
            entity.setChanged();
        }
    }

    public float getMergeAmount(float partialTicks) {
        if (!mixing) return 0f;
        float t = Math.min(1f, (stableTick + partialTicks) / mixTime);
        return t * t * (3f - 2f * t);
    }

    public boolean isMixing() {
        return this.mixing;
    }

    public float getSpinStep() {
        return 1 + (stableTick * 0.1f);
    }

    public float getSpinTick() {
        return mixing ? tick : 0F;
    }

    public boolean addItem(ItemStack item) {
        if (getAvailableSlots() == 0) return false;

        setItem(getFirstAvailableSlot(), item);
        return true;
    }

    public int getFirstAvailableSlot() {
        int i = 0;
        for (ItemStack itemStack : CONTAINER) {
            if (itemStack.isEmpty()) return i;
            i += 1;
        }
        return -1;
    }

    public int getAvailableSlots() {
        int slots = 0;
        for (ItemStack itemStack : CONTAINER) {
            if (itemStack.isEmpty()) slots += 1;
        }
        return slots;
    }

    @Override
    public int getContainerSize() {
        return CONTAINER.size();
    }

    @Override
    public boolean isEmpty() {
        return CONTAINER.stream().allMatch(ItemStack::isEmpty);
    }

    @Override
    public ItemStack getItem(int slot) {
        return CONTAINER.get(slot);
    }

    @Override
    public ItemStack removeItem(int slot, int count) {
        ItemStack result = ContainerHelper.removeItem(this.CONTAINER, slot, count);
        if (!result.isEmpty()) {
            this.setChanged();
        }

        return result;
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return ContainerHelper.takeItem(CONTAINER, slot);
    }

    @Override
    public void setItem(int slot, ItemStack itemStack) {
        this.CONTAINER.set(slot, itemStack.copyWithCount(1));
        itemStack.limitSize(this.getMaxStackSize(itemStack));
        this.setChanged();
    }

    @Override
    public boolean stillValid(Player player) {
        return Container.stillValidBlockEntity(this, player);
    }

    @Override
    public void clearContent() {
        Collections.fill(CONTAINER, ItemStack.EMPTY);
        setChanged();
    }

    // save

    @Override
    public void setChanged() {
        super.setChanged();
        if (level != null &&!level.isClientSide()) {
            level.sendBlockUpdated(worldPosition, this.getBlockState(), this.getBlockState(), Block.UPDATE_ALL);
        }
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
//        Collections.fill(CONTAINER, ItemStack.EMPTY);
        ContainerHelper.saveAllItems(output, CONTAINER, true);
        output.putInt("item", state);
        output.putInt("progress", progress);
        output.putBoolean("mixing", mixing);
        output.putInt("mixTime", mixTime);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        ContainerHelper.loadAllItems(input, CONTAINER);
        state = input.getInt("item").orElse(0);
        progress = input.getIntOr("progress", 0);
        mixing = input.getBooleanOr("mixing", false);
        mixTime = input.getIntOr("mixTime", 0);
    }

}
