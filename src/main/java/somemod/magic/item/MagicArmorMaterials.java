package somemod.magic.item;

import net.minecraft.item.ArmorItem.Type;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public enum MagicArmorMaterials implements ArmorMaterial {
    
	//TODO: Make original materials for some of these
	ARCANE("arcane_robe", 6, 1, 3, 2, 1, 27, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, Ingredient.ofItems(Items.LEATHER)),
	PIRATE("pirate", 	 11, 1, 4, 3, 1,  6, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, Ingredient.ofItems(Items.LEATHER)),
	HONEY("honey", 		  5, 1, 4, 3, 1, 12, SoundEvents.ITEM_ARMOR_EQUIP_GOLD,    0.0f, 0.05f,Ingredient.ofItems(Items.HONEYCOMB)),
	ALCHEMIST("alchemist",7, 2, 4, 3, 2, 17, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, Ingredient.ofItems(Items.GLASS_BOTTLE)),
	ELVEN("elven", 		 12, 2, 4, 3, 2, 22, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, Ingredient.ofItems(Items.IRON_INGOT)),//MagicItems.ELVEN_STEEL)),
	OCEANIC("oceanic", 	 10, 2, 4, 4, 2, 15, SoundEvents.ITEM_ARMOR_EQUIP_TURTLE,  0.0f, 0.0f, Ingredient.ofItems(Items.FIRE_CORAL)),
	DESERT("desert_nomad",9, 2, 5, 4, 1,  9, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, Ingredient.ofItems(Items.SANDSTONE)),
	LUNAR("lunar", 		 12, 2, 5, 4, 1, 19, SoundEvents.ITEM_ARMOR_EQUIP_IRON,    0.0f, 0.0f, Ingredient.ofItems(Items.IRON_INGOT)),
	PHANTOM("phantom", 	  7, 2, 5, 4, 2, 10, SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA,  0.0f, 0.0f, Ingredient.ofItems(Items.AIR)),
	DIVINE("divine", 	 25, 2, 5, 4, 2, 22, SoundEvents.ITEM_ARMOR_EQUIP_GOLD,    0.5f, 0.0f, Ingredient.ofItems(Items.GOLD_INGOT)),
	NECROTIC("necrotic", 14, 3, 5, 4, 2,  6, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,0.0f,0.0f, Ingredient.ofItems(Items.BONE)),
	LIVING("living",     14, 3, 5, 4, 2, 17, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.5f, 0.0f, Ingredient.ofItems(Items.OAK_WOOD)),
	SHADOW("shadow", 	  9, 3, 5, 4, 3, 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,0.0f,0.0f, Ingredient.ofItems(Items.NETHER_BRICK, Items.SOUL_SAND)),
	ANGELIC("angelic", 	 15, 3, 6, 5, 3, 19, SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA,  0.0f, 0.0f, Ingredient.ofItems(Items.ELYTRA)),
	DEEP_SEA("deep_sea", 23, 3, 6, 5, 3, 17, SoundEvents.ITEM_ARMOR_EQUIP_TURTLE,  0.0f, 0.05f,Ingredient.ofItems(Items.FIRE_CORAL)),
	MAGMA("magma", 		 20, 4, 6, 5, 3, 12, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,0.5f,0.0f, Ingredient.ofItems(Items.MAGMA_CREAM, Items.BLAZE_ROD)),
	GUARDIAN("guardian", 30, 3, 7, 5, 3, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.0f, 0.05f,Ingredient.ofItems(Items.DIAMOND));

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

	private MagicArmorMaterials(String name, int durabilityMultiplier, int protectionHead, int protectionChest, int protectionLegs, int protectionFeet, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Ingredient repairIngredient) {
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
