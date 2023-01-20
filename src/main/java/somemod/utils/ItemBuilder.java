package somemod.utils;

import java.util.function.Function;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import somemod.SomeMod;

public class ItemBuilder {
    
    private ItemBuilder(Item item) {
        this.item = item;
    }

    private final Item item;

    public static ItemBuilder defaultItem(String name) {
        return fromItem(name, new Item(new Item.Settings()));
    }

    public static ItemBuilder defaultArmorItem(String name, ArmorMaterial material, EquipmentSlot slot) {
        return fromItem(name, new ArmorItem(material, slot, new Item.Settings()));
    }

    public static ItemBuilder defaultBlockItem(String name, Block block) {
        return fromItem(name, new BlockItem(block, new Item.Settings()));
    }

    public static ItemBuilder fromItem(String name, Function<Item.Settings, Item> item) {
        return fromItem(name, item.apply(new Item.Settings()));
    }

    public static ItemBuilder fromItem(String name, Item item) {
        var builder = new ItemBuilder(item);
        return builder.register(name);
    }

    private ItemBuilder register(String name) {
        SomeMod.register(Registries.ITEM, name, item);
        return this;
    }

    public ItemBuilder addGroup(ItemGroup group) {
        ItemGroupEvents.modifyEntriesEvent(group).register(content -> content.add(item));
        return this;
    }

    public ItemBuilder addGroupAfter(ItemGroup group, Item after) {
        ItemGroupEvents.modifyEntriesEvent(group).register(content -> content.addAfter(after, item));
        return this;
    }

    public ItemBuilder addGroupBefore(ItemGroup group, Item before) {
        ItemGroupEvents.modifyEntriesEvent(group).register(content -> content.addBefore(before, item));
        return this;
    }

    public Item build() {
        return item;
    }

}
