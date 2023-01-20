package somemod.crystal.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import static somemod.utils.ItemBuilder.*;

import static somemod.crystal.block.CrystalBlocks.*;

public final class CrystalItems {
    
    //#region INGREDIENTS
	public static final Item CRYSTAL_DUST = defaultItem("crystal_dust").addGroupAfter(ItemGroups.INGREDIENTS, Items.ANCIENT_DEBRIS).build();
    public static final Item CRYSTAL      = defaultItem("crystal")     .addGroupAfter(ItemGroups.INGREDIENTS, CRYSTAL_DUST).build();
    //public static final Item AMETHYST   = defaultItem("amethyst")    .addGroupAfter(ItemGroups.INGREDIENTS, CRYSTAL).build(); // Apparently, they already added these to the game. ¯\_(ツ)_/¯
    public static final Item CITRINE      = defaultItem("citrine")     .addGroupAfter(ItemGroups.INGREDIENTS, CRYSTAL).build();
    public static final Item RUBY         = defaultItem("ruby")        .addGroupAfter(ItemGroups.INGREDIENTS, CITRINE).build();
    public static final Item SAPPHIRE     = defaultItem("sapphire")    .addGroupAfter(ItemGroups.INGREDIENTS, RUBY).build();
    //#endregion

    //#region TOOLS
    public static final Item CRYSTAL_SHOVEL  = fromItem("crystal_shovel",  CrystalShovelItem::new) .addGroupAfter(ItemGroups.TOOLS, Items.NETHERITE_HOE).build();
    public static final Item CRYSTAL_PICKAXE = fromItem("crystal_pickaxe", CrystalPickaxeItem::new).addGroupAfter(ItemGroups.TOOLS, CRYSTAL_SHOVEL).build();
    public static final Item CRYSTAL_AXE     = fromItem("crystal_axe",     CrystalAxeItem::new)    .addGroupAfter(ItemGroups.TOOLS, CRYSTAL_PICKAXE)
                                                                                                               .addGroupAfter(ItemGroups.COMBAT, Items.NETHERITE_AXE).build();
    public static final Item CRYSTAL_HOE     = fromItem("crystal_hoe",     CrystalHoeItem::new)    .addGroupAfter(ItemGroups.TOOLS, CRYSTAL_AXE).build();
    //#endregion

    //#region COMBAT
    public static final Item CRYSTAL_SWORD = fromItem("crystal_sword", CrystalSwordItem::new).addGroupAfter(ItemGroups.COMBAT, Items.NETHERITE_SWORD).build();
    
    public static final ArmorMaterial CRYSTAL_ARMOR_MATERIAL = new CrystalArmorMaterial();
    
    public static final Item CRYSTAL_HELMET     = defaultArmorItem("crystal_helmet",     CRYSTAL_ARMOR_MATERIAL, EquipmentSlot.HEAD) .addGroupAfter(ItemGroups.COMBAT, Items.NETHERITE_BOOTS).build();
    public static final Item CRYSTAL_CHESTPLATE = defaultArmorItem("crystal_chestplate", CRYSTAL_ARMOR_MATERIAL, EquipmentSlot.CHEST).addGroupAfter(ItemGroups.COMBAT, CRYSTAL_HELMET).build();
    public static final Item CRYSTAL_LEGGINGS   = defaultArmorItem("crystal_leggings",   CRYSTAL_ARMOR_MATERIAL, EquipmentSlot.LEGS) .addGroupAfter(ItemGroups.COMBAT, CRYSTAL_CHESTPLATE).build();
    public static final Item CRYSTAL_BOOTS      = defaultArmorItem("crystal_boots",      CRYSTAL_ARMOR_MATERIAL, EquipmentSlot.FEET) .addGroupAfter(ItemGroups.COMBAT, CRYSTAL_LEGGINGS).build();
    //#endregion

    //#region BLOCKS
    public static final Item CRYSTAL_GLASS_ITEM = defaultBlockItem("crystal_glass", CRYSTAL_GLASS).addGroupAfter(ItemGroups.NATURAL, Items.GLOWSTONE).build();
    public static final Item CRYSTAL_BLOCK_ITEM = defaultBlockItem("crystal_block", CRYSTAL_BLOCK).addGroupAfter(ItemGroups.NATURAL, CRYSTAL_GLASS_ITEM).build();
    public static final Item CITRINE_ORE_ITEM   = defaultBlockItem("citrine_ore",   CITRINE_ORE)  .addGroupAfter(ItemGroups.NATURAL, Items.EMERALD_ORE).build();
    public static final Item RUBY_ORE_ITEM      = defaultBlockItem("ruby_ore",      RUBY_ORE)     .addGroupAfter(ItemGroups.NATURAL, CITRINE_ORE_ITEM).build();
    public static final Item SAPPHIRE_ORE_ITEM  = defaultBlockItem("sapphire_ore",  SAPPHIRE_ORE) .addGroupAfter(ItemGroups.NATURAL, RUBY_ORE_ITEM).build();
    //#endregion

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}

}
