package net.quedoom.francium.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.quedoom.francium.block.entity.BlockContainingItemsEntity;
import org.jspecify.annotations.Nullable;

public class BlockContainingItems extends BaseEntityBlock {
    private final Block casingBlock;

    public BlockContainingItems(Block casingBlock, Properties properties) {
        super(properties);
        this.casingBlock = casingBlock;
    }
    public static final MapCodec<BlockContainingItems> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
            BuiltInRegistries.BLOCK.byNameCodec().fieldOf("casing_block").forGetter(BlockContainingItems::getCasingBlock),
            propertiesCodec()
    ).apply(i, BlockContainingItems::new));

    @Override
    protected MapCodec<BlockContainingItems> codec() {
        return CODEC;
    }

    public Block getCasingBlock() {
        return casingBlock;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return new BlockContainingItemsEntity(worldPosition, blockState);
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack destroyedWith) {
        player.awardStat(Stats.BLOCK_MINED.get(this));
        player.causeFoodExhaustion(0.005F);
    }

    @Override
    protected ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData) {
        return this.casingBlock.asItem().getDefaultInstance();
    }
}
