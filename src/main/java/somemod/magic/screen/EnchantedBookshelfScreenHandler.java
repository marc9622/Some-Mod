package somemod.magic.screen;

import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.Property;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import somemod.common.screen.AbstractConverterScreenHandler;
import somemod.magic.block.MagicBlocks;

// The way that the bookshelf screen workd currently,
// is that the player can put a book in the input slot,
// and the output slot will show the book with some random
// enchantments on it. The player can then take the book
// out of the output slot, and the original book will be
// destroyed. It works kind of like an anvil then.
// This means that the player can just take the original
// book out, if they don't want the enchantment.
// At this point, I don't know if that's a good thing or not.

public class EnchantedBookshelfScreenHandler extends AbstractConverterScreenHandler {

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
        this(MagicScreenHandlers.ENCHANTED_BOOKSHELF_SCREEN_HANDLER, syncId, playerInventory, context);
    }

    protected EnchantedBookshelfScreenHandler(ScreenHandlerType<? extends EnchantedBookshelfScreenHandler> type, int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(type, syncId, playerInventory, context);
        
        seeds.putIfAbsent(this.player, random.nextInt());
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

    @Override
    protected void onInputChanged(Inventory inputInventory, Inventory outputInventory) {
        ItemStack inputItemStack = inputInventory.getStack(0);

        if(inputItemStack.isEmpty()) {
            this.enchantmentPower = 0;
            this.enchantmentId = -1;
            this.enchantmentLevel = -1;
            this.enchantmentCost = 0;
        }

        else {
            this.context.run((world, pos) -> {
                this.random.setSeed(this.seed.get());
                outputInventory.setStack(0, generateOutput(this.random, inputItemStack));

                outputInventory.markDirty();
            });
        }

        this.sendContentUpdates();
    }

    @Override
    protected void onOutputChanged(Inventory inputInventory, Inventory outputInventory) {
        ItemStack outputItemStack = outputInventory.getStack(0);

        if(outputItemStack.isEmpty()) {
            this.enchantmentPower = 0;
            this.enchantmentId = -1;
            this.enchantmentLevel = -1;
            this.enchantmentCost = 0;
        }

        this.sendContentUpdates();
    }

    @Override
    protected void onTakeInputItem(Inventory inputInventory, Inventory outputInventory) {
        if (!player.world.isClient)
            outputInventory.setStack(0, ItemStack.EMPTY);

        inputInventory.markDirty();
        outputInventory.markDirty();
    }

    @Override
    protected void onTakeOutputItem(Inventory inputInventory, Inventory outputInventory) {
        ItemStack enchantedBookItemStack = outputInventory.getStack(0);

        if(!player.world.isClient) {
            if (!player.getAbilities().creativeMode)
                player.applyEnchantmentCosts(enchantedBookItemStack, getEnchantingCost());

            inputInventory.setStack(0, ItemStack.EMPTY);
        }

        player.incrementStat(Stats.ENCHANT_ITEM);

        // Not sure what this does, but it's used in the vanilla enchantment table
        if (player instanceof ServerPlayerEntity)
            Criteria.ENCHANTED_ITEM.trigger((ServerPlayerEntity)player, enchantedBookItemStack, this.getEnchantingCost());

        inputInventory.markDirty();
        outputInventory.markDirty();

        EnchantedBookshelfScreenHandler.seeds.put(player, this.random.nextInt());
        this.seed.set(EnchantedBookshelfScreenHandler.seeds.get(player));

        this.onContentChanged(outputInventory);
        this.context.run((world, pos) -> world.playSound(null, (BlockPos)pos, SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS, 1.0f, world.random.nextFloat() * 0.1f + 0.9f));    
    }

    @Override
    protected boolean canInsertIntoInput(ItemStack itemStack, Inventory inputInventory) {
        ItemStack inputStack = inputInventory.getStack(0);
        return itemStack.isOf(Items.BOOK)
            && inputStack.getCount() < Math.min(inputStack.getMaxCount(), this.getMaxInputItemCount());
    }

    @Override
    protected int getMaxInputItemCount() {
        return 1;
    }

    @Override
    protected boolean canTakeOutputItem(PlayerEntity playerEntity) {
        return this.player == playerEntity && EnchantedBookshelfScreenHandler.this.hasEnoughExperience();
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

    protected Block getBlock() {
        return MagicBlocks.ENCHANTED_BOOKSHELF;
    }

}
