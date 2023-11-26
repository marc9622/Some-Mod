package somemod;

import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntry.Reference;
import net.minecraft.structure.StructureSet;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePool.Projection;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.chunk.placement.StructurePlacement;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.structure.Structure;
import somemod.crystal.block.CrystalBlocks;
import somemod.crystal.item.CrystalItems;
import somemod.crystal.world_gen.CrystalBiomeModifications;
import somemod.crystal.world_gen.feature.CrystalFeatures;
import somemod.datagen.LanguageProviders;
import somemod.frost.block.FrostBlocks;
import somemod.frost.block.entity.FrostBlockEntityTypes;
import somemod.frost.block.entity.render.FrostBlockEntityRendererFactories;
import somemod.frost.enchantment.FrostEnchantments;
import somemod.frost.entity.effect.FrostStatusEffects;
import somemod.frost.item.FrostItems;
import somemod.frost.potion.FrostPotions;
import somemod.frost.village.FrostTradeOffers;
import somemod.frost.world_gen.FrostBiomeModifications;
import somemod.frost.world_gen.feature.FrostFeatures;
import somemod.magic.block.MagicBlocks;
import somemod.magic.block.entity.MagicBlockEntityTypes;
import somemod.magic.block.entity.render.MagicBlockEntityRendererFactories;
import somemod.magic.enchantment.MagicEnchantments;
import somemod.magic.entity.effect.MagicStatusEffects;
import somemod.magic.item.MagicItems;
import somemod.magic.potion.MagicPotions;
import somemod.magic.screen.MagicScreenHandlers;
import somemod.magic.village.MagicTradeOffers;
import somemod.magic.world_gen.MagicBiomeModifications;
import somemod.magic.world_gen.feature.MagicFeatures;
import somemod.magic.world_gen.structure.MagicStructurePieceTypes;
import somemod.magic.world_gen.structure.MagicStructureTypes;
import somemod.magic.world_gen.structure.processor.MagicStructureProcessorTypes;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;

public final class SomeMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("somemod");

	@Override
	public void onInitialize() {
		notifyFabric();

		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		// LOGGER.info("Hello Fabric world!");

		CrystalBiomeModifications.addCrystalModifications();
		MagicBiomeModifications.addMagicModifications();
        MagicTradeOffers.registerTradeOffers();
        FrostBiomeModifications.addFrostModifications();
        FrostTradeOffers.registerTradeOffers();
	}

	/**
	 * If a class is not referenced anywhere, it might not be loaded.
	 * This method does nothing, but it makes sure that the classes are loaded.
	 */
	private static void notifyFabric() {
		CrystalBlocks.notifyFabric();
		CrystalItems.notifyFabric();
		CrystalFeatures.notifyFabric();
		
		MagicBlocks.notifyFabric();
		MagicBlockEntityTypes.notifyFabric();
		MagicBlockEntityRendererFactories.notifyFabric();
		MagicItems.notifyFabric();
		MagicScreenHandlers.notifyFabric();
		MagicFeatures.notifyFabric();
		MagicStructureTypes.notifyFabric();
		MagicStructurePieceTypes.notifyFabric();
		MagicStructureProcessorTypes.notifyFabric();
		MagicEnchantments.notifyFabric();
		MagicStatusEffects.notifyFabric();
		MagicPotions.notifyFabric();
		
        FrostBlocks.notifyFabric();
        FrostBlockEntityTypes.notifyFabric();
        FrostBlockEntityRendererFactories.notifyFabric();
		FrostItems.notifyFabric();
        FrostFeatures.notifyFabric();
        FrostEnchantments.notifyFabric();
        FrostStatusEffects.notifyFabric();
        FrostPotions.notifyFabric();
	}

    public static void logInfo(String string, Object... objects) { // info is for general information
        LOGGER.info(string.formatted(objects));
    }

    public static void logWarn(String string, Object... objects) { // warn is for information that might indicate a problem
        LOGGER.warn(string.formatted(objects));
    }

    public static void logError(String string, Object... objects) { // error is for when something goes wrong
        LOGGER.error(string.formatted(objects));
    }

    public static void logDebug(String string, Object... objects) { // debug is ment for information that is only useful for debugging
        LOGGER.debug(string.formatted(objects));
    }

    public static void logTrace(String string, Object... objects) { // trace is for very detailed information that is only useful for debugging
        LOGGER.trace(string.formatted(objects));
    }

	public static void logKeyRegistration(String path, String registry) {
		LOGGER.info("| Registering Key | " + registry + "\t <- \t" + path);
	}
	
	public static <R> RegistryKey<R> keyOf(RegistryKey<? extends Registry<R>> registry, String path) {
		logKeyRegistration(path, registry.getValue().toString());
		return RegistryKey.of(registry, id(path));
	}
	
	public static void logRegistration(String path, String registry) {
		LOGGER.info("| Registering Key | " + registry + "\t <- \t" + path);
	}

	public static <R, E extends R> E register(Registry<R> registry, String path, E entry) {
		logRegistration(path, registry.getKey().getValue().toString());
        return Registry.register(registry, id(path), entry);
    }

	public static <R, T extends R> Reference<R> register(Registerable<R> registerable, RegistryKey<R> key, T entry) {
		logRegistration(key.getValue().toString(), key.getRegistry().getPath().toString());
		return registerable.register(key, entry);
	}

    public static <I extends Item> I registerItem(String path, I item) {
        LanguageProviders.English.addItem(item, pathToEnglish(path));
        return register(Registries.ITEM, path, item);
    }

    public static <B extends Block> B registerBlock(String path, B block) {
        LanguageProviders.English.addBlock(block, pathToEnglish(path));
        return register(Registries.BLOCK, path, block);
    }

    public static <E extends Enchantment> E registerEnchantment(String path, E enchantment) {
        LanguageProviders.English.addEnchantment(enchantment, pathToEnglish(path));
        return register(Registries.ENCHANTMENT, path, enchantment);
    }

    public static <S extends StatusEffect> S registerStatusEffect(String path, S statusEffect) {
        LanguageProviders.English.addStatusEffect(statusEffect, pathToEnglish(path));
        return register(Registries.STATUS_EFFECT, path, statusEffect);
    }

    public static Potion registerPotionBase(String path, Potion potion) {
        LanguageProviders.English.addPotion(potion, pathToEnglish(path));
        return register(Registries.POTION, path, potion);
    }

    public static Potion registerPotion(String path, Potion potion) {
        return register(Registries.POTION, path, potion);
    }

    public static <E extends EntityType<?>> E registerEntityType(String path, E entityType) {
        LanguageProviders.English.addEntityType(entityType, pathToEnglish(path));
        return register(Registries.ENTITY_TYPE, path, entityType);
    }

    public static <E extends EntityAttribute> E registerEntityAttribute(String path, E entityAttribute) {
        LanguageProviders.English.addEntityAttribute(entityAttribute, pathToEnglish(path));
        return register(Registries.ATTRIBUTE, path, entityAttribute);
    }

    public static RegistryKey<ItemGroup> registerItemGroup(String path, RegistryKey<ItemGroup> itemGroup) {
        LanguageProviders.English.addItemGroup(itemGroup, pathToEnglish(path));
        return itemGroup;
    }

	public static <FC extends FeatureConfig> Feature<FC> registerFeature(String path, Feature<FC> feature) {
		return SomeMod.register(Registries.FEATURE, path, feature);
	}

	public static <FC extends FeatureConfig, F extends Feature<FC>> Reference<ConfiguredFeature<?, ?>> registerConfiguredFeature(Registerable<ConfiguredFeature<?, ?>> registerable, RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
		logRegistration(key.getValue().toString(), key.getRegistry().getPath().toString());
		return registerable.register(key, new ConfiguredFeature<>(feature, config));
	}

	public static Reference<PlacedFeature> registerPlacedFeature(Registerable<PlacedFeature> featureRegisterable, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> entry, PlacementModifier... modifiers) {
        logRegistration(key.getValue().toString(), key.getRegistry().getPath().toString());
		return featureRegisterable.register(key, new PlacedFeature(entry, List.of(modifiers)));
    }

	public static Reference<StructureSet> registerStructureSet(Registerable<StructureSet> registerable, RegistryKey<StructureSet> key, RegistryEntry<Structure> structure, StructurePlacement placement) {
		logRegistration(key.getValue().toString(), key.getRegistry().getPath().toString());
		return registerable.register(key, new StructureSet(structure, placement));
	}

	public static Reference<StructureProcessorList> registerStructureProcessorList(Registerable<StructureProcessorList> processorListRegisterable, RegistryKey<StructureProcessorList> key, StructureProcessor... processors) {
		logRegistration(key.getValue().toString(), key.getRegistry().getPath().toString());
		return processorListRegisterable.register(key, new StructureProcessorList(Arrays.asList(processors)));
	}

	public static Reference<StructurePool> registerStructurePool(Registerable<StructurePool> registerable, RegistryKey<StructurePool> key, RegistryEntry<StructurePool> fallback, List<Pair<Function<Projection, ? extends StructurePoolElement>, Integer>> elementCountsByGetters, Projection projection) {
		logRegistration(key.getValue().toString(), key.getRegistry().getPath().toString());
		return registerable.register(key, new StructurePool(fallback, elementCountsByGetters, projection));
	}

	public static Reference<StructurePool> registerStructurePool(Registerable<StructurePool> registerable, RegistryKey<StructurePool> key, RegistryEntry<StructurePool> fallback, Pair<Function<Projection, ? extends StructurePoolElement>, Integer> elementCountsByGetter, Projection projection) {
		return registerStructurePool(registerable, key, fallback, ImmutableList.of(elementCountsByGetter), projection);
	}

	public static void logAdvancementRegistration(String path) {
		LOGGER.info("| Registering Advancement | " + path);
	}

	public static void logLootTableRegistration(Identifier id) {
		LOGGER.info("| Registering Loot Table | " + id.toString());
	}

	public static Identifier registerLootTable(String path) {
		path = "chests/" + path;
		LOGGER.info("| Registering Loot Table | " + path);
		return SomeMod.id(path);
	}

	public static Identifier id(String path) {
		return new Identifier("somemod", path);
	}

	public static String idString(String path) {
		return "somemod:" + path;
	}

    private static String pathToEnglish(String path) {
        char[] chars = path.toCharArray();
        if (chars.length <= 0) throw new IllegalArgumentException("Path must not be empty");

        chars[0] = Character.toUpperCase(chars[0]);

        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == '_') {
                chars[i] = ' ';
                if (i + 1 < chars.length)
                    chars[i + 1] = Character.toUpperCase(chars[i + 1]);
            }
        }

        return new String(chars);
    }
}
