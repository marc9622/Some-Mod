package somemod.mixin;

import java.util.UUID;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import somemod.frost.entity.attribute.FrostEntityAttributes;

@Mixin(BucketItem.class)
public abstract class LavaBucketWarmth extends ItemAttributeModifiers {

    @Nullable
    private ImmutableMultimap<EntityAttribute, EntityAttributeModifier> attributeModifiers = null;

    @Inject(
        method = "<init>(" +
                 "Lnet/minecraft/fluid/Fluid;" +
                 "Lnet/minecraft/item/Item$Settings;" +
                 ")V",
        at = @At("RETURN"))
    private void addAttributeModifiers(Fluid fluid, Item.Settings settings, CallbackInfo ci) {
        if (fluid == Fluids.LAVA) {
            attributeModifiers = ImmutableMultimap.of(
                FrostEntityAttributes.WARMTH, new EntityAttributeModifier(UUID.fromString("a48e5bcb-2716-4359-8c5e-9be2daf40973"), "Item warmth", 1.0f, Operation.ADDITION)
            );
            return;
        }
    }

    @Override
    public void getAttributeModifiers(EquipmentSlot slot, CallbackInfoReturnable<Multimap<EntityAttribute, EntityAttributeModifier>> cir) {
        if (attributeModifiers == null) return;
        switch (slot) {
            case MAINHAND, OFFHAND -> cir.setReturnValue(attributeModifiers);
            default -> {}
        }
    }

}
