package somemod.utils;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import somemod.SomeMod;

public class ItemBuilder<I extends Item> {
    
    private final String name;

    private final Function<Item.Settings, I> itemConstructor;
    private Item.Settings settings;

    private Consumer<I> groupAdder = item -> {};

    private ItemBuilder(String name, Function<Item.Settings, I> itemConstructor, Item.Settings settings) {
        this.name = name;
        this.itemConstructor = itemConstructor;
        this.settings = settings;
    }

    public static ItemBuilder<Item> defaultItem(String name) {
        return fromItem(name, Item::new);
    }

    public static ItemBuilder<ArmorItem> defaultArmorItem(String name, ArmorMaterial material, ArmorItem.Type type) {
        return fromItem(name, settings -> new ArmorItem(material, type, settings));
    }

    public static ItemBuilder<BlockItem> defaultBlockItem(String name, Block block) {
        return fromItem(name, settings -> new BlockItem(block, settings));
    }

    public static <I extends Item> ItemBuilder<I> fromItem(String name, Function<Item.Settings, I> itemConstructor) {
        return new ItemBuilder<I>(name, itemConstructor, new Item.Settings());
    }

    public ItemBuilder<I> modifySettings(UnaryOperator<Item.Settings> settingsModifier) {
        settings = settingsModifier.apply(settings);
        return this;
    }
    
    public ItemBuilder<I> addGroup(RegistryKey<ItemGroup> group) {
        groupAdder = groupAdder.andThen(
            item -> ItemGroupEvents.modifyEntriesEvent(group).register(content -> content.add(item)));
        return this;
    }
    
    public ItemBuilder<I> groupAfter(RegistryKey<ItemGroup> group, Item after) {
        groupAdder = groupAdder.andThen(
            item -> ItemGroupEvents.modifyEntriesEvent(group).register(content -> content.addAfter(after, item))
        );
        return this;
    }
    
    public ItemBuilder<I> groupBefore(RegistryKey<ItemGroup> group, Item before) {
        groupAdder = groupAdder.andThen(
            item -> ItemGroupEvents.modifyEntriesEvent(group).register(content -> content.addBefore(before, item))
        );
        return this;
    }

    public I build() {
        I item = itemConstructor.apply(settings);
        groupAdder.accept(item);
        return SomeMod.register(Registries.ITEM, name, item);
    }

}
