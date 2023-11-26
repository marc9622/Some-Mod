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
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Rarity;
import somemod.SomeMod;

public class ItemBuilder<I extends Item> {
    
    private final String path;

    private final Function<Item.Settings, I> itemConstructor;

    private ItemBuilder(String path, Function<Item.Settings, I> itemConstructor) {
        this.path = path;
        this.itemConstructor = itemConstructor;
    }

    public static ItemBuilder<Item> item(String name) {
        return item(name, Item::new);
    }

    public static ItemBuilder<ArmorItem> armorItem(String name, ArmorMaterial material, ArmorItem.Type type) {
        return item(name, settings -> new ArmorItem(material, type, settings));
    }

    public static ItemBuilder<BlockItem> blockItem(String path, Block block) {
        return new ItemBuilder<BlockItem>(path, settings -> new BlockItem(block, settings));
    }

    public static <I extends Item> ItemBuilder<I> item(String name, Function<Item.Settings, I> itemConstructor) {
        return new ItemBuilder<I>(name, itemConstructor);
    }

    public Grouper set(UnaryOperator<Item.Settings> settingsModifier) {
        return new Grouper(settingsModifier.apply(new Item.Settings()));
    }

    public Grouper set(Rarity rarity) {
        return new Grouper(new Item.Settings().rarity(rarity));
    }

    public I build() {
        I item = itemConstructor.apply(new Item.Settings());
        return SomeMod.registerItem(path, item);
    }
    
    public Grouper addGroup(RegistryKey<ItemGroup> group) {
        return new Grouper().addGroup(group);
    }
    
    public Grouper after(RegistryKey<ItemGroup> group, Item after) {
        return new Grouper().after(group, after);
    }
    
    public Grouper before(RegistryKey<ItemGroup> group, Item before) {
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

        public Grouper after(RegistryKey<ItemGroup> group, Item after) {
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
            return SomeMod.registerItem(path, item);
        }
    }

}
