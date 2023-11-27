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
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
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
    private void setFrozenTicks(LivingEntity entity, int frozenTicks) {

        boolean isPlayer = false;
        if (entity instanceof PlayerEntity player) {
            isPlayer = true;

            if (player.getAbilities().creativeMode || player.isSpectator()) {
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

        int bodyHeat = 1; // avoid division by 0
        for (ItemStack stack : entity.getArmorItems()) {
            if (stack.isIn(ItemTags.FREEZE_IMMUNE_WEARABLES))
                bodyHeat += 1;
        }

        // Non-player entities are most likely not able to equip
        // warm armor, meaning that they will often freeze to death.
        // This should help them a bit.
        if (!(entity instanceof PlayerEntity))
            bodyHeat += 2;

        if (entity.inPowderSnow) {
            increase += 1.50f / bodyHeat;
            //SomeMod.logInfo("in powder snow adds " + 1.00f / bodyHeat);
        }

        Block steppingBlock = entity.getSteppingBlockState().getBlock();
        if (steppingBlock == Blocks.SNOW ||
            steppingBlock == Blocks.SNOW_BLOCK ||
            steppingBlock == Blocks.ICE) {
            //SomeMod.logInfo("stepping on snow");
            increase += 0.20f / bodyHeat;
        }

        @Nullable
        StatusEffectInstance frostbite = entity.getStatusEffect(FrostStatusEffects.FROSTBITE);

        if (frostbite != null) {
            float amount = frostbite.getAmplifier() + 0.50f;
            increase += amount / bodyHeat;
            //SomeMod.logInfo("frostbite adds " + amount / bodyHeat);
        }

        World world = entity.getWorld();
        BlockPos pos = entity.getBlockPos();
        Biome biome = world.getBiome(pos).value();
        float temperature = biome.getTemperature();

        // Temperature can be between 2.0f and -0.7f
        if (isPlayer) increase += -temperature * 1.50f;
        //SomeMod.logInfo("temperature: " + temperature + ", adds " + temperature * -1.50f);

        float light = (float) world.getLightLevel(pos) / (float) world.getMaxLightLevel();
        increase += -light * 0.66;
        //SomeMod.logInfo("light: " + light + ", adds " + light * -0.66f);

        // All players have a base increase of -0.15f because bodyHeat starts at 1.
        increase += -bodyHeat * 0.15f;
        //SomeMod.logInfo("body heat: " + bodyHeat + ", adds " + bodyHeat * -0.15f);

        if (temperature <= 0.15f) {

            if (isPlayer && entity.isInsideWaterOrBubbleColumn()) {
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
                    increase += 0.33f / bodyHeat;

                //SomeMod.logInfo("snowing adds " + 0.20f + ", sky adds " + 0.33f / bodyHeat);
            }

            if (isNight && isSkyVisible)
                increase += 0.33f / bodyHeat;
        }

        //SomeMod.logInfo("freezing increase: " + increase);

        int integer = (int) Math.round(Math.floor(increase));
        float rest = increase - integer;

        float randomNumber = entity.getRandom().nextFloat();
        integer += randomNumber < rest ? 1 : 0;
        
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

