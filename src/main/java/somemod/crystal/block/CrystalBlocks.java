package somemod.crystal.block;

import java.util.function.Function;
import java.util.function.UnaryOperator;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.registry.Registries;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import somemod.SomeMod;

public class CrystalBlocks {
    
    //#region NATURAL
    public static final Block CRYSTAL_GLASS = BlockBuilder.defaultBlock("crystal_glass", Material.GLASS).modifySettings(s -> s.strength(0.3F, 0.3F).sounds(BlockSoundGroup.GLASS).mapColor(MapColor.OFF_WHITE).requiresTool().nonOpaque()).build();
    public static final Block CRYSTAL_BLOCK = BlockBuilder.defaultBlock("crystal_block", Material.METAL).modifySettings(s -> s.strength(5F, 6F).sounds(BlockSoundGroup.METAL).requiresTool()).build();
    
    public static final Block CITRINE_ORE   = BlockBuilder.defaultXpBlock("citrine_ore", Material.STONE, UniformIntProvider.create(3, 7)).modifySettings(s -> s.strength(3F, 3F).sounds(BlockSoundGroup.STONE).requiresTool()).build();
    public static final Block RUBY_ORE      = BlockBuilder.defaultXpBlock("ruby_ore",    Material.STONE, UniformIntProvider.create(3, 7)).modifySettings(s -> s.strength(3F, 3F).sounds(BlockSoundGroup.STONE).requiresTool()).build();
    public static final Block SAPPHIRE_ORE  = BlockBuilder.defaultXpBlock("sapphire_ore",Material.STONE, UniformIntProvider.create(3, 7)).modifySettings(s -> s.strength(3F, 3F).sounds(BlockSoundGroup.STONE).requiresTool()).build();
    //#endregion

    private static class BlockBuilder {

        private BlockBuilder(String name, Function<Block.Settings, Block> blockConstructor, AbstractBlock.Settings settings) {
            this.name = name;
            this.blockConstructor = blockConstructor;
            this.settings = settings;
        }

        private final String name;

        private final Function<Block.Settings, Block> blockConstructor;
        private AbstractBlock.Settings settings;

        public static BlockBuilder defaultBlock(String name, Material material) {
            return fromBlock(name, Block::new, material);
        }

        public static BlockBuilder defaultXpBlock(String name, Material material, UniformIntProvider xp) {
            return fromBlock(name, settings -> new ExperienceDroppingBlock(settings, xp), material);
        }

        public static BlockBuilder fromBlock(String name, Function<Block.Settings, Block> blockConstructor, Material material) {
            return new BlockBuilder(name, blockConstructor, FabricBlockSettings.of(material));
        }

        public BlockBuilder modifySettings(UnaryOperator<AbstractBlock.Settings> settingsModifier) {
            settings = settingsModifier.apply(settings);
            return this;
        }

        private Block register() {
            return SomeMod.register(Registries.BLOCK, name, blockConstructor.apply(settings));
        }

        public Block build() {
            return register();
        }

    }

}
