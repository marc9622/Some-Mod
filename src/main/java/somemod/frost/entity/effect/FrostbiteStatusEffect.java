package somemod.frost.entity.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class FrostbiteStatusEffect extends StatusEffect {

    protected FrostbiteStatusEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        // The mixin redirect on setFrozenTicks handles increasing the frozen ticks;
        entity.setFrozenTicks(entity.getFrozenTicks());
    }

}

