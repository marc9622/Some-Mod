package somemod.common.entity.effect;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;

public abstract class DamageDealtStatusEffect extends StatusEffect {

    protected DamageDealtStatusEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    public abstract void onDamageApplied(DamageSource source, float amount, LivingEntity victim, LivingEntity attacker, StatusEffectInstance effectInstance);

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
    }

    @Override
    public void applyInstantEffect(Entity source, Entity attacker, LivingEntity target, int amplifier, double proximity) {
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return false;
    }

    @Override
    public boolean isInstant() {
        return false;
    }
    
}
