package de.aelpecyem.besmirchment.mixin;

import de.aelpecyem.besmirchment.common.Besmirchment;
import moriyashiine.bewitchment.api.BewitchmentAPI;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BewitchmentAPI.class)
public class BewitchmentAPIMixin {
    @Inject(method = "isVampire", at = @At("HEAD"), cancellable = true)
    private static void isVampire(Entity entity, boolean includeHumanForm, CallbackInfoReturnable<Boolean> cir){
        if (Besmirchment.isWerepyre(entity, includeHumanForm)){
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "isWerewolf", at = @At("HEAD"), cancellable = true)
    private static void isWerewolf(Entity entity, boolean includeHumanForm, CallbackInfoReturnable<Boolean> cir){
        if (Besmirchment.isWerepyre(entity, includeHumanForm)){
            cir.setReturnValue(true);
        }
    }
}
