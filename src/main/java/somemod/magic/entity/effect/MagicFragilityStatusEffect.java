package somemod.magic.entity.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectCategory;
import somemod.common.entity.effect.DamageTakenStatusEffect;

public class MagicFragilityStatusEffect extends DamageTakenStatusEffect {

    protected MagicFragilityStatusEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public float modifyAppliedDamage(LivingEntity entity, int amplifier, DamageSource source, float amount, float amountOriginal) {
        if(!source.isOf(DamageTypes.MAGIC))
            return amount;
        
        return amount + amplifier + 1f; // +1f because amplifier is 0-based
    }

}
