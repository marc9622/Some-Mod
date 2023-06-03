package somemod.datagen;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.condition.WeatherCheckLootCondition;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.entry.LootTableEntry;
import net.minecraft.loot.function.ConditionalLootFunction;
import net.minecraft.loot.function.LootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.function.SetDamageLootFunction;
import net.minecraft.loot.function.SetEnchantmentsLootFunction;
import net.minecraft.loot.function.SetLoreLootFunction;
import net.minecraft.loot.function.SetNameLootFunction;
import net.minecraft.loot.function.SetPotionLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.LootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import somemod.SomeMod;
import somemod.magic.potion.MagicPotions;

import static somemod.magic.item.MagicItems.*;

@SuppressWarnings("unused")
public class ChestLootTableProvider extends SimpleFabricLootTableProvider {

    private static final List<Consumer<BiConsumer<Identifier, LootTable.Builder>>> exportings = new ArrayList<>();

    public ChestLootTableProvider(FabricDataOutput output) {
        super(output, LootContextTypes.CHEST);
    }

    @Override
    public void accept(BiConsumer<Identifier, LootTable.Builder> exporter) {
        exportings.forEach(exporting -> exporting.accept(exporter));
    }

    //#region Elven
    /*
        Aerendy and Elaith are in love, but far apart.
        Elaith sends a letter saying she will visit Aerendy in the villiage.

        Emryth is on a small journey towards the east, but see their enemies approaching for attack.
        He warns the others by sending a letter back to the village, but sadly also dies.

        Aerendy sees this letter and convince the others to flee to the west.
        He also writes a letter to King Elnaril and Ashryn to inform about the attack.

        Aerendy, Artin and Isarrel seek out to find Emryth, but they cannot find him.
        Sadly Isarrel dies while trying to find Emryth.

        Aerendy writes a letter back to Elaith, saying she should not come to the village.
        Aerendy and Artin decide to flee west with the others.

        Aleva finds Elaith's dead body and informs Aerendy.
     */

    // TODO: Use Text.translateable()
    private static final Text[] ELVEN_LORE_ELNARIL = new Text[]{Text.of("Belonged to " + "King Elnaril Gilran"),Text.of("of the Elven Kingdom.")};
    private static final Text[] ELVEN_LORE_ASHRYN  = new Text[]{Text.of("Belogned to " + "Ashryn Gilran"),      Text.of("of the Elven Kingdom.")};
    private static final Text[] ELVEN_LORE_EMRYTH  = new Text[]{Text.of("Lost by "     + "Emryth Vallee"),      Text.of("after dying in battle.")};
    private static final Text[] ELVEN_LORE_AERENDY = new Text[]{Text.of("Gifted to "   + "Aerendy Naereiros"),  Text.of("for his heroric deeds.")};
    private static final Text[] ELVEN_LORE_ELAITH  = new Text[]{Text.of("Given to "    + "Elaith Leoleth"),     Text.of("from her beloved.")};
    private static final Text[] ELVEN_LORE_ARTIN   = new Text[]{Text.of("Made by "     + "Artin Rohan Glynlee,"),Text.of("A true master of his craft.")};
    private static final Text[] ELVEN_LORE_ISARREL = new Text[]{Text.of("Lost by "     + "Isarrel Aywin-Enjor"),Text.of("after dying in battle.")};
    private static final Text[] ELVEN_LORE_ALEVA   = new Text[]{Text.of("Belonged to " + "Princess Aleva Wrangella,"),Text.of("The lone hunter.")};

    private static final LeafEntry.Builder<?> ELVEN_LORE_1 =
        itemEntry(Items.PAPER, 1, constant(1), setName(Text.of("Letter")), setLore(
            Text.of("Dear Aerendy, it has been too long"),
            Text.of("since I last saw you. I miss you"),
            Text.of("so very much. I am planning to come"),
            Text.of("visit you very soon. - Elaith")));

    private static final LeafEntry.Builder<?> ELVEN_LORE_2 =
        itemEntry(Items.PAPER, 1, constant(1), setName(Text.of("Note")), setLore(
            Text.of("I will be going on a small journey"),
            Text.of("not too far east. Do not worry; I will"),
            Text.of("be back before you know it. - Emryth")));

    private static final LeafEntry.Builder<?> ELVEN_LORE_3 =
        itemEntry(Items.PAPER, 1, constant(1), setName(Text.of("Note")), setLore(
            Text.of("THEY ARE COMING FROM THE EAST"),
            Text.of("WARN THE OTHERS AND PREPARE YOURSELVES")));

    private static final LeafEntry.Builder<?> ELVEN_LORE_4 =
        itemEntry(Items.PAPER, 1, constant(1), setName(Text.of("Note")), setLore(
            Text.of("To Artin and Isarrel,"),
            Text.of("I have written a letter to"),
            Text.of("Elnaril and Ashryn, informing"),
            Text.of("them about the attack."),
            Text.of("We must flee west. - Aerendy")));

    private static final LeafEntry.Builder<?> ELVEN_LORE_5 =
        itemEntry(Items.PAPER, 1, constant(1), setName(Text.of("Note")), setLore(
            Text.of("Isarrel, hopefully you will see this."),
            Text.of("Aerendy and I have been looking for him,"),
            Text.of("but he is nowhere to be found."),
            Text.of("So we have decided to travel"),
            Text.of("west tomorrow at noon, but by the"),
            Text.of("time you read this, we have"),
            Text.of("probably already left. - Artin")));

    private static final LeafEntry.Builder<?> ELVEN_LORE_6 =
        itemEntry(Items.PAPER, 1, constant(1), setName(Text.of("Letter")), setLore(
            Text.of("Elaith, my dear. If you find this,"),
            Text.of("please do not go towards the village."),
            Text.of("I have travelled west with the others."),
            Text.of("I hope you are safe.")));

    private static final LeafEntry.Builder<?> ELVEN_LORE_7 =
        itemEntry(Items.PAPER, 1, constant(1), setName(Text.of("Letter")), setLore(
            Text.of("Aerendy Naereiros... I am sorry"),
            Text.of("to inform you... but I found"),
            Text.of("Elaith dead near the village..."),
            Text.of("I am so so sorry..."),
            Text.of(" - Aleva Wrangella")));

    private static final LeafEntry.Builder<?> ELVEN_HELMET_ELNARIL =
        itemEntry(ELVEN_HELMET, 1, constant(1), setDamage(uniform(0.5f, 0.95f)), setLore(ELVEN_LORE_ELNARIL), setEnchantments()
            .enchantment(Enchantments.PROTECTION, uniform(2, 3))
            .enchantment(Enchantments.UNBREAKING, constant(1)));

    private static final LeafEntry.Builder<?> ELVEN_HELMET_ASHRYN =
        itemEntry(ELVEN_HELMET, 1, constant(1), setDamage(uniform(0.25f, 0.95f)), setLore(ELVEN_LORE_ASHRYN), setEnchantments()
            .enchantment(Enchantments.PROTECTION, uniform(1, 2))
            .enchantment(Enchantments.MENDING, constant(1)));

    private static final LeafEntry.Builder<?> ELVEN_CHESTPLATE_EMRYTH =
        itemEntry(ELVEN_CHESTPLATE, 1, constant(1), setDamage(uniform(0.1f, 0.5f)), setLore(ELVEN_LORE_EMRYTH), setEnchantments()
            .enchantment(Enchantments.PROTECTION, uniform(0, 3))
            .enchantment(Enchantments.MENDING, constant(1)));

    private static final LeafEntry.Builder<?> ELVEN_CHESTPLATE_AERENDY =
        itemEntry(ELVEN_CHESTPLATE, 1, constant(1), setDamage(uniform(0.8f, 0.95f)), setLore(ELVEN_LORE_AERENDY), setEnchantments()
            .enchantment(Enchantments.PROTECTION, uniform(1, 2))
            .enchantment(Enchantments.THORNS, uniform(0, 1))
            .enchantment(Enchantments.UNBREAKING, uniform(0, 1)));

    private static final LeafEntry.Builder<?> ELVEN_SWORD_ELAITH =
        itemEntry(Items.IRON_SWORD, 1, constant(1), setDamage(uniform(0.2f, 0.6f)), setLore(ELVEN_LORE_ELAITH), setEnchantments()
            .enchantment(Enchantments.SMITE, uniform(1, 2))
            .enchantment(Enchantments.UNBREAKING, constant(1)));

    private static final LeafEntry.Builder<?> ELVEN_LEGGINGS_ARTIN =
        itemEntry(ELVEN_LEGGINGS, 1, constant(1), setDamage(uniform(0.1f, 0.95f)), setLore(ELVEN_LORE_ARTIN), setEnchantments()
            .enchantment(Enchantments.FEATHER_FALLING, uniform(0, 1))
            .enchantment(Enchantments.PROTECTION, uniform(1, 2))
            .enchantment(Enchantments.UNBREAKING, uniform(0, 2)));

    private static final LeafEntry.Builder<?> ELVEN_BOOTS_ISARREL =
        itemEntry(ELVEN_BOOTS, 1, constant(1), setDamage(uniform(0.5f, 0.95f)), setLore(ELVEN_LORE_ISARREL), setEnchantments()
            .enchantment(Enchantments.DEPTH_STRIDER, constant(1))
            .enchantment(Enchantments.PROTECTION, uniform(0, 1)));

    private static final LeafEntry.Builder<?> ELVEN_BOW_ALEVA =
        itemEntry(Items.BOW, 1, constant(1), setDamage(uniform(0.15f, 0.95f)), setLore(ELVEN_LORE_ALEVA), setEnchantments()
            .enchantment(Enchantments.POWER, uniform(0, 2))
            .enchantment(Enchantments.PUNCH, uniform(0, 1))
            .enchantment(Enchantments.INFINITY, uniform(0, 1))
            .enchantment(Enchantments.MENDING, uniform(0, 1)));

    private static final LeafEntry.Builder<?> ELVEN_HELMET_BASIC     = itemEntry(ELVEN_HELMET,     2, constant(1), setDamage(uniform(0.1f, 0.95f)));
    private static final LeafEntry.Builder<?> ELVEN_CHESTPLATE_BASIC = itemEntry(ELVEN_CHESTPLATE, 2, constant(1), setDamage(uniform(0.1f, 0.95f)));
    private static final LeafEntry.Builder<?> ELVEN_LEGGINGS_BASIC   = itemEntry(ELVEN_LEGGINGS,   2, constant(1), setDamage(uniform(0.1f, 0.95f)));
    private static final LeafEntry.Builder<?> ELVEN_BOOTS_BASIC      = itemEntry(ELVEN_BOOTS,      2, constant(1), setDamage(uniform(0.1f, 0.95f)));
    private static final LeafEntry.Builder<?> ELVEN_SWORD_BASIC      = itemEntry(Items.IRON_SWORD, 2, constant(1), setDamage(uniform(0.1f, 0.95f)));
    private static final LeafEntry.Builder<?> ELVEN_BOW_BASIC        = itemEntry(Items.BOW,        2, constant(1), setDamage(uniform(0.1f, 0.95f)));

    public static final Identifier ELVEN_CHEST_WRITING =
        registerLootTable("elven_chest_writing",
            LootPool.builder()
                .rolls(uniform(2, 3))
                .with(itemEntry(Items.BOOK,       4, uniform(1, 3)))
                .with(itemEntry(Items.PAPER,     10, uniform(2, 4)))
                .with(itemEntry(Items.FEATHER,    6, uniform(2, 4)))
                .with(itemEntry(Items.INK_SAC,    5, constant(1)))
                .with(itemEntry(Items.BLACK_DYE,  2, uniform(1, 3)))
                .with(itemEntry(Items.GREEN_DYE,  1, uniform(1, 2)))
                .with(itemEntry(Items.PURPLE_DYE, 1, uniform(1, 2)))
                .with(itemEntry(Items.YELLOW_DYE, 1, uniform(1, 2)))
                .with(itemEntry(Items.STRING,     3, uniform(2, 4)))
                .with(itemEntry(Items.NAME_TAG,   1))
        );

    public static final Identifier ELVEN_CHEST_FOOD =
        registerLootTable("elven_chest_food",
            LootPool.builder()
                .rolls(uniform(2, 3))
                .with(itemEntry(Items.WHEAT,       3, uniform(2, 4)))
                .with(itemEntry(Items.WHEAT_SEEDS, 1, uniform(2, 4)))
                .with(itemEntry(Items.BREAD,       2, uniform(1, 3)))
                .with(itemEntry(Items.APPLE,       4, uniform(2, 4)))
                .with(itemEntry(Items.SWEET_BERRIES, 4, uniform(2, 4)))
                .with(itemEntry(Items.SUGAR,       1, uniform(2, 4)))
                .with(itemEntry(Items.SUGAR_CANE,  1, uniform(2, 4)))
                .with(itemEntry(Items.STRING,      2, uniform(2, 4)))
                .with(itemEntry(Items.NAME_TAG,    1))
        );

    public static final Identifier ELVEN_CHEST_COMBAT =
        registerLootTable("elven_chest_combat",
            LootPool.builder()
                .rolls(uniform(2, 3))
                .with(itemEntry(Items.ARROW,  5, uniform(2, 4)))
                .with(itemEntry(Items.STICK,  4, uniform(2, 4)))
                .with(itemEntry(Items.FLINT,  3, uniform(2, 4)))
                .with(itemEntry(Items.APPLE,  1, uniform(1, 3)))
                .with(itemEntry(Items.POTION, 1, constant(1), setPotion(Potions.HEALING)))
                .with(itemEntry(Items.POTION, 1, constant(1), setPotion(Potions.REGENERATION)))
                .with(itemEntry(Items.POTION, 1, constant(1), setPotion(Potions.INVISIBILITY)))
                .with(itemEntry(Items.WOODEN_SWORD, 1, constant(1), setDamage(uniform(0.2f, 0.8f))))
                .with(itemEntry(Items.STONE_SWORD,  1, constant(1), setDamage(uniform(0.2f, 0.8f))))
                .with(itemEntry(Items.WOODEN_AXE,   1, constant(1), setDamage(uniform(0.2f, 0.8f))))
                .with(itemEntry(Items.STONE_AXE,    1, constant(1), setDamage(uniform(0.2f, 0.8f))))
                .with(itemEntry(Items.BOW,          2, constant(1), setDamage(uniform(0.2f, 0.8f))))
        );

    public static final Identifier ELVEN_CHEST_MAGIC =
        registerLootTable("elven_chest_magic",
            LootPool.builder()
                .rolls(constant(2))
                .with(itemEntry(Items.GLASS_BOTTLE, 8, uniform(1, 2)))
                .with(itemEntry(Items.POTION, 2, uniform(1, 2), setPotion(Potions.AWKWARD)))
                .with(itemEntry(Items.POTION, 2, uniform(1, 2), setPotion(Potions.MUNDANE)))
                .with(itemEntry(Items.POTION, 2, uniform(1, 2), setPotion(Potions.THICK)))
                .with(itemEntry(Items.BOOK,   4, uniform(2, 4))),
            LootPool.builder()
                .rolls(constant(1))
                .with(itemEntry(Items.ENDER_PEARL,  8, uniform(1, 3)))
                .with(itemEntry(Items.SPIDER_EYE,  10, uniform(1, 3)))
                .with(itemEntry(Items.FERMENTED_SPIDER_EYE, 5, constant(1)))
                .with(itemEntry(Items.ROTTEN_FLESH, 5, uniform(1, 3)))
                .with(itemEntry(Items.SLIME_BALL,  10, uniform(2, 4)))
                .with(itemEntry(Items.SUGAR,        5, uniform(1, 3)))
                .with(itemEntry(Items.GOLDEN_APPLE, 2, constant(1)))
                .with(itemEntry(Items.GLISTERING_MELON_SLICE, 2, constant(1)))
                .with(itemEntry(Items.EXPERIENCE_BOTTLE, 1, uniform(1, 2))),
            LootPool.builder()
                .rolls(constant(1))
                .with(itemEntry(Items.POTION,         4, constant(1), setPotion(MagicPotions.AGILITY)))
                .with(itemEntry(Items.SPLASH_POTION,  4, constant(1), setPotion(MagicPotions.AGILITY)))
                .with(itemEntry(Items.POTION,         3, constant(1), setPotion(Potions.SWIFTNESS)))
                .with(itemEntry(Items.SPLASH_POTION,  3, constant(1), setPotion(Potions.SWIFTNESS)))
                .with(itemEntry(Items.POTION,         3, constant(1), setPotion(MagicPotions.FERAL_ENDURANCE)))
                .with(itemEntry(Items.SPLASH_POTION,  3, constant(1), setPotion(MagicPotions.FERAL_ENDURANCE)))
                .with(itemEntry(Items.POTION,         2, constant(1), setPotion(MagicPotions.MAGIC_RESILIENCE)))
                .with(itemEntry(Items.SPLASH_POTION,  2, constant(1), setPotion(MagicPotions.MAGIC_RESILIENCE)))
                .with(itemEntry(Items.POTION,         1, constant(1), setPotion(Potions.LUCK)))
                .with(itemEntry(Items.ENCHANTED_BOOK, 1, constant(1), setEnchantments().enchantment(Enchantments.MENDING, constant(1))))
                .with(itemEntry(Items.ENCHANTED_BOOK, 2, constant(1), setEnchantments().enchantment(Enchantments.PROTECTION, uniform(1, 2))))
                .with(itemEntry(Items.ENCHANTED_BOOK, 2, constant(1), setEnchantments().enchantment(Enchantments.UNBREAKING, uniform(1, 2))))
                .with(itemEntry(Items.ENCHANTED_BOOK, 2, constant(1), setEnchantments().enchantment(Enchantments.SMITE, constant(1))))
                .with(itemEntry(Items.ENCHANTED_BOOK, 2, constant(1), setEnchantments().enchantment(Enchantments.INFINITY, constant(1))))
        );

    public static final Identifier ELVEN_CHEST_DARK_OAK =
        registerLootTable("elven_chest_building_dark_oak",
            LootPool.builder()
                .rolls(constant(1))
                .with(itemEntry(Items.DARK_OAK_LOG,    4, uniform(1, 3)))
                .with(itemEntry(Items.DARK_OAK_PLANKS, 5, uniform(2, 4)))
                .with(itemEntry(Items.DARK_OAK_FENCE,  2, uniform(2, 4)))
        );

    public static final Identifier ELVEN_CHEST_OAK =
        registerLootTable("elven_chest_building_oak",
            LootPool.builder()
                .rolls(constant(1))
                .with(itemEntry(Items.OAK_LOG,    4, uniform(1, 3)))
                .with(itemEntry(Items.OAK_PLANKS, 5, uniform(2, 4)))
                .with(itemEntry(Items.OAK_FENCE,  2, uniform(2, 4)))
        );

    public static final Identifier ELVEN_CHEST_BIRCH =
        registerLootTable("elven_chest_building_birch",
            LootPool.builder()
                .rolls(constant(1))
                .with(itemEntry(Items.BIRCH_LOG,    4, uniform(1, 3)))
                .with(itemEntry(Items.BIRCH_PLANKS, 5, uniform(2, 4)))
                .with(itemEntry(Items.BIRCH_FENCE,  2, uniform(2, 4)))
        );

    public static final Identifier ELVEN_CHEST_BUILDING =
        registerLootTable("elven_chest_building",
            LootPool.builder()
                .rolls(constant(1))
                .conditionally(randomChance(0.5f))
                .with(itemEntry(Items.CHEST))
                .with(itemEntry(Items.CRAFTING_TABLE))
                .with(itemEntry(Items.CAMPFIRE))
                .with(itemEntry(Items.WOODEN_PICKAXE, 1, constant(1), setDamage(uniform(0.2f, 0.8f))))
                .with(itemEntry(Items.WOODEN_AXE,     1, constant(1), setDamage(uniform(0.2f, 0.8f))))
                .with(itemEntry(Items.WOODEN_SHOVEL,  1, constant(1), setDamage(uniform(0.2f, 0.8f))))
                .with(itemEntry(Items.WOODEN_HOE,     1, constant(1), setDamage(uniform(0.2f, 0.8f)))),
            LootPool.builder()
                .rolls(constant(2))
                .with(LootTableEntry.builder(ELVEN_CHEST_DARK_OAK))
                .with(LootTableEntry.builder(ELVEN_CHEST_OAK))
                .with(LootTableEntry.builder(ELVEN_CHEST_BIRCH)),
            LootPool.builder()
                .rolls(constant(2))
                .with(itemEntry(Items.STICK,       5, uniform(2, 6)))
                .with(itemEntry(Items.COBBLESTONE, 1, uniform(2, 4)))
                .with(itemEntry(Items.STRING,      3, uniform(2, 4)))
                .with(itemEntry(Items.FLINT,       3, uniform(1, 2)))
        );

    public static final Identifier ELVEN_CHEST_VALUABLES =
        registerLootTable("elven_chest_valuables",
            LootPool.builder()
                .rolls(constant(1))
                .with(itemEntry(Items.EXPERIENCE_BOTTLE, 1, uniform(1, 2)))
                .with(itemEntry(Items.EMERALD, 1, constant(1)))
                .with(itemEntry(Items.DIAMOND, 1, constant(1)))
                .with(itemEntry(Items.GOLD_NUGGET, 3, uniform(9, 18)))
                .with(itemEntry(Items.LEATHER_HORSE_ARMOR, 3, constant(1)))
                .with(itemEntry(Items.IRON_HORSE_ARMOR,    2, constant(1)))
                .with(itemEntry(Items.GOLDEN_HORSE_ARMOR,  2, constant(1)))
                .with(itemEntry(Items.COMPASS, 1, constant(1)))
                .with(itemEntry(Items.CLOCK,   1, constant(1)))
        );

    public static final Identifier ELVEN_CHEST =
        registerLootTable("elven_chest",
            LootPool.builder()
                .rolls(constant(1))
                .with(ELVEN_HELMET_ELNARIL)
                .with(ELVEN_HELMET_ASHRYN)
                .with(ELVEN_CHESTPLATE_EMRYTH)
                .with(ELVEN_CHESTPLATE_AERENDY)
                .with(ELVEN_LEGGINGS_ARTIN)
                .with(ELVEN_BOOTS_ISARREL)
                .with(ELVEN_SWORD_ELAITH)
                .with(ELVEN_BOW_ALEVA)
                .with(ELVEN_HELMET_BASIC)
                .with(ELVEN_CHESTPLATE_BASIC)
                .with(ELVEN_LEGGINGS_BASIC)
                .with(ELVEN_BOOTS_BASIC)
                .with(ELVEN_SWORD_BASIC)
                .with(ELVEN_BOW_BASIC)
                .with(itemEntry(ELVEN_STEEL, 5, uniform(3, 5))),
            LootPool.builder()
                .rolls(constant(1))
                .with(lootTableEntry(ELVEN_CHEST_WRITING))
                .with(lootTableEntry(ELVEN_CHEST_FOOD))
                .with(lootTableEntry(ELVEN_CHEST_COMBAT))
                .with(lootTableEntry(ELVEN_CHEST_MAGIC))
                .with(lootTableEntry(ELVEN_CHEST_BUILDING))
                .with(lootTableEntry(ELVEN_CHEST_VALUABLES)),
            LootPool.builder()
                .rolls(constant(1))
                .with(lootTableEntry(ELVEN_CHEST_WRITING))
                .with(lootTableEntry(ELVEN_CHEST_FOOD))
                .with(lootTableEntry(ELVEN_CHEST_COMBAT))
                .with(lootTableEntry(ELVEN_CHEST_BUILDING))
                .with(ELVEN_LORE_1)
                .with(ELVEN_LORE_2)
                .with(ELVEN_LORE_3)
                .with(ELVEN_LORE_4)
                .with(ELVEN_LORE_5)
                .with(ELVEN_LORE_6)
                .with(ELVEN_LORE_7),
            LootPool.builder()
                .rolls(constant(1))
                .with(itemEntry(ELVEN_STEEL, 1, uniform(1, 4)))
        );
    //#endregion

    private static Identifier registerLootTable(String path, LootPool.Builder... poolBuilders) {
        Identifier id = SomeMod.registerLootTable(path);
        exportings.add(exporter -> export(exporter, id, poolBuilders));
        return id;
    }

    private static void export(BiConsumer<Identifier, LootTable.Builder> exporter, Identifier id, LootPool.Builder... poolBuilders) {
        LootTable.Builder builder = LootTable.builder();
        for (LootPool.Builder poolBuilder : poolBuilders) {
            builder = builder.pool(poolBuilder);
        }
        exporter.accept(id, builder);
    }
    
    private static LeafEntry.Builder<?> itemEntry(ItemConvertible item) {
        return ItemEntry.builder(item);
    }

    private static LeafEntry.Builder<?> itemEntry(Item item, int weight) {
        return itemEntry(item).weight(weight);
    }

    private static LeafEntry.Builder<?> itemEntry(Item item, int weight, LootNumberProvider countRange) {
        return itemEntry(item, weight).apply(ChestLootTableProvider.setCount(countRange));
    }

    private static LeafEntry.Builder<?> itemEntry(Item item, int weight, LootNumberProvider countRange, ConditionalLootFunction.Builder<?>... lootFunctions) {
        LeafEntry.Builder<?> builder = itemEntry(item, weight, countRange);
        for (LootFunction.Builder lootFunction : lootFunctions)
            builder = builder.apply(lootFunction);
        return builder;
    }
    
    private static LeafEntry.Builder<?> itemEntry(Item item, int weight, LootNumberProvider countRange, List<ConditionalLootFunction.Builder<?>> lootFunctions, List<LootCondition.Builder> lootConditions) {
        LeafEntry.Builder<?> builder = itemEntry(item, weight, countRange);
        for (LootFunction.Builder lootFunction : lootFunctions)
            builder = builder.apply(lootFunction);
        for (LootCondition.Builder lootCondition : lootConditions)
            builder = builder.conditionally(lootCondition);
        return builder;
    }

    private static LootTableEntry.Builder<?> lootTableEntry(Identifier id) {
        return LootTableEntry.builder(id);
    }

    private static SetNameLootFunction.Builder<?> setName(Text name) {
        return SetNameLootFunction.builder(name);
    }

    private static SetLoreLootFunction.Builder setLore(Text... lore) {
        SetLoreLootFunction.Builder builder = SetLoreLootFunction.builder();
        for (Text line : lore)
            builder = builder.lore(line);
        return builder;
    }

    private static SetDamageLootFunction.Builder<?> setDamage(LootNumberProvider damageRange) {
        return SetDamageLootFunction.builder(damageRange);
    }

    private static SetEnchantmentsLootFunction.Builder setEnchantments() {
        return new SetEnchantmentsLootFunction.Builder();
    }

    private static ConditionalLootFunction.Builder<?> setPotion(Potion potion) {
        return SetPotionLootFunction.builder(potion);
    }

    private static ConditionalLootFunction.Builder<?> setCount(LootNumberProvider countRange) {
        return SetCountLootFunction.builder(countRange);
    }

    private static UniformLootNumberProvider uniform(float min, float max) {
        return UniformLootNumberProvider.create(min, max);
    }

    private static ConstantLootNumberProvider constant(float value) {
        return ConstantLootNumberProvider.create(value);
    }
    
    private static enum Weather {
        SUNNY,
        RAINING,
        THUNDERING,
        THUNDERSTORM
    }

    private static LootCondition.Builder weatherCheck(Weather weather) {
        boolean raining    = weather == Weather.RAINING    || weather == Weather.THUNDERSTORM;
        boolean thundering = weather == Weather.THUNDERING || weather == Weather.THUNDERSTORM;
        return WeatherCheckLootCondition.create().raining(raining).thundering(thundering);
    }
    
    private static LootCondition.Builder randomChance(float chance) {
        return RandomChanceLootCondition.builder(chance);
    }
    
}
