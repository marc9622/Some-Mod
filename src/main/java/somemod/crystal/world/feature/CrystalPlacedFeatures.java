package somemod.crystal.world.feature;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;
import somemod.SomeMod;

/**
 * This class is used to register the placed features related to crystals.
 */
public class CrystalPlacedFeatures {

    public static final RegistryKey<PlacedFeature> END_CRYSTAL_GLASS_PLACED_FEATURE = RegistryKey.of(RegistryKeys.PLACED_FEATURE, SomeMod.id("end_crystal_glass_pillar"));

    public static final RegistryKey<PlacedFeature> ORE_CITRINE  = RegistryKey.of(RegistryKeys.PLACED_FEATURE, SomeMod.id("ore_citrine"));
    public static final RegistryKey<PlacedFeature> ORE_SAPPHIRE = RegistryKey.of(RegistryKeys.PLACED_FEATURE, SomeMod.id("ore_sapphire"));
    public static final RegistryKey<PlacedFeature> ORE_RUBY     = RegistryKey.of(RegistryKeys.PLACED_FEATURE, SomeMod.id("ore_ruby"));

    /**
     * Registers the placed features related to crystals.
     * Is called by the {@link somemod.datagen.SomeModDataGeneration} class.
     */
    public static void bootstrap(Registerable<PlacedFeature> featureRegisterable) {

        RegistryEntryLookup<ConfiguredFeature<?, ?>> registryEntryLookup = featureRegisterable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        RegistryEntry.Reference<ConfiguredFeature<?, ?>> crystalRegistryEntry = registryEntryLookup.getOrThrow(CrystalConfiguredFeatures.END_CRYSTAL_GLASS_CONFIGURED_FEATURE);
        PlacedFeatures.register(featureRegisterable, END_CRYSTAL_GLASS_PLACED_FEATURE, crystalRegistryEntry, 
            CountPlacementModifier.of(4),
            RarityFilterPlacementModifier.of(64),
            SquarePlacementModifier.of(),
            BiomePlacementModifier.of()
        );

        RegistryEntry.Reference<ConfiguredFeature<?, ?>> citrineRegistryEntry = registryEntryLookup.getOrThrow(CrystalConfiguredFeatures.ORE_CITRINE);
        PlacedFeatures.register(featureRegisterable, ORE_CITRINE, citrineRegistryEntry, 
            CountPlacementModifier.of(100),
            HeightRangePlacementModifier.trapezoid(YOffset.fixed(-16), YOffset.fixed(480)),
            SquarePlacementModifier.of(),
            BiomePlacementModifier.of()
        );

        RegistryEntry.Reference<ConfiguredFeature<?, ?>> sapphireRegistryEntry = registryEntryLookup.getOrThrow(CrystalConfiguredFeatures.ORE_SAPPHIRE);
        PlacedFeatures.register(featureRegisterable, ORE_SAPPHIRE, sapphireRegistryEntry, 
            CountPlacementModifier.of(100),
            HeightRangePlacementModifier.trapezoid(YOffset.fixed(-16), YOffset.fixed(480)),
            SquarePlacementModifier.of(),
            BiomePlacementModifier.of()
        );

        RegistryEntry.Reference<ConfiguredFeature<?, ?>> rubyRegistryEntry = registryEntryLookup.getOrThrow(CrystalConfiguredFeatures.ORE_RUBY);
        PlacedFeatures.register(featureRegisterable, ORE_RUBY, rubyRegistryEntry, 
            CountPlacementModifier.of(100),
            HeightRangePlacementModifier.trapezoid(YOffset.fixed(-16), YOffset.fixed(480)),
            SquarePlacementModifier.of(),
            BiomePlacementModifier.of()
        );

    }

}