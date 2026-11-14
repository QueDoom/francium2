package net.quedoom.francium.block.thicc_farming;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.quedoom.quet.api.LoadedMods;
import net.quedoom.thicc_farming.block.ModProperties;
import net.quedoom.thicc_farming.block.thicc_farming.RotateableEighthsEatableBlock;
import net.quedoom.thicc_farming.init.ModStats;
import net.quedoom.thicc_farming.init.ModTags;
import net.quedoom.thicc_farming.item.CleaverItem;
import net.quedoom.thicc_farming.util.EighthsEatableOctant;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import toughasnails.api.thirst.IThirst;
import toughasnails.api.thirst.ThirstHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class EighthsEatableBlock extends Block {
    public static final BooleanProperty NORTH_EAST_DOWN = ModProperties.NORTH_EAST_DOWN;
    public static final BooleanProperty NORTH_WEST_DOWN = ModProperties.NORTH_WEST_DOWN;
    public static final BooleanProperty SOUTH_EAST_DOWN = ModProperties.SOUTH_EAST_DOWN;
    public static final BooleanProperty SOUTH_WEST_DOWN = ModProperties.SOUTH_WEST_DOWN;
    public static final BooleanProperty NORTH_EAST_UP = ModProperties.NORTH_EAST_UP;
    public static final BooleanProperty NORTH_WEST_UP = ModProperties.NORTH_WEST_UP;
    public static final BooleanProperty SOUTH_EAST_UP = ModProperties.SOUTH_EAST_UP;
    public static final BooleanProperty SOUTH_WEST_UP = ModProperties.SOUTH_WEST_UP;

    protected final int nutrition;
    protected final float saturation;
    protected final String item;

    public EighthsEatableBlock(String item, int nutrition, float saturation, Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(NORTH_EAST_DOWN, true).setValue(NORTH_WEST_DOWN, true).setValue(SOUTH_EAST_DOWN, true).setValue(SOUTH_EAST_DOWN, true)
                                                                 .setValue(NORTH_EAST_UP, true).setValue(NORTH_WEST_UP, true).setValue(SOUTH_EAST_UP, true).setValue(SOUTH_WEST_UP, true));
        this.nutrition = nutrition;
        this.saturation = saturation;
        this.item = item;
    }

    public EighthsEatableBlock(int nutrition, float saturation, Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(NORTH_EAST_DOWN, true).setValue(NORTH_WEST_DOWN, true).setValue(SOUTH_EAST_DOWN, true).setValue(SOUTH_EAST_DOWN, true)
                                                                 .setValue(NORTH_EAST_UP, true).setValue(NORTH_WEST_UP, true).setValue(SOUTH_EAST_UP, true).setValue(SOUTH_WEST_UP, true));
        this.nutrition = nutrition;
        this.saturation = saturation;
        this.item = "";
    }

    public EighthsEatableBlock(String item, Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(NORTH_EAST_DOWN, true).setValue(NORTH_WEST_DOWN, true).setValue(SOUTH_EAST_DOWN, true).setValue(SOUTH_EAST_DOWN, true)
                .setValue(NORTH_EAST_UP, true).setValue(NORTH_WEST_UP, true).setValue(SOUTH_EAST_UP, true).setValue(SOUTH_WEST_UP, true));
        this.nutrition = 1;
        this.saturation = 0.1F;
        this.item = item;
    }

    public EighthsEatableBlock(Properties properties) {
        super( properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(NORTH_EAST_DOWN, true).setValue(NORTH_WEST_DOWN, true).setValue(SOUTH_EAST_DOWN, true).setValue(SOUTH_EAST_DOWN, true)
                .setValue(NORTH_EAST_UP, true).setValue(NORTH_WEST_UP, true).setValue(SOUTH_EAST_UP, true).setValue(SOUTH_WEST_UP, true));
        this.nutrition = 1;
        this.saturation = 0.1F;
        this.item = "";
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(
                NORTH_EAST_DOWN,
                NORTH_WEST_DOWN,
                SOUTH_EAST_DOWN,
                SOUTH_WEST_DOWN,
                NORTH_EAST_UP,
                NORTH_WEST_UP,
                SOUTH_EAST_UP,
                SOUTH_WEST_UP
        );
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        VoxelShape ned = EighthsEatableOctant.ned.get(state) ? box(8, 0, 0, 16, 8, 8) : Shapes.empty();
        VoxelShape nwd = EighthsEatableOctant.nwd.get(state) ? box(0, 0, 0, 8, 8, 8) : Shapes.empty();
        VoxelShape sed = EighthsEatableOctant.sed.get(state) ? box(8, 0, 8, 16, 8, 16) : Shapes.empty();
        VoxelShape swd = EighthsEatableOctant.swd.get(state) ? box(0, 0, 8, 8, 8, 16) : Shapes.empty();
        VoxelShape neu = EighthsEatableOctant.neu.get(state) ? box(8, 8, 0, 16, 16, 8) : Shapes.empty();
        VoxelShape nwu = EighthsEatableOctant.nwu.get(state) ? box(0, 8, 0, 8, 16, 8) : Shapes.empty();
        VoxelShape seu = EighthsEatableOctant.seu.get(state) ? box(8, 8, 8, 16, 16, 16) : Shapes.empty();
        VoxelShape swu = EighthsEatableOctant.swu.get(state) ? box(0, 8, 8, 8, 16, 16) : Shapes.empty();

        return Shapes.or(neu, nwu, seu, swu, ned, nwd, sed, swd);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (level.isClientSide()) {
            if (eat(level, pos, state, player, hitResult).consumesAction()) {
                return InteractionResult.SUCCESS;
            }

            if (player.getItemInHand(InteractionHand.MAIN_HAND).isEmpty()) {
                return InteractionResult.CONSUME;
            }
        }

        return this.eat(level, pos, state, player, hitResult);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (this.item.isEmpty() || this.item.isBlank()) {
            return InteractionResult.TRY_WITH_EMPTY_HAND;
        }
        if (itemStack.getItem() instanceof CleaverItem || itemStack.is(ModTags.Items.CLEAVERS)) {
            itemStack.hurtAndBreak(1, player, hand.asEquipmentSlot());
            return extract(level, pos, state, player, hitResult);
        }
        return InteractionResult.TRY_WITH_EMPTY_HAND;
    }

    public Item getItem() {
        Identifier id = Identifier.tryParse(this.item);
        return BuiltInRegistries.ITEM.getValue(id);
    }

    protected InteractionResult extract(final Level level, final BlockPos pos, final BlockState state, final Player player, final BlockHitResult hitResult) {
        int bites = getBites(state);
        if (bites < 7) {
            Vec3 hitPos = hitResult.getLocation();
            BlockPos blockPos = hitResult.getBlockPos();
            Direction side = hitResult.getDirection();

            EighthsEatableOctant octant = this.getOctant(hitPos, blockPos, state, side, (state.getBlock() instanceof RotateableEighthsEatableBlock ? state.getValue(RotateableEighthsEatableBlock.FACING) : null));

            Vec3 worldRelative = octant.worldRelative(pos);
            Containers.dropItemStack(level, worldRelative.x, worldRelative.y, worldRelative.z, getItem().getDefaultInstance());

            set(level, pos, state, octant);

        } else {
            level.removeBlock(pos, false);
            level.gameEvent(player, GameEvent.BLOCK_DESTROY, pos);
        }

        return InteractionResult.SUCCESS;
    }

    protected void set(LevelAccessor level, BlockPos pos, BlockState state, EighthsEatableOctant octant) {
        octant.set(((Level) level), state, pos, false);
    }

    protected InteractionResult eat(final LevelAccessor level, final BlockPos pos, final BlockState state, final Player player, final BlockHitResult hitResult) {
        if (!player.canEat(false)) {
            return InteractionResult.PASS;
        }

        player.awardStat(ModStats.EAT_EIGHTHS_BLOCK);
        playerEat(player);
        int bites = getBites(state);
        level.gameEvent(player, GameEvent.EAT, pos);
        if (bites < 7) {
            Vec3 hitPos = hitResult.getLocation();
            BlockPos blockPos = hitResult.getBlockPos();
            Direction side = hitResult.getDirection();

            EighthsEatableOctant octant = this.getOctant(hitPos, blockPos, state, side, (state.getBlock() instanceof RotateableEighthsEatableBlock ? state.getValue(RotateableEighthsEatableBlock.FACING) : null));

            set(level, pos, state, octant);

        } else {
            level.removeBlock(pos, false);
            level.gameEvent(player, GameEvent.BLOCK_DESTROY, pos);
        }

        return InteractionResult.SUCCESS;
    }

    protected void playerEat(Player player) {
        player.getFoodData().eat(nutrition, saturation);
    }

    protected void playerdrink(Player player, int thirst, float hydration) {
        if (LoadedMods.TOUGH_AS_NAILS && player instanceof ServerPlayer) {
            IThirst thirstHelper = ThirstHelper.getThirst(player);
            thirstHelper.addThirst(thirst);
            thirstHelper.addHydration(hydration);
        }
    }

    protected @NonNull EighthsEatableOctant getOctant(Vec3 hitPos, BlockPos blockPos, BlockState state, Direction side) {
        return getOctant(hitPos, blockPos, state, side, null);
    }
    protected @NonNull EighthsEatableOctant getOctant(Vec3 hitPos, BlockPos blockPos, BlockState state, Direction side, @Nullable Direction facing) {
        double dx = hitPos.x - blockPos.getX() - (side.equals(Direction.EAST) ? 0.01F : -0.01);
        double dy = hitPos.y - blockPos.getY() - (side.equals(Direction.UP) ? 0.01F : -0.01);
        double dz = hitPos.z - blockPos.getZ() - (side.equals(Direction.NORTH) ? -0.01F : 0.01);

        EighthsEatableOctant octant = EighthsEatableOctant.ofBooleans(dz < 0.5, !(dx < 0.5), !(dy < 0.5));
        return orRandomRemaining(octant, state);
    }

    protected EighthsEatableOctant orRandomRemaining(EighthsEatableOctant octant, BlockState state) {
        if (!octant.isEmpty(state)) return octant;
        List<EighthsEatableOctant> remaining = new ArrayList<>();
        for (EighthsEatableOctant o : EighthsEatableOctant.values()) {
            if (!o.isEmpty(state)) remaining.add(o);
        }
        return remaining.isEmpty() ? octant
                : remaining.get(ThreadLocalRandom.current().nextInt(remaining.size()));
    }

    protected static int getBites(BlockState state) {
        int bites = 0;
        if (!state.getValue(NORTH_EAST_DOWN)) {
            bites++;
        }
        if (!state.getValue(NORTH_WEST_DOWN)) {
            bites++;
        }
        if (!state.getValue(SOUTH_EAST_DOWN)) {
            bites++;
        }
        if (!state.getValue(SOUTH_WEST_DOWN)) {
            bites++;
        }
        if (!state.getValue(NORTH_EAST_UP)) {
            bites++;
        }
        if (!state.getValue(NORTH_WEST_UP)) {
            bites++;
        }
        if (!state.getValue(SOUTH_EAST_UP)) {
            bites++;
        }
        if (!state.getValue(SOUTH_WEST_UP)) {
            bites++;
        }
        return bites;
    }



}
