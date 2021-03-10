package de.aelpecyem.besmirchment.mixin;

import de.aelpecyem.besmirchment.common.packet.SparklePacket;
import de.aelpecyem.besmirchment.common.registry.BSMStatusEffects;
import de.aelpecyem.besmirchment.common.registry.BSMTransformations;
import moriyashiine.bewitchment.api.BewitchmentAPI;
import moriyashiine.bewitchment.api.interfaces.entity.BloodAccessor;
import moriyashiine.bewitchment.common.entity.interfaces.RespawnTimerAccessor;
import moriyashiine.bewitchment.common.entity.interfaces.WerewolfAccessor;
import moriyashiine.bewitchment.common.misc.BWUtil;
import moriyashiine.bewitchment.common.network.packet.TransformationAbilityPacket;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = BWUtil.class, remap = false)
public class BWUtilMixin {
    @Inject(method = "doVampireLogic(Lnet/minecraft/class_1657;Z)V", at = @At("HEAD"), cancellable = true)
    private static void doVampireLogic(PlayerEntity player, boolean alternateForm, CallbackInfo ci) {
        if (BSMTransformations.isWerepyre(player, true)) {
            boolean pledged = BSMTransformations.hasWerepyrePledge(player);
            if (((RespawnTimerAccessor) player).getRespawnTimer() <= 0 && player.world.isDay() && !player.world.isRaining() && player.world.isSkyVisible(player.getBlockPos())) {
                if (!player.hasStatusEffect(BSMStatusEffects.SUNSCREEN)){
                    player.setOnFireFor(8);
                }else if (player.age % 20 == 0){
                    SparklePacket.send(player);
                }
            }
            HungerManager hungerManager = player.getHungerManager();
            if (((BloodAccessor) player).getBlood() > 0) {
                if (player.age % (pledged ? 30 : 40) == 0) {
                    if (player.getHealth() < player.getMaxHealth()) {
                        player.heal(1);
                        hungerManager.addExhaustion(3);
                    }
                    if ((hungerManager.isNotFull() || hungerManager.getSaturationLevel() < 10) && ((BloodAccessor) player).drainBlood(1, false)) {
                        hungerManager.add(1, 20);
                    }
                }
                BSMTransformations.handleNourish(player);
            }
            ci.cancel();
        }
    }

    @Redirect(method = "doVampireLogic(Lnet/minecraft/class_1657;Z)V", at = @At(value = "INVOKE", target = "net/minecraft/entity/player/PlayerEntity.setOnFireFor(I)V", remap = true))
    private static void setOnFire(PlayerEntity player, int duration){
        if (!player.hasStatusEffect(BSMStatusEffects.SUNSCREEN)){
            player.setOnFireFor(duration);
        }else if(player.age % 20 == 0){
            SparklePacket.send(player);
        }
    }

    @Inject(method = "doWerewolfLogic(Lnet/minecraft/class_1657;Z)V", at = @At("HEAD"), cancellable = true)
    private static void doWerewolfLogic(PlayerEntity player, boolean alternateForm, CallbackInfo ci){
        if (BSMTransformations.isWerepyre(player, true)) {
            boolean forced = ((WerewolfAccessor) player).getForcedTransformation();
            if (!alternateForm && BewitchmentAPI.getMoonPhase(player.world) == 0 && player.world.isNight() && player.world.isSkyVisible(player.getBlockPos())) {
                TransformationAbilityPacket.useAbility(player, true);
                ((WerewolfAccessor) player).setForcedTransformation(true);
            } else if (alternateForm && forced && (player.world.isDay() || BewitchmentAPI.getMoonPhase(player.world) != 0)) {
                TransformationAbilityPacket.useAbility(player, true);
                ((WerewolfAccessor) player).setForcedTransformation(false);
            }
            if (alternateForm) {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, Integer.MAX_VALUE, 0, true, false));
                player.getArmorItems().forEach(stack -> player.dropStack(stack.split(1)));
                if (BWUtil.isTool(player.getMainHandStack())) {
                    player.dropStack(player.getMainHandStack().split(1));
                }
                if (BWUtil.isTool(player.getOffHandStack())) {
                    player.dropStack(player.getOffHandStack().split(1));
                }
                if (!forced && !BSMTransformations.hasWerepyrePledge(player)) {
                    TransformationAbilityPacket.useAbility(player, true);
                }
            }
            ci.cancel();
        }
    }
}
