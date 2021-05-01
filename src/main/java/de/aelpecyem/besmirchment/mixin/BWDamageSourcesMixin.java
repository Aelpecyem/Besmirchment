package de.aelpecyem.besmirchment.mixin;

import de.aelpecyem.besmirchment.common.registry.BSMTransformations;
import moriyashiine.bewitchment.api.BewitchmentAPI;
import moriyashiine.bewitchment.api.interfaces.entity.BloodAccessor;
import moriyashiine.bewitchment.common.registry.BWDamageSources;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static moriyashiine.bewitchment.common.registry.BWDamageSources.isEffective;

@Mixin(value = BWDamageSources.class, remap = false)
public class BWDamageSourcesMixin {
    @Inject(method = "handleDamage", at = @At("TAIL"), cancellable = true)
    private static void handleDamage(LivingEntity entity, DamageSource source, float amount, CallbackInfoReturnable<Float> cir){
        if (BewitchmentAPI.isWeakToSilver(entity) && BewitchmentAPI.isSourceFromSilver(source)) {
            cir.setReturnValue(amount + 4);
        }
        else if (BSMTransformations.isWerepyre(entity, true)) {
            cir.setReturnValue(handleWerepyreDamage(entity, source, amount));
        }
    }

    @Unique
    private static float handleWerepyreDamage(LivingEntity entity, DamageSource source, float amount) {
        if (!isEffective(source, true)) {
            if (entity.getHealth() - amount < 1) {
                BloodAccessor bloodAccessor = (BloodAccessor) entity;
                while (entity.getHealth() - amount <= 0 && bloodAccessor.getBlood() > 0) {
                    amount--;
                    bloodAccessor.drainBlood(2, false);
                }
            }
        }
        return amount;
    }
}
