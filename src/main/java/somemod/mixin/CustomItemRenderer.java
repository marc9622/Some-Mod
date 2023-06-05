package somemod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import somemod.common.block.CustomBlockItemRenderer;

@Mixin(ItemRenderer.class)
public abstract class CustomItemRenderer {
    
    @Shadow
    private BuiltinModelItemRenderer builtinModelItemRenderer;

    @Inject(
        method = "renderItem(" +
                 "Lnet/minecraft/item/ItemStack;" +
                 "Lnet/minecraft/client/render/model/json/ModelTransformationMode;" +
                 "Z" +
                 "Lnet/minecraft/client/util/math/MatrixStack;" +
                 "Lnet/minecraft/client/render/VertexConsumerProvider;" +
                 "I" +
                 "I" +
                 "Lnet/minecraft/client/render/model/BakedModel;)" +
                 "V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/util/math/MatrixStack;" +
                     "translate(FFF)V",
            ordinal = 0,
            shift = At.Shift.AFTER,
            by = 0),
        cancellable = true)
    private void renderCustomItemBlockEntity(ItemStack stack, ModelTransformationMode mode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, BakedModel model, CallbackInfo ci) {
        if(stack.getItem() instanceof BlockItem blockItem) {
            if(blockItem.getBlock() instanceof CustomBlockItemRenderer customBlock) {
                {
                    matrices.push();
                    ((CustomModelItemRenderer) (Object) builtinModelItemRenderer)
                        .getBlockEntityRenderDispatcher()
                        .renderEntity(customBlock.getRenderedBlockEntity(), matrices, vertexConsumers, light, overlay);
                    matrices.pop();
                }
                matrices.pop();
                ci.cancel();
            }
        }
    }

}
