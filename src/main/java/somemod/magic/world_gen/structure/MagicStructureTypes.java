package somemod.magic.world_gen.structure;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.world.gen.structure.StructureType;
import somemod.SomeMod;

public class MagicStructureTypes {
    
    public static final StructureType<TowerStructure> TOWER = Registry.register(Registries.STRUCTURE_TYPE, SomeMod.id("tower_structure"), () -> TowerStructure.CODEC);

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}

}
