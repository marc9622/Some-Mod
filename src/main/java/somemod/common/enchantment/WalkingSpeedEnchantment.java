package somemod.common.enchantment;

import net.minecraft.entity.LivingEntity;

public interface WalkingSpeedEnchantment {

    public float scaleMovementInput(LivingEntity entity, int enchantmentLevel);

}

