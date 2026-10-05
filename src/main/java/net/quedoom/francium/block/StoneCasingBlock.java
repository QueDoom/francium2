package net.quedoom.francium.block;

import net.minecraft.world.level.block.Block;
import net.quedoom.francium.init.ModBlocks;
import org.jspecify.annotations.NonNull;

public class StoneCasingBlock extends CasingWithPotentialContainer {
    public StoneCasingBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NonNull Block containingBlock() {
        return ModBlocks.STONE_CASING_CONTAINING_BLOCK;
    }

    @Override
    protected @NonNull Block containingItems() {
        return ModBlocks.STONE_CASING_CONTAINING_ITEMS;
    }
}
