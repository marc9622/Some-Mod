package somemod.frost.item;

import static somemod.utils.ArmorMaterialBuilder.of;

import java.util.UUID;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import somemod.common.item.ArmorItemsAttributes;
import somemod.common.item.ArmorItemsAttributes.AttributePair;
import somemod.frost.entity.attribute.FrostEntityAttributes;

public final class FrostArmorMaterials {

    //TODO: Make original materials for some of these
    public static final ArmorMaterial ARCTIC    = of("arctic",    14, 2, 4, 3, 1, 10, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.05f, () -> Items.SNOW);
    public static final ArmorMaterial GLACIER   = of("glacier",   15, 2, 6, 4, 2, 12, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 	0.0f, 0.1f,  () -> Items.ICE);
    public static final ArmorMaterial BLIZZARD  = of("blizzard",  11, 2, 6, 5, 2, 12, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.1f,  () -> Items.SNOW);
    public static final ArmorMaterial FROSTBITE = of("frostbite", 19, 3, 8, 6, 3, 14, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.5f, 0.0f,  () -> Items.PACKED_ICE);
    public static final ArmorMaterial ICE_QUEEN = of("ice_queen", 24, 3, 8, 6, 3, 28, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.5f, 0.0f,  () -> Items.DIAMOND);

    static {
        ArmorItemsAttributes.customAttributes.add((material, type) -> {
            if (material == ArmorMaterials.LEATHER) {
                return new AttributePair(FrostEntityAttributes.WARMTH, switch (type) {
                    case HELMET     -> new EntityAttributeModifier(UUID.fromString("231f8b34-cd42-4a51-b0bc-824fc9f7a17a"), "Armor warmth", 0.8f, Operation.ADDITION);
                    case CHESTPLATE -> new EntityAttributeModifier(UUID.fromString("bcf29fb1-b1ae-458c-a202-fefe0dd6e095"), "Armor warmth", 1.2f, Operation.ADDITION);
                    case LEGGINGS   -> new EntityAttributeModifier(UUID.fromString("64154915-bfcb-47d6-ad7f-a9e7267486a6"), "Armor warmth", 1.0f, Operation.ADDITION);
                    case BOOTS      -> new EntityAttributeModifier(UUID.fromString("beb9b060-2c0d-46c0-9612-7989c74c6ed3"), "Armor warmth", 0.5f, Operation.ADDITION);
                });
            }
            return null;
        });
    }

    public static Multimap<EntityAttribute, EntityAttributeModifier> arctic(EquipmentSlot slot) {
        return ImmutableMultimap.of(FrostEntityAttributes.WARMTH, switch (slot) {
            case HEAD  -> new EntityAttributeModifier(UUID.fromString("a7534de4-e7f6-432b-a13c-482e1b416485"), "Armor warmth", 1.2f, Operation.ADDITION);
            case CHEST -> new EntityAttributeModifier(UUID.fromString("1e29a6bf-75ab-48cb-bc76-cb086d718339"), "Armor warmth", 1.5f, Operation.ADDITION);
            case LEGS  -> new EntityAttributeModifier(UUID.fromString("f0c4cc59-28bd-47d4-afbb-b06adc4ef169"), "Armor warmth", 1.4f, Operation.ADDITION);
            case FEET  -> new EntityAttributeModifier(UUID.fromString("01ca0c88-bfc2-4eff-9d8d-0a8853d5caa4"), "Armor warmth", 1.0f, Operation.ADDITION);
            default -> throw new IllegalArgumentException();
        });
    }

    public static Multimap<EntityAttribute, EntityAttributeModifier> glacier(EquipmentSlot slot) {
        return ImmutableMultimap.of(FrostEntityAttributes.WARMTH, switch (slot) {
            case HEAD  -> new EntityAttributeModifier(UUID.fromString("182d117b-4585-4fac-b62e-a561c29cdc22"), "Armor warmth", -0.6f, Operation.ADDITION);
            case CHEST -> new EntityAttributeModifier(UUID.fromString("22320e5d-ab2b-403a-8ff1-b0a2cc418355"), "Armor warmth", -1.0f, Operation.ADDITION);
            case LEGS  -> new EntityAttributeModifier(UUID.fromString("ea172f23-46b8-464a-a125-bb4553094c30"), "Armor warmth", -0.8f, Operation.ADDITION);
            case FEET  -> new EntityAttributeModifier(UUID.fromString("9ec78043-be6f-4706-b260-206fdead3671"), "Armor warmth", -0.4f, Operation.ADDITION);
            default -> throw new IllegalArgumentException();
        });
    }

    public static Multimap<EntityAttribute, EntityAttributeModifier> frostbite(EquipmentSlot slot) {
        return ImmutableMultimap.of(FrostEntityAttributes.WARMTH, switch (slot) {
            case HEAD  -> new EntityAttributeModifier(UUID.fromString("0991ce63-94b0-4221-9e94-7bd467922f83"), "Armor warmth", -1.2f, Operation.ADDITION);
            case CHEST -> new EntityAttributeModifier(UUID.fromString("2fcbe4c9-4f47-4ff7-a55b-8234d43f7bef"), "Armor warmth", -1.5f, Operation.ADDITION);
            case LEGS  -> new EntityAttributeModifier(UUID.fromString("89726807-4cef-4377-b320-46f062be0696"), "Armor warmth", -1.4f, Operation.ADDITION);
            case FEET  -> new EntityAttributeModifier(UUID.fromString("3dc2f9b7-570a-4981-8b15-b24494bdcde3"), "Armor warmth", -1.0f, Operation.ADDITION);
            default -> throw new IllegalArgumentException();
        });
    }
}
