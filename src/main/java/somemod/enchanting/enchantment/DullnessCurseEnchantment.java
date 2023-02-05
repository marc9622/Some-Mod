package somemod.enchanting.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

/**
 * The Curse of Dullness Enchantment cannot be
 * combined with any other enchantment,
 * which means that items with this curse
 * cannot have any other enchantments.
 */
public class DullnessCurseEnchantment extends Enchantment {

    protected DullnessCurseEnchantment(Enchantment.Rarity rarity, EquipmentSlot ... slots) {
        super(rarity, EnchantmentTarget.VANISHABLE, slots);
    }

    @Override
    public int getMinPower(int level) {
        return 20;
    }

    @Override
    public int getMaxPower(int level) {
        return 50;
    }

    @Override
    public int getMaxLevel() {
        return 1;
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
        return true;
    }

    @Override
    public boolean isCursed() {
        return true;
    }

    @Override
    public boolean canAccept(Enchantment other) {
        return false;
    }
    
}
