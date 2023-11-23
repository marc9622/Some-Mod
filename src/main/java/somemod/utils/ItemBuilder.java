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

    private ItemBuilder(String name, Function<Item.Settings, I> itemConstructor) {
        this.name = name;
        this.itemConstructor = itemConstructor;
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
        return new ItemBuilder<I>(name, itemConstructor);
    }

    public Grouper settings(UnaryOperator<Item.Settings> settingsModifier) {
        return new Grouper(settingsModifier.apply(new Item.Settings()));
    }

    public I build() {
        I item = itemConstructor.apply(new Item.Settings());
        return SomeMod.register(Registries.ITEM, name, item);
    }
    
    public Grouper addGroup(RegistryKey<ItemGroup> group) {
        return new Grouper().addGroup(group);
    }
    
    public Grouper groupAfter(RegistryKey<ItemGroup> group, Item after) {
        return new Grouper().groupAfter(group, after);
    }
    
    public Grouper groupBefore(RegistryKey<ItemGroup> group, Item before) {
        return new Grouper().groupBefore(group, before);
    }

    public final class Grouper {

        private final Item.Settings settings;
        private Consumer<I> groupAdder = item -> {};

        private Grouper() {
            this(new Item.Settings());
        }

        private Grouper(Item.Settings settings) {
            this.settings = settings;
        }

        public Grouper addGroup(RegistryKey<ItemGroup> group) {
            groupAdder = groupAdder.andThen(
                item -> ItemGroupEvents.modifyEntriesEvent(group).register(content -> content.add(item))
            );
            return this;
        }

        public Grouper groupAfter(RegistryKey<ItemGroup> group, Item after) {
            groupAdder = groupAdder.andThen(
                item -> ItemGroupEvents.modifyEntriesEvent(group).register(content -> content.addAfter(after, item))
            );
            return this;
        }

        public Grouper groupBefore(RegistryKey<ItemGroup> group, Item before) {
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

}
