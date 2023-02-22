package somemod.magic.world_gen.structure;

import java.util.Map;
import java.util.function.Function;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.StructureTerrainAdaptation;
import net.minecraft.world.gen.structure.Structure;
import somemod.SomeMod;
import somemod.magic.tag.MagicHasStructureBiomeTags;
import somemod.magic.world_gen.structure.pool.MagicStructurePools;

public class MagicStructures {
    
    public static final RegistryKey<Structure> ENCHANTING_TOWER_COMMON = SomeMod.keyOf(RegistryKeys.STRUCTURE, "enchanting_tower_common");
    public static final RegistryKey<Structure> ENCHANTING_TOWER_RARE   = SomeMod.keyOf(RegistryKeys.STRUCTURE, "enchanting_tower_rare");

    public static void bootstrap(Registerable<Structure> registerable) {

        final Function<TagKey<Biome>, TowerStructure> ENCHANTING_TOWER_STRUCTURE = biomeTag -> new TowerStructure(
            new Structure.Config(
                registerable.getRegistryLookup(RegistryKeys.BIOME).getOrThrow(biomeTag),
                Map.of(),
                GenerationStep.Feature.SURFACE_STRUCTURES,
                StructureTerrainAdaptation.BEARD_THIN
            ),
            registerable.getRegistryLookup(RegistryKeys.TEMPLATE_POOL).getOrThrow(MagicStructurePools.ENCHANTING_TOWER_BOTTOM),
            ImmutableList.of(
                new Pair<>(
                    registerable.getRegistryLookup(RegistryKeys.TEMPLATE_POOL).getOrThrow(MagicStructurePools.ENCHANTING_TOWER_MIDDLE),
                    TowerStructure.RotationStyle.COPY_PREVIOUS),
                new Pair<>(
                    registerable.getRegistryLookup(RegistryKeys.TEMPLATE_POOL).getOrThrow(MagicStructurePools.ENCHANTING_TOWER_TOP),
                    TowerStructure.RotationStyle.RANDOM)
            )
        );

        SomeMod.register(registerable, ENCHANTING_TOWER_COMMON, ENCHANTING_TOWER_STRUCTURE.apply(MagicHasStructureBiomeTags.ENCHANTING_TOWER_COMMON_HAS_STRUCTURE));
        SomeMod.register(registerable, ENCHANTING_TOWER_RARE, ENCHANTING_TOWER_STRUCTURE.apply(MagicHasStructureBiomeTags.ENCHANTING_TOWER_RARE_HAS_STRUCTURE));

    }

}