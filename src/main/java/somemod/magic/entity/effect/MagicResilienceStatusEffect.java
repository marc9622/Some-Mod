package somemod.magic.entity.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectCategory;

public class MagicResilienceStatusEffect extends DamageTakenStatusEffect {

    protected MagicResilienceStatusEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public float modifyAppliedDamage(LivingEntity entity, int amplifier, DamageSource source, float amount, float amountOriginal) {
        if(!source.isOf(DamageTypes.MAGIC))
            return amount;
        
        if(amplifier <= 0)
            return amount > 1f ? amount - 1f : amount;

        if(amplifier > amount * 2)
            return 0;
        
        return Math.max(amount - amplifier, (2f * amount) / (amplifier + 2));
    }

}
