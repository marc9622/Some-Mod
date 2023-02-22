package somemod.magic.entity.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectCategory;

public class WoundedStatusEffect extends DamageTakenStatusEffect {

    protected WoundedStatusEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public float modifyAppliedDamage(LivingEntity entity, int amplifier, DamageSource source, float amount, float amountOriginal) {
        // Only applies extra damage on physical attacks/damage
        // TODO: Unsure if falling block and fire damage should count as physical damage or not
        if(source.isFallingBlock() || source.isFire() || source.isMagic() || source.isOutOfWorld())
            return amount;
        
        return amount + amplifier + 1f; // +1f because amplifier is 0-based
    }

}
