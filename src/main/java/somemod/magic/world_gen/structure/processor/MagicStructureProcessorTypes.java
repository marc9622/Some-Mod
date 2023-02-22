package somemod.magic.world_gen.structure.processor;

import net.minecraft.registry.Registries;
import net.minecraft.structure.processor.StructureProcessorType;
import somemod.SomeMod;

public class MagicStructureProcessorTypes {
    
    public static final StructureProcessorType<BiomeRuleStructureProcessor> BIOME_RULE = SomeMod.register(Registries.STRUCTURE_PROCESSOR, "biome_rule", () -> BiomeRuleStructureProcessor.CODEC);

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}

}
