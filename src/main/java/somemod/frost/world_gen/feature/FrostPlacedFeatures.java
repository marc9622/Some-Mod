package somemod.frost.world_gen.feature;

import net.minecraft.block.Block;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.Direction;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.BlockFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;
import somemod.SomeMod;

public class FrostPlacedFeatures {

    public static final RegistryKey<PlacedFeature> SPRUCE_CHEST_SNOWY = SomeMod.keyOf(RegistryKeys.PLACED_FEATURE, "spruce_chest_snowy");
    public static final RegistryKey<PlacedFeature> SPRUCE_CHEST_TAIGA = SomeMod.keyOf(RegistryKeys.PLACED_FEATURE, "spruce_chest_taiga");
    public static final RegistryKey<PlacedFeature> SPRUCE_CHEST_MOUNTAIN = SomeMod.keyOf(RegistryKeys.PLACED_FEATURE, "spruce_chest_mountain");

    public static final RegistryKey<PlacedFeature> ICE_CHEST = SomeMod.keyOf(RegistryKeys.PLACED_FEATURE, "ice_chest");

    public static void bootstrap(Registerable<PlacedFeature> registerable) {

        RegistryEntryLookup<ConfiguredFeature<?, ?>> registryEntryLookup = registerable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        RegistryEntry.Reference<ConfiguredFeature<?, ?>> configuredSpruceChestSnowy = registryEntryLookup.getOrThrow(FrostConfiguredFeatures.SPRUCE_CHEST_SNOWY);
        RegistryEntry.Reference<ConfiguredFeature<?, ?>> configuredSpruceChestTaiga = registryEntryLookup.getOrThrow(FrostConfiguredFeatures.SPRUCE_CHEST_TAIGA);
        RegistryEntry.Reference<ConfiguredFeature<?, ?>> configuredSpruceChestMountain = registryEntryLookup.getOrThrow(FrostConfiguredFeatures.SPRUCE_CHEST_MOUNTAIN);
        RegistryEntry.Reference<ConfiguredFeature<?, ?>> configuredIceChest = registryEntryLookup.getOrThrow(FrostConfiguredFeatures.ICE_CHEST);

        registerChest(registerable, SPRUCE_CHEST_SNOWY, configuredSpruceChestSnowy, 64, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP);

        registerChest(registerable, SPRUCE_CHEST_TAIGA, configuredSpruceChestTaiga, 64, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP);

        registerChest(registerable, SPRUCE_CHEST_MOUNTAIN, configuredSpruceChestMountain, 64, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP);

        registerChest(registerable, ICE_CHEST, configuredIceChest, 16, HeightRangePlacementModifier.trapezoid(YOffset.fixed(0), YOffset.fixed(64)));

    }

    private static void registerChest(Registerable<PlacedFeature> registerable, RegistryKey<PlacedFeature> placedFeatureKey,
                                      RegistryEntry.Reference<ConfiguredFeature<?, ?>> configuredFeatureKey,
                                      int rarity, PlacementModifier placementModifier, Block... validGroundBlocks) {

        SomeMod.registerPlacedFeature(registerable, placedFeatureKey, configuredFeatureKey,
            RarityFilterPlacementModifier.of(rarity),
            //CountPlacementModifier.of(10),
            placementModifier,
            SquarePlacementModifier.of(),
            BiomePlacementModifier.of(),
            BlockFilterPlacementModifier.of(
                BlockPredicate.bothOf(
                    BlockPredicate.IS_AIR_OR_WATER,
                    validGroundBlocks.length >= 1
                    ? BlockPredicate.matchingBlocks(Direction.DOWN.getVector(), validGroundBlocks)
                    : BlockPredicate.alwaysTrue()))
        );
    }

}
