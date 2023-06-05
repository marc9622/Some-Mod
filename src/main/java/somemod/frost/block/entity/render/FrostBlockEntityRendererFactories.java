package somemod.frost.block.entity.render;

import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.block.entity.ChestBlockEntityRenderer;
import somemod.frost.block.entity.FrostBlockEntityTypes;

public class FrostBlockEntityRendererFactories {
    
    static {
        BlockEntityRendererFactories.register(FrostBlockEntityTypes.SPRUCE_CHEST_ENTITY, ChestBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(FrostBlockEntityTypes.ICE_CHEST_ENTITY, ChestBlockEntityRenderer::new);
    }

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}

}
