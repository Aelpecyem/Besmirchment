package de.aelpecyem.besmirchment.mixin;

import de.aelpecyem.besmirchment.common.entity.TameableDemon;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MobEntity.class)
public class MobEntityMixin {
    @Environment(EnvType.CLIENT)
    @Inject(method = "handleStatus", at = @At("HEAD"))
    public void handleStatus(byte status, CallbackInfo ci) {
        if (this instanceof TameableDemon) {
            if (status == 7) {
                ((TameableDemon) this).showEmoteParticle(true);
            } else if (status == 6) {
                ((TameableDemon) this).showEmoteParticle(false);
            }
        }
    }

    @Inject(method = "canTarget(Lnet/minecraft/entity/LivingEntity;)Z", at = @At("HEAD"), cancellable = true)
    public void canTarget(LivingEntity target, CallbackInfoReturnable<Boolean> cir) {
        if (this instanceof TameableDemon && ((TameableDemon) this).isOwner(target)){
            cir.setReturnValue(false);
        }
    }
}
