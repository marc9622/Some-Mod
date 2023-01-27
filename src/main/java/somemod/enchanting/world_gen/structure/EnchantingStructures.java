package somemod.enchanting.world_gen.structure;

import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.StructureTerrainAdaptation;
import net.minecraft.world.gen.structure.Structure;
import somemod.SomeMod;
import somemod.enchanting.tag.EnchantingHasStructureBiomeTags;
import somemod.enchanting.world_gen.structure.pool.EnchantingStructurePools;

public class EnchantingStructures {
    
    public static final RegistryKey<Structure> ENCHANTING_TOWER = SomeMod.keyOf(RegistryKeys.STRUCTURE, "enchanting_tower");

    public static void bootstrap(Registerable<Structure> registerable) {

        SomeMod.register(registerable, ENCHANTING_TOWER, new TowerStructure(
            new Structure.Config(
                registerable.getRegistryLookup(RegistryKeys.BIOME).getOrThrow(EnchantingHasStructureBiomeTags.ENCHANTING_TOWER_HAS_STRUCTURE),
                Map.of(),
                GenerationStep.Feature.SURFACE_STRUCTURES,
                StructureTerrainAdaptation.BEARD_THIN
            ),
            registerable.getRegistryLookup(RegistryKeys.TEMPLATE_POOL).getOrThrow(EnchantingStructurePools.ENCHANTING_TOWER_BOTTOM),
            ImmutableList.of(
                new Pair<>(
                    registerable.getRegistryLookup(RegistryKeys.TEMPLATE_POOL).getOrThrow(EnchantingStructurePools.ENCHANTING_TOWER_MIDDLE),
                    TowerStructure.RotationStyle.COPY_PREVIOUS),
                new Pair<>(
                    registerable.getRegistryLookup(RegistryKeys.TEMPLATE_POOL).getOrThrow(EnchantingStructurePools.ENCHANTING_TOWER_TOP),
                    TowerStructure.RotationStyle.RANDOM)
            )
        ));

    }

}
