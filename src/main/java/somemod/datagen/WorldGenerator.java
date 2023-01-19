package somemod.datagen;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;

public class WorldGenerator extends FabricDynamicRegistryProvider {
    
    public WorldGenerator(FabricDataOutput output, CompletableFuture<WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(WrapperLookup registries, Entries entries) {
        // final RegistryWrapper.Impl<ConfiguredFeature<?,?>> configuredFeatureRegistry = registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE);
        // entries.add(CrystalConfiguredFeatures.END_CRYSTAL_GLASS_CONFIGURED_FEATURE, configuredFeatureRegistry.getOrThrow(CrystalConfiguredFeatures.END_CRYSTAL_GLASS_CONFIGURED_FEATURE).value());

        // final RegistryWrapper.Impl<PlacedFeature> placedFeatureRegistry = registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE);
        // entries.add(CrystalPlacedFeatures.END_CRYSTAL_GLASS_PLACED_FEATURE, placedFeatureRegistry.getOrThrow(CrystalPlacedFeatures.END_CRYSTAL_GLASS_PLACED_FEATURE).value());

        entries.addAll(registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE));
        entries.addAll(registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE));
    }

    @Override
    public String getName() {
        return "somemod";
    }

}
