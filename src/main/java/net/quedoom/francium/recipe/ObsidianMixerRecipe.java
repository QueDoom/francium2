package net.quedoom.francium.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.PrimitiveCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.quedoom.francium.block.entity.ObsidianMixerEntity;
import net.quedoom.francium.init.ModItems;
import net.quedoom.francium.init.ModRecipeTypes;
import net.quedoom.quet.misc.GetPath;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ObsidianMixerRecipe implements Recipe<ObsidianMixerRecipeInput> {
    public static final MapCodec<ObsidianMixerRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Ingredient.CODEC.fieldOf("firstItem").forGetter(ObsidianMixerRecipe::firstItem),
                    Ingredient.CODEC.fieldOf("secondItem").forGetter(ObsidianMixerRecipe::secondItem),
                    Ingredient.CODEC.fieldOf("thirdItem").forGetter(ObsidianMixerRecipe::thirdItem),
                    Ingredient.CODEC.fieldOf("fourthItem").forGetter(ObsidianMixerRecipe::fourthItem),
                    PrimitiveCodec.INT.fieldOf("time").forGetter(ObsidianMixerRecipe::time),
                    ItemStackTemplate.CODEC.fieldOf("result").forGetter(ObsidianMixerRecipe::result)
            ).apply(instance, ObsidianMixerRecipe::new)
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, ObsidianMixerRecipe> STREAM_CODEC = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC,
            ObsidianMixerRecipe::firstItem,
            Ingredient.CONTENTS_STREAM_CODEC,
            ObsidianMixerRecipe::secondItem,
            Ingredient.CONTENTS_STREAM_CODEC,
            ObsidianMixerRecipe::thirdItem,
            Ingredient.CONTENTS_STREAM_CODEC,
            ObsidianMixerRecipe::fourthItem,

            ByteBufCodecs.VAR_INT,
            ObsidianMixerRecipe::time,

            ItemStackTemplate.STREAM_CODEC,
            ObsidianMixerRecipe::result,

            ObsidianMixerRecipe::new
    );

    private final Ingredient firstItem, secondItem, thirdItem, fourthItem;
    private final int time;
    private final ItemStackTemplate result;

    public ObsidianMixerRecipe(Ingredient firstItem, Ingredient secondItem, Ingredient thirdItem, Ingredient fourthItem, int time, ItemStackTemplate result) {
        this.firstItem = firstItem;
        this.secondItem = secondItem;
        this.thirdItem = thirdItem;
        this.fourthItem = fourthItem;
        this.time = time;
        this.result = result;
    }

    public Ingredient firstItem() {
        return firstItem;
    }
    public Ingredient secondItem() {
        return secondItem;
    }
    public Ingredient thirdItem() {
        return thirdItem;
    }
    public Ingredient fourthItem() {
        return fourthItem;
    }
    public int time() {
        return time;
    }
    public ItemStackTemplate result() {
        return result;
    }

    public static Optional<RecipeHolder<ObsidianMixerRecipe>> find(Level level, ObsidianMixerEntity entity) {
        if (!(level instanceof ServerLevel server))  return Optional.empty();
        ObsidianMixerRecipeInput input = new ObsidianMixerRecipeInput(entity.getItem(0), entity.getItem(1), entity.getItem(2), entity.getItem(3));
        return server.recipeAccess().getRecipeFor(ModRecipeTypes.OBSIDIAN_MIXING, input, level);
    }

    @Override
    public boolean matches(ObsidianMixerRecipeInput input, Level level) {
        List<Ingredient> ingredients = List.of(firstItem, secondItem, thirdItem, fourthItem);
        List<ItemStack> stacks = List.of(
                input.firstItem(), input.secondItem(), input.thirdItem(), input.fourthItem());

        boolean[][] ok = new boolean[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                ok[i][j] = ingredients.get(i).test(stacks.get(j));
            }
        }
        return assign(ok, 0, new boolean[4]);
    }

    private static boolean assign(boolean[][] ok, int ingredient, boolean[] usedSlots) {
        if (ingredient == ok.length) return true;
        for (int slot = 0; slot < ok.length; slot++) {
            if (!usedSlots[slot] && ok[ingredient][slot]) {
                usedSlots[slot] = true;
                if (assign(ok, ingredient + 1, usedSlots)) return true;
                usedSlots[slot] = false;
            }
        }
        return false;
    }

    @Override
    public ItemStack assemble(ObsidianMixerRecipeInput input) {
        return result.create();
    }
    public ItemStack assemble() {
        return result.create();
    }

    @Override
    public boolean showNotification() {
        return false;
    }

    @Override
    public String group() {
        return GetPath.get(result.item().value());
    }

    @Override
    public RecipeSerializer<? extends Recipe<ObsidianMixerRecipeInput>> getSerializer() {
        return ModRecipeTypes.OBSIDIAN_MIXING_SERIALIZER;
    }

    @Override
    public RecipeType<? extends Recipe<ObsidianMixerRecipeInput>> getType() {
        return ModRecipeTypes.OBSIDIAN_MIXING;
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.NOT_PLACEABLE;
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return null;
    }

    public static final class Type implements RecipeType<ObsidianMixerRecipe> {
        private Type() {
        }

        public static final ObsidianMixerRecipe.Type INSTANCE = new ObsidianMixerRecipe.Type();
        public static final String ID = "obsidian_mixing";
    }
}
