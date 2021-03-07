package de.aelpecyem.besmirchment.client.renderer;

import de.aelpecyem.besmirchment.client.model.BeelzebubEntityModel;
import de.aelpecyem.besmirchment.common.Besmirchment;
import de.aelpecyem.besmirchment.common.entity.BeelzebubEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class BeelzebubEntityRenderer extends MobEntityRenderer<BeelzebubEntity, BeelzebubEntityModel<BeelzebubEntity>> {
    private static final Identifier TEXTURE = Besmirchment.id("textures/entity/beelzebub.png");

    public BeelzebubEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new BeelzebubEntityModel<>(), 0.5F);
        this.addFeature(new HeldItemFeatureRenderer<>(this));
    }

    @Override
    public void render(BeelzebubEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if (mobEntity.isSneaking()){
            matrixStack.translate(0, 0.1, 0);
        }
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    public Identifier getTexture(BeelzebubEntity entity) {
        return TEXTURE;
    }
}