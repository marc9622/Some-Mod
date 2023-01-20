package somemod.crystal.block;

import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import static somemod.utils.BlockBuilder.*;

public class CrystalBlocks {
    
    //#region NATURAL
    public static final Block CRYSTAL_GLASS = defaultBlock("crystal_glass", Material.GLASS).modifySettings(s -> s.strength(0.3F, 0.3F).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.OFF_WHITE).requiresTool().nonOpaque()).build();
    public static final Block CRYSTAL_BLOCK = defaultBlock("crystal_block", Material.METAL).modifySettings(s -> s.strength(5F, 6F).sounds(BlockSoundGroup.METAL).requiresTool()).build();
    
    public static final Block CITRINE_ORE   = defaultXpBlock("citrine_ore", Material.STONE, UniformIntProvider.create(3, 7)).modifySettings(s -> s.strength(3F, 3F).sounds(BlockSoundGroup.STONE).requiresTool()).build();
    public static final Block RUBY_ORE      = defaultXpBlock("ruby_ore",    Material.STONE, UniformIntProvider.create(3, 7)).modifySettings(s -> s.strength(3F, 3F).sounds(BlockSoundGroup.STONE).requiresTool()).build();
    public static final Block SAPPHIRE_ORE  = defaultXpBlock("sapphire_ore",Material.STONE, UniformIntProvider.create(3, 7)).modifySettings(s -> s.strength(3F, 3F).sounds(BlockSoundGroup.STONE).requiresTool()).build();
    //#endregion

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}

}
