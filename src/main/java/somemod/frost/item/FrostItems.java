package somemod.frost.item;

import net.minecraft.item.ArmorItem.Type;
import net.minecraft.util.Rarity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import somemod.common.item.StaticEffectArmorItem;
import somemod.frost.block.FrostBlocks;
import static somemod.frost.item.FrostArmorMaterials.*;
import static somemod.utils.ItemBuilder.*;

public class FrostItems {

    public static final BlockItem SPRUCE_CHEST = defaultBlockItem("spruce_chest", FrostBlocks.SPRUCE_CHEST)
        .settings(s -> s.rarity(Rarity.COMMON)).groupAfter(ItemGroups.FUNCTIONAL, Items.CHEST).build();

    public static final BlockItem ICE_CHEST = defaultBlockItem("ice_chest", FrostBlocks.ICE_CHEST)
        .settings(s -> s.rarity(Rarity.COMMON)).groupAfter(ItemGroups.FUNCTIONAL, Items.CHEST).build();
    
    // Arctic: Craftable & Found in chests
    public static final ArmorItem ARCTIC_BOOTS  = defaultArmorItem("arctic_boots",  ARCTIC, Type.BOOTS).groupBefore(ItemGroups.COMBAT, Items.CHAINMAIL_HELMET).build();
    public static final ArmorItem ARCTIC_PANTS  = defaultArmorItem("arctic_pants",  ARCTIC, Type.LEGGINGS).groupBefore(ItemGroups.COMBAT, ARCTIC_BOOTS).build();
    public static final ArmorItem ARCTIC_JACKET = defaultArmorItem("arctic_jacket", ARCTIC, Type.CHESTPLATE).groupBefore(ItemGroups.COMBAT, ARCTIC_PANTS).build();
    public static final ArmorItem ARCTIC_HAT    = defaultArmorItem("arctic_hat",    ARCTIC, Type.HELMET).groupBefore(ItemGroups.COMBAT, ARCTIC_JACKET).build();
    
    // Arctic: Craftable & found in chests
    public static final ArmorItem GLACIER_BOOTS      = defaultArmorItem("glacier_boots",      GLACIER, Type.BOOTS).groupBefore(ItemGroups.COMBAT, Items.IRON_HELMET).build();
    public static final ArmorItem GLACIER_LEGGINGS   = defaultArmorItem("glacier_leggings",   GLACIER, Type.LEGGINGS).groupBefore(ItemGroups.COMBAT, GLACIER_BOOTS).build();
    public static final ArmorItem GLACIER_CHESTPLATE = defaultArmorItem("glacier_chestplate", GLACIER, Type.CHESTPLATE).groupBefore(ItemGroups.COMBAT, GLACIER_LEGGINGS).build();
    public static final ArmorItem GLACIER_HELMET = fromItem("glacier_helmet", StaticEffectArmorItem.builder(GLACIER, Type.HELMET).requires(GLACIER_BOOTS, GLACIER_LEGGINGS, GLACIER_CHESTPLATE)
                                                .gives(StatusEffects.STRENGTH, 1).build()).groupBefore(ItemGroups.COMBAT, GLACIER_CHESTPLATE).build();

    // Blizzard: Craftable
    public static final ArmorItem BLIZZARD_BOOTS = fromItem("blizzard_boots", StaticEffectArmorItem.builder(BLIZZARD, Type.BOOTS).gives(StatusEffects.SPEED, 0).build())
                                                                                                                            .groupAfter(ItemGroups.COMBAT, Items.IRON_BOOTS).build();

    // Frostbite: Craftable & found in chests
    public static final ArmorItem FROSTBITE_CHESTPLATE = defaultArmorItem("frostbite_chestplate", FROSTBITE, Type.CHESTPLATE).groupAfter(ItemGroups.COMBAT, Items.DIAMOND_BOOTS).build();
    public static final ArmorItem FROSTBITE_LEGGINGS = fromItem("frostbite_leggings", StaticEffectArmorItem.builder(FROSTBITE, Type.LEGGINGS).requires(FROSTBITE_CHESTPLATE)
                                                .gives(StatusEffects.RESISTANCE, 1).build()).groupAfter(ItemGroups.COMBAT, FROSTBITE_CHESTPLATE).build();

    // Ice Queen: Found in chests
    public static final ArmorItem ICE_QUEEN_CROWN = fromItem("ice_queen_crown", StaticEffectArmorItem.builder(ICE_QUEEN, Type.HELMET)
                                                .gives(StatusEffects.REGENERATION).gives(StatusEffects.LUCK).build()).groupAfter(ItemGroups.COMBAT, Items.NETHERITE_BOOTS).build();

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}

}
