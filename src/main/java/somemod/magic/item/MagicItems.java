package somemod.magic.item;

import net.minecraft.item.ArmorItem.Type;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.util.Rarity;
import somemod.common.item.StaticEffectArmorItem;
import somemod.common.item.RandomEffectArmorItem;
import somemod.magic.block.MagicBlocks;
import somemod.magic.entity.effect.MagicStatusEffects;

import static somemod.magic.item.MagicArmorMaterials.*;
import static somemod.utils.ItemBuilder.*;

// TODO: Maybe move the initializations these items into a static block, so that the 'public static final BlockItem' doesn't take so much space.
public class MagicItems {
    
    public static final BlockItem ENCHANTED_BOOKSHELF =          blockItem("enchanted_bookshelf",          MagicBlocks.ENCHANTED_BOOKSHELF)         
                                                                .set(Rarity.UNCOMMON).after(ItemGroups.FUNCTIONAL, Items.BOOKSHELF).build(); // Or maybe next to enchanting table instead?
    public static final BlockItem OBSIDIAN_ENCHANTED_BOOKSHELF = blockItem("obsidian_enchanted_bookshelf", MagicBlocks.OBSIDIAN_ENCHANTED_BOOKSHELF)
                                                                .set(Rarity.RARE)    .after(ItemGroups.FUNCTIONAL, ENCHANTED_BOOKSHELF).build(); // Or maybe next to enchanting table instead?
    
    public static final BlockItem FORGOTTEN_CHEST = blockItem("forgotten_chest", MagicBlocks.FORGOTTEN_CHEST).set(Rarity.COMMON).after(ItemGroups.FUNCTIONAL, Items.CHEST).build();

    //#region ARMOR
    // Arcane: Found in chests
    public static final ArmorItem ARCANE_HAT   = armorItem("arcane_hat",  ARCANE, Type.HELMET)    .after(ItemGroups.COMBAT, Items.LEATHER_BOOTS).build();
    public static final ArmorItem ARCANE_ROBE  = armorItem("arcane_robe", ARCANE, Type.CHESTPLATE).after(ItemGroups.COMBAT, ARCANE_HAT).build();
    public static final ArmorItem ARCANE_PANTS = item("arcane_pants", StaticEffectArmorItem.of(ARCANE, Type.HELMET).requires(ARCANE_HAT, ARCANE_ROBE)
                                                .gives(MagicStatusEffects.MAGIC_RESILIENCE).build())             .after(ItemGroups.COMBAT, ARCANE_ROBE).build();
    
    // Pirate: Craftable & Found in chests & Villager tradeable
    public static final ArmorItem PIRATE_HAT   = armorItem("pirate_hat",   PIRATE, Type.HELMET)    .after(ItemGroups.COMBAT, ARCANE_PANTS).build();
    public static final ArmorItem PIRATE_SHIRT = armorItem("pirate_shirt", PIRATE, Type.CHESTPLATE).after(ItemGroups.COMBAT, PIRATE_HAT).build();
    public static final ArmorItem PIRATE_PANTS = armorItem("pirate_pants", PIRATE, Type.LEGGINGS)  .after(ItemGroups.COMBAT, PIRATE_SHIRT).build();
    public static final ArmorItem PIRATE_BOOTS = armorItem("pirate_boots", PIRATE, Type.BOOTS)     .after(ItemGroups.COMBAT, PIRATE_PANTS).build();
    
    // Honey: Craftable
    public static final ArmorItem HONEY_MASK       = armorItem("honey_mask",       HONEY, Type.HELMET)    .after(ItemGroups.COMBAT, PIRATE_BOOTS).build();
    public static final ArmorItem HONEY_CHESTPIECE = armorItem("honey_chestpiece", HONEY, Type.CHESTPLATE).after(ItemGroups.COMBAT, HONEY_MASK).build();
    public static final ArmorItem HONEY_LEGPIECE   = armorItem("honey_legpiece",   HONEY, Type.LEGGINGS)  .after(ItemGroups.COMBAT, HONEY_CHESTPIECE).build();
    public static final ArmorItem HONEY_BOOTS      = item("honey_boots", RandomEffectArmorItem.of(HONEY, Type.BOOTS).requires(HONEY_MASK, HONEY_CHESTPIECE, HONEY_LEGPIECE)
                                                        .addGroup(StatusEffects.SATURATION).durationAndChance(1, 1f / 600f)
                                                    .build())                                                                .after(ItemGroups.COMBAT, HONEY_LEGPIECE).build();

    // Alchemist: Found in chests & Villager tradeable
    public static final ArmorItem ALCHEMIST_JACKET = armorItem("alchemist_jacket", ALCHEMIST, Type.CHESTPLATE).after(ItemGroups.COMBAT, PIRATE_BOOTS).build();
    public static final ArmorItem ALCHEMIST_PANTS  = armorItem("alchemist_pants", ALCHEMIST, Type.LEGGINGS)  .after(ItemGroups.COMBAT, ALCHEMIST_JACKET).build();
    public static final ArmorItem ALCHEMIST_BOOTS  = item("alchemist_boots", RandomEffectArmorItem.of(ALCHEMIST, Type.BOOTS).requires(ALCHEMIST_JACKET, ALCHEMIST_PANTS)
                                                        .addGroup(MagicStatusEffects.MAGIC_RESILIENCE).durationAndChance(600, 1f / (10_000f))
                                                        .copyGroupSingle(StatusEffects.ABSORPTION)
                                                        .copyGroupSingle(StatusEffects.REGENERATION)
                                                        .copyGroupSingle(StatusEffects.HASTE)
                                                        .copyGroupSingle(StatusEffects.NIGHT_VISION)
                                                        .copyGroupSingle(StatusEffects.INVISIBILITY)
                                                        .copyGroupSingle(StatusEffects.FIRE_RESISTANCE)
                                                        .copyGroupSingle(StatusEffects.WATER_BREATHING)
                                                        .addGroup(StatusEffects.NAUSEA).and(StatusEffects.POISON).durationAndChance(200, 1f / (20_000f))
                                                    .build())                                                                     .after(ItemGroups.COMBAT, ALCHEMIST_PANTS).build();
    
    // Elven: Craftable & Found in chests
    public static final Item ELVEN_STEEL           = item     ("elven_steel")                             .after(ItemGroups.INGREDIENTS, Items.GOLD_INGOT).build();
    public static final ArmorItem ELVEN_HELMET     = armorItem("elven_helmet",     ELVEN, Type.HELMET)    .after(ItemGroups.COMBAT, ALCHEMIST_BOOTS).build();
    public static final ArmorItem ELVEN_CHESTPLATE = armorItem("elven_chestplate", ELVEN, Type.CHESTPLATE).after(ItemGroups.COMBAT, ELVEN_HELMET).build();
    public static final ArmorItem ELVEN_LEGGINGS   = armorItem("elven_leggings",   ELVEN, Type.LEGGINGS)  .after(ItemGroups.COMBAT, ELVEN_CHESTPLATE).build();
    public static final ArmorItem ELVEN_BOOTS      = item("elven_boots", StaticEffectArmorItem.of(ELVEN, Type.BOOTS).requires(ELVEN_HELMET, ELVEN_CHESTPLATE, ELVEN_LEGGINGS)
                                                    .gives(StatusEffects.SPEED).build())                                      .after(ItemGroups.COMBAT, ELVEN_LEGGINGS).build();

    // Oceanic: Craftable & Villager tradeable TODO: Maybe rename to 'Diving'?
    public static final ArmorItem OCEANIC_MASK     = item("oceanic_mask", StaticEffectArmorItem.of(OCEANIC, Type.HELMET).gives(StatusEffects.WATER_BREATHING).build())
                                                                                                                                       .after(ItemGroups.COMBAT, Items.CHAINMAIL_BOOTS).build();
    public static final ArmorItem OCEANIC_SUIT     = armorItem("oceanic_suit",     OCEANIC, Type.CHESTPLATE).after(ItemGroups.COMBAT, OCEANIC_MASK).build();
    public static final ArmorItem OCEANIC_LEGGINGS = armorItem("oceanic_leggings", OCEANIC, Type.LEGGINGS)  .after(ItemGroups.COMBAT, OCEANIC_SUIT).build();
    public static final ArmorItem OCEANIC_SHOES    = armorItem("oceanic_shoes",    OCEANIC, Type.BOOTS)     .after(ItemGroups.COMBAT, OCEANIC_LEGGINGS).build();

    // Desert: Craftable
    public static final ArmorItem DESERT_HELMET     = armorItem("desert_helmet",     DESERT, Type.HELMET)    .after(ItemGroups.COMBAT, OCEANIC_SHOES).build();
    public static final ArmorItem DESERT_CHESTPLATE = armorItem("desert_chestplate", DESERT, Type.CHESTPLATE).after(ItemGroups.COMBAT, DESERT_HELMET).build();
    public static final ArmorItem DESERT_LEGGINGS   = armorItem("desert_leggings",   DESERT, Type.LEGGINGS)  .after(ItemGroups.COMBAT, DESERT_CHESTPLATE).build();
    public static final ArmorItem DESERT_BOOTS      = item("desert_boots", StaticEffectArmorItem.of(DESERT, Type.BOOTS).requires(DESERT_HELMET, DESERT_CHESTPLATE, DESERT_LEGGINGS)
                                                    .gives(StatusEffects.STRENGTH).build())                                       .after(ItemGroups.COMBAT, DESERT_LEGGINGS).build();
    
    // Lunar: Craftable & Found in chests
    public static final ArmorItem LUNAR_HELMET     = armorItem("lunar_helmet",     LUNAR, Type.HELMET)    .after(ItemGroups.COMBAT, DESERT_BOOTS).build();
    public static final ArmorItem LUNAR_CHESTPLATE = armorItem("lunar_chestplate", LUNAR, Type.CHESTPLATE).after(ItemGroups.COMBAT, LUNAR_HELMET).build();
    public static final ArmorItem LUNAR_LEGGINGS   = armorItem("lunar_leggings",   LUNAR, Type.LEGGINGS)  .after(ItemGroups.COMBAT, LUNAR_CHESTPLATE).build();
    public static final ArmorItem LUNAR_BOOTS      = item("lunar_boots", StaticEffectArmorItem.of(LUNAR, Type.BOOTS).requires(LUNAR_HELMET, LUNAR_CHESTPLATE, LUNAR_LEGGINGS)
                                                    .gives(StatusEffects.NIGHT_VISION).build())                                   .after(ItemGroups.COMBAT, LUNAR_LEGGINGS).build();
    
    // Phantom: Found in chests
    public static final ArmorItem PHANTOM_MASK       = armorItem("phantom_mask",       PHANTOM, Type.HELMET)    .after(ItemGroups.COMBAT, LUNAR_BOOTS).build();
    public static final ArmorItem PHANTOM_CHESTPLATE = armorItem("phantom_chestplate", PHANTOM, Type.CHESTPLATE).after(ItemGroups.COMBAT, PHANTOM_MASK).build();
    public static final ArmorItem PHANTOM_LEGGINGS   = item("phantom_leggings", StaticEffectArmorItem.of(PHANTOM, Type.LEGGINGS).requires(PHANTOM_MASK, PHANTOM_CHESTPLATE)
                                                    .gives(StatusEffects.SLOW_FALLING).build())                                       .after(ItemGroups.COMBAT, PHANTOM_CHESTPLATE).build();

    // Divine: Found in chests
    public static final ArmorItem DIVINE_HELMET     = armorItem("divine_helmet",     DIVINE, Type.HELMET)    .after(ItemGroups.COMBAT, PHANTOM_LEGGINGS).build();
    public static final ArmorItem DIVINE_CHESTPLATE = armorItem("divine_chestplate", DIVINE, Type.CHESTPLATE).after(ItemGroups.COMBAT, DIVINE_HELMET).build();
    public static final ArmorItem DIVINE_LEGGINGS   = armorItem("divine_leggings",   DIVINE, Type.LEGGINGS)  .after(ItemGroups.COMBAT, DIVINE_CHESTPLATE).build();
    public static final ArmorItem DIVINE_BOOTS      = item("divine_boots", StaticEffectArmorItem.of(DIVINE, Type.BOOTS).requires(DIVINE_HELMET, DIVINE_CHESTPLATE, DIVINE_LEGGINGS)
                                                    .gives(StatusEffects.RESISTANCE).build()).after(ItemGroups.COMBAT, DIVINE_LEGGINGS).build();
    
    // Necrotic: Craftable & Found in chests
    public static final ArmorItem NECROTIC_MASK       = armorItem("necrotic_mask",       NECROTIC, Type.HELMET)    .after(ItemGroups.COMBAT, DIVINE_BOOTS).build();
    public static final ArmorItem NECROTIC_CHESTPLATE = armorItem("necrotic_chestplate", NECROTIC, Type.CHESTPLATE).after(ItemGroups.COMBAT, NECROTIC_MASK).build();
    public static final ArmorItem NECROTIC_LEGGINGS   = armorItem("necrotic_leggings",   NECROTIC, Type.LEGGINGS)  .after(ItemGroups.COMBAT, NECROTIC_CHESTPLATE).build();
    public static final ArmorItem NECROTIC_BOOTS      = item("necrotic_boots", StaticEffectArmorItem.of(NECROTIC, Type.BOOTS).requires(NECROTIC_MASK, NECROTIC_CHESTPLATE, NECROTIC_LEGGINGS)
                                                    .gives(StatusEffects.STRENGTH, 1).build())                                            .after(ItemGroups.COMBAT, NECROTIC_LEGGINGS).build();

    // Living: Craftable
    public static final ArmorItem LIVING_MASK     = armorItem("living_mask",     LIVING, Type.HELMET)    .after(ItemGroups.COMBAT, NECROTIC_BOOTS).build();
    public static final ArmorItem LIVING_SUIT     = armorItem("living_suit",     LIVING, Type.CHESTPLATE).after(ItemGroups.COMBAT, LIVING_MASK).build();
    public static final ArmorItem LIVING_LEGGINGS = armorItem("living_leggings", LIVING, Type.LEGGINGS)  .after(ItemGroups.COMBAT, LIVING_SUIT).build();
    public static final ArmorItem LIVING_BOOTS    = item("living_boots", RandomEffectArmorItem.of(LIVING, Type.BOOTS).requires(LIVING_MASK, LIVING_SUIT, LIVING_LEGGINGS)
                                                        .addGroup(StatusEffects.REGENERATION).durationAndChance(200, 1f / 100f)
                                                    .build()).after(ItemGroups.COMBAT, LIVING_LEGGINGS).build();

    // Shadow: Found in chests
    public static final ArmorItem SHADOW_MASK       = armorItem("shadow_mask",       SHADOW, Type.HELMET)    .after(ItemGroups.COMBAT, Items.IRON_BOOTS).build();
    public static final ArmorItem SHADOW_CHESTPIECE = armorItem("shadow_chestpiece", SHADOW, Type.CHESTPLATE).after(ItemGroups.COMBAT, SHADOW_MASK).build();
    public static final ArmorItem SHADOW_LEGPIECE   = item("shadow_legpiece", StaticEffectArmorItem.of(SHADOW, Type.LEGGINGS).requires(SHADOW_MASK, SHADOW_CHESTPIECE)
                                                    .gives(StatusEffects.INVISIBILITY).build())                                   .after(ItemGroups.COMBAT, SHADOW_CHESTPIECE).build();

    // Angelic: Found in chests
    public static final ArmorItem ANGELIC_CHESTPLATE = armorItem("angelic_chestplate", ANGELIC, Type.CHESTPLATE).after(ItemGroups.COMBAT, SHADOW_LEGPIECE).build();
    public static final ArmorItem ANGELIC_LEGGINGS   = item("angelic_leggings", StaticEffectArmorItem.of(ANGELIC, Type.LEGGINGS).requires(ANGELIC_CHESTPLATE)
                                                    .gives(StatusEffects.SLOW_FALLING, 1).build())                                    .after(ItemGroups.COMBAT, ANGELIC_CHESTPLATE).build();

    // Deep Sea: Craftable & Found in chests
    public static final ArmorItem DEEP_SEA_MASK       = item("deep_sea_mask", StaticEffectArmorItem.of(DEEP_SEA, Type.HELMET).gives(StatusEffects.WATER_BREATHING).build())
                                                                                                                                          .after(ItemGroups.COMBAT, ANGELIC_LEGGINGS).build();
    public static final ArmorItem DEEP_SEA_CHESTPLATE = armorItem("deep_sea_chestplate", DEEP_SEA, Type.CHESTPLATE).after(ItemGroups.COMBAT, DEEP_SEA_MASK).build();
    public static final ArmorItem DEEP_SEA_LEGGINGS   = armorItem("deep_sea_leggings",   DEEP_SEA, Type.LEGGINGS)  .after(ItemGroups.COMBAT, DEEP_SEA_CHESTPLATE).build();
    public static final ArmorItem DEEP_SEA_BOOTS      = item("deep_sea_boots", StaticEffectArmorItem.of(DEEP_SEA, Type.BOOTS).requires(DEEP_SEA_MASK, DEEP_SEA_CHESTPLATE, DEEP_SEA_LEGGINGS)
                                                    .gives(StatusEffects.CONDUIT_POWER).build())                                          .after(ItemGroups.COMBAT, DEEP_SEA_LEGGINGS).build();

    // Magma: Found in chests
    public static final ArmorItem MAGMA_CHESTPLATE = armorItem("magma_chestplate", MAGMA, Type.CHESTPLATE).after(ItemGroups.COMBAT, Items.GOLDEN_BOOTS).build();
    public static final ArmorItem MAGMA_LEGGINGS   = armorItem("magma_leggings",   MAGMA, Type.LEGGINGS)  .after(ItemGroups.COMBAT, MAGMA_CHESTPLATE).build();
    public static final ArmorItem MAGMA_BOOTS      = item("magma_boots", StaticEffectArmorItem.of(MAGMA, Type.BOOTS).requires(MAGMA_CHESTPLATE, MAGMA_LEGGINGS)
                                                    .gives(StatusEffects.FIRE_RESISTANCE).build())                            .after(ItemGroups.COMBAT, MAGMA_LEGGINGS).build();

    // Guardian: Craftable
    public static final ArmorItem GUARDIAN_HELMET     = armorItem("guardian_helmet",     GUARDIAN, Type.HELMET)    .after(ItemGroups.COMBAT, MAGMA_BOOTS).build();
    public static final ArmorItem GUARDIAN_CHESTPLATE = armorItem("guardian_chestplate", GUARDIAN, Type.CHESTPLATE).after(ItemGroups.COMBAT, GUARDIAN_HELMET).build();
    public static final ArmorItem GUARDIAN_LEGGINGS   = armorItem("guardian_leggings",   GUARDIAN, Type.LEGGINGS)  .after(ItemGroups.COMBAT, GUARDIAN_CHESTPLATE).build();
    public static final ArmorItem GUARDIAN_BOOTS      = item("guardian_boots", StaticEffectArmorItem.of(GUARDIAN, Type.BOOTS).requires(GUARDIAN_HELMET, GUARDIAN_CHESTPLATE, GUARDIAN_LEGGINGS)
                                                    .gives(StatusEffects.RESISTANCE, 2).build())                                          .after(ItemGroups.COMBAT, GUARDIAN_LEGGINGS).build();
    //#endregion

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}

}
