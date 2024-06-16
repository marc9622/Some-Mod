package somemod.magic.entity.render.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import somemod.magic.entity.mob.GhostEntity;

@Environment(EnvType.CLIENT)
public class GhostEntityModel extends SinglePartEntityModel<GhostEntity> {

    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart head;

    public GhostEntityModel(ModelPart root) {
        super(RenderLayer::getEntityTranslucent);
        this.root = root.getChild(EntityModelPartNames.ROOT);
        this.body = this.root.getChild(EntityModelPartNames.BODY);
        this.head = this.root.getChild(EntityModelPartNames.HEAD);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData rootData = modelData.getRoot().addChild(EntityModelPartNames.ROOT, ModelPartBuilder.create(), ModelTransform.pivot(0.0f, -2.5f, 0.0f));
		rootData.addChild(
            EntityModelPartNames.HEAD,
            ModelPartBuilder.create().uv(0, 0).cuboid(-2.5F, -5.0F, -2.5F, 5.0F, 5.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 20.0F, 0.0F)
        );
		rootData.addChild(
            EntityModelPartNames.BODY,
            ModelPartBuilder.create().uv(0, 10).cuboid(-1.5F, 0.0F, -1.0F, 3.0F, 4.0F, 2.0F, new Dilation(0.0F)).uv(0, 16).cuboid(-1.5F, 1.0F, -1.0F, 3.0F, 5.0F, 2.0F, new Dilation(-0.2F)), ModelTransform.pivot(0.0F, 20.0F, 0.0F)
        );
        return TexturedModelData.of(modelData, 32, 32);
    }

    @Override
	public void setAngles(GhostEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.head.yaw = headYaw * 0.017453292f;
		this.head.pitch = headPitch * 0.017453292f;
        this.body.pitch = 0.15707964f;
	}

    @Override
    public ModelPart getPart() {
        return this.root;
    }
}
