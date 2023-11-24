package somemod.magic.enchantment;

import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;
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
import net.minecraft.world.World;
import somemod.common.enchantment.SprintingEnchantment;

public final class HermesEnchantment extends SprintingEnchantment {

    private static final UUID HERMES_SPEED_BOOST_ID = UUID.fromString("621976BB-92DE-4D3C-91DF-3EE3D3CD1FC4");
    private static final EntityAttributeModifier HERMES_SPEED_BOOST_LOW = new EntityAttributeModifier(HERMES_SPEED_BOOST_ID, "Hermes speed boost low", 0.01f, EntityAttributeModifier.Operation.ADDITION);
    private static final EntityAttributeModifier HERMES_SPEED_BOOST_MID = new EntityAttributeModifier(HERMES_SPEED_BOOST_ID, "Hermes speed boost mid", 0.02f, EntityAttributeModifier.Operation.ADDITION);
    private static final EntityAttributeModifier HERMES_SPEED_BOOST_HIGH = new EntityAttributeModifier(HERMES_SPEED_BOOST_ID, "Hermes speed boost high", 0.03f, EntityAttributeModifier.Operation.ADDITION);
    private static final EntityAttributeModifier HERMES_SPEED_BOOST_TOP = new EntityAttributeModifier(HERMES_SPEED_BOOST_ID, "Hermes speed boost top", 0.04f, EntityAttributeModifier.Operation.ADDITION);

    /**
     * A map representing how many consecutive ticks a player has been sprinting.
     */
    private static final Map<LivingEntity, Integer> SPRINTING_DURATIONS = new WeakHashMap<>();

    protected HermesEnchantment(Enchantment.Rarity rarity, EquipmentSlot... slots) {
        super(rarity, EnchantmentTarget.ARMOR_LEGS, slots);
    }

    @Override
    public StackingBehavior stackingBehavior() {
        return StackingBehavior.ApplyMax;
    }
    
    @Override
    public int getMinPower(int level) {
        return (int) (2.5f * (level+1) * (level+1));
    }

    @Override
    public int getMaxPower(int level) {
        return getMinPower(level) * 3;
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

    protected static boolean shouldGiveLowBoost(int level, int duration) {
        return level >= 1 && duration >= 30f / (level / 2f);
    }

    protected static boolean shouldGiveMidBoost(int level, int duration) {
        return level >= 2 && duration >= 55f / (level / 2f);
    }

    protected static boolean shouldGiveHighBoost(int level, int duration) {
        return level >= 3 && duration >= 75f / (level / 2f);
    }

    protected static boolean shouldGiveTopBoost(int level, int duration) {
        return level >= 4 && duration >= 90f / (level / 2f);
    }

    @Override
    public void tickSprintEnchantment(LivingEntity entity, int enchLevel) {
        World world = entity.getWorld();

        if (world.isClient) {
            final Integer duration = SPRINTING_DURATIONS.get(entity);
            if (duration == null) return; // the player isn't sprinting

            final ParticleEffect particle;
            if      (shouldGiveTopBoost(enchLevel, duration)) particle = ParticleTypes./*BIG*/FLAME;
            else if (shouldGiveLowBoost(enchLevel, duration)) particle = ParticleTypes.SMALL_FLAME;
            else return;

            final Random random = entity.getRandom();
            if (random.nextInt(enchLevel + 1) == 0) return; // 1 in enchantmentLevel chance of not spawning a particle

            Supplier<Double> randomNum = () -> (random.nextDouble() - 0.5d); // -0.5 to 0.5
            double px = randomNum.get() + entity.getX();
            double py = randomNum.get() + entity.getY();
            double pz = randomNum.get() + entity.getZ();
            double vx = randomNum.get() / 4d;
            double vy = randomNum.get() / 2d;
            double vz = randomNum.get() / 4d;
            world.addParticle(particle, px, py, pz, vx, vy, vz);
        }
        else /* !isClient */ {
            final EntityAttributeModifier speedAttribute;

            setSpeedAttribute: {
                if (!entity.isSprinting() || enchLevel <= 0) { // level must be above zero to avoid division by zero
                    speedAttribute = null;
                    SPRINTING_DURATIONS.remove(entity);
                    break setSpeedAttribute;
                }

                final Integer duration = SPRINTING_DURATIONS.get(entity);

                if (duration == null) { // happens when the player has just started sprinting
                    speedAttribute = null;
                    SPRINTING_DURATIONS.put(entity, 0);
                    break setSpeedAttribute;
                }

                if      (shouldGiveTopBoost(enchLevel, duration)) speedAttribute = HERMES_SPEED_BOOST_TOP;
                else if (shouldGiveHighBoost(enchLevel,duration)) speedAttribute = HERMES_SPEED_BOOST_HIGH;
                else if (shouldGiveMidBoost(enchLevel, duration)) speedAttribute = HERMES_SPEED_BOOST_MID;
                else if (shouldGiveLowBoost(enchLevel, duration)) speedAttribute = HERMES_SPEED_BOOST_LOW;
                else                                              speedAttribute = null;

                SPRINTING_DURATIONS.put(entity, duration + 1);
                break setSpeedAttribute;
            }

            EntityAttributeInstance attributeInstance = entity.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);

            if (speedAttribute != null) attributeInstance.addTemporaryModifier(speedAttribute);

            // Just to be safe, we are also removing the attribute even when we don't add it.
            SprintingEnchantment.addConsumerBeforeNextTick(e -> attributeInstance.removeModifier(HERMES_SPEED_BOOST_ID));
        }
    }
    
    @Override
    public boolean canAccept(Enchantment other) {
        return super.canAccept(other)
            && other != Enchantments.SWIFT_SNEAK
            && other != MagicEnchantments.DASHING;
    }

}
