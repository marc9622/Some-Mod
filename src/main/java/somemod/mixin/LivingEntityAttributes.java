package somemod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;

@Mixin(LivingEntity.class)
public abstract class LivingEntityAttributes {

    @Inject(
        method = "createLivingAttributes(" +
                 ")Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;",
        at = @At("RETURN"),
        cancellable = true)
    private static void createLivingAttributes(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        DefaultAttributeContainer.Builder builder = cir.getReturnValue();
        for (EntityAttribute attribute : somemod.common.entity.LivingEntityAttributes.customAttributes) {
            builder = builder.add(attribute);
        }
        cir.setReturnValue(builder);
    }

}
