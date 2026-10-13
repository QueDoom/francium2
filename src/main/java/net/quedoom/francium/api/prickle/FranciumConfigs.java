package net.quedoom.francium.api.prickle;

import net.darkhax.pricklemc.common.api.annotations.Value;

public class FranciumConfigs {
    @Value(name = "Bellow Delay", comment = "How many tick to wait before the bellow sucks in new air", writeDefault = true)
    public int bellowDelay = 20;
}
