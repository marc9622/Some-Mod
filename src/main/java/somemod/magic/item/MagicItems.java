package somemod.magic.item;

import net.minecraft.item.ArmorItem.Type;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.util.Rarity;

import somemod.magic.block.MagicBlocks;
import somemod.magic.entity.effect.MagicStatusEffects;

import static somemod.magic.item.MagicArmorMaterials.*;
import static somemod.utils.ItemBuilder.*;

public class MagicItems {
    
    public static final BlockItem ENCHANTED_BOOKSHELF =
        defaultBlockItem("enchanted_bookshelf", MagicBlocks.ENCHANTED_BOOKSHELF)
        .settings(s -> s.rarity(Rarity.UNCOMMON)).groupAfter(ItemGroups.FUNCTIONAL, Items.BOOKSHELF).build(); // Or maybe next to enchanting table instead?
    
    public static final BlockItem OBSIDIAN_ENCHANTED_BOOKSHELF =
        defaultBlockItem("obsidian_enchanted_bookshelf", MagicBlocks.OBSIDIAN_ENCHANTED_BOOKSHELF)
        .settings(s -> s.rarity(Rarity.RARE)).groupAfter(ItemGroups.FUNCTIONAL, ENCHANTED_BOOKSHELF).build(); // Or maybe next to enchanting table instead?
    
    public static final BlockItem FORGOTTEN_CHEST =
        defaultBlockItem("forgotten_chest", MagicBlocks.FORGOTTEN_CHEST)
        .settings(s -> s.rarity(Rarity.COMMON)).groupAfter(ItemGroups.FUNCTIONAL, Items.CHEST).build();

    //#region ARMOR
    // Arcane: Found in chests
    public static final ArmorItem ARCANE_HAT   = defaultArmorItem("arcane_hat",   ARCANE, Type.HELMET).groupAfter(ItemGroups.COMBAT, Items.LEATHER_BOOTS).build();
    public static final ArmorItem ARCANE_ROBE  = defaultArmorItem("arcane_robe",  ARCANE, Type.CHESTPLATE).groupAfter(ItemGroups.COMBAT, ARCANE_HAT).build();
    public static final ArmorItem ARCANE_PANTS = fromItem("arcane_pants", s -> new EffectArmorItem(ARCANE, Type.HELMET, s, MagicStatusEffects.MAGIC_RESILIENCE, 0, ARCANE_HAT, ARCANE_ROBE))
                                                                                                            .groupAfter(ItemGroups.COMBAT, ARCANE_ROBE).build();
    
    // Pirate: Craftable & Found in chests
    public static final ArmorItem PIRATE_HAT   = defaultArmorItem("pirate_hat",   PIRATE, Type.HELMET).groupAfter(ItemGroups.COMBAT, ARCANE_PANTS).build();
    public static final ArmorItem PIRATE_SHIRT = defaultArmorItem("pirate_shirt", PIRATE, Type.CHESTPLATE).groupAfter(ItemGroups.COMBAT, PIRATE_HAT).build();
    public static final ArmorItem PIRATE_PANTS = defaultArmorItem("pirate_pants", PIRATE, Type.LEGGINGS).groupAfter(ItemGroups.COMBAT, PIRATE_SHIRT).build();
    public static final ArmorItem PIRATE_BOOTS = defaultArmorItem("pirate_boots", PIRATE, Type.BOOTS).groupAfter(ItemGroups.COMBAT, PIRATE_PANTS).build();
    
    // Honey: Craftable
    public static final ArmorItem HONEY_MASK       = defaultArmorItem("honey_mask",       HONEY, Type.HELMET).groupAfter(ItemGroups.COMBAT, PIRATE_BOOTS).build();
    public static final ArmorItem HONEY_CHESTPIECE = defaultArmorItem("honey_chestpiece", HONEY, Type.CHESTPLATE).groupAfter(ItemGroups.COMBAT, HONEY_MASK).build();
    public static final ArmorItem HONEY_LEGPIECE   = defaultArmorItem("honey_legpiece",   HONEY, Type.LEGGINGS).groupAfter(ItemGroups.COMBAT, HONEY_CHESTPIECE).build();
    public static final ArmorItem HONEY_BOOTS = fromItem("honey_boots", s -> new EffectArmorItem(HONEY, Type.BOOTS, s, StatusEffects.ABSORPTION, 1, HONEY_MASK, HONEY_CHESTPIECE, HONEY_LEGPIECE))
                                                                                                            .groupAfter(ItemGroups.COMBAT, HONEY_LEGPIECE).build();

    // Alchemist: Found in chests TODO: Use RandomEffectArmorItem
    public static final ArmorItem ALCHEMIST_JACKET = defaultArmorItem("alchemist_jacket", ALCHEMIST, Type.CHESTPLATE).groupAfter(ItemGroups.COMBAT, PIRATE_BOOTS).build();
    public static final ArmorItem ALCHEMIST_PANTS  = defaultArmorItem("alchemist_pants",  ALCHEMIST, Type.LEGGINGS).groupAfter(ItemGroups.COMBAT, ALCHEMIST_JACKET).build();
    public static final ArmorItem ALCHEMIST_BOOTS  = defaultArmorItem("alchemist_boots",  ALCHEMIST, Type.BOOTS).groupAfter(ItemGroups.COMBAT, ALCHEMIST_PANTS).build();
    
    // Elven: Craftable & Found in chests
    public static final ArmorItem ELVEN_HELMET     = defaultArmorItem("elven_helmet",     ELVEN, Type.HELMET).groupAfter(ItemGroups.COMBAT, ALCHEMIST_BOOTS).build();
    public static final ArmorItem ELVEN_CHESTPLATE = defaultArmorItem("elven_chestplate", ELVEN, Type.CHESTPLATE).groupAfter(ItemGroups.COMBAT, ELVEN_HELMET).build();
    public static final ArmorItem ELVEN_LEGGINGS   = defaultArmorItem("elven_leggings",   ELVEN, Type.LEGGINGS).groupAfter(ItemGroups.COMBAT, ELVEN_CHESTPLATE).build();
    public static final ArmorItem ELVEN_BOOTS = fromItem("elven_boots", s -> new EffectArmorItem(ELVEN, Type.BOOTS, s, StatusEffects.SPEED, 0, ELVEN_HELMET, ELVEN_CHESTPLATE, ELVEN_LEGGINGS))
                                                                                                            .groupAfter(ItemGroups.COMBAT, ELVEN_LEGGINGS).build();
    public static final Item ELVEN_STEEL           = defaultItem     ("elven_steel")                        .groupAfter(ItemGroups.INGREDIENTS, Items.GOLD_INGOT).build();

    // Oceanic: Craftable TODO: Maybe rename to 'Diving'?
    public static final ArmorItem OCEANIC_MASK = fromItem("oceanic_mask", s -> new EffectArmorItem(OCEANIC, Type.HELMET, s, StatusEffects.WATER_BREATHING, 0))
                                                                                                            .groupAfter(ItemGroups.COMBAT, Items.CHAINMAIL_BOOTS).build();
    public static final ArmorItem OCEANIC_SUIT     = defaultArmorItem("oceanic_suit",     OCEANIC, Type.CHESTPLATE).groupAfter(ItemGroups.COMBAT, OCEANIC_MASK).build();
    public static final ArmorItem OCEANIC_LEGGINGS = defaultArmorItem("oceanic_leggings", OCEANIC, Type.LEGGINGS).groupAfter(ItemGroups.COMBAT, OCEANIC_SUIT).build();
    public static final ArmorItem OCEANIC_SHOES    = defaultArmorItem("oceanic_shoes",    OCEANIC, Type.BOOTS).groupAfter(ItemGroups.COMBAT, OCEANIC_LEGGINGS).build();

    // Desert: Craftable
    public static final ArmorItem DESERT_HELMET     = defaultArmorItem("desert_helmet",     DESERT, Type.HELMET).groupAfter(ItemGroups.COMBAT, OCEANIC_SHOES).build();
    public static final ArmorItem DESERT_CHESTPLATE = defaultArmorItem("desert_chestplate", DESERT, Type.CHESTPLATE).groupAfter(ItemGroups.COMBAT, DESERT_HELMET).build();
    public static final ArmorItem DESERT_LEGGINGS   = defaultArmorItem("desert_leggings",   DESERT, Type.LEGGINGS).groupAfter(ItemGroups.COMBAT, DESERT_CHESTPLATE).build();
    public static final ArmorItem DESERT_BOOTS = fromItem("desert_boots", s -> new EffectArmorItem(DESERT, Type.BOOTS, s, StatusEffects.STRENGTH, 0, DESERT_HELMET, DESERT_CHESTPLATE, DESERT_LEGGINGS))
                                                                                                            .groupAfter(ItemGroups.COMBAT, DESERT_LEGGINGS).build();
    
    // Lunar: Craftable & Found in chests
    public static final ArmorItem LUNAR_HELMET     = defaultArmorItem("lunar_helmet",     LUNAR, Type.HELMET).groupAfter(ItemGroups.COMBAT, DESERT_BOOTS).build();
    public static final ArmorItem LUNAR_CHESTPLATE = defaultArmorItem("lunar_chestplate", LUNAR, Type.CHESTPLATE).groupAfter(ItemGroups.COMBAT, LUNAR_HELMET).build();
    public static final ArmorItem LUNAR_LEGGINGS   = defaultArmorItem("lunar_leggings",   LUNAR, Type.LEGGINGS).groupAfter(ItemGroups.COMBAT, LUNAR_CHESTPLATE).build();
    public static final ArmorItem LUNAR_BOOTS = fromItem("lunar_boots", s -> new EffectArmorItem(LUNAR, Type.BOOTS, s, StatusEffects.NIGHT_VISION, 0, LUNAR_HELMET, LUNAR_CHESTPLATE, LUNAR_LEGGINGS))
                                                                                                            .groupAfter(ItemGroups.COMBAT, LUNAR_LEGGINGS).build();
    
    // Phantom: Found in chests
    public static final ArmorItem PHANTOM_MASK       = defaultArmorItem("phantom_mask",       PHANTOM, Type.HELMET).groupAfter(ItemGroups.COMBAT, LUNAR_BOOTS).build();
    public static final ArmorItem PHANTOM_CHESTPLATE = defaultArmorItem("phantom_chestplate", PHANTOM, Type.CHESTPLATE).groupAfter(ItemGroups.COMBAT, PHANTOM_MASK).build();
    public static final ArmorItem PHANTOM_LEGGINGS = fromItem("phantom_leggings", s -> new EffectArmorItem(PHANTOM, Type.LEGGINGS, s, StatusEffects.SLOW_FALLING, 0, PHANTOM_MASK, PHANTOM_CHESTPLATE))
                                                                                                            .groupAfter(ItemGroups.COMBAT, PHANTOM_CHESTPLATE).build();

    // Divine: Found in chests
    public static final ArmorItem DIVINE_HELMET     = defaultArmorItem("divine_helmet",     DIVINE, Type.HELMET).groupAfter(ItemGroups.COMBAT, PHANTOM_LEGGINGS).build();
    public static final ArmorItem DIVINE_CHESTPLATE = defaultArmorItem("divine_chestplate", DIVINE, Type.CHESTPLATE).groupAfter(ItemGroups.COMBAT, DIVINE_HELMET).build();
    public static final ArmorItem DIVINE_LEGGINGS   = defaultArmorItem("divine_leggings",   DIVINE, Type.LEGGINGS).groupAfter(ItemGroups.COMBAT, DIVINE_CHESTPLATE).build();
    public static final ArmorItem DIVINE_BOOTS = fromItem("divine_boots", s -> new EffectArmorItem(DIVINE, Type.BOOTS, s, StatusEffects.RESISTANCE, 0, DIVINE_HELMET, DIVINE_CHESTPLATE, DIVINE_LEGGINGS))
                                                                                                            .groupAfter(ItemGroups.COMBAT, DIVINE_LEGGINGS).build();
    
    // Necrotic: Craftable & Found in chests
    public static final ArmorItem NECROTIC_MASK       = defaultArmorItem("necrotic_mask",       NECROTIC, Type.HELMET).groupAfter(ItemGroups.COMBAT, DIVINE_BOOTS).build();
    public static final ArmorItem NECROTIC_CHESTPLATE = defaultArmorItem("necrotic_chestplate", NECROTIC, Type.CHESTPLATE).groupAfter(ItemGroups.COMBAT, NECROTIC_MASK).build();
    public static final ArmorItem NECROTIC_LEGGINGS   = defaultArmorItem("necrotic_leggings",   NECROTIC, Type.LEGGINGS).groupAfter(ItemGroups.COMBAT, NECROTIC_CHESTPLATE).build();
    public static final ArmorItem NECROTIC_BOOTS = fromItem("necrotic_boots", s -> new EffectArmorItem(NECROTIC, Type.BOOTS, s, StatusEffects.STRENGTH, 1, NECROTIC_MASK, NECROTIC_CHESTPLATE, NECROTIC_LEGGINGS))
                                                                                                            .groupAfter(ItemGroups.COMBAT, NECROTIC_LEGGINGS).build();

    // Living: Craftable
    public static final ArmorItem LIVING_MASK     = defaultArmorItem("living_mask",     LIVING, Type.HELMET).groupAfter(ItemGroups.COMBAT, NECROTIC_BOOTS).build();
    public static final ArmorItem LIVING_SUIT     = defaultArmorItem("living_suit",     LIVING, Type.CHESTPLATE).groupAfter(ItemGroups.COMBAT, LIVING_MASK).build();
    public static final ArmorItem LIVING_LEGGINGS = defaultArmorItem("living_leggings", LIVING, Type.LEGGINGS).groupAfter(ItemGroups.COMBAT, LIVING_SUIT).build();
    public static final ArmorItem LIVING_BOOTS = fromItem("living_boots", s -> new EffectArmorItem(LIVING, Type.BOOTS, s, StatusEffects.REGENERATION, 0, LIVING_MASK, LIVING_SUIT, LIVING_LEGGINGS))
                                                                                                            .groupAfter(ItemGroups.COMBAT, LIVING_LEGGINGS).build();

    // Shadow: Found in chests
    public static final ArmorItem SHADOW_MASK       = defaultArmorItem("shadow_mask",       SHADOW, Type.HELMET).groupAfter(ItemGroups.COMBAT, Items.IRON_BOOTS).build();
    public static final ArmorItem SHADOW_CHESTPIECE = defaultArmorItem("shadow_chestpiece", SHADOW, Type.CHESTPLATE).groupAfter(ItemGroups.COMBAT, SHADOW_MASK).build();
    public static final ArmorItem SHADOW_LEGPIECE   = fromItem("shadow_legpiece", s -> new EffectArmorItem(SHADOW, Type.LEGGINGS, s, StatusEffects.INVISIBILITY, 0, SHADOW_MASK, SHADOW_CHESTPIECE))
                                                                                                            .groupAfter(ItemGroups.COMBAT, SHADOW_CHESTPIECE).build();

    // Angelic: Found in chests
    public static final ArmorItem ANGELIC_CHESTPLATE = defaultArmorItem("angelic_chestplate", ANGELIC, Type.CHESTPLATE).groupAfter(ItemGroups.COMBAT, SHADOW_LEGPIECE).build();
    public static final ArmorItem ANGELIC_LEGGINGS = fromItem("angelic_leggings", s -> new EffectArmorItem(ANGELIC, Type.LEGGINGS, s, StatusEffects.SLOW_FALLING, 1, ANGELIC_CHESTPLATE))
                                                                                                            .groupAfter(ItemGroups.COMBAT, ANGELIC_CHESTPLATE).build();

    // Deep Sea: Craftable & Found in chests
    public static final ArmorItem DEEP_SEA_MASK       = defaultArmorItem("deep_sea_mask",       DEEP_SEA, Type.HELMET).groupAfter(ItemGroups.COMBAT, ANGELIC_LEGGINGS).build();
    public static final ArmorItem DEEP_SEA_CHESTPLATE = defaultArmorItem("deep_sea_chestplate", DEEP_SEA, Type.CHESTPLATE).groupAfter(ItemGroups.COMBAT, DEEP_SEA_MASK).build();
    public static final ArmorItem DEEP_SEA_LEGGINGS   = defaultArmorItem("deep_sea_leggings",   DEEP_SEA, Type.LEGGINGS).groupAfter(ItemGroups.COMBAT, DEEP_SEA_CHESTPLATE).build();
    public static final ArmorItem DEEP_SEA_BOOTS      = fromItem("deep_sea_boots", s -> new EffectArmorItem(DEEP_SEA, Type.BOOTS, s, StatusEffects.WATER_BREATHING, 0, DEEP_SEA_MASK, DEEP_SEA_CHESTPLATE, DEEP_SEA_LEGGINGS))
                                                                                                            .groupAfter(ItemGroups.COMBAT, DEEP_SEA_LEGGINGS).build();

    // Magma: Found in chests
    public static final ArmorItem MAGMA_CHESTPLATE = defaultArmorItem("magma_chestplate", MAGMA, Type.CHESTPLATE).groupAfter(ItemGroups.COMBAT, Items.GOLDEN_BOOTS).build();
    public static final ArmorItem MAGMA_LEGGINGS   = defaultArmorItem("magma_leggings",   MAGMA, Type.LEGGINGS).groupAfter(ItemGroups.COMBAT, MAGMA_CHESTPLATE).build();
    public static final ArmorItem MAGMA_BOOTS = fromItem("magma_boots", s -> new EffectArmorItem(MAGMA, Type.BOOTS, s, StatusEffects.FIRE_RESISTANCE, 0, MAGMA_CHESTPLATE, MAGMA_LEGGINGS))
                                                                                                            .groupAfter(ItemGroups.COMBAT, MAGMA_LEGGINGS).build();

    // Guardian: Craftable
    public static final ArmorItem GUARDIAN_HELMET     = defaultArmorItem("guardian_helmet",     GUARDIAN, Type.HELMET).groupAfter(ItemGroups.COMBAT, MAGMA_BOOTS).build();
    public static final ArmorItem GUARDIAN_CHESTPLATE = defaultArmorItem("guardian_chestplate", GUARDIAN, Type.CHESTPLATE).groupAfter(ItemGroups.COMBAT, GUARDIAN_HELMET).build();
    public static final ArmorItem GUARDIAN_LEGGINGS   = defaultArmorItem("guardian_leggings",   GUARDIAN, Type.LEGGINGS).groupAfter(ItemGroups.COMBAT, GUARDIAN_CHESTPLATE).build();
    public static final ArmorItem GUARDIAN_BOOTS = fromItem("guardian_boots", s -> new EffectArmorItem(GUARDIAN, Type.BOOTS, s, StatusEffects.HEALTH_BOOST, 5, GUARDIAN_HELMET, GUARDIAN_CHESTPLATE, GUARDIAN_LEGGINGS))
                                                                                                            .groupAfter(ItemGroups.COMBAT, GUARDIAN_LEGGINGS).build();
    //#endregion

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}

}
