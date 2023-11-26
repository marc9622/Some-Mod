package somemod.magic.entity.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import somemod.SomeMod;

public class MagicStatusEffects {
    
    public static final StatusEffect WOUNDED          = SomeMod.registerStatusEffect("wounded",          new WoundedStatusEffect(StatusEffectCategory.HARMFUL, 0xBB622A));
    public static final StatusEffect MAGIC_FRAGILITY  = SomeMod.registerStatusEffect("magic_fragility",  new MagicFragilityStatusEffect(StatusEffectCategory.HARMFUL, 0xCCAA22));
    public static final StatusEffect MAGIC_RESILIENCE = SomeMod.registerStatusEffect("magic_resilience", new MagicResilienceStatusEffect(StatusEffectCategory.BENEFICIAL, 0x22CCBB));

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}

}
