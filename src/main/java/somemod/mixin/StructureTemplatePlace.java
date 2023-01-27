package somemod.mixin;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.structure.StructureTemplate;

@Mixin(StructureTemplate.class)
public abstract class StructureTemplatePlace {
    
    // @Redirect(
    //     method =
    //         "place(" +
    //         "Lnet/minecraft/world/ServerWorldAccess;" +
    //         "Lnet/minecraft/util/math/BlockPos;" +
    //         "Lnet/minecraft/util/math/BlockPos;" +
    //         "Lnet/minecraft/structure/StructurePlacementData;" +
    //         "Lnet/minecraft/util/math/random/Random;" +
    //         "I)" +
    //         "Z",
    //     at = @At(
    //         value = "INVOKE",
    //         target =
    //             "Lnet/minecraft/world/ServerWorldAccess;" +
    //             "setBlockState(" +
    //             "Lnet/minecraft/util/math/BlockPos;" +
    //             "Lnet/minecraft/block/BlockState;" +
    //             "I)" +
    //             "Z"))
    // private boolean newSetBlockState(ServerWorldAccess world, BlockPos pos, BlockState state, int flags) {
        
    //     return true;
    // }

}
