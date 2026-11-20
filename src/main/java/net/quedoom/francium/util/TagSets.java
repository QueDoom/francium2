package net.quedoom.francium.util;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.quedoom.quet.init.ModRegistrator;

import java.util.*;

public record TagSets(List<TagSet> sets, ModRegistrator registrator) {
    private static final Map<String, List<TagSet>> SETS = new HashMap<>();

    public static void initialize(String namespace) {
        SETS.put(namespace, new ArrayList<>());
    }

    public static void addTag(String namespace, TagSet set) {
        SETS.get(namespace).add(set);
    }

    public static void addTags(TagSets sets) {
        SETS.get(sets.registrator.namespace()).addAll(sets.sets());
    }

    public static void addTags(String namespace, TagSet... sets) {
        SETS.get(namespace).addAll(Arrays.asList(sets));
    }

    public static List<TagSet> getSets(String namespace) {
        return SETS.get(namespace);
    }

    public static Optional<TagSet> getSet(String namespace, TagKey<Block> blockTag) {
        return SETS.get(namespace).stream().filter(tagSet -> tagSet.block().location().equals(blockTag.location())).findFirst();
    }
}
