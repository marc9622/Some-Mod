package somemod.enchanting.world_gen.structure.processor;

import java.util.Map;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.structure.processor.StructureProcessorRule;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.biome.Biome;

public class StructureProcessorBiomeRule {

    public static final StructureProcessorRule NOP = new StructureProcessorRule(null, null, null) {
        @Override
        public boolean test(BlockState input, BlockState currentState, BlockPos originalPos, BlockPos currentPos, BlockPos pivot, Random random) {
            return false;
        }
        @Override
        public BlockState getOutputState() {
            throw new UnsupportedOperationException();
        }
        @Override
        public NbtCompound getOutputNbt() {
            throw new UnsupportedOperationException();
        }
    };

    public static final Codec<StructureProcessorBiomeRule> CODEC =
        RecordCodecBuilder.create(
            instance -> instance.group(
                Codec.unboundedMap(
                    Codec.either(Biome.REGISTRY_CODEC, TagKey.codec(RegistryKeys.BIOME)),
                    StructureProcessorRule.CODEC)
            .fieldOf("rules").forGetter(rule -> rule.rulesMap))
        .apply(instance, StructureProcessorBiomeRule::new));

    private final Map<Either<RegistryEntry<Biome>, TagKey<Biome>>, StructureProcessorRule> rulesMap;
    private StructureProcessorRule defaultRule = NOP;

    public StructureProcessorBiomeRule(Map<Either<RegistryEntry<Biome>, TagKey<Biome>>, StructureProcessorRule> rules) {
        this.rulesMap = rules;
    }

    public StructureProcessorBiomeRule(Map<Either<RegistryEntry<Biome>, TagKey<Biome>>, StructureProcessorRule> rules, StructureProcessorRule defaultRule) {
        this.rulesMap = rules;
        this.defaultRule = defaultRule;
    }

    public StructureProcessorRule getRule(RegistryEntry<Biome> biome) {
        return rulesMap
                .keySet()
                .stream()
                .filter(
                    either -> either.map(
                        biomeEntry -> biomeEntry == biome,
                        tag -> biome.isIn(tag)))
                .findFirst()
                .map(either -> rulesMap.get(either))
                .orElse(defaultRule);
    }

    public static class Builder {
        private final Map<Either<RegistryEntry<Biome>, TagKey<Biome>>, StructureProcessorRule> rulesMap = new java.util.HashMap<>();
        private StructureProcessorRule defaultRule = NOP;

        public Builder add(RegistryEntry<Biome> biome, StructureProcessorRule rule) {
            rulesMap.put(Either.left(biome), rule);
            return this;
        }

        public Builder add(TagKey<Biome> tag, StructureProcessorRule rule) {
            rulesMap.put(Either.right(tag), rule);
            return this;
        }

        public Builder setDefaultRule(StructureProcessorRule rule) {
            defaultRule = rule;
            return this;
        }

        public StructureProcessorBiomeRule build() {
            return new StructureProcessorBiomeRule(rulesMap, defaultRule);
        }
    }

}
