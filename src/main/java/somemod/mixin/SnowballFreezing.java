package somemod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.util.hit.EntityHitResult;

@Mixin(SnowballEntity.class)
public abstract class SnowballFreezing {

    @Inject(
        method = "onEntityHit(" +
                 "Lnet/minecraft/util/hit/EntityHitResult;" +
                 ")V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/Entity;" +
                     "damage(" +
                     "Lnet/minecraft/entity/damage/DamageSource;" +
                     "F)Z"),
            locals = LocalCapture.CAPTURE_FAILHARD)
    private void freeze(EntityHitResult entityHitResult, CallbackInfo ci, Entity entity) {
        entity.setFrozenTicks(entity.getFrozenTicks() + 4);
    }

}

