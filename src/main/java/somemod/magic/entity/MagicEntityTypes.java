package somemod.magic.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import somemod.SomeMod;
import somemod.magic.entity.mob.GhostEntity;

public final class MagicEntityTypes {

    public static final EntityType<GhostEntity> GHOST = SomeMod.registerEntityType("ghost", 
        FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, GhostEntity::new).dimensions(EntityDimensions.fixed(1.0f, 1.0f)).fireImmune().build());

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}

}
