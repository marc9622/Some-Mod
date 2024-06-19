package somemod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.ArmorEntityModel;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import somemod.crystal.block.CrystalBlocks;
import somemod.frost.entity.FrostEntityTypes;
import somemod.frost.entity.render.ArcticZombieEntityRenderer;
import somemod.frost.entity.render.model.FrostEntityModelLayers;
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

        EntityRendererRegistry.register(FrostEntityTypes.ARCTIC_ZOMBIE, ArcticZombieEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(FrostEntityModelLayers.ARCTIC_ZOMBIE,             () -> TexturedModelData.of(BipedEntityModel.getModelData(Dilation.NONE, 0.0f), 64, 64));
        EntityModelLayerRegistry.registerModelLayer(FrostEntityModelLayers.ARCTIC_ZOMBIE_INNER_ARMOR, () -> TexturedModelData.of(ArmorEntityModel.getModelData(new Dilation(0.5f)), 64, 32));
        EntityModelLayerRegistry.registerModelLayer(FrostEntityModelLayers.ARCTIC_ZOMBIE_OUTER_ARMOR, () -> TexturedModelData.of(ArmorEntityModel.getModelData(new Dilation(1.0f)), 64, 32));
    }

}
