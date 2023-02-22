package somemod.mixin;

import java.util.function.BiConsumer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.Registries;
import somemod.magic.enchantment.SprintingEnchantment;

@Mixin(LivingEntity.class)
public abstract class EntitySprinting {

    @Inject(method = "setSprinting(Z)V",
            at = @At("HEAD"))
    private void setSprinting(boolean sprinting, CallbackInfo info) {
        LivingEntity entity = (LivingEntity)(Object)this;

        invokeOnSprintingEnchantment(entity, (enchantment, level) -> {
            if(sprinting) enchantment.onStartSprint(entity, level);
            else enchantment.onEndSprint(entity, level);
        });
    }
    
    @Inject(method = "tickMovement()V",
            at = @At("HEAD"))
    private void tickMovement(CallbackInfo info) {
        LivingEntity entity = (LivingEntity)(Object)this;
        
        SprintingEnchantment.beforeTickSprintEnchantments(entity);
        invokeOnSprintingEnchantment(entity, (enchantment, level) -> 
            enchantment.tickSprintEnchantment(entity, level)
        );
    }

    private void invokeOnSprintingEnchantment(LivingEntity entity, BiConsumer<SprintingEnchantment, Integer> consumer) {
        for(ItemStack stack : entity.getItemsEquipped()) {
            NbtList nbtList = stack.getEnchantments();
            for (int i = 0; i < nbtList.size(); ++i) {
                NbtCompound nbtCompound = nbtList.getCompound(i);
                Registries.ENCHANTMENT.getOrEmpty(EnchantmentHelper.getIdFromNbt(nbtCompound))
                                        .ifPresent(ench -> {
                                            if(ench instanceof SprintingEnchantment sprintEnch)
                                                consumer.accept(sprintEnch, EnchantmentHelper.getLevelFromNbt(nbtCompound));
                                        });
            }
        }
    }

}
