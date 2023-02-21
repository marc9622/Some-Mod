package somemod.enchanting.entity.effect;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public abstract class DamageTakenStatusEffect extends StatusEffect {
    
    protected DamageTakenStatusEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    /**
     * Called when the entity is about to take damage.
     * <p>
     * Can be overridden to trigger certain events on damage taken,
     * or simply update the amount of damage applied.
     * @param entity The entity taking damage
     * @param source The source of the damage
     * @param amount The amount of damage to be taken
     * <i>after</i> modifications such as resistance
     * and armor protection has been calculated.
     * @param amountOriginal The amount of damage
     * originally to be taken <i>before</i> modifications such as
     * resistance and armor protection.
     * @return The new amount of damage to be taken
     */
    public abstract float modifyAppliedDamage(LivingEntity entity, int amplifier, DamageSource source, float amount, float amountOriginal);

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
