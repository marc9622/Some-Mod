package somemod.magic.world_gen.structure;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.structure.StructurePieceType;
import somemod.SomeMod;

public class MagicStructurePieceTypes {
    
    public static final StructurePieceType TOWER = Registry.register(Registries.STRUCTURE_PIECE, SomeMod.id("tower"), TowerGenerator.TowerPiece::new);

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}

}
