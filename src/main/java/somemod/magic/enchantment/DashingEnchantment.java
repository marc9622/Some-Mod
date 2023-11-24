package somemod.magic.enchantment;

import java.io.IOException;
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
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import somemod.SomeMod;
import somemod.common.enchantment.SprintingEnchantment;

public final class DashingEnchantment extends SprintingEnchantment {

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
    private static final Map<LivingEntity, Integer> DASHING_DURATIONS = new WeakHashMap<>();

    protected DashingEnchantment(Enchantment.Rarity rarity, EquipmentSlot... slots) {
        super(rarity, EnchantmentTarget.ARMOR_LEGS, slots);
    }

    @Override
    public StackingBehavior stackingBehavior() {
        return StackingBehavior.ApplyMax;
    }

    @Override
    public int getMinPower(int level) {
        return level * 10 + 5;
    }

    @Override
    public int getMaxPower(int level) {
        return getMinPower(level) + 20;
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
        
        try (World world = entity.getWorld()) {
            if (world.isClient) {
                Random random = entity.getRandom();
                float pitch = (random.nextFloat() - random.nextFloat()) * 0.2f + 1.0f; // Copied from other minecraft sounds
                entity.playSound(SoundEvents.ENTITY_GHAST_SHOOT, 0.2f, pitch);
            }
            else {
                DASHING_DURATIONS.put(entity, getDashDuration(enchantmentLevel));
            }
        }
        catch (IOException e) {
            SomeMod.logError("Error trying to close world: " + e);
        }
    }

    @Override
    public void tickSprintEnchantment(LivingEntity entity, int enchantmentLevel) {
        
        final Integer duration = DASHING_DURATIONS.get(entity);
        if(duration == null) return; // No reason to update entities not in the map, since they haven't started any dashes

        World world;
        
        if((world = entity.getWorld()).isClient) {
            if(duration > 0) {
                Random random = entity.getRandom();
                if(random.nextInt(enchantmentLevel + 1) == 0) return;

                Supplier<Double> randomNum = () -> random.nextDouble() - 0.5f; // Number between -0.5 and 0.5
                double px = randomNum.get()      + entity.getX();
                double py = randomNum.get() / 2d + entity.getY() + 0.25f;
                double pz = randomNum.get()      + entity.getZ();
                double vx = randomNum.get() / 8d;
                double vy = randomNum.get() / 16d;
                double vz = randomNum.get() / 8d;
                world.addParticle(ParticleTypes.FLAME, px, py, pz, vx,vy, vz);
            }
        }
        else {
            final EntityAttributeModifier speedAttribute;

            if(enchantmentLevel > 0) {
                if(duration > 0) {
                    if(entity.isSprinting()) {
                        speedAttribute = DASHING_SPEED_BOOST;
                        DASHING_DURATIONS.put(entity, duration - 1);
                    }
                    else /* !isSprinting */ {
                        speedAttribute = null;
                        DASHING_DURATIONS.put(entity, 0);
                    }
                }
                else /* duration <= 0 */ {
                    speedAttribute = null;
                    DASHING_DURATIONS.put(entity, duration - 1);
                }
            }
            else /* enchantmentLevel <= 0 */ {
                speedAttribute = null;
                DASHING_DURATIONS.remove(entity);
            }

            EntityAttributeInstance attributeInstance = entity.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);

            if(speedAttribute != null) attributeInstance.addTemporaryModifier(speedAttribute);

            // Just to be safe, we are also removing the attribute even when we don't add it.
            DashingEnchantment.addConsumerBeforeNextTick(e -> attributeInstance.removeModifier(DASHING_SPEED_BOOST_ID));
        }
    }

    @Override
    public boolean canAccept(Enchantment other) {
        return super.canAccept(other)
            && other != Enchantments.SWIFT_SNEAK
            && other != MagicEnchantments.HERMES;
    }

}
