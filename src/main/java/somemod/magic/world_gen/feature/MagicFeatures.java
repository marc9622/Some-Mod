package somemod.magic.world_gen.feature;

import net.minecraft.world.gen.feature.Feature;
import somemod.SomeMod;
import somemod.common.world_gen.feature.SingleChestFeature;
import somemod.common.world_gen.feature.SingleChestFeatureConfig;

public class MagicFeatures {
    
    public static final Feature<SingleChestFeatureConfig> FORGOTTEN_CHEST = SomeMod.registerFeature("forgotten_chest", new SingleChestFeature(SingleChestFeatureConfig.CODEC));

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}

}
