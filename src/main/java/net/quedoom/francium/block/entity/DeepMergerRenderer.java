package net.quedoom.francium.block.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.phys.Vec3;
import net.quedoom.francium.init.ModItems;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.jspecify.annotations.Nullable;

import java.util.List;

public class DeepMergerRenderer implements BlockEntityRenderer<DeepMergerEntity, DeepMergerRenderer.DeepMergerRenderState> {
    private final ItemModelResolver itemModelResolver;
    private final ItemModelResolver rawItemModelResolver;
    private final List<Float> xList = List.of(8F, 5.5F, 10.5F, 3F, 8F, 13F);
    private final List<Float> zList = List.of(3F, 8F, 8F, 13F, 13F, 13F);

    public DeepMergerRenderer(BlockEntityRendererProvider.Context context) {
        this.itemModelResolver = context.itemModelResolver();
        this.rawItemModelResolver = context.itemModelResolver();
    }

    @Override public DeepMergerRenderState createRenderState() {
        return new DeepMergerRenderState();
    }

    @Override
    public void extractRenderState(DeepMergerEntity blockEntity, DeepMergerRenderState state, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);

        itemModelResolver.updateForTopItem(state.itemStackRenderState, ModItems.SLOT.getDefaultInstance(), ItemDisplayContext.FIXED,
                blockEntity.getLevel(), null, 0);

        rawItemModelResolver.updateForTopItem(state.rawItemStackRenderState, ModItems.RAW_SLOT.getDefaultInstance(), ItemDisplayContext.FIXED,
                blockEntity.getLevel(), null, 0);

    }

    @Override
    public void submit(DeepMergerRenderState state, PoseStack poseStack, SubmitNodeCollector queue, CameraRenderState camera) {
        for (int i = 0; i < 6; i++) {
            poseStack.pushPose();

            float x = pixelCoordsToBlock(xList.get(i));
            float y = 1.2F;
            float z = pixelCoordsToBlock(zList.get(i));

            poseStack.translate(x, y, z);

            poseStack.mulPose(camera.orientation);

            poseStack.scale(0.45f, 0.45f, 0.45f);

            if (i > 2) {
                state.itemStackRenderState.submit(poseStack, queue, state.lightCoords + 1, OverlayTexture.NO_OVERLAY, 0);
            } else state.rawItemStackRenderState.submit(poseStack, queue, state.lightCoords + 1, OverlayTexture.NO_OVERLAY, 0);


            poseStack.popPose();
        }

    }

    public class DeepMergerRenderState extends BlockEntityRenderState {
        final ItemStackRenderState itemStackRenderState = new ItemStackRenderState();
        final ItemStackRenderState rawItemStackRenderState = new ItemStackRenderState();
    }

    private float pixelCoordsToBlock(float value) {
        return (1F / 16F) * value;
    }

}
