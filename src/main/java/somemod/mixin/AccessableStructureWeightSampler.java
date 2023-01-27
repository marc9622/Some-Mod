package somemod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import it.unimi.dsi.fastutil.objects.ObjectListIterator;
import net.minecraft.structure.JigsawJunction;
import net.minecraft.world.gen.StructureWeightSampler;
import net.minecraft.world.gen.StructureWeightSampler.Piece;

@Mixin(StructureWeightSampler.class)
public interface AccessableStructureWeightSampler {

    @Accessor("pieceIterator")
    ObjectListIterator<Piece> getPieceIterator();
    
    @Accessor("junctionIterator")
    ObjectListIterator<JigsawJunction> getJunctionIterator();

}