package de.aelpecyem.besmirchment.mixin.client;

import de.aelpecyem.besmirchment.client.BesmirchmentClient;
import de.aelpecyem.besmirchment.common.entity.interfaces.DyeableEntity;
import de.aelpecyem.besmirchment.common.item.WitchyDyeItem;
import de.aelpecyem.besmirchment.common.registry.BSMTransformations;
import moriyashiine.bewitchment.client.renderer.entity.living.GhostEntityRenderer;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.awt.*;

@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin extends EntityRenderer {
    protected LivingEntityRendererMixin(EntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }

    @Inject(method = "getRenderLayer", at = @At("HEAD"), cancellable = true)
    private void getRenderLayer(LivingEntity entity, boolean showBody, boolean translucent, boolean showOutline, CallbackInfoReturnable<RenderLayer> cir){
        if (BSMTransformations.isLich(entity, true)){
            cir.setReturnValue(RenderLayer.getEntityTranslucent(this.getTexture(entity)));
        }
    }

    @SuppressWarnings("UnresolvedMixinReference")
    @ModifyArgs(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/model/EntityModel;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;IIFFFF)V"))
    private void ghostify(Args args, LivingEntity entity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if (BSMTransformations.isLich(entity, true)) {
            args.set(2, LightmapTextureManager.pack(15, 15));
            if (entity instanceof DyeableEntity && (((DyeableEntity) entity).getColor() >= 0 || ((DyeableEntity) entity).getColor() == WitchyDyeItem.FUNNI_NUMBER)) {
                Vector3f rgb = new Vector3f(Vec3d.unpackRgb(((DyeableEntity) entity).getColor() == WitchyDyeItem.FUNNI_NUMBER ? BesmirchmentClient.HSBtoRGB(((entity.age + g) % 100) / 100F, 1,1) : ((DyeableEntity) entity).getColor()));
                args.set(4, rgb.getX());
                args.set(5, rgb.getY());
                args.set(6, rgb.getZ());
            }else{
                args.set(4, 0.5F);
                args.set(5, 1F);
                args.set(6, 0.5F);
            }
            args.set(7, 0.6F);
        }
    }
}
