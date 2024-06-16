package somemod.magic.entity.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import somemod.magic.entity.mob.GhostEntity;
import somemod.magic.entity.render.model.GhostEntityModel;
import somemod.magic.entity.render.model.MagicEntityModelLayers;

@Environment(EnvType.CLIENT)
public class GhostEntityRenderer extends MobEntityRenderer<GhostEntity, GhostEntityModel> {

    private static final Identifier TEXTURE = new Identifier("textures/entity/illager/vex.png"); //SomeMod.id("textures/entity/ghost.png");

    public GhostEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new GhostEntityModel(context.getPart(MagicEntityModelLayers.GHOST)), 0.3f);
    }

    @Override
    public Identifier getTexture(GhostEntity entity) {
        return TEXTURE;
    }

}
