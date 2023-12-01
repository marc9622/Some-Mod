package somemod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;

@Mixin(Biome.class)
public interface BiomeTemperature {

    @Invoker("getTemperature")
    public float invokeGetTemperature(BlockPos pos);

}

