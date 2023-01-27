package somemod.datagen;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.world.biome.Biome;
import somemod.enchanting.tag.EnchantingHasStructureBiomeTags;

public class BiomeTagGenerator extends FabricTagProvider<Biome> {

    public BiomeTagGenerator(FabricDataOutput output, CompletableFuture<WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.BIOME, registriesFuture);
    }

    @Override
    protected void configure(WrapperLookup registries) {
        
        getOrCreateTagBuilder(EnchantingHasStructureBiomeTags.ENCHANTING_TOWER_HAS_STRUCTURE).forceAddTag(BiomeTags.IS_MOUNTAIN).forceAddTag(BiomeTags.IS_FOREST);
    
    }
    
}