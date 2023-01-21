package somemod.enchanting.block.entity;

import org.jetbrains.annotations.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Nameable;
import net.minecraft.util.math.BlockPos;

public class EnchantedBookshelfBlockEntity extends BlockEntity implements Nameable, NamedScreenHandlerFactory {

    @Nullable
    private Text customName;

    public EnchantedBookshelfBlockEntity(BlockPos pos, BlockState state) {
        super(EnchantingBlockEntities.ENCHANTED_BOOKSHELF_ENTITY, pos, state);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        if (this.customName != null) {
            nbt.putString("CustomName", Text.Serializer.toJson(this.customName));
        }
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        if (nbt.contains("CustomName", NbtElement.STRING_TYPE)) {
            this.customName = Text.Serializer.fromJson(nbt.getString("CustomName"));
        }
    }

    public void setCustomName(Text customName) {
        this.customName = customName;
    }

    @Override
    public Text getName() {
        if (this.customName != null) {
            return this.customName;
        }
        return Text.of("Enchant");
    }

    @Override
    public Text getDisplayName() {
        return this.getName();
    }

    @Override
    @Nullable
    public Text getCustomName() {
        return this.customName;
    }

    @Override
    @Nullable
    public ScreenHandler createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return null;
    }
    
}