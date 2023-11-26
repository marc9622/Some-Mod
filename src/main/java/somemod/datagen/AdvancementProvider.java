package somemod.datagen;

import java.util.function.Consumer;
import java.util.function.Function;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import somemod.SomeMod;
import somemod.crystal.item.CrystalItems;

public class AdvancementProvider extends FabricAdvancementProvider {

    public static final String END_BREAK_END_STONE_CRYSTAL_TITLE = "advancements.somemod.end.break.end_stone.crystal.title";
    public static final String END_BREAK_END_STONE_CRYSTAL_DESCRIPTION = "advancements.somemod.break.end_stone.crystal.description";

    public static final String JEWELLERY_ROOT_TITLE = "advancements.somemod.jewellery.root.title";
    public static final String JEWELLERY_ROOT_DESCRIPTION = "advancements.somemod.jewellery.root.description";

    public static final String JEWELLERY_CRAFT_CRYSTAL_TITLE = "advancements.somemod.jewellery.craft.crystal.title";
    public static final String JEWELLERY_CRAFT_CRYSTAL_DESCRIPTION = "advancements.somemod.jewellery.craft.crystal.description";

    public static final String JEWELLERY_CRAFT_CRYSTAL_ARMOR_TITLE = "advancements.somemod.jewellery.craft.crystal.armor.title";
    public static final String JEWELLERY_CRAFT_CRYSTAL_ARMOR_DESCRIPTION = "advancements.somemod.jewellery.craft.crystal.armor.description";

    public static final String JEWELLERY_CRAFT_CRYSTAL_TOOLS_TITLE = "advancements.somemod.jewellery.craft.crystal.tools.title";
    public static final String JEWELLERY_CRAFT_CRYSTAL_TOOLS_DESCRIPTION = "advancements.somemod.jewellery.craft.crystal.tools.description";

    protected AdvancementProvider(FabricDataOutput output) {
        super(output);
    }

    @SuppressWarnings("unused")
    @Override
    public void generateAdvancement(Consumer<Advancement> exporter) {

        // Advancement endBreakEndstoneCrystal = create("end/break_endstone_crystal", exporter, b -> b
        //     .parent(new Identifier("minecraft", "advancements/end/root")) // TODO: I don't know how to get the advancement from the registry
        //     .display(
        //             CrystalBlocks.CRYSTAL_GLASS,
        //             Text.translatable("advancements.somemod.end.break.end_stone.crystal.title"),
        //             Text.translatable("advancements.somemod.end.break.end_stone.crystal.description"),
        //             null,
        //             AdvancementFrame.TASK,
        //             true, true, false
        //     )
        //     .criterion("has_crystal_dust", InventoryChangedCriterion.Conditions.items(CrystalItems.CRYSTAL_DUST))
        //     .criterion("has_crystal_glass", InventoryChangedCriterion.Conditions.items(CrystalItems.CRYSTAL_GLASS_ITEM))
        //     .requirements(anyOf("has_crystal_dust", "has_crystal_glass")));

        Advancement jewelleryRoot = create("jewellery/root", exporter, b -> b
            .display(
                CrystalItems.CRYSTAL_DUST,
                Text.translatable(JEWELLERY_ROOT_TITLE),
                Text.translatable(JEWELLERY_ROOT_DESCRIPTION),
                new Identifier("minecraft:textures/gui/advancements/backgrounds/nether.png"), // TODO: Make custom background
                AdvancementFrame.TASK,
                false, false, false
            )
            .criterion("has_crystal_dust", InventoryChangedCriterion.Conditions.items(CrystalItems.CRYSTAL_DUST))
            .requirements(anyOf("has_crystal_dust")));
            //.requirements(anyOf("has_crystal_dust")));

        Advancement jewelleryCraftCrystal = create("jewellery/craft_crystal", exporter, b -> b
            .parent(jewelleryRoot)
            .display(
                CrystalItems.CRYSTAL,
                Text.translatable(JEWELLERY_CRAFT_CRYSTAL_TITLE),
                Text.translatable(JEWELLERY_CRAFT_CRYSTAL_DESCRIPTION),
                null,
                AdvancementFrame.TASK,
                true, true, false
            )
            .criterion("has_crystal", InventoryChangedCriterion.Conditions.items(CrystalItems.CRYSTAL))
            .requirements(anyOf("has_crystal")));
            //.requirements(anyOf("has_crystal")));

        Advancement jewelleryCraftCrystalArmor = create("jewellery/craft_crystal_armor", exporter, b -> b
            .parent(jewelleryCraftCrystal)
            .display(
                CrystalItems.CRYSTAL_CHESTPLATE,
                Text.translatable(JEWELLERY_CRAFT_CRYSTAL_ARMOR_TITLE),
                Text.translatable(JEWELLERY_CRAFT_CRYSTAL_ARMOR_DESCRIPTION),
                null,
                AdvancementFrame.TASK,
                true, true, false
            )
            .criterion("has_helmet", InventoryChangedCriterion.Conditions.items(CrystalItems.CRYSTAL_HELMET))
            .criterion("has_chestplate", InventoryChangedCriterion.Conditions.items(CrystalItems.CRYSTAL_CHESTPLATE))
            .criterion("has_leggings", InventoryChangedCriterion.Conditions.items(CrystalItems.CRYSTAL_LEGGINGS))
            .criterion("has_boots", InventoryChangedCriterion.Conditions.items(CrystalItems.CRYSTAL_BOOTS))
            .requirements(anyOf("has_helmet", "has_chestplate", "has_leggings", "has_boots")));
            //.requirements(anyOf("has_helmet", "has_chestplate", "has_leggings", "has_boots")));

        Advancement jewelleryCraftCrystalTools = create("jewellery/craft_crystal_tools", exporter, b -> b
            .parent(jewelleryCraftCrystal)
            .display(
                CrystalItems.CRYSTAL_PICKAXE,
                Text.translatable(JEWELLERY_CRAFT_CRYSTAL_TOOLS_TITLE),
                Text.translatable(JEWELLERY_CRAFT_CRYSTAL_TOOLS_DESCRIPTION),
                null,
                AdvancementFrame.TASK,
                true, true, false
            )
            .criterion("has_sword", InventoryChangedCriterion.Conditions.items(CrystalItems.CRYSTAL_SWORD))
            .criterion("has_pickaxe", InventoryChangedCriterion.Conditions.items(CrystalItems.CRYSTAL_PICKAXE))
            .criterion("has_axe", InventoryChangedCriterion.Conditions.items(CrystalItems.CRYSTAL_AXE))
            .criterion("has_shovel", InventoryChangedCriterion.Conditions.items(CrystalItems.CRYSTAL_SHOVEL))
            .criterion("has_hoe", InventoryChangedCriterion.Conditions.items(CrystalItems.CRYSTAL_HOE))
            .requirements(anyOf("has_pickaxe", "has_axe", "has_shovel", "has_hoe", "has_sword")));
            //.requirements(anyOf("has_pickaxe", "has_axe", "has_shovel", "has_hoe", "has_sword")));
    }

    private static Advancement create(String path, Consumer<Advancement> exporter, Function<Advancement.Builder, Advancement.Builder> function) {
        Advancement advancement = function.apply(Advancement.Builder.create()).build(exporter, SomeMod.idString(path));
        SomeMod.logAdvancementRegistration(path);
        return advancement;
    }

    private static String[][] anyOf(String... strings) {
        return new String[][] { strings };
    }

    // For 12.0.2

    // private static AdvancementRequirements allOf(String... requirements) {
    //     return AdvancementRequirements.allOf(List.of(requirements));
    // }

    // private static AdvancementRequirements anyOf(String... requirements) {
    //     return AdvancementRequirements.anyOf(List.of(requirements));
    // }

}
