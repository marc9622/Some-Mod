package somemod.crystal.item;

import static somemod.utils.ArmorMaterialBuilder.of;

import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;

public final class CrystalArmorMaterials {
    
    public static final ArmorMaterial CRYSTAL      = of("crystal",      33, 3, 8, 6, 3, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,   4.0f, 0.0f,  () -> CrystalItems.CRYSTAL);
    public static final ArmorMaterial DRAGON_SCALE = of("dragon_scale", 42, 2, 8, 6, 3,  9, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2.5f, 0.15f, () -> Items.NETHERITE_INGOT);

}
