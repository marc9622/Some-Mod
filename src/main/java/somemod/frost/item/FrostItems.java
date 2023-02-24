package somemod.frost.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;

import static somemod.frost.item.FrostArmorMaterials.*;
import static somemod.utils.ItemBuilder.*;

public class FrostItems {
    
    public static final Item ARCTIC_BOOTS  = defaultArmorItem("arctic_boots",  ARCTIC, EquipmentSlot.FEET).addGroupBefore(ItemGroups.COMBAT, Items.CHAINMAIL_HELMET).build();
    public static final Item ARCTIC_PANTS  = defaultArmorItem("arctic_pants",  ARCTIC, EquipmentSlot.LEGS).addGroupBefore(ItemGroups.COMBAT, ARCTIC_BOOTS).build();
    public static final Item ARCTIC_JACKET = defaultArmorItem("arctic_jacket", ARCTIC, EquipmentSlot.CHEST).addGroupBefore(ItemGroups.COMBAT, ARCTIC_PANTS).build();
    public static final Item ARCTIC_HAT    = defaultArmorItem("arctic_hat",    ARCTIC, EquipmentSlot.HEAD).addGroupBefore(ItemGroups.COMBAT, ARCTIC_JACKET).build();
    
    public static final Item GLACIER_BOOTS      = defaultArmorItem("glacier_boots",      GLACIER, EquipmentSlot.FEET).addGroupBefore(ItemGroups.COMBAT, Items.IRON_HELMET).build();
    public static final Item GLACIER_LEGGINGS   = defaultArmorItem("glacier_leggings",   GLACIER, EquipmentSlot.LEGS).addGroupBefore(ItemGroups.COMBAT, GLACIER_BOOTS).build();
    public static final Item GLACIER_CHESTPLATE = defaultArmorItem("glacier_chestplate", GLACIER, EquipmentSlot.CHEST).addGroupBefore(ItemGroups.COMBAT, GLACIER_LEGGINGS).build();
    public static final Item GLACIER_HELMET     = defaultArmorItem("glacier_helmet",     GLACIER, EquipmentSlot.HEAD).addGroupBefore(ItemGroups.COMBAT, GLACIER_CHESTPLATE).build();

    public static final Item BLIZZARD_BOOTS = defaultArmorItem("blizzard_boots", BLIZZARD, EquipmentSlot.FEET).addGroupAfter(ItemGroups.COMBAT, Items.IRON_BOOTS).build();

    public static final Item FROSTBITE_CHESTPLATE = defaultArmorItem("frostbite_chestplate", FROSTBITE, EquipmentSlot.CHEST).addGroupAfter(ItemGroups.COMBAT, Items.DIAMOND_BOOTS).build();
    public static final Item FROSTBITE_LEGGINGS   = defaultArmorItem("frostbite_leggings",   FROSTBITE, EquipmentSlot.LEGS).addGroupAfter(ItemGroups.COMBAT, FROSTBITE_CHESTPLATE).build();

    public static final Item ICE_QUEEN_CROWN = defaultArmorItem("ice_queen_crown", ICE_QUEEN, EquipmentSlot.HEAD).addGroupAfter(ItemGroups.COMBAT, Items.NETHERITE_BOOTS).build();

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}

}
