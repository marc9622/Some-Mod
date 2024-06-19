package somemod.frost.entity.render.model;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import somemod.SomeMod;

public final class FrostEntityModelLayers {

    public static final EntityModelLayer ARCTIC_ZOMBIE = new EntityModelLayer(SomeMod.id("arctic_zombie"), "main");
    public static final EntityModelLayer ARCTIC_ZOMBIE_INNER_ARMOR = new EntityModelLayer(SomeMod.id("arctic_zombie"), "inner_armor");
    public static final EntityModelLayer ARCTIC_ZOMBIE_OUTER_ARMOR = new EntityModelLayer(SomeMod.id("arctic_zombie"), "outer_armor");
}
