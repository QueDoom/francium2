package net.quedoom.francium.datagen.model;

import net.minecraft.client.data.models.model.*;
import net.quedoom.francium.Francium;

import java.util.Optional;

public class FranciumBlockModelGenerators {
    public static final ModelTemplate EIGHTHS_EATABLE_OCTANT = block("eighths_eatable_octant", TextureSlot.SIDE, TextureSlot.TOP, TextureSlot.INSIDE);


    // helper method for creating Models
    private static ModelTemplate block(String parent, TextureSlot... requiredTextureKeys) {
        return new ModelTemplate(Optional.of(Francium.of("item/" + parent)), Optional.empty(), requiredTextureKeys);
    }

    // helper method for creating Models with variants
    private static ModelTemplate block(String parent, String variant, TextureSlot... requiredTextureKeys) {
        return new ModelTemplate(Optional.of(Francium.of("item/" + parent)), Optional.of(variant), requiredTextureKeys);
    }


//    private static BlockModelDefinitionGenerator createEighthsEatableOctantBlockStates(Block item, Identifier id) {
//        MultiVariant model = BlockModelGenerators.plainVariant(id);
//
//        return MultiVariantGenerator.dispatch(item, model)
//                .with(PropertyDispatch.initial(ModProperties.NORTH_EAST_UP, ModProperties.NORTH_WEST_UP, ModProperties.SOUTH_EAST_UP, ModProperties.SOUTH_WEST_DOWN))
//    }
}
