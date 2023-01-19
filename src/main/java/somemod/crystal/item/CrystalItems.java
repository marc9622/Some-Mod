package somemod.crystal.item;

import java.util.function.Function;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import somemod.SomeMod;
import static somemod.crystal.block.CrystalBlocks.*;

public final class CrystalItems {
    
    //#region INGREDIENTS
	public static final Item CRYSTAL_DUST = ItemBuilder.defaultItem("crystal_dust").addGroupAfter(ItemGroups.INGREDIENTS, Items.ANCIENT_DEBRIS).build();
    public static final Item CRYSTAL      = ItemBuilder.defaultItem("crystal")     .addGroupAfter(ItemGroups.INGREDIENTS, CRYSTAL_DUST).build();
    //public static final Item AMETHYST   = ItemBuilder.defaultItem("amethyst")    .addGroupAfter(ItemGroups.INGREDIENTS, CRYSTAL).build(); // Apparently, they already added these to the game. ¯\_(ツ)_/¯
    public static final Item CITRINE      = ItemBuilder.defaultItem("citrine")     .addGroupAfter(ItemGroups.INGREDIENTS, CRYSTAL).build();
    public static final Item RUBY         = ItemBuilder.defaultItem("ruby")        .addGroupAfter(ItemGroups.INGREDIENTS, CITRINE).build();
    public static final Item SAPPHIRE     = ItemBuilder.defaultItem("sapphire")    .addGroupAfter(ItemGroups.INGREDIENTS, RUBY).build();
    //#endregion

    //#region TOOLS
    public static final Item CRYSTAL_SHOVEL  = ItemBuilder.fromItem("crystal_shovel",  CrystalShovelItem::new) .addGroupAfter(ItemGroups.TOOLS, Items.NETHERITE_HOE).build();
    public static final Item CRYSTAL_PICKAXE = ItemBuilder.fromItem("crystal_pickaxe", CrystalPickaxeItem::new).addGroupAfter(ItemGroups.TOOLS, CRYSTAL_SHOVEL).build();
    public static final Item CRYSTAL_AXE     = ItemBuilder.fromItem("crystal_axe",     CrystalAxeItem::new)    .addGroupAfter(ItemGroups.TOOLS, CRYSTAL_PICKAXE)
                                                                                                               .addGroupAfter(ItemGroups.COMBAT, Items.NETHERITE_AXE).build();
    public static final Item CRYSTAL_HOE     = ItemBuilder.fromItem("crystal_hoe",     CrystalHoeItem::new)    .addGroupAfter(ItemGroups.TOOLS, CRYSTAL_AXE).build();
    //#endregion

    //#region COMBAT
    public static final Item CRYSTAL_SWORD = ItemBuilder.fromItem("crystal_sword", CrystalSwordItem::new).addGroupAfter(ItemGroups.COMBAT, Items.NETHERITE_SWORD).build();
    
    public static final ArmorMaterial CRYSTAL_ARMOR_MATERIAL = new CrystalArmorMaterial();
    
    public static final Item CRYSTAL_HELMET     = ItemBuilder.defaultArmorItem("crystal_helmet",     CRYSTAL_ARMOR_MATERIAL, EquipmentSlot.HEAD) .addGroupAfter(ItemGroups.COMBAT, Items.NETHERITE_BOOTS).build();
    public static final Item CRYSTAL_CHESTPLATE = ItemBuilder.defaultArmorItem("crystal_chestplate", CRYSTAL_ARMOR_MATERIAL, EquipmentSlot.CHEST).addGroupAfter(ItemGroups.COMBAT, CRYSTAL_HELMET).build();
    public static final Item CRYSTAL_LEGGINGS   = ItemBuilder.defaultArmorItem("crystal_leggings",   CRYSTAL_ARMOR_MATERIAL, EquipmentSlot.LEGS) .addGroupAfter(ItemGroups.COMBAT, CRYSTAL_CHESTPLATE).build();
    public static final Item CRYSTAL_BOOTS      = ItemBuilder.defaultArmorItem("crystal_boots",      CRYSTAL_ARMOR_MATERIAL, EquipmentSlot.FEET) .addGroupAfter(ItemGroups.COMBAT, CRYSTAL_LEGGINGS).build();
    //#endregion

    //#region BLOCKS
    public static final Item CRYSTAL_GLASS_ITEM = ItemBuilder.defaultBlockItem("crystal_glass", CRYSTAL_GLASS).addGroupAfter(ItemGroups.NATURAL, Items.GLOWSTONE).build();
    public static final Item CRYSTAL_BLOCK_ITEM = ItemBuilder.defaultBlockItem("crystal_block", CRYSTAL_BLOCK).addGroupAfter(ItemGroups.NATURAL, CRYSTAL_GLASS_ITEM).build();
    public static final Item CITRINE_ORE_ITEM   = ItemBuilder.defaultBlockItem("citrine_ore",   CITRINE_ORE)  .addGroupAfter(ItemGroups.NATURAL, Items.EMERALD_ORE).build();
    public static final Item RUBY_ORE_ITEM      = ItemBuilder.defaultBlockItem("ruby_ore",      RUBY_ORE)     .addGroupAfter(ItemGroups.NATURAL, CITRINE_ORE_ITEM).build();
    public static final Item SAPPHIRE_ORE_ITEM  = ItemBuilder.defaultBlockItem("sapphire_ore",  SAPPHIRE_ORE) .addGroupAfter(ItemGroups.NATURAL, RUBY_ORE_ITEM).build();
    //#endregion

    private static class ItemBuilder {

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

}
