package somemod.magic.block;

import static somemod.utils.BlockBuilder.*;

import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;

public class MagicBlocks {
    
    public static final Block ENCHANTED_BOOKSHELF =
        fromBlock("enchanted_bookshelf", EnchantedBookshelfBlock::new, Material.METAL)
        .modifySettings(s -> s.strength(1.5F).luminance(state -> 7).sounds(BlockSoundGroup.METAL).mapColor(MapColor.GOLD).requiresTool()).build();

    public static final Block OBSIDIAN_ENCHANTED_BOOKSHELF =
        fromBlock("obsidian_enchanted_bookshelf", ObsidianEnchantedBookshelfBlock::new, Material.STONE)
        .modifySettings(s -> s.strength(5.0f, 1200.0f).luminance(state -> 7).sounds(BlockSoundGroup.STONE).mapColor(MapColor.BLACK).requiresTool()).build();

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}

}
