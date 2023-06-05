package somemod.magic.block;

import static somemod.utils.BlockBuilder.*;

import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import somemod.common.block.CustomChestBlock;
import somemod.magic.block.entity.MagicBlockEntityTypes;

public class MagicBlocks {
    
    public static final EnchantedBookshelfBlock ENCHANTED_BOOKSHELF =
        fromBlock("enchanted_bookshelf", EnchantedBookshelfBlock::new, Material.METAL)
        .modifySettings(s -> s.strength(3.5f).luminance(state -> 7).sounds(BlockSoundGroup.METAL).mapColor(MapColor.GOLD).requiresTool()).build();

    public static final ObsidianEnchantedBookshelfBlock OBSIDIAN_ENCHANTED_BOOKSHELF =
        fromBlock("obsidian_enchanted_bookshelf", ObsidianEnchantedBookshelfBlock::new, Material.STONE)
        .modifySettings(s -> s.strength(5.0f, 1200.0f).luminance(state -> 7).sounds(BlockSoundGroup.STONE).mapColor(MapColor.BLACK).requiresTool()).build();

    public static final CustomChestBlock FORGOTTEN_CHEST =
        fromBlock("forgotten_chest", settings -> new CustomChestBlock(settings, () -> MagicBlockEntityTypes.FORGOTTEN_CHEST_ENTITY), Material.WOOD)
        .modifySettings(s -> s.strength(2, 5f).sounds(BlockSoundGroup.WOOD)).build();

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}

}
