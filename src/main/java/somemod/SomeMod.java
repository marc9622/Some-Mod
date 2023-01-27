package somemod;

import net.fabricmc.api.ModInitializer;
import net.minecraft.registry.Registerable;
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
import somemod.crystal.world_gen.CrystalGeneration;
import somemod.crystal.world_gen.feature.CrystalFeatures;
import somemod.enchanting.block.EnchantingBlocks;
import somemod.enchanting.block.entity.EnchantingBlockEntities;
import somemod.enchanting.item.EnchantingItems;
import somemod.enchanting.screen.EnchantingScreenHandlers;
import somemod.enchanting.world_gen.structure.EnchantingStructurePieceTypes;
import somemod.enchanting.world_gen.structure.EnchantingStructureTypes;
import somemod.enchanting.world_gen.structure.processor.EnchantingStructureProcessorTypes;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;

public class SomeMod implements ModInitializer {
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

		addRegistryEntries();
	}
	
	private static void addRegistryEntries() {
		CrystalFeatures.register();
		CrystalGeneration.generateCrystalFeatures();
	}

	/**
	 * If a class is not referenced anywhere, it might not be loaded.
	 * This method does nothing, but it makes sure that the classes are loaded.
	 */
	private static void notifyFabric() {
		CrystalBlocks.notifyFabric();
		CrystalItems.notifyFabric();
		EnchantingBlocks.notifyFabric();
		EnchantingBlockEntities.notifyFabric();
		EnchantingItems.notifyFabric();
		EnchantingScreenHandlers.notifyFabric();
		EnchantingStructureTypes.notifyFabric();
		EnchantingStructurePieceTypes.notifyFabric();
		EnchantingStructureProcessorTypes.notifyFabric();
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

	public static Identifier id(String path) {
		return new Identifier("somemod", path);
	}

	public static String idString(String path) {
		return "somemod:" + path;
	}
}
