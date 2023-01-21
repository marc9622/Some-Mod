package somemod.enchanting.block;

import static somemod.utils.BlockBuilder.*;

import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;

public class EnchantingBlocks {
    
    public static final Block ENCHANTED_BOOKSHELF = fromBlock("enchanted_bookshelf", EnchantedBookshelfBlock::new, Material.WOOD).modifySettings(s -> s.strength(1.5F).luminance(state -> 7).sounds(BlockSoundGroup.WOOD)).build();

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}

}
