package somemod.frost.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import somemod.common.enchantment.ArrowEnchantment;
import somemod.frost.item.FrostItems;

public class FrozenQuiver extends Enchantment implements ArrowEnchantment {

    protected FrozenQuiver(Rarity weight, EquipmentSlot... slotTypes) {
        super(weight, EnchantmentTarget.BOW, slotTypes);
    }

    @Override
    public ItemStack getProjectileType(PlayerEntity player, ItemStack bow, int level, ItemStack arrows) {
        return new ItemStack(FrostItems.FROSTBITE_ARROW);
    }

}

