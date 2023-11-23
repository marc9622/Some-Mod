package somemod.crystal.block;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.block.MapColor;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import static somemod.utils.BlockBuilder.*;

public class CrystalBlocks {
    
    //#region NATURAL
    public static final Block CRYSTAL_GLASS = defaultBlock("crystal_glass").copyWith(Blocks.GLASS, s -> s.strength(0.3F, 0.3F).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.OFF_WHITE).requiresTool().nonOpaque());
    public static final Block CRYSTAL_BLOCK = defaultBlock("crystal_block").copyWith(Blocks.DIAMOND_BLOCK, s -> s.strength(5F, 6F).sounds(BlockSoundGroup.METAL).requiresTool());
    
    public static final ExperienceDroppingBlock CITRINE_ORE  = defaultXpBlock("citrine_ore",  UniformIntProvider.create(3, 7)).copyWith(Blocks.STONE, s -> s.strength(3F, 3F).sounds(BlockSoundGroup.STONE).requiresTool());
    public static final ExperienceDroppingBlock RUBY_ORE     = defaultXpBlock("ruby_ore",     UniformIntProvider.create(3, 7)).copyWith(Blocks.STONE, s -> s.strength(3F, 3F).sounds(BlockSoundGroup.STONE).requiresTool());
    public static final ExperienceDroppingBlock SAPPHIRE_ORE = defaultXpBlock("sapphire_ore", UniformIntProvider.create(3, 7)).copyWith(Blocks.STONE, s -> s.strength(3F, 3F).sounds(BlockSoundGroup.STONE).requiresTool());
    //#endregion

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}

}
