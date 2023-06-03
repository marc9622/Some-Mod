package somemod.magic.world_gen.feature;

import net.minecraft.world.gen.feature.Feature;
import somemod.SomeMod;

public class MagicFeatures {
    
    public static final Feature<ForgottenChestFeatureConfig> FORGOTTEN_CHEST = SomeMod.registerFeature("forgotten_chest", new ForgottenChestFeature(ForgottenChestFeatureConfig.CODEC));

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}

}
