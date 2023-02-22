package somemod.magic.world_gen.structure.processor;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.StructureTemplate.StructureBlockInfo;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorRule;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldView;

public class BiomeRuleStructureProcessor extends StructureProcessor {
    
    public static final Codec<BiomeRuleStructureProcessor> CODEC =
        RecordCodecBuilder.create(
            instance -> instance.group(
                StructureProcessorBiomeRule.CODEC.listOf().fieldOf("rules").forGetter(rule -> rule.rules))
            .apply(instance, BiomeRuleStructureProcessor::new));

    private final ImmutableList<StructureProcessorBiomeRule> rules;

    public BiomeRuleStructureProcessor(StructureProcessorBiomeRule rules) {
        this.rules = ImmutableList.of(rules);
    }

    public BiomeRuleStructureProcessor(List<StructureProcessorBiomeRule> rules) {
        this.rules = ImmutableList.copyOf(rules);
    }

    @Override
    public StructureBlockInfo process(WorldView world, BlockPos pos, BlockPos pivot, StructureBlockInfo originalBlockInfo, StructureBlockInfo currentBlockInfo, StructurePlacementData data) {
        
        Random random = Random.create(MathHelper.hashCode(currentBlockInfo.pos));
        BlockState blockState = world.getBlockState(currentBlockInfo.pos);

        for (StructureProcessorBiomeRule structureProcessorBiomeRule : this.rules) {
            StructureProcessorRule rule = structureProcessorBiomeRule.getRule(world.getBiome(currentBlockInfo.pos));
            if (!rule.test(currentBlockInfo.state, blockState, originalBlockInfo.pos, currentBlockInfo.pos, pivot, random))
                continue;

            return new StructureTemplate.StructureBlockInfo(currentBlockInfo.pos, rule.getOutputState(), rule.getOutputNbt());

        }
        return currentBlockInfo;
    }

    @Override
    protected StructureProcessorType<BiomeRuleStructureProcessor> getType() {
        return MagicStructureProcessorTypes.BIOME_RULE;
    }

}
