package somemod.datagen;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.world.biome.Biome;
import static net.minecraft.world.biome.BiomeKeys.*;
import somemod.enchanting.tag.EnchantingHasStructureBiomeTags;

public class BiomeTagProvider extends FabricTagProvider<Biome> {

    public BiomeTagProvider(FabricDataOutput output, CompletableFuture<WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.BIOME, registriesFuture);
    }

    @Override
    protected void configure(WrapperLookup registries) {
        
        getOrCreateTagBuilder(EnchantingHasStructureBiomeTags.ENCHANTING_TOWER_COMMON_HAS_STRUCTURE).add(
            DARK_FOREST,
            FLOWER_FOREST,
            OLD_GROWTH_BIRCH_FOREST,
            OLD_GROWTH_PINE_TAIGA,
            OLD_GROWTH_SPRUCE_TAIGA,
            FROZEN_PEAKS,
            JAGGED_PEAKS,
            GROVE,
            ICE_SPIKES
        );
        
        getOrCreateTagBuilder(EnchantingHasStructureBiomeTags.ENCHANTING_TOWER_RARE_HAS_STRUCTURE).add(
            BIRCH_FOREST,
            FOREST,
            SNOWY_TAIGA,
            TAIGA,
            WINDSWEPT_FOREST,
            MEADOW,
            STONY_PEAKS,
            SPARSE_JUNGLE,
            WINDSWEPT_SAVANNA
        );
    
    }
    
}

