package somemod.magic.world_gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import somemod.magic.tag.MagicBiomeTags;
import somemod.magic.world_gen.feature.MagicPlacedFeatures;

public class MagicBiomeModifications {
    
    public static void addMagicModifications() {

        BiomeModifications.addFeature(BiomeSelectors.tag(MagicBiomeTags.ELVEN_HAS_FORGOTTEN_CHEST), GenerationStep.Feature.SURFACE_STRUCTURES, MagicPlacedFeatures.FORGOTTEN_CHEST_ELVEN);
        BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.MINESHAFT_HAS_STRUCTURE).and(BiomeSelectors.includeByKey(BiomeKeys.DRIPSTONE_CAVES, BiomeKeys.LUSH_CAVES, BiomeKeys.DEEP_DARK)), GenerationStep.Feature.UNDERGROUND_STRUCTURES, MagicPlacedFeatures.FORGOTTEN_CHEST_UNDERGROUND_TEST);
        
    }

}
