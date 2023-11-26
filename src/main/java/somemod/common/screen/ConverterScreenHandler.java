package somemod.common.screen;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;

public abstract class ConverterScreenHandler extends ScreenHandler {
    
    public static final int INPUT_SLOT_INDEX = 0;
    public static final int OUTPUT_SLOT_INDEX = INPUT_SLOT_INDEX + 1;
    private static final int PLAYER_INVENTORY_START_INDEX = OUTPUT_SLOT_INDEX + 1;
    private static final int PLAYER_HOTBAR_START_INDEX = PLAYER_INVENTORY_START_INDEX + 27;
    private static final int PLAYER_INVENTORY_END_INDEX = PLAYER_INVENTORY_START_INDEX + 36;

    private final CraftingResultInventory outputInventory = new CraftingResultInventory() {};
    private final Inventory inputInventory = new SimpleInventory(1) {
        @Override
        public void markDirty() {
            super.markDirty();
            ConverterScreenHandler.this.onContentChanged(this);
        }
    };

    protected final ScreenHandlerContext context;
    protected final PlayerEntity player;

    protected ConverterScreenHandler(ScreenHandlerType<? extends ConverterScreenHandler> type, int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(type, syncId);

        this.context = context;
        this.player = playerInventory.player;
        this.addSlot(new Slot(this.inputInventory, INPUT_SLOT_INDEX, 53, 20) {

            @Override
            public boolean canInsert(ItemStack stack) {
                return ConverterScreenHandler.this.canInsertIntoInput(stack, ConverterScreenHandler.this.inputInventory);
            }

            @Override
            public int getMaxItemCount() {
                return ConverterScreenHandler.this.getMaxInputItemCount();
            }

            @Override
            public void onTakeItem(PlayerEntity player, ItemStack stack) {
                ConverterScreenHandler.this.onTakeInputItem(ConverterScreenHandler.this.inputInventory, ConverterScreenHandler.this.outputInventory);
            }
            
        });
        this.addSlot(new Slot(this.outputInventory, OUTPUT_SLOT_INDEX, 107, 20) {

            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }

            @Override
            public boolean canTakeItems(PlayerEntity playerEntity) {
                return ConverterScreenHandler.this.canTakeOutputItem(playerEntity);
            }

            @Override
            public void onTakeItem(PlayerEntity player, ItemStack stack) {
                ConverterScreenHandler.this.onTakeOutputItem(ConverterScreenHandler.this.inputInventory, ConverterScreenHandler.this.outputInventory);
            }

        });

        for(int i = 0; i < 3; ++i)
            for(int j = 0; j < 9; ++j)
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 51 + i * 18));
    
        for(int i = 0; i < 9; ++i)
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 109));

    }

    @Override
    public void onContentChanged(Inventory inventory) {
        super.onContentChanged(inventory);

        if(inventory == this.inputInventory)
            this.onInputChanged(this.inputInventory, this.outputInventory);
        
        if(inventory == this.outputInventory)
            this.onOutputChanged(this.inputInventory, this.outputInventory);
    }

    abstract protected void onInputChanged(Inventory inputInventory, Inventory outputInventory);

    protected void onOutputChanged(Inventory inputInventory, Inventory outputInventory) { return; }

    abstract protected void onTakeInputItem(Inventory inputInventory, Inventory outputInventory);

    abstract protected void onTakeOutputItem(Inventory inputInventory, Inventory outputInventory);

    protected boolean canInsertIntoInput(ItemStack stack, Inventory inputInventory) { return true; }

    protected int getMaxInputItemCount() { return Item.DEFAULT_MAX_COUNT; }

    protected boolean canTakeOutputItem(PlayerEntity player) { return true; }

    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        this.context.run((world, blockPos) -> this.dropInventory(player, this.inputInventory));
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return ScreenHandler.canUse(this.context, this.player, this.getBlock());
    }

    protected abstract Block getBlock();

    @Override
    public ItemStack quickMove(PlayerEntity player, final int selectedSlotIndex) {
        final Slot selectedSlot = this.slots.get(selectedSlotIndex);

        if(selectedSlot == null || !selectedSlot.hasStack())
            return ItemStack.EMPTY;

        ItemStack selectedItemStackNew = selectedSlot.getStack();
        ItemStack selectedItemStackOriginal = selectedItemStackNew.copy();

        if(selectedSlotIndex == INPUT_SLOT_INDEX) {
            if(this.insertItem(selectedItemStackNew, PLAYER_INVENTORY_START_INDEX, PLAYER_INVENTORY_END_INDEX, false))
                selectedSlot.onQuickTransfer(selectedItemStackNew, selectedItemStackOriginal);
            else
                return ItemStack.EMPTY;
        }
        
        else if(selectedSlotIndex == OUTPUT_SLOT_INDEX) {
            if(this.insertItem(selectedItemStackNew, PLAYER_INVENTORY_START_INDEX, PLAYER_INVENTORY_END_INDEX, true))
                selectedSlot.onQuickTransfer(selectedItemStackNew, selectedItemStackOriginal);
            else
                return ItemStack.EMPTY;
        }
        
        else if(selectedSlotIndex >= PLAYER_INVENTORY_START_INDEX && selectedSlotIndex < PLAYER_INVENTORY_END_INDEX) {
            
            if(canInsertIntoInput(selectedItemStackNew, this.inputInventory)) {
                if(this.insertItem(selectedItemStackNew, INPUT_SLOT_INDEX, INPUT_SLOT_INDEX + 1, false))
                    selectedSlot.onQuickTransfer(selectedItemStackNew, selectedItemStackOriginal);
                else
                    return ItemStack.EMPTY;
            }

            else {
                if(selectedSlotIndex < PLAYER_HOTBAR_START_INDEX) {
                    if(this.insertItem(selectedItemStackNew, PLAYER_HOTBAR_START_INDEX, PLAYER_INVENTORY_END_INDEX, false))
                        selectedSlot.onQuickTransfer(selectedItemStackNew, selectedItemStackOriginal);
                    else
                        return ItemStack.EMPTY;
                }
                else if(this.insertItem(selectedItemStackNew, PLAYER_INVENTORY_START_INDEX, PLAYER_HOTBAR_START_INDEX, false))
                    selectedSlot.onQuickTransfer(selectedItemStackNew, selectedItemStackOriginal);
                else
                    return ItemStack.EMPTY;
            }
        }

        if(selectedItemStackNew.isEmpty())
            selectedSlot.setStack(ItemStack.EMPTY);
        else
            selectedSlot.markDirty();

        if(selectedItemStackNew.getCount() == selectedItemStackOriginal.getCount())
            return ItemStack.EMPTY;

        selectedSlot.onTakeItem(player, selectedItemStackNew);
        
        return ItemStack.EMPTY;
    }

}
