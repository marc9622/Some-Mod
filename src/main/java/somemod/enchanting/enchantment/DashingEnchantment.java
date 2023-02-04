package somemod.enchanting.enchantment;

import java.util.Map;
import java.util.UUID;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;

public class DashingEnchantment extends Enchantment {

    private static final UUID DASHING_SPEED_BOOST_ID = UUID .fromString("75F66D0E-BC9D-4276-896E-2EE85C7ED9D9");
    private static final EntityAttributeModifier DASHING_SPEED_BOOST = new EntityAttributeModifier(DASHING_SPEED_BOOST_ID, "Dashing speed boost", 0.1f, EntityAttributeModifier.Operation.ADDITION);

    /**
     * A map representing how many ticks should pass before a dash runs out for each Entity.
     * When the number hits zero, the dash should end.
     * <p>
     * The number should continue decreasing into the negatives.
     * That is because negative numbers tells us how long ago the last dash ran out,
     * which is used as a cooldown.
     */
    private static final Map<LivingEntity, Integer> DASHING_DURATIONS = new java.util.HashMap<>();

    protected DashingEnchantment(Enchantment.Rarity rarity, EquipmentSlot ... slots) {
        super(rarity, EnchantmentTarget.ARMOR_LEGS, slots);
    }
    
    @Override
    public int getMinPower(int level) {
        return level * 10 + 5;
    }

    @Override
    public int getMaxPower(int level) {
        return super.getMinPower(level) + 20;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return false;
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return false;
    }

    @Override
    public boolean isTreasure() {
        return true;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    protected static int getDashDuration(int level) {
        return level * 4 + 2;
    }

    protected static int getDashCooldown(int level) {
        return level * 2 + 16;
    }

    public static boolean canStartDash(LivingEntity entity, int enchantmentLevel) {
        if(entity.world.isClient) return false;

        if(enchantmentLevel <= 0) return false;

        Integer duration = DASHING_DURATIONS.get(entity);
        return duration == null || duration < -getDashCooldown(enchantmentLevel);
    }

    public static void startDash(LivingEntity entity, int enchantmentLevel) {
        if(entity.world.isClient) return;

        if(enchantmentLevel <= 0) return;

        EntityAttributeInstance movementSpeedAttribute = entity.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
        if(movementSpeedAttribute.getModifier(DASHING_SPEED_BOOST_ID) != null) {
            movementSpeedAttribute.removeModifier(DASHING_SPEED_BOOST);
        }
        movementSpeedAttribute.addTemporaryModifier(DASHING_SPEED_BOOST);

        DASHING_DURATIONS.put(entity, getDashDuration(enchantmentLevel));
    }

    public static void tickDash(LivingEntity entity) {
        if(entity.world.isClient) return;

        Integer duration = DASHING_DURATIONS.get(entity);
        
        if(duration == null) return;

        EntityAttributeInstance movementSpeedAttribute = entity.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
        
        if(duration <= 0) {
            if(movementSpeedAttribute.getModifier(DASHING_SPEED_BOOST_ID) != null) {
                movementSpeedAttribute.removeModifier(DASHING_SPEED_BOOST);
            }
        }

        DASHING_DURATIONS.put(entity, duration-1);
    }

    @Override
    public boolean canAccept(Enchantment other) {
        return super.canAccept(other) && other != Enchantments.SWIFT_SNEAK;
    }

}