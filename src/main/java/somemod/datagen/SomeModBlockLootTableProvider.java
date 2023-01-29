package somemod.datagen;

import java.util.function.BiConsumer;
import java.util.function.Function;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.condition.SurvivesExplosionLootCondition;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.ExplosionDecayLootFunction;
import net.minecraft.loot.function.LimitCountLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.operator.BoundedIntUnaryOperator;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.util.Identifier;
import somemod.SomeMod;
import static somemod.crystal.block.CrystalBlocks.*;
import static somemod.crystal.item.CrystalItems.*;
import static somemod.enchanting.block.EnchantingBlocks.*;

public class SomeModBlockLootTableProvider extends SimpleFabricLootTableProvider {

    public SomeModBlockLootTableProvider(FabricDataOutput output) {
        super(output, LootContextTypes.BLOCK);
    }

    @Override
    public void accept(BiConsumer<Identifier, LootTable.Builder> exporter) {
       
        addDropGlowStone(exporter, CRYSTAL_GLASS, CRYSTAL_DUST);
        addDropItself(exporter, CRYSTAL_BLOCK);
        
        addDropOre(exporter, CITRINE_ORE,  CITRINE);
        addDropOre(exporter, RUBY_ORE,     RUBY);
        addDropOre(exporter, SAPPHIRE_ORE, SAPPHIRE);

        addDrop(exporter, ENCHANTED_BOOKSHELF,
            LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1.0f))
                .with(ItemEntry.builder(ENCHANTED_BOOKSHELF)
                .conditionally(WITH_SILK_TOUCH)),
            LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(3.0f))
                .with(ItemEntry.builder(Items.BOOK))
                .conditionally(WITH_SILK_TOUCH.invert()),
            LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1.0f))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 4.0f)))
                .with(ItemEntry.builder(Items.GOLD_INGOT))
                .conditionally(WITH_SILK_TOUCH.invert())
        );

        addDrop(exporter, OBSIDIAN_ENCHANTED_BOOKSHELF,
            LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1.0f))
                .with(ItemEntry.builder(OBSIDIAN_ENCHANTED_BOOKSHELF)
                .conditionally(WITH_SILK_TOUCH)),
            LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(3.0f))
                .with(ItemEntry.builder(Items.BOOK))
                .conditionally(WITH_SILK_TOUCH.invert()),
            LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1.0f))
                .with(ItemEntry.builder(Items.OBSIDIAN))
                .conditionally(WITH_SILK_TOUCH.invert())
        );

    }

    protected static final LootCondition.Builder WITH_SILK_TOUCH = MatchToolLootCondition.builder(ItemPredicate.Builder.create().enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, NumberRange.IntRange.atLeast(1))));

    private void addDrop(BiConsumer<Identifier, LootTable.Builder> exporter, Block block, LootTable.Builder lootTableBuilder) {
        Identifier id = block.getLootTableId();
        SomeMod.logLootTableRegistration(id.getPath());
        exporter.accept(id, lootTableBuilder);
    }

    private void addDrop(BiConsumer<Identifier, LootTable.Builder> exporter, Block block, LootPool.Builder... lootPoolBuilders) {
        LootTable.Builder builder = LootTable.builder();
        for (LootPool.Builder lootPoolBuilder : lootPoolBuilders)
            builder.pool(lootPoolBuilder);
        addDrop(exporter, block, builder);
    }

    @SuppressWarnings("unused")
    private void addDrop(BiConsumer<Identifier, LootTable.Builder> exporter, Block block, Function<Block, LootTable.Builder> lootTableBuilder) {
        this.addDrop(exporter, block, lootTableBuilder.apply(block));
    }

    private void addDropItself(BiConsumer<Identifier, LootTable.Builder> exporter, Block block) {
        addDrop(exporter, block, LootTable.builder().pool(
            LootPool.builder()
            .rolls(ConstantLootNumberProvider.create(1.0f))
            .with(ItemEntry.builder(block))
            .conditionally(SurvivesExplosionLootCondition.builder())
        ));
    }

    private void addDropSilkTouch(BiConsumer<Identifier, LootTable.Builder> exporter, Block block, LootPoolEntry.Builder<?> elseLootTableBuilder) {
        addDrop(exporter, block, BlockLootTableGenerator.dropsWithSilkTouch(block, elseLootTableBuilder));
    }

    private void addDropOre(BiConsumer<Identifier, LootTable.Builder> exporter, Block block, Item item) {
        addDropSilkTouch(exporter, block,
            ItemEntry.builder(item)
            .apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))
            .apply(ExplosionDecayLootFunction.builder()));
    }

    private void addDropGlowStone(BiConsumer<Identifier, LootTable.Builder> exporter, Block block, Item item) {
        addDropSilkTouch(exporter, block,
            ItemEntry.builder(item)
            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 4.0f)))
            .apply(ApplyBonusLootFunction.uniformBonusCount(Enchantments.FORTUNE))
            .apply(LimitCountLootFunction.builder(BoundedIntUnaryOperator.create(1, 4)))
            .apply(ExplosionDecayLootFunction.builder())
        );
    }
    
}
