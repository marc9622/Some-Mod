package somemod.magic.enchantment;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;

public abstract class SprintingEnchantment extends Enchantment {

    private static List<Consumer<LivingEntity>> beforeTickConsumers = new ArrayList<>();

    protected SprintingEnchantment(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
    }

    /**
     * Determines what to do when an entity has multiple equipment pieces that have the same sprinting enchantment.
     * <p>
     * Two sprinting enchantments are considered 'the same' if they have the same registry id.
     */
    public static enum StackingBehavior {
        /** Apply each individual enchantment one by one */
        ApplyIndividually,
        /** Applies the enchantment once using the sum of all the levels */
        ApplySummed,
        /** Only applies the enchantment with the highest level */
        ApplyMax,
    }

    /** {@see StackingBehavior} */
    public abstract StackingBehavior stackingBehavior();

    public void onStartSprint(LivingEntity entity, int enchantmentLevel) {}

    public void onEndSprint(LivingEntity entity, int enchantmentLevel) {}

    public static void beforeTickSprintEnchantments(LivingEntity entity) {
        if (entity.world.isClient) return;

        beforeTickConsumers.forEach(consumer -> consumer.accept(entity));
        beforeTickConsumers.clear();
    }

    public void tickSprintEnchantment(LivingEntity entity, int enchantmentLevel) {}

    protected static void addConsumerBeforeNextTick(Consumer<LivingEntity> consumer) {
        beforeTickConsumers.add(consumer);
    }

}
