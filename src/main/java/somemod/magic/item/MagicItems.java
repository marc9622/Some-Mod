package somemod.magic.item;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;

import static net.minecraft.entity.effect.StatusEffects.*;
import static net.minecraft.item.Items.*;
import static net.minecraft.item.ItemGroups.*;
import static net.minecraft.item.ArmorItem.Type.*;
import static net.minecraft.util.Rarity.*;
import somemod.common.item.StaticEffectArmorItem;
import somemod.common.item.RandomEffectArmorItem;
import somemod.magic.block.MagicBlocks;
import somemod.magic.entity.MagicEntityTypes;
import static somemod.magic.entity.effect.MagicStatusEffects.*;

import static somemod.magic.item.MagicArmorMaterials.*;
import static somemod.utils.ItemBuilder.*;

// TODO: Maybe move the initializations these items into a static block, so that the 'public static final BlockItem' doesn't take so much space.
public class MagicItems {

    public static final BlockItem ENCHANTED_BOOKSHELF =          blockItem("enchanted_bookshelf",          MagicBlocks.ENCHANTED_BOOKSHELF)         
                                                                .set(UNCOMMON).after(FUNCTIONAL, BOOKSHELF).build(); // Or maybe next to enchanting table instead?
    public static final BlockItem OBSIDIAN_ENCHANTED_BOOKSHELF = blockItem("obsidian_enchanted_bookshelf", MagicBlocks.OBSIDIAN_ENCHANTED_BOOKSHELF)
                                                                .set(RARE)    .after(FUNCTIONAL, ENCHANTED_BOOKSHELF).build(); // Or maybe next to enchanting table instead?
    
    public static final BlockItem FORGOTTEN_CHEST = blockItem("forgotten_chest", MagicBlocks.FORGOTTEN_CHEST).set(COMMON).after(FUNCTIONAL, CHEST).build();

    //#region ARMOR
    // Arcane: Found in chests
    public static final ArmorItem ARCANE_HAT   = armorItem("arcane_hat",  ARCANE, HELMET)    .after(COMBAT, LEATHER_BOOTS).build();
    public static final ArmorItem ARCANE_ROBE  = armorItem("arcane_robe", ARCANE, CHESTPLATE).after(COMBAT, ARCANE_HAT).build();
    public static final ArmorItem ARCANE_PANTS = item("arcane_pants", StaticEffectArmorItem.of(ARCANE, HELMET).requires(ARCANE_HAT, ARCANE_ROBE)
                                                .gives(MAGIC_RESILIENCE).build()).after(COMBAT, ARCANE_ROBE).build();
    
    // Pirate: Craftable & Found in chests & Villager tradeable
    public static final ArmorItem PIRATE_HAT   = armorItem("pirate_hat",   PIRATE, HELMET)    .after(COMBAT, ARCANE_PANTS).build();
    public static final ArmorItem PIRATE_SHIRT = armorItem("pirate_shirt", PIRATE, CHESTPLATE).after(COMBAT, PIRATE_HAT).build();
    public static final ArmorItem PIRATE_PANTS = armorItem("pirate_pants", PIRATE, LEGGINGS)  .after(COMBAT, PIRATE_SHIRT).build();
    public static final ArmorItem PIRATE_BOOTS = armorItem("pirate_boots", PIRATE, BOOTS)     .after(COMBAT, PIRATE_PANTS).build();
    
    // Honey: Craftable
    public static final ArmorItem HONEY_MASK       = armorItem("honey_mask",       HONEY, HELMET)    .after(COMBAT, PIRATE_BOOTS).build();
    public static final ArmorItem HONEY_CHESTPIECE = armorItem("honey_chestpiece", HONEY, CHESTPLATE).after(COMBAT, HONEY_MASK).build();
    public static final ArmorItem HONEY_LEGPIECE   = armorItem("honey_legpiece",   HONEY, LEGGINGS)  .after(COMBAT, HONEY_CHESTPIECE).build();
    public static final ArmorItem HONEY_BOOTS      = item("honey_boots", RandomEffectArmorItem.of(HONEY, BOOTS).requires(HONEY_MASK, HONEY_CHESTPIECE, HONEY_LEGPIECE)
                                                    .addGroup(SATURATION).durationAndChance(1, 1f / 600f).build()).after(COMBAT, HONEY_LEGPIECE).build();

    // Alchemist: Found in chests & Villager tradeable
    public static final ArmorItem ALCHEMIST_JACKET = armorItem("alchemist_jacket", ALCHEMIST, CHESTPLATE).after(COMBAT, PIRATE_BOOTS).build();
    public static final ArmorItem ALCHEMIST_PANTS  = armorItem("alchemist_pants",  ALCHEMIST, LEGGINGS)  .after(COMBAT, ALCHEMIST_JACKET).build();
    public static final ArmorItem ALCHEMIST_BOOTS  = item("alchemist_boots", RandomEffectArmorItem.of(ALCHEMIST, BOOTS).requires(ALCHEMIST_JACKET, ALCHEMIST_PANTS)
                                                        .addGroup(MAGIC_RESILIENCE).durationAndChance(600, 1f / (10_000f))
                                                        .copyGroupSingle(ABSORPTION)
                                                        .copyGroupSingle(REGENERATION)
                                                        .copyGroupSingle(HASTE)
                                                        .copyGroupSingle(NIGHT_VISION)
                                                        .copyGroupSingle(INVISIBILITY)
                                                        .copyGroupSingle(FIRE_RESISTANCE)
                                                        .copyGroupSingle(WATER_BREATHING)
                                                        .addGroup(NAUSEA).and(POISON).durationAndChance(200, 1f / (20_000f)).build()).after(COMBAT, ALCHEMIST_PANTS).build();
    
    // Elven: Craftable & Found in chests
    public static final Item ELVEN_STEEL           = item     ("elven_steel")                        .after(INGREDIENTS, GOLD_INGOT).build();
    public static final ArmorItem ELVEN_HELMET     = armorItem("elven_helmet",     ELVEN, HELMET)    .after(COMBAT, ALCHEMIST_BOOTS).build();
    public static final ArmorItem ELVEN_CHESTPLATE = armorItem("elven_chestplate", ELVEN, CHESTPLATE).after(COMBAT, ELVEN_HELMET).build();
    public static final ArmorItem ELVEN_LEGGINGS   = armorItem("elven_leggings",   ELVEN, LEGGINGS)  .after(COMBAT, ELVEN_CHESTPLATE).build();
    public static final ArmorItem ELVEN_BOOTS      = item("elven_boots", StaticEffectArmorItem.of(ELVEN, BOOTS).requires(ELVEN_HELMET, ELVEN_CHESTPLATE, ELVEN_LEGGINGS)
                                                        .gives(SPEED).build()).after(COMBAT, ELVEN_LEGGINGS).build();

    // Oceanic: Craftable & Villager tradeable TODO: Maybe rename to 'Diving'?
    public static final ArmorItem OCEANIC_MASK     = item("oceanic_mask", StaticEffectArmorItem.of(OCEANIC, HELMET).gives(WATER_BREATHING).build())
                                                                                                            .after(COMBAT, CHAINMAIL_BOOTS).build();
    public static final ArmorItem OCEANIC_SUIT     = armorItem("oceanic_suit",     OCEANIC, CHESTPLATE).after(COMBAT, OCEANIC_MASK).build();
    public static final ArmorItem OCEANIC_LEGGINGS = armorItem("oceanic_leggings", OCEANIC, LEGGINGS)  .after(COMBAT, OCEANIC_SUIT).build();
    public static final ArmorItem OCEANIC_SHOES    = armorItem("oceanic_shoes",    OCEANIC, BOOTS)     .after(COMBAT, OCEANIC_LEGGINGS).build();

    // Desert: Craftable
    public static final ArmorItem DESERT_HELMET     = armorItem("desert_helmet",     DESERT, HELMET)    .after(COMBAT, OCEANIC_SHOES).build();
    public static final ArmorItem DESERT_CHESTPLATE = armorItem("desert_chestplate", DESERT, CHESTPLATE).after(COMBAT, DESERT_HELMET).build();
    public static final ArmorItem DESERT_LEGGINGS   = armorItem("desert_leggings",   DESERT, LEGGINGS)  .after(COMBAT, DESERT_CHESTPLATE).build();
    public static final ArmorItem DESERT_BOOTS      = item("desert_boots", StaticEffectArmorItem.of(DESERT, BOOTS).requires(DESERT_HELMET, DESERT_CHESTPLATE, DESERT_LEGGINGS)
                                                    .gives(STRENGTH).build())                           .after(COMBAT, DESERT_LEGGINGS).build();
    
    // Lunar: Craftable & Found in chests
    public static final ArmorItem LUNAR_HELMET     = armorItem("lunar_helmet",     LUNAR, HELMET)    .after(COMBAT, DESERT_BOOTS).build();
    public static final ArmorItem LUNAR_CHESTPLATE = armorItem("lunar_chestplate", LUNAR, CHESTPLATE).after(COMBAT, LUNAR_HELMET).build();
    public static final ArmorItem LUNAR_LEGGINGS   = armorItem("lunar_leggings",   LUNAR, LEGGINGS)  .after(COMBAT, LUNAR_CHESTPLATE).build();
    public static final ArmorItem LUNAR_BOOTS      = item("lunar_boots", StaticEffectArmorItem.of(LUNAR, BOOTS).requires(LUNAR_HELMET, LUNAR_CHESTPLATE, LUNAR_LEGGINGS)
                                                    .gives(NIGHT_VISION).build())                    .after(COMBAT, LUNAR_LEGGINGS).build();
    
    // Phantom: Found in chests
    public static final ArmorItem PHANTOM_MASK       = armorItem("phantom_mask",       PHANTOM, HELMET)    .after(COMBAT, LUNAR_BOOTS).build();
    public static final ArmorItem PHANTOM_CHESTPLATE = armorItem("phantom_chestplate", PHANTOM, CHESTPLATE).after(COMBAT, PHANTOM_MASK).build();
    public static final ArmorItem PHANTOM_LEGGINGS   = item("phantom_leggings", StaticEffectArmorItem.of(PHANTOM, LEGGINGS).requires(PHANTOM_MASK, PHANTOM_CHESTPLATE)
                                                    .gives(SLOW_FALLING).build())                          .after(COMBAT, PHANTOM_CHESTPLATE).build();

    // Divine: Found in chests
    public static final ArmorItem DIVINE_HELMET     = armorItem("divine_helmet",     DIVINE, HELMET)    .after(COMBAT, PHANTOM_LEGGINGS).build();
    public static final ArmorItem DIVINE_CHESTPLATE = armorItem("divine_chestplate", DIVINE, CHESTPLATE).after(COMBAT, DIVINE_HELMET).build();
    public static final ArmorItem DIVINE_LEGGINGS   = armorItem("divine_leggings",   DIVINE, LEGGINGS)  .after(COMBAT, DIVINE_CHESTPLATE).build();
    public static final ArmorItem DIVINE_BOOTS      = item("divine_boots", StaticEffectArmorItem.of(DIVINE, BOOTS).requires(DIVINE_HELMET, DIVINE_CHESTPLATE, DIVINE_LEGGINGS)
                                                    .gives(RESISTANCE).build())                         .after(COMBAT, DIVINE_LEGGINGS).build();
    
    // Necrotic: Craftable & Found in chests
    public static final ArmorItem NECROTIC_MASK       = armorItem("necrotic_mask",       NECROTIC, HELMET)    .after(COMBAT, DIVINE_BOOTS).build();
    public static final ArmorItem NECROTIC_CHESTPLATE = armorItem("necrotic_chestplate", NECROTIC, CHESTPLATE).after(COMBAT, NECROTIC_MASK).build();
    public static final ArmorItem NECROTIC_LEGGINGS   = armorItem("necrotic_leggings",   NECROTIC, LEGGINGS)  .after(COMBAT, NECROTIC_CHESTPLATE).build();
    public static final ArmorItem NECROTIC_BOOTS      = item("necrotic_boots", StaticEffectArmorItem.of(NECROTIC, BOOTS)
                                                    .requires(NECROTIC_MASK, NECROTIC_CHESTPLATE, NECROTIC_LEGGINGS).gives(STRENGTH, 1).build())                                              .after(COMBAT, NECROTIC_LEGGINGS).build();

    // Living: Craftable
    public static final ArmorItem LIVING_MASK     = armorItem("living_mask",     LIVING, HELMET)    .after(COMBAT, NECROTIC_BOOTS).build();
    public static final ArmorItem LIVING_SUIT     = armorItem("living_suit",     LIVING, CHESTPLATE).after(COMBAT, LIVING_MASK).build();
    public static final ArmorItem LIVING_LEGGINGS = armorItem("living_leggings", LIVING, LEGGINGS)  .after(COMBAT, LIVING_SUIT).build();
    public static final ArmorItem LIVING_BOOTS    = item("living_boots", RandomEffectArmorItem.of(LIVING, BOOTS).requires(LIVING_MASK, LIVING_SUIT, LIVING_LEGGINGS)
                                                    .addGroup(REGENERATION).durationAndChance(200, 1f / 100f).build()).after(COMBAT, LIVING_LEGGINGS).build();

    // Shadow: Found in chests
    public static final ArmorItem SHADOW_MASK       = armorItem("shadow_mask",       SHADOW, HELMET)    .after(COMBAT, IRON_BOOTS).build();
    public static final ArmorItem SHADOW_CHESTPIECE = armorItem("shadow_chestpiece", SHADOW, CHESTPLATE).after(COMBAT, SHADOW_MASK).build();
    public static final ArmorItem SHADOW_LEGPIECE   = item("shadow_legpiece", StaticEffectArmorItem.of(SHADOW, LEGGINGS).requires(SHADOW_MASK, SHADOW_CHESTPIECE)
                                                    .gives(INVISIBILITY).build())                       .after(COMBAT, SHADOW_CHESTPIECE).build();

    // Angelic: Found in chests
    public static final ArmorItem ANGELIC_CHESTPLATE = armorItem("angelic_chestplate", ANGELIC, CHESTPLATE).after(COMBAT, SHADOW_LEGPIECE).build();
    public static final ArmorItem ANGELIC_LEGGINGS   = item("angelic_leggings", StaticEffectArmorItem.of(ANGELIC, LEGGINGS).requires(ANGELIC_CHESTPLATE)
                                                    .gives(SLOW_FALLING, 1).build())                       .after(COMBAT, ANGELIC_CHESTPLATE).build();

    // Deep Sea: Craftable & Found in chests
    public static final ArmorItem DEEP_SEA_MASK       = item("deep_sea_mask", StaticEffectArmorItem.of(DEEP_SEA, HELMET).gives(WATER_BREATHING).build())
                                                                                                              .after(COMBAT, ANGELIC_LEGGINGS).build();
    public static final ArmorItem DEEP_SEA_CHESTPLATE = armorItem("deep_sea_chestplate", DEEP_SEA, CHESTPLATE).after(COMBAT, DEEP_SEA_MASK).build();
    public static final ArmorItem DEEP_SEA_LEGGINGS   = armorItem("deep_sea_leggings",   DEEP_SEA, LEGGINGS)  .after(COMBAT, DEEP_SEA_CHESTPLATE).build();
    public static final ArmorItem DEEP_SEA_BOOTS      = item("deep_sea_boots", StaticEffectArmorItem.of(DEEP_SEA, BOOTS)
                                                    .requires(DEEP_SEA_MASK, DEEP_SEA_CHESTPLATE, DEEP_SEA_LEGGINGS).gives(CONDUIT_POWER).build())                                              .after(COMBAT, DEEP_SEA_LEGGINGS).build();

    // Magma: Found in chests
    public static final ArmorItem MAGMA_CHESTPLATE = armorItem("magma_chestplate", MAGMA, CHESTPLATE).after(COMBAT, GOLDEN_BOOTS).build();
    public static final ArmorItem MAGMA_LEGGINGS   = armorItem("magma_leggings",   MAGMA, LEGGINGS)  .after(COMBAT, MAGMA_CHESTPLATE).build();
    public static final ArmorItem MAGMA_BOOTS      = item("magma_boots", StaticEffectArmorItem.of(MAGMA, BOOTS).requires(MAGMA_CHESTPLATE, MAGMA_LEGGINGS)
                                                    .gives(FIRE_RESISTANCE).build())                 .after(COMBAT, MAGMA_LEGGINGS).build();

    // Guardian: Craftable
    public static final ArmorItem GUARDIAN_HELMET     = armorItem("guardian_helmet",     GUARDIAN, HELMET)    .after(COMBAT, MAGMA_BOOTS).build();
    public static final ArmorItem GUARDIAN_CHESTPLATE = armorItem("guardian_chestplate", GUARDIAN, CHESTPLATE).after(COMBAT, GUARDIAN_HELMET).build();
    public static final ArmorItem GUARDIAN_LEGGINGS   = armorItem("guardian_leggings",   GUARDIAN, LEGGINGS)  .after(COMBAT, GUARDIAN_CHESTPLATE).build();
    public static final ArmorItem GUARDIAN_BOOTS      = item("guardian_boots", StaticEffectArmorItem.of(GUARDIAN, BOOTS)
                                                    .requires(GUARDIAN_HELMET, GUARDIAN_CHESTPLATE, GUARDIAN_LEGGINGS)
                                                    .gives(RESISTANCE, 2).build())                            .after(COMBAT, GUARDIAN_LEGGINGS).build();
    //#endregion

    public static final SpawnEggItem GHOST_SPAWN_EGG = spawnEggItem("ghost_spawn_egg", MagicEntityTypes.GHOST, 0, 0).addGroup(SPAWN_EGGS).build();

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}

}
