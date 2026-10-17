package net.quedoom.francium.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.AbstractBannerBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.quedoom.francium.Francium;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.francium.init.ModLootTables;
import net.quedoom.francium.init.ModTags;
import net.quedoom.francium.util.ColorHelper;
import net.quedoom.francium.util.DropFromLootTable;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(Block.class)
public class PlayerDestroyBlockMixin {
    @Shadow
    public static void dropResources(BlockState state, Level level, BlockPos pos) {
        throw new UnsupportedOperationException("Implemented via mixin");
    }

    @Shadow
    public static void dropResources(BlockState state, LevelAccessor level, BlockPos pos, @Nullable BlockEntity blockEntity) {
        throw new UnsupportedOperationException("Implemented via mixin");
    }

    @Inject(
            method = "playerDestroy",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/Block;dropResources(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/entity/BlockEntity;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/item/ItemStack;)V"),
            cancellable = true)
    private void dropDust(Level level, Player player, BlockPos pos, BlockState state, BlockEntity blockEntity, ItemStack destroyedWith, CallbackInfo ci) {
        if (((Object) this) instanceof AbstractBannerBlock abstractBannerBlock) {
            dropResources(Blocks.WHITE_BANNER.defaultBlockState(), level, pos);
            Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), ColorHelper.toItem(abstractBannerBlock.getColor()).getDefaultInstance());
            ci.cancel();
        }

        if (state.is(Blocks.OAK_LEAVES) || state.is(Blocks.DARK_OAK_LEAVES)) {
            if (level.getRandom().nextInt(100) < 1) {
                level.setBlockAndUpdate(pos, ModBlocks.THICK_APPLE.defaultBlockState());
            }
            return;
        }

        if (state.is(ModTags.Blocks.DROPS_FORBIDDEN_DUST) || state.is(ModTags.Blocks.DROPS_FORBIDDEN_FLAKE) || state.is(ModTags.Blocks.SMALL_DROPS_FORBIDDEN_FLAKE)) {
            if (state.is(ModTags.Blocks.DROPS_FORBIDDEN_DUST)) {
               Block.dropResources(ModBlocks.SPECIAL_FORBIDDEN_DUST.defaultBlockState(), level, pos);
            } else if (state.is(ModTags.Blocks.DROPS_FORBIDDEN_FLAKE)) {
                Block.dropResources(ModBlocks.SPECIAL_FORBIDDEN_FLAKE.defaultBlockState(), level, pos);
            } else {
                Block.dropResources(ModBlocks.SMALL_SPECIAL_FORBIDDEN_FLAKE.defaultBlockState(), level, pos);
            }
            ci.cancel();
            return;
        }

        if (state.is(ModTags.Blocks.TILLS)) {
            boolean blocks = state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.ROOTED_DIRT) || state.is(Blocks.COARSE_DIRT) || state.is(Blocks.PODZOL);
            if (destroyedWith.is(ItemTags.HOES)) {
                level.setBlockAndUpdate(pos, Blocks.FARMLAND.defaultBlockState());
                ci.cancel();
            } else {
                if (blocks) {
                    level.setBlockAndUpdate(pos, Blocks.DIRT.defaultBlockState());
                    ci.cancel();
                }
            }
        }
    }

}
