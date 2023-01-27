package somemod.crystal.world_gen.feature;

import java.util.List;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import somemod.SomeMod;
import somemod.crystal.block.CrystalBlocks;

/**
 * This class is used to register the configured features related to crystals.
 */
public class CrystalConfiguredFeatures {
    
    public static final RegistryKey<ConfiguredFeature<?, ?>> END_CRYSTAL_GLASS_CONFIGURED_FEATURE = SomeMod.keyOf(RegistryKeys.CONFIGURED_FEATURE, "end_crystal_glass_pillar");

    //public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_AMETHYST = SomeMod.keyOf(RegistryKeys.CONFIGURED_FEATURE, "ore_amethyst");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_CITRINE  = SomeMod.keyOf(RegistryKeys.CONFIGURED_FEATURE, "ore_citrine");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_RUBY     = SomeMod.keyOf(RegistryKeys.CONFIGURED_FEATURE, "ore_ruby");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_SAPPHIRE = SomeMod.keyOf(RegistryKeys.CONFIGURED_FEATURE, "ore_sapphire");

    /**
     * Registers the configured features related to crystals.
     * Is called by the {@link somemod.datagen.SomeModDataGeneration} class.
     */
    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> registerable) {

        TagMatchRuleTest stoneRuleTest = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        TagMatchRuleTest deepslateRuleTest = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        SomeMod.registerConfiguredFeature(registerable, END_CRYSTAL_GLASS_CONFIGURED_FEATURE, CrystalFeatures.END_CRYSTAL_GLASS_PILLAR, new EndCrystalGlassPillarConfig(1, 5));

        SomeMod.registerConfiguredFeature(registerable, ORE_CITRINE, Feature.ORE,
            new OreFeatureConfig(
                List.of(
                    OreFeatureConfig.createTarget(stoneRuleTest, CrystalBlocks.CITRINE_ORE.getDefaultState()),
                    OreFeatureConfig.createTarget(deepslateRuleTest, CrystalBlocks.CITRINE_ORE.getDefaultState())),
                3));

                SomeMod.registerConfiguredFeature(registerable, ORE_RUBY, Feature.ORE,
            new OreFeatureConfig(
                List.of(
                    OreFeatureConfig.createTarget(stoneRuleTest, CrystalBlocks.RUBY_ORE.getDefaultState()),
                    OreFeatureConfig.createTarget(deepslateRuleTest, CrystalBlocks.RUBY_ORE.getDefaultState())),
                3));

        SomeMod.registerConfiguredFeature(registerable, ORE_SAPPHIRE, Feature.ORE,
            new OreFeatureConfig(
                List.of(
                    OreFeatureConfig.createTarget(stoneRuleTest, CrystalBlocks.SAPPHIRE_ORE.getDefaultState()),
                    OreFeatureConfig.createTarget(deepslateRuleTest, CrystalBlocks.SAPPHIRE_ORE.getDefaultState())),
                3));

    }

}