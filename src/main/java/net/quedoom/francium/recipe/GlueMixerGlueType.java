package net.quedoom.francium.recipe;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.quedoom.francium.init.ModItems;

public enum GlueMixerGlueType {
    NORMAL,
    VEGAN,
    ECHO,
    SUPER;

    public static GlueMixerGlueType fromStack(ItemStack value) {
        if (value.is(ModItems.LEAF)) {
            return VEGAN;
        } else if (value.is(Items.ECHO_SHARD)) {
            return ECHO;
        } else if (value.is(Items.SLIME_BLOCK)) {
            return NORMAL;
        } else if (value.is(Items.HONEY_BLOCK)) {
            return SUPER;
        } else {
            throw new IllegalArgumentException("Not a valid Stack");
        }
    }

    public static ItemStack toStack(GlueMixerGlueType value) {
        return switch (value) {
            default -> Items.SLIME_BLOCK.getDefaultInstance();
            case VEGAN -> ModItems.LEAF.getDefaultInstance();
            case ECHO -> Items.ECHO_SHARD.getDefaultInstance();
            case SUPER -> Items.HONEY_BLOCK.getDefaultInstance();
        };
    }

    public static Item toItem(GlueMixerGlueType value) {
        return switch (value) {
            default -> Items.SLIME_BLOCK;
            case VEGAN -> ModItems.LEAF;
            case ECHO -> Items.ECHO_SHARD;
            case SUPER -> Items.HONEY_BLOCK;
        };
    }

    public static int toInt(GlueMixerGlueType value) {
        return switch (value) {
            default -> 0;
            case VEGAN -> 1;
            case ECHO -> 2;
            case SUPER -> 3;
        };
    }

    public static boolean test(GlueMixerGlueType type, GlueMixerInput input) {
        return toStack(type) == input.type();
    }
}
