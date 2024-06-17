package somemod.mixin;

import java.util.UUID;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Item;
import somemod.frost.entity.attribute.FrostEntityAttributes;

@Mixin(ArmorItem.class)
public abstract class LeatherArmorWarmth {

    @Accessor("attributeModifiers") @Mutable
    public abstract void setAttributeModifiers(Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers);

    @Inject(
        method = "<init>(" +
                 "Lnet/minecraft/item/ArmorMaterial;" +
                 "Lnet/minecraft/item/ArmorItem$Type;" +
                 "Lnet/minecraft/item/Item$Settings;" +
                 ")V",
        at = @At("TAIL"),
        locals = LocalCapture.CAPTURE_FAILHARD)
    private void addWarmth(ArmorMaterial material, ArmorItem.Type type, Item.Settings settings, CallbackInfo ci, ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder) {
        if (material == ArmorMaterials.LEATHER) {
            builder.put(FrostEntityAttributes.WARMTH, switch (type) {
                case HELMET     -> new EntityAttributeModifier(UUID.fromString("231f8b34-cd42-4a51-b0bc-824fc9f7a17a"), "Armor warmth", 0.8f, EntityAttributeModifier.Operation.ADDITION);
                case CHESTPLATE -> new EntityAttributeModifier(UUID.fromString("bcf29fb1-b1ae-458c-a202-fefe0dd6e095"), "Armor warmth", 1.2f, EntityAttributeModifier.Operation.ADDITION);
                case LEGGINGS   -> new EntityAttributeModifier(UUID.fromString("64154915-bfcb-47d6-ad7f-a9e7267486a6"), "Armor warmth", 1.0f, EntityAttributeModifier.Operation.ADDITION);
                case BOOTS      -> new EntityAttributeModifier(UUID.fromString("beb9b060-2c0d-46c0-9612-7989c74c6ed3"), "Armor warmth", 0.6f, EntityAttributeModifier.Operation.ADDITION);
            });
            this.setAttributeModifiers(builder.build());
        }
    }

}

