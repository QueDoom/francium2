package net.quedoom.francium.datagen.model;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.BlockModelDefinitionGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.quedoom.francium.Francium;
import net.quedoom.francium.init.ModProperties;

import java.util.Optional;

public class CustomBlockModelGenerators {
    public static final ModelTemplate EIGHTHS_EATABLE_OCTANT = block("eighths_eatable_octant", TextureSlot.SIDE, TextureSlot.TOP, TextureSlot.INSIDE);


    // helper method for creating Models
    private static ModelTemplate block(String parent, TextureSlot... requiredTextureKeys) {
        return new ModelTemplate(Optional.of(Francium.id("block/" + parent)), Optional.empty(), requiredTextureKeys);
    }

    // helper method for creating Models with variants
    private static ModelTemplate block(String parent, String variant, TextureSlot... requiredTextureKeys) {
        return new ModelTemplate(Optional.of(Francium.id("block/" + parent)), Optional.of(variant), requiredTextureKeys);
    }


//    private static BlockModelDefinitionGenerator createEighthsEatableOctantBlockStates(Block block, Identifier id) {
//        MultiVariant model = BlockModelGenerators.plainVariant(id);
//
//        return MultiVariantGenerator.dispatch(block, model)
//                .with(PropertyDispatch.initial(ModProperties.NORTH_EAST_UP, ModProperties.NORTH_WEST_UP, ModProperties.SOUTH_EAST_UP, ModProperties.SOUTH_WEST_DOWN))
//    }
}
