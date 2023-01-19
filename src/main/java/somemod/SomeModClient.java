package somemod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import somemod.crystal.block.CrystalBlocks;

public class SomeModClient implements ClientModInitializer {
    
    @Override
    public void onInitializeClient() {
        
        BlockRenderLayerMap.INSTANCE.putBlock(CrystalBlocks.CRYSTAL_GLASS, RenderLayer.getTranslucent());

    }

}
