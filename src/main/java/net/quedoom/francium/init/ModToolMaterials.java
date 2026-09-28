package net.quedoom.francium.init;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ToolMaterial;
import net.quedoom.quet.init.QueTToolMaterial;

public class ModToolMaterials extends QueTToolMaterial {

    public static final ToolMaterial SHARP_STICK = register(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 18, 0.1F, 0.0F, 4, ItemTags.WOODEN_TOOL_MATERIALS);
    public static final ToolMaterial SHARP_ROCK = register(BlockTags.INCORRECT_FOR_STONE_TOOL, 27, 1.4F, 0.0F, 4, ItemTags.STONE_TOOL_MATERIALS);
    public static final ToolMaterial SHARP_DEEPSLATE_ROCK = register(BlockTags.INCORRECT_FOR_STONE_TOOL, 53, 1.74F, 0.5F, 8, ItemTags.STONE_TOOL_MATERIALS);
    public static final ToolMaterial SHARP_AMETHYST_ROCK = register(BlockTags.INCORRECT_FOR_COPPER_TOOL, 65, 2.2F, 1F, 12, ModTags.Items.AMETHYST_ROCK_MATERIALS);
    public static final ToolMaterial SHARP_OBSIDIAN_ROCK = register(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 127, 3F, 1.25F, 14, ModTags.Items.OBSIDIAN_ROCK_MATERIALS);
    public static final ToolMaterial SHARP_BEDROCK_ROCK = register(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 1023, 4F, 5F, 21, ModTags.Items.BEDROCK_ROCK_MATERIALS);
}
