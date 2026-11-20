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
import org.joml.Quaternionf;
import org.jspecify.annotations.Nullable;

public class BedrockPlateauEntityRenderer implements BlockEntityRenderer<BedrockPlateauEntity, BedrockPlateauEntityRenderer.BedrockPlateauEntityRenderState> {
    private final ItemModelResolver itemModelResolver;

    public BedrockPlateauEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.itemModelResolver = context.itemModelResolver();
    }

    @Override
    public BedrockPlateauEntityRenderState createRenderState() {
        return new BedrockPlateauEntityRenderState();
    }

    @Override
    public void extractRenderState(BedrockPlateauEntity blockEntity, BedrockPlateauEntityRenderState state, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);

        int seed = (int) blockEntity.getBlockPos().asLong();
        state.hasItem = !blockEntity.isEmpty();
        if (state.hasItem)
            itemModelResolver.updateForTopItem(state.item, blockEntity.getTheItem(), ItemDisplayContext.FIXED,
                    blockEntity.getLevel(), null, seed);
    }

    @Override
    public void submit(BedrockPlateauEntityRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        if (!state.hasItem) return;
        poseStack.pushPose();

        poseStack.translate(0.5, 0.5, 0.5);
        poseStack.scale(0.5f, 0.5f, 0.5f);
        poseStack.mulPose(Axis.YN.rotationDegrees(46));
        poseStack.translate(0, -0.35, 0);

        state.item.submit(poseStack, submitNodeCollector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);

        poseStack.popPose();
    }

    public static class BedrockPlateauEntityRenderState extends BlockEntityRenderState {
        public boolean hasItem;

        public ItemStackRenderState item = new ItemStackRenderState();
    }
}
