package somemod.magic.block;

import static somemod.utils.BlockBuilder.*;

import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.sound.BlockSoundGroup;
import somemod.common.block.CustomChestBlock;
import somemod.magic.block.entity.MagicBlockEntityTypes;

public class MagicBlocks {
    
    public static final EnchantedBookshelfBlock ENCHANTED_BOOKSHELF = block("enchanted_bookshelf", EnchantedBookshelfBlock::new)
        .copyWith(Blocks.GOLD_BLOCK, s -> s.strength(3.5f).luminance(state -> 7).sounds(BlockSoundGroup.METAL).mapColor(MapColor.GOLD).requiresTool());

    public static final ObsidianEnchantedBookshelfBlock OBSIDIAN_ENCHANTED_BOOKSHELF = block("obsidian_enchanted_bookshelf", ObsidianEnchantedBookshelfBlock::new)
        .copyWith(Blocks.OBSIDIAN, s -> s.strength(5.0f, 1200.0f).luminance(state -> 7).sounds(BlockSoundGroup.STONE).mapColor(MapColor.BLACK).requiresTool());

    public static final CustomChestBlock FORGOTTEN_CHEST = block("forgotten_chest", s -> new CustomChestBlock(s, () -> MagicBlockEntityTypes.FORGOTTEN_CHEST_ENTITY))
        .copyWith(Blocks.CHEST, s -> s.strength(2, 5f).sounds(BlockSoundGroup.WOOD));

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}

}
