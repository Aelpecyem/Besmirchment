package de.aelpecyem.besmirchment.client.renderer;

import de.aelpecyem.besmirchment.common.Besmirchment;
import de.aelpecyem.besmirchment.common.entity.FinalBroomEntity;
import moriyashiine.bewitchment.api.client.renderer.BroomEntityRenderer;
import moriyashiine.bewitchment.common.Bewitchment;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

public class FinalBroomEntityRenderer extends BroomEntityRenderer<FinalBroomEntity> {
    private static final Identifier TEXTURE = Besmirchment.id("textures/entity/broom/final.png");
    public FinalBroomEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher);
    }

    @Override
    public Identifier getTexture(FinalBroomEntity entity) {
        return TEXTURE;
    }
}
