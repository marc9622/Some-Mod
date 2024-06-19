package somemod.frost.entity.attribute;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import somemod.frost.entity.FrostEntityTypes;
import somemod.frost.entity.mob.ArcticZombieEntity;

public final class FrostDefaultEntityAttributes {

    public static void registerEntityAttributes() {
        FabricDefaultAttributeRegistry.register(FrostEntityTypes.ARCTIC_ZOMBIE, ArcticZombieEntity.createMobAttributes());
    }

}
