package somemod.datagen;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;

public class WorldGenProvider extends FabricDynamicRegistryProvider {
    
    public WorldGenProvider(FabricDataOutput output, CompletableFuture<WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(WrapperLookup registries, Entries entries) {
        entries.addAll(registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE));
        entries.addAll(registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE));
        entries.addAll(registries.getWrapperOrThrow(RegistryKeys.STRUCTURE));
        entries.addAll(registries.getWrapperOrThrow(RegistryKeys.STRUCTURE_SET));
        entries.addAll(registries.getWrapperOrThrow(RegistryKeys.PROCESSOR_LIST));
        entries.addAll(registries.getWrapperOrThrow(RegistryKeys.TEMPLATE_POOL));
    }

    @Override
    public String getName() {
        return "somemod";
    }

}
