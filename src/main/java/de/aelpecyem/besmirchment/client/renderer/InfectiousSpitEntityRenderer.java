package de.aelpecyem.besmirchment.client.renderer;

import de.aelpecyem.besmirchment.common.entity.InfectiousSpitEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.model.LlamaSpitEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

@Environment(EnvType.CLIENT)
public class InfectiousSpitEntityRenderer extends EntityRenderer<InfectiousSpitEntity> {
    private static final Identifier TEXTURE = new Identifier("textures/entity/llama/spit.png");
    private final LlamaSpitEntityModel<InfectiousSpitEntity> model = new LlamaSpitEntityModel<>();

    public InfectiousSpitEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher);
    }

    public void render(InfectiousSpitEntity llamaSpitEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        matrixStack.translate(0.0D, 0.15, 0.0D);
        matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(MathHelper.lerp(g, llamaSpitEntity.prevYaw, llamaSpitEntity.yaw) - 90.0F));
        matrixStack.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(MathHelper.lerp(g, llamaSpitEntity.prevPitch, llamaSpitEntity.pitch)));
        this.model.setAngles(llamaSpitEntity, g, 0.0F, -0.1F, 0.0F, 0.0F);
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(this.model.getLayer(TEXTURE));
        Vec3d rgb = Vec3d.unpackRgb(llamaSpitEntity.getColor());
        this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, (float) rgb.x, (float) rgb.y, (float) rgb.z, 1.0F);
        matrixStack.pop();
        super.render(llamaSpitEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    public Identifier getTexture(InfectiousSpitEntity llamaSpitEntity) {
        return TEXTURE;
    }
}