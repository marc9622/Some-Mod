package somemod.datagen;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import somemod.crystal.block.CrystalBlocks;
import somemod.crystal.tag.CrystalBlockTags;

public class BlockTagGenerator extends FabricTagProvider<Block> {

    public BlockTagGenerator(FabricDataOutput output, CompletableFuture<WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.BLOCK, registriesFuture);
    }

    @Override
    protected void configure(WrapperLookup registries) {
        
        getOrCreateTagBuilder(CrystalBlockTags.GUARDED_BY_ENDERMEN).add(CrystalBlocks.CRYSTAL_GLASS, CrystalBlocks.CRYSTAL_BLOCK);
    
    }
    
}
