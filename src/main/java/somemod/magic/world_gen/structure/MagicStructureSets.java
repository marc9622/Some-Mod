package somemod.magic.world_gen.structure;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.StructureSet;
import net.minecraft.world.gen.chunk.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.gen.chunk.placement.SpreadType;
import net.minecraft.world.gen.structure.Structure;
import somemod.SomeMod;

public class MagicStructureSets {
    
    public static final RegistryKey<StructureSet> ENCHANTING_TOWER_COMMON = SomeMod.keyOf(RegistryKeys.STRUCTURE_SET, "enchanting_tower_common");
    public static final RegistryKey<StructureSet> ENCHANTING_TOWER_RARE   = SomeMod.keyOf(RegistryKeys.STRUCTURE_SET, "enchanting_tower_rare");

    public static void bootstrap(Registerable<StructureSet> registerable) {

        RegistryEntryLookup<Structure> registryEntryLookup = registerable.getRegistryLookup(RegistryKeys.STRUCTURE);
        
        SomeMod.registerStructureSet(registerable, ENCHANTING_TOWER_COMMON,
            registryEntryLookup.getOrThrow(MagicStructures.ENCHANTING_TOWER_COMMON),
            new RandomSpreadStructurePlacement(20, 15, SpreadType.LINEAR, 1201149419)
        );

        SomeMod.registerStructureSet(registerable, ENCHANTING_TOWER_RARE,
            registryEntryLookup.getOrThrow(MagicStructures.ENCHANTING_TOWER_RARE),
            new RandomSpreadStructurePlacement(75, 40, SpreadType.LINEAR, 1201149419)
        );

    }

}
