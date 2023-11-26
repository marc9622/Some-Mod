package somemod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import somemod.SomeMod;

// TODO: Make frostbite armor give an effect that makes being frozen a buff.
// TODO: Reduce the effectiveness of freeze immune armor.
@Mixin(LivingEntity.class)
public abstract class EntityFreezing {

    @Redirect(
        method = "tickMovement()V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/LivingEntity;" +
                     "setFrozenTicks(I)V"))
    // This function slowly increases frozen ticks when it is snowing.
    private void setFrozenTicks(LivingEntity entity, int frozenTicks) {
        final int currentTicks = entity.getFrozenTicks();

        int maxTicks = entity.getMinFreezeDamageTicks();
        if (currentTicks > maxTicks) {
            entity.setFrozenTicks(maxTicks);
            return;
        }

        // If the entity can not freeze, or we are already increasing the
        // frozen ticks, then return.
        if (!entity.canFreeze() || frozenTicks > currentTicks) {
            entity.setFrozenTicks(frozenTicks);
            return;
        }

        SomeMod.logInfo("currentTicks = " + currentTicks);

        World world = entity.getWorld();
        BlockPos pos = entity.getBlockPos();
        Biome biome = world.getBiome(pos).value();
        float temperature = biome.getTemperature();

        if (temperature <= 0.15f) {

            if (entity.isInsideWaterOrBubbleColumn()) {
                entity.setFrozenTicks(currentTicks + 1);
                return;
            }

            float chance = -temperature; // temperature can be between 2.0f and -0.7f

            if (world.isRaining()) {
                if (world.isSkyVisible(pos))
                    chance += 0.15f;
                else
                    chance += 0.05f;
            }

            float light = (float) world.getLightLevel(pos) / (float) world.getMaxLightLevel();

            chance -= light * 0.50f;


            float random = entity.getRandom().nextFloat();
            if (chance > 0)
                entity.setFrozenTicks(random < chance      ? currentTicks + 1: currentTicks);
            else
                entity.setFrozenTicks(random < chance * -1 ? currentTicks - 1: currentTicks);
            return;
        }

        entity.setFrozenTicks(currentTicks - 1);
    }

}

