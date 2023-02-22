package somemod.crystal.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public enum CrystalArmorMaterials implements ArmorMaterial {
    
	CRYSTAL("crystal", 33, new int[]{3, 6, 8, 3}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 4.0f, 0.0f, Ingredient.ofItems(CrystalItems.CRYSTAL)),
	DRAGON_SCALE("dragon_scale_armor", 42, new int[]{3, 6, 8, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2.5f, 0.15f, Ingredient.ofItems(Items.NETHERITE_INGOT)); //TODO: Make original material

	private static final int[] BASE_DURABILITY = new int[] {13, 15, 16, 11};
	private final String name;
	private final int durabilityMultiplier;
	private final int[] protectionAmounts;
	private final int enchantability;
	private final SoundEvent equipSound;
	private final float toughness;
	private final float knockbackResistance;
	private final Ingredient repairIngredient;

	private CrystalArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Ingredient repairIngredient) {
		this.name = name;
		this.durabilityMultiplier = durabilityMultiplier;
		this.protectionAmounts = protectionAmounts;
		this.enchantability = enchantability;
		this.equipSound = equipSound;
		this.toughness = toughness;
		this.knockbackResistance = knockbackResistance;
		this.repairIngredient = repairIngredient;
	}

    @Override
	public int getDurability(EquipmentSlot slot) {
		return BASE_DURABILITY[slot.getEntitySlotId()] * this.durabilityMultiplier;
	}
 
	@Override
	public int getProtectionAmount(EquipmentSlot slot) {
		return this.protectionAmounts[slot.getEntitySlotId()];
	}
 
	@Override
	public int getEnchantability() {
		return this.enchantability;
	}
 
	@Override
	public SoundEvent getEquipSound() {
		return this.equipSound;
	}
 
	@Override
	public Ingredient getRepairIngredient() {
		return this.repairIngredient;
	}
 
	@Override
	public String getName() {
		return this.name;
	}
 
	@Override
	public float getToughness() {
		return this.toughness;
	}
 
	@Override
	public float getKnockbackResistance() {
		return this.knockbackResistance;
	}

}
