package somemod.enchanting.enchantment;

import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.random.Random;

public class DashingEnchantment extends SprintingEnchantment {

    private static final UUID DASHING_SPEED_BOOST_ID = UUID.fromString("75F66D0E-BC9D-4276-896E-2EE85C7ED9D9");
    private static final EntityAttributeModifier DASHING_SPEED_BOOST = new EntityAttributeModifier(DASHING_SPEED_BOOST_ID, "Dashing speed boost", 0.05f, EntityAttributeModifier.Operation.ADDITION);

    /**
     * A map representing how many ticks should pass before a dash runs out for each Entity.
     * When the number hits zero, the dash should end.
     * <p>
     * The number should continue decreasing into the negatives.
     * That is because negative numbers tells us how long ago the last dash ran out,
     * which is used as a cooldown.
     */
    private static final Map<LivingEntity, Integer> DASHING_DURATIONS = new java.util.WeakHashMap<>();

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

    protected static int getDashDuration(int level) {
        return level * 6 + 4;
    }

    protected static int getDashCooldown(int level) {
        return level * 2 + 16;
    }

    @Override
    public void onStartSprint(LivingEntity entity, int enchantmentLevel) {
        Integer duration;
        if (enchantmentLevel <= 0 || (duration = DASHING_DURATIONS.get(entity)) != null && duration > -getDashCooldown(enchantmentLevel))
            return;
        
        if(entity.world.isClient) {
            Random random = entity.getRandom();
            float pitch = (random.nextFloat() - random.nextFloat()) * 0.2f + 1.0f; // Copied from other minecraft sounds
            entity.playSound(SoundEvents.ENTITY_GHAST_SHOOT, 0.2f, pitch);
        }
        else {
            DASHING_DURATIONS.put(entity, getDashDuration(enchantmentLevel));
        }
    }

    @Override
    public void tickSprintEnchantment(LivingEntity entity, int enchantmentLevel) {
        
        // No reason to update entities not in the map, since they haven't started any dashes
        Integer duration = DASHING_DURATIONS.get(entity);
        if(duration == null) return;
        
        EntityAttributeModifier speedAttribute;
        
        if(entity.world.isClient) {
            // Just check duration since the server will make sure the
            // duration is positive only when the entity is dashing.
            if(duration > 0) {
                Random random = entity.getRandom();
                if(random.nextInt(enchantmentLevel + 1) == 0) return;
                Supplier<Double> randomNum = () -> random.nextDouble() - 0.5f; // Number between -0.5 and 0.5
                double x = entity.getX() + randomNum.get();
                double y = entity.getY() + randomNum.get() / 2 + 0.25f;
                double z = entity.getZ() + randomNum.get();
                double vx = randomNum.get() / 8;
                double vy = randomNum.get() / 16;
                double vz = randomNum.get() / 8;
                entity.world.addParticle(ParticleTypes.FLAME, x, y, z, vx,vy, vz);
            }
        }
        else {
            // If entity has equipped item with Dashing level more than zero (Items with enchantment levels of zero is possible through commands)
            if(enchantmentLevel > 0) {
                int newDuration;

                if(duration > 0) {
                    // If entity is sprinting with active dash,
                    // add speed boost and decrement duration.
                    if(entity.isSprinting()) {
                        speedAttribute = DASHING_SPEED_BOOST;
                        newDuration = duration-1;
                    }
                    // If entity stopped sprinting with active dash,
                    // don't add speed boost and set duration to zero.
                    else {
                        speedAttribute = null;
                        newDuration = 0;
                    }
                }
                // If entity does not have an active dash,
                // don't add speed boost and decrement duration.
                else {
                    speedAttribute = null;
                    newDuration = duration-1;
                }
                
                DASHING_DURATIONS.put(entity, newDuration);
            }
            // If leggings with dashing equipment is longer equipped,
            // don't add speed boost and remove entity from durations map.
            else {
                speedAttribute = null;
                DASHING_DURATIONS.remove(entity);
            }

            EntityAttributeInstance attributeInstance = entity.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
            DashingEnchantment.addConsumerBeforeNextTick(e -> attributeInstance.removeModifier(DASHING_SPEED_BOOST_ID));

            if(speedAttribute != null)
                attributeInstance.addTemporaryModifier(speedAttribute);
        }
    }

    @Override
    public boolean canAccept(Enchantment other) {
        return super.canAccept(other)
            && other != Enchantments.SWIFT_SNEAK
            && other != EnchantingEnchantments.HERMES;
    }

}
