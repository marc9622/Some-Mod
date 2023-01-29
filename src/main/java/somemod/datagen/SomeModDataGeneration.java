package somemod.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import somemod.crystal.world_gen.feature.CrystalConfiguredFeatures;
import somemod.crystal.world_gen.feature.CrystalPlacedFeatures;
import somemod.enchanting.world_gen.structure.EnchantingStructureSets;
import somemod.enchanting.world_gen.structure.EnchantingStructures;
import somemod.enchanting.world_gen.structure.pool.EnchantingStructurePools;
import somemod.enchanting.world_gen.structure.processor.EnchantingStructureProcessorLists;

public class SomeModDataGeneration implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(AdvancementsProvider::new);
        pack.addProvider(BiomeTagProvider::new);
        pack.addProvider(BlockTagProvider::new);
        pack.addProvider(SomeModBlockLootTableProvider::new);
        pack.addProvider(WorldGenProvider::new);
    }
    
    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {

        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, registry -> CrystalConfiguredFeatures.bootstrap(registry)); // Compiler won't let me use a method reference here.. Bug?
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, CrystalPlacedFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.STRUCTURE, EnchantingStructures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.STRUCTURE_SET, EnchantingStructureSets::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.PROCESSOR_LIST, EnchantingStructureProcessorLists::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.TEMPLATE_POOL, EnchantingStructurePools::bootstrap);

    }

}
