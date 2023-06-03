package somemod.magic.screen;

import net.minecraft.registry.Registries;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;
import somemod.SomeMod;

public class MagicScreenHandlers {
    
    public static final ScreenHandlerType<EnchantedBookshelfScreenHandler> ENCHANTED_BOOKSHELF_SCREEN_HANDLER =
        SomeMod.register(Registries.SCREEN_HANDLER, "enchanted_bookshelf", new ScreenHandlerType<>(EnchantedBookshelfScreenHandler::new, FeatureFlags.VANILLA_FEATURES));
    public static final ScreenHandlerType<ObsidianEnchantedBookshelfScreenHandler> OBSIDIAN_ENCHANTED_BOOKSHELF_SCREEN_HANDLER =
        SomeMod.register(Registries.SCREEN_HANDLER, "obsidian_enchanted_bookshelf", new ScreenHandlerType<>(ObsidianEnchantedBookshelfScreenHandler::new, FeatureFlags.VANILLA_FEATURES));

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}

}
