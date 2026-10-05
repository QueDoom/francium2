package net.quedoom.francium.block.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

public class BlockContainingRenderer implements BlockEntityRenderer<BlockContainingEntity, BlockContainingRenderer.BlockContainingRenderState > {
    private final BlockModelResolver blockModelResolver;

    public BlockContainingRenderer(BlockEntityRendererProvider.Context context) {
        this.blockModelResolver = context.blockModelResolver();
    }

    @Override
    public BlockContainingRenderState createRenderState() {
        return new BlockContainingRenderState();
    }

    @Override
    public void extractRenderState(BlockContainingEntity be, BlockContainingRenderState state, float partialTicks,
                                   Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(be, state, partialTicks, cameraPosition, breakProgress);

        ItemStack stack = be.getTheItem();
        state.hasBlock = stack.getItem() instanceof BlockItem;
        if (state.hasBlock) {
            BlockState inner = ((BlockItem) stack.getItem()).getBlock().defaultBlockState();
            blockModelResolver.update(state.blockModelRenderState, inner, BlockDisplayContext.create());
        }
    }

    @Override
    public void submit(BlockContainingRenderState state, PoseStack poseStack,
                       SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        if (!state.hasBlock) return;
        poseStack.pushPose();
        poseStack.translate(0.5, 0.5, 0.5);
        poseStack.scale(0.98f, 0.98f, 0.98f);
        poseStack.translate(-0.5, -0.5, -0.5);
        state.blockModelRenderState.submit(poseStack, submitNodeCollector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);
        poseStack.popPose();
    }

    public static class BlockContainingRenderState extends BlockEntityRenderState {
        public final BlockModelRenderState blockModelRenderState = new BlockModelRenderState();
        public boolean hasBlock;
    }
}
