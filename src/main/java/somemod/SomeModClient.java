package somemod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import somemod.crystal.block.CrystalBlocks;
import somemod.magic.entity.MagicEntityTypes;
import somemod.magic.entity.render.GhostEntityRenderer;
import somemod.magic.entity.render.model.GhostEntityModel;
import somemod.magic.entity.render.model.MagicEntityModelLayers;
import somemod.magic.screen.EnchantedBookshelfScreen;
import somemod.magic.screen.MagicScreenHandlers;

public class SomeModClient implements ClientModInitializer {
    
    @Override
    public void onInitializeClient() {
        
        BlockRenderLayerMap.INSTANCE.putBlock(CrystalBlocks.CRYSTAL_GLASS, RenderLayer.getTranslucent());

        HandledScreens.register(MagicScreenHandlers.ENCHANTED_BOOKSHELF_SCREEN_HANDLER,          EnchantedBookshelfScreen::new);
        HandledScreens.register(MagicScreenHandlers.OBSIDIAN_ENCHANTED_BOOKSHELF_SCREEN_HANDLER, EnchantedBookshelfScreen::new);

        EntityRendererRegistry.register(MagicEntityTypes.GHOST, GhostEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(MagicEntityModelLayers.GHOST, GhostEntityModel::getTexturedModelData);

    }

}
