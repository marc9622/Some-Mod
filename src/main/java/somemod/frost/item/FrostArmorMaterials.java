package somemod.frost.item;

import static somemod.utils.ArmorMaterialBuilder.of;

import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;

public final class FrostArmorMaterials {
    
    //TODO: Make original materials for some of these
    public static final ArmorMaterial ARCTIC    = of("arctic",    14, 2, 5, 3, 1, 10, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.05f, () -> Items.SNOW);
    public static final ArmorMaterial GLACIER   = of("glacier",   15, 2, 6, 4, 2, 12, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 	0.0f, 0.1f,  () -> Items.ICE);
    public static final ArmorMaterial BLIZZARD  = of("blizzard",  11, 2, 6, 5, 2, 12, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.1f,  () -> Items.SNOW);
    public static final ArmorMaterial FROSTBITE = of("frostbite", 19, 3, 8, 6, 3, 14, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.5f, 0.0f,  () -> Items.PACKED_ICE);
    public static final ArmorMaterial ICE_QUEEN = of("ice_queen", 24, 3, 8, 6, 3, 28, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.5f, 0.0f,  () -> Items.DIAMOND);

}
