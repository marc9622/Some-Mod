package somemod.crystal.item;

import net.minecraft.item.ArmorItem.Type;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;

import somemod.crystal.block.CrystalBlocks;
import static somemod.crystal.item.CrystalArmorMaterials.*;
import static somemod.utils.ItemBuilder.*;

public final class CrystalItems {
    
    //#region INGREDIENTS
	public static final Item CRYSTAL_DUST = defaultItem("crystal_dust").groupAfter(ItemGroups.INGREDIENTS, Items.ANCIENT_DEBRIS).build();
    public static final Item CRYSTAL      = defaultItem("crystal")     .groupAfter(ItemGroups.INGREDIENTS, CRYSTAL_DUST).build();
    //public static final Item AMETHYST   = defaultItem("amethyst")    .groupAfter(ItemGroups.INGREDIENTS, CRYSTAL).build(); // Apparently, they already added these to the game. ¯\_(ツ)_/¯
    public static final Item CITRINE      = defaultItem("citrine")     .groupAfter(ItemGroups.INGREDIENTS, CRYSTAL).build();
    public static final Item RUBY         = defaultItem("ruby")        .groupAfter(ItemGroups.INGREDIENTS, CITRINE).build();
    public static final Item SAPPHIRE     = defaultItem("sapphire")    .groupAfter(ItemGroups.INGREDIENTS, RUBY).build();
    //#endregion

    //#region TOOLS
    public static final CrystalShovelItem CRYSTAL_SHOVEL   = fromItem("crystal_shovel",  CrystalShovelItem::new) .groupAfter(ItemGroups.TOOLS, Items.NETHERITE_HOE).build();
    public static final CrystalPickaxeItem CRYSTAL_PICKAXE = fromItem("crystal_pickaxe", CrystalPickaxeItem::new).groupAfter(ItemGroups.TOOLS, CRYSTAL_SHOVEL).build();
    public static final CrystalAxeItem CRYSTAL_AXE         = fromItem("crystal_axe",     CrystalAxeItem::new)    .groupAfter(ItemGroups.TOOLS, CRYSTAL_PICKAXE).groupAfter(ItemGroups.COMBAT, Items.NETHERITE_AXE).build();
    public static final CrystalHoeItem CRYSTAL_HOE         = fromItem("crystal_hoe",     CrystalHoeItem::new)    .groupAfter(ItemGroups.TOOLS, CRYSTAL_AXE).build();
    //#endregion

    //#region COMBAT
    public static final CrystalSwordItem CRYSTAL_SWORD = fromItem("crystal_sword", CrystalSwordItem::new).groupAfter(ItemGroups.COMBAT, Items.NETHERITE_SWORD).build();
    
    public static final ArmorItem CRYSTAL_HELMET     = defaultArmorItem("crystal_helmet",     CrystalArmorMaterials.CRYSTAL, Type.HELMET)    .groupAfter(ItemGroups.COMBAT, Items.NETHERITE_BOOTS).build();
    public static final ArmorItem CRYSTAL_CHESTPLATE = defaultArmorItem("crystal_chestplate", CrystalArmorMaterials.CRYSTAL, Type.CHESTPLATE).groupAfter(ItemGroups.COMBAT, CRYSTAL_HELMET).build();
    public static final ArmorItem CRYSTAL_LEGGINGS   = defaultArmorItem("crystal_leggings",   CrystalArmorMaterials.CRYSTAL, Type.LEGGINGS)  .groupAfter(ItemGroups.COMBAT, CRYSTAL_CHESTPLATE).build();
    public static final ArmorItem CRYSTAL_BOOTS      = defaultArmorItem("crystal_boots",      CrystalArmorMaterials.CRYSTAL, Type.BOOTS)     .groupAfter(ItemGroups.COMBAT, CRYSTAL_LEGGINGS).build();
    
    public static final ArmorItem DRAGON_SCALE_HELMET     = defaultArmorItem("dragon_scale_helmet",     DRAGON_SCALE, Type.HELMET)    .groupAfter(ItemGroups.COMBAT, CRYSTAL_BOOTS).build();
    public static final ArmorItem DRAGON_SCALE_CHESTPLATE = defaultArmorItem("dragon_scale_chestplate", DRAGON_SCALE, Type.CHESTPLATE).groupAfter(ItemGroups.COMBAT, DRAGON_SCALE_HELMET).build();
    public static final ArmorItem DRAGON_SCALE_LEGGINGS   = defaultArmorItem("dragon_scale_leggings",   DRAGON_SCALE, Type.LEGGINGS)  .groupAfter(ItemGroups.COMBAT, DRAGON_SCALE_CHESTPLATE).build();
    public static final ArmorItem DRAGON_SCALE_BOOTS      = defaultArmorItem("dragon_scale_boots",      DRAGON_SCALE, Type.BOOTS)     .groupAfter(ItemGroups.COMBAT, DRAGON_SCALE_LEGGINGS).build();
    //#endregion

    //#region BLOCKS
    public static final BlockItem CRYSTAL_GLASS = defaultBlockItem("crystal_glass", CrystalBlocks.CRYSTAL_GLASS).groupAfter(ItemGroups.NATURAL, Items.GLOWSTONE).build();
    public static final BlockItem CRYSTAL_BLOCK = defaultBlockItem("crystal_block", CrystalBlocks.CRYSTAL_BLOCK).groupAfter(ItemGroups.NATURAL, CRYSTAL_GLASS).build();
    public static final BlockItem CITRINE_ORE   = defaultBlockItem("citrine_ore",   CrystalBlocks.CITRINE_ORE)  .groupAfter(ItemGroups.NATURAL, Items.EMERALD_ORE).build();
    public static final BlockItem RUBY_ORE      = defaultBlockItem("ruby_ore",      CrystalBlocks.RUBY_ORE)     .groupAfter(ItemGroups.NATURAL, CITRINE_ORE).build();
    public static final BlockItem SAPPHIRE_ORE  = defaultBlockItem("sapphire_ore",  CrystalBlocks.SAPPHIRE_ORE) .groupAfter(ItemGroups.NATURAL, RUBY_ORE).build();
    //#endregion

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}

}
