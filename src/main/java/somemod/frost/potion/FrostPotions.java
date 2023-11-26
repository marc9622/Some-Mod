package somemod.frost.potion;

import net.minecraft.potion.Potion;
import somemod.frost.entity.effect.FrostStatusEffects;

import static somemod.magic.potion.MagicPotions.*;

public final class FrostPotions {

    public static final Potion FROSTBITE = register("frostbite", FrostStatusEffects.FROSTBITE, 900);
    public static final Potion LONG_FROSTBITE = register("long", "frostbite", FrostStatusEffects.FROSTBITE, 1800);
    public static final Potion STRONG_FROSTBITE = register("strong", "frostbite", FrostStatusEffects.FROSTBITE, 432, 2);

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}
}

