package somemod.mixin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import somemod.common.enchantment.SprintingEnchantment;
import somemod.common.enchantment.SprintingEnchantment.StackingBehavior;

@Mixin(LivingEntity.class)
public abstract class EntitySprinting {

    @Inject(method = "setSprinting(Z)V",
            at = @At("HEAD"))
    private void setSprinting(boolean sprinting, CallbackInfo info) {
        LivingEntity entity = (LivingEntity)(Object)this;

        invokeOnSprintingEnchantment(entity, (enchantment, level) -> {
            if (sprinting) enchantment.onStartSprint(entity, level);
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
        // would normally have created a class that contains these three values, but Mixins does not allow defining new classes in the same file.
        List<SprintingEnchantment> nonStackableEnchs = null;
        List<Identifier> nonStackableIds = null;
        List<Integer> nonStackableLevels = null;

        for (ItemStack stack : entity.getItemsEquipped()) {
            NbtList nbtList = stack.getEnchantments();

            nextEnchantment: for (int i = 0; i < nbtList.size(); ++i) {
                NbtCompound nbtCompound = nbtList.getCompound(i);
                Identifier id = EnchantmentHelper.getIdFromNbt(nbtCompound);
                Optional<Enchantment> ench = Registries.ENCHANTMENT.getOrEmpty(id);

                if (ench.isPresent() && ench.get() instanceof SprintingEnchantment sprintEnch) {
                    int level = EnchantmentHelper.getLevelFromNbt(nbtCompound);
                    StackingBehavior stackingBehavior = sprintEnch.stackingBehavior();

                    if (stackingBehavior == StackingBehavior.ApplyIndividually) {
                        consumer.accept(sprintEnch, level);
                        continue nextEnchantment;
                    }

                    if (nonStackableEnchs  == null) {
                        nonStackableEnchs  = new ArrayList<>(2);
                        nonStackableIds    = new ArrayList<>(2);
                        nonStackableLevels = new ArrayList<>(2);
                    }

                    for (int j = 0; j < nonStackableEnchs.size(); j++) {
                        if (nonStackableIds.get(j) == id) {
                            if (stackingBehavior == StackingBehavior.ApplySummed)
                                nonStackableLevels.set(j, nonStackableLevels.get(j) + level);
                            else /* stackingBehavior == StackingBehavior.ApplyMax */
                                nonStackableLevels.set(j, Math.max(nonStackableLevels.get(j), level));

                            continue nextEnchantment;
                        }
                    }

                    // if no matching enchantment was found
                    nonStackableEnchs.add(sprintEnch);
                    nonStackableIds.add(id);
                    nonStackableLevels.add(level);
                }
            }
        }

        if (nonStackableEnchs != null) {
            for (int i = 0; i < nonStackableEnchs.size(); i++)
                consumer.accept(nonStackableEnchs.get(i), nonStackableLevels.get(i));
        }
    }

}
