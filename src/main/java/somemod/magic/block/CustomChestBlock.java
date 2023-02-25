package somemod.magic.block;

import java.util.function.Supplier;

import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.util.math.BlockPos;

public class CustomChestBlock extends ChestBlock {

    public CustomChestBlock(Settings settings, Supplier<BlockEntityType<? extends ChestBlockEntity>> type) {
        super(settings, type);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return entityTypeRetriever.get().instantiate(pos, state);
    }

}
