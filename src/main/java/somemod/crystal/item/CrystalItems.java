package somemod.crystal.item;

import net.minecraft.item.ArmorItem.Type;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import somemod.crystal.block.CrystalBlocks;
import static somemod.crystal.item.CrystalArmorMaterials.*;
import static somemod.utils.ItemBuilder.*;

public final class CrystalItems {
    
    //#region INGREDIENTS
	public static final Item CRYSTAL_DUST = item("crystal_dust").after(ItemGroups.INGREDIENTS, Items.ANCIENT_DEBRIS).build();
    public static final Item CRYSTAL      = item("crystal")       .after(ItemGroups.INGREDIENTS, CRYSTAL_DUST).build();
    //public static final Item AMETHYST   = defaultItem("amethyst", "Amethyst")        .groupAfter(ItemGroups.INGREDIENTS, CRYSTAL).build(); // Apparently, they already added these to the game. ¯\_(ツ)_/¯
    public static final Item CITRINE      = item("citrine")       .after(ItemGroups.INGREDIENTS, CRYSTAL).build();
    public static final Item RUBY         = item("ruby")          .after(ItemGroups.INGREDIENTS, CITRINE).build();
    public static final Item SAPPHIRE     = item("sapphire")      .after(ItemGroups.INGREDIENTS, RUBY).build();

    // TODO: Add new kind of crafting block that should be used to convert crystals between crystals

    //#endregion

    //#region TOOLS
    public static final ShovelItem CRYSTAL_SHOVEL   = shovelItem("crystal_shovel", CrystalToolMaterial.INSTANCE, 0.5f, -3f).after(ItemGroups.TOOLS, Items.NETHERITE_HOE).build();
    public static final PickaxeItem CRYSTAL_PICKAXE = pickaxeItem("crystal_pickaxe", CrystalToolMaterial.INSTANCE, 0, -2.8f).after(ItemGroups.TOOLS, CRYSTAL_SHOVEL).build();
    public static final AxeItem CRYSTAL_AXE         = axeItem("crystal_axe", CrystalToolMaterial.INSTANCE, 5f, -3f).after(ItemGroups.TOOLS, CRYSTAL_PICKAXE).after(ItemGroups.COMBAT, Items.NETHERITE_AXE).build();
    public static final HoeItem CRYSTAL_HOE         = hoeItem("crystal_hoe", CrystalToolMaterial.INSTANCE, -5, 0f).after(ItemGroups.TOOLS, CRYSTAL_AXE).build();
    //#endregion

    //#region COMBAT
    public static final SwordItem CRYSTAL_SWORD = swordItem("crystal_sword", CrystalToolMaterial.INSTANCE, 3, -2.4f).after(ItemGroups.COMBAT, Items.NETHERITE_SWORD).build();
    
    public static final ArmorItem CRYSTAL_HELMET     = armorItem("crystal_helmet",     CrystalArmorMaterials.CRYSTAL, Type.HELMET)    .after(ItemGroups.COMBAT, Items.NETHERITE_BOOTS).build();
    public static final ArmorItem CRYSTAL_CHESTPLATE = armorItem("crystal_chestplate", CrystalArmorMaterials.CRYSTAL, Type.CHESTPLATE).after(ItemGroups.COMBAT, CRYSTAL_HELMET).build();
    public static final ArmorItem CRYSTAL_LEGGINGS   = armorItem("crystal_leggings",   CrystalArmorMaterials.CRYSTAL, Type.LEGGINGS)  .after(ItemGroups.COMBAT, CRYSTAL_CHESTPLATE).build();
    public static final ArmorItem CRYSTAL_BOOTS      = armorItem("crystal_boots",      CrystalArmorMaterials.CRYSTAL, Type.BOOTS)     .after(ItemGroups.COMBAT, CRYSTAL_LEGGINGS).build();
    
    public static final ArmorItem DRAGON_SCALE_HELMET     = armorItem("dragon_scale_helmet",     DRAGON_SCALE, Type.HELMET)    .after(ItemGroups.COMBAT, CRYSTAL_BOOTS).build();
    public static final ArmorItem DRAGON_SCALE_CHESTPLATE = armorItem("dragon_scale_chestplate", DRAGON_SCALE, Type.CHESTPLATE).after(ItemGroups.COMBAT, DRAGON_SCALE_HELMET).build();
    public static final ArmorItem DRAGON_SCALE_LEGGINGS   = armorItem("dragon_scale_leggings",   DRAGON_SCALE, Type.LEGGINGS)  .after(ItemGroups.COMBAT, DRAGON_SCALE_CHESTPLATE).build();
    public static final ArmorItem DRAGON_SCALE_BOOTS      = armorItem("dragon_scale_boots",      DRAGON_SCALE, Type.BOOTS)     .after(ItemGroups.COMBAT, DRAGON_SCALE_LEGGINGS).build();
    //#endregion

    //#region BLOCKS
    public static final BlockItem CRYSTAL_GLASS = blockItem("crystal_glass", CrystalBlocks.CRYSTAL_GLASS).after(ItemGroups.NATURAL, Items.GLOWSTONE).build();
    public static final BlockItem CRYSTAL_BLOCK = blockItem("crystal_block", CrystalBlocks.CRYSTAL_BLOCK).after(ItemGroups.NATURAL, CRYSTAL_GLASS).build();
    public static final BlockItem CITRINE_ORE   = blockItem("citrine_ore",   CrystalBlocks.CITRINE_ORE)  .after(ItemGroups.NATURAL, Items.EMERALD_ORE).build();
    public static final BlockItem RUBY_ORE      = blockItem("ruby_ore",      CrystalBlocks.RUBY_ORE)     .after(ItemGroups.NATURAL, CITRINE_ORE).build();
    public static final BlockItem SAPPHIRE_ORE  = blockItem("sapphire_ore",  CrystalBlocks.SAPPHIRE_ORE) .after(ItemGroups.NATURAL, RUBY_ORE).build();
    //#endregion

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}

}
