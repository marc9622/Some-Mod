package somemod.magic.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.block.enums.ChestType;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.math.BlockPos;
import somemod.SomeMod;

public abstract class CustomChestBlockEntity extends ChestBlockEntity {

    public CustomChestBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    public abstract SpriteIdentifier getSpriteIdentifier(BlockEntity blockEntity, ChestType type);

    protected static final SpriteIdentifier getChestTextureId(String path) {
        return new SpriteIdentifier(TexturedRenderLayers.CHEST_ATLAS_TEXTURE, SomeMod.id(path));
    }
    
}
