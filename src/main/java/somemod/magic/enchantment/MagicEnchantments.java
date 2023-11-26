package somemod.magic.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import somemod.SomeMod;

public class MagicEnchantments {
    
    public static final Enchantment DASHING        = SomeMod.registerEnchantment("dashing",        new DashingEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.LEGS));
    public static final Enchantment HERMES         = SomeMod.registerEnchantment("hermes_sprint",  new HermesEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.LEGS));
    public static final Enchantment BLAST_STRIKE   = SomeMod.registerEnchantment("blast_strike",   new BlastStrikeEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.MAINHAND));
    public static final Enchantment DULLNESS_CURSE = SomeMod.registerEnchantment("dullness_curse", new DullnessCurseEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.values()));

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}

}
