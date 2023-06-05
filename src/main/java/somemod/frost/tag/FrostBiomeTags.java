package somemod.frost.tag;

import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.biome.Biome;
import somemod.SomeMod;

public class FrostBiomeTags {

    public static final TagKey<Biome> HAS_SPRUCE_CHEST_SNOWY = TagKey.of(RegistryKeys.BIOME, SomeMod.id("has_spruce_chest/snowy"));
    public static final TagKey<Biome> HAS_SPRUCE_CHEST_TAIGA = TagKey.of(RegistryKeys.BIOME, SomeMod.id("has_spruce_chest/taiga"));
    public static final TagKey<Biome> HAS_SPRUCE_CHEST_MOUNTAIN = TagKey.of(RegistryKeys.BIOME, SomeMod.id("has_spruce_chest/mountain"));
    public static final TagKey<Biome> HAS_ICE_CHEST = TagKey.of(RegistryKeys.BIOME, SomeMod.id("has_ice_chest"));

}
