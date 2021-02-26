package de.aelpecyem.besmirchment.client.renderer;

import de.aelpecyem.besmirchment.common.Besmirchment;
import de.aelpecyem.besmirchment.common.entity.FinalBroomEntity;
import moriyashiine.bewitchment.api.client.model.BroomEntityModel;
import moriyashiine.bewitchment.api.client.renderer.BroomEntityRenderer;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

public class FinalBroomEntityRenderer extends BroomEntityRenderer<FinalBroomEntity> {
    private final BroomEntityModel bristleModel = new BroomEntityModel(); //because the original model is private;; agony
    private static final Identifier TEXTURE = Besmirchment.id("textures/entity/broom/final.png");
    private static final Identifier TEXTURE_BRISTLES = Besmirchment.id("textures/entity/broom/final_bristles.png");
    public FinalBroomEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher);
    }

    @Override
    public void render(FinalBroomEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
        int age = entity.age / 25 + entity.getEntityId();
        int totalColor = DyeColor.values().length;
        int currentColor = age % totalColor;
        int nextColor = (age + 1) % totalColor;
        float delta = ((float)(entity.age % 25) + tickDelta) / 25.0F;
        float[] currentColors = SheepEntity.getRgbColor(DyeColor.byId(currentColor));
        float[] nextColors = SheepEntity.getRgbColor(DyeColor.byId(nextColor));
        matrices.push();
        matrices.translate(0.0D, -1.0D, 0.0D);
        matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(90.0F - yaw + 90.0F));
        matrices.translate(0.0D, 0.0D, -0.35D);
        this.bristleModel.setAngles(entity, yaw, 0.0F, (float)(entity.age + entity.getEntityId()), 0.0F, 0.0F);
        this.bristleModel.render(matrices, vertexConsumers.getBuffer(this.bristleModel.getLayer(this.getTexture(entity))), light, OverlayTexture.DEFAULT_UV, currentColors[0] * (1.0F - delta) + nextColors[0] * delta, currentColors[1] * (1.0F - delta) + nextColors[1] * delta, currentColors[2] * (1.0F - delta) + nextColors[2] * delta, 1.0F);
        matrices.pop();
    }

    @Override
    public Identifier getTexture(FinalBroomEntity entity) {
        return TEXTURE;
    }
}
