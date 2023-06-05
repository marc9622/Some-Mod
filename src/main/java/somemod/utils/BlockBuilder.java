package somemod.utils;

import java.util.function.Function;
import java.util.function.UnaryOperator;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.block.Material;
import net.minecraft.registry.Registries;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import somemod.SomeMod;

public class BlockBuilder<B extends Block> {

    private final String name;

    private final Function<Block.Settings, B> blockConstructor;
    private Block.Settings settings;

    private BlockBuilder(String name, Function<Block.Settings, B> blockConstructor, Block.Settings settings) {
        this.name = name;
        this.blockConstructor = blockConstructor;
        this.settings = settings;
    }

    public static BlockBuilder<Block> defaultBlock(String name, Material material) {
        return fromBlock(name, Block::new, material);
    }

    public static BlockBuilder<ExperienceDroppingBlock> defaultXpBlock(String name, Material material, UniformIntProvider xp) {
        return fromBlock(name, settings -> new ExperienceDroppingBlock(settings, xp), material);
    }

    public static <B extends Block> BlockBuilder<B> fromBlock(String name, Function<Block.Settings, B> blockConstructor, Material material) {
        return new BlockBuilder<B>(name, blockConstructor, FabricBlockSettings.of(material));
    }

    public BlockBuilder<B> modifySettings(UnaryOperator<Block.Settings> settingsModifier) {
        settings = settingsModifier.apply(settings);
        return this;
    }

    public B build() {
        return SomeMod.register(Registries.BLOCK, name, blockConstructor.apply(settings));
    }

}
