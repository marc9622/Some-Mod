package somemod.frost.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public enum FrostArmorMaterials implements ArmorMaterial {
    
    //TODO: Make original materials for some of these
	ARCTIC("arctic",       14, new int[]{1, 3, 5, 2}, 10, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.05f, Ingredient.ofItems(Items.SNOW)),
    GLACIER("glacier",     15, new int[]{2, 4, 6, 2}, 12, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 	0.0f, 0.1f, Ingredient.ofItems(Items.ICE)),
    BLIZZARD("blizzard",   11, new int[]{2, 5, 6, 2}, 12, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.1f, Ingredient.ofItems(Items.SNOW)),
    FROSTBITE("frostbite", 19, new int[]{3, 6, 8, 3}, 14, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.5f, 0.0f, Ingredient.ofItems(Items.PACKED_ICE)),
    ICE_QUEEN("ice_queen", 24, new int[]{3, 6, 8, 3}, 28, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.5f, 0.0f, Ingredient.ofItems(Items.DIAMOND));

	private static final int[] BASE_DURABILITY = new int[] {13, 15, 16, 11};
	private final String name;
	private final int durabilityMultiplier;
	private final int[] protectionAmounts;
	private final int enchantability;
	private final SoundEvent equipSound;
	private final float toughness;
	private final float knockbackResistance;
	private final Ingredient repairIngredient;

	private FrostArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Ingredient repairIngredient) {
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
