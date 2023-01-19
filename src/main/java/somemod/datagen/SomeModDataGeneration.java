package somemod.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import somemod.crystal.world.feature.CrystalConfiguredFeatures;
import somemod.crystal.world.feature.CrystalPlacedFeatures;

public class SomeModDataGeneration implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(WorldGenerator::new);
        pack.addProvider(BlockTagGenerator::new);
    }
    
    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {

        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, registry -> CrystalConfiguredFeatures.bootstrap(registry)); // Compiler won't let me use a method reference here.. Bug?
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, CrystalPlacedFeatures::bootstrap);
        
    }

}
