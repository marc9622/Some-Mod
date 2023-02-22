package somemod.magic.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registries;
import somemod.SomeMod;

public class MagicEnchantments {
    
    public static final Enchantment DASHING = SomeMod.register(Registries.ENCHANTMENT, "dashing", new DashingEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.LEGS));
    public static final Enchantment HERMES  = SomeMod.register(Registries.ENCHANTMENT, "hermes",  new HermesEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.LEGS));
    public static final Enchantment DULLNESS_CURSE = SomeMod.register(Registries.ENCHANTMENT, "dullness_curse", new DullnessCurseEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.values()));

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}

}
