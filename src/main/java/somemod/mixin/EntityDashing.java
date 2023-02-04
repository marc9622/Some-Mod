package somemod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import somemod.enchanting.enchantment.DashingEnchantment;
import somemod.enchanting.enchantment.EnchantingEnchantments;

@Mixin(LivingEntity.class)
public abstract class EntityDashing {

    @Inject(method = "setSprinting(Z)V",
            at = @At("HEAD"))
    private void setSprinting(boolean sprinting, CallbackInfo info) {
        if(sprinting) {
            LivingEntity entity = (LivingEntity)(Object)this;
            int enchantmentLevel = EnchantmentHelper.getEquipmentLevel(EnchantingEnchantments.DASHING, entity);
            if(DashingEnchantment.canStartDash(entity, enchantmentLevel))
                DashingEnchantment.startDash(entity, enchantmentLevel);
        }
    }
    
    @Inject(method = "tickMovement()V",
            at = @At("HEAD"))
    private void tickMovement(CallbackInfo info) {
        LivingEntity entity = (LivingEntity)(Object)this;
        DashingEnchantment.tickDash(entity);
    }

}
