package somemod.frost.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import somemod.SomeMod;

public final class FrostEnchantments {

    public static final Enchantment WINTER_WALKER = SomeMod.registerEnchantment("winter_walker", new WinterWalkerEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.FEET));
    public static final Enchantment FROZEN_QUIVER = SomeMod.registerEnchantment("frozen_quiver", new FrozenQuiverEnchantment(Enchantment.Rarity.UNCOMMON));
    public static final Enchantment ICE_COLD = SomeMod.registerEnchantment("ice_cold", new IceColdEnchantment(Enchantment.Rarity.VERY_RARE));

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}

}

