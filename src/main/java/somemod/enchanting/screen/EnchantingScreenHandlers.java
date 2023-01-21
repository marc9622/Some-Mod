package somemod.enchanting.screen;

import net.minecraft.registry.Registries;
import net.minecraft.screen.ScreenHandlerType;
import somemod.SomeMod;

public class EnchantingScreenHandlers {
    
    public static final ScreenHandlerType<EnchantedBookshelfScreenHandler> ENCHANTED_BOOKSHELF_SCREEN_HANDLER =
        SomeMod.register(Registries.SCREEN_HANDLER, "enchanted_bookshelf", new ScreenHandlerType<>(EnchantedBookshelfScreenHandler::new));
    public static final ScreenHandlerType<ObsidianEnchantedBookshelfScreenHandler> OBSIDIAN_ENCHANTED_BOOKSHELF_SCREEN_HANDLER =
        SomeMod.register(Registries.SCREEN_HANDLER, "obsidian_enchanted_bookshelf", new ScreenHandlerType<>(ObsidianEnchantedBookshelfScreenHandler::new));

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}

}
