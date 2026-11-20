package net.quedoom.francium.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.ContainerUser;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.quedoom.francium.Francium;
import net.quedoom.francium.block.menu.BundleTableMenu;
import net.quedoom.francium.init.ModBlockEntities;
import org.jspecify.annotations.Nullable;

public class BundleTableEntity extends BaseContainerBlockEntity implements Container {
    public final NonNullList<ItemStack> container = NonNullList.withSize(6, ItemStack.EMPTY);

    public BundleTableEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.BUNDLE_TABLE, worldPosition, blockState);
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("menu." + Francium.MOD_ID + ".bundle_table");
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return container;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> items) {
        if (container.size() == items.size()) {
            for (int i = 0; i < items.size(); i++) {
                container.set(i, items.get(i));
            }
        }
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
        return new BundleTableMenu(containerId, inventory, this,  2);
    }

    @Override
    public void stopOpen(ContainerUser containerUser) {
        setChanged();
    }

    @Override
    public int getContainerSize() {
        return 6;
    }

    public NonNullList<ItemStack> getContainer() {
        return container;
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        ContainerHelper.saveAllItems(output, container);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        container.clear();
        ContainerHelper.loadAllItems(input, container);
    }

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
}
