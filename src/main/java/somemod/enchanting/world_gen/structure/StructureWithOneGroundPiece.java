package somemod.enchanting.world_gen.structure;

import net.minecraft.structure.StructurePiece;
import net.minecraft.world.gen.StructureTerrainAdaptation;

public interface StructureWithOneGroundPiece {

    public StructureTerrainAdaptation getTerrainAdaptation();

    // public BlockBox getGroundPieceBoundingBox();

    public boolean isGroundPiece(StructurePiece piece);

    // public StructurePiece getGroundPiece();

}
