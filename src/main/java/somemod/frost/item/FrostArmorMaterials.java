package somemod.frost.item;

import net.minecraft.item.ArmorItem.Type;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public enum FrostArmorMaterials implements ArmorMaterial {
    
    //TODO: Make original materials for some of these
	ARCTIC("arctic",       14, 2, 5, 3, 1, 10, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.05f, Ingredient.ofItems(Items.SNOW)),
    GLACIER("glacier",     15, 2, 6, 4, 2, 12, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 	0.0f, 0.1f, Ingredient.ofItems(Items.ICE)),
    BLIZZARD("blizzard",   11, 2, 6, 5, 2, 12, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.1f, Ingredient.ofItems(Items.SNOW)),
    FROSTBITE("frostbite", 19, 3, 8, 6, 3, 14, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.5f, 0.0f, Ingredient.ofItems(Items.PACKED_ICE)),
    ICE_QUEEN("ice_queen", 24, 3, 8, 6, 3, 28, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.5f, 0.0f, Ingredient.ofItems(Items.DIAMOND));

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

	private FrostArmorMaterials(String name, int durabilityMultiplier, int protectionHead, int protectionChest, int protectionLegs, int protectionFeet, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Ingredient repairIngredient) {
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
