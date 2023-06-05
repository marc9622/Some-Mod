package somemod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.ChestType;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.ChestBlockEntityRenderer;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import somemod.common.block.entity.CustomChestBlockEntity;

@Mixin(ChestBlockEntityRenderer.class)
public abstract class CustomChestEntityRenderer {
    
    private static CustomChestBlockEntity entity = null;

    @Inject(
        method = "render(" +
                 "Lnet/minecraft/block/entity/BlockEntity;" +
                 "F" +
                 "Lnet/minecraft/client/util/math/MatrixStack;" +
                 "Lnet/minecraft/client/render/VertexConsumerProvider;" +
                 "I" +
                 "I)" +
                 "V",
        at = @At("HEAD"))
    private void getEntityType(BlockEntity blockEntity, float tickDelta, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int j, CallbackInfo ci) {
        entity = blockEntity instanceof CustomChestBlockEntity entity ? entity : null;
    }

    @ModifyVariable(
        method = "render(" +
                 "Lnet/minecraft/block/entity/BlockEntity;" +
                 "F" +
                 "Lnet/minecraft/client/util/math/MatrixStack;" +
                 "Lnet/minecraft/client/render/VertexConsumerProvider;" +
                 "I" +
                 "I)" +
                 "V",
        at = @At(
            value = "INVOKE_ASSIGN",
            target = "Lnet/minecraft/client/render/TexturedRenderLayers;" + 
                     "getChestTextureId(" +
                     "Lnet/minecraft/block/entity/BlockEntity;" +
                     "Lnet/minecraft/block/enums/ChestType;" +
                     "Z)" +
                     "Lnet/minecraft/client/util/SpriteIdentifier;"))
    private SpriteIdentifier getCustomChestTexture(SpriteIdentifier spriteIdentifier) {
        if(entity != null) {
            World world = entity.getWorld();
            BlockState blockState = world != null ? entity.getCachedState() : Blocks.CHEST.getDefaultState().with(ChestBlock.FACING, Direction.SOUTH);
            ChestType chestType = blockState.getOrEmpty(ChestBlock.CHEST_TYPE).orElse(ChestType.SINGLE);
            spriteIdentifier = entity.getSpriteIdentifier(entity, chestType);
            entity = null;
        }
        return spriteIdentifier;
    }

}
