package net.quedoom.francium.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;

public class CleaverItem extends Item {
    public CleaverItem(ToolMaterial material, float attackDamageBaseline, float attackSpeedBaseline, Properties properties) {
        super(properties.tool(material, BlockTags.MINEABLE_WITH_HOE, attackDamageBaseline, attackSpeedBaseline, 0.0f));
    }
}
