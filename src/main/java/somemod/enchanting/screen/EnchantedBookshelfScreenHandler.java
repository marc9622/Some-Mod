package somemod.enchanting.screen;

import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.Property;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

import somemod.enchanting.block.EnchantingBlocks;

// The way that the bookshelf screen workd currently,
// is that the player can put a book in the input slot,
// and the output slot will show the book with some random
// enchantments on it. The player can then take the book
// out of the output slot, and the original book will be
// destroyed. It works kind of like an anvil then.
// This means that the player can just take the original
// book out, if they don't want the enchantment.
// At this point, I don't know if that's a good thing or not.

public class EnchantedBookshelfScreenHandler extends ScreenHandler {

    public static final int INPUT_SLOT_INDEX = 0;
    public static final int OUTPUT_SLOT_INDEX = 1;
    private static final int PLAYER_INVENTORY_START_INDEX = 2;
    private static final int PLAYER_INVENTORY_END_INDEX = PLAYER_INVENTORY_START_INDEX + 36;
    
    private final CraftingResultInventory outputInventory = new CraftingResultInventory() {};
    private final Inventory inputInventory = new SimpleInventory(1) {
        @Override
        public void markDirty() {
            super.markDirty();
            EnchantedBookshelfScreenHandler.this.onContentChanged(this);
        }
    };

    protected final ScreenHandlerContext context;
    protected final PlayerEntity player;

    private final Random random = Random.create();
    private final Property seed = Property.create(); // Stores the seed for the current player
    private static final Map<PlayerEntity, Integer> seeds = new WeakHashMap<>(); // Stores the seeds for each player.
    // Enchanting tables uses a field in the player entity, but to do it like that, I would have to add a new field to the player entity, which I'm not sure is possible.

    public int enchantmentPower = 0;
    public int enchantmentId = -1;
    public int enchantmentLevel = 0;
    public int enchantmentCost = 0;

    public EnchantedBookshelfScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, ScreenHandlerContext.EMPTY);
    }

    public EnchantedBookshelfScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        this(EnchantingScreenHandlers.ENCHANTED_BOOKSHELF_SCREEN_HANDLER, syncId, playerInventory, context);
    }

    protected EnchantedBookshelfScreenHandler(ScreenHandlerType<? extends EnchantedBookshelfScreenHandler> type, int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(type, syncId);
        
        int i;

        this.context = context;
        this.player = playerInventory.player;
        this.addSlot(new Slot(this.inputInventory, INPUT_SLOT_INDEX, 53, 20) {

            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.getItem() == Items.BOOK;
            }

            @Override
            public int getMaxItemCount() {
                return 1;
            }

            @Override
            public void onTakeItem(PlayerEntity player, ItemStack stack) {
                EnchantedBookshelfScreenHandler.this.onTakeOriginalBook();
            }
            
        });
        this.addSlot(new Slot(this.outputInventory, OUTPUT_SLOT_INDEX, 107, 20) {

            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }

            @Override
            public boolean canTakeItems(PlayerEntity playerEntity) {
                return this.hasStack() && EnchantedBookshelfScreenHandler.this.hasEnoughExperience();
            }

            @Override
            public void onTakeItem(PlayerEntity player, ItemStack stack) {
                EnchantedBookshelfScreenHandler.this.onTakeEnchantedBook();
            }

        });

        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 51 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 109));
        }
    
        seeds.putIfAbsent(player, random.nextInt());
        this.addProperty(this.seed).set(seeds.get(player));
        this.addProperty(new Property() {

            @Override
            public int get() {
                return EnchantedBookshelfScreenHandler.this.enchantmentPower;
            }

            @Override
            public void set(int value) {
                EnchantedBookshelfScreenHandler.this.enchantmentPower = value;
            }

        });
        this.addProperty(new Property() {

            @Override
            public int get() {
                return EnchantedBookshelfScreenHandler.this.enchantmentId;
            }

            @Override
            public void set(int value) {
                EnchantedBookshelfScreenHandler.this.enchantmentId = value;
            }

        });
        this.addProperty(new Property() {

            @Override
            public int get() {
                return EnchantedBookshelfScreenHandler.this.enchantmentLevel;
            }

            @Override
            public void set(int value) {
                EnchantedBookshelfScreenHandler.this.enchantmentLevel = value;
            }

        });
        this.addProperty(new Property() {

            @Override
            public int get() {
                return EnchantedBookshelfScreenHandler.this.enchantmentCost;
            }

            @Override
            public void set(int value) {
                EnchantedBookshelfScreenHandler.this.enchantmentCost = value;
            }

        });
    }

    /**
     * Called when the content of any of the inventories changes.
     */
    @Override
    public void onContentChanged(Inventory inventory) {
        super.onContentChanged(inventory);

        if(inventory == this.inputInventory) {
            ItemStack inputItemStack = inventory.getStack(0);
            if(inputItemStack.isEmpty()) {
                this.enchantmentPower = 0;
                this.enchantmentId = -1;
                this.enchantmentLevel = -1;
                this.enchantmentCost = 0;
            }
            else {
                this.context.run((world, pos) -> {
                    this.random.setSeed(this.seed.get());
                    this.outputInventory.setStack(0, generateOutput(this.random, inputItemStack));

                    this.outputInventory.markDirty();
                    this.sendContentUpdates();
                });
            }
        }
    
        if(inventory == this.outputInventory) {
            ItemStack outputItemStack = inventory.getStack(0);
            if(outputItemStack.isEmpty()) {
                this.enchantmentPower = 0;
                this.enchantmentId = -1;
                this.enchantmentLevel = -1;
                this.enchantmentCost = 0;
            }

            this.sendContentUpdates();
        }
    }

    protected ItemStack generateOutput(Random random, ItemStack inputItemStack) {
        // The enchantment power should probably be shown in the GUI
        this.enchantmentPower = random.nextBetween(getMinEnchantmentPower(), this.getMaxEnchantmentPower()); // Random from Min to Max

        List<EnchantmentLevelEntry> list;

        if(this.enchantmentPower == 0
        || (list = EnchantmentHelper.generateEnchantments(this.random, inputItemStack, this.enchantmentPower, this.canGenerateTreasure())).isEmpty()) {
            this.enchantmentLevel = -1;
            this.enchantmentId = -1;
            this.enchantmentCost = 0;
            return ItemStack.EMPTY;
        }
        else {
            this.enchantmentCost = this.calculateEnchantingCost(random, this.enchantmentPower, inputItemStack, list);
            
            ItemStack outputItemStack = new ItemStack(Items.ENCHANTED_BOOK);

            for(EnchantmentLevelEntry enchantmentLevelEntry : list) {
                EnchantedBookItem.addEnchantment(outputItemStack, enchantmentLevelEntry);
            }

            return outputItemStack;
        }
    }
    
    protected int getMaxEnchantmentPower() {
        return 10;
    }
    
    protected int getMinEnchantmentPower() {
        return 1;
    }

    protected boolean hasEnoughExperience() {
        return player.experienceLevel >= this.getEnchantingCost() || player.getAbilities().creativeMode;
    }

    public int getEnchantingCost() {
        return this.enchantmentCost;
    }

    protected int calculateEnchantingCost(Random random, int enchantmentPower, ItemStack inputItemStack, List<EnchantmentLevelEntry> enchantmentLevelEntry) {
        return enchantmentPower;
    }
    
    public boolean hasPlayerEnoughExperience() {
        return this.hasEnoughExperience();
    }

    protected boolean canGenerateTreasure() {
        return false;
    }

    private void onTakeOriginalBook() {

        if (!player.world.isClient) {
            this.outputInventory.setStack(0, ItemStack.EMPTY);
        }

        this.inputInventory.markDirty();
        this.outputInventory.markDirty();

    }

    private void onTakeEnchantedBook() {

        ItemStack enchantedBookItemStack = this.outputInventory.getStack(0);

        if(!player.world.isClient) {
            if (!player.getAbilities().creativeMode)
                player.applyEnchantmentCosts(enchantedBookItemStack, this.getEnchantingCost());

            this.inputInventory.setStack(0, ItemStack.EMPTY);
        }

        player.incrementStat(Stats.ENCHANT_ITEM);

        // Not sure what this does, but it's used in the vanilla enchantment table
        if (player instanceof ServerPlayerEntity)
            Criteria.ENCHANTED_ITEM.trigger((ServerPlayerEntity)player, enchantedBookItemStack, this.getEnchantingCost());

        this.inputInventory.markDirty();
        this.outputInventory.markDirty();

        EnchantedBookshelfScreenHandler.seeds.put(player, this.random.nextInt());
        this.seed.set(EnchantedBookshelfScreenHandler.seeds.get(player));

        this.onContentChanged(this.outputInventory);
        this.context.run((world, pos) -> world.playSound(null, (BlockPos)pos, SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS, 1.0f, world.random.nextFloat() * 0.1f + 0.9f));    
    }

    @Override
    public void close(PlayerEntity player) {
        super.close(player);
        this.context.run((world, pos) -> this.dropInventory(player, this.inputInventory));
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return EnchantedBookshelfScreenHandler.canUse(this.context, this.player, EnchantingBlocks.ENCHANTED_BOOKSHELF);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, final int selectedSlotIndex) {
        final Slot selectedSlot = (Slot)this.slots.get(selectedSlotIndex);
        
        if(selectedSlot == null || !selectedSlot.hasStack())
            return ItemStack.EMPTY;

        ItemStack itemStack = ItemStack.EMPTY;
        ItemStack itemStack2 = selectedSlot.getStack();
        itemStack = itemStack2.copy();

        if(selectedSlotIndex == INPUT_SLOT_INDEX) {
            if(!this.insertItem(itemStack2, PLAYER_INVENTORY_START_INDEX, PLAYER_INVENTORY_END_INDEX, false))
                return ItemStack.EMPTY;

            selectedSlot.onQuickTransfer(itemStack2, itemStack);
        }
        else if(selectedSlotIndex == OUTPUT_SLOT_INDEX) {
            if(!this.insertItem(itemStack2, PLAYER_INVENTORY_START_INDEX, PLAYER_INVENTORY_END_INDEX, true))
                return ItemStack.EMPTY;

            selectedSlot.onQuickTransfer(itemStack2, itemStack);
        }
        else if(selectedSlotIndex >= PLAYER_INVENTORY_START_INDEX && selectedSlotIndex < PLAYER_INVENTORY_END_INDEX) {
            Slot inputSlot = this.slots.get(INPUT_SLOT_INDEX);
            if(inputSlot.getStack().getCount() >= inputSlot.getMaxItemCount()
            || !this.insertItem(itemStack2, INPUT_SLOT_INDEX, INPUT_SLOT_INDEX + 1, false))
                return ItemStack.EMPTY;
        }
        else
            return ItemStack.EMPTY;

        selectedSlot.markDirty();
        
        if(itemStack2.getCount() == itemStack.getCount())
            return ItemStack.EMPTY;

        selectedSlot.onTakeItem(player, itemStack2);
        return ItemStack.EMPTY;
    }

}