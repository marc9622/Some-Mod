package somemod.crystal.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class CrystalArmorMaterial implements ArmorMaterial{
    
	private static final int[] BASE_DURABILITY = new int[] {13, 15, 16, 11};
	private static final int[] PROTECTION_VALUES = new int[] {3, 6, 8, 3}; 

    @Override
	public int getDurability(EquipmentSlot slot) {
		return BASE_DURABILITY[slot.getEntitySlotId()] * 42;
	}
 
	@Override
	public int getProtectionAmount(EquipmentSlot slot) {
		return PROTECTION_VALUES[slot.getEntitySlotId()];
	}
 
	@Override
	public int getEnchantability() {
		return 20;
	}
 
	@Override
	public SoundEvent getEquipSound() {
		return SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND; //Currently uses diamond's sound.
	}
 
	@Override
	public Ingredient getRepairIngredient() {
		return Ingredient.ofItems(CrystalItems.CRYSTAL);
	}
 
	@Override
	public String getName() {
		return "crystal";
	}
 
	@Override
	public float getToughness() {
		return 4F;
	}
 
	@Override
	public float getKnockbackResistance() {
		return 0.1F;
	}


}
