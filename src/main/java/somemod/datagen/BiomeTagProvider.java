package somemod.datagen;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.world.biome.Biome;
import somemod.frost.tag.FrostBiomeTags;
import somemod.magic.tag.MagicBiomeTags;

import static net.minecraft.world.biome.BiomeKeys.*;

public class BiomeTagProvider extends FabricTagProvider<Biome> {

    public BiomeTagProvider(FabricDataOutput output, CompletableFuture<WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.BIOME, registriesFuture);
    }

    @Override
    protected void configure(WrapperLookup registries) {
        
        getOrCreateTagBuilder(MagicBiomeTags.ENCHANTING_TOWER_COMMON_HAS_STRUCTURE).add(
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
        
        getOrCreateTagBuilder(MagicBiomeTags.ENCHANTING_TOWER_RARE_HAS_STRUCTURE).add(
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

        getOrCreateTagBuilder(MagicBiomeTags.ELVEN_HAS_FORGOTTEN_CHEST).add(
            FOREST,
            DARK_FOREST,
            FLOWER_FOREST,
            BIRCH_FOREST,
            TAIGA,
            SNOWY_TAIGA,
            OLD_GROWTH_BIRCH_FOREST,
            OLD_GROWTH_PINE_TAIGA,
            OLD_GROWTH_SPRUCE_TAIGA
        );

        // From 'has_structure/igloo'
        getOrCreateTagBuilder(FrostBiomeTags.HAS_SPRUCE_CHEST_SNOWY).add(
            SNOWY_PLAINS,
            SNOWY_SLOPES,
            SNOWY_BEACH
        );

        // From 'is_taiga'
        getOrCreateTagBuilder(FrostBiomeTags.HAS_SPRUCE_CHEST_TAIGA).add(
            TAIGA,
            SNOWY_TAIGA,
            OLD_GROWTH_PINE_TAIGA,
            OLD_GROWTH_SPRUCE_TAIGA,
            GROVE
        );

        // From 'is_mountain'
        getOrCreateTagBuilder(FrostBiomeTags.HAS_SPRUCE_CHEST_MOUNTAIN).add(
            MEADOW,
            FROZEN_PEAKS, // TODO: maybe move to _SNOWY?
            JAGGED_PEAKS,
            STONY_PEAKS
        );

        getOrCreateTagBuilder(FrostBiomeTags.HAS_ICE_CHEST).add(
            // Biomes with a temperature < 0.0
            FROZEN_PEAKS,
            JAGGED_PEAKS,
            SNOWY_TAIGA,
            SNOWY_SLOPES,
            GROVE
        );

        getOrCreateTagBuilder(FrostBiomeTags.IS_COLD).add(
            FROZEN_PEAKS,
            JAGGED_PEAKS,
            SNOWY_PLAINS,
            SNOWY_TAIGA,
            SNOWY_SLOPES,
            SNOWY_BEACH,
            GROVE
        );
    
    }
    
}

