package somemod.enchanting.entity.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectCategory;

public class MagicFragilityStatusEffect extends DamageTakenStatusEffect {

    protected MagicFragilityStatusEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public float modifyAppliedDamage(LivingEntity entity, int amplifier, DamageSource source, float amount, float amountOriginal) {
        // Only applies extra damage on magic damage
        if(!source.isMagic())
            return amount;
        
        return amount + amplifier + 1f; // +1f because amplifier is 0-based
    }

}
