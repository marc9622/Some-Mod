package somemod.crystal.item;

import net.minecraft.item.ArmorItem.Type;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public enum CrystalArmorMaterials implements ArmorMaterial {
    
    CRYSTAL("crystal",           33, 3, 8, 6, 3, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,  4.0f, 0.0f, Ingredient.ofItems(CrystalItems.CRYSTAL)),
	DRAGON_SCALE("dragon_scale", 42, 2, 8, 6, 3,  9, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,2.5f, 0.15f,Ingredient.ofItems(Items.NETHERITE_INGOT)); // TODO: Make original material

    private static final int BASE_DURABILITY_HEAD = 11;
    private static final int BASE_DURABILITY_CHEST = 16;
    private static final int BASE_DURABILITY_LEGS = 15;
    private static final int BASE_DURABILITY_FEET = 13;

	private final String name;
	private final int durabilityMultiplier;
    private final int protectionHead;
    private final int protectionChest;
    private final int protectionLegs;
    private final int protectionFeet;
	private final int enchantability;
	private final SoundEvent equipSound;
	private final float toughness;
	private final float knockbackResistance;
	private final Ingredient repairIngredient;

	private CrystalArmorMaterials(String name, int durabilityMultiplier, int protectionHead, int protectionChest, int protectionLegs, int protectionFeet, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Ingredient repairIngredient) {
		this.name = name;
		this.durabilityMultiplier = durabilityMultiplier;
        this.protectionHead = protectionHead;
        this.protectionChest = protectionChest;
        this.protectionLegs = protectionLegs;
        this.protectionFeet = protectionFeet;
		this.enchantability = enchantability;
		this.equipSound = equipSound;
		this.toughness = toughness;
		this.knockbackResistance = knockbackResistance;
		this.repairIngredient = repairIngredient;
	}

    @Override
	public int getDurability(Type slot) {
        return switch (slot) {
            case HELMET -> BASE_DURABILITY_HEAD;
            case CHESTPLATE -> BASE_DURABILITY_CHEST;
            case LEGGINGS -> BASE_DURABILITY_LEGS;
            case BOOTS -> BASE_DURABILITY_FEET;
        } * this.durabilityMultiplier;
	}
 
	@Override
	public int getProtection(Type slot) {
        return switch (slot) {
            case HELMET -> protectionHead;
            case CHESTPLATE -> protectionChest;
            case LEGGINGS -> protectionLegs;
            case BOOTS -> protectionFeet;
        };
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
