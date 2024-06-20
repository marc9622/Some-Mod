package somemod.frost.data.server.loottable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import net.minecraft.data.server.loottable.LootTableGenerator;
import static net.minecraft.item.Items.*;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContext.EntityTarget;
import net.minecraft.util.Identifier;
import somemod.frost.entity.FrostEntityTypes;

import static somemod.datagen.LootTableProvider.*;

public final class FrostEntityLootTableGenerator implements LootTableGenerator {

    private static final List<Consumer<BiConsumer<Identifier, LootTable.Builder>>> exportings = new ArrayList<>();

    @Override
    public void accept(BiConsumer<Identifier, LootTable.Builder> exporter) {
        exportings.forEach(exporting -> exporting.accept(exporter));
    }


    public static final Identifier ARCTIC_ZOMBIE =
        registerEntityLootTable(FrostEntityTypes.ARCTIC_ZOMBIE,
            LootPool.builder()
                .rolls(constant(1.0f))
                .with(itemEntry(ROTTEN_FLESH, 1, uniform(0.0f, 2.0f), setLooting(uniform(0.0f, 1.0f)))),
            LootPool.builder()
                .rolls(constant(1.0f))
                .with(itemEntry(IRON_INGOT))
                .with(itemEntry(CARROT))
                .with(itemEntry(POTATO, setSmelted().conditionally(entityCheck(EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE))))
                .conditionally(killedByPlayerCheck())
                .conditionally(randomChanceWithLootingCheck(0.025f, 0.01f)),
            LootPool.builder()
                .rolls(constant(1.0f))
                .with(itemEntry(LEATHER,   10))
                .with(itemEntry(WHITE_WOOL, 1))
                .with(itemEntry(ICE,        1))
                .conditionally(killedByPlayerCheck())
                .conditionally(randomChanceWithLootingCheck(0.20f, 0.05f))
        );
}
