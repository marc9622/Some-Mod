package somemod.enchanting.tag;

import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.biome.Biome;
import somemod.SomeMod;

public class EnchantingHasStructureBiomeTags {
    
    public static final TagKey<Biome> ENCHANTING_TOWER_COMMON_HAS_STRUCTURE = TagKey.of(RegistryKeys.BIOME, SomeMod.id("has_structure/enchanting_tower_common"));
    public static final TagKey<Biome> ENCHANTING_TOWER_RARE_HAS_STRUCTURE   = TagKey.of(RegistryKeys.BIOME, SomeMod.id("has_structure/enchanting_tower_rare"));

}
