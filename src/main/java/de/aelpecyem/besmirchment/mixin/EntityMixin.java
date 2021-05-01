package de.aelpecyem.besmirchment.mixin;

import de.aelpecyem.besmirchment.common.entity.interfaces.TameableDemon;
import de.aelpecyem.besmirchment.common.registry.BSMTransformations;
import moriyashiine.bewitchment.api.BewitchmentAPI;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.scoreboard.AbstractTeam;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin {
    @Inject(method = "getScoreboardTeam", at = @At("HEAD"), cancellable = true)
    public void getScoreboardTeam(CallbackInfoReturnable<AbstractTeam> cir) {
        if (this instanceof TameableDemon && ((TameableDemon) this).isTamed()) {
            LivingEntity livingEntity = ((TameableDemon) this).getOwner();
            if (livingEntity != null) {
                cir.setReturnValue(livingEntity.getScoreboardTeam());
            }
        }
    }

    @Inject(method = "isTeammate", at = @At("HEAD"), cancellable = true)
    public void isTeammate(Entity other, CallbackInfoReturnable<Boolean> cir) {
        if (this instanceof TameableDemon && ((TameableDemon) this).isTamed()) {
            LivingEntity livingEntity = ((TameableDemon) this).getOwner();
            if (other == livingEntity) {
                cir.setReturnValue(true);
            } else if (livingEntity != null) {
                cir.setReturnValue(livingEntity.isTeammate(other));
            }
        }
    }

    @ModifyVariable(method = "setPose", at = @At("HEAD"))
    private EntityPose setPose(EntityPose pose) {
        if (((Object) this) instanceof PlayerEntity) {
            if (BSMTransformations.isWerepyre((Entity) (Object) this, false)) {
                if (pose == EntityPose.FALL_FLYING || pose == EntityPose.SWIMMING) {
                    return EntityPose.STANDING;
                }
            }
        }
        return pose;
    }

    @Inject(method = "isFireImmune", at = @At("RETURN"), cancellable = true)
    private void isFireImmune(CallbackInfoReturnable<Boolean> callbackInfo) {
        if (callbackInfo.getReturnValue() && BSMTransformations.isWerepyre((Entity) (Object) this, true)) {
            callbackInfo.setReturnValue(false);
        }
    }
}
