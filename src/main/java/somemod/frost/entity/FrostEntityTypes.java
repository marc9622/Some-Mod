package somemod.frost.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.world.Heightmap;
import somemod.SomeMod;
import somemod.frost.entity.mob.ArcticZombieEntity;

public final class FrostEntityTypes {

    public static final EntityType<ArcticZombieEntity> ARCTIC_ZOMBIE = SomeMod.registerEntityType("arctic_zombie", 
        FabricEntityTypeBuilder.createMob().spawnGroup(SpawnGroup.MONSTER).entityFactory( ArcticZombieEntity::new).dimensions(EntityDimensions.fixed(0.6f, 1.95f)).trackRangeChunks(8).spawnRestriction(SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ArcticZombieEntity::canSpawn).build());

    public static void notifyFabric() {/* This is just here to make sure the class is loaded */}

}
