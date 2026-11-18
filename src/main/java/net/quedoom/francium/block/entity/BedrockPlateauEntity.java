package net.quedoom.francium.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.ticks.ContainerSingleItem;
import net.quedoom.francium.block.BedrockPlateauBlock;
import net.quedoom.francium.init.ModBlockEntities;
import net.quedoom.francium.init.ModBlocks;
import net.quedoom.francium.init.ModRecipeTypes;
import net.quedoom.francium.recipe.BedrockCrushingRecipe;
import net.quedoom.francium.recipe.BedrockCrushingRecipeInput;
import net.quedoom.francium.recipe.DeepMergingRecipe;
import org.jspecify.annotations.Nullable;

import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;

public class BedrockPlateauEntity extends SingleItemBlockEntity {
    public BedrockPlateauEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.BEDROCK_PLATEAU, worldPosition, blockState);
    }

    public boolean crush(Level level, BlockPos pos, double fallDistance) {
        if (!(level.getBlockEntity(pos) instanceof BedrockPlateauEntity bedrockPlateau)) return false;
        BedrockCrushingRecipeInput input = new BedrockCrushingRecipeInput(bedrockPlateau.getTheItem(), fallDistance);
        Optional<RecipeHolder<BedrockCrushingRecipe>> foundRecipe;
        if (level instanceof ServerLevel serverLevel) {
            Collection<RecipeHolder<BedrockCrushingRecipe>> bedrockCrushingRecipes = serverLevel.recipeAccess().getAllOfType(ModRecipeTypes.BEDROCK_CRUSHING);
            foundRecipe = bedrockCrushingRecipes.stream()
                    .filter(h -> h.value().matches(input, level))
                    .max(Comparator.comparingDouble(h -> h.value().fallDistance()));
        } else {
            foundRecipe = Optional.empty();
        }

        if (foundRecipe.isPresent()) {
            BedrockCrushingRecipe recipe = foundRecipe.get().value();
            bedrockPlateau.clearContent();
            Vec3 centerPos = getBlockPos().getCenter();
            double x = centerPos.x;
            double y = centerPos.y;
            double z = centerPos.z;
            Containers.dropItemStack(level, x, y, z, recipe.assemble(input));
            level.setBlockAndUpdate(pos, ModBlocks.BEDROCK_PLATEAU.defaultBlockState().setValue(BedrockPlateauBlock.FULL, true));
            return true;
        }
        return false;
    }

}
