package somemod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;

/*
@Mixin(StructureWeightSampler.class)
public abstract class StructureWithOneGroundPieceTerrainAdaption {
 
    @Inject(method =
                "createStructureWeightSampler(" +
                "Lnet/minecraft/world/gen/StructureAccessor;" +
                "Lnet/minecraft/util/math/ChunkPos;)" +
                "Lnet/minecraft/world/gen/StructureWeightSampler;",
            at = @At(
                value = "INVOKE",
                target =
                    "Lnet/minecraft/world/gen/StructureAccessor;" +
                    "getStructureStarts(" +
                    "Lnet/minecraft/util/math/ChunkPos;" +
                    "Ljava/util/function/Predicate;)" +
                    "Ljava/util/List;"),
                locals = LocalCapture.CAPTURE_FAILHARD)
    public static void createStructureWeightSampler(StructureAccessor world, ChunkPos pos, CallbackInfoReturnable<StructureWeightSampler> info, int x, int y, ObjectArrayList<Piece> objectList) {
     
        world.getStructureStarts(pos, structure -> structure instanceof StructureWithOneGroundPiece).forEach(start -> {
         
            StructureTerrainAdaptation structureTerrainAdaptation;
            BlockBox groundPieceBoundingBox;
         
            if(start.getStructure() instanceof StructureWithOneGroundPiece structure
            && (structureTerrainAdaptation = structure.getActualTerrainAdaptation()) != null
            && (groundPieceBoundingBox     = structure.getGroundPieceBoundingBox())  != null) {
             
                objectList.add(new Piece(groundPieceBoundingBox, structureTerrainAdaptation, 0));
            }
            else
                throw new IllegalStateException("StructureWithOneGroundPieceTerrainAdaption: Structure " + start.getStructure().toString() + " does not implement StructureWithOneGroundPiece or returned null for getActualTerrainAdaptation() or getGroundPieceBoundingBox().");
        });
    }

    @Inject(
        method =
            "<init>(" +
            "Lit/unimi/dsi/fastutil/objects/ObjectListIterator;" +
            "Lit/unimi/dsi/fastutil/objects/ObjectListIterator;)" +
            "V",
        at = @At("TAIL"))
    public void StructureWeightSampler(ObjectListIterator<Piece> pieceIterator, ObjectListIterator<JigsawJunction> junctionIterator, CallbackInfo info) {}

    @Accessor("pieceIterator")
    public abstract void setPieceIterator(ObjectListIterator<Piece> pieceIterator);
}
*/

@Mixin(NoiseChunkGenerator.class)
public abstract class StructureWithOneGroundPieceTerrainAdaption {

    // @Redirect(
    //     method =
    //         "createChunkNoiseSampler(" +
    //         "Lnet/minecraft/world/chunk/Chunk;" +
    //         "Lnet/minecraft/world/gen/StructureAccessor;" +
    //         "Lnet/minecraft/world/gen/chunk/Blender;" +
    //         "Lnet/minecraft/world/gen/noise/NoiseConfig;)" +
    //         "Lnet/minecraft/world/gen/chunk/ChunkNoiseSampler;",
    //     at = @At(
    //         value = "INVOKE",
    //         target =
    //             "Lnet/minecraft/world/gen/StructureWeightSampler;" +
    //             "createStructureWeightSampler(" +
    //             "Lnet/minecraft/world/gen/StructureAccessor;" +
    //             "Lnet/minecraft/util/math/ChunkPos;)" +
    //             "Lnet/minecraft/world/gen/StructureWeightSampler;"))
    // private StructureWeightSampler newCreateStructureWeightSampler(StructureAccessor world, ChunkPos pos) {
        
    //     StructureWeightSampler originalWeightSampler = StructureWeightSampler.createStructureWeightSampler(world, pos);
    //     ObjectListIterator<Piece> pieceIterator = ((AccessableStructureWeightSampler) originalWeightSampler).getPieceIterator();
    //     ObjectListIterator<JigsawJunction> junctionIterator = ((AccessableStructureWeightSampler) originalWeightSampler).getJunctionIterator();

    //     ObjectList<Piece> pieceList = new ObjectArrayList<>();
    //     pieceIterator.forEachRemaining(pieceList::add);

    //     world.getStructureStarts(pos, structure -> structure instanceof StructureWithOneGroundPiece || structure instanceof TowerStructure).forEach(structureStart -> {
            
    //         System.out.println("1");

    //         if(structureStart.getStructure() instanceof StructureWithOneGroundPiece structure) {
                
    //             System.out.println("2");

    //             StructureTerrainAdaptation structureTerrainAdaptation = structure.getTerrainAdaptation();

    //             List<StructurePiece> pieces = structureStart.getChildren();

    //             List<StructurePiece> nonGroundPieces = pieces.stream().filter(piece -> !structure.isGroundPiece(piece)).toList();

    //             for(StructurePiece nonGroundPiece : nonGroundPieces) {

    //                 System.out.println("3");

    //                 for(Piece weightSamplerPiece : pieceList) {

    //                     if(weightSamplerPiece.box().equals(nonGroundPiece.getBoundingBox())) {

    //                         System.out.println("4");

    //                         if(weightSamplerPiece.terrainAdjustment() == structureTerrainAdaptation) {

    //                             System.out.println("5");

    //                             // pieceList.remove(weightSamplerPiece);

    //                         }

    //                     }

    //                 }

    //             }

    //             // structureStart.getChildren().stream()
    //             // .filter(piece -> !structure.isGroundPiece(piece))
    //             // .forEach(nonGroundPiece ->
    //             //     pieceList.removeIf(weightSamplerPiece -> {
    //             //         System.out.println("-------->");
    //             //         return false; // weightSamplerPiece.box().equals(nonGroundPiece.getBoundingBox()) && weightSamplerPiece.terrainAdjustment() == structureTerrainAdaptation; /* && weightSamplerPiece.groundLevelDelta() == 0 */
    //             //     })
    //             // );
    //         }
    //     });

    //     return new StructureWeightSampler(pieceList.iterator(), junctionIterator);

    // }

}