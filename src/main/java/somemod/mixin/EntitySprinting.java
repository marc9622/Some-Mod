package somemod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.LivingEntity;
import somemod.enchanting.enchantment.DashingEnchantment;
import somemod.enchanting.enchantment.HermesEnchantment;

@Mixin(LivingEntity.class)
public abstract class EntitySprinting {

    @Inject(method = "setSprinting(Z)V",
            at = @At("HEAD"))
    private void setSprinting(boolean sprinting, CallbackInfo info) {
        if(sprinting) {
            LivingEntity entity = (LivingEntity)(Object)this;
            DashingEnchantment.startDash(entity);
        }
    }
    
    @Inject(method = "tickMovement()V",
            at = @At("HEAD"))
    private void tickMovement(CallbackInfo info) {
        LivingEntity entity = (LivingEntity)(Object)this;
        DashingEnchantment.tickDash(entity);
        HermesEnchantment.tickHermes(entity);
    }

}
