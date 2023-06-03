package somemod.magic.item;

import net.minecraft.item.ArmorItem.Type;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.util.Rarity;

import static somemod.magic.block.MagicBlocks.*;
import static somemod.magic.item.MagicArmorMaterials.*;
import static somemod.utils.ItemBuilder.*;

public class MagicItems {
    
    public static final Item ENCHANTED_BOOKSHELF_ITEM =
        defaultBlockItem("enchanted_bookshelf", ENCHANTED_BOOKSHELF)
        .modifySettings(s -> s.rarity(Rarity.UNCOMMON)).groupAfter(ItemGroups.FUNCTIONAL, Items.BOOKSHELF).build(); // Or maybe next to enchanting table instead?
    
    public static final Item OBSIDIAN_ENCHANTED_BOOKSHELF_ITEM =
        defaultBlockItem("obsidian_enchanted_bookshelf", OBSIDIAN_ENCHANTED_BOOKSHELF)
        .modifySettings(s -> s.rarity(Rarity.RARE)).groupAfter(ItemGroups.FUNCTIONAL, ENCHANTED_BOOKSHELF_ITEM).build(); // Or maybe next to enchanting table instead?
    
    public static final Item FORGOTTEN_CHEST_ITEM =
        defaultBlockItem("forgotten_chest", FORGOTTEN_CHEST)
        .modifySettings(s -> s.rarity(Rarity.COMMON)).groupBefore(ItemGroups.FUNCTIONAL, Items.ENDER_CHEST).build();

    //#region ARMOR
    // Arcane: Uncraftable, found in chests
    public static final Item ARCANE_HAT   = defaultArmorItem("arcane_hat",   ARCANE, Type.HELMET).groupAfter(ItemGroups.COMBAT, Items.LEATHER_BOOTS).build();
    public static final Item ARCANE_ROBE  = defaultArmorItem("arcane_robe",  ARCANE, Type.CHESTPLATE).groupAfter(ItemGroups.COMBAT, ARCANE_HAT).build();
    public static final Item ARCANE_PANTS = defaultArmorItem("arcane_pants", ARCANE, Type.LEGGINGS).groupAfter(ItemGroups.COMBAT, ARCANE_ROBE).build();
    
    // Pirate: Craftable, found in chests
    public static final Item PIRATE_HAT   = defaultArmorItem("pirate_hat",   PIRATE, Type.HELMET).groupAfter(ItemGroups.COMBAT, ARCANE_PANTS).build();
    public static final Item PIRATE_SHIRT = defaultArmorItem("pirate_shirt", PIRATE, Type.CHESTPLATE).groupAfter(ItemGroups.COMBAT, PIRATE_HAT).build();
    public static final Item PIRATE_PANTS = defaultArmorItem("pirate_pants", PIRATE, Type.LEGGINGS).groupAfter(ItemGroups.COMBAT, PIRATE_SHIRT).build();
    public static final Item PIRATE_BOOTS = defaultArmorItem("pirate_boots", PIRATE, Type.BOOTS).groupAfter(ItemGroups.COMBAT, PIRATE_PANTS).build();
    
    // Honey: Craftable
    public static final Item HONEY_MASK       = defaultArmorItem("honey_mask",       HONEY, Type.HELMET).groupAfter(ItemGroups.COMBAT, PIRATE_BOOTS).build();
    public static final Item HONEY_CHESTPIECE = defaultArmorItem("honey_chestpiece", HONEY, Type.CHESTPLATE).groupAfter(ItemGroups.COMBAT, HONEY_MASK).build();
    public static final Item HONEY_LEGPIECE   = defaultArmorItem("honey_legpiece",   HONEY, Type.LEGGINGS).groupAfter(ItemGroups.COMBAT, HONEY_CHESTPIECE).build();
    public static final Item HONEY_BOOTS      = defaultArmorItem("honey_boots",      HONEY, Type.BOOTS).groupAfter(ItemGroups.COMBAT, HONEY_LEGPIECE).build();

    // Alchemist: Found in chests
    public static final Item ALCHEMIST_JACKET = defaultArmorItem("alchemist_jacket", ALCHEMIST, Type.CHESTPLATE).groupAfter(ItemGroups.COMBAT, PIRATE_BOOTS).build();
    public static final Item ALCHEMIST_PANTS  = defaultArmorItem("alchemist_pants",  ALCHEMIST, Type.LEGGINGS).groupAfter(ItemGroups.COMBAT, ALCHEMIST_JACKET).build();
    public static final Item ALCHEMIST_BOOTS  = defaultArmorItem("alchemist_boots",  ALCHEMIST, Type.BOOTS).groupAfter(ItemGroups.COMBAT, ALCHEMIST_PANTS).build();
    
    // Elven: Craftable, found in chests
    public static final Item ELVEN_HELMET     = defaultArmorItem("elven_helmet",     ELVEN, Type.HELMET).groupAfter(ItemGroups.COMBAT, ALCHEMIST_BOOTS).build();
    public static final Item ELVEN_CHESTPLATE = defaultArmorItem("elven_chestplate", ELVEN, Type.CHESTPLATE).groupAfter(ItemGroups.COMBAT, ELVEN_HELMET).build();
    public static final Item ELVEN_LEGGINGS   = defaultArmorItem("elven_leggings",   ELVEN, Type.LEGGINGS).groupAfter(ItemGroups.COMBAT, ELVEN_CHESTPLATE).build();
    public static final Item ELVEN_BOOTS      = defaultArmorItem("elven_boots",      ELVEN, Type.BOOTS).groupAfter(ItemGroups.COMBAT, ELVEN_LEGGINGS).build();
    public static final Item ELVEN_STEEL      = defaultItem("elven_steel")                                     .groupAfter(ItemGroups.INGREDIENTS, Items.GOLD_INGOT).build();

    // Oceanic: Craftable
    public static final Item OCEANIC_MASK     = defaultArmorItem("oceanic_mask",     OCEANIC, Type.HELMET).groupAfter(ItemGroups.COMBAT, Items.CHAINMAIL_BOOTS).build();
    public static final Item OCEANIC_SUIT     = defaultArmorItem("oceanic_suit",     OCEANIC, Type.CHESTPLATE).groupAfter(ItemGroups.COMBAT, OCEANIC_MASK).build();
    public static final Item OCEANIC_LEGGINGS = defaultArmorItem("oceanic_leggings", OCEANIC, Type.LEGGINGS).groupAfter(ItemGroups.COMBAT, OCEANIC_SUIT).build();
    public static final Item OCEANIC_SHOES    = defaultArmorItem("oceanic_shoes",    OCEANIC, Type.BOOTS).groupAfter(ItemGroups.COMBAT, OCEANIC_LEGGINGS).build();

    // Desert: Craftable
    public static final Item DESERT_HELMET     = defaultArmorItem("desert_helmet",     DESERT, Type.HELMET).groupAfter(ItemGroups.COMBAT, OCEANIC_SHOES).build();
    public static final Item DESERT_CHESTPLATE = defaultArmorItem("desert_chestplate", DESERT, Type.CHESTPLATE).groupAfter(ItemGroups.COMBAT, DESERT_HELMET).build();
    public static final Item DESERT_LEGGINGS   = defaultArmorItem("desert_leggings",   DESERT, Type.LEGGINGS).groupAfter(ItemGroups.COMBAT, DESERT_CHESTPLATE).build();
    public static final Item DESERT_BOOTS      = defaultArmorItem("desert_boots",      DESERT, Type.BOOTS).groupAfter(ItemGroups.COMBAT, DESERT_LEGGINGS).build();
    
    // Lunar: Craftable, found in chests
    public static final Item LUNAR_HELMET     = defaultArmorItem("lunar_helmet",     LUNAR, Type.HELMET).groupAfter(ItemGroups.COMBAT, DESERT_BOOTS).build();
    public static final Item LUNAR_CHESTPLATE = defaultArmorItem("lunar_chestplate", LUNAR, Type.CHESTPLATE).groupAfter(ItemGroups.COMBAT, LUNAR_HELMET).build();
    public static final Item LUNAR_LEGGINGS   = defaultArmorItem("lunar_leggings",   LUNAR, Type.LEGGINGS).groupAfter(ItemGroups.COMBAT, LUNAR_CHESTPLATE).build();
    public static final Item LUNAR_BOOTS      = defaultArmorItem("lunar_boots",      LUNAR, Type.BOOTS).groupAfter(ItemGroups.COMBAT, LUNAR_LEGGINGS).build();
    
    // Phantom: found in chests
    public static final Item PHANTOM_MASK       = defaultArmorItem("phantom_mask",     PHANTOM, Type.HELMET).groupAfter(ItemGroups.COMBAT, LUNAR_BOOTS).build();
    public static final Item PHANTOM_CHESTPLATE = defaultArmorItem("phantom_chestplate", PHANTOM, Type.CHESTPLATE).groupAfter(ItemGroups.COMBAT, PHANTOM_MASK).build();
    public static final Item PHANTOM_LEGGINGS   = defaultArmorItem("phantom_leggings",   PHANTOM, Type.LEGGINGS).groupAfter(ItemGroups.COMBAT, PHANTOM_CHESTPLATE).build();

    // Divine: found in chests
    public static final Item DIVINE_HELMET     = defaultArmorItem("divine_helmet",     DIVINE, Type.HELMET).groupAfter(ItemGroups.COMBAT, PHANTOM_LEGGINGS).build();
    public static final Item DIVINE_CHESTPLATE = defaultArmorItem("divine_chestplate", DIVINE, Type.CHESTPLATE).groupAfter(ItemGroups.COMBAT, DIVINE_HELMET).build();
    public static final Item DIVINE_LEGGINGS   = defaultArmorItem("divine_leggings",   DIVINE, Type.LEGGINGS).groupAfter(ItemGroups.COMBAT, DIVINE_CHESTPLATE).build();
    public static final Item DIVINE_BOOTS      = defaultArmorItem("divine_boots",      DIVINE, Type.BOOTS).groupAfter(ItemGroups.COMBAT, DIVINE_LEGGINGS).build();
    
    // Necrotic: Craftable, found in chests
    public static final Item NECROTIC_MASK       = defaultArmorItem("necrotic_mask",       NECROTIC, Type.HELMET).groupAfter(ItemGroups.COMBAT, DIVINE_BOOTS).build();
    public static final Item NECROTIC_CHESTPLATE = defaultArmorItem("necrotic_chestplate", NECROTIC, Type.CHESTPLATE).groupAfter(ItemGroups.COMBAT, NECROTIC_MASK).build();
    public static final Item NECROTIC_LEGGINGS   = defaultArmorItem("necrotic_leggings",   NECROTIC, Type.LEGGINGS).groupAfter(ItemGroups.COMBAT, NECROTIC_CHESTPLATE).build();
    public static final Item NECROTIC_BOOTS      = defaultArmorItem("necrotic_boots",      NECROTIC, Type.BOOTS).groupAfter(ItemGroups.COMBAT, NECROTIC_LEGGINGS).build();

    // Living: Craftable
    public static final Item LIVING_MASK     = defaultArmorItem("living_mask",     LIVING, Type.HELMET).groupAfter(ItemGroups.COMBAT, NECROTIC_BOOTS).build();
    public static final Item LIVING_SUIT     = defaultArmorItem("living_suit",     LIVING, Type.CHESTPLATE).groupAfter(ItemGroups.COMBAT, LIVING_MASK).build();
    public static final Item LIVING_LEGGINGS = defaultArmorItem("living_leggings", LIVING, Type.LEGGINGS).groupAfter(ItemGroups.COMBAT, LIVING_SUIT).build();
    public static final Item LIVING_BOOTS    = defaultArmorItem("living_boots",    LIVING, Type.BOOTS).groupAfter(ItemGroups.COMBAT, LIVING_LEGGINGS).build();

    // Shadow: Found in chests
    public static final Item SHADOW_MASK       = defaultArmorItem("shadow_mask",       SHADOW, Type.HELMET).groupAfter(ItemGroups.COMBAT, Items.IRON_BOOTS).build();
    public static final Item SHADOW_CHESTPIECE = defaultArmorItem("shadow_chestpiece", SHADOW, Type.CHESTPLATE).groupAfter(ItemGroups.COMBAT, SHADOW_MASK).build();
    public static final Item SHADOW_LEGPIECE   = defaultArmorItem("shadow_legpiece",   SHADOW, Type.LEGGINGS).groupAfter(ItemGroups.COMBAT, SHADOW_CHESTPIECE).build();

    // Angelic: Found in chests
    public static final Item ANGELIC_CHESTPLATE = defaultArmorItem("angelic_chestplate", ANGELIC, Type.CHESTPLATE).groupAfter(ItemGroups.COMBAT, SHADOW_LEGPIECE).build();
    public static final Item ANGELIC_LEGGINGS   = defaultArmorItem("angelic_leggings",   ANGELIC, Type.LEGGINGS).groupAfter(ItemGroups.COMBAT, ANGELIC_CHESTPLATE).build();

    // Deep Sea: Craftable, found in chests
    public static final Item DEEP_SEA_MASK       = defaultArmorItem("deep_sea_mask",       DEEP_SEA, Type.HELMET).groupAfter(ItemGroups.COMBAT, ANGELIC_LEGGINGS).build();
    public static final Item DEEP_SEA_CHESTPLATE = defaultArmorItem("deep_sea_chestplate", DEEP_SEA, Type.CHESTPLATE).groupAfter(ItemGroups.COMBAT, DEEP_SEA_MASK).build();
    public static final Item DEEP_SEA_LEGGINGS   = defaultArmorItem("deep_sea_leggings",   DEEP_SEA, Type.LEGGINGS).groupAfter(ItemGroups.COMBAT, DEEP_SEA_CHESTPLATE).build();
    public static final Item DEEP_SEA_BOOTS      = defaultArmorItem("deep_sea_boots",      DEEP_SEA, Type.BOOTS).groupAfter(ItemGroups.COMBAT, DEEP_SEA_LEGGINGS).build();

    // Magma: Found in chests
    public static final Item MAGMA_CHESTPLATE = defaultArmorItem("magma_chestplate", MAGMA, Type.CHESTPLATE).groupAfter(ItemGroups.COMBAT, Items.GOLDEN_BOOTS).build();
    public static final Item MAGMA_LEGGINGS   = defaultArmorItem("magma_leggings",   MAGMA, Type.LEGGINGS).groupAfter(ItemGroups.COMBAT, MAGMA_CHESTPLATE).build();
    public static final Item MAGMA_BOOTS      = defaultArmorItem("magma_boots",      MAGMA, Type.BOOTS).groupAfter(ItemGroups.COMBAT, MAGMA_LEGGINGS).build();

    // Guardian: Craftable
    public static final Item GUARDIAN_HELMET     = defaultArmorItem("guardian_helmet",     GUARDIAN, Type.HELMET).groupAfter(ItemGroups.COMBAT, MAGMA_BOOTS).build();
    public static final Item GUARDIAN_CHESTPLATE = defaultArmorItem("guardian_chestplate", GUARDIAN, Type.CHESTPLATE).groupAfter(ItemGroups.COMBAT, GUARDIAN_HELMET).build();
    public static final Item GUARDIAN_LEGGINGS   = defaultArmorItem("guardian_leggings",   GUARDIAN, Type.LEGGINGS).groupAfter(ItemGroups.COMBAT, GUARDIAN_CHESTPLATE).build();
    public static final Item GUARDIAN_BOOTS      = defaultArmorItem("guardian_boots",      GUARDIAN, Type.BOOTS).groupAfter(ItemGroups.COMBAT, GUARDIAN_LEGGINGS).build();
    //#endregion

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}

}
