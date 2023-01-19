package somemod.crystal.world.feature;

import net.minecraft.registry.Registries;
import net.minecraft.world.gen.feature.Feature;
import somemod.SomeMod;

/**
 * This class is used to register the biome features related to crystals.
 */
public final class CrystalFeatures {
    
    public static final Feature<EndCrystalGlassPillarConfig> END_CRYSTAL_GLASS_PILLAR = new EndCrystalGlassPillar(EndCrystalGlassPillarConfig.CODEC);

    /**
     * Registers the biome features related to crystals.
     * Is called by the {@link somemod.SomeMod} class.
     */
    public static void register() {

        SomeMod.register(Registries.FEATURE, "end_crystal_glass_pillar", END_CRYSTAL_GLASS_PILLAR);
        
    }

}
