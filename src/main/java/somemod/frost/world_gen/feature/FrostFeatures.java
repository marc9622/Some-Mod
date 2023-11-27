package somemod.frost.world_gen.feature;

import net.minecraft.world.gen.feature.Feature;
import somemod.SomeMod;
import somemod.common.world_gen.feature.SingleChestFeature;
import somemod.common.world_gen.feature.SingleChestFeatureConfig;

public class FrostFeatures {

    public static final Feature<SingleChestFeatureConfig> SPRUCE_CHEST = SomeMod.registerFeature("spruce_chest", new SingleChestFeature(SingleChestFeatureConfig.CODEC));
    public static final Feature<SingleChestFeatureConfig> ICE_CHEST    = SomeMod.registerFeature("ice_chest",    new SingleChestFeature(SingleChestFeatureConfig.CODEC));

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}

}
