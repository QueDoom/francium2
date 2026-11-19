package net.quedoom.francium.util;

import net.minecraft.client.color.item.MapColor;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public record ColorHelper(DyeColor color) {
    public static final ColorHelper WHITE = new ColorHelper(DyeColor.WHITE);
    public static final ColorHelper ORANGE = new ColorHelper(DyeColor.ORANGE);
    public static final ColorHelper MAGENTA = new ColorHelper(DyeColor.MAGENTA);
    public static final ColorHelper LIGHT_BLUE = new ColorHelper(DyeColor.LIGHT_BLUE);
    public static final ColorHelper YELLOW = new ColorHelper(DyeColor.YELLOW);
    public static final ColorHelper LIME = new ColorHelper(DyeColor.LIME);
    public static final ColorHelper PINK = new ColorHelper(DyeColor.PINK);
    public static final ColorHelper GRAY = new ColorHelper(DyeColor.GRAY);
    public static final ColorHelper LIGHT_GRAY = new ColorHelper(DyeColor.LIGHT_GRAY);
    public static final ColorHelper CYAN = new ColorHelper(DyeColor.CYAN);
    public static final ColorHelper PURPLE = new ColorHelper(DyeColor.PURPLE);
    public static final ColorHelper BLUE = new ColorHelper(DyeColor.BLUE);
    public static final ColorHelper BROWN = new ColorHelper(DyeColor.BROWN);
    public static final ColorHelper GREEN = new ColorHelper(DyeColor.GREEN);
    public static final ColorHelper RED = new ColorHelper(DyeColor.RED);
    public static final ColorHelper BLACK = new ColorHelper(DyeColor.BLACK);



    public static ColorHelper parse(DyeColor color) {
        return new ColorHelper(color);
    }

    public DyeColor toColor() {
        return color;
    }

    public Item toItem() {
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

    public Block toWool() {
        return switch (color) {
            case WHITE -> Blocks.WHITE_WOOL;
            case ORANGE -> Blocks.ORANGE_WOOL;
            case MAGENTA -> Blocks.MAGENTA_WOOL;
            case LIGHT_BLUE -> Blocks.LIGHT_BLUE_WOOL;
            case YELLOW -> Blocks.YELLOW_WOOL;
            case LIME -> Blocks.LIME_WOOL;
            case PINK -> Blocks.PINK_WOOL;
            case GRAY -> Blocks.GRAY_WOOL;
            case LIGHT_GRAY -> Blocks.LIGHT_GRAY_WOOL;
            case CYAN -> Blocks.CYAN_WOOL;
            case PURPLE -> Blocks.PURPLE_WOOL;
            case BLUE -> Blocks.BLUE_WOOL;
            case BROWN -> Blocks.BROWN_WOOL;
            case GREEN -> Blocks.GREEN_WOOL;
            case RED -> Blocks.RED_WOOL;
            case BLACK -> Blocks.BLACK_WOOL;
        };
    }

    public static Block toWool(DyeColor color) {
        return switch (color) {
            case WHITE -> Blocks.WHITE_WOOL;
            case ORANGE -> Blocks.ORANGE_WOOL;
            case MAGENTA -> Blocks.MAGENTA_WOOL;
            case LIGHT_BLUE -> Blocks.LIGHT_BLUE_WOOL;
            case YELLOW -> Blocks.YELLOW_WOOL;
            case LIME -> Blocks.LIME_WOOL;
            case PINK -> Blocks.PINK_WOOL;
            case GRAY -> Blocks.GRAY_WOOL;
            case LIGHT_GRAY -> Blocks.LIGHT_GRAY_WOOL;
            case CYAN -> Blocks.CYAN_WOOL;
            case PURPLE -> Blocks.PURPLE_WOOL;
            case BLUE -> Blocks.BLUE_WOOL;
            case BROWN -> Blocks.BROWN_WOOL;
            case GREEN -> Blocks.GREEN_WOOL;
            case RED -> Blocks.RED_WOOL;
            case BLACK -> Blocks.BLACK_WOOL;
        };
    }

    public Block toConcrete() {
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

    public Block toConcretePowder() {
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

    public Block toTerracotta() {
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

    public Block toGlazedTerracotta() {
        return switch (color) {
            case WHITE -> Blocks.WHITE_GLAZED_TERRACOTTA;
            case ORANGE -> Blocks.ORANGE_GLAZED_TERRACOTTA;
            case MAGENTA -> Blocks.MAGENTA_GLAZED_TERRACOTTA;
            case LIGHT_BLUE -> Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA;
            case YELLOW -> Blocks.YELLOW_GLAZED_TERRACOTTA;
            case LIME -> Blocks.LIME_GLAZED_TERRACOTTA;
            case PINK -> Blocks.PINK_GLAZED_TERRACOTTA;
            case GRAY -> Blocks.GRAY_GLAZED_TERRACOTTA;
            case LIGHT_GRAY -> Blocks.LIGHT_GRAY_GLAZED_TERRACOTTA;
            case CYAN -> Blocks.CYAN_GLAZED_TERRACOTTA;
            case PURPLE -> Blocks.PURPLE_GLAZED_TERRACOTTA;
            case BLUE -> Blocks.BLUE_GLAZED_TERRACOTTA;
            case BROWN -> Blocks.BROWN_GLAZED_TERRACOTTA;
            case GREEN -> Blocks.GREEN_GLAZED_TERRACOTTA;
            case RED -> Blocks.RED_GLAZED_TERRACOTTA;
            case BLACK -> Blocks.BLACK_GLAZED_TERRACOTTA ;
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
