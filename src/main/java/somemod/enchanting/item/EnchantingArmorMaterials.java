package somemod.enchanting.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public enum EnchantingArmorMaterials implements ArmorMaterial {
    
	//TODO: Make original materials for some of these
	ARCANE("arcane_robe", 6, new int[]{1, 2, 3, 1}, 27, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, Ingredient.ofItems(Items.LEATHER)),
	HONEY("honey", 		5,  new int[]{1, 3, 4, 1}, 12, SoundEvents.ITEM_ARMOR_EQUIP_GOLD,    0.0f, 0.05f, Ingredient.ofItems(Items.HONEYCOMB)),
	PIRATE("pirate", 	11, new int[]{1, 3, 5, 1}, 6,  SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, Ingredient.ofItems(Items.LEATHER)),
	ARCTIC("arctic", 	14, new int[]{1, 3, 5, 2}, 10, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.05f, Ingredient.ofItems(Items.SNOW)),
	ALCHEMIST("alchemist", 7, new int[]{2, 3, 4, 2}, 17, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, Ingredient.ofItems(Items.GLASS_BOTTLE)),
	ELVEN("elven", 		12, new int[]{2, 3, 4, 2}, 22, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, Ingredient.ofItems(Items.LEATHER)),
	OCEANIC("oceanic", 	10, new int[]{2, 4, 4, 2}, 15, SoundEvents.ITEM_ARMOR_EQUIP_TURTLE,  0.0f, 0.0f, Ingredient.ofItems(Items.FIRE_CORAL)),
	DESERT("desert_nomad", 9, new int[]{1, 4, 5, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, Ingredient.ofItems(Items.SANDSTONE)),
	LUNAR("lunar", 		12, new int[]{1, 4, 5, 2}, 19, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 	 0.0f, 0.0f, Ingredient.ofItems(Items.IRON_INGOT)),
	PHANTOM("phantom", 	7, 	new int[]{2, 4, 5, 2}, 10, SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA,  0.0f, 0.0f, Ingredient.ofItems(Items.AIR)),
	DIVINE("divine", 	25, new int[]{2, 4, 5, 2}, 22, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 	 0.5f, 0.0f, Ingredient.ofItems(Items.GOLD_INGOT)),
	GLACIER("glacier", 	15, new int[]{2, 4, 6, 2}, 12, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 	 0.0f, 0.1f, Ingredient.ofItems(Items.ICE)),
	NECROTIC("necrotic", 14, new int[]{2, 4, 5, 3}, 6, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0.0f, 0.0f, Ingredient.ofItems(Items.BONE)),
	LIVING("living",    14, new int[]{2, 4, 5, 3}, 17, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.5f, 0.0f, Ingredient.ofItems(Items.OAK_WOOD)),
	SHADOW("shadow", 	9, 	new int[]{3, 4, 5, 3}, 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0.0f, 0.0f, Ingredient.ofItems(Items.NETHER_BRICK, Items.SOUL_SAND)),
	ANGELIC("angelic", 	15, new int[]{3, 5, 6, 3}, 19, SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA,  0.0f, 0.0f, Ingredient.ofItems(Items.ELYTRA)),
	DEEP_SEA("deep_sea", 23, new int[]{3, 5, 6, 2}, 17, SoundEvents.ITEM_ARMOR_EQUIP_TURTLE, 0.0f, 0.05f, Ingredient.ofItems(Items.FIRE_CORAL)),
	MAGMA("magma", 		20, new int[]{3, 5, 6, 4}, 12, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0.5f, 0.0f, Ingredient.ofItems(Items.MAGMA_CREAM, Items.BLAZE_ROD)),
	GUARDIAN("guardian", 30, new int[]{3, 5, 7, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.0f, 0.05f, Ingredient.ofItems(Items.DIAMOND));

	private static final int[] BASE_DURABILITY = new int[] {13, 15, 16, 11};
	private final String name;
	private final int durabilityMultiplier;
	private final int[] protectionAmounts;
	private final int enchantability;
	private final SoundEvent equipSound;
	private final float toughness;
	private final float knockbackResistance;
	private final Ingredient repairIngredient;

	private EnchantingArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Ingredient repairIngredient) {
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
