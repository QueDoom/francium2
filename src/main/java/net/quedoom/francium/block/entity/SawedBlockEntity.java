package net.quedoom.francium.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.quedoom.francium.block.SawedBlock;
import net.quedoom.francium.init.ModBlockEntities;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

public class SawedBlockEntity extends BlockEntity {
    private Optional<BlockState> stateInside = Optional.empty();

    public SawedBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.SAWED_BLOCK_ENTITY, worldPosition, blockState);
    }

    public VoxelShape getContainingShape() {
        if (this.level == null) return SawedBlock.FULL_BOX;
        return stateInside.orElse(Blocks.DIRT.defaultBlockState()).getShape(this.level, this.worldPosition);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        putBlockState(output);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        getBlockState(input);
    }

    public void setBlock(BlockState state) {
        stateInside = Optional.of(state);
        setChanged();
    }

    public Optional<BlockState> getStateInside() { return stateInside; }

    private void putBlockState(ValueOutput output) {
        output.store("state_inside", BlockState.CODEC, stateInside.orElse(Blocks.AIR.defaultBlockState()));
    }
    private void getBlockState(ValueInput input) {
        stateInside = input.read("state_inside", BlockState.CODEC);
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
}
