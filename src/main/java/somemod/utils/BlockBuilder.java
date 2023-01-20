package somemod.utils;

import java.util.function.Function;
import java.util.function.UnaryOperator;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.block.Material;
import net.minecraft.registry.Registries;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import somemod.SomeMod;

public class BlockBuilder {

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