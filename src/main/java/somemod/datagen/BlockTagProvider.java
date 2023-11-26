package somemod.datagen;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.registry.tag.BlockTags;
import somemod.crystal.block.CrystalBlocks;
import somemod.crystal.tag.CrystalBlockTags;
import somemod.frost.block.FrostBlocks;
import somemod.magic.block.MagicBlocks;

public class BlockTagProvider extends FabricTagProvider<Block> {

    public BlockTagProvider(FabricDataOutput output, CompletableFuture<WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.BLOCK, registriesFuture);
    }

    @Override
    protected void configure(WrapperLookup registries) {

        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
            .add(CrystalBlocks.CRYSTAL_GLASS, CrystalBlocks.CRYSTAL_BLOCK, CrystalBlocks.CITRINE_ORE, CrystalBlocks.RUBY_ORE, CrystalBlocks.SAPPHIRE_ORE)
            .setReplace(false);
        
        getOrCreateTagBuilder(CrystalBlockTags.GUARDED_BY_ENDERMEN)
            .add(CrystalBlocks.CRYSTAL_GLASS, CrystalBlocks.CRYSTAL_BLOCK)
            .setReplace(false);

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
            .add(CrystalBlocks.CITRINE_ORE, CrystalBlocks.RUBY_ORE, CrystalBlocks.SAPPHIRE_ORE)
            .add(MagicBlocks.ENCHANTED_BOOKSHELF, MagicBlocks.OBSIDIAN_ENCHANTED_BOOKSHELF)
            .setReplace(false);

        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
            .add(MagicBlocks.FORGOTTEN_CHEST)
            .add(FrostBlocks.SPRUCE_CHEST)
            .setReplace(false);
    
    }
    
}
