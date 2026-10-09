package net.quedoom.francium.block.menu;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.StringUtil;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ItemEnchantments;

public class HeavyAnvilMenu extends AnvilMenu {
    public HeavyAnvilMenu(int containerId, Inventory inventory) {
        super(containerId, inventory);
    }
    public HeavyAnvilMenu(int containerId, Inventory inventory, final ContainerLevelAccess access) {
        super(containerId, inventory, access);
    }

    private final int maxCost = 60;

    @Override
    public void createResult() {
        ItemStack input = this.inputSlots.getItem(0);
        this.onlyRenaming = false;
        this.cost.set(1);
        int price = 0;
        long tax = 0L;
        int namingCost = 0;
        if (!input.isEmpty() && EnchantmentHelper.canStoreEnchantments(input)) {
            ItemStack result = input.copy();
            ItemStack addition = this.inputSlots.getItem(1);
            ItemEnchantments.Mutable enchantments = new ItemEnchantments.Mutable(EnchantmentHelper.getEnchantmentsForCrafting(result));
            tax += (long)input.getOrDefault(DataComponents.REPAIR_COST, 0).intValue() + addition.getOrDefault(DataComponents.REPAIR_COST, 0).intValue();
            this.repairItemCountCost = 0;
            if (!addition.isEmpty()) {
                boolean usingBook = addition.has(DataComponents.STORED_ENCHANTMENTS);
                if (result.isDamageableItem() && input.isValidRepairItem(addition)) {
                    int repairAmount = Math.min(result.getDamageValue(), result.getMaxDamage() / 4);
                    if (repairAmount <= 0) {
                        this.resultSlots.setItem(0, ItemStack.EMPTY);
                        this.cost.set(0);
                        return;
                    }

                    int count;
                    for (count = 0; repairAmount > 0 && count < addition.getCount(); count++) {
                        int resultDamage = result.getDamageValue() - repairAmount;
                        result.setDamageValue(resultDamage);
                        price++;
                        repairAmount = Math.min(result.getDamageValue(), result.getMaxDamage() / 4);
                    }

                    this.repairItemCountCost = count;
                } else {
                    if (!usingBook && (!result.is(addition.getItem()) || !result.isDamageableItem())) {
                        this.resultSlots.setItem(0, ItemStack.EMPTY);
                        this.cost.set(0);
                        return;
                    }

                    if (result.isDamageableItem() && !usingBook) {
                        int remaining1 = input.getMaxDamage() - input.getDamageValue();
                        int remaining2 = addition.getMaxDamage() - addition.getDamageValue();
                        int additional = remaining2 + result.getMaxDamage() * 12 / 100;
                        int remaining = remaining1 + additional;
                        int resultDamage = result.getMaxDamage() - remaining;
                        if (resultDamage < 0) {
                            resultDamage = 0;
                        }

                        if (resultDamage < result.getDamageValue()) {
                            result.setDamageValue(resultDamage);
                            price += 2;
                        }
                    }

                    ItemEnchantments additionalEnchantments = EnchantmentHelper.getEnchantmentsForCrafting(addition);
                    boolean isAnyEnchantmentCompatible = false;
                    boolean isAnyEnchantmentNotCompatible = false;

                    for (Object2IntMap.Entry<Holder<Enchantment>> entry : additionalEnchantments.entrySet()) {
                        Holder<Enchantment> enchantmentHolder = entry.getKey();
                        int current = enchantments.getLevel(enchantmentHolder);
                        int level = entry.getIntValue();
                        level = current == level ? level + 1 : Math.max(level, current);
                        Enchantment enchantment = enchantmentHolder.value();
                        boolean compatible = enchantment.canEnchant(input);
                        if (this.player.hasInfiniteMaterials() || input.is(Items.ENCHANTED_BOOK)) {
                            compatible = true;
                        }

                        for (Holder<Enchantment> other : enchantments.keySet()) {
                            if (!other.equals(enchantmentHolder) && !Enchantment.areCompatible(enchantmentHolder, other)) {
                                compatible = false;
                                price++;
                            }
                        }

                        if (!compatible) {
                            isAnyEnchantmentNotCompatible = true;
                        } else {
                            isAnyEnchantmentCompatible = true;
                            if (level > enchantment.getMaxLevel()) {
                                level = enchantment.getMaxLevel();
                            }

                            enchantments.set(enchantmentHolder, level);
                            int fee = enchantment.getAnvilCost();
                            if (usingBook) {
                                fee = Math.max(1, fee / 2);
                            }

                            price += fee * level;
                            if (input.getCount() > 1) {
                                price = maxCost;
                            }
                        }
                    }

                    if (isAnyEnchantmentNotCompatible && !isAnyEnchantmentCompatible) {
                        this.resultSlots.setItem(0, ItemStack.EMPTY);
                        this.cost.set(0);
                        return;
                    }
                }
            }

            if (this.itemName != null && !StringUtil.isBlank(this.itemName)) {
                if (!this.itemName.equals(input.getHoverName().getString())) {
                    namingCost = 1;
                    price += namingCost;
                    result.set(DataComponents.CUSTOM_NAME, Component.literal(this.itemName));
                }
            } else if (input.has(DataComponents.CUSTOM_NAME)) {
                namingCost = 1;
                price += namingCost;
                result.remove(DataComponents.CUSTOM_NAME);
            }

            int finalPrice = price <= 0 ? 0 : (int) Mth.clamp(tax + price, 0L, 2147483647L);
            this.cost.set(finalPrice);
            if (price <= 0) {
                result = ItemStack.EMPTY;
            }

            if (namingCost == price && namingCost > 0) {
                if (this.cost.get() >= maxCost) {
                    this.cost.set(maxCost - 1);
                }

                this.onlyRenaming = true;
            }

            if (this.cost.get() >= maxCost && !this.player.hasInfiniteMaterials()) {
                result = ItemStack.EMPTY;
            }

            if (!result.isEmpty()) {
                int baseCost = result.getOrDefault(DataComponents.REPAIR_COST, 0);
                if (baseCost < addition.getOrDefault(DataComponents.REPAIR_COST, 0)) {
                    baseCost = addition.getOrDefault(DataComponents.REPAIR_COST, 0);
                }

                if (namingCost != price || namingCost == 0) {
                    baseCost = calculateIncreasedRepairCost(baseCost);
                }

                result.set(DataComponents.REPAIR_COST, baseCost);
                EnchantmentHelper.setEnchantments(result, enchantments.toImmutable());
            }

            this.resultSlots.setItem(0, result);
            this.broadcastChanges();
        } else {
            this.resultSlots.setItem(0, ItemStack.EMPTY);
            this.cost.set(0);
        }
    }
}
