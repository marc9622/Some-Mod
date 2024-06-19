package somemod.mixin;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerChunkManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.noise.NoiseConfig;
import somemod.frost.entity.EntityFreezing;
import somemod.frost.entity.effect.FrostStatusEffects;

// TODO: Make frostbite armor give an effect that makes being frozen a buff.
// TODO: Make the entities' y-coordinate also affect freezing.
// TODO: Add at frozen ticks and local temperature to F3 debug info.
@Mixin(LivingEntity.class)
public abstract class EntityFrozenTicks {

    @Redirect(
        method = "tickMovement()V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/LivingEntity;" +
                     "setFrozenTicks(I)V"))
    // This function slowly increases frozen ticks when it is cold.
    private void setFrozenTicks(final LivingEntity entity, final int setTicks) {

        if (entity instanceof PlayerEntity player && !EntityFreezing.canFreeze(player)) {
            entity.setFrozenTicks(0);
            return;
        }

        final int currentTicks = entity.getFrozenTicks();
        //SomeMod.logInfo("### Info about: " + entity);
        //SomeMod.logInfo("currentTicks = " + currentTicks);

        if (currentTicks > entity.getMinFreezeDamageTicks()) {
            entity.setFrozenTicks(entity.getMinFreezeDamageTicks());
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
            float warmth = EntityFreezing.getEntityWarmth(entity);

            increase -= warmth * 0.15f;
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

            increase -= Math.min(warmth, 0.0f) * 0.50f;
        }

        /* Environment */ {
            World world = entity.getWorld();
            BlockPos pos = entity.getBlockPos();
            Biome biome = world.getBiome(pos).value();

            // The world should always be a server world, because the LivingEntity class
            // checks that world is not client before calling method, so this is just a
            // safety check.
            if (world instanceof ServerWorld serverWorld) {
                ServerChunkManager chunkManager = serverWorld.getChunkManager();
                ChunkGenerator chunkGenerator = chunkManager.getChunkGenerator();
                NoiseConfig noiseConfig = chunkManager.getNoiseConfig();

                double temp = EntityFreezing.getLocalTemperature(entity, chunkGenerator, noiseConfig, pos);
                increase += temp * -0.90f + 0.15f;
                //SomeMod.logInfo("Temp: " + temp);
            }

            float light = (float) world.getLightLevel(pos) / (float) world.getMaxLightLevel();
            increase += -light * 0.66;
            //SomeMod.logInfo("light: " + light + ", adds " + light * -0.66f);

            if (biome.isCold(pos)) {

                // TODO: If possible, make it so that all entities that don't live in water freeze in water.
                if (entity instanceof PlayerEntity && entity.isInsideWaterOrBubbleColumn()) {
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

}

