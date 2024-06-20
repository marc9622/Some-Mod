package somemod.magic.entity.attribute;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.mob.MobEntity;
import somemod.magic.entity.MagicEntityTypes;

public final class MagicDefaultEntityAttributes {

    public static void registerEntityAttributes() {
        FabricDefaultAttributeRegistry.register(MagicEntityTypes.GHOST, MobEntity.createMobAttributes());
    }
    
}
