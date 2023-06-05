package somemod.frost.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.enums.ChestType;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import somemod.common.block.entity.CustomChestBlockEntity;

public class IceChestBlockEntity extends CustomChestBlockEntity {

    public static final SpriteIdentifier ICE = IceChestBlockEntity.getTranslucentChestTextureId("entity/chest/ice");
    public static final SpriteIdentifier ICE_LEFT = IceChestBlockEntity.getTranslucentChestTextureId("entity/chest/ice_left");
    public static final SpriteIdentifier ICE_RIGHT = IceChestBlockEntity.getTranslucentChestTextureId("entity/chest/ice_right");

    protected IceChestBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    public IceChestBlockEntity(BlockPos blockPos, BlockState blockState) {
        this(FrostBlockEntityTypes.ICE_CHEST_ENTITY, blockPos, blockState);
    }

    public IceChestBlockEntity(BlockPos blockPos, BlockState blockState, Text customName) {
        this(FrostBlockEntityTypes.ICE_CHEST_ENTITY, blockPos, blockState);
        setCustomName(customName);
    }

    @Override
    public SpriteIdentifier getSpriteIdentifier(BlockEntity blockEntity, ChestType type) {
        return switch(type) {
            case LEFT -> IceChestBlockEntity.ICE_LEFT;
            case RIGHT -> IceChestBlockEntity.ICE_RIGHT;
            default -> IceChestBlockEntity.ICE;
        };
    }

}
