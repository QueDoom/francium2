package net.quedoom.francium.block.thicc_farming;

import net.minecraft.world.entity.player.Player;

public class EighthsDrinkableBlock extends EighthsEatableBlock {
    private final int thirst;
    private final float hydration;

    public EighthsDrinkableBlock(String item, int nutrition, float saturation, int thirst, float hydration, Properties properties) {
        super(item, nutrition, saturation, properties);
        this.thirst = thirst;
        this.hydration = hydration;
    }
    public EighthsDrinkableBlock(String item, int thirst, float hydration, Properties properties) {
        super(item, properties);
        this.thirst = thirst;
        this.hydration = hydration;
    }
    public EighthsDrinkableBlock(String item, Properties properties) {
        super(item, properties);
        this.thirst = 1;
        this.hydration = 0.5f;
    }
    public EighthsDrinkableBlock(int nutrition, float saturation, int thirst, float hydration, Properties properties) {
        super("", nutrition, saturation, properties);
        this.thirst = thirst;
        this.hydration = hydration;
    }
    public EighthsDrinkableBlock(int thirst, float hydration, Properties properties) {
        super("", properties);
        this.thirst = thirst;
        this.hydration = hydration;
    }
    public EighthsDrinkableBlock(Properties properties) {
        super("", properties);
        this.thirst = 1;
        this.hydration = 0.5f;
    }

    @Override
    protected void playerEat(Player player) {
        super.playerEat(player);
        super.playerdrink(player, thirst, hydration);
    }
}
