package somemod.magic.block;

import org.jetbrains.annotations.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import somemod.magic.block.entity.EnchantedBookshelfBlockEntity;
import somemod.magic.screen.ObsidianEnchantedBookshelfScreenHandler;

public class ObsidianEnchantedBookshelfBlock extends EnchantedBookshelfBlock {

    public ObsidianEnchantedBookshelfBlock(Settings settings) {
        super(settings);
    }

    @Override
    @Nullable
    public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof EnchantedBookshelfBlockEntity obsidianBookshelfBlockEntity) {
            Text text = obsidianBookshelfBlockEntity.getDisplayName();
            return new SimpleNamedScreenHandlerFactory((syncId, inventory, player) -> new ObsidianEnchantedBookshelfScreenHandler(syncId, inventory, ScreenHandlerContext.create(world, pos)), text);
        }
        return null;
    }
    
}
