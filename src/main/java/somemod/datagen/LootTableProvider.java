package somemod.datagen;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;

import net.minecraft.data.server.loottable.LootTableGenerator;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.condition.KilledByPlayerLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.condition.RandomChanceWithLootingLootCondition;
import net.minecraft.loot.condition.WeatherCheckLootCondition;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.context.LootContext.EntityTarget;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.entry.LootTableEntry;
import net.minecraft.loot.function.ConditionalLootFunction;
import net.minecraft.loot.function.FurnaceSmeltLootFunction;
import net.minecraft.loot.function.LootFunction;
import net.minecraft.loot.function.LootingEnchantLootFunction;
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
import net.minecraft.predicate.entity.EntityFlagsPredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import somemod.SomeMod;
import somemod.frost.data.server.loottable.FrostChestLootTableGenerator;
import somemod.frost.data.server.loottable.FrostEntityLootTableGenerator;
import somemod.magic.data.server.loottable.MagicChestLootTableGenerator;

@SuppressWarnings("unused")
public final class LootTableProvider extends SimpleFabricLootTableProvider {

    public static final EntityPredicate.Builder NEEDS_ENTITY_ON_FIRE = EntityPredicate.Builder.create().flags(EntityFlagsPredicate.Builder.create().onFire(true).build());

    private static final Collection<Consumer<BiConsumer<Identifier, LootTable.Builder>>> exportings = new ArrayList<>();
    private static final Collection<LootTableGenerator> generators = new ArrayList<>() {{
        add(new FrostChestLootTableGenerator());
        add(new FrostEntityLootTableGenerator());
        add(new MagicChestLootTableGenerator());
    }};

    public LootTableProvider(FabricDataOutput output) {
        super(output, LootContextTypes.CHEST);
    }

    @Override
    public void accept(BiConsumer<Identifier, LootTable.Builder> exporter) {
        exportings.forEach(exporting -> exporting.accept(exporter));
    }

    public static Identifier registerChestLootTable(String path, LootPool.Builder... poolBuilders) {
        Identifier id = SomeMod.registerChestLootTable(path);
        exportings.add(exporter -> export(exporter, id, poolBuilders));
        return id;
    }

    public static Identifier registerEntityLootTable(EntityType<?> entityType, LootPool.Builder... poolBuilders) {
        Identifier id = SomeMod.registerEntityLootTable(entityType);
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

    public static LeafEntry.Builder<?> itemEntry(Item item, ConditionalLootFunction.Builder<?>... lootFunctions) {
        LeafEntry.Builder<?> builder = itemEntry(item);
        for (LootFunction.Builder lootFunction : lootFunctions)
            builder = builder.apply(lootFunction);
        return builder;
    }
    
    public static LeafEntry.Builder<?> itemEntry(Item item, List<ConditionalLootFunction.Builder<?>> lootFunctions, List<LootCondition.Builder> lootConditions) {
        LeafEntry.Builder<?> builder = itemEntry(item, lootFunctions.toArray(ConditionalLootFunction.Builder<?>[]::new));
        for (LootCondition.Builder lootCondition : lootConditions)
            builder = builder.conditionally(lootCondition);
        return builder;
    }

    public static LeafEntry.Builder<?> itemEntry(Item item, int weight, ConditionalLootFunction.Builder<?>... lootFunctions) {
        return itemEntry(item, weight).weight(weight);
    }
    
    public static LeafEntry.Builder<?> itemEntry(Item item, int weight, List<ConditionalLootFunction.Builder<?>> lootFunctions, List<LootCondition.Builder> lootConditions) {
        LeafEntry.Builder<?> builder = itemEntry(item, weight, lootFunctions.toArray(ConditionalLootFunction.Builder<?>[]::new));
        for (LootCondition.Builder lootCondition : lootConditions)
            builder = builder.conditionally(lootCondition);
        return builder;
    }

    public static LeafEntry.Builder<?> itemEntry(Item item, int weight, LootNumberProvider countRange, ConditionalLootFunction.Builder<?>... lootFunctions) {
        LeafEntry.Builder<?> builder = itemEntry(item, weight).apply(LootTableProvider.setCount(countRange));
        for (LootFunction.Builder lootFunction : lootFunctions)
            builder = builder.apply(lootFunction);
        return builder;
    }

    public static LeafEntry.Builder<?> itemEntry(Item item, int weight, LootNumberProvider countRange, List<ConditionalLootFunction.Builder<?>> lootFunctions, List<LootCondition.Builder> lootConditions) {
        LeafEntry.Builder<?> builder = itemEntry(item, weight, countRange, lootFunctions.toArray(ConditionalLootFunction.Builder<?>[]::new));
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

    public static SetPotionLootFunction.Builder<?> setPotion(Potion potion) {
        return SetPotionLootFunction.builder(potion);
    }

    public static ConditionalLootFunction.Builder<?> setSmelted() {
        return FurnaceSmeltLootFunction.builder();
    }

    public static LootingEnchantLootFunction.Builder setLooting(LootNumberProvider extraRange) {
        return LootingEnchantLootFunction.builder(extraRange);
    }

    public static SetCountLootFunction.Builder<?> setCount(LootNumberProvider countRange) {
        return SetCountLootFunction.builder(countRange);
    }

    public static UniformLootNumberProvider uniform(float min, float max) {
        return UniformLootNumberProvider.create(min, max);
    }

    public static ConstantLootNumberProvider constant(float value) {
        return ConstantLootNumberProvider.create(value);
    }
    
    public static LootCondition.Builder entityCheck(EntityTarget entity, EntityPredicate.Builder predicateBuilder) {
        return EntityPropertiesLootCondition.builder(entity, predicateBuilder);
    }

    public static LootCondition.Builder killedByPlayerCheck() {
        return KilledByPlayerLootCondition.builder();
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

    public static LootCondition.Builder randomChanceWithLootingCheck(float chance, float lootingMultiplier) {
        return RandomChanceWithLootingLootCondition.builder(chance, lootingMultiplier);
    }

    public static LootCondition.Builder randomChanceCheck(float chance) {
        return RandomChanceLootCondition.builder(chance);
    }
    
}
