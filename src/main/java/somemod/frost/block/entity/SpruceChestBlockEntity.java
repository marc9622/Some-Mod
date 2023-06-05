package somemod.frost.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.enums.ChestType;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import somemod.common.block.entity.CustomChestBlockEntity;

public class SpruceChestBlockEntity extends CustomChestBlockEntity {

    public static final SpriteIdentifier SPRUCE = SpruceChestBlockEntity.getChestTextureId("entity/chest/spruce");
    public static final SpriteIdentifier SPRUCE_LEFT = SpruceChestBlockEntity.getChestTextureId("entity/chest/spruce_left");
    public static final SpriteIdentifier SPRUCE_RIGHT = SpruceChestBlockEntity.getChestTextureId("entity/chest/spruce_right");

    protected SpruceChestBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    public SpruceChestBlockEntity(BlockPos blockPos, BlockState blockState) {
        this(FrostBlockEntityTypes.SPRUCE_CHEST_ENTITY, blockPos, blockState);
    }

    public SpruceChestBlockEntity(BlockPos blockPos, BlockState blockState, Text customName) {
        this(FrostBlockEntityTypes.SPRUCE_CHEST_ENTITY, blockPos, blockState);
        setCustomName(customName);
    }

    @Override
    public SpriteIdentifier getSpriteIdentifier(BlockEntity blockEntity, ChestType type) {
        return switch(type) {
            case LEFT -> SpruceChestBlockEntity.SPRUCE_LEFT;
            case RIGHT -> SpruceChestBlockEntity.SPRUCE_RIGHT;
            default -> SpruceChestBlockEntity.SPRUCE;
        };
    }

}
