package somemod.frost.entity.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import somemod.SomeMod;

public final class FrostStatusEffects {

    public static final StatusEffect FROSTBITE = SomeMod.registerStatusEffect("frostbite", new FrostbiteStatusEffect(StatusEffectCategory.HARMFUL, 0x8CD1D9));

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}
}

