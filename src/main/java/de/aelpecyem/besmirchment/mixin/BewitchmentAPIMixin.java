package de.aelpecyem.besmirchment.mixin;

import de.aelpecyem.besmirchment.common.registry.BSMTransformations;
import moriyashiine.bewitchment.api.BewitchmentAPI;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = BewitchmentAPI.class, remap = false)
public class BewitchmentAPIMixin {
    @Inject(method = "isVampire(Lnet/minecraft/class_1297;Z)Z", at = @At("HEAD"), cancellable = true)
    private static void isVampire(Entity entity, boolean includeHumanForm, CallbackInfoReturnable<Boolean> cir){
        if (BSMTransformations.isWerepyre(entity, includeHumanForm)){
            cir.setReturnValue(includeHumanForm);
        }
    }

    @Inject(method = "isWerewolf(Lnet/minecraft/class_1297;Z)Z", at = @At("HEAD"), cancellable = true)
    private static void isWerewolf(Entity entity, boolean includeHumanForm, CallbackInfoReturnable<Boolean> cir){
        if (BSMTransformations.isWerepyre(entity, includeHumanForm)){
            cir.setReturnValue(true);
        }
    }
}
