package somemod.frost.data.server.loottable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static net.minecraft.enchantment.Enchantments.*;
import net.minecraft.data.server.loottable.LootTableGenerator;
import static net.minecraft.item.Items.*;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.util.Identifier;

import static somemod.datagen.ChestLootTableProvider.*;
import static somemod.frost.enchantment.FrostEnchantments.*;
import static somemod.frost.item.FrostItems.*;

public final class FrostChestLootTableGenerator implements LootTableGenerator {

    private static final List<Consumer<BiConsumer<Identifier, LootTable.Builder>>> exportings = new ArrayList<>();

    @Override
    public void accept(BiConsumer<Identifier, LootTable.Builder> exporter) {
        exportings.forEach(exporting -> exporting.accept(exporter));
    }

    //#region FROST
    private static final Identifier SPRUCE_CHEST_ARCTIC_ARMOR =
        registerLootTable("spruce_chest_arctic_armor",
            LootPool.builder()
                .rolls(constant(1))
                .conditionally(randomChance(0.15f))
                .with(itemEntry(ARCTIC_HAT,    1, constant(1), setDamage(uniform(0.1f, 0.9f)))),
            LootPool.builder()
                .rolls(constant(1))
                .conditionally(randomChance(0.15f))
                .with(itemEntry(ARCTIC_COAT, 1, constant(1), setDamage(uniform(0.1f, 0.9f)))),
            LootPool.builder()
                .rolls(constant(1))
                .conditionally(randomChance(0.15f))
                .with(itemEntry(ARCTIC_PANTS,  1, constant(1), setDamage(uniform(0.1f, 0.9f)))),
            LootPool.builder()
                .rolls(constant(1))
                .conditionally(randomChance(0.15f))
                .with(itemEntry(ARCTIC_BOOTS,  1, constant(1), setDamage(uniform(0.1f, 0.9f))))
        );

    private static final Identifier SPRUCE_CHEST_GLACIER_ARMOR =
        registerLootTable("spruce_chest_glacier_armor",
            LootPool.builder()
                .rolls(constant(1))
                .with(itemEntry(GLACIER_HELMET,     1, constant(1), setDamage(uniform(0.5f, 1.0f)), setEnchantments().enchantment(PROTECTION, uniform(0, 1))))
                .with(itemEntry(GLACIER_CHESTPLATE, 1, constant(1), setDamage(uniform(0.5f, 1.0f)), setEnchantments().enchantment(ICE_COLD, uniform(0, 1))))
                .with(itemEntry(GLACIER_LEGGINGS,   1, constant(1), setDamage(uniform(0.5f, 1.0f)), setEnchantments().enchantment(PROTECTION, uniform(0, 1))))
                .with(itemEntry(GLACIER_BOOTS,      1, constant(1), setDamage(uniform(0.5f, 1.0f)), setEnchantments().enchantment(FROST_WALKER, uniform(0, 1))))
        );

    public static final Identifier SPRUCE_CHEST_SNOWY =
        registerLootTable("spruce_chest_snowy",
            LootPool.builder()
                .rolls(constant(1))
                .with(lootTableEntry(SPRUCE_CHEST_ARCTIC_ARMOR, 2))
                .with(lootTableEntry(SPRUCE_CHEST_GLACIER_ARMOR)),
            LootPool.builder()
                .rolls(uniform(8, 12))
                .with(itemEntry(SNOWBALL, 8))
                .with(itemEntry(TORCH,    8))
                .with(itemEntry(COAL,     2))
                .with(itemEntry(STICK,    2))
                .with(itemEntry(SPRUCE_LOG,     2))
                .with(itemEntry(SPRUCE_PLANKS,  2))
                .with(itemEntry(SPRUCE_FENCE,   2))
                .with(itemEntry(CRAFTING_TABLE, 1))
                .with(itemEntry(FURNACE,        1)),
            LootPool.builder()
                .rolls(constant(1))
                .with(itemEntry(BREAD, 1, uniform(0, 3)))
                .with(itemEntry(APPLE, 1, uniform(0, 6))),
            LootPool.builder()
                .rolls(constant(1))
                .with(itemEntry(STONE_SWORD,  1, constant(1), setDamage(uniform(0.1f, 0.9f))))
                .with(itemEntry(STONE_SHOVEL, 1, constant(1), setDamage(uniform(0.1f, 0.9f))))
                .with(itemEntry(CAMPFIRE)),
            LootPool.builder()
                .rolls(constant(1))
                .conditionally(randomChance(0.5f))
                .with(itemEntry(IRON_INGOT, 1, uniform(1, 3)))
                .with(itemEntry(GOLD_INGOT, 1, uniform(1, 3))),
            LootPool.builder()
                .rolls(constant(1))
                .conditionally(randomChance(0.1f))
                .with(itemEntry(DIAMOND))
        );

    public static final Identifier SPRUCE_CHEST_TAIGA =
        registerLootTable("spruce_chest_taiga",
            LootPool.builder()
                .rolls(constant(1))
                .with(lootTableEntry(SPRUCE_CHEST_ARCTIC_ARMOR)),
            LootPool.builder()
                .rolls(uniform(8, 12))
                .with(itemEntry(TORCH,    8))
                .with(itemEntry(COAL,     2))
                .with(itemEntry(STICK,    2))
                .with(itemEntry(SPRUCE_LOG,     2))
                .with(itemEntry(SPRUCE_PLANKS,  2))
                .with(itemEntry(SPRUCE_FENCE,   2))
                .with(itemEntry(CRAFTING_TABLE, 1))
                .with(itemEntry(FURNACE,        1)),
            LootPool.builder()
                .rolls(constant(1))
                .with(itemEntry(BREAD, 1, uniform(0, 3)))
                .with(itemEntry(APPLE, 1, uniform(0, 6)))
                .with(itemEntry(SWEET_BERRIES, 4, uniform(0, 8))),
            LootPool.builder()
                .rolls(constant(1))
                .with(itemEntry(STONE_SWORD,   1, constant(1), setDamage(uniform(0.1f, 0.9f))))
                .with(itemEntry(STONE_PICKAXE, 1, constant(1), setDamage(uniform(0.1f, 0.9f))))
                .with(itemEntry(STONE_AXE,     1, constant(1), setDamage(uniform(0.1f, 0.9f))))
                .with(itemEntry(STONE_HOE,     1, constant(1), setDamage(uniform(0.1f, 0.9f))))
                .with(itemEntry(CAMPFIRE)),
            LootPool.builder()
                .rolls(constant(1))
                .conditionally(randomChance(0.5f))
                .with(itemEntry(IRON_INGOT, 1, uniform(1, 3)))
                .with(itemEntry(GOLD_INGOT, 1, uniform(1, 3)))
        );

    public static final Identifier SPRUCE_CHEST_MOUNTAIN =
        registerLootTable("spruce_chest_mountain",
            LootPool.builder()
                .rolls(constant(1))
                .with(lootTableEntry(SPRUCE_CHEST_ARCTIC_ARMOR, 2))
                .with(lootTableEntry(SPRUCE_CHEST_GLACIER_ARMOR)),
            LootPool.builder()
                .rolls(uniform(8, 12))
                .with(itemEntry(TORCH,    8))
                .with(itemEntry(COAL,     2))
                .with(itemEntry(STICK,    2))
                .with(itemEntry(SPRUCE_LOG,     2))
                .with(itemEntry(SPRUCE_PLANKS,  2))
                .with(itemEntry(SPRUCE_FENCE,   2))
                .with(itemEntry(CRAFTING_TABLE, 1))
                .with(itemEntry(FURNACE,        1)),
            LootPool.builder()
                .rolls(constant(1))
                .with(itemEntry(BREAD, 1, uniform(0, 3)))
                .with(itemEntry(APPLE, 1, uniform(0, 6))),
            LootPool.builder()
                .rolls(constant(1))
                .with(itemEntry(STONE_SWORD,   1, constant(1), setDamage(uniform(0.1f, 0.9f))))
                .with(itemEntry(STONE_PICKAXE, 1, constant(1), setDamage(uniform(0.1f, 0.9f))))
                .with(itemEntry(CAMPFIRE)),
            LootPool.builder()
                .rolls(constant(1))
                .conditionally(randomChance(0.5f))
                .with(itemEntry(IRON_INGOT, 1, uniform(1, 3)))
                .with(itemEntry(GOLD_INGOT, 1, uniform(1, 3))),
            LootPool.builder()
                .rolls(constant(1))
                .conditionally(randomChance(0.1f))
                .with(itemEntry(EMERALD))
        );

    private static final Identifier ICE_CHEST_BOW =
        registerLootTable("ice_chest_bow",
            LootPool.builder()
                .rolls(constant(1))
                .with(itemEntry(FROSTBITE_ARROW, 3, uniform(4, 8)))
                .with(itemEntry(BOW,             1, constant(1), setEnchantments().enchantment(FROZEN_QUIVER, constant(1))))
                .with(itemEntry(BOOK,            1, constant(1), setEnchantments().enchantment(FROZEN_QUIVER, constant(1)))),
            LootPool.builder()
                .with(itemEntry(ARROW,           1, uniform(6, 12)))
        );

    private static final Identifier ICE_CHEST_ARMOR =
        registerLootTable("ice_chest_armor",
            LootPool.builder()
                .rolls(constant(1))
                .with(itemEntry(FROSTBITE_CHESTPLATE, 2, constant(1), setDamage(uniform(0.2f, 1.0f)), setEnchantments().enchantment(PROTECTION, uniform(0, 2)).enchantment(UNBREAKING, uniform(0, 2)).enchantment(THORNS, uniform(0, 2))))
                .with(itemEntry(FROSTBITE_LEGGINGS,   2, constant(1), setDamage(uniform(0.2f, 1.0f)), setEnchantments().enchantment(PROTECTION, uniform(0, 2)).enchantment(UNBREAKING, uniform(0, 2)).enchantment(THORNS, uniform(0, 2)))),
            LootPool.builder()
                .rolls(uniform(2, 4))
                .with(itemEntry(IRON_INGOT,   8, uniform(1, 2)))
                .with(itemEntry(IRON_SWORD,   1, constant(1), setDamage(uniform(0.1f, 0.9f))))
                .with(itemEntry(IRON_PICKAXE, 1, constant(1), setDamage(uniform(0.1f, 0.9f))))
                .with(itemEntry(IRON_AXE,     1, constant(1), setDamage(uniform(0.1f, 0.9f))))
                .with(itemEntry(IRON_SHOVEL,  1, constant(1), setDamage(uniform(0.1f, 0.9f))))
        );

    private static final Identifier ICE_CHEST_BOOK =
        registerLootTable("ice_chest_book",
            LootPool.builder()
                .rolls(constant(1))
                .with(itemEntry(BOOK,            1, constant(1), setEnchantments().enchantment(FROZEN_QUIVER, constant(1))))
                .with(itemEntry(BOOK,            1, constant(1), setEnchantments().enchantment(ICE_COLD,      uniform(0, 3)))),
            LootPool.builder()
                .with(itemEntry(BOOK,            1, uniform(1, 3)))
                .with(itemEntry(FEATHER,         1, constant(1)))
        );

    private static final Identifier ICE_CHEST_CROWN =
        registerLootTable("ice_chest_crown",
            LootPool.builder()
                .rolls(constant(1))
                .with(itemEntry(ICE_QUEEN_CROWN, 1, constant(1), setEnchantments().enchantment(PROTECTION, uniform(2, 3)).enchantment(MENDING, constant(1)))),
            LootPool.builder()
                .rolls(constant(1))
                .with(itemEntry(SKELETON_SKULL)),
            LootPool.builder()
                .rolls(uniform(2, 4))
                .with(itemEntry(GOLD_NUGGET, 1, uniform(3, 5)))
                .with(itemEntry(GOLD_INGOT,  1, uniform(1, 2)))
                .with(itemEntry(BONE,        1, uniform(1, 3)))
        );

    public static final Identifier ICE_CHEST =
        registerLootTable("ice_chest",
            LootPool.builder()
                .rolls(uniform(4, 8))
                .with(itemEntry(SNOWBALL,   1, uniform(2, 4)))
                .with(itemEntry(ICE,        1)),
            LootPool.builder()
                .rolls(constant(1))
                .with(lootTableEntry(ICE_CHEST_BOW,   2))
                .with(lootTableEntry(ICE_CHEST_ARMOR, 2))
                .with(lootTableEntry(ICE_CHEST_BOOK,  1))
                .with(lootTableEntry(ICE_CHEST_CROWN, 1))
        );
    //#endregion

}

