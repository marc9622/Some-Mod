package somemod.magic.entity.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import somemod.SomeMod;

public class MagicStatusEffects {
    
    public static final StatusEffect WOUNDED          = SomeMod.register(Registries.STATUS_EFFECT, "wounded", new WoundedStatusEffect(StatusEffectCategory.HARMFUL, 0xBB622A));
    public static final StatusEffect MAGIC_FRAGILITY  = SomeMod.register(Registries.STATUS_EFFECT, "magic_fragility", new MagicFragilityStatusEffect(StatusEffectCategory.HARMFUL, 0xCCAA22));
    public static final StatusEffect MAGIC_RESILIENCE = SomeMod.register(Registries.STATUS_EFFECT, "magic_resilience", new MagicResilienceStatusEffect(StatusEffectCategory.BENEFICIAL, 0x22CCBB));
    public static final StatusEffect BLAST_STRIKE     = SomeMod.register(Registries.STATUS_EFFECT, "blast_strike", new BlastStrikeStatusEffect(StatusEffectCategory.BENEFICIAL, 0x997766));

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}

}
