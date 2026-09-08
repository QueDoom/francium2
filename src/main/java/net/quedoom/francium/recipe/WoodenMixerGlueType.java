package net.quedoom.francium.recipe;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.quedoom.francium.init.ModItems;

public enum WoodenMixerGlueType {
    NORMAL,
    VEGAN,
    ECHO,
    SUPER,
    STEEL;

    public static WoodenMixerGlueType fromStack(ItemStack value) {
        if (value.is(ModItems.LEAF)) {
            return VEGAN;
        } else if (value.is(Items.ECHO_SHARD)) {
            return ECHO;
        } else if (value.is(Items.SLIME_BLOCK)) {
            return NORMAL;
        } else if (value.is(Items.HONEY_BLOCK)) {
            return SUPER;
        }else if (value.is(Items.COAL)) {
            return STEEL;
        } else {
            throw new IllegalArgumentException("Not a valid Stack");
        }
    }

    public static ItemStack toStack(WoodenMixerGlueType value) {
        return switch (value) {
            default -> Items.SLIME_BLOCK.getDefaultInstance();
            case VEGAN -> ModItems.LEAF.getDefaultInstance();
            case ECHO -> Items.ECHO_SHARD.getDefaultInstance();
            case SUPER -> Items.HONEY_BLOCK.getDefaultInstance();
            case STEEL -> Items.COAL.getDefaultInstance();
        };
    }

    public static Item toItem(WoodenMixerGlueType value) {
        return switch (value) {
            default -> Items.SLIME_BLOCK;
            case VEGAN -> ModItems.LEAF;
            case ECHO -> Items.ECHO_SHARD;
            case SUPER -> Items.HONEY_BLOCK;
            case STEEL -> Items.COAL;
        };
    }

    public Item toItem() {
        return switch (this) {
            default -> Items.SLIME_BLOCK;
            case VEGAN -> ModItems.LEAF;
            case ECHO -> Items.ECHO_SHARD;
            case SUPER -> Items.HONEY_BLOCK;
            case STEEL -> Items.COAL;
        };
    }

    public static int toInt(WoodenMixerGlueType value) {
        return switch (value) {
            default -> 0;
            case VEGAN -> 1;
            case ECHO -> 2;
            case SUPER -> 3;
            case STEEL -> 4;
        };
    }

    public static boolean test(WoodenMixerGlueType type, GlueMixerInput input) {
        return toStack(type) == input.type();
    }
}
