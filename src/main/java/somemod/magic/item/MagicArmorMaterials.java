package somemod.magic.item;

import static somemod.utils.ArmorMaterialBuilder.of;

import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;

public final class MagicArmorMaterials {
    
	//TODO: Make original materials for some of these
	public static final ArmorMaterial ARCANE =    of("arcane_robe", 6, 1, 3, 2, 1, 27, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, () -> Items.LEATHER);
	public static final ArmorMaterial PIRATE =    of("pirate", 	   11, 1, 4, 3, 1,  6, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, () -> Items.LEATHER);
	public static final ArmorMaterial HONEY =     of("honey", 		5, 1, 4, 3, 1, 12, SoundEvents.ITEM_ARMOR_EQUIP_GOLD,    0.0f, 0.05f,() -> Items.HONEYCOMB);
	public static final ArmorMaterial ALCHEMIST = of("alchemist",   7, 2, 4, 3, 2, 17, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, () -> Items.GLASS_BOTTLE);
	public static final ArmorMaterial ELVEN =     of("elven", 	   12, 2, 4, 3, 2, 22, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, () -> MagicItems.ELVEN_STEEL);
	public static final ArmorMaterial OCEANIC =   of("oceanic",    10, 2, 4, 4, 2, 15, SoundEvents.ITEM_ARMOR_EQUIP_TURTLE,  0.0f, 0.0f, () -> Items.FIRE_CORAL);
	public static final ArmorMaterial DESERT =    of("desert_nomad",9, 2, 5, 4, 1,  9, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, () -> Items.SANDSTONE);
	public static final ArmorMaterial LUNAR =     of("lunar", 	   12, 2, 5, 4, 1, 19, SoundEvents.ITEM_ARMOR_EQUIP_IRON,    0.0f, 0.0f, () -> Items.IRON_INGOT);
	public static final ArmorMaterial PHANTOM =   of("phantom", 	7, 2, 5, 4, 2, 10, SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA,  0.0f, 0.0f, () -> Items.AIR);
	public static final ArmorMaterial DIVINE =    of("divine", 	   25, 2, 5, 4, 2, 22, SoundEvents.ITEM_ARMOR_EQUIP_GOLD,    0.5f, 0.0f, () -> Items.GOLD_INGOT);
	public static final ArmorMaterial NECROTIC =  of("necrotic",   14, 3, 5, 4, 2,  6, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,0.0f,0.0f, () -> Items.BONE);
	public static final ArmorMaterial LIVING =    of("living",     14, 3, 5, 4, 2, 17, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.5f, 0.0f, () -> Items.OAK_WOOD);
	public static final ArmorMaterial SHADOW =    of("shadow", 	    9, 3, 5, 4, 3, 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,0.0f,0.0f, () -> Items.NETHER_BRICK, () -> Items.SOUL_SAND);
	public static final ArmorMaterial ANGELIC =   of("angelic",    15, 3, 6, 5, 3, 19, SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA,  0.0f, 0.0f, () -> Items.ELYTRA);
	public static final ArmorMaterial DEEP_SEA =  of("deep_sea",   23, 3, 6, 5, 3, 17, SoundEvents.ITEM_ARMOR_EQUIP_TURTLE,  0.0f, 0.05f,() -> Items.FIRE_CORAL);
	public static final ArmorMaterial MAGMA =     of("magma", 	   20, 4, 6, 5, 3, 12, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,0.5f,0.0f, () -> Items.MAGMA_CREAM, () -> Items.BLAZE_ROD);
	public static final ArmorMaterial GUARDIAN =  of("guardian",   30, 3, 7, 5, 3, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.0f, 0.05f,() -> Items.DIAMOND);

}
