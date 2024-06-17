package somemod.utils;

import java.util.function.Consumer;
import java.util.function.UnaryOperator;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Rarity;
import somemod.SomeMod;

public class ItemBuilder<I extends Item> {
    @FunctionalInterface
    public static interface ItemConstructor<I extends Item> {
        public I create(Item.Settings settings);
    }

    @FunctionalInterface
    public static interface AttributeItemConstructor<I extends Item> {
        public I create(Item.Settings settings, Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers);
    }

    private final String path;

    private final AttributeItemConstructor<I> itemConstructor;

    private ItemBuilder(String path, AttributeItemConstructor<I> itemConstructor) {
        this.path = path;
        this.itemConstructor = itemConstructor;
    }

    public static ItemBuilder<Item>.Settinger item(String name) {
        return item(name, (settings, attributeModifiers) -> new Item(settings)).attributes(ImmutableMultimap.of());
    }

    public static ItemBuilder<BlockItem>.Settinger blockItem(String path, Block block) {
        return item(path, (settings, attributeModifiers) -> new BlockItem(block, settings)).attributes(ImmutableMultimap.of());
    }

    public static ItemBuilder<ArmorItem> armorItem(String name, ArmorMaterial material, ArmorItem.Type type) {
        return item(name, (settings, attributeModifiers) -> new ArmorItem(material, type, settings) {
            private final Multimap<EntityAttribute, EntityAttributeModifier> attributes; {
                attributes = ImmutableMultimap.<EntityAttribute, EntityAttributeModifier>builder()
                    .putAll(super.getAttributeModifiers(this.type.getEquipmentSlot()))
                    .putAll(attributeModifiers).build();
            }
            @Override
            public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
                return slot == this.type.getEquipmentSlot() ? this.attributes : super.getAttributeModifiers(slot);
            }
        });
    }

    public static ItemBuilder<ShovelItem> shovelItem(String name, ToolMaterial material, float attackDamage, float attackSpeed) {
        return item(name, (settings, attributeModifiers) -> new ShovelItem(material, attackDamage, attackSpeed, settings) {
            private final Multimap<EntityAttribute, EntityAttributeModifier> attributes; {
                attributes = ImmutableMultimap.<EntityAttribute, EntityAttributeModifier>builder()
                    .putAll(super.getAttributeModifiers(EquipmentSlot.MAINHAND))
                    .putAll(attributeModifiers).build();
            }
            @Override
            public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
                return slot == EquipmentSlot.MAINHAND ? this.attributes : super.getAttributeModifiers(slot);
            }
        });
    }

    public static ItemBuilder<PickaxeItem> pickaxeItem(String name, ToolMaterial material, int attackDamage, float attackSpeed) {
        return item(name, (settings, attributeModifiers) -> new PickaxeItem(material, attackDamage, attackSpeed, settings) {
            private final Multimap<EntityAttribute, EntityAttributeModifier> attributes; {
                attributes =
                ImmutableMultimap.<EntityAttribute, EntityAttributeModifier>builder()
                    .putAll(super.getAttributeModifiers(EquipmentSlot.MAINHAND))
                    .putAll(attributeModifiers).build();
            }
            @Override
            public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
                return slot == EquipmentSlot.MAINHAND ? this.attributes : super.getAttributeModifiers(slot);
            }
        });
    }

    public static ItemBuilder<AxeItem> axeItem(String name, ToolMaterial material, float attackDamage, float attackSpeed) {
        return item(name, (settings, attributeModifiers) -> new AxeItem(material, attackDamage, attackSpeed, settings) {
            private final Multimap<EntityAttribute, EntityAttributeModifier> attributes; {
                attributes = ImmutableMultimap.<EntityAttribute, EntityAttributeModifier>builder()
                    .putAll(super.getAttributeModifiers(EquipmentSlot.MAINHAND))
                    .putAll(attributeModifiers).build();
            }
            @Override
            public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
                return slot == EquipmentSlot.MAINHAND ? this.attributes : super.getAttributeModifiers(slot);
            }
        });
    }

    public static ItemBuilder<HoeItem> hoeItem(String name, ToolMaterial material, int attackDamage, float attackSpeed) {
        return item(name, (settings, attributeModifiers) -> new HoeItem(material, attackDamage, attackSpeed, settings) {
            private final Multimap<EntityAttribute, EntityAttributeModifier> attributes; {
                attributes = ImmutableMultimap.<EntityAttribute, EntityAttributeModifier>builder()
                    .putAll(super.getAttributeModifiers(EquipmentSlot.MAINHAND))
                    .putAll(attributeModifiers).build();
            }
            @Override
            public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
                return slot == EquipmentSlot.MAINHAND ? this.attributes : super.getAttributeModifiers(slot);
            }
        });
    }

    public static ItemBuilder<SwordItem> swordItem(String name, ToolMaterial material, int attackDamage, float attackSpeed) {
        return item(name, (settings, attributeModifiers) -> new SwordItem(material, attackDamage, attackSpeed, settings) {
            private final Multimap<EntityAttribute, EntityAttributeModifier> attributes; {
                attributes = ImmutableMultimap.<EntityAttribute, EntityAttributeModifier>builder()
                    .putAll(super.getAttributeModifiers(EquipmentSlot.MAINHAND))
                    .putAll(attributeModifiers).build();
            }
            @Override
            public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
                return slot == EquipmentSlot.MAINHAND ? this.attributes : super.getAttributeModifiers(slot);
            }
        });
    }

    public static <I extends Item> ItemBuilder<I> item(String name, AttributeItemConstructor<I> itemConstructor) {
        return new ItemBuilder<I>(name, itemConstructor);
    }

    public static <I extends Item> ItemBuilder<I>.Settinger item(String name, ItemConstructor<I> itemConstructor) {
        return item(name, (settings, attributeModifiers) -> itemConstructor.create(settings)).attributes(ImmutableMultimap.of());
    }

    public Settinger attributes(Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers) {
        return new Settinger(attributeModifiers);
    }

    public Settinger attribute(EntityAttribute attribute, EntityAttributeModifier modifier) {
        return new Settinger(ImmutableMultimap.<EntityAttribute, EntityAttributeModifier>of(attribute, modifier));
    }

    public Grouper set(UnaryOperator<Item.Settings> settingsModifier) {
        return new Grouper(settingsModifier.apply(new Item.Settings()));
    }

    public Grouper set(Rarity rarity) {
        return new Grouper(new Item.Settings().rarity(rarity));
    }

    public Grouper addGroup(RegistryKey<ItemGroup> group) {
        return new Grouper().addGroup(group);
    }
    
    public Grouper after(RegistryKey<ItemGroup> group, Item after) {
        return new Grouper().after(group, after);
    }
    
    public Grouper before(RegistryKey<ItemGroup> group, Item before) {
        return new Grouper().before(group, before);
    }

    public I build() {
        I item = itemConstructor.create(new Item.Settings(), ImmutableMultimap.of());
        return SomeMod.registerItem(path, item);
    }
    
    public final class Settinger {
        private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

        private Settinger(Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers) {
            this.attributeModifiers = attributeModifiers;
        }

        public Grouper set(UnaryOperator<Item.Settings> settingsModifier) {
            return new Grouper(settingsModifier.apply(new Item.Settings()), attributeModifiers);
        }

        public Grouper set(Rarity rarity) {
            return new Grouper(new Item.Settings().rarity(rarity), attributeModifiers);
        }

        public Grouper addGroup(RegistryKey<ItemGroup> group) {
            return new Grouper(attributeModifiers).addGroup(group);
        }
        
        public Grouper after(RegistryKey<ItemGroup> group, Item after) {
            return new Grouper(attributeModifiers).after(group, after);
        }
        
        public Grouper before(RegistryKey<ItemGroup> group, Item before) {
            return new Grouper(attributeModifiers).before(group, before);
        }

        public I build() {
            I item = itemConstructor.create(new Item.Settings(), attributeModifiers);
            return SomeMod.registerItem(path, item);
        }
    }

    public final class Grouper {
        private final Item.Settings settings;
        private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

        private Consumer<I> groupAdder = item -> {};

        private Grouper() {
            this(ImmutableMultimap.of());
        }

        private Grouper(Item.Settings settings) {
            this(settings, ImmutableMultimap.of());
        }

        private Grouper(Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers) {
            this(new Item.Settings(), attributeModifiers);
        }

        private Grouper(Item.Settings settings, Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers) {
            this.settings = settings;
            this.attributeModifiers = attributeModifiers;
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

        public Grouper before(RegistryKey<ItemGroup> group, Item before) {
            groupAdder = groupAdder.andThen(
                item -> ItemGroupEvents.modifyEntriesEvent(group).register(content -> content.addBefore(before, item))
            );
            return this;
        }

        public I build() {
            I item = itemConstructor.create(settings, attributeModifiers);
            groupAdder.accept(item);
            return SomeMod.registerItem(path, item);
        }
    }
}
