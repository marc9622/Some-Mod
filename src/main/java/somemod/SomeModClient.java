package somemod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import somemod.crystal.block.CrystalBlocks;
import somemod.enchanting.screen.EnchantedBookshelfScreen;
import somemod.enchanting.screen.EnchantingScreenHandlers;

public class SomeModClient implements ClientModInitializer {
    
    @Override
    public void onInitializeClient() {
        
        BlockRenderLayerMap.INSTANCE.putBlock(CrystalBlocks.CRYSTAL_GLASS, RenderLayer.getTranslucent());

        HandledScreens.register(EnchantingScreenHandlers.ENCHANTED_BOOKSHELF_SCREEN_HANDLER, EnchantedBookshelfScreen::new);
        HandledScreens.register(EnchantingScreenHandlers.OBSIDIAN_ENCHANTED_BOOKSHELF_SCREEN_HANDLER, EnchantedBookshelfScreen::new);

    }

}
