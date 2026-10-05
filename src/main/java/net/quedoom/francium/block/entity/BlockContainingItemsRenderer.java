package net.quedoom.francium.block.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import java.util.List;

public class BlockContainingItemsRenderer implements BlockEntityRenderer<BlockContainingItemsEntity, BlockContainingItemsRenderer.BlockContainingRenderState> {
    private static final float INSET = 0.03f;

    private final ItemModelResolver itemModelResolver;

    public BlockContainingItemsRenderer(BlockEntityRendererProvider.Context context) {
        this.itemModelResolver = context.itemModelResolver();
    }

    @Override
    public BlockContainingRenderState createRenderState() {
        return new BlockContainingRenderState();
    }

    @Override
    public void extractRenderState(BlockContainingItemsEntity be, BlockContainingRenderState state, float partialTicks,
                                   Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(be, state, partialTicks, cameraPosition, breakProgress);

        int seed = (int) be.getBlockPos().asLong();
        for (int i = 0; i < 6; i++) {
            ItemStack stack = i < be.getContainerSize() ? be.getItem(i) : ItemStack.EMPTY;
            if (stack.isEmpty()) {
                state.items[i].clear();
            } else {
                itemModelResolver.updateForTopItem(state.items[i], stack, ItemDisplayContext.FIXED,
                        be.getLevel(), null, seed + i);
            }
        }
    }

    private static Direction getDirection(int i) {
        return List.of(Direction.UP, Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.DOWN).get(i);
    }

    @Override
    public void submit(BlockContainingRenderState state, PoseStack poseStack,
                       SubmitNodeCollector collector, CameraRenderState camera) {
        for (int i = 0; i < 6; i++) {
            ItemStackRenderState item = state.items[i];
            if (item.isEmpty()) continue;

            Direction dir = getDirection(i);

            poseStack.pushPose();
            poseStack.translate(0.5, 0.5, 0.5);
            switch (dir) {
                case UP -> poseStack.mulPose(Axis.XP.rotationDegrees(-90));
                case DOWN -> poseStack.mulPose(Axis.XP.rotationDegrees(90));
                default -> poseStack.mulPose(Axis.YP.rotationDegrees(-dir.toYRot()));
            }
            poseStack.translate(0, 0, 0.5f - INSET);
            poseStack.scale(1, 1, 1 );
            item.submit(poseStack, collector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);
            poseStack.popPose();
        }
    }

    public static class BlockContainingRenderState extends BlockEntityRenderState {
        public final ItemStackRenderState[] items = new ItemStackRenderState[6];

        public BlockContainingRenderState() {
            for (int i = 0; i < items.length; i++) items[i] = new ItemStackRenderState();
        }
    }
}
