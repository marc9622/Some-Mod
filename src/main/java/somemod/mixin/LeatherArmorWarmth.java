package somemod.mixin;

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
import somemod.frost.item.FrostArmorMaterials;

// TODO: Also do this for other kinds of items (such as to allow torches and lava buckets to give warmth when held)
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
    private void addAttributeModifiers(ArmorMaterial material, ArmorItem.Type type, Item.Settings settings, CallbackInfo ci, ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder) {
        if (material == ArmorMaterials.LEATHER) {
            builder.putAll(FrostArmorMaterials.leather(type.getEquipmentSlot()));
            this.setAttributeModifiers(builder.build());
        }
    }

}

