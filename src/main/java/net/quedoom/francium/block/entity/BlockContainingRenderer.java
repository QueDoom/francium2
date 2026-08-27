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
    public void extractRenderState(BlockContainingEntity blockEntity, BlockContainingRenderState state, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);
    }

    @Override
    public void submit(BlockContainingRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        poseStack.pushPose();
        if (state.containerItem.getItem() instanceof BlockItem && state.containerItem.isEmpty()) {
            blockModelResolver.update(state.blockModelRenderState, ((BlockItem) state.containerItem.getItem()).getBlock().defaultBlockState(), BlockDisplayContext.create());
            state.blockModelRenderState.submit(poseStack, submitNodeCollector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);
        }
        poseStack.popPose();
    }

    public class BlockContainingRenderState extends BlockEntityRenderState {
        BlockModelRenderState blockModelRenderState = new BlockModelRenderState();
        ItemStack containerItem = ItemStack.EMPTY;
    }
}
