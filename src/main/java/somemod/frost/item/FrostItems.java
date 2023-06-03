package somemod.frost.item;

import net.minecraft.item.ArmorItem.Type;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;

import static somemod.frost.item.FrostArmorMaterials.*;
import static somemod.utils.ItemBuilder.*;

public class FrostItems {
    
    // Arctic: Craftable
    public static final Item ARCTIC_BOOTS  = defaultArmorItem("arctic_boots",  ARCTIC, Type.BOOTS).groupBefore(ItemGroups.COMBAT, Items.CHAINMAIL_HELMET).build();
    public static final Item ARCTIC_PANTS  = defaultArmorItem("arctic_pants",  ARCTIC, Type.LEGGINGS).groupBefore(ItemGroups.COMBAT, ARCTIC_BOOTS).build();
    public static final Item ARCTIC_JACKET = defaultArmorItem("arctic_jacket", ARCTIC, Type.CHESTPLATE).groupBefore(ItemGroups.COMBAT, ARCTIC_PANTS).build();
    public static final Item ARCTIC_HAT    = defaultArmorItem("arctic_hat",    ARCTIC, Type.HELMET).groupBefore(ItemGroups.COMBAT, ARCTIC_JACKET).build();
    
    // Arctic: Craftable, found in chests
    public static final Item GLACIER_BOOTS      = defaultArmorItem("glacier_boots",      GLACIER, Type.BOOTS).groupBefore(ItemGroups.COMBAT, Items.IRON_HELMET).build();
    public static final Item GLACIER_LEGGINGS   = defaultArmorItem("glacier_leggings",   GLACIER, Type.LEGGINGS).groupBefore(ItemGroups.COMBAT, GLACIER_BOOTS).build();
    public static final Item GLACIER_CHESTPLATE = defaultArmorItem("glacier_chestplate", GLACIER, Type.CHESTPLATE).groupBefore(ItemGroups.COMBAT, GLACIER_LEGGINGS).build();
    public static final Item GLACIER_HELMET     = defaultArmorItem("glacier_helmet",     GLACIER, Type.HELMET).groupBefore(ItemGroups.COMBAT, GLACIER_CHESTPLATE).build();

    // Blizzard: Craftable
    public static final Item BLIZZARD_BOOTS = defaultArmorItem("blizzard_boots", BLIZZARD, Type.BOOTS).groupAfter(ItemGroups.COMBAT, Items.IRON_BOOTS).build();

    // Frostbite: Craftable, found in chests
    public static final Item FROSTBITE_CHESTPLATE = defaultArmorItem("frostbite_chestplate", FROSTBITE, Type.CHESTPLATE).groupAfter(ItemGroups.COMBAT, Items.DIAMOND_BOOTS).build();
    public static final Item FROSTBITE_LEGGINGS   = defaultArmorItem("frostbite_leggings",   FROSTBITE, Type.LEGGINGS).groupAfter(ItemGroups.COMBAT, FROSTBITE_CHESTPLATE).build();

    // Ice Queen: Found in chests
    public static final Item ICE_QUEEN_CROWN = defaultArmorItem("ice_queen_crown", ICE_QUEEN, Type.HELMET).groupAfter(ItemGroups.COMBAT, Items.NETHERITE_BOOTS).build();

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}

}
