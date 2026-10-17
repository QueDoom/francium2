package net.quedoom.francium.util;

import net.minecraft.client.color.item.MapColor;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class ColorHelper {
    public static Item toItem(DyeColor color) {
        return switch (color) {
            case WHITE -> Items.WHITE_DYE;
            case ORANGE -> Items.ORANGE_DYE;
            case MAGENTA -> Items.MAGENTA_DYE;
            case LIGHT_BLUE -> Items.LIGHT_BLUE_DYE;
            case YELLOW -> Items.YELLOW_DYE;
            case LIME -> Items.LIME_DYE;
            case PINK -> Items.PINK_DYE;
            case GRAY -> Items.GRAY_DYE;
            case LIGHT_GRAY -> Items.LIGHT_GRAY_DYE;
            case CYAN -> Items.CYAN_DYE;
            case PURPLE -> Items.PURPLE_DYE;
            case BLUE -> Items.BLUE_DYE;
            case BROWN -> Items.BROWN_DYE;
            case GREEN -> Items.GREEN_DYE;
            case RED -> Items.RED_DYE;
            case BLACK -> Items.BLACK_DYE;
        };
    }

    public static Block toConcrete(DyeColor color) {
        return switch (color) {
            case WHITE -> Blocks.WHITE_CONCRETE;
            case ORANGE -> Blocks.ORANGE_CONCRETE;
            case MAGENTA -> Blocks.MAGENTA_CONCRETE;
            case LIGHT_BLUE -> Blocks.LIGHT_BLUE_CONCRETE;
            case YELLOW -> Blocks.YELLOW_CONCRETE;
            case LIME -> Blocks.LIME_CONCRETE;
            case PINK -> Blocks.PINK_CONCRETE;
            case GRAY -> Blocks.GRAY_CONCRETE;
            case LIGHT_GRAY -> Blocks.LIGHT_GRAY_CONCRETE;
            case CYAN -> Blocks.CYAN_CONCRETE;
            case PURPLE -> Blocks.PURPLE_CONCRETE;
            case BLUE -> Blocks.BLUE_CONCRETE;
            case BROWN -> Blocks.BROWN_CONCRETE;
            case GREEN -> Blocks.GREEN_CONCRETE;
            case RED -> Blocks.RED_CONCRETE;
            case BLACK -> Blocks.BLACK_CONCRETE;
        };
    }

    public static Block toConcretePowder(DyeColor color) {
        return switch (color) {
            case WHITE -> Blocks.WHITE_CONCRETE_POWDER;
            case ORANGE -> Blocks.ORANGE_CONCRETE_POWDER;
            case MAGENTA -> Blocks.MAGENTA_CONCRETE_POWDER;
            case LIGHT_BLUE -> Blocks.LIGHT_BLUE_CONCRETE_POWDER;
            case YELLOW -> Blocks.YELLOW_CONCRETE_POWDER;
            case LIME -> Blocks.LIME_CONCRETE_POWDER;
            case PINK -> Blocks.PINK_CONCRETE_POWDER;
            case GRAY -> Blocks.GRAY_CONCRETE_POWDER;
            case LIGHT_GRAY -> Blocks.LIGHT_GRAY_CONCRETE_POWDER;
            case CYAN -> Blocks.CYAN_CONCRETE_POWDER;
            case PURPLE -> Blocks.PURPLE_CONCRETE_POWDER;
            case BLUE -> Blocks.BLUE_CONCRETE_POWDER;
            case BROWN -> Blocks.BROWN_CONCRETE_POWDER;
            case GREEN -> Blocks.GREEN_CONCRETE_POWDER;
            case RED -> Blocks.RED_CONCRETE_POWDER;
            case BLACK -> Blocks.BLACK_CONCRETE_POWDER;
        };
    }

    public static Block toTerracotta(DyeColor color) {
        return switch (color) {
            case WHITE -> Blocks.WHITE_TERRACOTTA;
            case ORANGE -> Blocks.ORANGE_TERRACOTTA;
            case MAGENTA -> Blocks.MAGENTA_TERRACOTTA;
            case LIGHT_BLUE -> Blocks.LIGHT_BLUE_TERRACOTTA;
            case YELLOW -> Blocks.YELLOW_TERRACOTTA;
            case LIME -> Blocks.LIME_TERRACOTTA;
            case PINK -> Blocks.PINK_TERRACOTTA;
            case GRAY -> Blocks.GRAY_TERRACOTTA;
            case LIGHT_GRAY -> Blocks.LIGHT_GRAY_TERRACOTTA;
            case CYAN -> Blocks.CYAN_TERRACOTTA;
            case PURPLE -> Blocks.PURPLE_TERRACOTTA;
            case BLUE -> Blocks.BLUE_TERRACOTTA;
            case BROWN -> Blocks.BROWN_TERRACOTTA;
            case GREEN -> Blocks.GREEN_TERRACOTTA;
            case RED -> Blocks.RED_TERRACOTTA;
            case BLACK -> Blocks.BLACK_TERRACOTTA;
        };
    }

    public static DyeColor dyeColorFromString(String string) {
        if (string.contains("white")) {
            return DyeColor.WHITE;
        }
        if (string.contains("light_gray")) {
            return DyeColor.LIGHT_GRAY;
        }
        if (string.contains("gray")) {
            return DyeColor.GRAY;
        }
        if (string.contains("black")) {
            return DyeColor.BLACK;
        }
        if (string.contains("brown")) {
            return DyeColor.BROWN;
        }
        if (string.contains("red")) {
            return DyeColor.RED;
        }
        if (string.contains("orange")) {
            return DyeColor.ORANGE;
        }
        if (string.contains("yellow")) {
            return DyeColor.YELLOW;
        }
        if (string.contains("lime")) {
            return DyeColor.LIME;
        }
        if (string.contains("green")) {
            return DyeColor.GREEN;
        }
        if (string.contains("cyan")) {
            return DyeColor.CYAN;
        }
        if (string.contains("light_blue")) {
            return DyeColor.LIGHT_BLUE;
        }
        if (string.contains("blue")) {
            return DyeColor.BLUE;
        }
        if (string.contains("purple")) {
            return DyeColor.PURPLE;
        }
        if (string.contains("magenta")) {
            return DyeColor.MAGENTA;
        }
        if (string.contains("pink")) {
            return DyeColor.PINK;
        }
        return DyeColor.WHITE;
    }
}
