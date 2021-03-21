package de.aelpecyem.besmirchment.mixin.client;

import de.aelpecyem.besmirchment.client.BesmirchmentClient;
import de.aelpecyem.besmirchment.common.registry.BSMTransformations;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(InGameOverlayRenderer.class)
public class InGameOverlayRendererMixin {
    @Inject(method = "getInWallBlockState", at = @At("HEAD"), cancellable = true)
    private static void getInWallBlockState(PlayerEntity playerEntity, CallbackInfoReturnable<BlockState> cir){
        if (BSMTransformations.isLich(playerEntity, true)){
            cir.setReturnValue(null);
        }
    }
}
