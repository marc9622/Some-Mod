package somemod.magic.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.enums.ChestType;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.math.BlockPos;

public class ForgottenChestBlockEntity extends CustomChestBlockEntity {

    public static final SpriteIdentifier FORGOTTEN = ForgottenChestBlockEntity.getChestTextureId("entity/chest/forgotten");
    public static final SpriteIdentifier FORGOTTEN_LEFT = ForgottenChestBlockEntity.getChestTextureId("entity/chest/forgotten_left");
    public static final SpriteIdentifier FORGOTTEN_RIGHT = ForgottenChestBlockEntity.getChestTextureId("entity/chest/forgotten_right");

    protected ForgottenChestBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    public ForgottenChestBlockEntity(BlockPos blockPos, BlockState blockState) {
        this(MagicBlockEntityTypes.FORGOTTEN_CHEST, blockPos, blockState);
    }

    @Override
    public SpriteIdentifier getSpriteIdentifier(BlockEntity blockEntity, ChestType type) {
        return switch(type) {
            case LEFT -> ForgottenChestBlockEntity.FORGOTTEN_LEFT;
            case RIGHT -> ForgottenChestBlockEntity.FORGOTTEN_RIGHT;
            default -> ForgottenChestBlockEntity.FORGOTTEN;
        };
    }

}
