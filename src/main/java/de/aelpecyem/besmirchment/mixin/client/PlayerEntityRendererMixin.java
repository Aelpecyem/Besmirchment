package de.aelpecyem.besmirchment.mixin.client;

import de.aelpecyem.besmirchment.common.entity.DyeableEntity;
import moriyashiine.bewitchment.api.BewitchmentAPI;
import moriyashiine.bewitchment.api.entity.BroomEntity;
import moriyashiine.bewitchment.common.entity.interfaces.TrueInvisibleAccessor;
import moriyashiine.bewitchment.common.entity.interfaces.WerewolfAccessor;
import moriyashiine.bewitchment.common.entity.living.WerewolfEntity;
import moriyashiine.bewitchment.common.entity.living.util.BWHostileEntity;
import moriyashiine.bewitchment.common.registry.BWEntityTypes;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(value = PlayerEntityRenderer.class, priority = 999)
public class PlayerEntityRendererMixin {
    @Inject(method = "render", at = @At(value = "HEAD"), cancellable = true)
    private void render(AbstractClientPlayerEntity player, float yaw, float tickDelta, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light, CallbackInfo callbackInfo) {
        if (!player.isInvisible()) {
            int color = ((DyeableEntity) player).getColor();
            if (BewitchmentAPI.isWerewolf(player, false) && color > -1) {
                if (!((TrueInvisibleAccessor) player).getTrueInvisible()) {
                    if (player.getVehicle() instanceof BroomEntity) {
                        matrixStack.translate(0.0D, MathHelper.sin((float) (player.getVehicle().age + player.getVehicle().getEntityId()) / 4.0F) / 16.0F, 0.0D);
                    }
                    WerewolfEntity entity = BWEntityTypes.WEREWOLF.create(player.world);
                    entity.getDataTracker().set(BWHostileEntity.VARIANT, ((WerewolfAccessor) player).getWerewolfVariant());
                    entity.age = player.age;
                    entity.hurtTime = player.hurtTime;
                    entity.maxHurtTime = 2147483647;
                    entity.limbDistance = player.limbDistance;
                    entity.lastLimbDistance = player.lastLimbDistance;
                    entity.limbAngle = player.limbAngle;
                    entity.headYaw = player.headYaw;
                    entity.prevHeadYaw = player.prevHeadYaw;
                    entity.bodyYaw = player.bodyYaw;
                    entity.prevBodyYaw = player.prevBodyYaw;
                    entity.handSwinging = player.handSwinging;
                    entity.handSwingTicks = player.handSwingTicks;
                    entity.handSwingProgress = player.handSwingProgress;
                    entity.lastHandSwingProgress = player.lastHandSwingProgress;
                    entity.pitch = player.pitch;
                    entity.prevPitch = player.prevPitch;
                    entity.preferredHand = player.preferredHand;
                    entity.setStackInHand(Hand.MAIN_HAND, player.getMainHandStack());
                    entity.setStackInHand(Hand.OFF_HAND, player.getOffHandStack());
                    entity.setCurrentHand(player.getActiveHand() == null ? Hand.MAIN_HAND : player.getActiveHand());
                    entity.setSneaking(player.isSneaking());
                    entity.setPose(player.getPose());
                    ((DyeableEntity) entity).setColor(color);
                    if (player.hasVehicle()) {
                        entity.startRiding(player.getVehicle(), true);
                    }
                    float width = 1.0F / (entity.getType().getWidth() / EntityType.PLAYER.getWidth());
                    matrixStack.scale(width, 1.0F / (entity.getType().getHeight() / EntityType.PLAYER.getHeight()), width);
                    MinecraftClient.getInstance().getEntityRenderDispatcher().getRenderer(entity).render(entity, yaw, tickDelta, matrixStack, vertexConsumerProvider, light);
                }
                callbackInfo.cancel();
            }
        }
    }
}
