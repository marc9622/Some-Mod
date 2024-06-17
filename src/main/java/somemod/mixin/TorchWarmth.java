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

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;
import net.minecraft.item.Item;
import net.minecraft.item.VerticallyAttachableBlockItem;
import net.minecraft.util.math.Direction;
import somemod.frost.entity.attribute.FrostEntityAttributes;

@Mixin(VerticallyAttachableBlockItem.class)
public abstract class TorchWarmth extends ItemAttributeModifiers {

    @Nullable
    private ImmutableMultimap<EntityAttribute, EntityAttributeModifier> attributeModifiers = null;

    @Inject(
        method = "<init>(" +
                 "Lnet/minecraft/block/Block;" +
                 "Lnet/minecraft/block/Block;" +
                 "Lnet/minecraft/item/Item$Settings;" +
                 "Lnet/minecraft/util/math/Direction;" +
                 ")V",
        at = @At("RETURN"))
    private void addAttributeModifiers(Block standingBlock, Block wallBlock, Item.Settings settings, Direction verticalAttachmentDirection, CallbackInfo ci) {
        if (standingBlock == Blocks.TORCH || standingBlock == Blocks.REDSTONE_TORCH || standingBlock == Blocks.SOUL_TORCH) {
            attributeModifiers = ImmutableMultimap.of(
                FrostEntityAttributes.WARMTH, new EntityAttributeModifier(UUID.fromString("a48e5bcb-2716-4359-8c5e-9be2daf40973"), "Item warmth", 0.5f, Operation.ADDITION)
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
