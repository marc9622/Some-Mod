package somemod.common.block;

import java.util.function.Supplier;

import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.util.math.BlockPos;

public class CustomChestBlock extends ChestBlock implements CustomBlockItemRenderer {

    private boolean isBlockEntityGenerated = false;
    private BlockEntity itemBlockEntity = null;
    private final Supplier<BlockEntity> itemBlockEntityGetter;

    public CustomChestBlock(Settings settings, Supplier<BlockEntityType<? extends ChestBlockEntity>> type) {
        super(settings, type);
        this.itemBlockEntityGetter = () -> type.get().instantiate(BlockPos.ORIGIN, this.getDefaultState());
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return entityTypeRetriever.get().instantiate(pos, state);
    }

    @Override
    public BlockEntity getRenderedBlockEntity() {
        if (!isBlockEntityGenerated) {
            itemBlockEntity = itemBlockEntityGetter.get();
            isBlockEntityGenerated = true;
        }
        return itemBlockEntity;
    }

}
