package net.quedoom.francium.item;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class DashOrbItem extends Item {
    public DashOrbItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        Vec3 look = player.getLookAngle();
        player.setDeltaMovement(look.scale(1.5));
        player.hurtMarked = true;
        if (!player.hasInfiniteMaterials()) {
            player.getItemInHand(player.getUsedItemHand()).shrink(1);
        }

        return InteractionResult.SUCCESS;
    }
}
