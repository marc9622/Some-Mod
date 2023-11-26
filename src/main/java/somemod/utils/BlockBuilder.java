package somemod.utils;

import java.util.function.UnaryOperator;

import net.minecraft.block.Block;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import somemod.SomeMod;

/**
 * A builder class for creating blocks.
 */
public class BlockBuilder<B extends Block> {

    @FunctionalInterface
    public interface BlockConstructor<B> {
        B createWith(Block.Settings settings);
    }

    private final String name;

    private final BlockConstructor<B> blockConstructor;

    private BlockBuilder(String name, BlockConstructor<B> blockConstructor) {
        this.name = name;
        this.blockConstructor = blockConstructor;
    }

    /**
     * Create a default block using {@code Block::new}.
     */
    public static BlockBuilder<Block> block(String name) {
        return block(name, Block::new);
    }

    /**
     * Create an experience dropping block that drops {@code xp} using {@code ExperienceDroppingBlock::new}.
     */
    public static BlockBuilder<ExperienceDroppingBlock> xpBlock(String name, UniformIntProvider xp) {
        return block(name, settings -> new ExperienceDroppingBlock(settings, xp));
    }

    /**
     * Create a new block using the given {@code blockConstructor}.
     */
    public static <B extends Block> BlockBuilder<B> block(String name, BlockConstructor<B> blockConstructor) {
        return new BlockBuilder<B>(name, blockConstructor);
    }

    /**
     * Copies the settings from {@code block} and uses them to create the block.
     */
    public B copy(Block block) {
        return SomeMod.registerBlock(name, blockConstructor.createWith(Block.Settings.copy(block)));
    }

    /**
     * Creates the block with the given {@code settings}.
     */
    public B with(Block.Settings settings) {
        return SomeMod.registerBlock(name, blockConstructor.createWith(settings));
    }

    /**
     * Copies the settings from {@code block} applies the given {@code modifier} to them and uses them to create the block.
     */
    public B copyWith(Block block, UnaryOperator<Block.Settings> modifier) {
        return SomeMod.registerBlock(name, blockConstructor.createWith(modifier.apply(Block.Settings.copy(block))));
    }

}
