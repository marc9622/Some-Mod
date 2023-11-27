package somemod.common.enchantment;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public interface ArrowEnchantment {

    public ItemStack getProjectileType(PlayerEntity player, ItemStack bow, int level, ItemStack arrows);

}

