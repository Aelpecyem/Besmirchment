package de.aelpecyem.besmirchment.client.renderer;

import de.aelpecyem.besmirchment.common.Besmirchment;
import de.aelpecyem.besmirchment.common.entity.FinalBroomEntity;
import de.aelpecyem.besmirchment.common.registry.BSMUtil;
import moriyashiine.bewitchment.api.client.model.BroomEntityModel;
import moriyashiine.bewitchment.api.client.renderer.BroomEntityRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

@Environment(EnvType.CLIENT)
public class FinalBroomEntityRenderer extends BroomEntityRenderer<FinalBroomEntity> {
    private final BroomEntityModel bristleModel = new BroomEntityModel(); //because the original model is private;; agony
    private static final Identifier TEXTURE = Besmirchment.id("textures/entity/broom/final_broom_base.png");
    private static final Identifier TEXTURE_BRISTLES = Besmirchment.id("textures/entity/broom/final_broom_tintable.png");
    public FinalBroomEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher);
    }

    @Override
    public void render(FinalBroomEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
        Vector3f rgb = new Vector3f(Vec3d.unpackRgb(BSMUtil.HSBtoRGB(((entity.age + tickDelta) % 100) / 100F, 1, 1)));
        matrices.push();
        matrices.translate(0.0D, -1.0D, 0.0D);
        matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(90.0F - yaw + 90.0F));
        matrices.translate(0.0D, 0.0D, -0.35D);
        bristleModel.setAngles(entity, yaw, 0.0F, (float)(entity.age + entity.getEntityId()), 0.0F, 0.0F);
        bristleModel.render(matrices, vertexConsumers.getBuffer(bristleModel.getLayer(TEXTURE_BRISTLES)), 15728640, OverlayTexture.DEFAULT_UV, rgb.getX(), rgb.getY(), rgb.getZ(), 1.0F);
        matrices.pop();
    }

    @Override
    public Identifier getTexture(FinalBroomEntity entity) {
        return TEXTURE;
    }
}
