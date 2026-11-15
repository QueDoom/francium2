package net.quedoom.francium.util;

import com.mojang.math.Quadrant;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.BlockModelDefinitionGenerator;
import net.minecraft.client.data.models.blockstates.ConditionBuilder;
import net.minecraft.client.data.models.blockstates.MultiPartGenerator;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.renderer.block.dispatch.VariantMutator;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.quedoom.francium.init.ModModels;
import net.quedoom.francium.init.ModProperties;
import net.quedoom.quet.datagen.models.QTBlockModelGenerators;

public record ThiccModelGenerators(BlockModelGenerators vanilla, QTBlockModelGenerators queT) {

    public static ConditionBuilder condition() {
        return new ConditionBuilder();
    }


    public void thickCrop(final Block block, boolean registerItem) {
        TextureMapping topMapping = ModModels.TextureMappings.topOctant(block);
        TextureMapping bottomMapping = ModModels.TextureMappings.bottomOctant(block);
        MultiVariant neu = BlockModelGenerators.plainVariant(ModModels.THICK_CROP_NEU.create(block, topMapping, vanilla.modelOutput));
        MultiVariant nwu = BlockModelGenerators.plainVariant(ModModels.THICK_CROP_NWU.create(block, topMapping, vanilla.modelOutput));
        MultiVariant seu = BlockModelGenerators.plainVariant(ModModels.THICK_CROP_SEU.create(block, topMapping, vanilla.modelOutput));
        MultiVariant swu = BlockModelGenerators.plainVariant(ModModels.THICK_CROP_SWU.create(block, topMapping, vanilla.modelOutput));
        MultiVariant ned = BlockModelGenerators.plainVariant(ModModels.THICK_CROP_NED.create(block, bottomMapping, vanilla.modelOutput));
        MultiVariant nwd = BlockModelGenerators.plainVariant(ModModels.THICK_CROP_NWD.create(block, bottomMapping, vanilla.modelOutput));
        MultiVariant sed = BlockModelGenerators.plainVariant(ModModels.THICK_CROP_SED.create(block, bottomMapping, vanilla.modelOutput));
        MultiVariant swd = BlockModelGenerators.plainVariant(ModModels.THICK_CROP_SWD.create(block, bottomMapping, vanilla.modelOutput));
        vanilla.blockStateOutput.accept(createThickCropBlockstate(block,
                neu, nwu, seu, swu, ned, nwd, sed, swd
        ));
        if (registerItem) {
            Identifier inventory = ModModels.THICK_CROP.create(block.asItem(), ModModels.TextureMappings.thickCrop(block), vanilla.modelOutput);
            vanilla.registerSimpleItemModel(block, inventory);
        }
    }
    public void thickCropWithoutItem(final Block block) {
        thickCrop(block, false);
    }
    public void thickCrop(final Block block) {
        thickCrop(block, true);
    }

    public void rotatableThickCropWithFace(final Block face, final Block baseBlock, boolean registerItem) {
        TextureMapping topMapping = ModModels.TextureMappings.topOctant(baseBlock);
        TextureMapping bottomMapping = ModModels.TextureMappings.bottomOctant(baseBlock);
        TextureMapping carvedMapping = ModModels.TextureMappings.thickCropWithFaceOctant(face, baseBlock);
        MultiVariant neu = BlockModelGenerators.plainVariant(ModModels.THICK_CROP_WITH_FACE_NEU.create(face, carvedMapping, vanilla.modelOutput));
        MultiVariant nwu = BlockModelGenerators.plainVariant(ModModels.THICK_CROP_WITH_FACE_NWU.create(face, carvedMapping, vanilla.modelOutput));
        MultiVariant ned = BlockModelGenerators.plainVariant(ModModels.THICK_CROP_WITH_FACE_NED.create(face, carvedMapping, vanilla.modelOutput));
        MultiVariant nwd = BlockModelGenerators.plainVariant(ModModels.THICK_CROP_WITH_FACE_NWD.create(face, carvedMapping, vanilla.modelOutput));

        MultiVariant seu = BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(baseBlock, "_seu"));
        MultiVariant swu = BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(baseBlock, "_swu"));
        MultiVariant sed = BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(baseBlock, "_sed"));
        MultiVariant swd = BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(baseBlock, "_swd"));
        vanilla.blockStateOutput.accept(createRotatableThickCropBlockstate(face,
                neu, nwu, ned, nwd, seu, swu, sed, swd
        ));
        if (registerItem) {
            Identifier inventory = ModModels.THICK_CROP_WITH_FACE.create(face.asItem(), ModModels.TextureMappings.thickCropWithFace(face, baseBlock), vanilla.modelOutput);
            vanilla.registerSimpleItemModel(face, inventory);
        }
    }
    public void rotatableThickCropWithFaceWithoutItem(final Block block, final Block pumpkin) {
        rotatableThickCropWithFace(block, pumpkin, false);
    }
    public void rotatableThickCropWithFace(final Block block, final Block pumpkin) {
        rotatableThickCropWithFace(block, pumpkin, true);
    }

    public static BlockModelDefinitionGenerator createThickCropBlockstate(
            Block block,
            MultiVariant neu, MultiVariant nwu, MultiVariant seu, MultiVariant swu,
            MultiVariant ned, MultiVariant nwd, MultiVariant sed, MultiVariant swd) {
        return MultiPartGenerator.multiPart(block)
                .with(condition().term(ModProperties.NORTH_EAST_UP, true), neu)
                .with(condition().term(ModProperties.NORTH_WEST_UP, true), nwu)
                .with(condition().term(ModProperties.SOUTH_EAST_UP, true), seu)
                .with(condition().term(ModProperties.SOUTH_WEST_UP, true), swu)
                .with(condition().term(ModProperties.NORTH_EAST_DOWN, true), ned)
                .with(condition().term(ModProperties.NORTH_WEST_DOWN, true), nwd)
                .with(condition().term(ModProperties.SOUTH_EAST_DOWN, true), sed)
                .with(condition().term(ModProperties.SOUTH_WEST_DOWN, true), swd);
    }

    private static final BooleanProperty[] OCTANT_PROPS = {
            ModProperties.NORTH_EAST_UP,   ModProperties.NORTH_WEST_UP,
            ModProperties.SOUTH_EAST_UP,   ModProperties.SOUTH_WEST_UP,
            ModProperties.NORTH_EAST_DOWN, ModProperties.NORTH_WEST_DOWN,
            ModProperties.SOUTH_EAST_DOWN, ModProperties.SOUTH_WEST_DOWN
    };

    public static BlockModelDefinitionGenerator createRotatableThickCropBlockstate(
            Block block,
            MultiVariant neu, MultiVariant nwu, MultiVariant ned, MultiVariant nwd,
            MultiVariant seu, MultiVariant swu, MultiVariant sed, MultiVariant swd) {

        MultiVariant[] models = {neu, nwu, seu, swu, ned, nwd, sed, swd};
        MultiPartGenerator generator = MultiPartGenerator.multiPart(block);

        for (Direction facing : new Direction[]{Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST}) {
            VariantMutator rotation = switch (facing) {
                case EAST  -> VariantMutator.Y_ROT.withValue(Quadrant.R90);
                case SOUTH -> VariantMutator.Y_ROT.withValue(Quadrant.R180);
                case WEST  -> VariantMutator.Y_ROT.withValue(Quadrant.R270);
                default    -> null;
            };
            for (int i = 0; i < 8; i++) {
                MultiVariant model = rotation == null ? models[i] : models[i].with(rotation);
                generator.with(
                        condition().term(BlockStateProperties.HORIZONTAL_FACING, facing)
                                .term(OCTANT_PROPS[i], true),
                        model);
            }
        }
        return generator;
    }

    public void thickApple(final Block block) {
        TextureMapping topMapping = ModModels.TextureMappings.topAppleLikeOctant(block);
        TextureMapping bottomMapping = ModModels.TextureMappings.bottomAppleLikeOctant(block);
        TextureMapping stemMapping = ModModels.TextureMappings.thickAppleStem(block);
        MultiVariant neu = BlockModelGenerators.plainVariant(ModModels.THICK_CROP_WITH_MULTIBLE_INNER_FACE_TEXTURES_NEU.create(block, topMapping, vanilla.modelOutput));
        MultiVariant nwu = BlockModelGenerators.plainVariant(ModModels.THICK_CROP_WITH_MULTIBLE_INNER_FACE_TEXTURES_NWU.create(block, topMapping, vanilla.modelOutput));
        MultiVariant seu = BlockModelGenerators.plainVariant(ModModels.THICK_CROP_WITH_MULTIBLE_INNER_FACE_TEXTURES_SEU.create(block, topMapping, vanilla.modelOutput));
        MultiVariant swu = BlockModelGenerators.plainVariant(ModModels.THICK_CROP_WITH_MULTIBLE_INNER_FACE_TEXTURES_SWU.create(block, topMapping, vanilla.modelOutput));
        MultiVariant ned = BlockModelGenerators.plainVariant(ModModels.THICK_CROP_WITH_MULTIBLE_INNER_FACE_TEXTURES_NED.create(block, bottomMapping, vanilla.modelOutput));
        MultiVariant nwd = BlockModelGenerators.plainVariant(ModModels.THICK_CROP_WITH_MULTIBLE_INNER_FACE_TEXTURES_NWD.create(block, bottomMapping, vanilla.modelOutput));
        MultiVariant sed = BlockModelGenerators.plainVariant(ModModels.THICK_CROP_WITH_MULTIBLE_INNER_FACE_TEXTURES_SED.create(block, bottomMapping, vanilla.modelOutput));
        MultiVariant swd = BlockModelGenerators.plainVariant(ModModels.THICK_CROP_WITH_MULTIBLE_INNER_FACE_TEXTURES_SWD.create(block, bottomMapping, vanilla.modelOutput));
        MultiVariant ne = BlockModelGenerators.plainVariant(ModModels.THICK_STEM_NE.create(block, stemMapping, vanilla.modelOutput));
        MultiVariant nw = BlockModelGenerators.plainVariant(ModModels.THICK_STEM_NW.create(block, stemMapping, vanilla.modelOutput));
        MultiVariant se = BlockModelGenerators.plainVariant(ModModels.THICK_STEM_SE.create(block, stemMapping, vanilla.modelOutput));
        MultiVariant sw = BlockModelGenerators.plainVariant(ModModels.THICK_STEM_SW.create(block, stemMapping, vanilla.modelOutput));
        Identifier inventory = ModModels.THICK_APPLE.create(block.asItem(), ModModels.TextureMappings.thickApple(block), vanilla.modelOutput);
        vanilla.blockStateOutput.accept(createThickAppleBlockstate(block,
                neu, nwu, seu, swu, ned, nwd, sed, swd, ne, nw, se, sw
        ));
        vanilla.registerSimpleItemModel(block, inventory);
    }

    public static BlockModelDefinitionGenerator createThickAppleBlockstate(
            Block block,
            MultiVariant neu, MultiVariant nwu, MultiVariant seu, MultiVariant swu,
            MultiVariant ned, MultiVariant nwd, MultiVariant sed, MultiVariant swd,
            MultiVariant ne, MultiVariant nw, MultiVariant se, MultiVariant sw
            ) {
        return MultiPartGenerator.multiPart(block)
                .with(condition().term(ModProperties.NORTH_EAST_UP, true), neu)
                .with(condition().term(ModProperties.NORTH_WEST_UP, true), nwu)
                .with(condition().term(ModProperties.SOUTH_EAST_UP, true), seu)
                .with(condition().term(ModProperties.SOUTH_WEST_UP, true), swu)
                .with(condition().term(ModProperties.NORTH_EAST_DOWN, true), ned)
                .with(condition().term(ModProperties.NORTH_WEST_DOWN, true), nwd)
                .with(condition().term(ModProperties.SOUTH_EAST_DOWN, true), sed)
                .with(condition().term(ModProperties.SOUTH_WEST_DOWN, true), swd)
                .with(condition().term(ModProperties.NORTH_EAST_UP, true), ne)
                .with(condition().term(ModProperties.NORTH_WEST_UP, true), nw)
                .with(condition().term(ModProperties.SOUTH_EAST_UP, true), se)
                .with(condition().term(ModProperties.SOUTH_WEST_UP, true), sw)
                ;
    }

    public void thickCropWithMultibleFaceTextures(final Block block) {
        TextureMapping topMapping = ModModels.TextureMappings.topAppleLikeOctant(block);
        TextureMapping bottomMapping = ModModels.TextureMappings.bottomAppleLikeOctant(block);
        MultiVariant neu = BlockModelGenerators.plainVariant(ModModels.THICK_CROP_WITH_MULTIBLE_INNER_FACE_TEXTURES_NEU.create(block, topMapping, vanilla.modelOutput));
        MultiVariant nwu = BlockModelGenerators.plainVariant(ModModels.THICK_CROP_WITH_MULTIBLE_INNER_FACE_TEXTURES_NWU.create(block, topMapping, vanilla.modelOutput));
        MultiVariant seu = BlockModelGenerators.plainVariant(ModModels.THICK_CROP_WITH_MULTIBLE_INNER_FACE_TEXTURES_SEU.create(block, topMapping, vanilla.modelOutput));
        MultiVariant swu = BlockModelGenerators.plainVariant(ModModels.THICK_CROP_WITH_MULTIBLE_INNER_FACE_TEXTURES_SWU.create(block, topMapping, vanilla.modelOutput));
        MultiVariant ned = BlockModelGenerators.plainVariant(ModModels.THICK_CROP_WITH_MULTIBLE_INNER_FACE_TEXTURES_NED.create(block, bottomMapping, vanilla.modelOutput));
        MultiVariant nwd = BlockModelGenerators.plainVariant(ModModels.THICK_CROP_WITH_MULTIBLE_INNER_FACE_TEXTURES_NWD.create(block, bottomMapping, vanilla.modelOutput));
        MultiVariant sed = BlockModelGenerators.plainVariant(ModModels.THICK_CROP_WITH_MULTIBLE_INNER_FACE_TEXTURES_SED.create(block, bottomMapping, vanilla.modelOutput));
        MultiVariant swd = BlockModelGenerators.plainVariant(ModModels.THICK_CROP_WITH_MULTIBLE_INNER_FACE_TEXTURES_SWD.create(block, bottomMapping, vanilla.modelOutput));
        Identifier inventory = ModModels.THICK_CROP.create(block.asItem(), ModModels.TextureMappings.thickApple(block), vanilla.modelOutput);
        vanilla.blockStateOutput.accept(createThickCropBlockstate(block,
                neu, nwu, seu, swu, ned, nwd, sed, swd
        ));
        vanilla.registerSimpleItemModel(block, inventory);
    }

    public void thickCropFoliage(final Block block) {
        TextureMapping foliageMapping = ModModels.TextureMappings.octantFoliage(block);
        MultiVariant ne = BlockModelGenerators.plainVariant(ModModels.THICK_FOLIAGE_NE.create(block, foliageMapping, vanilla.modelOutput));
        MultiVariant nw = BlockModelGenerators.plainVariant(ModModels.THICK_FOLIAGE_NW.create(block, foliageMapping, vanilla.modelOutput));
        MultiVariant se = BlockModelGenerators.plainVariant(ModModels.THICK_FOLIAGE_SE.create(block, foliageMapping, vanilla.modelOutput));
        MultiVariant sw = BlockModelGenerators.plainVariant(ModModels.THICK_FOLIAGE_SW.create(block, foliageMapping, vanilla.modelOutput));
        vanilla.blockStateOutput.accept(createThickCropFoliageBlockstate(block,
                ne, nw, se, sw
        ));
    }

    public static BlockModelDefinitionGenerator createThickCropFoliageBlockstate(
            Block block,
            MultiVariant ne, MultiVariant nw, MultiVariant se, MultiVariant sw) {
        return MultiPartGenerator.multiPart(block)
                .with(condition().term(ModProperties.THICK_FOLIAGE_NE, true), ne)
                .with(condition().term(ModProperties.THICK_FOLIAGE_NW, true), nw)
                .with(condition().term(ModProperties.THICK_FOLIAGE_SE, true), se)
                .with(condition().term(ModProperties.THICK_FOLIAGE_SW, true), sw);
    }
}
