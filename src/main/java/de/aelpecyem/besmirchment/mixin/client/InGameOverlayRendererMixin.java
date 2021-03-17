package de.aelpecyem.besmirchment.mixin.client;

import de.aelpecyem.besmirchment.client.BesmirchmentClient;
import de.aelpecyem.besmirchment.common.registry.BSMTransformations;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameOverlayRenderer.class)
public class InGameOverlayRendererMixin {
    @Inject(method = "renderInWallOverlay", at = @At("HEAD"), cancellable = true)
    private static void getInWallBlockState(MinecraftClient minecraftClient, Sprite sprite, MatrixStack matrixStack, CallbackInfo ci){
        if (BSMTransformations.isLich(minecraftClient.player, true)){
            BesmirchmentClient.fogTicks = 10;
            ci.cancel();
        }
    }
}
