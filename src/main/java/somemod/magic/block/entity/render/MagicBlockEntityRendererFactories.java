package somemod.magic.block.entity.render;

import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.block.entity.ChestBlockEntityRenderer;
import somemod.magic.block.entity.MagicBlockEntityTypes;

public class MagicBlockEntityRendererFactories {
    
    static {
        BlockEntityRendererFactories.register(MagicBlockEntityTypes.FORGOTTEN_CHEST_ENTITY, ChestBlockEntityRenderer::new);
    }

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}

}
