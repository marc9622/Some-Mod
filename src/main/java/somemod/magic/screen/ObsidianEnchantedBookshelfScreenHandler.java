package somemod.magic.screen;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerContext;
import somemod.magic.block.MagicBlocks;

public class ObsidianEnchantedBookshelfScreenHandler extends EnchantedBookshelfScreenHandler {

    public ObsidianEnchantedBookshelfScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, ScreenHandlerContext.EMPTY);
    }

    public ObsidianEnchantedBookshelfScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(MagicScreenHandlers.OBSIDIAN_ENCHANTED_BOOKSHELF_SCREEN_HANDLER, syncId, playerInventory, context);
    }

    @Override
    protected int getMaxEnchantmentPower() {
        return 30;
    }

    @Override
    protected int getMinEnchantmentPower() {
        return 10;
    }

    @Override
    protected boolean canGenerateTreasure() {
        return true;
    }

    @Override
    protected Block getBlock() {
        return MagicBlocks.OBSIDIAN_ENCHANTED_BOOKSHELF;
    }
    
}
