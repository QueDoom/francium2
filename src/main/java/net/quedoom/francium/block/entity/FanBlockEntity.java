package net.quedoom.francium.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.quedoom.francium.api.prickle.FranciumConfigManager;
import net.quedoom.francium.block.FanBlock;
import net.quedoom.francium.init.ModBlockEntities;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.francium.init.ModTags;
import org.jspecify.annotations.Nullable;

public class FanBlockEntity extends BlockEntity {
    private float spinning = 0;
    private float spinSpeed = 1;
    private boolean isSpinning = false;
    private int tick = 0;

    private static float MINI_MULT = 16f/18f;

    public FanBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.FAN, worldPosition, blockState);
    }

    public static void tick(ServerLevel level, BlockState state, BlockPos pos, FanBlockEntity entity) {
        entity.tick = (entity.tick + 1) % FranciumConfigManager.CONFIG.stoked_fire_time - 1;
        if (entity.isSpinning) {
            if (entity.spinSpeed < 1) {
                entity.spinSpeed = 1;
            }
            entity.spinSpeed *= FranciumConfigManager.CONFIG.fan_acceleration;
            entity.spinSpeed = Math.clamp(entity.spinSpeed, 0f, FranciumConfigManager.CONFIG.fan_max_speed);
        } else {
            entity.spinSpeed *= FranciumConfigManager.CONFIG.fan_deacceleration;
        }
        entity.setSpinning((entity.spinning + entity.spinSpeed) % 360);

        if (entity.spinSpeed < FranciumConfigManager.CONFIG.fan_max_speed * MINI_MULT) return;
        Direction facing = state.getValue(FanBlock.FACING);

        if (level.getGameTime() % 2 == 0) {
            spawnWindParticles(level, pos, facing, entity.spinSpeed / FranciumConfigManager.CONFIG.fan_max_speed);
        }

        if (entity.tick != 0) return;

        for (int i = 1; i <= FranciumConfigManager.CONFIG.fan_max_reach; i++) {
            BlockState targetState = level.getBlockState(pos.relative(facing, i));
            if (targetState.is(ModTags.Blocks.FAN_BLOWS_THROUGH)) {
                    if (targetState.is(Blocks.FIRE)) {
                        boolean north = targetState.getValue(FireBlock.NORTH);
                        boolean east = targetState.getValue(FireBlock.EAST);
                        boolean south = targetState.getValue(FireBlock.SOUTH);
                        boolean west = targetState.getValue(FireBlock.WEST);
                        boolean up = targetState.getValue(FireBlock.UP);

                        if (!north && !east && !south && !west && !up) {
                            if (level.getRandom().nextFloat() <= FranciumConfigManager.CONFIG.stoke_fire_chance)
                                level.setBlockAndUpdate(pos.relative(facing, i), ModBlocks.STOKED_FIRE.defaultBlockState());

                        }
                    }

                    if (targetState.is(ModBlocks.STOKED_FIRE)) {
                        ((StokedFireEntity) level.getBlockEntity(pos.relative(facing, i))).blow();
                    }
            } else break;
        }
    }

    private static void spawnWindParticles(ServerLevel level, BlockPos pos, Direction facing, float strength) {
        RandomSource random = level.getRandom();
        int maxReach = FranciumConfigManager.CONFIG.fan_max_reach;

        double reach = 0;
        for (int i = 1; i <= maxReach; i++) {
            BlockState s = level.getBlockState(pos.relative(facing, i));
            if (s.is(ModTags.Blocks.FAN_BLOWS_THROUGH) || s.isAir()) {
                reach = i;
            } else break;
        }
        if (reach == 0) return;

        if (random.nextFloat() > 0.6f + 0.4f * strength) return;

        double vx = facing.getStepX();
        double vy = facing.getStepY();
        double vz = facing.getStepZ();
        double speed = 0.03 + 0.03 * strength;

        float expected = (0.3f + 0.2f * strength) * (float) reach;
        int amount = (int) expected;
        if (random.nextFloat() < expected - amount) amount++;

        for (int n = 0; n < amount; n++) {
            double fwd = 0.6 + random.nextDouble() * Math.max(0.1, reach - 0.6 - 0.4);

            double ox = facing.getAxis() == Direction.Axis.X ? 0 : (random.nextDouble() - 0.5) * 0.7;
            double oy = facing.getAxis() == Direction.Axis.Y ? 0 : (random.nextDouble() - 0.5) * 0.7;
            double oz = facing.getAxis() == Direction.Axis.Z ? 0 : (random.nextDouble() - 0.5) * 0.7;

            double x = pos.getX() + 0.5 + vx * fwd + ox;
            double y = pos.getY() + 0.5 + vy * fwd + oy;
            double z = pos.getZ() + 0.5 + vz * fwd + oz;

            level.sendParticles(ParticleTypes.CLOUD, x, y, z, 0, vx, vy, vz, speed);
        }
    }

    public float spinning() {
        return spinning;
    }

    public void setSpinning(float spinning) {
        this.spinning = spinning;
        setChanged();
    }

    public boolean isSpinning() {
        return this.isSpinning;
    }

    public void isSpinning(boolean value) {
        this.isSpinning = value;
        setChanged();
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        this.spinning = input.getFloatOr("spinning", 0f);
        this.spinSpeed = input.getFloatOr("spinSpeed", 0f);
        this.isSpinning = input.getBooleanOr("isSpinning", false);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putFloat("spinning", this.spinning);
        output.putFloat("spinSpeed", this.spinSpeed);
        output.putBoolean("isSpinning", this.isSpinning);
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
