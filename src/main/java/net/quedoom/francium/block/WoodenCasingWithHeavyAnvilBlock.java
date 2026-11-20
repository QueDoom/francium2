package net.quedoom.francium.block;

import com.terraformersmc.modmenu.util.mod.Mod;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.quedoom.francium.init.ModBlocks;
import org.jspecify.annotations.Nullable;

public class WoodenCasingWithHeavyAnvilBlock extends Block {
    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;

    public WoodenCasingWithHeavyAnvilBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData) {
        return ModBlocks.WOODEN_CASING.asItem().getDefaultInstance();
    }

    @Override
    public void destroy(LevelAccessor level, BlockPos pos, BlockState state) {
        spawnAnvilAfterBreak(((Level) level), pos, state.getValue(FACING));
    }

    public static void spawnAnvilAfterBreak(Level level, BlockPos pos, Direction facing) {
        BlockState state = ModBlocks.HEAVY_ANVIL.defaultBlockState().setValue(HeavyAnvilBlock.FACING, facing);

        FallingBlockEntity entity = FallingBlockEntity.fall(level, pos, state);

        entity.setPos(entity.getX(), entity.getY() + 0.35, entity.getZ());
    }
}
