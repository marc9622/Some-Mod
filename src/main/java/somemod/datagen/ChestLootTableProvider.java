package somemod.datagen;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import static net.minecraft.enchantment.Enchantments.*;

import net.minecraft.data.server.loottable.LootTableGenerator;
import net.minecraft.item.Item;
import static net.minecraft.item.Items.*;
import net.minecraft.item.ItemConvertible;
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
import somemod.frost.data.server.loottable.FrostChestLootTableGenerator;

import static somemod.magic.item.MagicItems.*;
import somemod.magic.potion.MagicPotions;

@SuppressWarnings("unused")
public final class ChestLootTableProvider extends SimpleFabricLootTableProvider {

    private static final Collection<Consumer<BiConsumer<Identifier, LootTable.Builder>>> exportings = new ArrayList<>();
    private static final Collection<LootTableGenerator> generators = new ArrayList<>() {{
        add(new FrostChestLootTableGenerator());
    }};

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
        itemEntry(PAPER, 1, constant(1), setName(Text.of("Letter")), setLore(
            Text.of("Dear Aerendy, it has been too long"),
            Text.of("since I last saw you. I miss you"),
            Text.of("so very much. I am planning to come"),
            Text.of("visit you very soon. - Elaith")));

    private static final LeafEntry.Builder<?> ELVEN_LORE_2 =
        itemEntry(PAPER, 1, constant(1), setName(Text.of("Note")), setLore(
            Text.of("I will be going on a small journey"),
            Text.of("not too far east. Do not worry; I will"),
            Text.of("be back before you know it. - Emryth")));

    private static final LeafEntry.Builder<?> ELVEN_LORE_3 =
        itemEntry(PAPER, 1, constant(1), setName(Text.of("Note")), setLore(
            Text.of("THEY ARE COMING FROM THE EAST"),
            Text.of("WARN THE OTHERS AND PREPARE YOURSELVES")));

    private static final LeafEntry.Builder<?> ELVEN_LORE_4 =
        itemEntry(PAPER, 1, constant(1), setName(Text.of("Note")), setLore(
            Text.of("To Artin and Isarrel,"),
            Text.of("I have written a letter to"),
            Text.of("Elnaril and Ashryn, informing"),
            Text.of("them about the attack."),
            Text.of("We must flee west. - Aerendy")));

    private static final LeafEntry.Builder<?> ELVEN_LORE_5 =
        itemEntry(PAPER, 1, constant(1), setName(Text.of("Note")), setLore(
            Text.of("Isarrel, hopefully you will see this."),
            Text.of("Aerendy and I have been looking for him,"),
            Text.of("but he is nowhere to be found."),
            Text.of("So we have decided to travel"),
            Text.of("west tomorrow at noon, but by the"),
            Text.of("time you read this, we have"),
            Text.of("probably already left. - Artin")));

    private static final LeafEntry.Builder<?> ELVEN_LORE_6 =
        itemEntry(PAPER, 1, constant(1), setName(Text.of("Letter")), setLore(
            Text.of("Elaith, my dear. If you find this,"),
            Text.of("please do not go towards the village."),
            Text.of("I have travelled west with the others."),
            Text.of("I hope you are safe.")));

    private static final LeafEntry.Builder<?> ELVEN_LORE_7 =
        itemEntry(PAPER, 1, constant(1), setName(Text.of("Letter")), setLore(
            Text.of("Aerendy Naereiros... I am sorry"),
            Text.of("to inform you... but I found"),
            Text.of("Elaith dead near the village..."),
            Text.of("I am so so sorry..."),
            Text.of(" - Aleva Wrangella")));

    private static final LeafEntry.Builder<?> ELVEN_HELMET_ELNARIL =
        itemEntry(ELVEN_HELMET, 1, constant(1), setDamage(uniform(0.5f, 0.95f)), setLore(ELVEN_LORE_ELNARIL), setEnchantments()
            .enchantment(PROTECTION, uniform(2, 3))
            .enchantment(UNBREAKING, constant(1)));

    private static final LeafEntry.Builder<?> ELVEN_HELMET_ASHRYN =
        itemEntry(ELVEN_HELMET, 1, constant(1), setDamage(uniform(0.25f, 0.95f)), setLore(ELVEN_LORE_ASHRYN), setEnchantments()
            .enchantment(PROTECTION, uniform(1, 2))
            .enchantment(MENDING, constant(1)));

    private static final LeafEntry.Builder<?> ELVEN_CHESTPLATE_EMRYTH =
        itemEntry(ELVEN_CHESTPLATE, 1, constant(1), setDamage(uniform(0.1f, 0.5f)), setLore(ELVEN_LORE_EMRYTH), setEnchantments()
            .enchantment(PROTECTION, uniform(0, 3))
            .enchantment(MENDING, constant(1)));

    private static final LeafEntry.Builder<?> ELVEN_CHESTPLATE_AERENDY =
        itemEntry(ELVEN_CHESTPLATE, 1, constant(1), setDamage(uniform(0.8f, 0.95f)), setLore(ELVEN_LORE_AERENDY), setEnchantments()
            .enchantment(PROTECTION, uniform(1, 2))
            .enchantment(THORNS, uniform(0, 1))
            .enchantment(UNBREAKING, uniform(0, 1)));

    private static final LeafEntry.Builder<?> ELVEN_SWORD_ELAITH =
        itemEntry(IRON_SWORD, 1, constant(1), setDamage(uniform(0.2f, 0.6f)), setLore(ELVEN_LORE_ELAITH), setEnchantments()
            .enchantment(SMITE, uniform(1, 2))
            .enchantment(UNBREAKING, constant(1)));

    private static final LeafEntry.Builder<?> ELVEN_LEGGINGS_ARTIN =
        itemEntry(ELVEN_LEGGINGS, 1, constant(1), setDamage(uniform(0.1f, 0.95f)), setLore(ELVEN_LORE_ARTIN), setEnchantments()
            .enchantment(FEATHER_FALLING, uniform(0, 1))
            .enchantment(PROTECTION, uniform(1, 2))
            .enchantment(UNBREAKING, uniform(0, 2)));

    private static final LeafEntry.Builder<?> ELVEN_BOOTS_ISARREL =
        itemEntry(ELVEN_BOOTS, 1, constant(1), setDamage(uniform(0.5f, 0.95f)), setLore(ELVEN_LORE_ISARREL), setEnchantments()
            .enchantment(DEPTH_STRIDER, constant(1))
            .enchantment(PROTECTION, uniform(0, 1)));

    private static final LeafEntry.Builder<?> ELVEN_BOW_ALEVA =
        itemEntry(BOW, 1, constant(1), setDamage(uniform(0.15f, 0.95f)), setLore(ELVEN_LORE_ALEVA), setEnchantments()
            .enchantment(POWER, uniform(0, 2))
            .enchantment(PUNCH, uniform(0, 1))
            .enchantment(INFINITY, uniform(0, 1))
            .enchantment(MENDING, uniform(0, 1)));

    private static final LeafEntry.Builder<?> ELVEN_HELMET_BASIC     = itemEntry(ELVEN_HELMET,     2, constant(1), setDamage(uniform(0.1f, 0.95f)));
    private static final LeafEntry.Builder<?> ELVEN_CHESTPLATE_BASIC = itemEntry(ELVEN_CHESTPLATE, 2, constant(1), setDamage(uniform(0.1f, 0.95f)));
    private static final LeafEntry.Builder<?> ELVEN_LEGGINGS_BASIC   = itemEntry(ELVEN_LEGGINGS,   2, constant(1), setDamage(uniform(0.1f, 0.95f)));
    private static final LeafEntry.Builder<?> ELVEN_BOOTS_BASIC      = itemEntry(ELVEN_BOOTS,      2, constant(1), setDamage(uniform(0.1f, 0.95f)));
    private static final LeafEntry.Builder<?> ELVEN_SWORD_BASIC      = itemEntry(IRON_SWORD, 2, constant(1), setDamage(uniform(0.1f, 0.95f)));
    private static final LeafEntry.Builder<?> ELVEN_BOW_BASIC        = itemEntry(BOW,        2, constant(1), setDamage(uniform(0.1f, 0.95f)));

    private static final Identifier ELVEN_CHEST_WRITING =
        registerLootTable("elven_chest_writing",
            LootPool.builder()
                .rolls(uniform(2, 3))
                .with(itemEntry(BOOK,       4, uniform(1, 3)))
                .with(itemEntry(PAPER,     10, uniform(2, 4)))
                .with(itemEntry(FEATHER,    6, uniform(2, 4)))
                .with(itemEntry(INK_SAC,    5, constant(1)))
                .with(itemEntry(BLACK_DYE,  2, uniform(1, 3)))
                .with(itemEntry(GREEN_DYE,  1, uniform(1, 2)))
                .with(itemEntry(PURPLE_DYE, 1, uniform(1, 2)))
                .with(itemEntry(YELLOW_DYE, 1, uniform(1, 2)))
                .with(itemEntry(STRING,     3, uniform(2, 4)))
                .with(itemEntry(NAME_TAG,   1))
        );

    private static final Identifier ELVEN_CHEST_FOOD =
        registerLootTable("elven_chest_food",
            LootPool.builder()
                .rolls(uniform(2, 3))
                .with(itemEntry(WHEAT,       3, uniform(2, 4)))
                .with(itemEntry(WHEAT_SEEDS, 1, uniform(2, 4)))
                .with(itemEntry(BREAD,       2, uniform(1, 3)))
                .with(itemEntry(APPLE,       4, uniform(2, 4)))
                .with(itemEntry(SWEET_BERRIES, 4, uniform(2, 4)))
                .with(itemEntry(SUGAR,       1, uniform(2, 4)))
                .with(itemEntry(SUGAR_CANE,  1, uniform(2, 4)))
                .with(itemEntry(STRING,      2, uniform(2, 4)))
                .with(itemEntry(NAME_TAG,    1))
        );

    private static final Identifier ELVEN_CHEST_COMBAT =
        registerLootTable("elven_chest_combat",
            LootPool.builder()
                .rolls(uniform(2, 3))
                .with(itemEntry(ARROW,  5, uniform(2, 4)))
                .with(itemEntry(STICK,  4, uniform(2, 4)))
                .with(itemEntry(FLINT,  3, uniform(2, 4)))
                .with(itemEntry(APPLE,  1, uniform(1, 3)))
                .with(itemEntry(POTION, 1, constant(1), setPotion(Potions.HEALING)))
                .with(itemEntry(POTION, 1, constant(1), setPotion(Potions.REGENERATION)))
                .with(itemEntry(POTION, 1, constant(1), setPotion(Potions.INVISIBILITY)))
                .with(itemEntry(WOODEN_SWORD, 1, constant(1), setDamage(uniform(0.2f, 0.8f))))
                .with(itemEntry(STONE_SWORD,  1, constant(1), setDamage(uniform(0.2f, 0.8f))))
                .with(itemEntry(WOODEN_AXE,   1, constant(1), setDamage(uniform(0.2f, 0.8f))))
                .with(itemEntry(STONE_AXE,    1, constant(1), setDamage(uniform(0.2f, 0.8f))))
                .with(itemEntry(BOW,          2, constant(1), setDamage(uniform(0.2f, 0.8f))))
        );

    private static final Identifier ELVEN_CHEST_MAGIC =
        registerLootTable("elven_chest_magic",
            LootPool.builder()
                .rolls(constant(2))
                .with(itemEntry(GLASS_BOTTLE, 8, uniform(1, 2)))
                .with(itemEntry(POTION, 2, uniform(1, 2), setPotion(Potions.AWKWARD)))
                .with(itemEntry(POTION, 2, uniform(1, 2), setPotion(Potions.MUNDANE)))
                .with(itemEntry(POTION, 2, uniform(1, 2), setPotion(Potions.THICK)))
                .with(itemEntry(BOOK,   4, uniform(2, 4))),
            LootPool.builder()
                .rolls(constant(1))
                .with(itemEntry(ENDER_PEARL,  8, uniform(1, 3)))
                .with(itemEntry(SPIDER_EYE,  10, uniform(1, 3)))
                .with(itemEntry(FERMENTED_SPIDER_EYE, 5, constant(1)))
                .with(itemEntry(ROTTEN_FLESH, 5, uniform(1, 3)))
                .with(itemEntry(SLIME_BALL,  10, uniform(2, 4)))
                .with(itemEntry(SUGAR,        5, uniform(1, 3)))
                .with(itemEntry(GOLDEN_APPLE, 2, constant(1)))
                .with(itemEntry(GLISTERING_MELON_SLICE, 2, constant(1)))
                .with(itemEntry(EXPERIENCE_BOTTLE, 1, uniform(1, 2))),
            LootPool.builder()
                .rolls(constant(1))
                .with(itemEntry(POTION,         4, constant(1), setPotion(MagicPotions.AGILITY)))
                .with(itemEntry(SPLASH_POTION,  4, constant(1), setPotion(MagicPotions.AGILITY)))
                .with(itemEntry(POTION,         3, constant(1), setPotion(Potions.SWIFTNESS)))
                .with(itemEntry(SPLASH_POTION,  3, constant(1), setPotion(Potions.SWIFTNESS)))
                .with(itemEntry(POTION,         3, constant(1), setPotion(MagicPotions.FERAL_ENDURANCE)))
                .with(itemEntry(SPLASH_POTION,  3, constant(1), setPotion(MagicPotions.FERAL_ENDURANCE)))
                .with(itemEntry(POTION,         2, constant(1), setPotion(MagicPotions.MAGIC_RESILIENCE)))
                .with(itemEntry(SPLASH_POTION,  2, constant(1), setPotion(MagicPotions.MAGIC_RESILIENCE)))
                .with(itemEntry(POTION,         1, constant(1), setPotion(Potions.LUCK)))
                .with(itemEntry(ENCHANTED_BOOK, 1, constant(1), setEnchantments().enchantment(MENDING, constant(1))))
                .with(itemEntry(ENCHANTED_BOOK, 2, constant(1), setEnchantments().enchantment(PROTECTION, uniform(1, 2))))
                .with(itemEntry(ENCHANTED_BOOK, 2, constant(1), setEnchantments().enchantment(UNBREAKING, uniform(1, 2))))
                .with(itemEntry(ENCHANTED_BOOK, 2, constant(1), setEnchantments().enchantment(SMITE, constant(1))))
                .with(itemEntry(ENCHANTED_BOOK, 2, constant(1), setEnchantments().enchantment(INFINITY, constant(1))))
        );

    private static final Identifier ELVEN_CHEST_DARK_OAK =
        registerLootTable("elven_chest_building_dark_oak",
            LootPool.builder()
                .rolls(constant(1))
                .with(itemEntry(DARK_OAK_LOG,    4, uniform(1, 3)))
                .with(itemEntry(DARK_OAK_PLANKS, 5, uniform(2, 4)))
                .with(itemEntry(DARK_OAK_FENCE,  2, uniform(2, 4)))
        );

    private static final Identifier ELVEN_CHEST_OAK =
        registerLootTable("elven_chest_building_oak",
            LootPool.builder()
                .rolls(constant(1))
                .with(itemEntry(OAK_LOG,    4, uniform(1, 3)))
                .with(itemEntry(OAK_PLANKS, 5, uniform(2, 4)))
                .with(itemEntry(OAK_FENCE,  2, uniform(2, 4)))
        );

    private static final Identifier ELVEN_CHEST_BIRCH =
        registerLootTable("elven_chest_building_birch",
            LootPool.builder()
                .rolls(constant(1))
                .with(itemEntry(BIRCH_LOG,    4, uniform(1, 3)))
                .with(itemEntry(BIRCH_PLANKS, 5, uniform(2, 4)))
                .with(itemEntry(BIRCH_FENCE,  2, uniform(2, 4)))
        );

    private static final Identifier ELVEN_CHEST_BUILDING =
        registerLootTable("elven_chest_building",
            LootPool.builder()
                .rolls(constant(1))
                .conditionally(randomChance(0.5f))
                .with(itemEntry(CHEST))
                .with(itemEntry(CRAFTING_TABLE))
                .with(itemEntry(CAMPFIRE))
                .with(itemEntry(WOODEN_PICKAXE, 1, constant(1), setDamage(uniform(0.2f, 0.8f))))
                .with(itemEntry(WOODEN_AXE,     1, constant(1), setDamage(uniform(0.2f, 0.8f))))
                .with(itemEntry(WOODEN_SHOVEL,  1, constant(1), setDamage(uniform(0.2f, 0.8f))))
                .with(itemEntry(WOODEN_HOE,     1, constant(1), setDamage(uniform(0.2f, 0.8f)))),
            LootPool.builder()
                .rolls(constant(2))
                .with(LootTableEntry.builder(ELVEN_CHEST_DARK_OAK))
                .with(LootTableEntry.builder(ELVEN_CHEST_OAK))
                .with(LootTableEntry.builder(ELVEN_CHEST_BIRCH)),
            LootPool.builder()
                .rolls(constant(2))
                .with(itemEntry(STICK,       5, uniform(2, 6)))
                .with(itemEntry(COBBLESTONE, 1, uniform(2, 4)))
                .with(itemEntry(STRING,      3, uniform(2, 4)))
                .with(itemEntry(FLINT,       3, uniform(1, 2)))
        );

    private static final Identifier ELVEN_CHEST_VALUABLES =
        registerLootTable("elven_chest_valuables",
            LootPool.builder()
                .rolls(constant(1))
                .with(itemEntry(EXPERIENCE_BOTTLE, 1, uniform(1, 2)))
                .with(itemEntry(EMERALD, 1, constant(1)))
                .with(itemEntry(DIAMOND, 1, constant(1)))
                .with(itemEntry(GOLD_NUGGET, 3, uniform(9, 18)))
                .with(itemEntry(LEATHER_HORSE_ARMOR, 3, constant(1)))
                .with(itemEntry(IRON_HORSE_ARMOR,    2, constant(1)))
                .with(itemEntry(GOLDEN_HORSE_ARMOR,  2, constant(1)))
                .with(itemEntry(COMPASS, 1, constant(1)))
                .with(itemEntry(CLOCK,   1, constant(1)))
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

    public static Identifier registerLootTable(String path, LootPool.Builder... poolBuilders) {
        Identifier id = SomeMod.registerLootTable(path);
        exportings.add(exporter -> export(exporter, id, poolBuilders));
        return id;
    }

    public static void export(BiConsumer<Identifier, LootTable.Builder> exporter, Identifier id, LootPool.Builder... poolBuilders) {
        LootTable.Builder builder = LootTable.builder();
        for (LootPool.Builder poolBuilder : poolBuilders)
            builder = builder.pool(poolBuilder);
        exporter.accept(id, builder);
    }
    
    public static LeafEntry.Builder<?> itemEntry(ItemConvertible item) {
        return ItemEntry.builder(item);
    }

    public static LeafEntry.Builder<?> itemEntry(Item item, int weight) {
        return itemEntry(item).weight(weight);
    }

    public static LeafEntry.Builder<?> itemEntry(Item item, int weight, LootNumberProvider countRange) {
        return itemEntry(item, weight).apply(ChestLootTableProvider.setCount(countRange));
    }

    public static LeafEntry.Builder<?> itemEntry(Item item, int weight, LootNumberProvider countRange, ConditionalLootFunction.Builder<?>... lootFunctions) {
        LeafEntry.Builder<?> builder = itemEntry(item, weight, countRange);
        for (LootFunction.Builder lootFunction : lootFunctions)
            builder = builder.apply(lootFunction);
        return builder;
    }
    
    public static LeafEntry.Builder<?> itemEntry(Item item, int weight, LootNumberProvider countRange, List<ConditionalLootFunction.Builder<?>> lootFunctions, List<LootCondition.Builder> lootConditions) {
        LeafEntry.Builder<?> builder = itemEntry(item, weight, countRange);
        for (LootFunction.Builder lootFunction : lootFunctions)
            builder = builder.apply(lootFunction);
        for (LootCondition.Builder lootCondition : lootConditions)
            builder = builder.conditionally(lootCondition);
        return builder;
    }

    public static LootTableEntry.Builder<?> lootTableEntry(Identifier id) {
        return LootTableEntry.builder(id);
    }

    public static LootTableEntry.Builder<?> lootTableEntry(Identifier id, int weight) {
        return LootTableEntry.builder(id).weight(weight);
    }

    public static SetNameLootFunction.Builder<?> setName(Text name) {
        return SetNameLootFunction.builder(name);
    }

    public static SetLoreLootFunction.Builder setLore(Text... lore) {
        SetLoreLootFunction.Builder builder = SetLoreLootFunction.builder();
        for (Text line : lore)
            builder = builder.lore(line);
        return builder;
    }

    public static SetDamageLootFunction.Builder<?> setDamage(LootNumberProvider damageRange) {
        return SetDamageLootFunction.builder(damageRange);
    }

    public static SetEnchantmentsLootFunction.Builder setEnchantments() {
        return new SetEnchantmentsLootFunction.Builder();
    }

    public static ConditionalLootFunction.Builder<?> setPotion(Potion potion) {
        return SetPotionLootFunction.builder(potion);
    }

    public static ConditionalLootFunction.Builder<?> setCount(LootNumberProvider countRange) {
        return SetCountLootFunction.builder(countRange);
    }

    public static UniformLootNumberProvider uniform(float min, float max) {
        return UniformLootNumberProvider.create(min, max);
    }

    public static ConstantLootNumberProvider constant(float value) {
        return ConstantLootNumberProvider.create(value);
    }
    
    public static enum Weather {
        SUNNY,
        RAINING,
        THUNDERING,
        THUNDERSTORM
    }

    public static LootCondition.Builder weatherCheck(Weather weather) {
        boolean raining    = weather == Weather.RAINING    || weather == Weather.THUNDERSTORM;
        boolean thundering = weather == Weather.THUNDERING || weather == Weather.THUNDERSTORM;
        return WeatherCheckLootCondition.create().raining(raining).thundering(thundering);
    }
    
    public static LootCondition.Builder randomChance(float chance) {
        return RandomChanceLootCondition.builder(chance);
    }
    
}
