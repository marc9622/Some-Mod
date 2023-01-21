package somemod.utils;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;

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
    
    private ItemBuilder(String name, Function<Item.Settings, Item> itemConstructor, Item.Settings settings) {
        this.name = name;
        this.itemConstructor = itemConstructor;
        this.settings = settings;
    }

    private final String name;

    private final Function<Item.Settings, Item> itemConstructor;
    private Item.Settings settings;

    private Consumer<Item> groupAdder = item -> {};

    public static ItemBuilder defaultItem(String name) {
        return fromItem(name, Item::new);
    }

    public static ItemBuilder defaultArmorItem(String name, ArmorMaterial material, EquipmentSlot slot) {
        return fromItem(name, settings -> new ArmorItem(material, slot, settings));
    }

    public static ItemBuilder defaultBlockItem(String name, Block block) {
        return fromItem(name, settings -> new BlockItem(block, settings));
    }

    public static ItemBuilder fromItem(String name, Function<Item.Settings, Item> itemConstructor) {
        return new ItemBuilder(name, itemConstructor, new Item.Settings());
    }

    public ItemBuilder modifySettings(UnaryOperator<Item.Settings> settingsModifier) {
        settings = settingsModifier.apply(settings);
        return this;
    }
    
    public ItemBuilder addGroup(ItemGroup group) {
        groupAdder = groupAdder.andThen(
            item -> ItemGroupEvents.modifyEntriesEvent(group).register(content -> content.add(item)));
        return this;
    }
    
    public ItemBuilder addGroupAfter(ItemGroup group, Item after) {
        groupAdder = groupAdder.andThen(
            item -> ItemGroupEvents.modifyEntriesEvent(group).register(content -> content.addAfter(after, item)));
        return this;
    }
    
    public ItemBuilder addGroupBefore(ItemGroup group, Item before) {
        groupAdder = groupAdder.andThen(
            item -> ItemGroupEvents.modifyEntriesEvent(group).register(content -> content.addBefore(before, item)));
        return this;
    }

    private Item register(Item item) {
        return SomeMod.register(Registries.ITEM, name, item);
    }

    public Item build() {
        Item item = itemConstructor.apply(settings);
        groupAdder.accept(item);
        return register(item);
    }

}
