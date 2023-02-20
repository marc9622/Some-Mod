package somemod.enchanting.enchantment;

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

    public void onStartSprint(LivingEntity entity, int enchantmentLevel) {}

    public void onEndSprint(LivingEntity entity, int enchantmentLevel) {}

    public static void beforeTickSprintEnchantments(LivingEntity entity) {
        if(entity.world.isClient) return;

        beforeTickConsumers.forEach(consumer -> consumer.accept(entity));
        beforeTickConsumers.clear();
    }

    public void tickSprintEnchantment(LivingEntity entity, int enchantmentLevel) {}

    protected static void addConsumerBeforeNextTick(Consumer<LivingEntity> consumer) {
        beforeTickConsumers.add(consumer);
    }

}
