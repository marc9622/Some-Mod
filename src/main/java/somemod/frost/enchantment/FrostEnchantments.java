package somemod.frost.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import somemod.SomeMod;

public final class FrostEnchantments {

    public static final Enchantment WINTER_WALKER = SomeMod.registerEnchantment("winter_walker", new WinterWalkerEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.FEET));

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}

}

