package somemod.enchanting.item;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Rarity;

import static somemod.utils.ItemBuilder.*;

import static somemod.enchanting.block.EnchantingBlocks.*;

public class EnchantingItems {
    
    public static final Item ENCHANTED_BOOKSHELF_ITEM =
        fromItem("enchanted_bookshelf", s -> new BlockItem(ENCHANTED_BOOKSHELF, s) {
            @Override public boolean hasGlint(ItemStack stack) { return true; }
        }).modifySettings(s -> s.rarity(Rarity.UNCOMMON)).addGroupBefore(ItemGroups.FUNCTIONAL, Items.BOOKSHELF).build(); // Or maybe next to enchanting table instead?

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}

}
