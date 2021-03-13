package de.aelpecyem.besmirchment.mixin.client;

import de.aelpecyem.besmirchment.client.renderer.TamedDemonEntityRenderer;
import moriyashiine.bewitchment.client.model.entity.living.DemonEntityModel;
import moriyashiine.bewitchment.client.renderer.entity.living.DemonEntityRenderer;
import moriyashiine.bewitchment.common.entity.living.DemonEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(value = DemonEntityRenderer.class, remap = false)
public abstract class DemonEntityRendererMixin extends MobEntityRenderer<DemonEntity, DemonEntityModel<DemonEntity>> {
    public DemonEntityRendererMixin(EntityRenderDispatcher entityRenderDispatcher, DemonEntityModel entityModel, float f) {
        super(entityRenderDispatcher, entityModel, f);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void injectConstructor(EntityRenderDispatcher entityRenderDispatcher, CallbackInfo ci) {
        this.addFeature(new TamedDemonEntityRenderer((DemonEntityRenderer) (Object) this));
    }
}
