package somemod.magic.enchantment;

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
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.random.Random;

public class HermesEnchantment extends SprintingEnchantment {

    private static final UUID HERMES_SPEED_BOOST_ID = UUID.fromString("621976BB-92DE-4D3C-91DF-3EE3D3CD1FC4");
    private static final EntityAttributeModifier HERMES_SPEED_BOOST_LOW = new EntityAttributeModifier(HERMES_SPEED_BOOST_ID, "Hermes speed boost low", 0.01f, EntityAttributeModifier.Operation.ADDITION);
    private static final EntityAttributeModifier HERMES_SPEED_BOOST_MID = new EntityAttributeModifier(HERMES_SPEED_BOOST_ID, "Hermes speed boost mid", 0.02f, EntityAttributeModifier.Operation.ADDITION);
    private static final EntityAttributeModifier HERMES_SPEED_BOOST_HIGH = new EntityAttributeModifier(HERMES_SPEED_BOOST_ID, "Hermes speed boost high", 0.03f, EntityAttributeModifier.Operation.ADDITION);
    private static final EntityAttributeModifier HERMES_SPEED_BOOST_TOP = new EntityAttributeModifier(HERMES_SPEED_BOOST_ID, "Hermes speed boost top", 0.04f, EntityAttributeModifier.Operation.ADDITION);

    /**
     * A map representing how many consecutive ticks a player has been sprinting.
     */
    private static final Map<LivingEntity, Integer> SPRINTING_DURATION = new java.util.WeakHashMap<>();

    protected HermesEnchantment(Enchantment.Rarity rarity, EquipmentSlot ... slots) {
        super(rarity, EnchantmentTarget.ARMOR_LEGS, slots);
    }
    
    @Override
    public int getMinPower(int level) {
        return (int) (2.5f * (level+1) * (level+1));
    }

    @Override
    public int getMaxPower(int level) {
        return super.getMinPower(level) * 3;
    }

    @Override
    public int getMaxLevel() {
        return 4;
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

    protected static boolean shouldGiveLowBoost(int level, int currentTicks) {
        return level >= 1 && currentTicks >= 30f / (level / 2f);
    }

    protected static boolean shouldGiveMidBoost(int level, int currentTicks) {
        return level >= 2 && currentTicks >= 55f / (level / 2f);
    }

    protected static boolean shouldGiveHighBoost(int level, int currentTicks) {
        return level >= 3 && currentTicks >= 75f / (level / 2f);
    }

    protected static boolean shouldGiveTopBoost(int level, int currentTicks) {
        return level >= 4 && currentTicks >= 90f / (level / 2f);
    }

    @Override
    public void tickSprintEnchantment(LivingEntity entity, int enchantmentLevel) {
        EntityAttributeModifier speedAttribute;

        if(entity.world.isClient) {
            Integer currentTicks = SPRINTING_DURATION.get(entity);
            if(currentTicks == null)
                return;

            ParticleEffect particle;

            if(shouldGiveTopBoost(enchantmentLevel, currentTicks))
                particle = ParticleTypes.FLAME;
            else if(shouldGiveLowBoost(enchantmentLevel, currentTicks))
                particle = ParticleTypes.SMALL_FLAME;
            else
                return;

            Random random = entity.getRandom();
            if(random.nextInt(enchantmentLevel + 1) == 0) return;
            Supplier<Double> randomNum = () -> random.nextDouble() - 0.5f; // Number between -0.5 and 0.5
            double x = entity.getX() + randomNum.get();
            double y = entity.getY() + randomNum.get();
            double z = entity.getZ() + randomNum.get();
            double vx = 0 + randomNum.get() / 4;
            double vy = 0 + randomNum.get() / 2;
            double vz = 0 + randomNum.get() / 4;
            entity.world.addParticle(particle, x, y, z, vx,vy, vz);
        }
        else {
            // Level must be above zero to avoid division by zero
            if (entity.isSprinting() && enchantmentLevel > 0) {
                
                Integer currentTicks = SPRINTING_DURATION.get(entity);
                if(currentTicks == null) {
                    speedAttribute = null;
                    SPRINTING_DURATION.put(entity, 0);
                }
                else if(shouldGiveTopBoost(enchantmentLevel, currentTicks))
                    speedAttribute = HERMES_SPEED_BOOST_TOP;
                else if(shouldGiveHighBoost(enchantmentLevel, currentTicks))
                    speedAttribute = HERMES_SPEED_BOOST_HIGH;
                else if(shouldGiveMidBoost(enchantmentLevel, currentTicks))
                    speedAttribute = HERMES_SPEED_BOOST_MID;
                else if(shouldGiveLowBoost(enchantmentLevel, currentTicks))
                    speedAttribute = HERMES_SPEED_BOOST_LOW;
                else
                    speedAttribute = null;

                SPRINTING_DURATION.compute(entity, (key, duration) -> duration + 1);
            }
            else {
                speedAttribute = null;
                SPRINTING_DURATION.remove(entity);
            }

            EntityAttributeInstance attributeInstance = entity.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
            HermesEnchantment.addConsumerBeforeNextTick(e -> attributeInstance.removeModifier(HERMES_SPEED_BOOST_ID));

            if(speedAttribute != null)
                attributeInstance.addTemporaryModifier(speedAttribute);
        }
    }
    
    @Override
    public boolean canAccept(Enchantment other) {
        return super.canAccept(other)
            && other != Enchantments.SWIFT_SNEAK
            && other != MagicEnchantments.DASHING;
    }

}
