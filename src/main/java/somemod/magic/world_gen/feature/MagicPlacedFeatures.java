package somemod.magic.world_gen.feature;

import net.minecraft.block.Block;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.Direction;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.BlockFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;
import somemod.SomeMod;

public class MagicPlacedFeatures {
    
    public static final RegistryKey<PlacedFeature> FORGOTTEN_CHEST_ELVEN = SomeMod.keyOf(RegistryKeys.PLACED_FEATURE, "forgotten_chest_elven");
    public static final RegistryKey<PlacedFeature> FORGOTTEN_CHEST_UNDERGROUND_TEST = SomeMod.keyOf(RegistryKeys.PLACED_FEATURE, "forgotten_chest_underground_test");

    public static void bootstrap(Registerable<PlacedFeature> registerable) {

        RegistryEntryLookup<ConfiguredFeature<?, ?>> registryEntryLookup = registerable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        RegistryEntry.Reference<ConfiguredFeature<?, ?>> configuredForgottenChestElven = registryEntryLookup.getOrThrow(MagicConfiguredFeatures.FORGOTTEN_CHEST_ELVEN);
        
        registerChest(registerable, FORGOTTEN_CHEST_ELVEN, configuredForgottenChestElven, 64, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP);
        
        registerChest(registerable, FORGOTTEN_CHEST_UNDERGROUND_TEST, configuredForgottenChestElven, 1, PlacedFeatures.BOTTOM_TO_120_RANGE);

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
