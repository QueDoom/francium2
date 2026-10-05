package net.quedoom.francium.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.TickTask;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.quedoom.francium.init.ModBlockEntities;
import org.jspecify.annotations.Nullable;

import java.util.Collections;

public class BlockContainingItemsEntity extends BlockEntity implements Container {
    public NonNullList<ItemStack> container = NonNullList.withSize(6, ItemStack.EMPTY);

    public BlockContainingItemsEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.BLOCK_CONTAINING_ITEMS_ENTITY, worldPosition, blockState);
    }

    @Override
    public void preRemoveSideEffects(BlockPos pos, BlockState state) {
        if (!(level instanceof ServerLevel server)) return;

        int slot = firstSlotWithItem();

        if (slot == -1) {
            Block.dropResources(state, server, pos, this);
            return;
        }

        Containers.dropItemStack(server, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, container.get(slot).copy());

        NonNullList<ItemStack> remaining = NonNullList.withSize(container.size(), ItemStack.EMPTY);
        for (int i = 0; i < container.size(); i++) {
            if (i != slot) remaining.set(i, container.get(i).copy());
        }

        clearContent();

        MinecraftServer ms = server.getServer();
        ms.schedule(new TickTask(ms.getTickCount(), () -> {
            server.setBlock(pos, state, Block.UPDATE_ALL);
            if (server.getBlockEntity(pos) instanceof BlockContainingItemsEntity be) {
                for (int i = 0; i < remaining.size(); i++) be.container.set(i, remaining.get(i));
                be.setChanged();
            }
        }));
    }

    public int firstSlotWithItem() {
        for (int i = 0; i < container.size(); i++)
            if (!container.get(i).isEmpty())
                return i;
        return -1;
    }

    public int availableSlots() {
        int filledSlots = 0;
        for (ItemStack stack : container) {
            if (!stack.isEmpty()) filledSlots += 1;
        }
        return getContainerSize() - filledSlots;
    }

    public boolean canPlace(ItemStack stack) {
        return availableSlots() >= stack.count();
    }

    public boolean placeInAvailableSlots(ItemStack stack) {
        if (!canPlace(stack)) return false;
        int toPlace = stack.count();
        for (int slot = 0; slot < container.size() && toPlace > 0; slot++) {
            if (container.get(slot).isEmpty()) {
                container.set(slot, stack.copyWithCount(1));
                toPlace--;
            }
        }
        setChanged();
        return true;
    }

    @Override
    public int getContainerSize() {
        return container.size();
    }

    @Override
    public boolean isEmpty() {
        return container.stream().allMatch(ItemStack::isEmpty);
    }

    @Override
    public ItemStack getItem(int slot) {
        return container.get(slot);
    }

    @Override
    public ItemStack removeItem(int slot, int count) {
        ItemStack result = ContainerHelper.removeItem(this.container, slot, count);
        if (!result.isEmpty()) {
            this.setChanged();
        }

        return result;
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return ContainerHelper.takeItem(container, slot);
    }

    @Override
    public void setItem(int slot, ItemStack itemStack) {
        this.container.set(slot, itemStack.copyWithCount(1));
        itemStack.limitSize(this.getMaxStackSize(itemStack));
        this.setChanged();
    }

    @Override
    public boolean stillValid(Player player) {
        return Container.stillValidBlockEntity(this, player);
    }

    @Override
    public void clearContent() {
        Collections.fill(container, ItemStack.EMPTY);
        this.setChanged();
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
        ContainerHelper.saveAllItems(output, container);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        ContainerHelper.loadAllItems(input, container);
    }
}
