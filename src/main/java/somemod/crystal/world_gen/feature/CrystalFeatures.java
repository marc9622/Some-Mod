package somemod.crystal.world_gen.feature;

import net.minecraft.world.gen.feature.Feature;
import somemod.SomeMod;

/**
 * This class is used to register the biome features related to crystals.
 */
public final class CrystalFeatures {
    
    public static final Feature<EndCrystalGlassPillarConfig> END_CRYSTAL_GLASS_PILLAR = SomeMod.registerFeature("end_crystal_glass_pillar", new EndCrystalGlassPillar(EndCrystalGlassPillarConfig.CODEC));

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}

}
