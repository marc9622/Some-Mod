package somemod.frost.entity.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ZombieEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ArcticZombieEntityRenderer extends ZombieEntityRenderer {

    private static final Identifier TEXTURE = new Identifier("textures/entity/zombie/husk.png"); //SomeMod.id("textures/entity/zombie/arctic_zombie.png");

    public ArcticZombieEntityRenderer(EntityRendererFactory.Context context) {
        super(context, EntityModelLayers.HUSK, EntityModelLayers.HUSK_INNER_ARMOR, EntityModelLayers.HUSK_OUTER_ARMOR);
    }

    @Override
    protected void scale(ZombieEntity zombieEntity, MatrixStack matrixStack, float f) {
        float g = 1.0625f;
        matrixStack.scale(g, g, g);
        super.scale(zombieEntity, matrixStack, f);
    }

    @Override
    public Identifier getTexture(ZombieEntity zombieEntity) {
        return TEXTURE;
    }
}
