package somemod.crystal.world_gen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.gen.feature.FeatureConfig;

public record EndCrystalGlassPillarConfig(int minHeight, int maxHeight) implements FeatureConfig {
    
    public static Codec<EndCrystalGlassPillarConfig> CODEC = RecordCodecBuilder.create(
        instance ->
            instance.group(
                Codecs.POSITIVE_INT.fieldOf("min_height").forGetter(EndCrystalGlassPillarConfig::minHeight),
                Codecs.POSITIVE_INT.fieldOf("max_height").forGetter(EndCrystalGlassPillarConfig::maxHeight))
            .apply(instance, EndCrystalGlassPillarConfig::new));

    public int maxHeight() {
        return maxHeight;
    }

    public int minHeight() {
        return minHeight;
    }
}
