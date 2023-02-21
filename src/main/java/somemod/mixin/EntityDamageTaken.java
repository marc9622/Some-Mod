package somemod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import somemod.enchanting.entity.effect.DamageTakenStatusEffect;

@Mixin(LivingEntity.class)
public abstract class EntityDamageTaken {

    @Inject(method = "modifyAppliedDamage(" +
                     "Lnet/minecraft/entity/damage/DamageSource;" +
                     "F)" +
                     "F",
            at = @At("RETURN"),
            cancellable = true)
    private void modifyAppliedDamageAfter(DamageSource source, float amount, CallbackInfoReturnable<Float> cir) {
        LivingEntity entity = (LivingEntity)(Object)this;

        entity.getStatusEffects().forEach(instance -> {
            if(instance.getEffectType() instanceof DamageTakenStatusEffect effect)
                cir.setReturnValue(effect.modifyAppliedDamage(entity, instance.getAmplifier(), source, cir.getReturnValue(), amount));
        });
    }

}
