package somemod.crystal.world_gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;
import somemod.crystal.world_gen.feature.CrystalPlacedFeatures;

public class CrystalBiomeModifications {
    
    public static void addCrystalModifications() {

        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(), GenerationStep.Feature.SURFACE_STRUCTURES, CrystalPlacedFeatures.END_CRYSTAL_GLASS_PLACED_FEATURE);

        // TODO: Consider making these biome specific.

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, CrystalPlacedFeatures.ORE_CITRINE);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, CrystalPlacedFeatures.ORE_SAPPHIRE);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, CrystalPlacedFeatures.ORE_RUBY);
        
    }

}
