package net.quedoom.francium.util;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.quedoom.francium.init.ModTags;

import java.util.List;

public record TagSet(TagKey<Item> item, TagKey<Block> block) {
    public List<Item> toItemArray() {
        return ModTags.blocksInTag(block).stream()
                .map(Block::asItem)
                .filter(item -> item != Items.AIR)
                .distinct()
                .toList();
    }
}
