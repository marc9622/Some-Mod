package somemod.enchanting.entity.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import somemod.SomeMod;

public class EnchantingStatusEffects {
    
    public static final StatusEffect WOUNDED = SomeMod.register(Registries.STATUS_EFFECT, "wounded", new WoundedStatusEffect(StatusEffectCategory.HARMFUL, 0xAA1A0A));
    
    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}

}
