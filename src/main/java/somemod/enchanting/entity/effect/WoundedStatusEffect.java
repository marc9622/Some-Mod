package somemod.enchanting.entity.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectCategory;

public class WoundedStatusEffect extends DamageTakenStatusEffect {

    protected WoundedStatusEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public float modifyAppliedDamage(LivingEntity entity, int amplifier, DamageSource source, float amount, float amountOriginal) {
        return amount + 0.5f * amplifier;
    }

}
