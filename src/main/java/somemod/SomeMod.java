package somemod;

import net.fabricmc.api.ModInitializer;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import somemod.crystal.block.CrystalBlocks;
import somemod.crystal.item.CrystalItems;
import somemod.crystal.world.feature.CrystalFeatures;
import somemod.crystal.world.gen.CrystalGeneration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

		LOGGER.info("Hello Fabric world!");

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
	}
	
	public static void logRegistration(String name, String registry) {
		LOGGER.info("Registering " + name + " in " + registry);
	}

	public static <V, T extends V> T register(Registry<V> registry, String name, T entry) {
		logRegistration(name, registry.getKey().getValue().toString());
        return Registry.register(registry, id(name), entry);
    }

	public static Identifier id(String path) {
		return new Identifier("somemod", path);
	}
}
