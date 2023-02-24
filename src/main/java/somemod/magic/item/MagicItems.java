package somemod.magic.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Rarity;

import static somemod.magic.block.MagicBlocks.*;
import static somemod.magic.item.MagicArmorMaterials.*;
import static somemod.utils.ItemBuilder.*;

public class MagicItems {
    
    public static final Item ENCHANTED_BOOKSHELF_ITEM =
        fromItem("enchanted_bookshelf", s -> new BlockItem(ENCHANTED_BOOKSHELF, s) {
            @Override public boolean hasGlint(ItemStack stack) { return false; }
        }).modifySettings(s -> s.rarity(Rarity.UNCOMMON)).addGroupAfter(ItemGroups.FUNCTIONAL, Items.BOOKSHELF).build(); // Or maybe next to enchanting table instead?
    public static final Item OBSIDIAN_ENCHANTED_BOOKSHELF_ITEM =
        fromItem("obsidian_enchanted_bookshelf", s -> new BlockItem(OBSIDIAN_ENCHANTED_BOOKSHELF, s) {
            @Override public boolean hasGlint(ItemStack stack) { return false; }
        }).modifySettings(s -> s.rarity(Rarity.RARE)).addGroupAfter(ItemGroups.FUNCTIONAL, ENCHANTED_BOOKSHELF_ITEM).build(); // Or maybe next to enchanting table instead?

    //#region ARMOR
    public static final Item ARCANE_HAT      = defaultArmorItem("arcane_hat",   ARCANE, EquipmentSlot.CHEST).addGroupAfter(ItemGroups.COMBAT, Items.LEATHER_BOOTS).build();
    public static final Item ARCANE_ROBE     = defaultArmorItem("arcane_robe",  ARCANE, EquipmentSlot.CHEST).addGroupAfter(ItemGroups.COMBAT, ARCANE_HAT).build();
    public static final Item ARCANE_LEGGINGS = defaultArmorItem("arcane_pants", ARCANE, EquipmentSlot.LEGS).addGroupAfter(ItemGroups.COMBAT, ARCANE_ROBE).build();
    
    public static final Item HONEY_MASK       = defaultArmorItem("honey_mask",       HONEY, EquipmentSlot.HEAD).addGroupAfter(ItemGroups.COMBAT, ARCANE_LEGGINGS).build();
    public static final Item HONEY_CHESTPIECE = defaultArmorItem("honey_chestpiece", HONEY, EquipmentSlot.CHEST).addGroupAfter(ItemGroups.COMBAT, HONEY_MASK).build();
    public static final Item HONEY_LEGGINGS   = defaultArmorItem("honey_leggings",   HONEY, EquipmentSlot.LEGS).addGroupAfter(ItemGroups.COMBAT, HONEY_CHESTPIECE).build();
    public static final Item HONEY_BOOTS      = defaultArmorItem("honey_boots",      HONEY, EquipmentSlot.FEET).addGroupAfter(ItemGroups.COMBAT, HONEY_LEGGINGS).build();
    
    public static final Item PIRATE_HAT   = defaultArmorItem("pirate_hat",   PIRATE, EquipmentSlot.HEAD).addGroupAfter(ItemGroups.COMBAT, HONEY_BOOTS).build();
    public static final Item PIRATE_SHIRT = defaultArmorItem("pirate_shirt", PIRATE, EquipmentSlot.CHEST).addGroupAfter(ItemGroups.COMBAT, PIRATE_HAT).build();
    public static final Item PIRATE_PANTS = defaultArmorItem("pirate_pants", PIRATE, EquipmentSlot.LEGS).addGroupAfter(ItemGroups.COMBAT, PIRATE_SHIRT).build();
    public static final Item PIRATE_BOOTS = defaultArmorItem("pirate_boots", PIRATE, EquipmentSlot.FEET).addGroupAfter(ItemGroups.COMBAT, PIRATE_PANTS).build();

    public static final Item ALCHEMIST_JACKET = defaultArmorItem("alchemist_jacket", ALCHEMIST, EquipmentSlot.CHEST).addGroupAfter(ItemGroups.COMBAT, PIRATE_BOOTS).build();
    public static final Item ALCHEMIST_PANTS  = defaultArmorItem("alchemist_pants",  ALCHEMIST, EquipmentSlot.LEGS).addGroupAfter(ItemGroups.COMBAT, ALCHEMIST_JACKET).build();
    public static final Item ALCHEMIST_BOOTS  = defaultArmorItem("alchemist_boots",  ALCHEMIST, EquipmentSlot.FEET).addGroupAfter(ItemGroups.COMBAT, ALCHEMIST_PANTS).build();
    
    public static final Item ELVEN_HELMET     = defaultArmorItem("elven_helmet",     ELVEN, EquipmentSlot.HEAD).addGroupAfter(ItemGroups.COMBAT, ALCHEMIST_BOOTS).build();
    public static final Item ELVEN_CHESTPLATE = defaultArmorItem("elven_chestplate", ELVEN, EquipmentSlot.CHEST).addGroupAfter(ItemGroups.COMBAT, ELVEN_HELMET).build();
    public static final Item ELVEN_LEGGINGS   = defaultArmorItem("elven_leggings",   ELVEN, EquipmentSlot.LEGS).addGroupAfter(ItemGroups.COMBAT, ELVEN_CHESTPLATE).build();
    public static final Item ELVEN_BOOTS      = defaultArmorItem("elven_boots",      ELVEN, EquipmentSlot.FEET).addGroupAfter(ItemGroups.COMBAT, ELVEN_LEGGINGS).build();
    
    public static final Item OCEANIC_MASK     = defaultArmorItem("oceanic_mask",     OCEANIC, EquipmentSlot.HEAD).addGroupAfter(ItemGroups.COMBAT, Items.CHAINMAIL_BOOTS).build();
    public static final Item OCEANIC_SUIT     = defaultArmorItem("oceanic_suit",     OCEANIC, EquipmentSlot.CHEST).addGroupAfter(ItemGroups.COMBAT, OCEANIC_MASK).build();
    public static final Item OCEANIC_LEGGINGS = defaultArmorItem("oceanic_leggings", OCEANIC, EquipmentSlot.LEGS).addGroupAfter(ItemGroups.COMBAT, OCEANIC_SUIT).build();
    public static final Item OCEANIC_SHOES    = defaultArmorItem("oceanic_shoes",    OCEANIC, EquipmentSlot.FEET).addGroupAfter(ItemGroups.COMBAT, OCEANIC_LEGGINGS).build();

    public static final Item DESERT_HELMET     = defaultArmorItem("desert_helmet",     DESERT, EquipmentSlot.HEAD).addGroupAfter(ItemGroups.COMBAT, OCEANIC_SHOES).build();
    public static final Item DESERT_CHESTPLATE = defaultArmorItem("desert_chestplate", DESERT, EquipmentSlot.CHEST).addGroupAfter(ItemGroups.COMBAT, DESERT_HELMET).build();
    public static final Item DESERT_LEGGINGS   = defaultArmorItem("desert_leggings",   DESERT, EquipmentSlot.LEGS).addGroupAfter(ItemGroups.COMBAT, DESERT_CHESTPLATE).build();
    public static final Item DESERT_BOOTS      = defaultArmorItem("desert_boots",      DESERT, EquipmentSlot.FEET).addGroupAfter(ItemGroups.COMBAT, DESERT_LEGGINGS).build();
    
    public static final Item LUNAR_HELMET     = defaultArmorItem("lunar_helmet",     LUNAR, EquipmentSlot.HEAD).addGroupAfter(ItemGroups.COMBAT, DESERT_BOOTS).build();
    public static final Item LUNAR_CHESTPLATE = defaultArmorItem("lunar_chestplate", LUNAR, EquipmentSlot.CHEST).addGroupAfter(ItemGroups.COMBAT, LUNAR_HELMET).build();
    public static final Item LUNAR_LEGGINGS   = defaultArmorItem("lunar_leggings",   LUNAR, EquipmentSlot.LEGS).addGroupAfter(ItemGroups.COMBAT, LUNAR_CHESTPLATE).build();
    public static final Item LUNAR_BOOTS      = defaultArmorItem("lunar_boots",      LUNAR, EquipmentSlot.FEET).addGroupAfter(ItemGroups.COMBAT, LUNAR_LEGGINGS).build();
    
    public static final Item PHANTOM_MASK       = defaultArmorItem("phantom_mask",     PHANTOM, EquipmentSlot.HEAD).addGroupAfter(ItemGroups.COMBAT, LUNAR_BOOTS).build();
    public static final Item PHANTOM_CHESTPLATE = defaultArmorItem("phantom_chestplate", PHANTOM, EquipmentSlot.CHEST).addGroupAfter(ItemGroups.COMBAT, PHANTOM_MASK).build();
    public static final Item PHANTOM_LEGGINGS   = defaultArmorItem("phantom_leggings",   PHANTOM, EquipmentSlot.LEGS).addGroupAfter(ItemGroups.COMBAT, PHANTOM_CHESTPLATE).build();

    public static final Item DIVINE_HELMET     = defaultArmorItem("divine_helmet",     DIVINE, EquipmentSlot.HEAD).addGroupAfter(ItemGroups.COMBAT, PHANTOM_LEGGINGS).build();
    public static final Item DIVINE_CHESTPLATE = defaultArmorItem("divine_chestplate", DIVINE, EquipmentSlot.CHEST).addGroupAfter(ItemGroups.COMBAT, DIVINE_HELMET).build();
    public static final Item DIVINE_LEGGINGS   = defaultArmorItem("divine_leggings",   DIVINE, EquipmentSlot.LEGS).addGroupAfter(ItemGroups.COMBAT, DIVINE_CHESTPLATE).build();
    public static final Item DIVINE_BOOTS      = defaultArmorItem("divine_boots",      DIVINE, EquipmentSlot.FEET).addGroupAfter(ItemGroups.COMBAT, DIVINE_LEGGINGS).build();
    
    public static final Item NECROTIC_MASK       = defaultArmorItem("necrotic_mask",       NECROTIC, EquipmentSlot.HEAD).addGroupAfter(ItemGroups.COMBAT, DIVINE_BOOTS).build();
    public static final Item NECROTIC_CHESTPLATE = defaultArmorItem("necrotic_chestplate", NECROTIC, EquipmentSlot.CHEST).addGroupAfter(ItemGroups.COMBAT, NECROTIC_MASK).build();
    public static final Item NECROTIC_LEGGINGS   = defaultArmorItem("necrotic_leggings",   NECROTIC, EquipmentSlot.LEGS).addGroupAfter(ItemGroups.COMBAT, NECROTIC_CHESTPLATE).build();
    public static final Item NECROTIC_BOOTS      = defaultArmorItem("necrotic_boots",      NECROTIC, EquipmentSlot.FEET).addGroupAfter(ItemGroups.COMBAT, NECROTIC_LEGGINGS).build();

    public static final Item LIVING_MASK     = defaultArmorItem("living_mask",     LIVING, EquipmentSlot.HEAD).addGroupAfter(ItemGroups.COMBAT, NECROTIC_BOOTS).build();
    public static final Item LIVING_SUIT     = defaultArmorItem("living_suit",     LIVING, EquipmentSlot.CHEST).addGroupAfter(ItemGroups.COMBAT, LIVING_MASK).build();
    public static final Item LIVING_LEGGINGS = defaultArmorItem("living_leggings", LIVING, EquipmentSlot.LEGS).addGroupAfter(ItemGroups.COMBAT, LIVING_SUIT).build();
    public static final Item LIVING_BOOTS    = defaultArmorItem("living_boots",    LIVING, EquipmentSlot.FEET).addGroupAfter(ItemGroups.COMBAT, LIVING_LEGGINGS).build();

    public static final Item SHADOW_MASK       = defaultArmorItem("shadow_mask",       SHADOW, EquipmentSlot.HEAD).addGroupAfter(ItemGroups.COMBAT, Items.IRON_BOOTS).build();
    public static final Item SHADOW_CHESTPIECE = defaultArmorItem("shadow_chestplate", SHADOW, EquipmentSlot.CHEST).addGroupAfter(ItemGroups.COMBAT, SHADOW_MASK).build();
    public static final Item SHADOW_LEGGINGS   = defaultArmorItem("shadow_leggings",   SHADOW, EquipmentSlot.LEGS).addGroupAfter(ItemGroups.COMBAT, SHADOW_CHESTPIECE).build();

    public static final Item ANGELIC_CHESTPLATE = defaultArmorItem("angelic_chestplate", ANGELIC, EquipmentSlot.CHEST).addGroupAfter(ItemGroups.COMBAT, SHADOW_LEGGINGS).build();
    public static final Item ANGELIC_LEGGINGS   = defaultArmorItem("angelic_leggings",   ANGELIC, EquipmentSlot.LEGS).addGroupAfter(ItemGroups.COMBAT, ANGELIC_CHESTPLATE).build();

    public static final Item DEEP_SEA_MASK       = defaultArmorItem("deep_sea_mask",       DEEP_SEA, EquipmentSlot.HEAD).addGroupAfter(ItemGroups.COMBAT, ANGELIC_LEGGINGS).build();
    public static final Item DEEP_SEA_CHESTPLATE = defaultArmorItem("deep_sea_chestplate", DEEP_SEA, EquipmentSlot.CHEST).addGroupAfter(ItemGroups.COMBAT, DEEP_SEA_MASK).build();
    public static final Item DEEP_SEA_LEGGINGS   = defaultArmorItem("deep_sea_leggings",   DEEP_SEA, EquipmentSlot.LEGS).addGroupAfter(ItemGroups.COMBAT, DEEP_SEA_CHESTPLATE).build();
    public static final Item DEEP_SEA_BOOTS      = defaultArmorItem("deep_sea_boots",      DEEP_SEA, EquipmentSlot.FEET).addGroupAfter(ItemGroups.COMBAT, DEEP_SEA_LEGGINGS).build();

    public static final Item MAGMA_CHESTPLATE = defaultArmorItem("magma_chestplate", MAGMA, EquipmentSlot.CHEST).addGroupAfter(ItemGroups.COMBAT, DEEP_SEA_BOOTS).build();
    public static final Item MAGMA_LEGGINGS   = defaultArmorItem("magma_leggings",   MAGMA, EquipmentSlot.LEGS).addGroupAfter(ItemGroups.COMBAT, MAGMA_CHESTPLATE).build();
    public static final Item MAGMA_BOOTS      = defaultArmorItem("magma_boots",      MAGMA, EquipmentSlot.FEET).addGroupAfter(ItemGroups.COMBAT, MAGMA_LEGGINGS).build();

    public static final Item GUARDIAN_HELMET     = defaultArmorItem("guardian_helmet",     GUARDIAN, EquipmentSlot.HEAD).addGroupAfter(ItemGroups.COMBAT, MAGMA_BOOTS).build();
    public static final Item GUARDIAN_CHESTPLATE = defaultArmorItem("guardian_chestplate", GUARDIAN, EquipmentSlot.CHEST).addGroupAfter(ItemGroups.COMBAT, GUARDIAN_HELMET).build();
    public static final Item GUARDIAN_LEGGINGS   = defaultArmorItem("guardian_leggings",   GUARDIAN, EquipmentSlot.LEGS).addGroupAfter(ItemGroups.COMBAT, GUARDIAN_CHESTPLATE).build();
    public static final Item GUARDIAN_BOOTS      = defaultArmorItem("guardian_boots",      GUARDIAN, EquipmentSlot.FEET).addGroupAfter(ItemGroups.COMBAT, GUARDIAN_LEGGINGS).build();
    //#endregion

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}

}
