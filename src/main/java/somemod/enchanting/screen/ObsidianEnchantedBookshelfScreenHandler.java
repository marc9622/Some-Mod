package somemod.enchanting.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerContext;
import somemod.enchanting.block.EnchantingBlocks;

public class ObsidianEnchantedBookshelfScreenHandler extends EnchantedBookshelfScreenHandler {

    public ObsidianEnchantedBookshelfScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, ScreenHandlerContext.EMPTY);
    }

    public ObsidianEnchantedBookshelfScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(EnchantingScreenHandlers.OBSIDIAN_ENCHANTED_BOOKSHELF_SCREEN_HANDLER, syncId, playerInventory, context);
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
    public boolean canUse(PlayerEntity player) {
        return ObsidianEnchantedBookshelfScreenHandler.canUse(this.context, this.player, EnchantingBlocks.OBSIDIAN_ENCHANTED_BOOKSHELF);
    }
    
}
