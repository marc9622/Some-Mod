package somemod.magic.screen;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import somemod.SomeMod;

public class EnchantedBookshelfScreen extends HandledScreen<EnchantedBookshelfScreenHandler> {

    private static final Identifier TEXTURE = SomeMod.id("textures/gui/container/enchanted_bookshelf.png");
    private static final String ENCHANTING_COST_TEXT = "container.somemod.enchanted_bookshelf.cost"; // or use the vanilla anvil text: "container.repair.cost". (Already translated) 
    private static final String ENCHANTING_TOO_EXPENSIVE_TEXT = "container.somemod.enchanted_bookshelf.expensive"; // or use the vanilla anvil text: "container.repair.expensive". (Already translated)

    public EnchantedBookshelfScreen(EnchantedBookshelfScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundHeight = 133;
        this.playerInventoryTitleY = this.backgroundHeight - 94;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(context, mouseX, mouseY);
    }

    @Override
    protected void drawForeground(DrawContext context, int mouseX, int mouseY) {
        RenderSystem.disableBlend();
        super.drawForeground(context, mouseX, mouseY);
        
        int cost = this.handler.getEnchantingCost();
        if(cost <= 0) return;

        Text text;
        int color;

        if(this.handler.hasEnoughExperience()) {
            text = Text.translatable(ENCHANTING_COST_TEXT, cost);
            color = 0x80FF20; // 8453920 in dec
        }
        else {
            text = Text.translatable(ENCHANTING_TOO_EXPENSIVE_TEXT, cost);
            color = 0xFF6060;
        }

        int x = (this.backgroundWidth - this.textRenderer.getWidth(text)) - 9;
        int y = 39;

        context.fill(x - 2, y - 2, x + this.textRenderer.getWidth(text) + 2, y + 10, 0x4F000000);
        context.drawTextWithShadow(this.textRenderer, text, x, y, color);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        int x = (this.width - this.backgroundWidth) / 2;
        int y = (this.height - this.backgroundHeight) / 2;
        context.drawTexture(TEXTURE, x, y, 0, 0, this.backgroundWidth, this.backgroundHeight);
    }

}
