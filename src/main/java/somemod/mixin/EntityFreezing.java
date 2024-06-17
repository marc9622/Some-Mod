package somemod.mixin;

import java.util.WeakHashMap;
import java.util.function.ToDoubleFunction;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerChunkManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.biome.source.util.MultiNoiseUtil.MultiNoiseSampler;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.densityfunction.DensityFunction.UnblendedNoisePos;
import net.minecraft.world.gen.noise.NoiseConfig;
import net.minecraft.world.gen.noise.NoiseRouter;
import somemod.frost.entity.attribute.FrostEntityAttributes;
import somemod.frost.entity.effect.FrostStatusEffects;

// TODO: Make frostbite armor give an effect that makes being frozen a buff.
// TODO: Make the entities' y-coordinate also affect freezing.
// TODO: Make snowballs add a little freezing.
@Mixin(LivingEntity.class)
public abstract class EntityFreezing {

    @Redirect(
        method = "tickMovement()V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/LivingEntity;" +
                     "setFrozenTicks(I)V"))
    // This function slowly increases frozen ticks when it is cold.
    private void setFrozenTicks(final LivingEntity entity, final int setTicks) {

        @Nullable
        PlayerEntity player = null;
        if (entity instanceof PlayerEntity playerEntity) {
            player = playerEntity;

            if (player.isCreative() || player.isSpectator()) {
                entity.setFrozenTicks(0);
                return;
            }
        }

        final int currentTicks = entity.getFrozenTicks();
        //SomeMod.logInfo("### Info about: " + entity);
        //SomeMod.logInfo("currentTicks = " + currentTicks);

        int maxTicks = entity.getMinFreezeDamageTicks();
        if (currentTicks > maxTicks + 1) {
            entity.setFrozenTicks(maxTicks + 1);
            return;
        }

        if (currentTicks < 0) {
            entity.setFrozenTicks(0);
            return;
        }

        if (entity.isOnFire()) {
            entity.setFrozenTicks(currentTicks - 4);
            return;
        }

        float increase = 0;

        /* Body Heat */ {
            // // Preferably, warm armor should have the WARMTH attribute.
            // // But because FREEZE_IMMUNE_WEARABLES is already in the game,
            // // this will then automatically support armor with that tag.
            // double armorImmunity = 0;
            // for (ItemStack stack : entity.getArmorItems()) {
            //     if (stack.isIn(ItemTags.FREEZE_IMMUNE_WEARABLES)) {
            //         armorImmunity += 1;
            //     }
            // }

            double warmth = entity.getAttributeValue(FrostEntityAttributes.WARMTH);

            // Non-player entities are most likely not able to equip
            // warm armor, meaning that they will often freeze to death.
            // This should help them a bit.
            if (player == null)
                warmth += 2;
            else
                warmth += 1;

            // All players have a base increase of -0.15f because bodyHeat starts at 1.
            increase += -warmth * 0.15f;
            //SomeMod.logInfo("body heat: " + bodyHeat + ", adds " + bodyHeat * -0.15f);

            if (entity.inPowderSnow) {
                increase += 1.50f / Math.min(warmth, 1.0f);
                //SomeMod.logInfo("in powder snow adds " + 1.50f / bodyHeat);
            }

            Block steppingBlock = entity.getSteppingBlockState().getBlock();
            if (steppingBlock == Blocks.SNOW ||
                steppingBlock == Blocks.SNOW_BLOCK ||
                steppingBlock == Blocks.ICE) {
                //SomeMod.logInfo("stepping on snow");
                increase += 0.20f / Math.min(warmth, 1.0f);
            }

            @Nullable
            StatusEffectInstance frostbite = entity.getStatusEffect(FrostStatusEffects.FROSTBITE);
            if (frostbite != null) {
                float amount = frostbite.getAmplifier() + 0.50f;
                increase += amount / Math.min(warmth, 1.0f);
                //SomeMod.logInfo("frostbite adds " + amount / bodyHeat);
            }
        }

        /* Environment */ {
            World world = entity.getWorld();
            BlockPos pos = entity.getBlockPos();
            Biome biome = world.getBiome(pos).value();

            // The world should always be a server world, because the LivingEntity class
            // checks that world is not client before calling method, so this is just a
            // safety check.
            if (player != null && world instanceof ServerWorld serverWorld) {
                double temp = getLocalTemperature(player, serverWorld, pos);
                increase += temp * -0.90f + 0.15f;
                //SomeMod.logInfo("Temp: " + temp);
            }

            float light = (float) world.getLightLevel(pos) / (float) world.getMaxLightLevel();
            increase += -light * 0.66;
            //SomeMod.logInfo("light: " + light + ", adds " + light * -0.66f);

            if (biome.isCold(pos)) {

                if (player != null && entity.isInsideWaterOrBubbleColumn()) {
                    // Warm armor is not very effective in water.
                    increase += 1.00f;
                }

                boolean isSnowing = world.isRaining();
                boolean isSkyVisible = world.isSkyVisible(pos);
                boolean isNight = world.isNight();

                // It is colder when it is snowing.
                if (isSnowing) {
                    increase += 0.20f;

                    if (isSkyVisible)
                        increase += 0.33f;

                    //SomeMod.logInfo("snowing adds " + 0.20f + ", sky adds " + 0.33f / bodyHeat);
                }

                if (isNight && isSkyVisible)
                    increase += 0.33f;
            }
        }

        //SomeMod.logInfo("freezing increase: " + increase);

        int integer = (int) Math.round(Math.floor(increase));
        float rest = increase - integer;

        // Adds the rest in randomly
        // float randomNumber = entity.getRandom().nextFloat();
        // integer += randomNumber < rest ? 1 : 0;

        // Adds the rest in deterministically
        if (entity.age % (int) Math.ceil(1 / rest) == 0)
            integer += 1;
        
        entity.setFrozenTicks(currentTicks + integer);
    }

    @Redirect(
        method = "tickMovement()V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/LivingEntity;" +
                     "canFreeze()Z"))
    private boolean canFreeze(LivingEntity entity) {
        return true;
    }

    private static WeakHashMap<PlayerEntity, Float> cachedTemp = new WeakHashMap<>();

    private static float getLocalTemperature(PlayerEntity player, ServerWorld world, BlockPos pos) {
        if (player.age % 10 != 0) {
            Float temp = cachedTemp.get(player);
            if (temp != null)
                return temp;
        }

        ServerChunkManager chunkManager = world.getChunkManager();
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
        cachedTemp.put(player, result);
        return result;
    }

}

