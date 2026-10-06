package net.quedoom.francium.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.quedoom.francium.init.ModRecipeTypes;
import net.quedoom.francium.recipe.BlockRecipeInput;
import net.quedoom.francium.recipe.BurningRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(FireBlock.class)
public class BurningMixin {
    @Inject(
            method = "checkBurnOut",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void burning(Level level, BlockPos pos, int chance, RandomSource random, int age, CallbackInfo ci) {
        BlockState blockState = level.getBlockState(pos);
        Block block = blockState.getBlock();
        if (!(level instanceof ServerLevel serverLevel)) return;
        find(serverLevel, block).ifPresent(holder -> {
            BurningRecipe recipe = holder.value();
            float randomChance = (float) Math.random();
            if (recipe.chance() <= randomChance) {
                level.setBlock(pos, recipe.result(), 3);
                ci.cancel();
            }
        });
    }

    @Unique
    private static Optional<RecipeHolder<BurningRecipe>> find(ServerLevel level, Block block) {
        BlockRecipeInput input = new BlockRecipeInput(
                block
        );
        return level.recipeAccess().getRecipeFor(ModRecipeTypes.BURNING, input, level);
    }
}

