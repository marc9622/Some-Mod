package somemod.enchanting.potion;

import java.util.stream.Stream;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import somemod.SomeMod;
import somemod.enchanting.entity.effect.EnchantingStatusEffects;

public class EnchantingPotions {
    
    public static final Potion WOUNDED = register("wounded", EnchantingStatusEffects.WOUNDED, 900);
    public static final Potion LONG_WOUNDED = register("long", "wounded", EnchantingStatusEffects.WOUNDED, 1800);
    public static final Potion STRONG_WOUNDED = register("strong", "wounded", EnchantingStatusEffects.WOUNDED, 432, 2);

    public static final Potion MAGIC_FRAGILITY = register("magic_fragility", EnchantingStatusEffects.MAGIC_FRAGILITY, 1800);
    public static final Potion LONG_MAGIC_FRAGILITY = register("long", "magic_fragility", EnchantingStatusEffects.MAGIC_FRAGILITY, 4800);
    public static final Potion STRONG_MAGIC_FRAGILITY = register("strong", "magic_fragility", EnchantingStatusEffects.MAGIC_FRAGILITY, 400, 2);

    public static final Potion MAGIC_RESILIENCE = register("magic_resilience", EnchantingStatusEffects.MAGIC_RESILIENCE, 3600);
    public static final Potion LONG_MAGIC_RESILIENCE = register("long", "magic_resilience", EnchantingStatusEffects.MAGIC_RESILIENCE, 9600);
    public static final Potion STRONG_MAGIC_RESILIENCE = register("strong", "magic_resilience", EnchantingStatusEffects.MAGIC_RESILIENCE, 1800, 2);

    public static final Potion BLAST_STRIKE = register("blast_strike", EnchantingStatusEffects.BLAST_STRIKE, 1800);
    public static final Potion LONG_BLAST_STRIKE = register("long", "blast_strike", EnchantingStatusEffects.BLAST_STRIKE, 4800);
    public static final Potion STRONG_BLAST_STRIKE = register("strong", "blast_strike", EnchantingStatusEffects.BLAST_STRIKE, 400, 2);

    /* 
     * Ideas for potions combining different effects
     *
     *   Mystic Endurance: Wounded and Magic Resilience
     *   Feral Endurance: Resistance and Magic Fragility
     *   Stealth: Speed and Invisibility
     *   Phantom Speed: Speed, Magic Resilience, and Blindness
     *   Lunar Sight: Blindness and Night Vision
     *   Mighty Miner's Elixir: Haste and Slowness
     *   Swift Digging: Haste and Mining Fatigue
     *   Meditation: Regeneration and Slowness
     *   Agility: Speed and Jump Boost
     * 
     */

    public static final Potion MYSTIC_ENDURANCE = register("mystic_endurance", effects(EnchantingStatusEffects.MAGIC_RESILIENCE, EnchantingStatusEffects.WOUNDED), 900, 1);
    public static final Potion LONG_MYSTIC_ENDURANCE = register("long", "mystic_endurance", effects(EnchantingStatusEffects.MAGIC_RESILIENCE, EnchantingStatusEffects.WOUNDED), 1800, 1);
    public static final Potion STRONG_MYSTIC_ENDURANCE = register("strong", "mystic_endurance", effects(EnchantingStatusEffects.MAGIC_RESILIENCE, EnchantingStatusEffects.WOUNDED), 900, 3);

    public static final Potion FERAL_ENDURANCE = register("feral_endurance", effects(StatusEffects.RESISTANCE, EnchantingStatusEffects.MAGIC_FRAGILITY), 900, 1);
    public static final Potion LONG_FERAL_ENDURANCE = register("long", "feral_endurance", effects(StatusEffects.RESISTANCE, EnchantingStatusEffects.MAGIC_FRAGILITY), 1800, 1);
    public static final Potion STRONG_FERAL_ENDURANCE = register("strong", "feral_endurance", effects(StatusEffects.RESISTANCE, EnchantingStatusEffects.MAGIC_FRAGILITY), 900, 3);

    public static final Potion STEALTH = register("stealth", effects(StatusEffects.SPEED, StatusEffects.INVISIBILITY), 1800);
    public static final Potion LONG_STEALTH = register("long", "stealth", effects(StatusEffects.SPEED, StatusEffects.INVISIBILITY), 3600);

    public static final Potion PHANTOM_SPEED = register("phantom_speed", effectInstance(StatusEffects.SPEED, 1800, 2), effectInstance(EnchantingStatusEffects.MAGIC_RESILIENCE, 1800, 2), effectInstance(StatusEffects.BLINDNESS, 400, 0));

    public static final Potion LUNAR_SIGHT = register("lunar_sight", effectInstance(StatusEffects.NIGHT_VISION, 9600, 0), effectInstance(StatusEffects.BLINDNESS, 400, 0));

    public static final Potion MIGHTY_MINERS = register("mighty_miners", effects(StatusEffects.HASTE, StatusEffects.SLOWNESS), 400, 3);
    public static final Potion STRONG_MIGHTY_MINERS = register("strong", "mighty_miners", effects(StatusEffects.HASTE, StatusEffects.SLOWNESS), 400, 5);

    public static final Potion SWIFT_HANDS = register("swift_hands", effectInstance(StatusEffects.HASTE, 1800, 5), effectInstance(StatusEffects.MINING_FATIGUE, 3600, 1));

    public static final Potion MEDITATION = register("meditation", effects(StatusEffects.REGENERATION, StatusEffects.SLOWNESS), 1800);
    public static final Potion LONG_MEDITATION = register("long", "meditation", effects(StatusEffects.REGENERATION, StatusEffects.SLOWNESS), 3600);
    public static final Potion STRONG_MEDITATION = register("strong", "meditation", effectInstance(StatusEffects.REGENERATION, 1800, 2), effectInstance(StatusEffects.SLOWNESS, 1800, 0));

    public static final Potion AGILITY = register("agility", effects(StatusEffects.SPEED, StatusEffects.JUMP_BOOST), 3600);
    public static final Potion LONG_AGILITY = register("long", "agility", effects(StatusEffects.SPEED, StatusEffects.JUMP_BOOST), 9600);
    public static final Potion STRONG_AGILITY = register("strong", "agility", effectInstance(StatusEffects.SPEED, 1800, 3), effectInstance(StatusEffects.JUMP_BOOST, 1800, 1));

    private static final StatusEffectInstance effectInstance(StatusEffect effect, int duration, int amplifier) {
        return new StatusEffectInstance(effect, duration, amplifier);
    }
    private static final StatusEffect[] effects(StatusEffect... effects) {
        return effects;
    }

    private static final Potion register(String name, StatusEffect effect, int duration) {
        return register(name, effect, duration, 0);
    }
    private static final Potion register(String name, StatusEffect effect, int duration, int amplifier) {
        return register("", name, effect, duration, amplifier);
    }
    private static final Potion register(String prefix, String baseName, StatusEffect effect, int duration) {
        return register(prefix, baseName, effect, duration, 0);
    }
    private static final Potion register(String prefix, String baseName, StatusEffect effect, int duration, int amplifier) {
        return register(prefix, baseName, new StatusEffect[] {effect}, duration, amplifier);
    }
    private static final Potion register(String name, StatusEffect[] effects, int duration) {
        return register(name, effects, duration, 0);
    }
    private static final Potion register(String name, StatusEffect[] effects, int duration, int amplifier) {
        return register("", name, effects, duration, amplifier);
    }
    private static final Potion register(String prefix, String baseName, StatusEffect[] effects, int duration) {
        return register(prefix, baseName, effects, duration, 0);
    }
    private static final Potion register(String prefix, String baseName, StatusEffect[] effects, int duration, int amplifier) {
        return register(prefix, baseName, Stream.of(effects).map(effect -> new StatusEffectInstance(effect, duration, amplifier)).toArray(StatusEffectInstance[]::new));
    }
    private static final Potion register(String name, StatusEffectInstance... instances) {
        return register("", name, instances);
    }
    private static final Potion register(String prefix, String baseName, StatusEffectInstance... instances) {
        return SomeMod.register(Registries.POTION, prefix + "_" + baseName, new Potion(baseName, instances));
    }

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}

}
