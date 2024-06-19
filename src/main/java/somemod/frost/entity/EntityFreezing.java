package somemod.frost.entity;

import java.util.WeakHashMap;
import java.util.function.ToDoubleFunction;

import org.jetbrains.annotations.Nullable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerChunkManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.biome.source.util.MultiNoiseUtil.MultiNoiseSampler;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.densityfunction.DensityFunction.UnblendedNoisePos;
import net.minecraft.world.gen.noise.NoiseConfig;
import net.minecraft.world.gen.noise.NoiseRouter;
import somemod.mixin.BiomeTemperature;

public final class EntityFreezing {

    private static WeakHashMap<PlayerEntity, Float> cachedTemp = new WeakHashMap<>();

    public static float getLocalTemperature(@Nullable PlayerEntity player, ServerWorldAccess world, BlockPos pos) {
        if (player != null && player.age % 10 != 0) {
            Float temp = cachedTemp.get(player);
            if (temp != null)
                return temp;
        }

        if (world.getChunkManager() instanceof ServerChunkManager chunkManager) {
            ChunkGenerator chunkGenerator = chunkManager.getChunkGenerator();
            NoiseConfig noiseConfig = chunkManager.getNoiseConfig();

            BiomeSource biomeSource = chunkGenerator.getBiomeSource();
            int x = pos.getX(), y = pos.getY(), z = pos.getZ();
            MultiNoiseSampler noiseSampler = noiseConfig.getMultiNoiseSampler();
            ToDoubleFunction<RegistryEntry<Biome>> tempGetter = biome -> ((BiomeTemperature)(Object)biome.value()).invokeGetTemperature(pos);

            float biomes4 = (float) biomeSource.getBiomesInArea(x, y, z, 4, noiseSampler).stream().mapToDouble(tempGetter).average().orElse(0);
            float biomes8 = (float) biomeSource.getBiomesInArea(x, y, z, 8, noiseSampler).stream().mapToDouble(tempGetter).average().orElse(0);
            float biomes16 = (float) biomeSource.getBiomesInArea(x, y, z, 16, noiseSampler).stream().mapToDouble(tempGetter).average().orElse(0);
            float biomeTemp = biomes4 * 1.00f + biomes8 * 1.00f + biomes16 * 1.00f;

            NoiseRouter noiseRouter = noiseConfig.getNoiseRouter();
            float noiseTemp = (float) noiseRouter.temperature().sample(new UnblendedNoisePos(pos.getX(), pos.getY(), pos.getZ()));

            float result = biomeTemp * 0.25f + noiseTemp * 1.00f;
            if (player != null)
                cachedTemp.put(player, result);

            return result;
        }
        else throw new RuntimeException("My hypothesis was wrong :(");
    }

    public static float getLocalTemperature(ServerWorldAccess world, BlockPos pos) {
        return getLocalTemperature(null, world, pos);
    }
    
}
