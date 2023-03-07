package somemod.mixin;

import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(BuiltinModelItemRenderer.class)
public interface CustomModelItemRenderer {

    @Accessor
    public BlockEntityRenderDispatcher getBlockEntityRenderDispatcher();

}
