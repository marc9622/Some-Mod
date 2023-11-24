package somemod.mixin;

import java.util.Map.Entry;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import somemod.common.enchantment.WalkingSpeedEnchantment;

@Mixin(LivingEntity.class)
public abstract class EntityMovement {

    @Shadow
    private float getMovementSpeed(float slipperiness) { return 0.0f; }

    @Redirect(
        method = "applyMovementInput(" +
                 "Lnet/minecraft/util/math/Vec3d;" +
                 "F)" +
                 "Lnet/minecraft/util/math/Vec3d;",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/LivingEntity;" +
                     "getMovementSpeed(F)F"))
    private float getMovementSpeed(LivingEntity entity, float slipperiness) {
        float speed = getMovementSpeed(slipperiness);

        for (ItemStack stack : entity.getArmorItems()) {
            for (Entry<Enchantment, Integer> entry : EnchantmentHelper.get(stack).entrySet()) {
                if (entry.getKey() instanceof WalkingSpeedEnchantment enchantment) {
                    speed *= enchantment.scaleMovementInput(entity, entry.getValue());
                }
            }
        }

        return speed;
    }

}

