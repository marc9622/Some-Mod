package somemod.frost.world_gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;

import somemod.frost.tag.FrostBiomeTags;
import somemod.frost.world_gen.feature.FrostPlacedFeatures;

public class FrostBiomeModifications {

    public static void addFrostModifications() {

        BiomeModifications.addFeature(BiomeSelectors.tag(FrostBiomeTags.HAS_SPRUCE_CHEST_SNOWY), GenerationStep.Feature.SURFACE_STRUCTURES, FrostPlacedFeatures.SPRUCE_CHEST_SNOWY);
        BiomeModifications.addFeature(BiomeSelectors.tag(FrostBiomeTags.HAS_SPRUCE_CHEST_TAIGA), GenerationStep.Feature.SURFACE_STRUCTURES, FrostPlacedFeatures.SPRUCE_CHEST_TAIGA);
        BiomeModifications.addFeature(BiomeSelectors.tag(FrostBiomeTags.HAS_SPRUCE_CHEST_MOUNTAIN), GenerationStep.Feature.SURFACE_STRUCTURES, FrostPlacedFeatures.SPRUCE_CHEST_MOUNTAIN);
        // TODO: maybe add an underground variant?

        BiomeModifications.addFeature(BiomeSelectors.tag(FrostBiomeTags.HAS_ICE_CHEST), GenerationStep.Feature.UNDERGROUND_STRUCTURES, FrostPlacedFeatures.ICE_CHEST);

    }

}
