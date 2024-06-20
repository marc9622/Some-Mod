package somemod.frost.entity;

import java.util.WeakHashMap;
import java.util.function.ToDoubleFunction;

import org.jetbrains.annotations.Nullable;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.biome.source.util.MultiNoiseUtil.MultiNoiseSampler;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.densityfunction.DensityFunction.UnblendedNoisePos;
import net.minecraft.world.gen.noise.NoiseConfig;
import net.minecraft.world.gen.noise.NoiseRouter;
import somemod.frost.entity.attribute.FrostEntityAttributes;
import somemod.mixin.BiomeTemperature;

public final class EntityFreezing {

    private static WeakHashMap<LivingEntity, Float> cachedTemp = new WeakHashMap<>();

    // TODO: Maybe allow creative players to freeze, but don't give them the debuffs,
    //       so that they can get the buffs from the ice crown?
    public static boolean canFreeze(PlayerEntity player) {
        return !player.isCreative() && !player.isSpectator();
    }

    public static float getEntityWarmth(LivingEntity entity) {
        float warmth = 1.0f;

        warmth += (float) entity.getAttributeValue(FrostEntityAttributes.WARMTH);

        // Non-player entities are most likely not able to equip
        // warm armor, meaning that they will often freeze to death.
        // This should help them a bit.
        if (!(entity instanceof PlayerEntity))
            warmth += 2.5f;

        warmth += Math.min(entity.getVelocity().lengthSquared() - 0.006f, 1.0f) * 0.30f;
        if (entity.isSneaking()) warmth += 0.20f;

        return warmth;
    }

    public static float getLocalTemperature(@Nullable LivingEntity entity, ChunkGenerator chunkGenerator, NoiseConfig noiseConfig, BlockPos pos) {
        if (entity != null && entity.age % 10 != 0) {
            Float temp = cachedTemp.get(entity);
            if (temp != null)
                return temp;
        }

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
        if (entity != null)
            cachedTemp.put(entity, result);

        return result;
    }

    public static float getLocalTemperature(ChunkGenerator chunkGenerator, NoiseConfig noiseConfig, BlockPos pos) {
        return getLocalTemperature(null, chunkGenerator, noiseConfig, pos);
    }
    
}
