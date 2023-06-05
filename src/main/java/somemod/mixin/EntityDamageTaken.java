package somemod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import somemod.common.entity.effect.DamageDealtStatusEffect;
import somemod.common.entity.effect.DamageTakenStatusEffect;

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

    @Inject(method = "applyDamage(" +
                     "Lnet/minecraft/entity/damage/DamageSource;" +
                     "F)" +
                     "V",
            at = @At("TAIL"))
    private void applyDamage(DamageSource source, float amount, CallbackInfo cir) {
        LivingEntity entity = (LivingEntity)(Object)this;

        if(source.getAttacker() instanceof LivingEntity attacker)
            attacker.getStatusEffects().forEach(instance -> {
                if(instance.getEffectType() instanceof DamageDealtStatusEffect effect)
                    effect.onDamageApplied(source, amount, entity, attacker, instance);
            });
    }

}
