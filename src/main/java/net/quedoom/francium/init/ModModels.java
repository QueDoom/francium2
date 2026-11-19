package net.quedoom.francium.init;

import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.world.level.block.Block;
import net.quedoom.francium.Francium;
import net.quedoom.francium.util.sewer.SewerArrowDirection;

import java.util.Optional;

public class ModModels {

    public static final ModelTemplate THICK_CROP_NED = block("thick_crop/ned", "_ned", TextureSlots.INNER, TextureSlots.SIDE, TextureSlots.TOP);
    public static final ModelTemplate THICK_CROP_NEU = block("thick_crop/neu", "_neu", TextureSlots.INNER, TextureSlots.SIDE, TextureSlots.TOP);
    public static final ModelTemplate THICK_CROP_NWD = block("thick_crop/nwd", "_nwd", TextureSlots.INNER, TextureSlots.SIDE, TextureSlots.TOP);
    public static final ModelTemplate THICK_CROP_NWU = block("thick_crop/nwu", "_nwu", TextureSlots.INNER, TextureSlots.SIDE, TextureSlots.TOP);
    public static final ModelTemplate THICK_CROP_SED = block("thick_crop/sed", "_sed", TextureSlots.INNER, TextureSlots.SIDE, TextureSlots.TOP);
    public static final ModelTemplate THICK_CROP_SEU = block("thick_crop/seu", "_seu", TextureSlots.INNER, TextureSlots.SIDE, TextureSlots.TOP);
    public static final ModelTemplate THICK_CROP_SWD = block("thick_crop/swd", "_swd", TextureSlots.INNER, TextureSlots.SIDE, TextureSlots.TOP);
    public static final ModelTemplate THICK_CROP_SWU = block("thick_crop/swu", "_swu", TextureSlots.INNER, TextureSlots.SIDE, TextureSlots.TOP);
    public static final ModelTemplate THICK_CROP = item("thick_crop", TextureSlot.SIDE, TextureSlot.TOP, TextureSlot.BOTTOM);

    public static final ModelTemplate THICK_CROP_WITH_MULTIBLE_INNER_FACE_TEXTURES_NED = block("thick_crop_with_multible_inner_face_textures/ned", "_ned", TextureSlots.INNER_SIDE, TextureSlots.INNER_TOP, TextureSlots.SIDE, TextureSlots.TOP);
    public static final ModelTemplate THICK_CROP_WITH_MULTIBLE_INNER_FACE_TEXTURES_NEU = block("thick_crop_with_multible_inner_face_textures/neu", "_neu", TextureSlots.INNER_SIDE, TextureSlots.INNER_TOP, TextureSlots.SIDE, TextureSlots.TOP);
    public static final ModelTemplate THICK_CROP_WITH_MULTIBLE_INNER_FACE_TEXTURES_NWD = block("thick_crop_with_multible_inner_face_textures/nwd", "_nwd", TextureSlots.INNER_SIDE, TextureSlots.INNER_TOP, TextureSlots.SIDE, TextureSlots.TOP);
    public static final ModelTemplate THICK_CROP_WITH_MULTIBLE_INNER_FACE_TEXTURES_NWU = block("thick_crop_with_multible_inner_face_textures/nwu", "_nwu", TextureSlots.INNER_SIDE, TextureSlots.INNER_TOP, TextureSlots.SIDE, TextureSlots.TOP);
    public static final ModelTemplate THICK_CROP_WITH_MULTIBLE_INNER_FACE_TEXTURES_SED = block("thick_crop_with_multible_inner_face_textures/sed", "_sed", TextureSlots.INNER_SIDE, TextureSlots.INNER_TOP, TextureSlots.SIDE, TextureSlots.TOP);
    public static final ModelTemplate THICK_CROP_WITH_MULTIBLE_INNER_FACE_TEXTURES_SEU = block("thick_crop_with_multible_inner_face_textures/seu", "_seu", TextureSlots.INNER_SIDE, TextureSlots.INNER_TOP, TextureSlots.SIDE, TextureSlots.TOP);
    public static final ModelTemplate THICK_CROP_WITH_MULTIBLE_INNER_FACE_TEXTURES_SWD = block("thick_crop_with_multible_inner_face_textures/swd", "_swd", TextureSlots.INNER_SIDE, TextureSlots.INNER_TOP, TextureSlots.SIDE, TextureSlots.TOP);
    public static final ModelTemplate THICK_CROP_WITH_MULTIBLE_INNER_FACE_TEXTURES_SWU = block("thick_crop_with_multible_inner_face_textures/swu", "_swu", TextureSlots.INNER_SIDE, TextureSlots.INNER_TOP, TextureSlots.SIDE, TextureSlots.TOP);
    public static final ModelTemplate THICK_APPLE = item("thick_apple_template", TextureSlot.SIDE, TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlots.STEM);

    public static final ModelTemplate THICK_CROP_WITH_FACE_NED = block("thick_crop_with_face/ned", "_ned", TextureSlots.INNER, TextureSlots.SIDE, TextureSlots.TOP, TextureSlots.FACE);
    public static final ModelTemplate THICK_CROP_WITH_FACE_NEU = block("thick_crop_with_face/neu", "_neu", TextureSlots.INNER, TextureSlots.SIDE, TextureSlots.TOP, TextureSlots.FACE);
    public static final ModelTemplate THICK_CROP_WITH_FACE_NWD = block("thick_crop_with_face/nwd", "_nwd", TextureSlots.INNER, TextureSlots.SIDE, TextureSlots.TOP, TextureSlots.FACE);
    public static final ModelTemplate THICK_CROP_WITH_FACE_NWU = block("thick_crop_with_face/nwu", "_nwu", TextureSlots.INNER, TextureSlots.SIDE, TextureSlots.TOP, TextureSlots.FACE);
    public static final ModelTemplate THICK_CROP_WITH_FACE = item("thick_crop_with_face", TextureSlot.SIDE, TextureSlot.TOP, TextureSlots.FACE);

    public static final ModelTemplate THICK_FOLIAGE_NE = block("thick_foliage/ne", "_ne", TextureSlots.FOLIAGE);
    public static final ModelTemplate THICK_FOLIAGE_NW = block("thick_foliage/nw", "_nw", TextureSlots.FOLIAGE);
    public static final ModelTemplate THICK_FOLIAGE_SE = block("thick_foliage/se", "_se", TextureSlots.FOLIAGE);
    public static final ModelTemplate THICK_FOLIAGE_SW = block("thick_foliage/sw", "_sw", TextureSlots.FOLIAGE);

    public static final ModelTemplate THICK_STEM_NE = block("thick_apple/ne", "_ne", TextureSlots.STEM);
    public static final ModelTemplate THICK_STEM_NW = block("thick_apple/nw", "_nw", TextureSlots.STEM);
    public static final ModelTemplate THICK_STEM_SE = block("thick_apple/se", "_se", TextureSlots.STEM);
    public static final ModelTemplate THICK_STEM_SW = block("thick_apple/sw", "_sw", TextureSlots.STEM);

    public static final ModelTemplate SEWER_ARROW_FORWARD = sewer("arrow_forward","_arrow_forward",  TextureSlots.SEWER_ARROW, TextureSlots.SEWER_ARROW_SMALL);
    public static final ModelTemplate SEWER_ARROW_LEFT = sewer("arrow_left","_arrow_left",  TextureSlots.SEWER_ARROW_LEFT, TextureSlots.SEWER_ARROW_SMALL);
    public static final ModelTemplate SEWER_ARROW_RIGHT = sewer("arrow_right","_arrow_right",  TextureSlots.SEWER_ARROW_RIGHT, TextureSlots.SEWER_ARROW_SMALL);

    public static final ModelTemplate SEWER_CENTER = sewer("center","_ce",  TextureSlots.SEWER);
    public static final ModelTemplate SEWER_CORNERS = sewer("corners","_co",  TextureSlots.SEWER);
    public static final ModelTemplate SEWER_CENTER_EAST = sewer("e_center","_ec",  TextureSlots.SEWER);
    public static final ModelTemplate SEWER_CENTER_WEST = sewer("w_center","_wc",  TextureSlots.SEWER);
    public static final ModelTemplate SEWER_CENTER_NORTH = sewer("n_center","_nc",  TextureSlots.SEWER);
    public static final ModelTemplate SEWER_CENTER_SOUTH = sewer("s_center","_sc",  TextureSlots.SEWER);
    public static final ModelTemplate SEWER_UD_NORTH = sewer("n_ud","_nud",  TextureSlots.SEWER);
    public static final ModelTemplate SEWER_UD_SOUTH = sewer("s_ud","_sud",  TextureSlots.SEWER);
    public static final ModelTemplate SEWER_UD_EAST = sewer("e_ud","_eud",  TextureSlots.SEWER);
    public static final ModelTemplate SEWER_UD_WEST = sewer("w_ud","_wud", TextureSlots.SEWER);
    public static final ModelTemplate SEWER_PILLAR_NE = sewer("ne_pillar","_nep",  TextureSlots.SEWER);
    public static final ModelTemplate SEWER_PILLAR_NW = sewer("nw_pillar","_nwp",  TextureSlots.SEWER);
    public static final ModelTemplate SEWER_PILLAR_SE = sewer("se_pillar","_sep",  TextureSlots.SEWER);
    public static final ModelTemplate SEWER_PILLAR_SW = sewer("sw_pillar","_swp", TextureSlots.SEWER);

    public static final ModelTemplate SEWER_ITEM = item("sewer_item", TextureSlots.SEWER, TextureSlots.SEWER_ARROW);

    private static ModelTemplate sewer(String parent, TextureSlot... requiredTextureKeys) {
        return new ModelTemplate(Optional.of(Francium.of("block/sewer/" + parent)), Optional.empty(), requiredTextureKeys);
    }
    private static ModelTemplate sewer(String parent, String variant, TextureSlot... requiredTextureKeys) {
        return new ModelTemplate(Optional.of(Francium.of("block/sewer/" + parent)), Optional.of(variant), requiredTextureKeys);
    }

    // helper method for creating Models
    private static ModelTemplate block(String parent, TextureSlot... requiredTextureKeys) {
        return new ModelTemplate(Optional.of(Francium.of("block/" + parent)), Optional.empty(), requiredTextureKeys);
    }

    // helper method for creating Models with variants
    private static ModelTemplate block(String parent, String variant, TextureSlot... requiredTextureKeys) {
        return new ModelTemplate(Optional.of(Francium.of("block/" + parent)), Optional.of(variant), requiredTextureKeys);
    }
    // helper method for creating Models
    private static ModelTemplate item(String parent, TextureSlot... requiredTextureKeys) {
        return new ModelTemplate(Optional.of(Francium.of("item/" + parent)), Optional.empty(), requiredTextureKeys);
    }

    // helper method for creating Models with variants
    private static ModelTemplate item(String parent, String variant, TextureSlot... requiredTextureKeys) {
        return new ModelTemplate(Optional.of(Francium.of("item/" + parent)), Optional.of(variant), requiredTextureKeys);
    }

    public static class TextureSlots {
        public static final TextureSlot INNER = TextureSlot.create("inner");
        public static final TextureSlot INNER_SIDE = TextureSlot.create("inner_side", INNER);
        public static final TextureSlot INNER_TOP = TextureSlot.create("inner_top", INNER);
        public static final TextureSlot SIDE = TextureSlot.create("side");
        public static final TextureSlot TOP = TextureSlot.create("top");
        public static final TextureSlot FOLIAGE = TextureSlot.create("foliage");
        public static final TextureSlot STEM = TextureSlot.create("stem");
        public static final TextureSlot FACE = TextureSlot.create("face");

        public static final TextureSlot SEWER = TextureSlot.create("sewer", TextureSlot.ALL);
        public static final TextureSlot SEWER_ARROW = TextureSlot.create("arrow");
        public static final TextureSlot SEWER_ARROW_LEFT = TextureSlot.create("left_arrow");
        public static final TextureSlot SEWER_ARROW_RIGHT = TextureSlot.create("arrow_right");
        public static final TextureSlot SEWER_ARROW_SMALL = TextureSlot.create("small_arrow");
    }

    public static class TextureMappings {
        public static TextureMapping topOctant(Block block) {
            return new TextureMapping()
                    .put(TextureSlots.INNER, new Material(ModelLocationUtils.getModelLocation(block, "_inner")))
                    .put(TextureSlots.SIDE, new Material(ModelLocationUtils.getModelLocation(block, "_side")))
                    .put(TextureSlots.TOP, new Material(ModelLocationUtils.getModelLocation(block, "_top")))
                    ;
        }
        public static TextureMapping bottomOctant(Block block) {
            return new TextureMapping()
                    .put(TextureSlots.INNER, new Material(ModelLocationUtils.getModelLocation(block, "_inner")))
                    .put(TextureSlots.SIDE, new Material(ModelLocationUtils.getModelLocation(block, "_side")))
                    .put(TextureSlots.TOP, new Material(ModelLocationUtils.getModelLocation(block, "_bottom")))
                    ;
        }
        public static TextureMapping topAppleLikeOctant(Block block) {
            return new TextureMapping()
                    .put(TextureSlots.INNER_SIDE, new Material(ModelLocationUtils.getModelLocation(block, "_inner_side")))
                    .put(TextureSlots.INNER_TOP, new Material(ModelLocationUtils.getModelLocation(block, "_inner_top")))
                    .put(TextureSlots.SIDE, new Material(ModelLocationUtils.getModelLocation(block, "_side")))
                    .put(TextureSlots.TOP, new Material(ModelLocationUtils.getModelLocation(block, "_top")))
                    ;
        }
        public static TextureMapping bottomAppleLikeOctant(Block block) {
            return new TextureMapping()
                    .put(TextureSlots.INNER_SIDE, new Material(ModelLocationUtils.getModelLocation(block, "_inner_side")))
                    .put(TextureSlots.INNER_TOP, new Material(ModelLocationUtils.getModelLocation(block, "_inner_top")))
                    .put(TextureSlots.SIDE, new Material(ModelLocationUtils.getModelLocation(block, "_side")))
                    .put(TextureSlots.TOP, new Material(ModelLocationUtils.getModelLocation(block, "_bottom")))
                    ;
        }
        public static TextureMapping thickCropWithFaceOctant(Block face, Block baseBlock) {
            return new TextureMapping()
                    .put(TextureSlots.INNER, new Material(ModelLocationUtils.getModelLocation(baseBlock, "_inner")))
                    .put(TextureSlots.SIDE, new Material(ModelLocationUtils.getModelLocation(baseBlock, "_side")))
                    .put(TextureSlots.TOP, new Material(ModelLocationUtils.getModelLocation(baseBlock, "_top")))
                    .put(TextureSlots.FACE, new Material(ModelLocationUtils.getModelLocation(face)))
                    ;
        }
        public static TextureMapping thickAppleStem(Block block) {
            return new TextureMapping()
                    .put(TextureSlots.STEM, new Material(ModelLocationUtils.getModelLocation(block, "_stem")))
                    ;
        }
        public static TextureMapping thickCrop(Block block) {
            return new TextureMapping()
                    .put(TextureSlot.SIDE,   TextureMapping.getBlockTexture(block, "_side"))
                    .put(TextureSlot.TOP,    TextureMapping.getBlockTexture(block, "_top"))
                    .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(block, "_bottom"));
        }
        public static TextureMapping thickCropWithFace(Block face, Block baseBlock) {
            return new TextureMapping()
                    .put(TextureSlot.SIDE,   TextureMapping.getBlockTexture(baseBlock, "_side"))
                    .put(TextureSlot.TOP,    TextureMapping.getBlockTexture(baseBlock, "_top"))
                    .put(TextureSlots.FACE, TextureMapping.getBlockTexture(face));
        }
        public static TextureMapping thickApple(Block block) {
            return new TextureMapping()
                    .put(TextureSlot.SIDE, new Material(ModelLocationUtils.getModelLocation(block, "_side")))
                    .put(TextureSlot.TOP, new Material(ModelLocationUtils.getModelLocation(block, "_top")))
                    .put(TextureSlot.BOTTOM, new Material(ModelLocationUtils.getModelLocation(block, "_bottom")))
                    .put(TextureSlots.STEM, new Material(ModelLocationUtils.getModelLocation(block, "_stem")))
                    ;
        }
        public static TextureMapping octantFoliage(Block block) {
            return new TextureMapping()
                    .put(TextureSlots.FOLIAGE, new Material(ModelLocationUtils.getModelLocation(block)));
        }

        public static TextureMapping sewer(Block block, String suffix) {
            return new TextureMapping()
                    .put(TextureSlots.SEWER, new Material(ModelLocationUtils.getModelLocation(block, "_" + suffix)));
        }
        public static TextureMapping sewerArrow(Block block, SewerArrowDirection arrow) {
            TextureSlot arrowe = switch (arrow) {
                case STRAIGHT -> TextureSlots.SEWER_ARROW;
                case LEFT -> TextureSlots.SEWER_ARROW_LEFT;
                case RIGHT -> TextureSlots.SEWER_ARROW_RIGHT;
            };
            return new TextureMapping()
                    .put(arrowe, new Material(ModelLocationUtils.getModelLocation(block, "_arrow_" + arrow.getSerializedName())));
        }
    }
}
