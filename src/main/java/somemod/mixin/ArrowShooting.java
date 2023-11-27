package somemod.mixin;

import java.util.Map.Entry;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import somemod.common.enchantment.ArrowEnchantment;

@Mixin(BowItem.class)
public abstract class ArrowShooting {

    // TODO: Make this work for non-player entities as well.
    @Redirect(
        method = "onStoppedUsing(" +
                 "Lnet/minecraft/item/ItemStack;" +
                 "Lnet/minecraft/world/World;" +
                 "Lnet/minecraft/entity/LivingEntity;" +
                 "I)V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/player/PlayerEntity;" +
                     "getProjectileType(" +
                     "Lnet/minecraft/item/ItemStack;)" +
                     "Lnet/minecraft/item/ItemStack;"))
    private ItemStack getProjectileType(PlayerEntity player, ItemStack bow) {
        ItemStack arrows = player.getProjectileType(bow);

        for (Entry<Enchantment, Integer> entry : EnchantmentHelper.get(bow).entrySet()) {
            Enchantment enchantment = entry.getKey();
            int level = entry.getValue();
            if (enchantment instanceof ArrowEnchantment arrowEnchantment)
                arrows = arrowEnchantment.getProjectileType(player, bow, level, arrows);
        }

        return arrows;
    }

}

