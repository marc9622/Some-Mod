package somemod.frost.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome.Precipitation;
import somemod.common.enchantment.WalkingSpeedEnchantment;

public class WinterWalkerEnchantment extends Enchantment implements WalkingSpeedEnchantment {

    protected WinterWalkerEnchantment(Rarity weight, EquipmentSlot... slotTypes) {
        super(weight, EnchantmentTarget.ARMOR_FEET, slotTypes);
    }

    @Override
    public float scaleMovementInput(LivingEntity entity, int enchantmentLevel) {
        World world = entity.getWorld();

        if (world.isRaining()) {
            BlockPos pos = entity.getSteppingPos();

            if (world.isSkyVisible(pos) &&
                world.getBiome(pos).value().getPrecipitation(pos) == Precipitation.SNOW) {
                return 1.0f + (0.2f * enchantmentLevel);
            }

        }

        return 1.0f;
    }

    @Override
    public int getMinPower(int level) {
        return level * 10;
    }

    @Override
    public int getMaxPower(int level) {
        return getMinPower(level) + 15;
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
        return true;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean canAccept(Enchantment other) {
        return super.canAccept(other)
            && other != Enchantments.DEPTH_STRIDER
            && other != Enchantments.FROST_WALKER;
    }

}

