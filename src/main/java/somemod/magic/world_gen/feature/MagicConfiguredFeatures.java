package somemod.magic.world_gen.feature;

import java.util.Optional;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import somemod.SomeMod;
import somemod.datagen.ChestLootTableProvider;

public class MagicConfiguredFeatures {
    
    public static final RegistryKey<ConfiguredFeature<?, ?>> FORGOTTEN_CHEST_ELVEN = SomeMod.keyOf(RegistryKeys.CONFIGURED_FEATURE, "forgotten_chest_elven");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> registerable) {
        
        SomeMod.registerConfiguredFeature(registerable,
            FORGOTTEN_CHEST_ELVEN, MagicFeatures.FORGOTTEN_CHEST,
            new ForgottenChestFeatureConfig(Optional.of(Text.of("Forgotten Elven Chest")),
            ChestLootTableProvider.ELVEN_CHEST));

    }

}
