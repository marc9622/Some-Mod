package somemod.frost.enchantment;

import java.util.Map;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.random.Random;

public class IceColdEnchantment extends Enchantment {

    protected IceColdEnchantment(Rarity weight, EquipmentSlot... slotTypes) {
        super(weight, EnchantmentTarget.ARMOR_CHEST, slotTypes);
    }

    @Override
    public int getMinPower(int level) {
        return (level - 1) * 15 + 5;
    }

    @Override
    public int getMaxPower(int level) {
        return getMinPower(level) + 40;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return true;
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return true;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean canAccept(Enchantment other) {
        return super.canAccept(other) &&
            other != Enchantments.THORNS;
    }

    @Override
    public void onUserDamaged(LivingEntity user, Entity attacker, int level) {
        Random random = user.getRandom();
        Map.Entry<EquipmentSlot, ItemStack> entry = EnchantmentHelper.chooseEquipmentWith(FrostEnchantments.ICE_COLD, user);
        if (attacker != null) {
            attacker.setFrozenTicks(attacker.getFrozenTicks() + getFreezeAmount(level, random));
        }

        if (entry != null) {
            entry.getValue().damage(2, user, entity -> entity.sendEquipmentBreakStatus(entry.getKey()));
        }
    }

    protected int getFreezeAmount(int level, Random random) {
        return random.nextInt(20) + (level * 10);
    }

}

