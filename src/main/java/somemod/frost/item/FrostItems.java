package somemod.frost.item;

import net.minecraft.item.ArmorItem.Type;
import net.minecraft.util.Rarity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import somemod.common.item.StaticEffectArmorItem;
import somemod.frost.block.FrostBlocks;
import static somemod.frost.item.FrostArmorMaterials.*;
import static somemod.utils.ItemBuilder.*;

public class FrostItems {

    public static final BlockItem SPRUCE_CHEST = blockItem("spruce_chest", FrostBlocks.SPRUCE_CHEST).set(Rarity.COMMON).after(ItemGroups.FUNCTIONAL, Items.CHEST).build();
    public static final BlockItem ICE_CHEST    = blockItem("ice_chest",    FrostBlocks.ICE_CHEST)   .set(Rarity.COMMON).after(ItemGroups.FUNCTIONAL, Items.CHEST).build();

    public static final ArrowItem FROSTBITE_ARROW = item("frostbite_arrow", FrostbiteArrowItem::new).after(ItemGroups.COMBAT, Items.TIPPED_ARROW).build();
    
    // Arctic: Craftable & Found in chests
    public static final ArmorItem ARCTIC_BOOTS  = armorItem("arctic_boots",  ARCTIC, Type.BOOTS)     .before(ItemGroups.COMBAT, Items.CHAINMAIL_HELMET).build();
    public static final ArmorItem ARCTIC_PANTS  = armorItem("arctic_pants",  ARCTIC, Type.LEGGINGS)  .before(ItemGroups.COMBAT, ARCTIC_BOOTS).build();
    public static final ArmorItem ARCTIC_JACKET = armorItem("arctic_jacket", ARCTIC, Type.CHESTPLATE).before(ItemGroups.COMBAT, ARCTIC_PANTS).build();
    public static final ArmorItem ARCTIC_HAT    = armorItem("arctic_hat",    ARCTIC, Type.HELMET)    .before(ItemGroups.COMBAT, ARCTIC_JACKET).build();
    
    // Arctic: Craftable & found in chests
    public static final ArmorItem GLACIER_BOOTS      = armorItem("glacier_boots",      GLACIER, Type.BOOTS)     .before(ItemGroups.COMBAT, Items.IRON_HELMET).build();
    public static final ArmorItem GLACIER_LEGGINGS   = armorItem("glacier_leggings",   GLACIER, Type.LEGGINGS)  .before(ItemGroups.COMBAT, GLACIER_BOOTS).build();
    public static final ArmorItem GLACIER_CHESTPLATE = armorItem("glacier_chestplate", GLACIER, Type.CHESTPLATE).before(ItemGroups.COMBAT, GLACIER_LEGGINGS).build();
    public static final ArmorItem GLACIER_HELMET     = item("glacier_helmet", StaticEffectArmorItem.of(GLACIER, Type.HELMET).requires(GLACIER_BOOTS, GLACIER_LEGGINGS, GLACIER_CHESTPLATE)
                                                     .gives(StatusEffects.STRENGTH, 1).build()).before(ItemGroups.COMBAT, GLACIER_CHESTPLATE).build();

    // Blizzard: Craftable
    public static final ArmorItem BLIZZARD_BOOTS = armorItem("blizzard_boots", BLIZZARD, Type.BOOTS).after(ItemGroups.COMBAT, Items.IRON_BOOTS).build();

    // Frostbite: Craftable & found in chests
    public static final ArmorItem FROSTBITE_CHESTPLATE = armorItem("frostbite_chestplate", FROSTBITE, Type.CHESTPLATE).after(ItemGroups.COMBAT, Items.DIAMOND_BOOTS).build();
    public static final ArmorItem FROSTBITE_LEGGINGS   = item("frostbite_leggings", StaticEffectArmorItem.of(FROSTBITE, Type.LEGGINGS).requires(FROSTBITE_CHESTPLATE)
                                                    .gives(StatusEffects.RESISTANCE, 1).build()).after(ItemGroups.COMBAT, FROSTBITE_CHESTPLATE).build();

    // Ice Queen: Found in chests
    public static final ArmorItem ICE_QUEEN_CROWN = item("ice_queen_crown", StaticEffectArmorItem.of(ICE_QUEEN, Type.HELMET)
                                                    .gives(StatusEffects.REGENERATION).gives(StatusEffects.LUCK).build()).after(ItemGroups.COMBAT, Items.NETHERITE_BOOTS).build();

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}

}
