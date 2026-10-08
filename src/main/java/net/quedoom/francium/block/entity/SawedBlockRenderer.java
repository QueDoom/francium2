package net.quedoom.francium.block.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.block.dispatch.BlockStateModel;
import net.minecraft.client.renderer.block.dispatch.BlockStateModelPart;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.geometry.BakedQuad;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.quedoom.francium.block.SawedBlock;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SawedBlockRenderer implements BlockEntityRenderer<SawedBlockEntity, SawedBlockRenderer.SawedBlockRenderState> {
    private final BlockModelResolver blockModelResolver;

    private static final int OCTANT_COUNT = 8;
    private static final float OCTANT_SIZE_PX = 8f;
    private static final float FULL_BLOCK_SIZE_PX = 16f;

    public SawedBlockRenderer(BlockEntityRendererProvider.Context context) {
        this.blockModelResolver = context.blockModelResolver();
    }

    @Override
    public SawedBlockRenderState createRenderState() {
        return new SawedBlockRenderState();
    }

    @Override
    public void extractRenderState(SawedBlockEntity sawedBlockEntity, SawedBlockRenderState renderState, float partialTicks,
                                   Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderState.extractBase(sawedBlockEntity, renderState, breakProgress);

        BlockState sawedBlockState = sawedBlockEntity.getBlockState();

        // Octant index = eastBit + 2 * upBit + 4 * southBit
        int enabledOctantMask = 0;
        if (sawedBlockState.getValue(SawedBlock.NORTH_WEST_DOWN)) enabledOctantMask |= 1 << 0;
        if (sawedBlockState.getValue(SawedBlock.NORTH_EAST_DOWN)) enabledOctantMask |= 1 << 1;
        if (sawedBlockState.getValue(SawedBlock.NORTH_WEST_UP))   enabledOctantMask |= 1 << 2;
        if (sawedBlockState.getValue(SawedBlock.NORTH_EAST_UP))   enabledOctantMask |= 1 << 3;
        if (sawedBlockState.getValue(SawedBlock.SOUTH_WEST_DOWN)) enabledOctantMask |= 1 << 4;
        if (sawedBlockState.getValue(SawedBlock.SOUTH_EAST_DOWN)) enabledOctantMask |= 1 << 5;
        if (sawedBlockState.getValue(SawedBlock.SOUTH_WEST_UP))   enabledOctantMask |= 1 << 6;
        if (sawedBlockState.getValue(SawedBlock.SOUTH_EAST_UP))   enabledOctantMask |= 1 << 7;
        renderState.enabledOctantMask = enabledOctantMask;

        Optional<BlockState> containedState = sawedBlockEntity.getStateInside().filter(state -> !state.isAir());
        renderState.hasContainedBlock = containedState.isPresent();
        if (!renderState.hasContainedBlock) return;

        BlockState inner = sawedBlockEntity.getStateInside().get();
        blockModelResolver.update(renderState.blockModelRenderState, inner, BlockDisplayContext.create());

        BlockStateModel containedBlockModel = Minecraft.getInstance()
                .getModelManager()
                .getBlockStateModelSet()
                .get(containedState.get());
        List<BlockStateModelPart> modelParts = new ArrayList<>();
        containedBlockModel.collectParts(RandomSource.create(42L), modelParts);

        TextureAtlasSprite fallbackSprite = null;
        for (Direction face : Direction.values()) {
            int faceIndex = face.get3DDataValue();
            renderState.spriteByFace[faceIndex] = null;
            for (BlockStateModelPart modelPart : modelParts) {
                List<BakedQuad> faceQuads = modelPart.getQuads(face);
                if (!faceQuads.isEmpty()) {
                    renderState.spriteByFace[faceIndex] = faceQuads.get(0).materialInfo().sprite();
                    if (fallbackSprite == null) fallbackSprite = renderState.spriteByFace[faceIndex];
                    break;
                }
            }
        }
        for (Direction face : Direction.values()) {
            int faceIndex = face.get3DDataValue();
            if (renderState.spriteByFace[faceIndex] == null) renderState.spriteByFace[faceIndex] = fallbackSprite;
        }
    }

    @Override
    public void submit(SawedBlockRenderState renderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        if (!renderState.hasContainedBlock) return;

        submitNodeCollector.submitCustomGeometry(poseStack, RenderTypes.entityCutout(TextureAtlas.LOCATION_BLOCKS), (poseEntry, vertexConsumer) -> {
            for (int octantIndex = 0; octantIndex < OCTANT_COUNT; octantIndex++) {
                if ((renderState.enabledOctantMask & (1 << octantIndex)) == 0) continue;

                int octantEastBit = octantIndex & 1;
                int octantUpBit = (octantIndex >> 1) & 1;
                int octantSouthBit = (octantIndex >> 2) & 1;

                float minX = octantEastBit * OCTANT_SIZE_PX;
                float minY = octantUpBit * OCTANT_SIZE_PX;
                float minZ = octantSouthBit * OCTANT_SIZE_PX;
                float maxX = minX + OCTANT_SIZE_PX;
                float maxY = minY + OCTANT_SIZE_PX;
                float maxZ = minZ + OCTANT_SIZE_PX;

                for (Direction face : Direction.values()) {
                    // Skip faces that touch another enabled octant (hidden inside the block)
                    int neighborEastBit = octantEastBit + face.getStepX();
                    int neighborUpBit = octantUpBit + face.getStepY();
                    int neighborSouthBit = octantSouthBit + face.getStepZ();
                    boolean neighborInsideBlock = neighborEastBit >= 0 && neighborEastBit <= 1
                            && neighborUpBit >= 0 && neighborUpBit <= 1
                            && neighborSouthBit >= 0 && neighborSouthBit <= 1;
                    if (neighborInsideBlock) {
                        int neighborIndex = neighborEastBit + 2 * neighborUpBit + 4 * neighborSouthBit;
                        if ((renderState.enabledOctantMask & (1 << neighborIndex)) != 0) continue;
                    }

                    TextureAtlasSprite faceSprite = renderState.spriteByFace[face.get3DDataValue()];
                    if (faceSprite == null) continue;

                    float[][] faceCorners = switch (face) {
                        case DOWN  -> new float[][]{{minX, minY, maxZ}, {minX, minY, minZ}, {maxX, minY, minZ}, {maxX, minY, maxZ}};
                        case UP    -> new float[][]{{minX, maxY, minZ}, {minX, maxY, maxZ}, {maxX, maxY, maxZ}, {maxX, maxY, minZ}};
                        case NORTH -> new float[][]{{maxX, maxY, minZ}, {maxX, minY, minZ}, {minX, minY, minZ}, {minX, maxY, minZ}};
                        case SOUTH -> new float[][]{{minX, maxY, maxZ}, {minX, minY, maxZ}, {maxX, minY, maxZ}, {maxX, maxY, maxZ}};
                        case WEST  -> new float[][]{{minX, maxY, minZ}, {minX, minY, minZ}, {minX, minY, maxZ}, {minX, maxY, maxZ}};
                        case EAST  -> new float[][]{{maxX, maxY, maxZ}, {maxX, minY, maxZ}, {maxX, minY, minZ}, {maxX, maxY, minZ}};
                    };

                    for (float[] corner : faceCorners) {
                        float cornerX = corner[0];
                        float cornerY = corner[1];
                        float cornerZ = corner[2];

                        // UV from position inside the FULL cube (0..16), so the texture matches the uncut block
                        float fullBlockU;
                        float fullBlockV;
                        switch (face) {
                            case DOWN  -> { fullBlockU = cornerX;                         fullBlockV = FULL_BLOCK_SIZE_PX - cornerZ; }
                            case UP    -> { fullBlockU = cornerX;                         fullBlockV = cornerZ; }
                            case NORTH -> { fullBlockU = FULL_BLOCK_SIZE_PX - cornerX;    fullBlockV = FULL_BLOCK_SIZE_PX - cornerY; }
                            case SOUTH -> { fullBlockU = cornerX;                         fullBlockV = FULL_BLOCK_SIZE_PX - cornerY; }
                            case WEST  -> { fullBlockU = cornerZ;                         fullBlockV = FULL_BLOCK_SIZE_PX - cornerY; }
                            default    -> { fullBlockU = FULL_BLOCK_SIZE_PX - cornerZ;    fullBlockV = FULL_BLOCK_SIZE_PX - cornerY; }
                        }

                        vertexConsumer.addVertex(poseEntry, cornerX / FULL_BLOCK_SIZE_PX, cornerY / FULL_BLOCK_SIZE_PX, cornerZ / FULL_BLOCK_SIZE_PX)
                                .setColor(-1)
                                .setUv(faceSprite.getU(fullBlockU / FULL_BLOCK_SIZE_PX), faceSprite.getV(fullBlockV / FULL_BLOCK_SIZE_PX))
                                .setOverlay(OverlayTexture.NO_OVERLAY)
                                .setLight(renderState.lightCoords)
                                .setNormal(poseEntry, face.getStepX(), face.getStepY(), face.getStepZ());
                    }
                }
            }
        });
    }

    public static class SawedBlockRenderState extends BlockEntityRenderState {
        public final BlockModelRenderState blockModelRenderState = new BlockModelRenderState();
        public final TextureAtlasSprite[] spriteByFace = new TextureAtlasSprite[6];
        public int enabledOctantMask;
        public boolean hasContainedBlock;
    }
}
