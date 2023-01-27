package somemod.enchanting.world_gen.structure;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.StructureSet;
import net.minecraft.world.gen.chunk.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.gen.chunk.placement.SpreadType;
import net.minecraft.world.gen.structure.Structure;
import somemod.SomeMod;

public class EnchantingStructureSets {
    
    public static final RegistryKey<StructureSet> ENCHANTING_TOWER = SomeMod.keyOf(RegistryKeys.STRUCTURE_SET, "enchanting_tower");

    public static void bootstrap(Registerable<StructureSet> registerable) {

        RegistryEntryLookup<Structure> registryEntryLookup = registerable.getRegistryLookup(RegistryKeys.STRUCTURE);
        
        SomeMod.registerStructureSet(registerable, ENCHANTING_TOWER,
            registryEntryLookup.getOrThrow(EnchantingStructures.ENCHANTING_TOWER),
            new RandomSpreadStructurePlacement(40, 30, SpreadType.LINEAR, 1201149419)
        );

    }

}
