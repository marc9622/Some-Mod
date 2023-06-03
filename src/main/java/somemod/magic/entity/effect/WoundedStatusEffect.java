package somemod.magic.entity.effect;

import java.util.stream.Stream;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectCategory;

public class WoundedStatusEffect extends DamageTakenStatusEffect {

    protected WoundedStatusEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public float modifyAppliedDamage(LivingEntity entity, int amplifier, DamageSource source, float amount, float amountOriginal) {
        // TODO: Unsure about which damage types should count as 'physical' damage
        if (Stream.of(DamageTypes.FALLING_BLOCK, DamageTypes.MAGIC, DamageTypes.INDIRECT_MAGIC, DamageTypes.WITHER, DamageTypes.FREEZE, DamageTypes.IN_FIRE, DamageTypes.ON_FIRE, DamageTypes.HOT_FLOOR, DamageTypes.DROWN, DamageTypes.STARVE, DamageTypes.BAD_RESPAWN_POINT, DamageTypes.OUT_OF_WORLD).anyMatch(type -> source.isOf(type)))
            return amount;
        
        return amount + amplifier + 1f; // +1f because amplifier is 0-based
    }

}
