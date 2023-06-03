package somemod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.MatchToolLootCondition;
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
import static somemod.crystal.block.CrystalBlocks.*;
import static somemod.crystal.item.CrystalItems.*;
import static somemod.magic.block.MagicBlocks.*;

import java.util.function.UnaryOperator;

public class BlockLootTableProvider extends FabricBlockLootTableProvider {

    public BlockLootTableProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate() {
        this.addGlowStoneLikeDrop(CRYSTAL_GLASS, CRYSTAL_DUST);
        this.addDropItself(CRYSTAL_BLOCK);
        
        this.addOreSingleWithItem(CITRINE_ORE,  CITRINE);
        this.addOreSingleWithItem(RUBY_ORE,     RUBY);
        this.addOreSingleWithItem(SAPPHIRE_ORE, SAPPHIRE);

        this.addBookshelfLikeDrop(ENCHANTED_BOOKSHELF, Items.GOLD_INGOT,
            builder -> builder.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 4.0f))));
        this.addBookshelfLikeDrop(OBSIDIAN_ENCHANTED_BOOKSHELF, Items.OBSIDIAN);
    }

    protected static final LootCondition.Builder WITH_SILK_TOUCH = MatchToolLootCondition.builder(ItemPredicate.Builder.create().enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, NumberRange.IntRange.atLeast(1))));

    private void addDrop(Block block, LootPool.Builder... lootPoolBuilders) {
        LootTable.Builder builder = LootTable.builder();
        for (LootPool.Builder lootPoolBuilder : lootPoolBuilders)
            builder.pool(lootPoolBuilder);
        super.addDrop(block, builder);
    }

    private void addDropItself(Block block) {
        super.addDrop(block);
    }

    private void addSilkTouchAndElse(Block block, LootPoolEntry.Builder<?> elseLootTableBuilder) {
        super.addDrop(block, BlockLootTableGenerator.dropsWithSilkTouch(block, elseLootTableBuilder));
    }

    private void addOreSingleWithItem(Block block, ItemConvertible item) {
        this.addSilkTouchAndElse(block,
            ItemEntry.builder(item)
            .apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))
            .apply(ExplosionDecayLootFunction.builder()));
    }

    private void addGlowStoneLikeDrop(Block block, ItemConvertible item) {
        this.addSilkTouchAndElse(block,
            ItemEntry.builder(item)
            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 4.0f)))
            .apply(ApplyBonusLootFunction.uniformBonusCount(Enchantments.FORTUNE))
            .apply(LimitCountLootFunction.builder(BoundedIntUnaryOperator.create(1, 4)))
            .apply(ExplosionDecayLootFunction.builder())
        );
    }

    public void addBookshelfLikeDrop(Block block, ItemConvertible otherMaterial) {
        this.addBookshelfLikeDrop(block, otherMaterial, UnaryOperator.identity());
    }

    public void addBookshelfLikeDrop(Block block, ItemConvertible otherMaterial, UnaryOperator<LootPool.Builder> otherLootPoolAdjustments) {
        this.addDrop(block,
            LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1.0f))
                .with(ItemEntry.builder(block))
                .conditionally(WITH_SILK_TOUCH),
            LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(3.0f))
                .with(ItemEntry.builder(Items.BOOK))
                .conditionally(WITHOUT_SILK_TOUCH),
            otherLootPoolAdjustments.apply(
                LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(otherMaterial))
                    .conditionally(WITHOUT_SILK_TOUCH)
            )
        );
    }
    
}
