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

    private final ItemModelResolver firstItemResolver;
    private final ItemModelResolver secondItemResolver;
    private final ItemModelResolver thirdItemResolver;
    private final ItemModelResolver fourthItemResolver;


    public ObsidianMixerRenderer(BlockEntityRendererProvider.Context context) {
        this.stickItemResolver = context.itemModelResolver();

        this.firstItemResolver = context.itemModelResolver();
        this.secondItemResolver = context.itemModelResolver();
        this.thirdItemResolver = context.itemModelResolver();
        this.fourthItemResolver = context.itemModelResolver();
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

        if (blockEntity.getItem(0).isEmpty()) {
            state.hasFirstItem = false;
        } else {
            state.hasFirstItem = true;
            firstItemResolver.updateForTopItem(state.firstItem, blockEntity.getItem(0), ItemDisplayContext.FIXED, blockEntity.getLevel(), null, 0);
        }
        if (blockEntity.getItem(1).isEmpty()) {
            state.hasSecondItem = false;
        } else {
            state.hasSecondItem = true;
            secondItemResolver.updateForTopItem(state.secondItem, blockEntity.getItem(1), ItemDisplayContext.FIXED, blockEntity.getLevel(), null, 0);
        }
        if (blockEntity.getItem(2).isEmpty()) {
            state.hasThirdItem = false;
        } else {
            state.hasThirdItem = true;
            thirdItemResolver.updateForTopItem(state.thirdItem, blockEntity.getItem(2), ItemDisplayContext.FIXED, blockEntity.getLevel(), null, 0);
        }
        if (blockEntity.getItem(3).isEmpty()) {
            state.hasFourthItem = false;
        } else {
            state.hasFourthItem = true;
            fourthItemResolver.updateForTopItem(state.fourthItem, blockEntity.getItem(3), ItemDisplayContext.FIXED, blockEntity.getLevel(), null, 0);
        }
    }

    private static final float firstQuarter = 0.21f;
    private static final float secondQuarter = 0.45f;
    private static final float height = 10f;

    private static final int rotationX = -5;
    private static final int rotationZ = -40;

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



        if (state.hasFirstItem) {
            poseStack.pushPose();
            poseStack.translate(0, pixelCoordsToBlock(height), 0);
            poseStack.mulPose(Axis.XP.rotationDegrees(rotationX));
            poseStack.mulPose(Axis.ZP.rotationDegrees(rotationZ));
            poseStack.translate(firstQuarter, 0, firstQuarter);
            state.firstItem.submit(poseStack, submitNodeCollector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);
            poseStack.popPose();
        }

        if (state.hasSecondItem) {
            poseStack.pushPose();
            poseStack.translate(0, pixelCoordsToBlock(height), 0);
            poseStack.mulPose(Axis.XP.rotationDegrees(rotationX));
            poseStack.mulPose(Axis.ZP.rotationDegrees(rotationZ));
            poseStack.translate(secondQuarter, 0, firstQuarter);
            state.secondItem.submit(poseStack, submitNodeCollector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);
            poseStack.popPose();
        }

        if (state.hasThirdItem) {
            poseStack.pushPose();
            poseStack.translate(0, pixelCoordsToBlock(height), 0);
            poseStack.mulPose(Axis.XP.rotationDegrees(rotationX));
            poseStack.mulPose(Axis.ZP.rotationDegrees(rotationZ));
            poseStack.translate(firstQuarter, 0, secondQuarter);
            state.thirdItem.submit(poseStack, submitNodeCollector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);
            poseStack.popPose();
        }

        if (state.hasFourthItem) {
            poseStack.pushPose();
            poseStack.translate(0, pixelCoordsToBlock(height), 0);
            poseStack.mulPose(Axis.XP.rotationDegrees(rotationX));
            poseStack.mulPose(Axis.ZP.rotationDegrees(rotationZ));
            poseStack.translate(secondQuarter, 0, secondQuarter);
            state.fourthItem.submit(poseStack, submitNodeCollector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);
            poseStack.popPose();
        }
    }

    public class ObsidianMixerRenderState extends BlockEntityRenderState {
        final ItemStackRenderState stickItemStackRenderState = new ItemStackRenderState();

        final ItemStackRenderState firstItem = new ItemStackRenderState();
        final ItemStackRenderState secondItem = new ItemStackRenderState();
        final ItemStackRenderState thirdItem = new ItemStackRenderState();
        final ItemStackRenderState fourthItem = new ItemStackRenderState();

        boolean hasFirstItem = false;
        boolean hasSecondItem = false;
        boolean hasThirdItem = false;
        boolean hasFourthItem = false;
    }

    private float pixelCoordsToBlock(float value) {
        return 0.0625f * value;
    }

}
