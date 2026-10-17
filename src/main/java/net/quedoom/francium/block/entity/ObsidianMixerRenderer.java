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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

public class ObsidianMixerRenderer implements BlockEntityRenderer<ObsidianMixerEntity, ObsidianMixerRenderer.ObsidianMixerRenderState> {
    private static final int SLOTS = 4;

    private static final float[] SLOT_ANGLE = {225f, 315f, 135f, 45f};
    private static final float[] SLOT_SPEED = {1.0f, 1.12f, 0.9f, 1.06f};

    // rendering static
    private static final float FLOOR_Y = 2f / 16f + 0.01f;
    private static final float MAX_SPIN_DEGREES = 14f;
    private static final float LAYER_STEP = 0.02f;
    private static final float MAX_HEIGHT_JITTER = 0.001f;
    private static final float ITEM_SCALE = 0.65f;
    // spin
    private static final float ORBIT_RADIUS = 3.8f / 16f;
    private static final float SPIN_SPEED = 6f;
    private static final float STICK_TILT = 20f;
    private static final float MERGE_GROWTH = 0.6f;

    private final ItemModelResolver itemModelResolver;

    public ObsidianMixerRenderer(BlockEntityRendererProvider.Context context) {
        this.itemModelResolver = context.itemModelResolver();
    }

    @Override
    public ObsidianMixerRenderState createRenderState() {
        return new ObsidianMixerRenderState();
    }

    @Override
    public void extractRenderState(ObsidianMixerEntity blockEntity, ObsidianMixerRenderState state, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);

        itemModelResolver.updateForTopItem(state.stickItemStackRenderState, Items.STICK.getDefaultInstance(),
                ItemDisplayContext.FIXED, blockEntity.getLevel(), null, 0);

        for (int i = 0; i < SLOTS; i++) {
            ItemStack stack = blockEntity.getItem(i);
            state.hasItem[i] = !stack.isEmpty();
            if (state.hasItem[i]) {
                itemModelResolver.updateForTopItem(state.items[i], stack, ItemDisplayContext.FIXED,
                        blockEntity.getLevel(), null, i);
            }
        }

        state.mergeAmount = blockEntity.getMergeAmount(partialTicks);
        float progress = blockEntity.getSpinTick();
        state.spinning = blockEntity.isMixing();
        if (state.spinning)
            state.stirAngle = (progress + partialTicks * blockEntity.getSpinStep()) * SPIN_SPEED;

    }

    @Override
    public void submit(ObsidianMixerRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        poseStack.pushPose();
        poseStack.translate(0.5f, 0, 0.5f);
        poseStack.mulPose(Axis.YP.rotationDegrees(-state.stirAngle * 0.98f));
        poseStack.translate(-0.5f, 0, -0.5f);

        poseStack.translate(0, pixelCoordsToBlock(0.875f), 0);
        poseStack.mulPose(Axis.XP.rotationDegrees(-5.75f + (state.spinning ? 25f : 0f)));
        poseStack.mulPose(Axis.ZP.rotationDegrees(-42.5f));
        poseStack.translate(pixelCoordsToBlock(2F), 1, pixelCoordsToBlock(4f));
        state.stickItemStackRenderState.submit(poseStack, submitNodeCollector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);
        poseStack.popPose();



        int seed = state.blockPos.hashCode();

        for (int i = 0; i < SLOTS; i++) {
            if (!state.hasItem[i]) continue;

            float angle = SLOT_ANGLE[i] + state.stirAngle * SLOT_SPEED[i];
            double rad = Math.toRadians(angle);
            float radius = ORBIT_RADIUS * (1f - state.mergeAmount);
            float x = 0.5f + radius * (float) Math.cos(rad);
            float z = 0.5f + radius * (float) Math.sin(rad);

            float spin = jitter(seed, i * 2) * MAX_SPIN_DEGREES;
            float dy = i * LAYER_STEP + jitter(seed, i * 2 + 1) * MAX_HEIGHT_JITTER;

            poseStack.pushPose();
            poseStack.translate(x, FLOOR_Y + dy, z);
            poseStack.mulPose(Axis.YP.rotationDegrees(-angle + spin));
            poseStack.mulPose(Axis.XP.rotationDegrees(90));
            float scale = ITEM_SCALE * (1f + MERGE_GROWTH * state.mergeAmount);
            poseStack.scale(scale, scale, scale);
            state.items[i].submit(poseStack, submitNodeCollector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);
            poseStack.popPose();
        }
    }

    private static float jitter(int seed, int salt) {
        int h = seed * 31 + salt * 0x9E3779B1;
        h ^= h >>> 15;
        h *= 0x85EBCA6B;
        h ^= h >>> 13;
        return (h & 0xFFFF) / 32767.5f - 1f;
    }

    public static class ObsidianMixerRenderState extends BlockEntityRenderState {
        final ItemStackRenderState stickItemStackRenderState = new ItemStackRenderState();

        float stirAngle = 0f;
        boolean spinning = false;
        float mergeAmount = 0f;

        final ItemStackRenderState[] items = new ItemStackRenderState[SLOTS];
        final boolean[] hasItem = new boolean[SLOTS];

        public ObsidianMixerRenderState() {
            for (int i = 0; i < SLOTS; i++) {
                items[i] = new ItemStackRenderState();
            }
        }
    }

    private float pixelCoordsToBlock(float value) {
        return 0.0625f * value;
    }
}

