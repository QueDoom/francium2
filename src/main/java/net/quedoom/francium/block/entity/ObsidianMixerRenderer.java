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
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

public class ObsidianMixerRenderer implements BlockEntityRenderer<ObsidianMixerEntity, ObsidianMixerRenderer.ObsidianMixerRenderState> {
    private final ItemModelResolver stickItemResolver;


    public ObsidianMixerRenderer(BlockEntityRendererProvider.Context context) {
        this.stickItemResolver = context.itemModelResolver();
    }

    @Override
    public ObsidianMixerRenderState createRenderState() {
        return new ObsidianMixerRenderState();
    }

    @Override
    public void extractRenderState(ObsidianMixerEntity blockEntity, ObsidianMixerRenderState state, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);

        stickItemResolver.updateForTopItem(state.stickItemStackRenderState, Items.STICK.getDefaultInstance(), ItemDisplayContext.FIXED,
                blockEntity.getLevel(), null, 0);


    }

    @Override
    public void submit(ObsidianMixerRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        //Stick Static
        poseStack.pushPose();
        poseStack.translate(0, pixelCoordsToBlock(0.875f), 0);
        poseStack.mulPose(Axis.XP.rotationDegrees(-5.75f));
        poseStack.mulPose(Axis.ZP.rotationDegrees(-45));
        poseStack.translate(pixelCoordsToBlock(2F), 1, pixelCoordsToBlock(4f));
        state.stickItemStackRenderState.submit(poseStack, submitNodeCollector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);
        poseStack.popPose();
    }

    public class ObsidianMixerRenderState extends BlockEntityRenderState {
        final ItemStackRenderState stickItemStackRenderState = new ItemStackRenderState();
    }

    private float pixelCoordsToBlock(float value) {
        return 0.0625f * value;
    }

}
