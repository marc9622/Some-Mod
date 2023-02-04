package somemod.enchanting.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registries;
import somemod.SomeMod;

public class EnchantingEnchantments {
    
    public static Enchantment DASHING = SomeMod.register(Registries.ENCHANTMENT, "dashing", new DashingEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.LEGS));
    
    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}

}
