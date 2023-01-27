package somemod.enchanting.world_gen.structure.pool;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.pool.StructurePools;
import net.minecraft.structure.processor.StructureProcessorList;
import somemod.SomeMod;
import somemod.enchanting.world_gen.structure.processor.EnchantingStructureProcessorLists;

public class EnchantingStructurePools {
    
    public static final RegistryKey<StructurePool> ENCHANTING_TOWER_BOTTOM = SomeMod.keyOf(RegistryKeys.TEMPLATE_POOL, "enchanting_tower_bottom");
    public static final RegistryKey<StructurePool> ENCHANTING_TOWER_MIDDLE = SomeMod.keyOf(RegistryKeys.TEMPLATE_POOL, "enchanting_tower_middle");
    public static final RegistryKey<StructurePool> ENCHANTING_TOWER_TOP    = SomeMod.keyOf(RegistryKeys.TEMPLATE_POOL, "enchanting_tower_top");

    public static void bootstrap(Registerable<StructurePool> registerable) {
        
        RegistryEntry.Reference<StructurePool> empty = registerable.getRegistryLookup(RegistryKeys.TEMPLATE_POOL).getOrThrow(StructurePools.EMPTY);
        RegistryEntry.Reference<StructureProcessorList> processorList = registerable.getRegistryLookup(RegistryKeys.PROCESSOR_LIST).getOrThrow(EnchantingStructureProcessorLists.ENCHANTING_TOWER);

        SomeMod.registerStructurePool(registerable, ENCHANTING_TOWER_BOTTOM,
            empty,
            ImmutableList.of(
                Pair.of(StructurePoolElement.ofProcessedSingle(SomeMod.idString("enchanting_tower/bottom_1"), processorList), 1),
                Pair.of(StructurePoolElement.ofProcessedSingle(SomeMod.idString("enchanting_tower/bottom_2"), processorList), 1)
            ),
            StructurePool.Projection.RIGID
        );

        SomeMod.registerStructurePool(registerable, ENCHANTING_TOWER_MIDDLE,
            empty,
            ImmutableList.of(
                Pair.of(StructurePoolElement.ofProcessedSingle(SomeMod.idString("enchanting_tower/middle_1"), processorList), 1),
                Pair.of(StructurePoolElement.ofProcessedSingle(SomeMod.idString("enchanting_tower/middle_2"), processorList), 1)
            ),
            StructurePool.Projection.RIGID
        );

        SomeMod.registerStructurePool(registerable, ENCHANTING_TOWER_TOP,
            empty,
            ImmutableList.of(
                Pair.of(StructurePoolElement.ofProcessedSingle(SomeMod.idString("enchanting_tower/top_1"), processorList), 1),
                Pair.of(StructurePoolElement.ofProcessedSingle(SomeMod.idString("enchanting_tower/top_2"), processorList), 1)
            ),
            StructurePool.Projection.RIGID
        );

    }

}
