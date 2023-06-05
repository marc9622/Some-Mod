package somemod.magic.world_gen.feature;

import java.util.Optional;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import somemod.SomeMod;
import somemod.common.world_gen.feature.SingleChestFeatureConfig;
import somemod.datagen.ChestLootTableProvider;
import somemod.magic.block.MagicBlocks;

public class MagicConfiguredFeatures {
    
    public static final RegistryKey<ConfiguredFeature<?, ?>> FORGOTTEN_CHEST_ELVEN = SomeMod.keyOf(RegistryKeys.CONFIGURED_FEATURE, "forgotten_chest_elven");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> registerable) {
        
        SomeMod.registerConfiguredFeature(registerable,
            FORGOTTEN_CHEST_ELVEN, MagicFeatures.FORGOTTEN_CHEST,
            new SingleChestFeatureConfig(
                Optional.of(Text.of("Forgotten Elven Chest")),
                MagicBlocks.FORGOTTEN_CHEST,
                ChestLootTableProvider.ELVEN_CHEST
            )
        );

    }

}
