package somemod.mixin;

import java.util.List;
import java.util.Locale;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.DebugHud;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.noise.NoiseConfig;
import somemod.frost.entity.EntityFreezing;

@Mixin(DebugHud.class)
public abstract class DebugHudTemperature {

    @Shadow @Final
    private MinecraftClient client;

    @Redirect(
        method = "getLeftText(" +
                 ")Ljava/util/List;",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/gen/chunk/ChunkGenerator;" +
                     "getDebugHudText(" +
                     "Ljava/util/List;" +
                     "Lnet/minecraft/world/gen/noise/NoiseConfig;" +
                     "Lnet/minecraft/util/math/BlockPos;" +
                     ")V"))
    protected void addTemperature(ChunkGenerator chunkGenerator, List<String> list, NoiseConfig noiseConfig, BlockPos blockPos) {

        if (blockPos.getY() >= client.world.getBottomY() && blockPos.getY() < client.world.getTopY()) {
            float temperature = EntityFreezing.getLocalTemperature(chunkGenerator, noiseConfig, blockPos);
            String string = String.format(Locale.ROOT, "Temperature: %.2f", temperature);

            if (client.getCameraEntity() instanceof PlayerEntity player && EntityFreezing.canFreeze(player)) {
                float warmth = EntityFreezing.getEntityWarmth(player);

                int frozenTicks = Math.max(Math.min(player.getFrozenTicks(), player.getMinFreezeDamageTicks()), 0);

                string += String.format(Locale.ROOT, " Warmth: %.2f Frozen: %d", warmth, frozenTicks);
            }

            list.add(string);
        }

        chunkGenerator.getDebugHudText(list, noiseConfig, blockPos);
    }

}
