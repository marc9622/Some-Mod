package somemod.crystal.world.feature;

import java.util.List;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import somemod.SomeMod;
import somemod.crystal.block.CrystalBlocks;

/**
 * This class is used to register the configured features related to crystals.
 */
public class CrystalConfiguredFeatures {
    
    public static final RegistryKey<ConfiguredFeature<?, ?>> END_CRYSTAL_GLASS_CONFIGURED_FEATURE = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, SomeMod.id("end_crystal_glass_pillar"));

    //public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_AMETHYST = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, SomeMod.id("ore_amethyst"));
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_CITRINE  = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, SomeMod.id("ore_citrine"));
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_RUBY     = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, SomeMod.id("ore_ruby"));
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_SAPPHIRE = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, SomeMod.id("ore_sapphire"));

    /**
     * Registers the configured features related to crystals.
     * Is called by the {@link somemod.datagen.SomeModDataGeneration} class.
     */
    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> featureRegisterable) {

        TagMatchRuleTest stoneRuleTest = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        TagMatchRuleTest deepslateRuleTest = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        ConfiguredFeatures.register(featureRegisterable, END_CRYSTAL_GLASS_CONFIGURED_FEATURE, CrystalFeatures.END_CRYSTAL_GLASS_PILLAR, new EndCrystalGlassPillarConfig(1, 5));

        ConfiguredFeatures.register(featureRegisterable, ORE_CITRINE, Feature.ORE,
            new OreFeatureConfig(
                List.of(
                    OreFeatureConfig.createTarget(stoneRuleTest, CrystalBlocks.CITRINE_ORE.getDefaultState()),
                    OreFeatureConfig.createTarget(deepslateRuleTest, CrystalBlocks.CITRINE_ORE.getDefaultState())),
                3));

        ConfiguredFeatures.register(featureRegisterable, ORE_RUBY, Feature.ORE,
            new OreFeatureConfig(
                List.of(
                    OreFeatureConfig.createTarget(stoneRuleTest, CrystalBlocks.RUBY_ORE.getDefaultState()),
                    OreFeatureConfig.createTarget(deepslateRuleTest, CrystalBlocks.RUBY_ORE.getDefaultState())),
                3));

        ConfiguredFeatures.register(featureRegisterable, ORE_SAPPHIRE, Feature.ORE,
            new OreFeatureConfig(
                List.of(
                    OreFeatureConfig.createTarget(stoneRuleTest, CrystalBlocks.SAPPHIRE_ORE.getDefaultState()),
                    OreFeatureConfig.createTarget(deepslateRuleTest, CrystalBlocks.SAPPHIRE_ORE.getDefaultState())),
                3));

    }

}