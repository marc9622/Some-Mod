package somemod.frost.world_gen.feature;

import java.util.Optional;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import somemod.SomeMod;
import somemod.common.world_gen.feature.SingleChestFeatureConfig;
import somemod.datagen.ChestLootTableProvider;
import somemod.frost.block.FrostBlocks;

public class FrostConfiguredFeatures {

    public static final RegistryKey<ConfiguredFeature<?, ?>> SPRUCE_CHEST_SNOWY = SomeMod.keyOf(RegistryKeys.CONFIGURED_FEATURE, "spruce_chest_snowy");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SPRUCE_CHEST_TAIGA = SomeMod.keyOf(RegistryKeys.CONFIGURED_FEATURE, "spruce_chest_taiga");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SPRUCE_CHEST_MOUNTAIN = SomeMod.keyOf(RegistryKeys.CONFIGURED_FEATURE, "spruce_chest_mountain");

    public static final RegistryKey<ConfiguredFeature<?, ?>> ICE_CHEST = SomeMod.keyOf(RegistryKeys.CONFIGURED_FEATURE, "ice_chest");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> registerable) {

        SomeMod.registerConfiguredFeature(registerable,
            SPRUCE_CHEST_SNOWY, FrostFeatures.SPRUCE_CHEST,
            new SingleChestFeatureConfig(
                Optional.of(Text.of("Snowy Spruce Chest")),
                FrostBlocks.SPRUCE_CHEST,
                ChestLootTableProvider.SPRUCE_CHEST_SNOWY
            )
        );

        SomeMod.registerConfiguredFeature(registerable,
            SPRUCE_CHEST_TAIGA, FrostFeatures.SPRUCE_CHEST,
            new SingleChestFeatureConfig(
                Optional.of(Text.of("Taiga Spruce Chest")),
                FrostBlocks.SPRUCE_CHEST,
                ChestLootTableProvider.SPRUCE_CHEST_TAIGA
            )
        );

        SomeMod.registerConfiguredFeature(registerable,
            SPRUCE_CHEST_MOUNTAIN, FrostFeatures.SPRUCE_CHEST,
            new SingleChestFeatureConfig(
                Optional.of(Text.of("Mountain Spruce Chest")),
                FrostBlocks.SPRUCE_CHEST,
                ChestLootTableProvider.SPRUCE_CHEST_MOUNTAIN
            )
        );

        SomeMod.registerConfiguredFeature(registerable,
            ICE_CHEST, FrostFeatures.ICE_CHEST,
            new SingleChestFeatureConfig(
                Optional.of(Text.of("Ice Chest")),
                FrostBlocks.ICE_CHEST,
                ChestLootTableProvider.ICE_CHEST
            )
        );

    }

}
