package de.aelpecyem.besmirchment.client.renderer;

import de.aelpecyem.besmirchment.common.Besmirchment;
import de.aelpecyem.besmirchment.common.entity.TameableDemon;
import moriyashiine.bewitchment.client.model.entity.living.DemonEntityModel;
import moriyashiine.bewitchment.common.entity.living.DemonEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class TamedDemonEntityRenderer extends FeatureRenderer<DemonEntity, DemonEntityModel<DemonEntity>> {
    private static final Identifier COLLAR_TEXTURE = Besmirchment.id("textures/entity/demon_collar.png");

    public TamedDemonEntityRenderer(FeatureRendererContext<DemonEntity, DemonEntityModel<DemonEntity>> featureRendererContext) {
        super(featureRendererContext);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, DemonEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (((TameableDemon)entity).isTamed()){
            render(this.getContextModel(), this.getContextModel(), COLLAR_TEXTURE, matrices, vertexConsumers, light, entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch, tickDelta, 1, 1, 1);
        }
    }
}
