package somemod.magic.entity.attribute;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import somemod.magic.entity.MagicEntityTypes;
import somemod.magic.entity.mob.GhostEntity;

public final class MagicDefaultEntityAttributes {

    public static void registerEntityAttributes() {
        FabricDefaultAttributeRegistry.register(MagicEntityTypes.GHOST, GhostEntity.createMobAttributes());
    }
    
}
