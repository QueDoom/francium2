package net.quedoom.francium.block.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.dispatch.BlockStateModel;
import net.minecraft.client.renderer.block.dispatch.BlockStateModelPart;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;
import net.quedoom.francium.block.FanBlock;
import net.quedoom.francium.init.ModBlockStateModels;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class FanBlockEntityRenderer implements BlockEntityRenderer<FanBlockEntity, FanBlockEntityRenderer.FanBlockEntityRenderState> {
    public FanBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public FanBlockEntityRenderState createRenderState() {
        return new FanBlockEntityRenderState();
    }

    @Override
    public void extractRenderState(FanBlockEntity blockEntity, FanBlockEntityRenderState state, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);

        state.models.clear();
        BlockStateModel model = Minecraft.getInstance().getModelManager().getModel(ModBlockStateModels.FAN_BLADE);
        if (model != null) model.collectParts(blockEntity.getLevel().getRandom(), state.models);

        state.spinning = blockEntity.spinning();

        state.facing = blockEntity.getLevel().getBlockState(blockEntity.getBlockPos()).getValue(FanBlock.FACING);
    }

    @Override
    public void submit(FanBlockEntityRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        poseStack.pushPose();
        poseStack.translate(0.5f, 0.5f, 0.5f);
        poseStack.mulPose(Axis.YP.rotationDegrees(180f - state.facing.toYRot()));
        poseStack.translate(-0.5f, -0.5f, -0.5f);
        poseStack.translate(8f / 16f, 15f / 16f, 4f / 16f);
        // blade go brrrr
        poseStack.mulPose(Axis.ZP.rotationDegrees(state.spinning));

        submitNodeCollector.submitBlockModel(
                poseStack,
                Sheets.cutoutBlockSheet(),
                state.models,
                new int[0],
                state.lightCoords,
                OverlayTexture.NO_OVERLAY,
                0
        );

        poseStack.popPose();
    }

    public static class FanBlockEntityRenderState extends BlockEntityRenderState {
        public List<BlockStateModelPart> models = new ArrayList<>();

        public float spinning; // 0 to 359.99
        public Direction facing;
    }
}
