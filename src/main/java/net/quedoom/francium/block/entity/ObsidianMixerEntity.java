package net.quedoom.francium.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.quedoom.francium.init.ModBlockEntities;
import org.jspecify.annotations.Nullable;

import java.util.Collections;

public class ObsidianMixerEntity extends BlockEntity implements Container {
    public NonNullList<ItemStack> CONTAINER = NonNullList.withSize(4, ItemStack.EMPTY);
    /**
     * state 0 = idle;<br>
     * state 1 = mixing;<br>
     * state 2 = resetting;
     */
    private int state = 0;
    private int progress = 0;

    public ObsidianMixerEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.OBSIDIAN_MIXER_ENTITY, worldPosition, blockState);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, BlockEntity e) {
        if (!(e instanceof ObsidianMixerEntity entity)) return;


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
        ContainerHelper.saveAllItems(output, CONTAINER);
        output.putInt("state", state);
        output.putInt("progress", progress);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        ContainerHelper.loadAllItems(input, CONTAINER);
        state = input.getInt("state").orElse(0);
        progress = input.getInt("progress").orElse(0);
    }

}
