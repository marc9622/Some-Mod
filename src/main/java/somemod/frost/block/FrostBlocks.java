package somemod.frost.block;

import static somemod.utils.BlockBuilder.*;

import net.minecraft.block.Blocks;
import net.minecraft.sound.BlockSoundGroup;
import somemod.common.block.CustomChestBlock;
import somemod.frost.block.entity.FrostBlockEntityTypes;

public class FrostBlocks {

    public static final CustomChestBlock SPRUCE_CHEST =
        block("spruce_chest", settings -> new CustomChestBlock(settings, () -> FrostBlockEntityTypes.SPRUCE_CHEST_ENTITY))
        .copyWith(Blocks.CHEST, s -> s.strength(2, 5f).sounds(BlockSoundGroup.WOOD));

    public static final CustomChestBlock ICE_CHEST =
        block("ice_chest", settings -> new IceChestBlock(settings, () -> FrostBlockEntityTypes.ICE_CHEST_ENTITY))
        .copyWith(Blocks.ICE, s -> s.strength(0.75f).sounds(BlockSoundGroup.GLASS).nonOpaque().slipperiness(0.98f).ticksRandomly());

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}

}
