package somemod.frost.item;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Items;
import static net.minecraft.entity.effect.StatusEffects.*;
import static net.minecraft.item.ItemGroups.*;
import static net.minecraft.item.ArmorItem.Type.*;
import static net.minecraft.util.Rarity.*;
import static net.minecraft.entity.EquipmentSlot.*;
import somemod.common.item.StaticEffectArmorItem;
import somemod.frost.block.FrostBlocks;
import static somemod.frost.item.FrostArmorMaterials.*;
import static somemod.utils.ItemBuilder.*;

public class FrostItems {

    public static final BlockItem SPRUCE_CHEST = blockItem("spruce_chest", FrostBlocks.SPRUCE_CHEST).set(COMMON).after(FUNCTIONAL, Items.CHEST).build();
    public static final BlockItem ICE_CHEST    = blockItem("ice_chest",    FrostBlocks.ICE_CHEST)   .set(COMMON).after(FUNCTIONAL, Items.CHEST).build();

    public static final ArrowItem FROSTBITE_ARROW = item("frostbite_arrow", FrostbiteArrowItem::new).after(COMBAT, Items.TIPPED_ARROW).build();
    
    // Arctic: Craftable & Found in chests
    public static final ArmorItem ARCTIC_BOOTS  = armorItem("arctic_boots",  ARCTIC, BOOTS)     .attributes(arctic(FEET)).before(COMBAT, Items.CHAINMAIL_HELMET).build();
    public static final ArmorItem ARCTIC_PANTS  = armorItem("arctic_pants",  ARCTIC, LEGGINGS)  .attributes(arctic(LEGS)).before(COMBAT, ARCTIC_BOOTS).build();
    public static final ArmorItem ARCTIC_COAT   = armorItem("arctic_coat",   ARCTIC, CHESTPLATE).attributes(arctic(CHEST)).before(COMBAT, ARCTIC_PANTS).build();
    public static final ArmorItem ARCTIC_HAT    = armorItem("arctic_hat",    ARCTIC, HELMET)    .attributes(arctic(HEAD)).before(COMBAT, ARCTIC_COAT).build();
    
    // Arctic: Craftable & found in chests
    public static final ArmorItem GLACIER_BOOTS      = armorItem("glacier_boots",      GLACIER, BOOTS)     .attributes(glacier(FEET)).before(COMBAT, Items.IRON_HELMET).build();
    public static final ArmorItem GLACIER_LEGGINGS   = armorItem("glacier_leggings",   GLACIER, LEGGINGS)  .attributes(glacier(LEGS)).before(COMBAT, GLACIER_BOOTS).build();
    public static final ArmorItem GLACIER_CHESTPLATE = armorItem("glacier_chestplate", GLACIER, CHESTPLATE).attributes(glacier(CHEST)).before(COMBAT, GLACIER_LEGGINGS).build();
    public static final ArmorItem GLACIER_HELMET     = item("glacier_helmet", StaticEffectArmorItem.of(GLACIER, HELMET).requires(GLACIER_BOOTS, GLACIER_LEGGINGS, GLACIER_CHESTPLATE)
                                                     .gives(STRENGTH, 1).build()).attributes(glacier(HEAD)).before(COMBAT, GLACIER_CHESTPLATE).build();

    // Blizzard: Craftable
    public static final ArmorItem BLIZZARD_BOOTS = armorItem("blizzard_boots", BLIZZARD, BOOTS).after(COMBAT, Items.IRON_BOOTS).build();

    // Frostbite: Craftable & found in chests
    public static final ArmorItem FROSTBITE_CHESTPLATE = armorItem("frostbite_chestplate", FROSTBITE, CHESTPLATE).attributes(frostbite(CHEST)).after(COMBAT, Items.DIAMOND_BOOTS).build();
    public static final ArmorItem FROSTBITE_LEGGINGS   = item("frostbite_leggings", StaticEffectArmorItem.of(FROSTBITE, LEGGINGS).requires(FROSTBITE_CHESTPLATE)
                                                    .gives(RESISTANCE, 1).build()).attributes(frostbite(LEGS)).after(COMBAT, FROSTBITE_CHESTPLATE).build();

    // Ice Queen: Found in chests
    public static final ArmorItem ICE_QUEEN_CROWN = item("ice_queen_crown", StaticEffectArmorItem.of(ICE_QUEEN, HELMET)
                                                    .gives(REGENERATION).gives(LUCK).build()).after(COMBAT, Items.NETHERITE_BOOTS).build();

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}

}
