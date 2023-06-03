package somemod.magic.world_gen.feature;

import java.util.Optional;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.gen.feature.FeatureConfig;

public record ForgottenChestFeatureConfig(Optional<Text> name, Identifier lootTable) implements FeatureConfig {
    
    public static Codec<ForgottenChestFeatureConfig> CODEC = RecordCodecBuilder.create(
        instance ->
            instance.group(
                Codecs.TEXT.optionalFieldOf("custom_name").forGetter(ForgottenChestFeatureConfig::name),
                Identifier.CODEC.fieldOf("loot_table").forGetter(ForgottenChestFeatureConfig::lootTable))
            .apply(instance, ForgottenChestFeatureConfig::new));

}
