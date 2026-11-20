package net.quedoom.francium.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.quedoom.francium.block.entity.BlockContainingEntity;
import net.quedoom.francium.block.entity.GlueMixerEntity;
import net.quedoom.francium.init.ModRecipeTypes;
import net.quedoom.francium.recipe.DeepMergerInput;
import net.quedoom.francium.recipe.DeepMergingRecipe;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

public class BlockContainingBlock extends BaseEntityBlock {
    private final Block casingBlock;

    public BlockContainingBlock(Block casingBlock, Properties properties) {
        super(properties);
        this.casingBlock = casingBlock;
    }
    public static final MapCodec<BlockContainingBlock> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
            BuiltInRegistries.BLOCK.byNameCodec().fieldOf("casing_block").forGetter(BlockContainingBlock::getCasingBlock),
            propertiesCodec()
    ).apply(i, BlockContainingBlock::new));

    @Override
    protected MapCodec<BlockContainingBlock> codec() {
        return CODEC;
    }

    public Block getCasingBlock() {
        return casingBlock;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return new BlockContainingEntity(worldPosition, blockState);
    }

    @Override
    protected ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData) {
        return casingBlock.asItem().getDefaultInstance();
    }
}
