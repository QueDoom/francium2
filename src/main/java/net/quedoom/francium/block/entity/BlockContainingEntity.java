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
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.ticks.ContainerSingleItem;
import net.quedoom.francium.init.ModBlockEntities;
import org.jspecify.annotations.Nullable;

public class BlockContainingEntity extends BlockEntity implements ContainerSingleItem.BlockContainerSingleItem {
    public NonNullList<ItemStack> containerList = NonNullList.withSize(1, ItemStack.EMPTY);

    public BlockContainingEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.BLOCK_CONTAINING_ENTITY, worldPosition, blockState);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        ContainerHelper.saveAllItems(output, containerList);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        ContainerHelper.loadAllItems(input, containerList);
    }

    @Override
    public BlockEntity getContainerBlockEntity() {
        return this;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public ItemStack getTheItem() {
        return containerList.getFirst();
    }

    @Override
    public void setTheItem(ItemStack itemStack) {
        containerList.set(0, itemStack.copyWithCount(1));
        setChanged();
    }

    @Override
    public void clearContent() {
        containerList.set(0, ItemStack.EMPTY);
        setChanged();
    }

    @Override
    public void preRemoveSideEffects(BlockPos pos, BlockState state) {
        if (!this.isEmpty()) {
            if (getTheItem().getItem() instanceof BlockItem blockItem) {
                this.level.setBlock(pos, blockItem.getBlock().defaultBlockState(), Block.UPDATE_ALL);
            }
        }
    }

    // sync

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
