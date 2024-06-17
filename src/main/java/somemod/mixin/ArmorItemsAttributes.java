package somemod.mixin;

import java.util.ArrayList;
import java.util.Collection;

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
import net.minecraft.item.Item;
import somemod.common.item.ArmorItemsAttributes.AttributeModifierAdder;
import somemod.common.item.ArmorItemsAttributes.AttributePair;

// TODO: Also do this for other kinds of items (such as to allow torches and lava buckets to give warmth when held)
@Mixin(ArmorItem.class)
public abstract class ArmorItemsAttributes {

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
        Collection<AttributePair> modifiers = new ArrayList<>();
        for (AttributeModifierAdder func : somemod.common.item.ArmorItemsAttributes.customAttributes) {
            AttributePair modifier = func.apply(material, type);

            if (modifier != null)
                modifiers.add(modifier);
        }

        if (!modifiers.isEmpty()) {
            for (AttributePair pair : modifiers) {
                builder.put(pair.attribute(), pair.modifier());
            }
            this.setAttributeModifiers(builder.build());
        }
    }

}

